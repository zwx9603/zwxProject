package com.dscomm.iecs.accept.restful;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * accept restful query  接口
 */
@Path("iecs/v1.0/query/shanghai")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcceptQuerySHResource {

    private static final Logger logger = LoggerFactory.getLogger(AcceptQuerySHResource.class);
    private LogService logService;
    private Environment env;
    private SessionDataStore sessionDataStore;
    private ServletService servletService;
    private IncidentService incidentService;
    private AttachmentService attachmentService;
    private DocumentService documentService;
    private InstructionService instructionService;
    private NewsCircularService newsCircularService;
    private VehicleService vehicleService;
    private LibsService libsService;
    private LocaleService localeService;
    private HandleService handleService;
    private OrganizationService organizationService;
    private WeatherService weatherService;

    private ParticipantFeedbackService participantFeedbackService;
    private FireSafetyMonitoringService fireSafetyMonitoringService;
    private IncidentInfoService incidentInfoService;
    private HandoverRecordSerivce handoverRecordSerivce;
    private ReinforcementService reinforcementService;
    private DocumentServiceSH documentServiceSH;


    @Autowired
    public AcceptQuerySHResource(LogService logService, Environment env, SessionDataStore sessionDataStore, ServletService servletService,
                                 IncidentService incidentService,
                                 AttachmentService attachmentService,
                                 DocumentService documentService, InstructionService instructionService,
                                 NewsCircularService newsCircularService,
                                 VehicleService vehicleService,
                                 LibsService libsService, LocaleService localeService,
                                 HandleService handleService, OrganizationService organizationService,
                                 WeatherService weatherService, ParticipantFeedbackService participantFeedbackService,
                                 FireSafetyMonitoringService fireSafetyMonitoringService,
                                 OrgRelationshipService orgRelationshipService, IncidentInfoService incidentInfoService,
                                 HandoverRecordSerivce handoverRecordSerivce, ReinforcementService reinforcementService,
                                 DocumentServiceSH documentServiceSH


    ) {
        this.logService = logService;
        this.env = env;
        this.sessionDataStore = sessionDataStore;
        this.servletService = servletService;
        this.incidentService = incidentService;
        this.attachmentService = attachmentService;
        this.documentService = documentService;
        this.instructionService = instructionService;
        this.newsCircularService = newsCircularService;
        this.vehicleService = vehicleService;
        this.libsService = libsService;
        this.localeService = localeService;
        this.handleService = handleService;
        this.organizationService = organizationService;
        this.weatherService = weatherService;
        this.participantFeedbackService = participantFeedbackService;
        this.fireSafetyMonitoringService = fireSafetyMonitoringService;
        this.incidentInfoService = incidentInfoService;
        this.handoverRecordSerivce = handoverRecordSerivce;
        this.reinforcementService = reinforcementService;
        this.documentServiceSH = documentServiceSH;
    }

    /**
     * 查询文书信息 （根据警情id获得文书信息）
     *
     * @param incidentId 上下文环境变量
     * @return 返回结果
     */
    @Path("findDocumentSH")
    @POST
    public DataVO<List<DocumentBeanSH>> findDocument(@QueryParam("incidentId") String incidentId, @QueryParam("organizationId") String organizationId,
                                                   @QueryParam("type") String type) {
        logService.infoLog(logger, "restful", "findDocument", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<DocumentBeanSH> res = documentServiceSH.findDocumentSH(incidentId, organizationId, type);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findDocument", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }




}
