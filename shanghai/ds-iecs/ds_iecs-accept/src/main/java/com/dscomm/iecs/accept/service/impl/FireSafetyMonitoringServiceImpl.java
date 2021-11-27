package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.FireSafetyMonitoringEntity;
import com.dscomm.iecs.accept.dal.po.ParticipantFeedbackEntity;
import com.dscomm.iecs.accept.dal.repository.FireSafetyMonitoringRepository;
import com.dscomm.iecs.accept.dal.repository.ParticipanFeedbackRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.FireSafetyMonitoringSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.FireSafetySaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.FireSafetyMonitoringBean;
import com.dscomm.iecs.accept.service.FireSafetyMonitoringService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.base.websocket.WebsocketBaseBean;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：出入火场登记 服务类实现
 */
@Component("fireSafetyMonitoringServiceImpl")
public class FireSafetyMonitoringServiceImpl implements FireSafetyMonitoringService {
    private static final Logger logger = LoggerFactory.getLogger(FireSafetyMonitoringServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private OrganizationService organizationService ;
    private FireSafetyMonitoringRepository fireSafetyMonitoringRepository ;
    private NotifyActionService notifyActionService ;
    private IncidentService incidentService ;
    private ServletService servletService ;
    private SystemConfigurationService systemConfigurationService ;
    private ParticipanFeedbackRepository participanFeedbackRepository;



    /**
     * 默认的构造函数
     */
    @Autowired
    public FireSafetyMonitoringServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                                           OrganizationService organizationService, FireSafetyMonitoringRepository fireSafetyMonitoringRepository,
                                           NotifyActionService notifyActionService, IncidentService incidentService, ServletService servletService,
                                           SystemConfigurationService systemConfigurationService,

                                           ParticipanFeedbackRepository participanFeedbackRepository) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.organizationService = organizationService ;
        this.fireSafetyMonitoringRepository = fireSafetyMonitoringRepository ;
        this.incidentService = incidentService ;
        this.notifyActionService = notifyActionService ;
        this.servletService = servletService ;
        this.systemConfigurationService = systemConfigurationService ;


        this.participanFeedbackRepository = participanFeedbackRepository;
    }

    /**
     * {@inheritDoc}
     *
     * @see FireSafetyMonitoringService#saveFireSafetyMonitoring(FireSafetySaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public   List<FireSafetyMonitoringBean> saveFireSafetyMonitoring(FireSafetySaveInputInfo queryBean ){
        if ( null == queryBean  || Strings.isBlank( queryBean.getIncidentId() ) ) {
            logService.infoLog(logger, "service", "saveFireSafetyMonitoring", "ParticipantSaveInputInfo or incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveFireSafetyMonitoring", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<FireSafetyMonitoringBean> res = new ArrayList<>();
            //获得机构名称map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            Long currentTime = servletService.getSystemTime() ;
            List<String> personIds=new ArrayList<>();
            queryBean.getFireSafetyMonitoringSaveInputInfo().forEach(bo->{
               personIds.add(bo.getPersonId()) ;
            });
            //根据fireSafetyType 设置进出场 1进场 0 出场
            List<FireSafetyMonitoringEntity> fireSafetyMonitoringEntityList = null ;
            if( queryBean.getFireSafetyType()!=null&&queryBean.getFireSafetyType() == 1 ){
                //保存参数转换
                //获得火场超时(分钟)
                Integer fireSceneOverTimeConfig = 30 ;
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("fireSceneOverTime") ;
                if( systemConfigurationBean != null ){
                    fireSceneOverTimeConfig = Integer.parseInt( systemConfigurationBean.getConfigValue()  ) ;
                }
                Long fireSceneOverTime = currentTime + fireSceneOverTimeConfig * 60 * 1000l  ;

                fireSafetyMonitoringEntityList  = HandleDispatchTransformUtil.transform( queryBean  , queryBean.getIncidentId() , currentTime , fireSceneOverTime  );

            }else if(queryBean.getFireSafetyType()!=null&& queryBean.getFireSafetyType() == 0  ){
                fireSafetyMonitoringEntityList = fireSafetyMonitoringRepository.findEnterFireSafetyByIncidentId( queryBean.getIncidentId() , personIds ) ;
                if( fireSafetyMonitoringEntityList != null && fireSafetyMonitoringEntityList.size() > 0 ){
                    for( FireSafetyMonitoringEntity fireSafetyMonitoringEntity : fireSafetyMonitoringEntityList ){
                        fireSafetyMonitoringEntity.setWithdrawFireTime( currentTime );
                    }
                }

            }

            if( null != fireSafetyMonitoringEntityList && fireSafetyMonitoringEntityList.size() > 0 ){

                logService.infoLog(logger, "repository", "save(dbParticipantFeedbackEntityList)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( fireSafetyMonitoringEntityList );

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbParticipantFeedbackEntityList)", String.format("repository is finished,execute time is :%sms", end - start));


                for( FireSafetyMonitoringEntity fireSafetyMonitoringEntity : fireSafetyMonitoringEntityList ){
                    FireSafetyMonitoringBean fireSafetyMonitoringBean =  HandleDispatchTransformUtil.transform( fireSafetyMonitoringEntity , organizationNameMap ) ;
                    res.add( fireSafetyMonitoringBean ) ;
                }
            }
            //修改作战人员信息表
            List<ParticipantFeedbackEntity> participantFeedbackEntities = participanFeedbackRepository.findParticipantFeedbackEntitiesByIncidentIdAndPersonIds(queryBean.getIncidentId(), personIds);
            if (participantFeedbackEntities!=null&&!participantFeedbackEntities.isEmpty()){
                for (ParticipantFeedbackEntity participantFeedbackEntity : participantFeedbackEntities) {
                    //进场
                    if (queryBean.getFireSafetyType()!=null&&queryBean.getFireSafetyType() == 1){
                        participantFeedbackEntity.setState(20);
                        participantFeedbackEntity.setEntryTime(currentTime);
                    }else if (queryBean.getFireSafetyType()!=null&& queryBean.getFireSafetyType() == 0  ){
                        //退场
                        participantFeedbackEntity.setState(30);
                        participantFeedbackEntity.setExitTime(currentTime);
                    }
                }
                accessor.save(participantFeedbackEntities);
            }

            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>() ;
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( queryBean.getIncidentId() );
            orgSet.addAll( orgIds ) ;
            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            //构建推送消息
            WebsocketBaseBean websocket = new WebsocketBaseBean() ;
            websocket.setWebsocketCode( WebsocketCodeEnum.SAVE_FIRESAFE_MONITORING.getCode() );
            websocket.setIncidentId( queryBean.getIncidentId() );
            websocket.setWebsocketBody( res );

            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_FIRESAFE_MONITORING.getCode(), websocket , orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveFireSafetyMonitoring", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveFireSafetyMonitoring", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see FireSafetyMonitoringService#findIncidentFireSafetyMonitoring( String )
     */
    @Transactional(  readOnly =  true )
    @Override
    public  List<FireSafetyMonitoringBean> findIncidentFireSafetyMonitoring( String incidentId  ){
        if (  Strings.isBlank( incidentId ) ) {
            logService.infoLog(logger, "service", "findIncidentParticipant", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentParticipant", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<FireSafetyMonitoringBean> res = new ArrayList<>();
            //获得机构名称map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;

            logService.infoLog(logger, "repository", "findParticipantFeedbackByIncidentId( incidentId )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<FireSafetyMonitoringEntity> fireSafetyMonitoringEntityList = fireSafetyMonitoringRepository.findFireSafetyMonitoringByIncidentId( incidentId ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findParticipantFeedbackByIncidentId( incidentId )", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != fireSafetyMonitoringEntityList && fireSafetyMonitoringEntityList.size() > 0 ){
                for( FireSafetyMonitoringEntity fireSafetyMonitoringEntity : fireSafetyMonitoringEntityList ){
                    FireSafetyMonitoringBean fireSafetyMonitoringBean =  HandleDispatchTransformUtil.transform( fireSafetyMonitoringEntity , organizationNameMap ) ;
                    res.add( fireSafetyMonitoringBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentParticipant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentParticipant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }

    }




    /**
     * {@inheritDoc}
     *
     * @see  #findLastFireSafetyMonitoring( String , List )
     */
    @Transactional(  readOnly =  true )
    @Override
    public   Map<String,FireSafetyMonitoringBean>   findLastFireSafetyMonitoring(String incidentId , List<String> personIds ){
        if (  Strings.isBlank( incidentId )  ||  personIds == null ||  personIds.size() < 1     ) {
            logService.infoLog(logger, "service", "findLastFireSafetyMonitoring", "incidentId or personIds is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findLastFireSafetyMonitoring", "service is started...");
            Long logStart = System.currentTimeMillis();

            Map<String,FireSafetyMonitoringBean>   res = new HashMap<>();
            //获得机构名称map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;

            logService.infoLog(logger, "repository", "findLastFireSafetyMonitoring( incidentId , personIds )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<FireSafetyMonitoringEntity> fireSafetyMonitoringEntityList  =   fireSafetyMonitoringRepository.findLastFireSafetyMonitoring( incidentId  , personIds ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findLastFireSafetyMonitoring( incidentId  , personIds )", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != fireSafetyMonitoringEntityList && fireSafetyMonitoringEntityList.size() > 0 ){
                List<String> fireSafetyPersonIds = new ArrayList<>() ;
                for( FireSafetyMonitoringEntity fireSafetyMonitoringEntity : fireSafetyMonitoringEntityList ){
                    FireSafetyMonitoringBean fireSafetyMonitoringBean =  HandleDispatchTransformUtil.transform( fireSafetyMonitoringEntity , organizationNameMap ) ;
                    String  personId = fireSafetyMonitoringBean.getPersonId() ;
                    if( !fireSafetyPersonIds.contains( personId ) ){
                        fireSafetyPersonIds.add(personId ) ;
                        res.put( fireSafetyMonitoringBean.getPersonId() , fireSafetyMonitoringBean  ) ;
                    }
                }
                fireSafetyMonitoringEntityList.clear();
                fireSafetyMonitoringEntityList=null;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findLastFireSafetyMonitoring", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findLastFireSafetyMonitoring", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }

    }
    
    /**
     * {@inheritDoc}
     *
     * @see #findEnterFireSafetyByVehicle( String , String  )
     */
    @Transactional(  readOnly =  true )
    @Override
    public  List<FireSafetyMonitoringBean> findEnterFireSafetyByVehicle( String vehicleId ,String incidentId ){
        if (  Strings.isBlank( vehicleId ) && Strings.isBlank( incidentId ) ) {
            logService.infoLog(logger, "service", "findEnterFireSafetyByVehicle", "vehicleId is null or incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEnterFireSafetyByVehicle", "service is started...");
            System.out.println("findEnterFireSafetyByVehicle=======service is started...");
            Long start = System.currentTimeMillis();
            List<FireSafetyMonitoringBean> res = new ArrayList<>();
            System.out.println("fireSafetyMonitoringRepository===="+fireSafetyMonitoringRepository);
            List<FireSafetyMonitoringEntity> fireSafetyMonitoringEntityList = fireSafetyMonitoringRepository.findEnterFireSafetyByVehicle( vehicleId,incidentId ) ;
            System.out.println("fireSafetyMonitoringEntityList=====size====="+fireSafetyMonitoringEntityList.size());
            //获得机构名称map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            if( null != fireSafetyMonitoringEntityList && fireSafetyMonitoringEntityList.size() > 0 ){
                for( FireSafetyMonitoringEntity fireSafetyMonitoringEntity : fireSafetyMonitoringEntityList ){
                    FireSafetyMonitoringBean fireSafetyMonitoringBean =  HandleDispatchTransformUtil.transform( fireSafetyMonitoringEntity , organizationNameMap ) ;
                    res.add( fireSafetyMonitoringBean ) ;
                }
            }
            
            Long end = System.currentTimeMillis();
            System.out.println("end - start====="+(end - start));
            logService.infoLog(logger, "repository", "findEnterFireSafetyByVehicle( vehicleId )", String.format("repository is finished,execute time is :%sms", end - start));
            
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEnterFireSafetyByVehicle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }


}
