package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.UnTrafficAlarmEntity;
import com.dscomm.iecs.accept.dal.repository.UnTrafficAlarmRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.UnTrafficAlarmBean;
import com.dscomm.iecs.accept.restful.vo.ReceiveUnTrafficAlarmVO;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.OrgRelationshipService;
import com.dscomm.iecs.accept.service.UnTrafficAlarmService;
import com.dscomm.iecs.accept.service.bean.ReceiveMessageBean;
import com.dscomm.iecs.accept.service.bean.TransferAssociateBean;
import com.dscomm.iecs.accept.service.pushData.PushPoliceService;
import com.dscomm.iecs.accept.utils.transform.UntrafficAlarmTransformUtil;
import com.dscomm.iecs.agent.enums.AgentStateEnum;
import com.dscomm.iecs.agent.enums.RoleEnum;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.graphql.typebean.OrgRelationshipBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.ext.TrafficTypeBakEnum;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DigestUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.client.rest.RestInvokeException;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ???????????????????????? ???????????????
 */
@Component("unTrafficAlarmServiceImpl")
public class UnTrafficAlarmServiceImpl implements UnTrafficAlarmService {
    private static final Logger logger = LoggerFactory.getLogger(UnTrafficAlarmServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private UnTrafficAlarmRepository unTrafficAlarmRepository ;
    private NotifyActionService notifyActionService ;
    private DictionaryService dictionaryService ;
    private RestClientInvoke restClientInvoke = null ;
    private Environment env;
    private AgentService agentService ;
    private UserService userService ;
    private ServletService servletService ;
    private SystemConfigurationService systemConfigurationService ;
    private IncidentService incidentService;
    private OrgRelationshipService orgRelationshipService ;
    private PushPoliceService pushPoliceService ;


    private List<String> dics;

    /**
     * ?????????????????????
     */
    @Autowired
    @Lazy(true)
    public UnTrafficAlarmServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                     OrganizationService organizationService ,
                                     UnTrafficAlarmRepository unTrafficAlarmRepository , NotifyActionService notifyActionService ,
                                     DictionaryService dictionaryService , Environment env , AgentService agentService , UserService userService ,
                                     ServletService servletService , SystemConfigurationService systemConfigurationService, IncidentService incidentService ,
                                     PushPoliceService pushPoliceService , OrgRelationshipService orgRelationshipService
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.organizationService = organizationService;
        this.unTrafficAlarmRepository = unTrafficAlarmRepository ;
        this.notifyActionService = notifyActionService ;
        this.dictionaryService = dictionaryService ;
        this.restClientInvoke = new RestClientInvoke();
        this.env = env ;
        this.agentService = agentService ;
        this.userService = userService ;
        this.servletService = servletService ;
        this.systemConfigurationService = systemConfigurationService ;
        this.incidentService = incidentService;
        this.orgRelationshipService = orgRelationshipService ;
        this.pushPoliceService = pushPoliceService ;


        dics = new ArrayList<>(Arrays.asList("XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX" ,"JZJG" ,"YWQK", "ZHCS" ,"JQDX"  ));

    }


    /**
     * {@inheritDoc}
     *
     * @see #saveUnTrafficAlarmIncident( ReceiveUnTrafficAlarmVO ,Boolean  )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveUnTrafficAlarmIncident( ReceiveUnTrafficAlarmVO inputInfo , Boolean whetherSaveIncident ) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveUnTrafficAlarmIncident", "DocumentSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveUnTrafficAlarmIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            UnTrafficAlarmBean res = null ;
            //?????? ???????????????id
            String  unTrafficAlarmId = DigestUtils.uuid().replaceAll("-","") ;

            //???????????????id ??? ??????id ??????
            UnTrafficAlarmEntity unTrafficAlarmEntity = UntrafficAlarmTransformUtil.transform( inputInfo.getUnTrafficAlarmVO()  ) ;
            unTrafficAlarmEntity.setDealState( 0 );
            unTrafficAlarmEntity.setId( unTrafficAlarmId );

            //?????????????????????
            logService.infoLog(logger, "repository", "save(dbUnTrafficAlarmEntity)", "repository is started...");
            Long startUnTrafficAlarm = System.currentTimeMillis();

            //??????
            accessor.save( unTrafficAlarmEntity ) ;

            Long endUnTrafficAlarm = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbUnTrafficAlarmEntity)", String.format("repository is finished,execute time is :%sms", endUnTrafficAlarm - startUnTrafficAlarm));


           if( unTrafficAlarmEntity != null  ){

               //????????????????????????????????????????????????
               if ( whetherSaveIncident ){
                   //??????????????? ?????????
                   IncidentSaveInputInfo queryBean = UntrafficAlarmTransformUtil.transformIncident( inputInfo.getUnTrafficAlarmVO() ) ;
                   queryBean.setUnTrafficAlarmId( unTrafficAlarmEntity.getId() );
                   IncidentBean incidentBean  = incidentService.saveIncident( queryBean,null,false,false ) ;
               }

               Map<String, String> organizationNameMap =  organizationService.findOrganizationNameMap() ;  //????????????
               Map<String,Map<String,String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
               res = UntrafficAlarmTransformUtil.transform( unTrafficAlarmEntity , dicsMap , organizationNameMap ) ;

           }

           //?????? ?????????????????????/????????????  ??????????????????????????????  ????????????????????????????????????
           if(  inputInfo.getTrafficAlarType() == 1 && !whetherSaveIncident){

               //??????webscoket?????? ???????????? ??????????????????
               Set<String> orgIdCodeSet = new HashSet<>();

//               if ( Strings.isBlank( unTrafficAlarmEntity.getSendOrgCode() ) ) {
//                   // ????????????id??????
//                   Set<String> orgIdSet = new HashSet<>();
//                   orgIdCodeSet.add( organizationService.getRootOrgId() ) ;
//                   List<String> orgIds = new ArrayList<>(orgIdSet) ;
//                   orgIdCodeSet.addAll( orgIds ) ;
//                   //????????????ids??????????????????
//                   List<String> orgCodes = organizationService.findOrganizationCodesByIds(orgIds) ;
//                   orgIdCodeSet.addAll( orgCodes ) ;
//               } else {
//                   // set????????????
//                   orgIdCodeSet.add(unTrafficAlarmEntity.getSendOrgCode());
//                   //  ??????id
//                   String organizationId = organizationService.findOrganizationIdsByCode(unTrafficAlarmEntity.getSendOrgCode());
//                   if (Objects.nonNull(organizationId)){
//                       // set??????id
//                       orgIdCodeSet.add(organizationId);
//                   }
//               }
               // ???????????? ???????????? ??????????????????
               // ????????????id??????
               Set<String> orgIdSet = new HashSet<>();
               //??????????????????????????????id
               String organizationId = null ;
               if( Strings.isNotBlank( res.getDistrictCode()) ){
                   OrgRelationshipBean orgRelationship  = orgRelationshipService.findOrgRelationship( res.getDistrictCode() ,
                           TrafficTypeBakEnum.INCIDENT_TRAFFICE_TYPE_QQXZ110.getCode()  );
                   organizationId = orgRelationship.getTransferDeptCode() ;
                   if( Strings.isBlank( organizationId) ){
                       organizationId = organizationService.getRootOrgId() ;
                   }
               }else{
                   organizationId = organizationService.getRootOrgId() ;
               }

               orgIdSet.add( organizationId ) ;
               List<String> orgIds = new ArrayList<>(orgIdSet) ;
               orgIdCodeSet.addAll( orgIds ) ;
               //????????????ids??????????????????
               List<String> ids =  organizationService.findOrganizationIdsByCodes(orgIds) ;
               List<String> orgCodes = organizationService.findOrganizationCodesByIds(orgIds) ;
               orgIdCodeSet.addAll( ids ) ;
               orgIdCodeSet.addAll( orgCodes ) ;
               //????????????
               Set<String> roles = new HashSet<>() ;
               roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //?????????
               roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_DISPATCH.getCode() ) ) ; //????????????
               roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_INTELLIGENT.getCode() ) ) ; //???????????????
               roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //????????????

               notifyActionService.pushMessageToDefaultSystemBusinessOrgRole( WebsocketCodeEnum.UNCALL_ACCEPT_INICIDENT.getCode() ,   res   ,orgIdCodeSet , roles );
           }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveUnTrafficAlarmIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return unTrafficAlarmId   ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveUnTrafficAlarmIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_UNTRAFFIC_ALAR_INCIDENT_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #receiveConfirmTrafficAlarm(  String ,   String )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public   Boolean receiveConfirmTrafficAlarm(   String  alarmPhone , String agentNumber     ) {
        if ( Strings.isBlank( alarmPhone )  || Strings.isBlank( agentNumber ) ) {
            logService.infoLog(logger, "service", "receiveConfirmTrafficAlarm", "alarmPhone or agentNumber is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "receiveConfirmTrafficAlarm", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false   ;

            //???????????????????????? ????????????
            logService.infoLog(logger, "repository", "save(dbUnTrafficAlarmEntity)", "repository is started...");
            Long startUnTrafficAlarm = System.currentTimeMillis();

            //??????
            List<UnTrafficAlarmEntity> unTrafficAlarmEntityList  = unTrafficAlarmRepository.findUnTrafficAlarmByAlarmPhone( alarmPhone ) ;

            Long endUnTrafficAlarm = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbUnTrafficAlarmEntity)", String.format("repository is finished,execute time is :%sms", endUnTrafficAlarm - startUnTrafficAlarm));


            if( unTrafficAlarmEntityList != null && unTrafficAlarmEntityList.size() > 0 ){
                UnTrafficAlarmEntity unTrafficAlarmEntity = unTrafficAlarmEntityList.get(0) ;
                Map<String, String> organizationNameMap =  organizationService.findOrganizationNameMap() ;  //????????????
                Map<String,Map<String,String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                UnTrafficAlarmBean unTrafficAlarmBean  = UntrafficAlarmTransformUtil.transform( unTrafficAlarmEntity , dicsMap , organizationNameMap ) ;

                //??????webscoket?????? ?????????????????????????????????????????????
                AgentBean agentBean = agentService.findAgent( agentNumber );
                if( agentBean != null && agentBean.getPersonBean() != null ){
                    List<ReceiverMessageBean> receivers =  new ArrayList<>() ;
                    ReceiverMessageBean receiver = new ReceiverMessageBean("user", agentBean.getPersonBean().getAccount());
                    receivers.add( receiver ) ;

                    notifyActionService.pushMessage( WebsocketCodeEnum.RECEIVE_CALL_INICIDENT.getCode() ,   unTrafficAlarmBean    , receivers  );
                }
            }




            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveUnTrafficAlarmIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveUnTrafficAlarmIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_UNTRAFFIC_ALAR_INCIDENT_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #receiveConfirmUnTrafficAlarm(String , String , String , Integer )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  Boolean receiveConfirmUnTrafficAlarm( String  receiveMessageId , String  incidentId , String agentNumber , Integer backResult  ) {
        if ( Strings.isBlank( incidentId )) {
            logService.infoLog(logger, "service", "receivingConfirmUnTrafficAlarm", "originalIncidentNumber is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "receivingConfirmUnTrafficAlarm", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false   ;

            //????????????id?????? ????????????
            UnTrafficAlarmEntity unTrafficAlarmEntity = accessor.getById( receiveMessageId , UnTrafficAlarmEntity.class ) ;

            if( unTrafficAlarmEntity != null ){

                ReceiveMessageBean receiveMessageBean = new ReceiveMessageBean() ;
                receiveMessageBean.setOriginalIncidentNumber( unTrafficAlarmEntity.getOriginalIncidentNumber() );
                receiveMessageBean.setBackResult( backResult );

                String integrateUrl =  env.getProperty("integrateUrl") ;
                try {
                    restClientInvoke.post(String.format("%s/rest/iecs/v1.0/outside/transferOutIncident", integrateUrl),
                            receiveMessageBean, DataVO.class);
                    if (logger.isDebugEnabled()) {
                        logger.debug(String.format("Invoke the %s restful service fail.", integrateUrl));
                    }
                    res = true ;
                } catch (RestInvokeException ex) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("Invoke the %s restful service fail.", integrateUrl), ex);
                    }
                }



                //??????webscoket?????? ???????????? ??????????????????
                //??????????????????
                Set<String> orgIdCodeSet = new HashSet<>();
                Set<String> orgIdSet = organizationService.findOrganizationIdSet();
                //??????????????????
                List<String> orgIds = new ArrayList<>() ;
                orgIds.addAll( orgIdSet ) ;
                List<String> orgCodes = organizationService.findOrganizationCodesByIds( orgIds) ;

                orgIdCodeSet.addAll( orgIds ) ;
                orgIdCodeSet.addAll( orgCodes ) ;
                //????????????
                Set<String> roles = new HashSet<>() ;
                roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //?????????
                roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_DISPATCH.getCode() ) ) ; //????????????
                roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_INTELLIGENT.getCode() ) ) ; //???????????????
                roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //????????????

                //?????????????????????????????????
                ReceiveMessageBean data = new ReceiveMessageBean();
                data.setReceiveMessageId( receiveMessageId );
                data.setOriginalIncidentNumber( unTrafficAlarmEntity.getOriginalIncidentNumber() );
                data.setReceiveAgentNumber( agentNumber );
                data.setBackResult( backResult );

                notifyActionService.pushMessageToDefaultSystemBusinessOrgRole( WebsocketCodeEnum.RECEIVE_UNCALL_INICIDENT.getCode() ,   data    ,orgIdCodeSet , roles );
            }



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveUnTrafficAlarmIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveUnTrafficAlarmIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_UNTRAFFIC_ALAR_INCIDENT_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #findUnTrafficAlarm(String)
     */
    @Transactional( readOnly =  true )
    @Override
    public List<UnTrafficAlarmBean> findUnTrafficAlarm(String incidentId) {
        if (Strings.isBlank( incidentId) ) {
            logService.infoLog(logger, "service", "findUnTrafficAlarm", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findUnTrafficAlarm", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<UnTrafficAlarmBean> res = new ArrayList<>();

            //?????????????????????
            logService.infoLog(logger, "repository", "save(dbUnTrafficAlarmEntity)", "repository is started...");
            Long startUnTrafficAlarm = System.currentTimeMillis();

            List<UnTrafficAlarmEntity> unTrafficAlarmEntityList = unTrafficAlarmRepository.findUnTrafficAlarmByIncidentId( incidentId ) ;


            Long endUnTrafficAlarm = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbUnTrafficAlarmEntity)", String.format("repository is finished,execute time is :%sms", endUnTrafficAlarm - startUnTrafficAlarm));

            if(unTrafficAlarmEntityList != null && unTrafficAlarmEntityList.size() >0  ){
                Map<String, String> organizationNameMap =  organizationService.findOrganizationNameMap() ;  //????????????
                Map<String,Map<String,String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                for( UnTrafficAlarmEntity unTrafficAlarmEntity : unTrafficAlarmEntityList ){
                    UnTrafficAlarmBean unTrafficAlarmBean  = UntrafficAlarmTransformUtil.transform( unTrafficAlarmEntity ,dicsMap , organizationNameMap ) ;
                    res.add( unTrafficAlarmBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUnTrafficAlarm", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUnTrafficAlarm", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_UNTRAFFIC_ALAR_INCIDENT_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #writeBackIncidentId(String,String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean writeBackIncidentId(String unTrafficAlarmId,String incidentId) {
        if (Strings.isBlank(unTrafficAlarmId) || Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "writeBackIncidentId", "incidentId or unTrafficAlarmId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "writeBackIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();

            UnTrafficAlarmEntity unTrafficAlarmEntity = accessor.getById(unTrafficAlarmId,UnTrafficAlarmEntity.class);

            if( unTrafficAlarmEntity == null ){
                logService.writeLog(logger, "service", "writeBackIncidentId", "unTrafficAlarmEntity is null ");
                return  Boolean.FALSE ;
            }

            //????????????id
            unTrafficAlarmEntity.setIncidentId(incidentId);

            logService.infoLog(logger, "restful", "transferAssociate110", "restful is started...");
            Long startrestful = System.currentTimeMillis();

            //????????????????????????
//            transferAssociate110(  unTrafficAlarmEntity.getOriginalIncidentNumber() , incidentId  );
            pushPoliceService.sendIncidentReceiveState( incidentId ,unTrafficAlarmEntity.getOriginalIncidentNumber() ,
                    unTrafficAlarmEntity.getDistrictCode() ) ;
            uncallAcceptListDelete( unTrafficAlarmId  ) ;

            Long endrestful = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "transferAssociate110", String.format("restful is finished,execute time is :%sms", endrestful - startrestful));

            logService.infoLog(logger, "repository", "save(dbUnTrafficAlarmEntity)", "repository is started...");
            Long startUnTrafficAlarm = System.currentTimeMillis();

            accessor.save( unTrafficAlarmEntity ) ;

            Long endUnTrafficAlarm = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbUnTrafficAlarmEntity)", String.format("repository is finished,execute time is :%sms", endUnTrafficAlarm - startUnTrafficAlarm));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "writeBackIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return Boolean.TRUE;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "writeBackIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.WRITE_BACK_INCIDENT_ID_FAIL);
        }
    }


    /**
     *  ??????110   ??????????????? ????????????????????????
     */
    private void transferAssociate110 ( String  transferIncidentId , String  incidentId    ){

        //??????110 ????????????
        SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType( "transferTo110" ) ;
        if( systemConfigurationBean == null ||  Strings.isBlank( systemConfigurationBean.getConfigValue() )){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        String transferTo110Url =  systemConfigurationBean.getConfigValue() ;
        TransferAssociateBean transferAssociateBean = new TransferAssociateBean() ;
        // ????????????110  ??????????????????30
        transferAssociateBean.setIncidentId( transferIncidentId );
        transferAssociateBean.setTransferIncidentId( incidentId    );
        try {
            //???????????? ???????????? ??????????????? ??????????????????
            restClientInvoke.post(String.format("%s/rest/ecs/v3.0/brace/transferAssociate", transferTo110Url),
                    transferAssociateBean  , DataVO.class);
        }catch (RestInvokeException ex) {
            logger.error(String.format("Invoke the %s restful service fail.", transferTo110Url), ex);
        }

    }




    /**
     * {@inheritDoc}
     *
     * @see #uncallAcceptListDelete( String )
     */
    @Transactional
    @Override
    public Boolean lockUncallAccept (String id   ){
        try {
            UserInfo userInfo = userService.getUserInfo();
            Long time = servletService.getSystemTime();
            logService.infoLog(logger, "service", "lockUncallAccept", "start...");
            Long time1 = System.currentTimeMillis();
            try {
                UnTrafficAlarmEntity po = accessor.getById(id , UnTrafficAlarmEntity.class );
                if (po!=null&&(po.getDealState()==null||po.getDealState()<2)){
                    po.setDealState(2);
                    accessor.save(po);
                }
            }catch (Exception ex){
                logService.erorLog(logger,"service","lockUncallAccept","fail to update subAlarm state",ex);
            }
            List<AgentBean> allAgent = agentService.findAllAgent();
            List<String> receivers = new ArrayList<>();
            if (allAgent != null && allAgent.size() > 0) {
                for (AgentBean agentBO : allAgent) {
                    if (  agentBO.getPersonBean()  != null  &&
                            agentBO.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode() &&
                            ( agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_CALLTAKING) ||
                                    agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR) ||
                                    agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_INTELLIGENT)  ||
                                    agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_SENIOR_DISPATCH) ||
                                    agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_CALLTAKING_DISPATCH) ) ){
                        if(  !agentBO.getPersonBean().getAccount().equals( userInfo.getAccount() )    ){
                            receivers.add(agentBO.getPersonBean().getAccount());
                        }
                    }
                }
                //websocket??????
                if (!receivers.isEmpty()) {
                    notifyActionService.pushMessageToUsers(WebsocketCodeEnum.UNCALL_ACCEPT_LISTLOCK.getCode(), id , receivers);
                }
            }
            Long time2 = System.currentTimeMillis();
            logService.infoLog(logger, "service", "lockUncallAccept", String.format("finish,execution time is :%s ms", time2 - time1));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "lockUncallAccept", String.format("send uncallAcceptListDelete websocket fail,the phone number is"), ex);
            throw new AcceptException(AcceptException.AccetpErrors.LOCK_UNCALLACCEPT_FAIL );
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #uncallAcceptListDelete( String )
     */
    @Transactional
    @Override
    public Boolean  uncallAcceptListDelete( String  id   ) {
        try {
            UserInfo userInfo = userService.getUserInfo();
            Long time = servletService.getSystemTime();
            logService.infoLog(logger, "service", "uncallAcceptListDelete", "start...");
            Long time1 = System.currentTimeMillis();
            try {
                UnTrafficAlarmEntity po = accessor.getById(id , UnTrafficAlarmEntity.class );
                if (po!=null&&(po.getDealState()==null||po.getDealState()<2)){
                    po.setDealState(2);
                    accessor.save(po);
                }
            }catch (Exception ex){
                logService.erorLog(logger,"service","uncallAcceptListDelete","fail to update subAlarm state",ex);
            }
            List<AgentBean> allAgent = agentService.findAllAgent();
            List<String> receivers = new ArrayList<>();
            logService.infoLog(logger, "service", "uncallAcceptListDelete", "AgentBean is :" + allAgent);
            if (allAgent != null && allAgent.size() > 0) {
                for (AgentBean agentBO : allAgent) {
                    if ((agentBO.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode() &&
                            agentBO.getPersonBean()!=null&&agentBO.getPersonBean().getPersonRole()!=null)&&
                            (agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_CALLTAKING) ||
                                    agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR)  ||
                                    agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_INTELLIGENT) ||
                                    agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_SENIOR_DISPATCH)  ||
                                    agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_CALLTAKING_DISPATCH)  )) {
                        receivers.add(agentBO.getPersonBean().getAccount() ) ;
                    }
                }
                //websocket??????
                if (!receivers.isEmpty() ) {
                    logService.infoLog(logger, "service", "uncallAcceptListDelete", "Call push method parameters id is :" + id + ",and receivers is " +receivers);
                    notifyActionService.pushMessageToUsers(WebsocketCodeEnum.UNCALL_ACCEPT_LISTDELETE.getCode(), id , receivers);
                }
            }
            Long time2 = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveInteractiveRecord", String.format("finish,execution time is :%s ms", time2 - time1));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "uncallAcceptListDelete", "send uncallAcceptListDelete websocket " +
                    "fail,the phone number is null", ex);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_UNCALLACCEPT_FAIL);
        }
    }











}
