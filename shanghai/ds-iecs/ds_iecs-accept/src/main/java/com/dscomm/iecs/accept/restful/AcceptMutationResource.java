package com.dscomm.iecs.accept.restful;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.service.AuthenticateService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 *  accept restful mutation 接口
 */
@Path("iecs/v1.0/mutation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcceptMutationResource  {

    private static final Logger logger = LoggerFactory.getLogger(AcceptMutationResource.class);
    private LogService logService;
    private SessionDataStore sessionDataStore;
    private ServletService servletService ;
    private HandleService handleService;
    private ReinforcementService reinforcementService;
    private NewsCircularService newsCircularService ;
    private AuthenticateService authenticateService;
    private UserService userService;
    private LocaleService localeService ;
    private IncidentService incidentService ;
    private VehicleStatusChangeService vehicleStatusChangeService ;

    private FireSafetyMonitoringService fireSafetyMonitoringService ;
    private ParticipantFeedbackService participantFeedbackService;

    private HandleMiniatureStationService handleMiniatureStationService ;
    private HandoverRecordSerivce handoverRecordSerivce;
    private DocumentService documentService;




    @Autowired
    public AcceptMutationResource(LogService logService, SessionDataStore sessionDataStore, ServletService servletService,
                                  HandleService handleService, ReinforcementService reinforcementService,
                                  NewsCircularService newsCircularService, AuthenticateService authenticateService,
                                  UserService userService, LocaleService localeService, IncidentService incidentService,
                                  VehicleStatusChangeService vehicleStatusChangeService,
                                  FireSafetyMonitoringService fireSafetyMonitoringService,
                                  ParticipantFeedbackService participantFeedbackService,
                                  HandleMiniatureStationService handleMiniatureStationService,
                                  HandoverRecordSerivce handoverRecordSerivce,

                                  DocumentService documentService) {
        this.logService = logService;
        this.sessionDataStore  = sessionDataStore ;
        this.servletService = servletService ;
        this.handleService = handleService;
        this.reinforcementService = reinforcementService;
        this.newsCircularService = newsCircularService ;
        this.authenticateService = authenticateService ;
        this.userService = userService ;
        this.localeService = localeService ;
        this.incidentService = incidentService ;
        this.vehicleStatusChangeService = vehicleStatusChangeService  ;
        this.fireSafetyMonitoringService = fireSafetyMonitoringService ;
        this.participantFeedbackService = participantFeedbackService;
        this.handleMiniatureStationService = handleMiniatureStationService ;
        this.handoverRecordSerivce = handoverRecordSerivce;

        this.documentService = documentService;
    }

    /**
     * 登录信息验证
     *
     * @return
     */
    private void  getLogin(  HttpHeaders headers ,  String changeSource ,  String imei )  {
        try{
            String token = headers.getHeaderString("tokenkey");
            if( Strings.isBlank( token ) ){
                token =  System.currentTimeMillis() +  ",127.0.0.1," + changeSource +"," + imei+ "";

            }
            Boolean res = authenticateService.authentication(token);
            if (!res) {
                userService.login(token, null , null ,true);
            }
        }  catch (Exception ex) {
            logService.erorLog(logger, "service", "login", String.format("login fail,token is:%s",  ""), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
        }

    }

    /**
     * 请求增援
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("saveReinforcementAsk")
    @POST
    public DataVO<ReinforcementAskBean> saveReinforcementAsk(    @Context HttpHeaders headers, @QueryParam("changeSource") String changeSource , @QueryParam("imei") String imei  ,
                                    ReinforcementAskSaveInputInfo queryBean ) {
        logService.infoLog(logger, "restful", "saveReinforcementAsk", "restful is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        this.getLogin( headers , changeSource ,     imei  ) ;

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        ReinforcementAskBean res = reinforcementService.saveReinforcementAsk(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveReinforcementAsk", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }



    /**
     * 修改警经纬度
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("updateIncidentAddress")
    @POST
    public  DataVO<IncidentBean> updateIncidentAddress(  @Context HttpHeaders headers, @QueryParam("changeSource") String changeSource , @QueryParam("imei") String imei  ,
                                                         IncidentSaveInputInfo queryBean ) {
        logService.infoLog(logger, "restful", "updateIncidentAddress", "restful is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        this.getLogin( headers ,   changeSource ,   imei  ) ;

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getId())  ||
                Strings.isBlank(queryBean.getLongitude()) || Strings.isBlank(queryBean.getLatitude())  ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        IncidentBean res = incidentService.updateIncidentAddress(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "updateIncidentAddress", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     *  修改案件车辆状态（单车）
     *
     * @param headers 上下文环境变量
     * @return 返回结果
     */
    @Path("buildIncidentVehicleStatus")
    @POST
    public DataVO<Boolean> buildIncidentVehicleStatus(  @Context HttpHeaders headers, @QueryParam("changeSource") String changeSource , @QueryParam("imei") String imei  ,
                                                 @QueryParam("incidentId") String incidentId , @QueryParam("handleId") String handleId ,
                                                 @QueryParam("vehicleId") String vehicleId  , @QueryParam("vehicleStatusCode") String vehicleStatusCode

    ) {
        logService.infoLog(logger, "restful", "buildIncidentVehicleStatus", "restful is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        this.getLogin( headers ,   changeSource ,   imei  ) ;

        //参数判断
        if (    Strings.isBlank( vehicleId ) || Strings.isBlank( vehicleStatusCode )  ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        if(   Strings.isNotBlank( incidentId ) ){
            handleService.buildIncidentVehicleStatus( incidentId , handleId , vehicleId , vehicleStatusCode , changeSource  );
        }else{
            vehicleStatusChangeService.updateVehicleStatus( null , null ,  vehicleId, null , vehicleStatusCode , changeSource);

        }

        Boolean res = true ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "buildIncidentVehicleStatus", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 保存现场反馈信息
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("saveLocale")
    @POST
    public DataVO<LocaleBean> saveLocale(  LocaleSaveInputInfo queryBean ) {
        logService.infoLog(logger, "restful", "saveLocale", "restful is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        LocaleBean res = localeService.saveLocaleMobile(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveLocale", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }





    /**
     * 根据消息通知ids 变更消息通知服务状态 为已接收
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("findNewsCircularReceiverCondition")
    @POST
    public DataVO<Boolean>  receiveNewsCircular(   @Context HttpHeaders headers, IdsInputInfo queryBean ) {
        logService.infoLog(logger, "restful", "receiveNewsCircular", "restful is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        //参数判断
        if ( queryBean == null || queryBean.getIds() == null || queryBean.getIds().size() < 1 ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = newsCircularService.receiveNewsCircular( queryBean.getIds() )   ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "receiveNewsCircular", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 根据ajid,clid,cllx 设置头车（尾车）
     *
     * @param
     * @return 返回结果
     */
    @Path("saveHandleVehicleIdentification")
    @POST
    public DataVO<Boolean>  saveHandleVehicleIdentification( @QueryParam("incidentId") String incidentId
                                            ,@QueryParam("vehicleId") String vehicleId,@QueryParam("vehicleIdentification") String vehicleIdentification) {
        logService.infoLog(logger, "restful", "saveHandleVehicleIdentification", "graphql is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        if (  Strings.isBlank( incidentId) || Strings.isBlank( vehicleId ) || Strings.isBlank( vehicleIdentification )) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = handleService.saveHandleVehicleIdentification(incidentId,vehicleId,vehicleIdentification);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveHandleVehicleIdentification", String.format("graphql is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }

    /**
     * 保存参战人员反馈
     * @param queryBean 参战人员信息
     * @return 参战人员信息
     * */
    @Path("saveParticipant")
    @POST
    public DataVO<List<ParticipantFeedbackBean>> saveParticipant(@Context HttpHeaders headers,ParticipantSaveInputInfo queryBean ) {
        logService.infoLog(logger, "restful", "saveParticipant", "graphql is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;
        if ( null == queryBean  || Strings.isBlank( queryBean.getIncidentId() )  || Strings.isBlank( queryBean.getVehicleId() ) ||
         queryBean.getParticipantFeedbackSaveInputInfo() == null || queryBean.getParticipantFeedbackSaveInputInfo().size() < 1 ) {
            logService.infoLog(logger, "service", "saveParticipant", "ParticipantSaveInputInfo or incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        List<ParticipantFeedbackBean> res = participantFeedbackService.saveParticipant(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveParticipant", String.format("graphql is finished,execute time is :%sms", end - start));





        return new DataVO<>(res);

    }

    /**
     * 保存车辆实际返回人员
     *@param inputInfo 参战人员信息
     * */
    @Path("saveParticipantBack")
    @POST
    public DataVO<Boolean> saveParticipantBack(@Context HttpHeaders headers,ParticipantBackInputInfo inputInfo){

        logService.infoLog(logger, "restful", "saveParticipantBack", "graphql is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        Boolean res = participantFeedbackService.saveParticipantBack(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveParticipantBack", String.format("graphql is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }



    /**
     * 出入火场登记( 保存火场安全监控 )
     *
     * @return 返回结果
     */
    @Path("saveFireSafetyMonitoring")
    @POST
    public DataVO<List<FireSafetyMonitoringBean>> saveFireSafetyMonitoring(  @Context HttpHeaders headers, @QueryParam("changeSource") String changeSource , @QueryParam("imei") String imei  ,
            FireSafetySaveInputInfo queryBean) {
        logService.infoLog(logger, "restful", "saveFireSafetyMonitoring", "graphql is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId()) || queryBean.getFireSafetyType() == null
                || null == queryBean.getFireSafetyMonitoringSaveInputInfo() || queryBean.getFireSafetyMonitoringSaveInputInfo().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<FireSafetyMonitoringBean> res = fireSafetyMonitoringService.saveFireSafetyMonitoring(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveFireSafetyMonitoring", String.format("graphql is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }

    /**
     * 新增警情restful
     * */
    @Path("saveIncident")
    @POST
    public DataVO<IncidentBean> saveIncident(@Context HttpHeaders headers,IncidentSaveInputInfo inputInfo) {
        logService.infoLog(logger, "restful", "saveIncident", "restful is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveIncident", "IncidentSaveInputInfo    is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        IncidentBean res = incidentService.saveIncident(inputInfo,null,false,false);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveIncident", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }

    /**
     * 保存交接班日志
     * */
    @Path("saveHandoverRecord")
    @POST
    public DataVO<HandoverRecordBean> saveHandoverRecord(@Context HttpHeaders headers, HandoverRecordSaveInputInfo queryBean) {
        logService.infoLog(logger, "restful", "saveHandoverRecord", "restful is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        if (queryBean == null) {
            logService.infoLog(logger, "service", "saveIncident", "IncidentSaveInputInfo    is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //参数判断
        if( queryBean == null || StringUtils.isBlank(queryBean.getHandoverPersonId()) || StringUtils.isBlank(queryBean.getHandoverPersonName()) || queryBean.getHandoverTime() == null ){
            logService.infoLog(logger, "service", "saveHandoverRecord", "saveHandoverRecord     is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //逻辑
        HandoverRecordBean res = handoverRecordSerivce.saveHandoverRecordRest( queryBean );
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveHandoverRecord", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }

    /**
     * 警情引导保存文书
     * @param headers
     * @param queryBean
     * @return
     */
    @Path("saveIncidentGuidance")
    @POST
    public DataVO<DocumentBean> saveIncidentGuidance(@Context HttpHeaders headers, IncidentGuidanceInputInfo  queryBean) {
        try {
            logService.infoLog(logger, "graphql", "sendRespProcedureMessage", "graphql is started...");
            Long start = System.currentTimeMillis();

            if (queryBean == null){
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }
            //逻辑
            DocumentBean res =  documentService.saveIncidentGuidance(queryBean);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "graphql", "sendRespProcedureMessage", String.format("graphql is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (AcceptException ex) {
            logService.erorLog(logger,"service","saveIncidentGuidance","fail to save saveIncidentGuidance",ex);
            return new DataVO<>(new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL));
        }
    }




}
