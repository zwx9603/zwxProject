package com.dscomm.iecs.agent.graphql;


import com.dscomm.iecs.base.service.log.LogService;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;

    @Autowired
    public AgentMutationExecution( LogService logService) {
        this.logService = logService ;
    }

    @Override
    public String getTypeName() {
        return "Mutation";
    }




}
