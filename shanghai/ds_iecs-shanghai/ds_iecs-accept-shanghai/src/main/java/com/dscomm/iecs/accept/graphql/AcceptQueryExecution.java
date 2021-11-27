package com.dscomm.iecs.accept.graphql;


import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.base.service.log.LogService;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.util.Strings;
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
@Component("acceptQueryExecution")
public class AcceptQueryExecution implements GraphQLAnnotationExecution {
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
    private DocumentServiceSH documentServiceSH;

    @Override
    public String getTypeName() {
        return "Query";
    }

    @Autowired


    public AcceptQueryExecution(LogService logService, Environment env,

                                AssistanceInfoService assistanceInfoService,
                                GradeService gradeService,VehicleNumService vehicleNumService,
                                VehicleDispatchingService vehicleDispatchingService,
                                FindVehicleGradeService findVehicleGradeService,
                                SendMessageService sendMessageService,FunctionInfoService functionInfoService,
                                IncidentStatesService incidentStatesService,DocumentServiceSH documentServiceSH
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
        this.documentServiceSH = documentServiceSH;
    }

    /**
     * graphql测试
     *
     * @param env 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("testQuery")
    public Boolean testQuery(DataFetchingEnvironment env) {


        return true;
    }



    /**
     * 获取援助信息
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findAssistanceInfoSH")
    public AssistanceBean findAssistanceInfoSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAssistanceInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        //获取输入的参数
        String zgdwdmId = environment.getArgument("id");
        if (Strings.isBlank( zgdwdmId)){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        AssistanceBean res = assistanceInfoService.queryInfo(zgdwdmId);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAssistanceInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

    /**
     * 根据参数案件类型、案件等级 、处置对象查询
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findGradeInfoByParamSH")
    public List<GradeBean> findGradeInfoByParamSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findGradeInfoByParam", "graphql is started...");
        Long start = System.currentTimeMillis();
        //获取输入的参数czdx、ajdj、ajlx
        String czdx = environment.getArgument("czdx");
        String ajdj = environment.getArgument("ajdj");
        String ajlx = environment.getArgument("ajlx");

        if (Strings.isBlank(czdx) && Strings.isBlank(czdx) && Strings.isBlank(czdx)){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<GradeBean> res = gradeService.queryInfo(czdx,ajdj,ajlx);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findGradeInfoByParam", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 查询应派车辆、已派车辆、本次调派车辆的信息
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findVehicleNumInfoSH")
    public List<VehicleNumBean> findVehicleNumInfoSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleNumInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        //获取输入的参数czdx、ajdj、ajlx
        String czdx = environment.getArgument("czdx");
        String ajdj = environment.getArgument("ajdj");
        String ajlx = environment.getArgument("ajlx");
        String id = environment.getArgument("id");
        if (Strings.isBlank(czdx) && Strings.isBlank(czdx) && Strings.isBlank(czdx) && Strings.isBlank(id)){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<VehicleNumBean> res = vehicleNumService.findVehicleNumInfo(id,czdx,ajdj,ajlx);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleNumInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /*
     * 查询主管中队即附近10个中队 驻防点 到火灾点的距离
     * */
    @GraphQLFieldAnnotation("findFireDzInfoSH")
    public Map<String,List<FireDzBean>>  findFireDzInfoSH (DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findFireDzInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<FireDzInputBean> input = environment.getArgument("inputInfo");
//        Map<String, Object> input = environment.getArgument("inputInfo");
//        FireDzInputBean queryBean = GraphQLUtils.parse(input, FireDzInputBean.class);
        //执行逻辑处理
        Map<String,List<FireDzBean>> fireDzBeans = vehicleDispatchingService.queryJL(input);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findFireDzInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return fireDzBeans;
    }

    /*
     * 根据案件信息做等级调派派车
     * */
    @GraphQLFieldAnnotation("findVehicleGradeSH")
    public void findVehicleGradeSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleGrade", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentgradeputInfo parse = GraphQLUtils.parse(input, IncidentgradeputInfo.class);
        findVehicleGradeService.findVehicleGrade(parse);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleGrade", String.format("graphql is finished,execute time is :%sms", end - start));
        return ;
    }

    /*
     * 发送短信配置列表全查询
     * */
    @GraphQLFieldAnnotation("findQueryAllInfoSH")
    public List<SendMessageBean> findQueryAllInfoSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findQueryAllInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        List<SendMessageBean> list = sendMessageService.queryAll();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findQueryAllInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 发送短信配置列表根据被发送人姓名或机构名称做条件查询
     * */
    @GraphQLFieldAnnotation("findQueryAllInfoByInfoSH")
    public List<SendMessageBean> findQueryAllInfoByInfoSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findQueryAllInfoByInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        String ldmc = environment.getArgument("ldmc");
        String ssjgbh = environment.getArgument("ssjgbh");
        List<SendMessageBean> list = sendMessageService.queryByInfo(ldmc,ssjgbh);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findQueryAllInfoByInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 对功能信息表做全查询
     * */
    @GraphQLFieldAnnotation("findFunctionInfoSH")
    public List<FunctionInfoBean> findFunctionInfoSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findFunctionInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        List<FunctionInfoBean> list = functionInfoService.queryInfo();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findFunctionInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 查询案件的所有状态
     * */
    @GraphQLFieldAnnotation("findIncidentStatesInfoSH")
    public List<IncidentStatesBean> findIncidentStatesInfoSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentStatesInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        List<IncidentStatesBean> list = incidentStatesService.queryInfo();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentStatesInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 根据条件查询案件的状态
     * */
    @GraphQLFieldAnnotation("findIncidentStatesInfoByInfoSH")
    public List<IncidentStatesBean> findIncidentStatesInfoByInfoSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentStatesInfoByInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        String ajid = environment.getArgument("ajid");
        List<IncidentStatesBean> list = incidentStatesService.queryInfoByInfo(ajid);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentStatesInfoByInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 查询主管中队即附近10个中队 驻防点 到火灾点的距离
     * */
    @GraphQLFieldAnnotation("findFireDzInfo")
    public Map<String,List<FireDzBean>>  findFireDzInfo (DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findFireDzInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<FireDzInputBean> input = environment.getArgument("inputInfo");
//        Map<String, Object> input = environment.getArgument("inputInfo");
//        FireDzInputBean queryBean = GraphQLUtils.parse(input, FireDzInputBean.class);
        //执行逻辑处理
        Map<String,List<FireDzBean>> fireDzBeans = vehicleDispatchingService.queryJL(input);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findFireDzInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return fireDzBeans;
    }

    /*
     * 根据案件信息做等级调派派车
     * */
    @GraphQLFieldAnnotation("findVehicleGrade")
    public void findVehicleGrade(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleGrade", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentgradeputInfo parse = GraphQLUtils.parse(input, IncidentgradeputInfo.class);
        findVehicleGradeService.findVehicleGrade(parse);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleGrade", String.format("graphql is finished,execute time is :%sms", end - start));
        return ;
    }

    /*
     * 发送短信配置列表全查询
     * */
    @GraphQLFieldAnnotation("findQueryAllInfo")
    public List<SendMessageBean> findQueryAllInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findQueryAllInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        List<SendMessageBean> list = sendMessageService.queryAll();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findQueryAllInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 发送短信配置列表根据被发送人姓名或机构名称做条件查询
     * */
    @GraphQLFieldAnnotation("findQueryAllInfoByInfo")
    public List<SendMessageBean> findQueryAllInfoByInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findQueryAllInfoByInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        String ldmc = environment.getArgument("ldmc");
        String ssjgbh = environment.getArgument("ssjgbh");
        List<SendMessageBean> list = sendMessageService.queryByInfo(ldmc,ssjgbh);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findQueryAllInfoByInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 对功能信息表做全查询
     * */
    @GraphQLFieldAnnotation("findFunctionInfo")
    public List<FunctionInfoBean> findFunctionInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findFunctionInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        List<FunctionInfoBean> list = functionInfoService.queryInfo();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findFunctionInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 查询案件的所有状态
     * */
    @GraphQLFieldAnnotation("findIncidentStatesInfo")
    public List<IncidentStatesBean> findIncidentStatesInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentStatesInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        List<IncidentStatesBean> list = incidentStatesService.queryInfo();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentStatesInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /*
     * 根据条件查询案件的状态
     * */
    @GraphQLFieldAnnotation("findIncidentStatesInfoByInfo")
    public List<IncidentStatesBean> findIncidentStatesInfoByInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentStatesInfoByInfo", "graphql is started...");
        Long start = System.currentTimeMillis();
        String ajid = environment.getArgument("ajid");
        List<IncidentStatesBean> list = incidentStatesService.queryInfoByInfo(ajid);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentStatesInfoByInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /**
     * 4.6 查询文书信息 （根据警情id获得文书信息）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDocumentSH")
    public List<DocumentBeanSH> findDocumentSH(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDocument", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        String type = environment.getArgument("type");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<DocumentBeanSH> res = documentServiceSH.findDocumentSH(incidentId, organizationId, type);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDocument", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

}
