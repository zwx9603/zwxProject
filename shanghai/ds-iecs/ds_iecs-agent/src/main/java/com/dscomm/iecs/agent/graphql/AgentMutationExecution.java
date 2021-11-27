package com.dscomm.iecs.agent.graphql;


import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.graphql.typebean.AuditAgentSleepParamBO;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.base.service.log.LogService;
import graphql.schema.DataFetchingEnvironment;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.mx.service.rest.graphql.GraphQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:坐席管理模块graphql增删改操作执行类
 *
 * @author YangFuxi
 * Date Time 2019/7/21 23:12
 */
@Component("agentMutationExecution")
public class AgentMutationExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(AgentMutationExecution.class);
    private LogService logService;
    private AgentCacheService agentCacheService;
    private AgentService agentService;
    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;

    @Autowired
    public AgentMutationExecution( LogService logService , AgentCacheService agentCacheService,
                                   AgentService agentService) {
        this.logService = logService ;
        this.agentCacheService = agentCacheService;
        this.agentService = agentService;
    }

    @Override
    public String getTypeName() {
        return "Mutation";
    }



    @GraphQLFieldAnnotation("mergeAgentCache")
    public List<AgentBean> mergeAgentCache(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "mergeAgentCache", "graphql is started...");
        Long start = System.currentTimeMillis();
        String operation = env.getArgument("operation");
        Map<String, Object> input = env.getArgument("agentInfo");
        AgentBean bo = GraphQLUtils.parse(input, AgentBean.class);
        Map<String, AgentBean> result = agentCacheService.mergeAgentCache(operation, bo, cacheKeyPrefix, true);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "mergeAgentCache", String.format("graphql is finished,execute time is :%sms", end - start));
        return new ArrayList<>(result.values());
    }

    /**
     * 更改坐席状态
     *
     * @param env 上下文环境变量
     * @return 更改后的座席信息
     */
    @GraphQLFieldAnnotation("changeAgentState")
    public AgentBean changeAgentState(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "changeAgentState", "graphql is started...");
        Long start = System.currentTimeMillis();
        String agentNum = (String) env.getArgument("agentNum");
        Integer agentWorkState = (Integer) env.getArgument("agentState");
        String phone = (String) env.getArgument("phone");
        AgentBean agentBean = agentService.changeAgentState(agentNum, agentWorkState, phone);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "changeAgentState", String.format("graphql is finished,execute time is :%sms", end - start));
        return agentBean;
    }

    /**
     * 更改坐席操作状态
     *
     * @param env 上下文环境变量
     * @return 更改后的座席信息
     */
    @GraphQLFieldAnnotation("changeAgentOperateState")
    public AgentBean changeAgentOperateState(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "changeAgentOperateState", "graphql is started...");
        Long start = System.currentTimeMillis();
        String agentNum = (String) env.getArgument("agentNum");
        Integer agentOperateState = (Integer) env.getArgument("agentOperateState");
        String phone = env.getArgument("phone");
        AgentBean agentBean = agentService.changeAgentOperateState(agentNum, agentOperateState, phone);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "changeAgentOperateState", String.format("graphql is finished,execute time is :%sms", end - start));
        return agentBean;
    }

    /**
     * 更改坐席话务状态
     *
     * @param env 上下文环境变量
     * @return 更改后的座席信息
     */
    @GraphQLFieldAnnotation("changeAgentPhoneState")
    public AgentBean changeAgentPhoneState(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "changeAgentPhoneState", "graphql is started...");
        Long start = System.currentTimeMillis();
        String agentNum = (String) env.getArgument("agentNum");
        Integer agentPhoneState = (Integer) env.getArgument("agentPhoneState");
        String phone = env.getArgument("phone");
        String meetingMark = env.getArgument("meetingMark");
        String remotePhone = env.getArgument("remotePhone");
        AgentBean agentBean = agentService.changeAgentPhoneState(agentNum, agentPhoneState, phone,meetingMark,remotePhone);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "changeAgentPhoneState", String.format("graphql is finished,execute time is :%sms", end - start));
        return agentBean;
    }

    /**
     * 坐席申请休眠
     *
     * @param env 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("applyChangeToSleep")
    public Boolean applyChangeToSleep(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "applyChangeToSleep", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        String reasonCode = env.getArgument("reasonCode");
        String reason = env.getArgument("reason");
        Boolean result = agentService.applyChangeToSleep(reasonCode, reason);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "applyChangeToSleep", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return result;
    }

    /**
     * 审核坐席申请休眠申请
     *
     * @param env 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("auditSleep")
    public Boolean auditSleep(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "auditSleep", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        Map<String, Object> info = env.getArgument("inputInfo");
        AuditAgentSleepParamBO bo = GraphQLUtils.parse(info, AuditAgentSleepParamBO.class);
        Boolean result = agentService.auditSleep(bo);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "auditSleep", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return result;
    }

    /**
     * 坐席休眠/解除休眠
     *
     * @param env 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("changeSleepState")
    public Boolean changeSleepState(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "changeSleepState", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        String operate = env.getArgument("operate");
        String dormancyCode = env.getArgument("dormancyCode");
        Boolean result = agentService.changeSleepState(operate,dormancyCode);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "changeSleepState", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return result;
    }

    /**
     * 坐席(用户)强制退出
     *
     * @param env 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("forceQuit")
    public Boolean forceQuit(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "forceQuit", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        String agentNum = env.getArgument("agentNum");
        String account = env.getArgument("account");
        String userinfoNum = env.getArgument("userinfoNum");

        Boolean  result = agentService.forceQuit(agentNum, account,userinfoNum);

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "forceQuit", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return result;
    }

}
