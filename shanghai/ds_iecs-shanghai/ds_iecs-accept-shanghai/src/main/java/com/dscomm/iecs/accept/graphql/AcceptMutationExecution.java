package com.dscomm.iecs.accept.graphql;


import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.base.service.log.LogService;
import graphql.schema.DataFetchingEnvironment;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.mx.service.rest.graphql.GraphQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * 描述:接警受理模块graphql查询类执行器
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:47
 */
@Component("acceptMutationExecution")
public class AcceptMutationExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(AcceptQueryExecution.class);
    private LogService logService;
    private Environment env;

    private AssistanceInfoService assistanceInfoService;
    private GradeService gradeService;
    private VehicleNumService vehicleNumService;
    private VehicleDispatchingService vehicleDispatchingService;
    private FindVehicleGradeService findVehicleGradeService;
    private SendMessageService sendMessageService;
    private FunctionInfoService functionInfoService;
    private IncidentStatesService incidentStatesService;
    private OrganizationInfoService organizationInfoService;
    private DocumentServiceSH documentServiceSH;


    @Override
    public String getTypeName() {
        return "Query";
    }

    @Autowired
    public AcceptMutationExecution(LogService logService, Environment env,

                                AssistanceInfoService assistanceInfoService,
                                GradeService gradeService,VehicleNumService vehicleNumService,
                                VehicleDispatchingService vehicleDispatchingService,
                                FindVehicleGradeService findVehicleGradeService,
                                SendMessageService sendMessageService,FunctionInfoService functionInfoService,
                                IncidentStatesService incidentStatesService,OrganizationInfoService organizationInfoService,
                                   DocumentServiceSH documentServiceSH
    ) {

        this.logService = logService;
        this.env = env ;


        this.assistanceInfoService = assistanceInfoService;
        this.gradeService = gradeService;
        this.vehicleNumService = vehicleNumService;
        this.vehicleDispatchingService = vehicleDispatchingService;
        this.findVehicleGradeService = findVehicleGradeService;
        this.sendMessageService = sendMessageService;
        this.functionInfoService = functionInfoService;
        this.incidentStatesService = incidentStatesService;
        this.organizationInfoService = organizationInfoService;
        this.documentServiceSH = documentServiceSH;
    }

    /**
     * graphql测试
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("testMutation")
    public Boolean testMutation(DataFetchingEnvironment environment) {
        return true;
    }


    /**
     * 保存友援队到里火灾最近的消防队的距离
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveAssistanceInfo")
    public Boolean saveAssistanceInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveAssistanceInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        AssistanceInputInfo queryBean = GraphQLUtils.parse(input, AssistanceInputInfo.class);

        //执行逻辑处理
        Boolean res = assistanceInfoService.saveAssistanceInfo(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveAssistanceInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存发送短信配置列表的信息
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("savaSendMessageInfo")
    public Boolean savaSendMessageInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "savaSendMessageInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        SendMessageInputInfo queryBean = GraphQLUtils.parse(input, SendMessageInputInfo.class);

        //执行逻辑处理
        Boolean res = sendMessageService.addInfo(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "savaSendMessageInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据人员id做批量删除
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("deleteInfoById")
    public Boolean deleteInfoById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "deleteInfoById", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        SendMessageInputInfo queryBean = GraphQLUtils.parse(input, SendMessageInputInfo.class);

        //执行逻辑处理
        Boolean res = sendMessageService.addInfo(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "deleteInfoById", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据人员id和人员所属机构批量修改手机号、是否发送信息
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("updateByInfo")
    public Boolean updateByInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateByInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        SendMessageInputInfo queryBean = GraphQLUtils.parse(input, SendMessageInputInfo.class);

        //执行逻辑处理
        Boolean aBoolean = sendMessageService.updateInfo(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateByInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return aBoolean;
    }

    /**
     * 修改消防力量机构信息
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("updateOrganizationInfo")
    public Boolean updateOrganizationInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateOrganizationInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        OrganizationInputInfo parse = GraphQLUtils.parse(input, OrganizationInputInfo.class);

        //执行逻辑处理
        Boolean aBoolean = organizationInfoService.updateOrganizationInfo(parse);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateOrganizationInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return aBoolean;
    }

    /**
     * 修改消防力量车辆信息
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("updateVehicleInfo")
    public Boolean updateVehicleInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateVehicleInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        VehiclexxInputInfo parse = GraphQLUtils.parse(input, VehiclexxInputInfo.class);

        //执行逻辑处理
        Boolean aBoolean = organizationInfoService.updateVehicleInfo(parse);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateVehicleInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return aBoolean;
    }

    /**
     * 保存消防力量车辆信息
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveVehicleInfo")
    public Boolean saveVehicleInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveVehicleInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        VehiclexxInputInfo parse = GraphQLUtils.parse(input, VehiclexxInputInfo.class);

        //执行逻辑处理
        Boolean aBoolean = organizationInfoService.saveVehicleInfo(parse);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveVehicleInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return aBoolean;
    }

    /**
     * 保存车辆功能的信息
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveFunctionInfo")
    public Boolean saveFunctionInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveFunctionInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        FunctionInputInfo parse = GraphQLUtils.parse(input, FunctionInputInfo.class);
        //执行逻辑处理
        Boolean aBoolean = functionInfoService.saveFunctionInfo(parse);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveFunctionInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return aBoolean;
    }

    /**
     * 保存警情引导文书
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveIncidentGuidance")
    public DocumentBeanSH saveIncidentGuidance (DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveIncidentGuidance", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> inputInfo = environment.getArgument("inputInfo");
        IncidentGuidanceInputInfo queryBean = GraphQLUtils.parse(inputInfo, IncidentGuidanceInputInfo.class);

        //逻辑
        DocumentBeanSH res =  documentServiceSH.saveIncidentGuidance(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncidentGuidance", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存修改警情文书
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveDocumentSH")
    public DocumentBeanSH saveDocument(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveDocument", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        DocumentSaveInputInfoSH queryBean = GraphQLUtils.parse(info, DocumentSaveInputInfoSH.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        queryBean.setWhetherUpdate( 1) ; //是否可以修改 0 不可以修改 1 可以修改 默认不可以修改
        DocumentBeanSH res = documentServiceSH.saveDocumentSH(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveDocument", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }




}
