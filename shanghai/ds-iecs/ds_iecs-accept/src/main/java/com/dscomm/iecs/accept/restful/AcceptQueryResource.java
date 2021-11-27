package com.dscomm.iecs.accept.restful;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.accept.service.bean.*;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.VehiclePersonsBean;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.error.UserInterfaceException;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * accept restful query  接口
 */
@Path("iecs/v1.0/query")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcceptQueryResource {

    private static final Logger logger = LoggerFactory.getLogger(AcceptQueryResource.class);
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
    private SmartRecommendationService smartRecommendationService;


    @Autowired
    public AcceptQueryResource(LogService logService, Environment env, SessionDataStore sessionDataStore, ServletService servletService,
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


                               SmartRecommendationService smartRecommendationService) {
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
        this.smartRecommendationService = smartRecommendationService;
    }


    /**
     * 根据车辆id获得 车辆参与案件信息 以及 车辆状态
     *
     * @param vehicleId 上下文环境变量
     * @return 返回结果
     */
    @Path("findVehicleInIncidentAndStatus")
    @POST
    public DataVO<VehicleIncidentBean> findVehicleInIncidentAndStatus(@Context HttpHeaders headers, @QueryParam("vehicleId") String vehicleId) {
        logService.infoLog(logger, "restful", "findVehicleInIncidentAndStatus", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(vehicleId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        EquipmentVehicleBean vehicleBean = vehicleService.findVehicle(vehicleId);
        VehicleIncidentBean res = new VehicleIncidentBean();
        res.setVehicleId(vehicleBean.getId());
        res.setVehicleStatusCode(vehicleBean.getVehicleStatusCode());
        res.setVehicleStatusName(vehicleBean.getVehicleStatusName());
        if (Strings.isNotBlank(vehicleBean.getIncidentNumber())) {
            res.setIncidentId(vehicleBean.getIncidentNumber());
            //车辆参与案件 获得车辆在案件标识状态
            HandleOrganizationVehicleBean handleOrganizationVehicleBean = handleService.findHandleOrganizationVehicle(res.getIncidentId(), res.getVehicleId());
            res.setVehicleIdentification(handleOrganizationVehicleBean.getVehicleIdentification());
        } else {
            res.setIncidentId("");
        }

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findVehicleInIncidentAndStatus", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 2.4 根据接收对象id、警情地址模糊匹配、警情类型、警情等级查询未结案警情信息(分页)
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("findIncidentsReceiverObjectIdUnfinished")
    @POST
    public DataVO<PaginationBean<IncidentBrieflyBean>> findIncidentsReceiverObjectIdUnfinished(@Context HttpHeaders headers, IncidentQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findIncidentsReceiverObjectIdUnfinished", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getReceiverObjectId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentsReceiverObjectIdUnfinished(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findIncidentsReceiverObjectIdUnfinished", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 2.4 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级查询未结案警情信息(分页)
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("findIncidentsUnfinished")
    @POST
    public DataVO<PaginationBean<IncidentBean>> findIncidentsUnfinished(@Context HttpHeaders headers, IncidentQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findIncidentsUnfinished", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBean> res = incidentService.findZJIncidents(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findIncidentsUnfinished", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 2.5根据固定时间段 辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态查询警情信息(分页)  （ 昨天10点- 今日10点 ）
     * 昨日 10点 今日 10 点
     *
     * @param queryBean 查询条件
     * @return 返回结果
     */
    @Path("findIncidentFixedTimeCondition")
    @POST
    public DataVO<PaginationBean<IncidentBrieflyBean>> findIncidentFixedTimeCondition(@Context HttpHeaders headers, IncidentQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findIncidentFixedTimeCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null) {
            queryBean = new IncidentQueryInputInfo();
        }
        //设置固定时间信息
        Long currentTime = servletService.getSystemTime();
        //设置今日10时间点
        Calendar cal = DateHandleUtils.buildCalendar(env.getProperty("timeZone"));
        cal.setTime(new Date(currentTime));
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Long startTime = cal.getTimeInMillis();
        //设置 昨日10 时间点
        Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, -1).getTime();
        queryBean.setStartTime(startTime);
        queryBean.setEndTime(endTime);


        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentsCondition(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findIncidentFixedTimeCondition", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 2.5 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段查询警情信息(分页)
     *
     * @param queryBean 查询条件
     * @return 返回结果
     */
    @Path("findIncidentCondition")
    @POST
    public DataVO<PaginationBean<IncidentBrieflyBean>> findIncidentCondition(@Context HttpHeaders headers, IncidentQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findIncidentCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentsCondition(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findIncidentCondition", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 根据警情id 获得 警情详情
     *
     * @return 返回更新结果
     */
    @Path("findIncident")
    @POST
    public DataVO<IncidentBean> findIncident(@Context HttpHeaders headers, @QueryParam("incidentId") String incidentId) {
        logService.infoLog(logger, "restful", "findIncident", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        IncidentBean res = incidentService.findIncident(incidentId, true);
        //车辆添加 案件参与车辆 添加案件出动车辆查询事所在位置
        List<HandleOrganizationVehicleBean> handleOrganizationVehicleList = res.getHandleOrganizationVehicleList();
        if (handleOrganizationVehicleList != null && handleOrganizationVehicleList.size() > 0) {
            for (HandleOrganizationVehicleBean handleOrganizationVehicleBean : handleOrganizationVehicleList) {
                String locationNumber = handleOrganizationVehicleBean.getLocationNumber();
                if (Strings.isNotBlank(locationNumber)) {
                    DeviceLocationBean deviceLocation = libsService.findCurrentDeviceLocation(locationNumber);
                    handleOrganizationVehicleBean.setDeviceLocationBean(deviceLocation);
                }

            }
        }

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findIncident", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 根据案件id获取案件附件
     *
     * @return 返回结果
     */
    @Path("findAttachment")
    @POST
    public DataVO<List<AttachmentBean>> findAttachment(@Context HttpHeaders headers, @QueryParam("incidentId") String incidentId, @QueryParam("relationId") String relationId) {
        logService.infoLog(logger, "restful", "findAttachment", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(incidentId) && Strings.isBlank(relationId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<AttachmentBean> res = attachmentService.findAttachment(incidentId, relationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findAttachment", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 根据案件id必填 其他条件 查询指令记录信息
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("findInstructionRecordCondition")
    @POST
    public DataVO<List<InstructionRecordBean>> findInstructionRecordCondition(@Context HttpHeaders headers, InstructionRecordQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findInstructionRecordCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<InstructionRecordBean> res = instructionService.findInstructionRecordCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findInstructionRecordCondition", String.format("restful is finished,execute time is :%sms", end - start));


        return new DataVO<>(res);

    }


    /**
     * 4.4 根据警情id 获得现场信息
     *
     * @param incidentId 上下文环境变量
     * @return 返回结果
     */
    @Path("findLocale")
    @POST
    public DataVO<List<LocaleBean>> findLocale(@QueryParam("incidentId") String incidentId) {
        logService.infoLog(logger, "restful", "findLocale", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<LocaleBean> res = localeService.findLocale(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findLocale", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 4.4 根据警情id 获得卷宗信息
     *
     * @param incidentId 上下文环境变量
     * @return 返回结果
     */
    @Path("findIncidentDossier")
    @POST
    public DataVO<IncidentDossierBean> findIncidentDossier(@QueryParam("incidentId") String incidentId) {
        logService.infoLog(logger, "restful", "IncidentDossierBean", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        IncidentDossierBean res = incidentInfoService.findIncidentDossier(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "IncidentDossierBean", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 4.4 根据条件 获得现场信息
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("findLocaleCondition")
    @POST
    public DataVO<List<LocaleBean>> findLocaleCondition(LocaleQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findLocaleCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<LocaleBean> res = localeService.findLocaleCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findLocaleCondition", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 查询文书信息 （根据警情id获得文书信息）
     *
     * @param incidentId 上下文环境变量
     * @return 返回结果
     */
    @Path("findDocument")
    @POST
    public DataVO<List<DocumentBean>> findDocument(@QueryParam("incidentId") String incidentId, @QueryParam("organizationId") String organizationId,
                                                   @QueryParam("type") String type) {
        logService.infoLog(logger, "restful", "findDocument", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<DocumentBean> res = documentService.findDocument(incidentId, organizationId, type);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findDocument", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 根据条件 查询消息通知
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("findNewsCircularCondition")
    @POST
    public DataVO<PaginationBean<NewsCircularBean>> findNewsCircularCondition(@Context HttpHeaders headers, NewsCircularQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findNewsCircularCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        PaginationBean<NewsCircularBean> res = newsCircularService.findNewsCircularCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findNewsCircularCondition", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 根据接收对象id 查询接收的消息通知
     *
     * @param queryBean 上下文环境变量
     * @return 返回结果
     */
    @Path("findNewsCircularReceiverCondition")
    @POST
    public DataVO<PaginationBean<NewsCircularReceiverBean>> findNewsCircularReceiverCondition(@Context HttpHeaders headers,
                                                                                              NewsCircularQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findNewsCircularReceiverCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getReceivingObjectId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        PaginationBean<NewsCircularReceiverBean> res = newsCircularService.findNewsCircularReceiverCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findNewsCircularReceiverCondition", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 7.3 根据车辆所在机构的行政区编码 获得天气信息
     *
     * @return 返回结果
     */
    @Path("findWeatherByVehicleId")
    @POST
    public DataVO<WeatherBean> findWeather(@QueryParam("vehicleId") String vehicleId) {
        logService.infoLog(logger, "restful", "findWeatherByVehicleId", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(vehicleId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        WeatherBean res = null;
        //执行逻辑处理
        List<WeatherBean> weatherBeans = new ArrayList<>();
        EquipmentVehicleBean equipmentVehicleBean = vehicleService.findVehicle(vehicleId);
        if (equipmentVehicleBean != null) {
            String organizationId = equipmentVehicleBean.getOrganizationId();
            OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(organizationId);
            if (organizationBean != null) {
                String districtCode = organizationBean.getDistrictCode();
                if (Strings.isNotBlank(districtCode)) {
                    weatherBeans = weatherService.findWeather(districtCode);
                } else {
                    throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
                }
            }
        }

        if (weatherBeans != null && weatherBeans.size() >= 1) {
            res = weatherBeans.get(0);
        }
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findWeatherByVehicleId", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 根据车辆id 获得车辆信息以及位置信息
     *
     * @return 返回更新结果
     */
    @Path("findVehicleDeviceLocation")
    @POST
    public DataVO<List<VehicleDeviceLocationBean>> findVehicleDeviceLocation(@Context HttpHeaders headers, @QueryParam("vehicleIds") String vehicleIdStr) {
        logService.infoLog(logger, "restful", "findIncident", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(vehicleIdStr)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        String[] vehicleIdsj = vehicleIdStr.split(",");
        List<String> vehicleIds = Arrays.asList(vehicleIdsj);
        //执行逻辑处理
        List<VehicleDeviceLocationBean> res = new ArrayList<>();
        List<EquipmentVehicleBean> vehicleBeanList = vehicleService.findVehicleCacheList(vehicleIds);
        //车辆查询事所在位置
        if (vehicleBeanList != null && vehicleBeanList.size() > 0) {
            for (EquipmentVehicleBean vehicleBean : vehicleBeanList) {
                String locationNumber = vehicleBean.getLocationNumber();
                VehicleDeviceLocationBean bean = new VehicleDeviceLocationBean();
                bean.setId(vehicleBean.getId());
                bean.setVehicleId(vehicleBean.getId());
                bean.setOrganizationId(vehicleBean.getOrganizationId());
                bean.setOrganizationName(vehicleBean.getOrganizationName());
                bean.setVehicleTypeCode(vehicleBean.getVehicleTypeCode());
                bean.setVehicleTypeName(vehicleBean.getVehicleTypeName());
                bean.setVehicleStatusCode(vehicleBean.getVehicleStatusCode());
                bean.setVehicleStatusName(vehicleBean.getVehicleStatusName());
                bean.setVehicleCode(vehicleBean.getVehicleCode());
                bean.setVehicleName(vehicleBean.getVehicleName());
                bean.setVehicleShortName(vehicleBean.getVehicleShortName());
                bean.setVehicleLevelCode(vehicleBean.getVehicleLevelCode());
                bean.setVehicleLevelName(vehicleBean.getVehicleLevelName());
                bean.setVehicleNumber(vehicleBean.getVehicleNumber());
                bean.setGpsNumber(vehicleBean.getGpsNumber());
                bean.setLocationNumber(vehicleBean.getLocationNumber());
                bean.setRadioCallSign(vehicleBean.getRadioCallSign());
                if (Strings.isNotBlank(locationNumber)) {
                    DeviceLocationBean deviceLocation = libsService.findCurrentDeviceLocation(locationNumber);
                    bean.setDeviceLocationBean(deviceLocation);
                }
                res.add(bean);
            }
        }

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findIncident", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 根据车辆ids 警情id  获得车载人员（值班 计划 )
     */
    @Path("findVehiclePersons")
    @POST
    public DataVO<List<VehiclePersonsBean>> findVehiclePersons(@Context HttpHeaders headers
            , @QueryParam("incidentId") String incidentId, @QueryParam("vehicleIds") String vehicleIdStr) {
        logService.infoLog(logger, "restful", "findVehiclePersons", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(vehicleIdStr)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        String[] vehicleIdsj = vehicleIdStr.split(",");
        List<String> vehicleIds = Arrays.asList(vehicleIdsj);

        List<VehiclePersonsBean> res = participantFeedbackService.findVehiclePersons(incidentId, vehicleIds);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findVehiclePersons", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 根据车辆ids 警情id  获得车辆人员信息
     */
    @Path("findIncidentParticipant")
    @POST
    public DataVO<List<ParticipantFeedbackBean>> findIncidentParticipant(@Context HttpHeaders headers
            , @QueryParam("incidentId") String incidentId, @QueryParam("vehicleIds") String vehicleIdStr) {
        logService.infoLog(logger, "restful", "findIncidentParticipant", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(vehicleIdStr)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        String[] vehicleIdsj = vehicleIdStr.split(",");
        List<String> vehicleIds = Arrays.asList(vehicleIdsj);

        List<ParticipantFeedbackBean> res = participantFeedbackService.findIncidentParticipant(incidentId, vehicleIds);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findIncidentParticipant", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


    /**
     * 根据车辆id 警情id 获得 火场安全监控
     *
     * @return 返回更新结果
     */
    @Path("findEnterFireSafetyByVehicle")
    @POST
    public DataVO<List<FireSafetyMonitoringBean>> findEnterFireSafetyByVehicle(
            @Context HttpHeaders headers,
            @QueryParam("vehicleId") String vehicleId,
            @QueryParam("incidentId") String incidentId
    ) {
        logService.infoLog(logger, "restful", "findEnterFireSafetyByVehicle", "restful is started...");
        Long start = System.currentTimeMillis();
        //参数判断
        if (Strings.isBlank(vehicleId) && Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<FireSafetyMonitoringBean> res = fireSafetyMonitoringService.findEnterFireSafetyByVehicle(vehicleId, incidentId);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findEnterFireSafetyByVehicle", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }

    /**
     * 查询交接班日志
     *
     * @param
     * @return 返回结果
     */
    @Path("findHandoverRecordList")
    @POST
    public DataVO<PaginationBean<HandoverRecordBean>> findHandoverNewRecordList(HandoverRecordQueryInputInfo queryBean) {
        logService.infoLog(logger, "graphql", "findHandoverRecordList", "graphql is started...");
        Long start = System.currentTimeMillis();
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        PaginationBean<HandoverRecordBean> res = handoverRecordSerivce.findHandoverRecordRestList(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHandoverRecordList", String.format("graphql is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }

    /**
     * 智能推荐警情级别
     *
     * @param
     * @return 返回结果
     */
    @Path("recommendIncidentLevel")
    @POST
    public DataVO<GradeJudgeResultBean> recommendIncidentLevel(GradeJudgeQueryParamBean queryBean) {
        try {
            logService.infoLog(logger, "graphql", "recommendIncidentLevel", "graphql is started...");
            Long start = System.currentTimeMillis();
            //参数判断
            if (queryBean == null) {
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }

            //执行逻辑处理
            GradeJudgeResultBean result = smartRecommendationService.transformGradeJudge(queryBean);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "graphql", "recommendIncidentLevel", String.format("graphql is finished,execute time is :%sms", end - start));

            return new DataVO<>(result);
        } catch (UserInterfaceException ex) {
            logService.erorLog(logger,"rest","recommendIncidentLevel","fail to recommendIncidentLevel",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logService.erorLog(logger,"rest","recommendIncidentLevel","fail to recommendIncidentLevel",ex);
            return new DataVO<>(new AcceptException());
        }

    }

    /**
     * 智能推荐警情调派力量
     *
     * @param
     * @return 返回结果
     */
    @Path("recommendHandleVehicle")
    @POST
    public DataVO<PowerTransferResultBean> recommendHandleVehicle(PowerTransferQueryParam queryBean) {
        try {
            logService.infoLog(logger, "graphql", "recommendHandleVehicle", "graphql is started...");
            Long start = System.currentTimeMillis();
            //参数判断
            if (queryBean == null) {
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }

            //执行逻辑处理
            PowerTransferResultBean result = smartRecommendationService.transformPowerTransfer(queryBean);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "graphql", "recommendHandleVehicle", String.format("graphql is finished,execute time is :%sms", end - start));

            return new DataVO<>(result);
        }  catch (UserInterfaceException ex) {
            logService.erorLog(logger,"rest","recommendHandleVehicle","fail to recommendHandleVehicle",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logService.erorLog(logger,"rest","recommendHandleVehicle","fail to recommendHandleVehicle",ex);
            return new DataVO<>(new AcceptException());
        }

    }

    @Path("recommendHandleVehicleDetail")
    @POST
    public DataVO<SmartRecommendVehicleBean> recommendHandleVehicleDetail(PowerTransferQueryParam queryBean) {
        try {
            logService.infoLog(logger, "graphql", "recommendHandleVehicleDetail", "graphql is started...");
            Long start = System.currentTimeMillis();
            //参数判断
            if (queryBean == null) {
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }

            //执行逻辑处理
            SmartRecommendVehicleBean res = smartRecommendationService.smartRecommendVehicle(queryBean);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "graphql", "recommendHandleVehicleDetail", String.format("graphql is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        }  catch (UserInterfaceException ex) {
            logService.erorLog(logger,"rest","recommendHandleVehicleDetail","fail to recommendHandleVehicleDetail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logService.erorLog(logger,"rest","recommendHandleVehicleDetail","fail to recommendHandleVehicleDetail",ex);
            return new DataVO<>(new AcceptException());
        }

    }

}
