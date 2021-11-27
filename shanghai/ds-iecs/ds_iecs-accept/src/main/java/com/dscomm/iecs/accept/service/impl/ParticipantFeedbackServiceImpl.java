package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.ParticipantFeedbackEntity;
import com.dscomm.iecs.accept.dal.repository.ParticipanFeedbackRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.ParticipantBackInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.ParticipantSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.FireSafetyMonitoringBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationVehicleBean;
import com.dscomm.iecs.accept.graphql.typebean.ParticipantFeedbackBean;
import com.dscomm.iecs.accept.service.FireSafetyMonitoringService;
import com.dscomm.iecs.accept.service.HandleService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.ParticipantFeedbackService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.websocket.WebsocketBaseBean;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.VehiclePersonsBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehiclePersonService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.basedata.service.enums.ParticipantPersonStateEnum;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
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
 * 描述：参战人员反馈 服务类实现
 */
@Component("participantFeedbackServiceImpl")
public class ParticipantFeedbackServiceImpl implements ParticipantFeedbackService {
    private static final Logger logger = LoggerFactory.getLogger(ParticipantFeedbackServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private OrganizationService organizationService ;
    private ParticipanFeedbackRepository participanFeedbackRepository ;
    private NotifyActionService notifyActionService ;
    private IncidentService incidentService ;
    private DictionaryService dictionaryService ;
    private FireSafetyMonitoringService fireSafetyMonitoringService ;
    private HandleService handleService ;
    private VehicleService vehicleService;
    private VehiclePersonService vehiclePersonService ;
    private ServletService servletService;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public ParticipantFeedbackServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                                          OrganizationService organizationService, ParticipanFeedbackRepository participanFeedbackRepository,
                                          NotifyActionService notifyActionService, IncidentService incidentService, DictionaryService dictionaryService,
                                          FireSafetyMonitoringService fireSafetyMonitoringService, HandleService handleService,
                                          VehicleService vehicleService, VehiclePersonService vehiclePersonService,
                                          ServletService servletService) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.organizationService = organizationService ;
        this.participanFeedbackRepository = participanFeedbackRepository ;
        this.notifyActionService = notifyActionService ;
        this.incidentService = incidentService ;
        this.dictionaryService = dictionaryService ;
        this.fireSafetyMonitoringService = fireSafetyMonitoringService ;
        this.vehicleService = vehicleService;
        this.handleService = handleService;
        this.vehiclePersonService = vehiclePersonService ;
        this.servletService = servletService;

        dics = new ArrayList<>(   Arrays.asList("WLRYZW"   )  );

    }



    /**
     * {@inheritDoc}
     *
     * @see #findVehiclePersons(   String  , List )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  List<VehiclePersonsBean> findVehiclePersons( String incidentId , List<String>  vehicleIds   ) {
        if (vehicleIds == null ||  vehicleIds.size() < 1 ) {
            logService.infoLog(logger, "service", "findVehiclePersons", "vehicleIds   is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehiclePersons", "service is started...");
            Long logStart = System.currentTimeMillis();


            //获得 车辆的值班信息
            List<VehiclePersonsBean>  res  = vehiclePersonService.findVehiclePersonsSplit( vehicleIds   ) ;

            //获得 车辆已登记人员信息
            List<ParticipantFeedbackBean>  participantFeedbackBeanList =  findIncidentParticipant(incidentId ,  vehicleIds   ) ;


            if( res != null && res.size() > 0  ){
                Map<String , String >  vehicleParticipantMap = new HashMap<>() ;
                for( ParticipantFeedbackBean  participantFeedbackBean : participantFeedbackBeanList  ){
                    if( participantFeedbackBean != null && participantFeedbackBean.getWhetherFeedback() == EnableEnum.ENABLE_FALSE.getCode() ){
                        vehicleParticipantMap.put(
                                participantFeedbackBean.getVehicleId() + participantFeedbackBean.getPersonId() , participantFeedbackBean.getId() ) ;
                    }
                }
                for( VehiclePersonsBean vehiclePersonsBean :  res ){
                    String  vehicleParticipantId = vehicleParticipantMap.get(vehiclePersonsBean.getVehicleId() + vehiclePersonsBean.getPersonId()  ) ;
                    if( Strings.isBlank( vehicleParticipantId )){
                        vehiclePersonsBean.setWhetherParticipant( EnableEnum.ENABLE_FALSE.getCode()  );
                    }else{
                        vehiclePersonsBean.setParticipantId(vehicleParticipantId);
                        vehiclePersonsBean.setWhetherParticipant( EnableEnum.ENABLE_TRUE.getCode()  );
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehiclePersons", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehiclePersons", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see ParticipantFeedbackService#saveParticipant(ParticipantSaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public   List<ParticipantFeedbackBean> saveParticipant(ParticipantSaveInputInfo queryBean ){
        if ( null == queryBean  || Strings.isBlank( queryBean.getIncidentId() )  || Strings.isBlank( queryBean.getVehicleId() )  ) {
            logService.infoLog(logger, "service", "saveParticipant", "ParticipantSaveInputInfo or incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveParticipant", "service is started...");
            Long logStart = System.currentTimeMillis();


            List<ParticipantFeedbackBean> res = new ArrayList<>();
            Map<String,VehiclePersonsBean> peopleMap = new HashMap<>();
            //获得机构名称map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;

            //如果没有处警信息，默认为最新的处警信息
            if (   StringUtils.isBlank(queryBean.getHandleId())  ){
                HandleOrganizationVehicleBean handleOrganizationVehicleBean = handleService.findHandleOrganizationVehicle( queryBean.getIncidentId()  , queryBean.getVehicleId() ) ;
                if ( handleOrganizationVehicleBean != null ){
                    String  handleId = handleOrganizationVehicleBean.getHandleId() ;
                    queryBean.setHandleId(handleId);
                }
            }


            //保存参数转换
            List<ParticipantFeedbackEntity> participantFeedbackEntityList = HandleDispatchTransformUtil.transform( queryBean ) ;

            List<String> vehicleIds = new ArrayList<>();
            vehicleIds.add( queryBean.getVehicleId() ) ;

            List<VehiclePersonsBean> vehiclePersonsBeans = vehiclePersonService.findVehiclePersonsSplit(vehicleIds   );


            if (vehiclePersonsBeans != null && vehiclePersonsBeans.size()>0){
                for (VehiclePersonsBean bean:vehiclePersonsBeans){
                    peopleMap.put(bean.getPersonId(),bean);
                }
            }

            //将人员信息写入entity
            if (participantFeedbackEntityList != null && participantFeedbackEntityList.size()>0){
                for (ParticipantFeedbackEntity entity :  participantFeedbackEntityList){
                    VehiclePersonsBean vehiclePersonsBean = peopleMap.get(entity.getPersonId()) ;
                    if ( vehiclePersonsBean != null ){
                        entity.setOrganizationId( vehiclePersonsBean.getOrganizationId() );
                        entity.setPersonName( vehiclePersonsBean.getPersonName()  );
                        entity.setPersonPhone( vehiclePersonsBean.getPersonPhone()  );
                        entity.setPersonRole( vehiclePersonsBean.getPersonType()  );
                    }
                }
            }

            //获得 案件 车辆的历史参战人员信息
            List<ParticipantFeedbackEntity>  hisEntityList = participanFeedbackRepository.findParticipantFeedbackByIncidentId( queryBean.getIncidentId()  , queryBean.getHandleId() ,  vehicleIds ) ;

            Map<String , ParticipantFeedbackEntity> hisMap = new HashMap<>() ;
            //默认历史设置为false
            if( hisEntityList != null && hisEntityList.size() > 0 ){
                for(ParticipantFeedbackEntity participantFeedbackEntity :  hisEntityList ) {
                    participantFeedbackEntity.setValid( false );
                    hisMap.put( participantFeedbackEntity.getIncidentId() + participantFeedbackEntity.getHandleId() +
                            participantFeedbackEntity.getVehicleId()  + participantFeedbackEntity.getPersonId() , participantFeedbackEntity ) ;

                }
            }

            for(ParticipantFeedbackEntity participantFeedbackEntity  :  participantFeedbackEntityList  ){
                String key =  participantFeedbackEntity.getIncidentId() + participantFeedbackEntity.getHandleId() +
                        participantFeedbackEntity.getVehicleId()  + participantFeedbackEntity.getPersonId() ;
                ParticipantFeedbackEntity hisParticipantFeedback = hisMap.get( key ) ;
                if( hisParticipantFeedback == null ){
                    hisMap.put( key , participantFeedbackEntity );
                }else{
                    hisParticipantFeedback.setValid( true );
                    hisMap.put( key , hisParticipantFeedback );
                }
            }

            List<ParticipantFeedbackEntity> saveEntity = new ArrayList<>( hisMap.values() ) ;

            logService.infoLog(logger, "repository", "save(dbParticipantFeedbackEntityList)", "repository is started...");
            Long start2 = System.currentTimeMillis();

            accessor.save( saveEntity ) ;

            Long end2 = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbParticipantFeedbackEntityList)", String.format("repository is finished,execute time is :%sms", end2 - start2));

            if( null != saveEntity && saveEntity.size() > 0 ){
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap   = dictionaryService.findDictionaryMap(dics) ;
                for( ParticipantFeedbackEntity participantFeedbackEntity : saveEntity ){
                    if( participantFeedbackEntity.isValid() ){
                        ParticipantFeedbackBean participantFeedbackBean =  HandleDispatchTransformUtil.transform( participantFeedbackEntity , dicsMap , organizationNameMap ) ;
                        res.add( participantFeedbackBean ) ;
                    }

                }
            }

            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>() ;
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( queryBean.getIncidentId() );
            orgSet.addAll( orgIds ) ;
            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);

            //构建推送消息
            WebsocketBaseBean websocket = new WebsocketBaseBean() ;
            websocket.setWebsocketCode( WebsocketCodeEnum.SAVE_PARTICIPANT.getCode() );
            websocket.setIncidentId( queryBean.getIncidentId() );
            websocket.setWebsocketBody( res );

            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_PARTICIPANT.getCode(), websocket, orgSet);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveParticipant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveParticipant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }
    /**
     * {@inheritDoc}
     *
     * @see ParticipantFeedbackService#saveParticipant(ParticipantSaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public  List<ParticipantFeedbackBean> saveParticipant(List<ParticipantSaveInputInfo> queryBean ){

        try {
            logService.infoLog(logger, "service", "saveParticipant", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<ParticipantFeedbackBean> res = new ArrayList<>();

            for(ParticipantSaveInputInfo participantSaveInputInfo : queryBean ){
                List<ParticipantFeedbackBean> participantFeedbackBean = saveParticipant( participantSaveInputInfo ) ;
                if( participantFeedbackBean != null && participantFeedbackBean.size() > 0 ){
                    res.addAll( participantFeedbackBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveParticipant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveParticipant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see  #findIncidentParticipant( String  , List  )
     */
    @Transactional(  readOnly =  true )
    @Override
    public  List<ParticipantFeedbackBean> findIncidentParticipant( String  incidentId ,  List<String>  vehicleIds  ){
        if (  Strings.isBlank( incidentId ) ) {
            logService.infoLog(logger, "service", "findIncidentParticipant", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentParticipant", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<ParticipantFeedbackBean> res = new ArrayList<>();
            //获得机构名称map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;

            logService.infoLog(logger, "repository", "findParticipantFeedbackByIncidentId( incidentId )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<ParticipantFeedbackEntity> participantFeedbackEntityList = null ;
            if( vehicleIds == null || vehicleIds.size() < 1 ){
                participantFeedbackEntityList = participanFeedbackRepository.findParticipantFeedbackByIncidentId( incidentId ) ;
            }else{
                participantFeedbackEntityList = participanFeedbackRepository.findParticipantFeedbackByIncidentId( incidentId , vehicleIds ) ;
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findParticipantFeedbackByIncidentId( incidentId )", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != participantFeedbackEntityList && participantFeedbackEntityList.size() > 0 ){
                List<String> personIds = new ArrayList<>();
                for( ParticipantFeedbackEntity participantFeedbackEntity : participantFeedbackEntityList ){
                    personIds.add( participantFeedbackEntity.getPersonId() ) ;
                }
                //获得 案件人员进入火场信息
                Map<String , FireSafetyMonitoringBean>  fireSafetyEnterMap   = fireSafetyMonitoringService.findLastFireSafetyMonitoring( incidentId , personIds ) ;

                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                for( ParticipantFeedbackEntity participantFeedbackEntity : participantFeedbackEntityList ){
                    ParticipantFeedbackBean participantFeedbackBean =  HandleDispatchTransformUtil.transform( participantFeedbackEntity ,dicsMap , organizationNameMap ) ;
                    FireSafetyMonitoringBean fireSafetyMonitoringBean = fireSafetyEnterMap.get(  participantFeedbackBean.getPersonId() ) ;
                    if( fireSafetyMonitoringBean == null ){
                        participantFeedbackBean.setWhetherEnterFireSafety(0);
                    }else if( fireSafetyMonitoringBean != null
                            && fireSafetyMonitoringBean.getEnterFireTime() != null
                            && fireSafetyMonitoringBean.getWithdrawFireTime() == null  ) {
                        participantFeedbackBean.setWhetherEnterFireSafety(1);
                        participantFeedbackBean.setEnterFireTime( fireSafetyMonitoringBean.getEnterFireTime() );
                    }else if( fireSafetyMonitoringBean != null
                            && fireSafetyMonitoringBean.getEnterFireTime() != null
                            && fireSafetyMonitoringBean.getWithdrawFireTime() != null ){
                        participantFeedbackBean.setWhetherEnterFireSafety(0);
                        participantFeedbackBean.setEnterFireTime( fireSafetyMonitoringBean.getEnterFireTime() );
                        participantFeedbackBean.setWithdrawFireTime( fireSafetyMonitoringBean.getWithdrawFireTime() );
                    }
                    res.add( participantFeedbackBean ) ;
                }
                participantFeedbackEntityList.clear();
                participantFeedbackEntityList=null;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentParticipant", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            res.sort(Comparator.comparing(ParticipantFeedbackBean::getId));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentParticipant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }





    /**
     * {@inheritDoc}
     *
     * @see #saveIncidentParticipantMerge(String, String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  void  saveIncidentParticipantMerge ( String sourceIncidentId, String targetIncidentId  ) {
        if (Strings.isBlank(sourceIncidentId) || Strings.isBlank(targetIncidentId)) {
            logService.infoLog(logger, "service", "saveIncidentParticipantMerge", "source or target IncidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncidentParticipantMerge", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据原案件id查找受理单
            logService.infoLog(logger, "repository", "findParticipantFeedbackByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<ParticipantFeedbackEntity> participantFeedbackEntityList = participanFeedbackRepository.findParticipantFeedbackByIncidentId(sourceIncidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findParticipantFeedbackByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //将受理单关联的案件id置为目标案件id
            if (participantFeedbackEntityList != null && participantFeedbackEntityList.size() > 0) {
                for (ParticipantFeedbackEntity entity : participantFeedbackEntityList ) {
                    entity.setIncidentId(targetIncidentId);
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbParticipantFeedbackEntitList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(participantFeedbackEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbParticipantFeedbackEntitList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentParticipantMerge", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncidentParticipantMerge", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.CHANGE_ACCEPTANCE_INCIDENT_ID_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see #saveIncidentParticipantSplit(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveIncidentParticipantSplit(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "recoverAcceptanceIncidentId", "incidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "recoverAcceptanceIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据原案件id查找受理单
            logService.infoLog(logger, "repository", "findParticipantFeedbackByOriginalIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<ParticipantFeedbackEntity> participantFeedbackEntityList =  participanFeedbackRepository.findParticipantFeedbackByOriginalIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findParticipantFeedbackByOriginalIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //将受理单关联的案件id置为原始案件id
            if (participantFeedbackEntityList != null && participantFeedbackEntityList.size() > 0) {
                for (ParticipantFeedbackEntity entity : participantFeedbackEntityList) {
                    entity.setIncidentId(entity.getOriginalIncidentNumber());
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbParticipantFeedbackEntitList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(participantFeedbackEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbParticipantFeedbackEntitList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "recoverAcceptanceIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "recoverAcceptanceIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.RECOVER_ACCEPTANCE_INCIDENT_ID_FAIL);
        }

    }

    /**
     * 车辆实际返回人员保存
     * @param  inputInfo
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean  saveParticipantBack(ParticipantBackInputInfo inputInfo) {
        if ( inputInfo == null || inputInfo.getIds() == null || inputInfo.getIds().size()<1 || inputInfo.getFeedbackCheckTime() == null ){
            logService.infoLog(logger, "service", "saveParticipantBack", "ids or feedbackCheckTime is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveParticipantBack", "service is started...");
            Long logStart = System.currentTimeMillis();

            String incidentId = null;
            String handleId = null;
            String vehicleId = null;
            List<String> notBackList = new ArrayList<>();//未返回人员
            List<ParticipantFeedbackEntity> entitiesBack = new ArrayList<>();

            List<ParticipantFeedbackEntity> entities = participanFeedbackRepository.findByValidAndIdIn(1,inputInfo.getIds());
            if (entities != null && entities.size()>0){
                incidentId = entities.get(0).getIncidentId();
                handleId = entities.get(0).getHandleId();
                vehicleId = entities.get(0).getVehicleId();
                for (ParticipantFeedbackEntity entity:entities){
                    entity.setWhetherFeedback( EnableEnum.ENABLE_TRUE.getCode() );
                    entity.setFeedbackCheckTime(inputInfo.getFeedbackCheckTime());
                    entitiesBack.add(entity);
                }

                accessor.save( entitiesBack ) ;


            }else {
                return false;
            }



            /**获取未返回的人员并推送*/
            List<ParticipantFeedbackEntity> notBackparticipantFeedbackEntities =
            participanFeedbackRepository.findByValidAndIncidentIdAndHandleIdAndVehicleIdAndWhetherFeedback(1,incidentId,handleId,vehicleId,0);
            if (notBackparticipantFeedbackEntities != null && notBackparticipantFeedbackEntities.size()>0){
                for (ParticipantFeedbackEntity entity:notBackparticipantFeedbackEntities){
                    notBackList.add(entity.getPersonName());
                }

                StringBuffer sbf = new StringBuffer();
                for (String name:notBackList){
                    sbf.append(name);
                    sbf.append(",");
                }
                sbf.delete(sbf.length()-1,sbf.length());
                sbf.append("未随车归队！");

                //消息通知案件参与单位
                Set<String> orgSet = new HashSet<>() ;
                List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( incidentId );
                orgSet.addAll( orgIds ) ;
                List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
                orgSet.addAll(orgCodes);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_PARTICIPANT_BACK.getCode(), sbf.toString(), orgSet);

            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveParticipantBack", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        }catch (Exception e){
            logService.erorLog(logger, "service", "saveParticipantBack", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     * @param inputInfo 参战人员信息
     * @return 参展人员信息
     */
    @Transactional
    @Override
    public List<ParticipantFeedbackBean> changeParticipantPersonState(ParticipantBackInputInfo inputInfo) {
        try {
            List<ParticipantFeedbackBean> list=new ArrayList<>();
            if (inputInfo!=null&&inputInfo.getIds()!=null&&!inputInfo.getIds().isEmpty()&&inputInfo.getState()!=null){
                List<ParticipantFeedbackEntity> entities = participanFeedbackRepository.findByValidAndIdIn(1,inputInfo.getIds());
                if (entities!=null&&!entities.isEmpty()){
                    List<ParticipantFeedbackEntity> temp=new ArrayList<>();
                    Long systemTime = servletService.getSystemTime();
                    if (inputInfo.getChangeTime()==null){
                        inputInfo.setChangeTime(systemTime);
                    }
                    for (ParticipantFeedbackEntity entity : entities) {
                        if (entity.getState()==null||inputInfo.getState()>entity.getState()){
                            //20进场
                            if (inputInfo.getState()==ParticipantPersonStateEnum.PERSON_APPROACH.getCode()){
                                entity.setEntryTime(inputInfo.getChangeTime());
                                entity.setWhetherFeedback(0);
                                entity.setState(inputInfo.getState());
                                entity.setCheckTime(systemTime);
                            }else if (inputInfo.getState()==ParticipantPersonStateEnum.PERSON_EXIT.getCode()){
                                entity.setState(inputInfo.getState());
                                entity.setExitTime(inputInfo.getChangeTime());
                                entity.setWhetherFeedback(1);
                                entity.setFeedbackCheckTime(systemTime);
                            }
                            temp.add(entity);
                        }
                    }
                    if (temp!=null&&!temp.isEmpty()){
                        accessor.save(temp);
                        //获取名称-代码对应字典
                        Map<String, Map<String, String>> dicsMap   = dictionaryService.findDictionaryMap(dics) ;
                        //获得机构名称map
                        Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
                        temp.forEach(entity->{
                            ParticipantFeedbackBean bean = HandleDispatchTransformUtil.transform(entity,dicsMap,organizationNameMap);
                            list.add(bean);
                        });
                        //发送消息给前端 一次最多发送10个
                        List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( list.get(0).getIncidentId() );
                        Set<String> orgSet = new HashSet<>() ;
                        if (orgIds!=null&&!orgIds.isEmpty()){
                            orgSet.addAll(orgIds);
                        }
                        //构建推送消息
                        WebsocketBaseBean websocket = new WebsocketBaseBean() ;
                        websocket.setWebsocketCode( WebsocketCodeEnum.SAVE_PARTICIPANT.getCode() );
                        websocket.setIncidentId(  list.get(0).getIncidentId() );

                        if (list.size()>10){
                            websocket.setWebsocketBody( list );
                            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_PARTICIPANT.getCode(), websocket, orgSet);
                        }else {
                            int i=0;
                            while (i<list.size()){
                                int j=i+10;
                                if (j>list.size()){
                                    j=list.size();
                                }
                                websocket.setWebsocketBody( list.subList(i,j));
                                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_PARTICIPANT.getCode(), websocket, orgSet);
                                i=j;
                            }
                        }

                    }
                }
            }
            return list;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "changeParticipantPersonState", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }


}
