package com.dscomm.iecs.accept.restful;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.firetypebean.FireControlVoiceReturnBean;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.agent.service.AuthenticateService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;


/**
 *  accept restful mutation 接口
 */
@Path("iecs/v1.0/mutation/shanghai")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcceptMutationSHResource {

    private static final Logger logger = LoggerFactory.getLogger(AcceptMutationSHResource.class);
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

    private DocumentServiceSH documentServiceSH;
    private FireControlVoiceService fireControlVoiceService;



    @Autowired
    public AcceptMutationSHResource(LogService logService  , SessionDataStore sessionDataStore , ServletService servletService ,
                                    HandleService handleService , ReinforcementService reinforcementService ,
                                    NewsCircularService newsCircularService , AuthenticateService authenticateService  ,
                                    UserService userService , LocaleService localeService, IncidentService incidentService ,
                                    VehicleStatusChangeService vehicleStatusChangeService,
                                    FireSafetyMonitoringService fireSafetyMonitoringService ,
                                    ParticipantFeedbackService participantFeedbackService,
                                    HandleMiniatureStationService handleMiniatureStationService,
                                    HandoverRecordSerivce handoverRecordSerivce, DocumentServiceSH documentServiceSH, FireControlVoiceService fireControlVoiceService

    ) {
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
        this.documentServiceSH = documentServiceSH;
        this.fireControlVoiceService = fireControlVoiceService;

    }

    /**
     * 警情引导保存文书
     * @param headers
     * @param queryBean
     * @return
     */
    @Path("saveIncidentGuidance")
    @POST
    public DataVO<DocumentBeanSH> saveIncidentGuidance(@Context HttpHeaders headers, IncidentGuidanceInputInfo  queryBean) {
        logService.infoLog(logger, "graphql", "sendRespProcedureMessage", "graphql is started...");
        Long start = System.currentTimeMillis();

        if (queryBean == null){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //逻辑
        DocumentBeanSH res =  documentServiceSH.saveIncidentGuidance(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "sendRespProcedureMessage", String.format("graphql is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }

    /**800M接口录音记录获取 测试接口*/
    @Path("fireControlVoice")
    @GET
    public DataVO<FireControlVoiceReturnBean> fireControlVoice(@Context HttpHeaders headers, @QueryParam("token") String token){
        logService.infoLog(logger, "restful", "saveHandoverRecord", "restful is started...");
        Long start = System.currentTimeMillis();

        FireControlVoiceReturnBean res = fireControlVoiceService.getFireControlVoice(token);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "saveHandoverRecord", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }




}
