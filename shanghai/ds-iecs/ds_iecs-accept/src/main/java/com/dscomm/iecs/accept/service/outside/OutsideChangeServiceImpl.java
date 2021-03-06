package com.dscomm.iecs.accept.service.outside;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.dal.po.TransferIncidentEntity;
import com.dscomm.iecs.accept.dal.repository.TransferIncidentRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.TransferIncidentBean;
import com.dscomm.iecs.accept.restful.vo.ReceiveUnTrafficAlarmVO;
import com.dscomm.iecs.accept.service.DocumentService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.OrgRelationshipService;
import com.dscomm.iecs.accept.service.bean.FireTransferBean;
import com.dscomm.iecs.accept.service.bean.FireTransferVO;
import com.dscomm.iecs.accept.service.bean.cad.IncidentDossierVO;
import com.dscomm.iecs.accept.service.pushData.PushPoliceService;
import com.dscomm.iecs.accept.utils.transform.UntrafficAlarmTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.OrgRelationshipBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.ext.TrafficTypeBakEnum;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_QT;
import com.dscomm.iecs.ext.incident.handle.HANDLE_TYPE_LA;
import com.dscomm.iecs.ext.incident.handle.HANDLE_TYPE_WLA;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.client.rest.RestInvokeException;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.dscomm.iecs.ext.TrafficTypeBakEnum.*;


/**
 * ????????????????????? ?????? ?????????
 */
@Component("outsideChangeServiceImpl")
public class OutsideChangeServiceImpl implements OutsideChangeService {

    private static final Logger logger = LoggerFactory.getLogger(OutsideChangeServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private RestClientInvoke restClientInvoke = null ;
    private IncidentService incidentService ;
    private ServletService servletService ;
    private OrganizationService organizationService ;
    private NotifyActionService notifyActionService ;
    private UserService userService ;
    private TransferIncidentRepository transferIncidentRepository ;
    private SystemConfigurationService systemConfigurationService ;
    private OrgRelationshipService orgRelationshipService ;
    private DocumentService documentService ;
    private PushPoliceService pushPoliceService ;
    private Boolean whetherPolice;
    /**
     * ?????????????????????
     */
    @Autowired
    public OutsideChangeServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env ,
                                    IncidentService incidentService , ServletService servletService ,
                                    OrganizationService organizationService  ,
                                    NotifyActionService notifyActionService , UserService userService ,
                                    TransferIncidentRepository transferIncidentRepository ,
                                     SystemConfigurationService systemConfigurationService ,
                                    OrgRelationshipService orgRelationshipService ,   DocumentService documentService ,
                                    PushPoliceService pushPoliceService

    ) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.restClientInvoke = new RestClientInvoke();
        this.incidentService  =  incidentService ;
        this.servletService = servletService ;
        this.organizationService = organizationService ;
        this.notifyActionService = notifyActionService ;
        this.userService = userService ;
        this.transferIncidentRepository = transferIncidentRepository ;
        this.systemConfigurationService = systemConfigurationService ;
        this.orgRelationshipService = orgRelationshipService ;
        this.documentService = documentService ;
        this.pushPoliceService = pushPoliceService ;
        whetherPolice = Boolean.parseBoolean(env.getProperty("police.enable"));

    }

    /**
     * {@inheritDoc}
     *
     * @see #transferOutIncident(OutsideInputInfo)
     */
    @Transactional(rollbackFor = Exception.class )
    @Override
    public  Boolean transferOutIncident(  OutsideInputInfo queryBean ) {
        if (  null == queryBean   ) {
            logService.infoLog(logger, "service", "transferOutIncident", " OutsideInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "transferOutIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false   ;

            //???????????????????????? ?????????????????????????????????
            IncidentSaveInputInfo incidentSaveInputInfo =  queryBean.getIncident() ;
            incidentSaveInputInfo.setHandleType( HANDLE_TYPE_WLA.getCode());
            String incidentId = incidentSaveInputInfo.getId() ; //??????id
            IncidentBean incidentBean  = null ;
            if( Strings.isBlank( incidentId ) ){
                incidentBean = incidentService.saveIncident( incidentSaveInputInfo , null , true , false  ) ;
            }else{
                incidentBean = incidentService.findIncident( incidentId , false ) ;
                if( incidentBean == null ){
                    incidentBean = incidentService.saveIncident( incidentSaveInputInfo , null , true , false  ) ;
                }
            }
            incidentId = incidentBean.getId() ;

            UserInfo userInfo = userService.getUserInfo() ;
            if( userInfo == null ){
                userInfo = new UserInfo();
            }

            //????????????  110 ??????
            List<String> type = queryBean.getType() ;
            if( type.contains(  TrafficTypeBakEnum.INCIDENT_TRAFFICE_TYPE_QQXZ110.getCode() )  ){
                OrgRelationshipBean orgRelationshipBean = orgRelationshipService.
                        findOrgRelationship( userInfo.getOrgId() , TrafficTypeBakEnum.INCIDENT_TRAFFICE_TYPE_QQXZ110.getCode() ) ;
                if( orgRelationshipBean != null ){
                    queryBean.setReceiveUnit( orgRelationshipBean.getTransferDeptCode() );
                }
                pushPoliceService.sendPoliceIncident( incidentBean ) ;
//                transferTo110( queryBean   ) ;
            }
            // ????????? ??????????????? ??????
            incidentService.removeIncident( incidentBean.getId() ) ;

            //???????????? ??? ???????????? ???????????? ???????????? ???
            TransferIncidentEntity transferIncidentEntity = transform( queryBean ) ;
            if( transferIncidentEntity != null ){
                transferIncidentEntity.setIncidentId( incidentId );
                //????????????  ????????????_??????????????????
                transferIncidentEntity.setTransferType( TRAFFICE_HANDLE_TYPE_ZJ.getCode() + "_" +  TrafficTypeBakEnum.INCIDENT_TRAFFICE_TYPE_QQXZ110.getCode() );
                transferIncidentEntity.setTransferUnit( userInfo.getOrgId() );
                transferIncidentEntity.setTransferPersonId( userInfo.getAccount() );
                transferIncidentEntity.setTransferPersonName( userInfo.getPersonName() );
                transferIncidentEntity.setTransferSeatNumber( userInfo.getAgentNum() );
                transferIncidentEntity.setTransferTime( servletService.getSystemTime() );
                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( transferIncidentEntity ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                //????????? ????????????
                DocumentSaveInputInfo documentSaveInputInfoHandlde = new DocumentSaveInputInfo();
                documentSaveInputInfoHandlde.setIncidentId( incidentBean.getId() );
                documentSaveInputInfoHandlde.setDateSourceId( transferIncidentEntity.getId() );
                documentSaveInputInfoHandlde.setTitle(DOCUMENT_TYPE_QT.getName());
                documentSaveInputInfoHandlde.setContent( "??????");
                documentSaveInputInfoHandlde.setType(DOCUMENT_TYPE_QT.getCode());
                documentSaveInputInfoHandlde.setFeedbackPerson(userInfo.getPersonName());
                documentSaveInputInfoHandlde.setFeedbackOrganizationId(userInfo.getOrgId());
                documentSaveInputInfoHandlde.setTerminalId(null);
                documentSaveInputInfoHandlde.setRemarks(null);
                documentService.saveDocument(documentSaveInputInfoHandlde);
            }

            //??????????????????????????????
            Set<String> orgSet = new HashSet<>() ;
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( incidentId );
            orgSet.addAll( orgIds ) ;
            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            if( orgCodes != null && orgCodes.size() > 0 ){
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_TRANSFER_OUT.getCode(), incidentBean , orgSet);
            }

            res = true ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "transferOutIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "transferOutIncident", "execution error", ex);
            return  false ;
        }
    }

    /**
     * ?????? 110
     * @param queryBean
     */
    private void transferTo110( OutsideInputInfo queryBean     ){

        //??????110 ????????????
        SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType( "transferTo110" ) ;
        if( systemConfigurationBean == null ||  Strings.isBlank( systemConfigurationBean.getConfigValue() )){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        String transferTo110Url =  systemConfigurationBean.getConfigValue() ;
        FireTransferBean fireTransferBean = UntrafficAlarmTransformUtil.transform(queryBean);
        // ????????????110  ??????????????????30
        fireTransferBean.setAlertCode("30");
        fireTransferBean.setSendOrgCode( queryBean.getReceiveUnit()  );
        try {
            //???????????????
            restClientInvoke.post(String.format("%s/rest/ecs/v3.0/brace/fireTransfer", transferTo110Url),
                    new FireTransferVO(fireTransferBean) , DataVO.class);

        }catch (RestInvokeException ex) {
            logger.error(String.format("Invoke the %s restful service fail.", transferTo110Url), ex);
            throw new AcceptException(   AcceptException.AccetpErrors.REQUEST_OUT_SIDE_FAIL  );
        }

    }



    /**
     * ??????110????????????
     * @param incidentId ??????id
     */
    @Override
    public IncidentDossierVO findIncidentDossier110(String incidentId) {
        //??????110 ????????????
        SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType( "transferTo110" ) ;
        if( systemConfigurationBean == null ||  Strings.isBlank( systemConfigurationBean.getConfigValue() )){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        String transferTo110Url =  systemConfigurationBean.getConfigValue() ;
        // ????????????110  ??????????????????30
        Map map = new HashMap();
        map.put("incidentId",incidentId);
        try {
            // ??????????????????????????????
            DataVO<JSONObject> dataVO = restClientInvoke.post(String.format("%s/rest/ecs/v3.0/brace/getIncidentDossierByIncidentId", transferTo110Url),
                    map, DataVO.class);
            IncidentDossierVO incidentDossierVO = JSON.toJavaObject(dataVO.getData(), IncidentDossierVO.class);
            return incidentDossierVO;
        }catch (RestInvokeException ex) {
            logger.error(String.format("Invoke the %s restful service fail.", transferTo110Url), ex);
        }
        return null;
    }




    /**
     * {@inheritDoc}
     *
     * @see #receiveTransferOutIncident(ReceiveUnTrafficAlarmVO )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public   Boolean receiveTransferOutIncident(  ReceiveUnTrafficAlarmVO inputInfo ) {
        if ( Strings.isBlank( inputInfo.getIncidentId() )) {
            logService.infoLog(logger, "service", "receivingConfirmUnTrafficAlarm", "getIncidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "receivingConfirmUnTrafficAlarm", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false   ;
            String incidentId = inputInfo.getIncidentId() ;
            Integer backResult = inputInfo.getBackResult() ;
            //????????????id?????? ????????????
            if( Strings.isBlank( incidentId ) ){
                logService.infoLog(logger, "service", "receivingConfirmUnTrafficAlarm", " IncidentBean is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL );
            }

            //????????????id????????????????????????
            TransferIncidentEntity transferIncidentEntity = transferIncidentRepository.findTransferIncidentByIncidentId( incidentId ) ;
//            //????????????id??????????????????
//            IncidentEntity incidentEntity = accessor.getById( incidentId , IncidentEntity.class ) ;
//            // ????????????????????????????????????
//            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
//
//            // ????????????id-????????????map
//            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
//            IncidentBean incidentBean = IncidentTransformUtil.transform( incidentEntity , dicsMap , organizationNameMap   ) ;
//            if( Strings.isNotBlank( incidentBean.getKeyUnitId( )  ) ) {
//                KeyUnitSimpleBean keyUnitSimple =keyUnitService.findKeyUnitSimple( incidentBean.getKeyUnitId() ) ;
//                if( keyUnitSimple != null ){
//                    incidentBean.setKeyUnitName(keyUnitSimple.getUnitName() );
//                }
//            }

            if( transferIncidentEntity != null ) {
                transferIncidentEntity.setTransferIncidentId( inputInfo.getOriginalIncidentNumber() );
                transferIncidentEntity.setReceiveStatus( String.valueOf(EnableEnum.ENABLE_TRUE.getCode() ) );

                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( transferIncidentEntity ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }


//            //??????????????????
//            if( backResult == 1 ){
//
//                transferIncidentEntity.setTransferIncidentId( inputInfo.getOriginalIncidentNumber() );
//                transferIncidentEntity.setReceiveStatus( String.valueOf(EnableEnum.ENABLE_TRUE.getCode() ) );
//
//                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", "repository is started...");
//                Long start = System.currentTimeMillis();
//
//                accessor.save( transferIncidentEntity ) ;
//
//                Long end = System.currentTimeMillis();
//                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));
//
//                //?????????????????? ????????????
//                List<ReceiverMessageBean> receivers = new ArrayList<>() ;
//                ReceiverMessageBean receiverMessageBean = new ReceiverMessageBean("user", transferIncidentEntity.getTransferPersonId()  );
//                receivers.add( receiverMessageBean ) ;
//
//                notifyActionService.pushMessage( WebsocketCodeEnum.TRANSFER_OUT_RECEIVE.getCode() ,   incidentBean    , receivers );
//
//            }else if( backResult == 0 ){
//
//                transferIncidentEntity.setTransferIncidentId( inputInfo.getOriginalIncidentNumber() );
//
//                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", "repository is started...");
//                Long start = System.currentTimeMillis();
//
//                accessor.save( transferIncidentEntity ) ;
//
//                Long end = System.currentTimeMillis();
//                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));
//
//
//                List<ReceiverMessageBean> receivers = new ArrayList<>() ;
//                ReceiverMessageBean receiverMessageBean = new ReceiverMessageBean("user", transferIncidentEntity.getTransferPersonId()  );
//                receivers.add( receiverMessageBean ) ;
//
//                notifyActionService.pushMessage( WebsocketCodeEnum.TRANSFER_OUT_REFUSE.getCode() ,   incidentBean    , receivers );
//            }

            res = true ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveUnTrafficAlarmIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveUnTrafficAlarmIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_UNTRAFFIC_ALAR_INCIDENT_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #dislocationIncident(OutsideInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public  Boolean dislocationIncident(  OutsideInputInfo queryBean ) {
        if (  null == queryBean  || Strings.isBlank( queryBean.getIncidentId() )) {
            logService.infoLog(logger, "service", "dislocationIncident", " OutsideInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        Boolean res =   false    ;

        try {
            logService.infoLog(logger, "service", "dislocationIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            UserInfo userInfo = userService.getUserInfo() ;
            if( userInfo == null ){
                userInfo = new UserInfo();
            }

            //????????????id?????? ????????????
            IncidentBean incidentBean = incidentService.findIncident( queryBean.getIncidentId() , false ) ;
            if( null == incidentBean ){
                IncidentSaveInputInfo  incidentSaveInputInfo = queryBean.getIncident() ;
                incidentSaveInputInfo.setHandleType( HANDLE_TYPE_WLA.getCode() );
                incidentBean = incidentService.saveIncident( incidentSaveInputInfo , userInfo  , false ,false ) ;
            }

            if (whetherPolice) {
                String policeUrl = env.getProperty("policeUrl");
                try {
                    restClientInvoke.post(String.format("%s/rest/iecs/v1.0/outside/dislocationIncident", policeUrl),
                            queryBean, DataVO.class);
                    if (logger.isDebugEnabled()) {
                        logger.debug(String.format("Invoke the %s restful service fail.", policeUrl));
                    }
                } catch (RestInvokeException ex) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("Invoke the %s restful service fail.", policeUrl), ex);
                    }
                }
            }

            // ????????? ??????????????? ??????
            incidentService.removeIncident( incidentBean.getId() ) ;

            //???????????? ?????? ??????????????????
            TransferIncidentEntity transferIncidentEntity = transform( queryBean ) ;
            if( transferIncidentEntity != null ){
                transferIncidentEntity.setIncidentId( incidentBean.getId()  );
                transferIncidentEntity.setTransferType( TRAFFICE_HANDLE_TYPE_CWJZ.getCode() + "_" +  TrafficTypeBakEnum.INCIDENT_TRAFFICE_TYPE_QQXZ110.getCode() );
                transferIncidentEntity.setTransferUnit( userInfo.getOrgId() );
                transferIncidentEntity.setTransferPersonId( userInfo.getAccount() );
                transferIncidentEntity.setTransferPersonName( userInfo.getPersonName() );
                transferIncidentEntity.setTransferSeatNumber( userInfo.getAgentNum() );
                transferIncidentEntity.setTransferTime( servletService.getSystemTime() );
                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( transferIncidentEntity ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                //????????? ????????????
                DocumentSaveInputInfo documentSaveInputInfoHandlde = new DocumentSaveInputInfo();
                documentSaveInputInfoHandlde.setIncidentId( incidentBean.getId() );
                documentSaveInputInfoHandlde.setDateSourceId( transferIncidentEntity.getId() );
                documentSaveInputInfoHandlde.setTitle(DOCUMENT_TYPE_QT.getName());
                documentSaveInputInfoHandlde.setContent( "??????");
                documentSaveInputInfoHandlde.setType(DOCUMENT_TYPE_QT.getCode());
                documentSaveInputInfoHandlde.setFeedbackPerson(userInfo.getPersonName());
                documentSaveInputInfoHandlde.setFeedbackOrganizationId(userInfo.getOrgId());
                documentSaveInputInfoHandlde.setTerminalId(null);
                documentSaveInputInfoHandlde.setRemarks(null);
                documentService.saveDocument(documentSaveInputInfoHandlde);

            }

            //??????????????????????????????
            Set<String> orgSet = new HashSet<>() ;
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( queryBean.getIncidentId() );
            orgSet.addAll( orgIds ) ;
            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            if( orgSet != null && orgSet.size() > 0 ){
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_DISLOCATION.getCode(), incidentBean, orgSet);
            }

            res =   true     ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "dislocationIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "dislocationIncident", "execution error", ex);
        }
        return  res ;

    }

    /**
     * {@inheritDoc}
     *
     * @see #dislocationIncident(OutsideInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public   Boolean  dislocationRelayRecordNumber( String dislocationId , String relayRecordNumber  ) {
        if (   Strings.isBlank( dislocationId ) ) {
            logService.infoLog(logger, "service", "dislocationRelayRecordNumber", " OutsideInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res = false ;

        try {
            logService.infoLog(logger, "service", "dislocationRelayRecordNumber", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransferIncidentEntity transferIncidentEntity = accessor.getById( dislocationId ,TransferIncidentEntity.class  ) ;
            if( transferIncidentEntity != null ){
                transferIncidentEntity.setRemarks( relayRecordNumber );

                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( transferIncidentEntity ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                UserInfo userInfo = userService.getUserInfo() ;
                if( userInfo == null ){
                    userInfo = new UserInfo();
                }

                //???????????? ?????????
                DocumentSaveInputInfo documentSaveInputInfoHandlde = new DocumentSaveInputInfo();
                documentSaveInputInfoHandlde.setIncidentId( transferIncidentEntity.getIncidentId() );
                documentSaveInputInfoHandlde.setDateSourceId( dislocationId );
                documentSaveInputInfoHandlde.setTitle(DOCUMENT_TYPE_QT.getName());
                documentSaveInputInfoHandlde.setContent( "????????????");
                documentSaveInputInfoHandlde.setType(DOCUMENT_TYPE_QT.getCode());
                documentSaveInputInfoHandlde.setFeedbackPerson(userInfo.getPersonName());
                documentSaveInputInfoHandlde.setFeedbackOrganizationId(userInfo.getOrgId());
                documentSaveInputInfoHandlde.setTerminalId(null);
                documentSaveInputInfoHandlde.setRemarks(null);
                documentService.saveDocument(documentSaveInputInfoHandlde);

                res = true ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "dislocationRelayRecordNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "dislocationRelayRecordNumber", "execution error", ex);
        }
        return  res ;

    }


    /**
     * {@inheritDoc}
     *
     * @see #assistIncident(OutsideInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public  Boolean assistIncident(  OutsideInputInfo queryBean ) {
        if (  null == queryBean  || Strings.isBlank( queryBean.getIncidentId() )) {
            logService.infoLog(logger, "service", "assistIncident", " OutsideInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "assistIncident", "service is started...");
            Long logStart = System.currentTimeMillis();


            Boolean res = false   ;

            //???????????????????????? ?????????????????????????????????
            IncidentSaveInputInfo incidentSaveInputInfo =  queryBean.getIncident() ;
            incidentSaveInputInfo.setHandleType( HANDLE_TYPE_LA.getCode());
            String incidentId = incidentSaveInputInfo.getId() ; //??????id
            IncidentBean incidentBean  = null ;
            if( Strings.isBlank( incidentId ) ){
                incidentBean = incidentService.saveIncident( incidentSaveInputInfo , null , true , false  ) ;
            }else{
                incidentBean = incidentService.findIncident( incidentId , false ) ;
                if( incidentBean == null ){
                    incidentBean = incidentService.saveIncident( incidentSaveInputInfo , null , true , false  ) ;
                }
            }
            incidentId = incidentBean.getId() ;

            UserInfo userInfo = userService.getUserInfo() ;

            //????????????  ???????????? 110
            List<String> type = queryBean.getType() ;
            if( type.contains(  TrafficTypeBakEnum.INCIDENT_TRAFFICE_TYPE_QQXZ110.getCode() )  ){
                OrgRelationshipBean orgRelationshipBean = orgRelationshipService.findOrgRelationship(
                        userInfo.getOrgId() , TrafficTypeBakEnum.INCIDENT_TRAFFICE_TYPE_QQXZ110.getCode() ) ;
                if( orgRelationshipBean != null ){
                    queryBean.setReceiveUnit( orgRelationshipBean.getTransferDeptCode() );
                }
                queryBean.setOutsideTime( servletService.getSystemTime() );
                pushPoliceService.sendPoliceHelp(queryBean);
            }

            //???????????? ?????? ??????????????????
            TransferIncidentEntity transferIncidentEntity = transform( queryBean ) ;
            if( transferIncidentEntity != null ){
                transferIncidentEntity.setIncidentId( incidentBean.getId()  );
                transferIncidentEntity.setTransferType( TRAFFICE_HANDLE_TYPE_QQXZ.getCode() + "_" +  TrafficTypeBakEnum.INCIDENT_TRAFFICE_TYPE_QQXZ110.getCode() );
                transferIncidentEntity.setTransferUnit( userInfo.getOrgId() );
                transferIncidentEntity.setTransferPersonId( userInfo.getAccount() );
                transferIncidentEntity.setTransferPersonName( userInfo.getPersonName() );
                transferIncidentEntity.setTransferSeatNumber( userInfo.getAgentNum() );
                transferIncidentEntity.setTransferTime( servletService.getSystemTime() );
                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( transferIncidentEntity ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTransferIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                //????????? ????????????
                DocumentSaveInputInfo documentSaveInputInfoHandlde = new DocumentSaveInputInfo();
                documentSaveInputInfoHandlde.setIncidentId( incidentBean.getId() );
                documentSaveInputInfoHandlde.setDateSourceId( transferIncidentEntity.getId() );
                documentSaveInputInfoHandlde.setTitle(DOCUMENT_TYPE_QT.getName());
                documentSaveInputInfoHandlde.setContent( "????????????");
                documentSaveInputInfoHandlde.setType(DOCUMENT_TYPE_QT.getCode());
                documentSaveInputInfoHandlde.setFeedbackPerson(userInfo.getPersonName());
                documentSaveInputInfoHandlde.setFeedbackOrganizationId(userInfo.getOrgId());
                documentSaveInputInfoHandlde.setTerminalId(null);
                documentSaveInputInfoHandlde.setRemarks(null);
                documentService.saveDocument(documentSaveInputInfoHandlde);

            }


            //??????????????????????????????
            Set<String> orgSet = new HashSet<>() ;
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( incidentId );
            orgSet.addAll( orgIds ) ;
            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            if( orgCodes != null && orgCodes.size() > 0 ){
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_ASSIST.getCode(), incidentBean , orgSet);
            }

            res = true ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "assistIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "assistIncident", "execution error", ex);
            return  false ;
        }

    }

    /**
     * ???????????? ??????
     *
     * @param source  ????????????INPUT
     * @return ????????????PO
     */
    public static TransferIncidentEntity transform(OutsideInputInfo source ) {
        if (null != source) {
            TransferIncidentEntity target = new TransferIncidentEntity();
            target.setReceiveUnit( source.getReceiveUnit() );
            target.setReceiveStatus( String.valueOf(EnableEnum.ENABLE_FALSE.getCode() ));
            target.setRemarks( source.getExplain()  );
            return target;
        }
        return null;
    }

    /**
     * ???????????? ??????
     *
     * @param source  ????????????INPUT
     * @return ????????????PO
     */
    public static TransferIncidentBean transform(TransferIncidentEntity source ) {
        if (null != source) {
            TransferIncidentBean target = new TransferIncidentBean();
            target.setId( source.getId() );
            target.setIncidentId( source.getId()  );
            target.setTransferType( source.getTransferType() ); //????????????  10??????110???20????????????
            target.setTransferUnit( source.getTransferUnit() );
            target.setTransferPersonId( source.getTransferPersonId() );
            target.setTransferPersonName( source.getTransferPersonName() );
            target.setTransferSeatNumber( source.getTransferSeatNumber() );
            target.setTransferTime( source.getTransferTime() );

            target.setReceiveUnit( source.getReceiveUnit() );
            target.setReceiveStatus( source.getReceiveStatus() );
            target.setRemarks( source.getRemarks()   );
            return target;
        }
        return null;
    }

}