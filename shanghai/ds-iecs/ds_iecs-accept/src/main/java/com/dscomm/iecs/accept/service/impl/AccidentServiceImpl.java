package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.AccidentEntity;
import com.dscomm.iecs.accept.dal.po.FightSituationEntity;
import com.dscomm.iecs.accept.dal.repository.AccidentRepository;
import com.dscomm.iecs.accept.dal.repository.FightSituationRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.AccidentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.AccidentBean;
import com.dscomm.iecs.accept.service.AccidentService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_TB;
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

import java.net.URLDecoder;
import java.util.*;

/**
 * 描述：现场信息 服务类实现
 */
@Component("accidentServiceImpl")
public class AccidentServiceImpl implements AccidentService {
    private static final Logger logger = LoggerFactory.getLogger(AccidentServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private AccidentRepository accidentRepository ;
    private IncidentService incidentService;
    private SystemConfigurationService systemConfigurationService;
    private FightSituationRepository fightSituationRepository;
    private OrganizationService organizationService;
    private NotifyActionService notifyActionService ;
    private DictionaryService dictionaryService ;
    private ServletService servletService ;
    private UserService userService ;

    private PushDataService  pushDataService  ;

    private Boolean whetherFillRecheck ;

    private List<String> dics;
    /**
     * 默认的构造函数
     */
    @Autowired
    public AccidentServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env ,
                               AccidentRepository accidentRepository,IncidentService incidentService, SystemConfigurationService systemConfigurationService,
                               FightSituationRepository fightSituationRepository , OrganizationService organizationService , NotifyActionService  notifyActionService ,
                               DictionaryService dictionaryService , ServletService servletService  , UserService userService ,
                               PushDataService  pushDataService

    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.accidentRepository = accidentRepository ;
        this.incidentService = incidentService;
        this.systemConfigurationService = systemConfigurationService;
        this.fightSituationRepository = fightSituationRepository;
        this.organizationService = organizationService;
        this.notifyActionService = notifyActionService;
        this.dictionaryService = dictionaryService ;
        this.servletService = servletService ;
        this.userService = userService ;
        this.pushDataService = pushDataService ;

        whetherFillRecheck = Boolean.parseBoolean(env.getProperty("whetherFillRecheck"));

        dics = new ArrayList<>(Arrays.asList("DWXZ", "YWQK", "JZJG", "AJDJ"  ));
    }

    /**
     * {@inheritDoc}
     *
     * @see AccidentService#saveAccident(AccidentSaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public AccidentBean saveAccident(AccidentSaveInputInfo queryBean){
        if ( null == queryBean  || Strings.isBlank( queryBean.getIncidentId() ) ) {
            logService.infoLog(logger, "service", "saveAccident", "AccidentSaveInputInfo or incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveAccident", "service is started...");
            Long logStart = System.currentTimeMillis();

            AccidentBean  res = null ;

            decodeAccidentSaveInputInfo(queryBean);//URLDecoder 转码

            //保存参数转换
            AccidentEntity accidentEntity = HandleDispatchTransformUtil.transform( queryBean  ) ;
            accidentEntity.setAccidentTime( servletService.getSystemTime() );
            UserInfo userInfo = userService.getUserInfo()  ;
            if( userInfo != null  ){
                accidentEntity.setSeatNumber( userInfo.getAgentNum() );
                accidentEntity.setPersonId( userInfo.getAccount() );
                accidentEntity.setPersonName( userInfo.getPersonName() );
                accidentEntity.setPersonNumber( userInfo.getAccount() );

            }
            //保存事故情况
            logService.infoLog(logger, "repository", "save(dbAccidentEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save( accidentEntity ) ;
            accidentEntity.setIdCode( accidentEntity.getId() );
            accessor.save( accidentEntity ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbAccidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            //确保作战情况 和 事故情况 id相同
            queryBean.setId(accidentEntity.getId());
            FightSituationEntity fightSituationEntity = transform(queryBean);

            //保存作战状况
            logService.infoLog(logger, "repository", "save(dbFightSituationEntity)", "repository is started...");
            Long fightSituationStart = System.currentTimeMillis();

            accessor.save( fightSituationEntity ) ;

            Long fightSituationEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbFightSituationEntity)", String.format("repository is finished,execute time is :%sms", fightSituationEnd - fightSituationStart));

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            res = HandleDispatchTransformUtil.transform( accidentEntity , fightSituationEntity , dicsMap ) ;

            //获取系统配置，判断是否回写结案反馈信息到警情
            SystemConfigurationBean whetherWriteBackConfig = systemConfigurationService.getSystemConfigByConfigType("whetherWriteBackAccidentToIncident");
            if (whetherWriteBackConfig != null) {
                String configValue = whetherWriteBackConfig.getConfigValue();
                if ( "true".equals(configValue)) {
                    incidentService.writeBackAccidentToIncident(res);
                }
            }

            //判断警情状态是否有 填报 复审状态
            //如果有填报复审状态  中队填报完成结案反馈 将案件状态变更为 填报状态
            if( whetherFillRecheck ) {
                incidentService.updateIncidentStatus( queryBean.getIncidentId()  , INCIDENT_STATUS_TB.getCode() , null ) ;
            }

            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>() ;
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( queryBean.getIncidentId() );
            orgSet.addAll( orgIds ) ;
            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_ACCIDENT.getCode(), res, orgSet);



            //其他
            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushIncidentAccident( res ,otherParams  ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveAccident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveAccident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_ACCIDENT_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see AccidentService#findAccident( String )
     */
    @Transactional(  readOnly =  true )
    @Override
    public AccidentBean findAccident(String incidentId) {
        if (  Strings.isBlank( incidentId ) ) {
            logService.infoLog(logger, "service", "findAccident", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAccident", "service is started...");
            Long logStart = System.currentTimeMillis();

            AccidentBean  res = null ;

            logService.infoLog(logger, "repository", "findAccidentByIncidentId( incidentId )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<AccidentEntity> accidentEntityList = accidentRepository.findAccidentByIncidentId( incidentId ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAccidentByIncidentId( incidentId )", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != accidentEntityList && accidentEntityList.size() > 0 ){
                AccidentEntity accidentEntity = accidentEntityList.get(0) ;

                //查询作战情况
                logService.infoLog(logger, "repository", "findFightSituationByIncidentId( incidentId )", "repository is started...");
                Long fightSituationStart = System.currentTimeMillis();

                List<FightSituationEntity> fightSituationEntityList = fightSituationRepository.findFightSituationByIncidentId(incidentId);

                Long fightSituationEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findFightSituationByIncidentId( incidentId )", String.format("repository is finished,execute time is :%sms", fightSituationEnd - fightSituationStart));

                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

                FightSituationEntity fightSituationEntity = null ;
                if(fightSituationEntityList != null && fightSituationEntityList.size() > 0 ){
                    fightSituationEntity = fightSituationEntityList.get(0);
                }
                res =  HandleDispatchTransformUtil.transform( accidentEntity , fightSituationEntity , dicsMap ) ;
                if (fightSituationEntityList!=null){
                    fightSituationEntityList.clear();
                    fightSituationEntityList=null;
                }
                accidentEntityList.clear();
                accidentEntityList=null;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAccident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAccident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_ACCIDENT_FAIL);
        }

    }

    /**
     *作战情况表 转换
     *
     * @param source 保存参数INPUT
     * @return 作战情况表PO
     */
    public static FightSituationEntity transform(AccidentSaveInputInfo source  ) {
        if (null != source) {
            FightSituationEntity target = new FightSituationEntity ();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setCombatSituation(source.getCombatSituation());
            target.setLocaleCommander(source.getLocaleCommander());
            target.setLocaleDeputyCommander(source.getLocaleDeputyCommander());
            target.setDispatcher(source.getDispatcher());
            target.setLocaleCorrespondent(source.getLocaleCorrespondent());
            target.setDetachmentCorrespondent(source.getDetachmentCorrespondent());
            target.setSquadronCommander(source.getSquadronCommander());
            target.setSquadronCorrespondent(source.getSquadronCorrespondent());
            target.setVideographer(source.getVideographer());

            target.setDispatchTrainNum(source.getDispatchTrainNum());
            target.setDispatchVehicleNum(source.getDispatchVehicleNum());
            target.setDispatchPersonNum(source.getDispatchPersonNum());
            target.setDispatchWaterGunNum( source.getDispatchWaterGunNum() );

            target.setCombatProcessDesc(source.getCombatProcessDesc());
            target.setRemarks( null );
            return target;
        }
        return null;
    }


    /**
     * 结案反馈输入信息解码
     * */
    private void decodeAccidentSaveInputInfo(AccidentSaveInputInfo source){
        try {
            if (source != null){
                if (!StringUtils.isBlank(source.getActualCrimeAddress())){
                    source.setActualCrimeAddress(URLDecoder.decode(source.getActualCrimeAddress(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getAccidentUnitName())){
                    source.setAccidentUnitName(URLDecoder.decode(source.getAccidentUnitName(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getBurningFloor())){
                    source.setBurningFloor(URLDecoder.decode(source.getBurningFloor(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getAccidentReason())){
                    source.setAccidentReason(URLDecoder.decode(source.getAccidentReason(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getSpreadSituation())){
                    source.setSpreadSituation(URLDecoder.decode(source.getSpreadSituation(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getBurningMaterial())){
                    source.setBurningMaterial(URLDecoder.decode(source.getBurningMaterial(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getWaterSituation())){
                    source.setWaterSituation(URLDecoder.decode(source.getWaterSituation(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getPeripherySituation())){
                    source.setPeripherySituation(URLDecoder.decode(source.getPeripherySituation(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getLeakageMaterial())){
                    source.setLeakageMaterial(URLDecoder.decode(source.getLeakageMaterial(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getRescueMeasures())){
                    source.setRescueMeasures(URLDecoder.decode(source.getRescueMeasures(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getCombatSituation())){
                    source.setCombatSituation(URLDecoder.decode(source.getCombatSituation(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getRemarks())){
                    source.setRemarks(URLDecoder.decode(source.getRemarks(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getResponsiblePersonName())){
                    source.setResponsiblePersonName(URLDecoder.decode(source.getResponsiblePersonName(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getResponsiblePersonCareer())){
                    source.setResponsiblePersonCareer(URLDecoder.decode(source.getResponsiblePersonCareer(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getResponsiblePersonNativePlace())){
                    source.setResponsiblePersonNativePlace(URLDecoder.decode(source.getResponsiblePersonNativePlace(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getLocaleCommander())){
                    source.setLocaleCommander(URLDecoder.decode(source.getLocaleCommander(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getLocaleDeputyCommander())){
                    source.setLocaleDeputyCommander(URLDecoder.decode(source.getLocaleDeputyCommander(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getDispatcher())){
                    source.setDispatcher(URLDecoder.decode(source.getDispatcher(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getLocaleCorrespondent())){
                    source.setLocaleCorrespondent(URLDecoder.decode(source.getLocaleCorrespondent(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getDetachmentCorrespondent())){
                    source.setDetachmentCorrespondent(URLDecoder.decode(source.getDetachmentCorrespondent(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getSquadronCommander())){
                    source.setSquadronCommander(URLDecoder.decode(source.getSquadronCommander(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getSquadronCorrespondent())){
                    source.setSquadronCorrespondent(URLDecoder.decode(source.getSquadronCorrespondent(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getVideographer())){
                    source.setVideographer(URLDecoder.decode(source.getVideographer(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getCombatProcessDesc())){
                    source.setCombatProcessDesc(URLDecoder.decode(source.getCombatProcessDesc(),"UTF-8"));
                }
            }
        }catch (Exception e){
            throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
        }

    }

}
