package com.dscomm.iecs.accept.graphql;

import com.dscomm.iecs.base.service.log.LogService;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:图上指挥  模块graphql查询类执行器
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:47
 */
@Component("fireMutationExecution")
public class FireMutationExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(FireMutationExecution.class);
    private LogService logService;


    @Override
    public String getTypeName() {
        return "Mutation";
    }

    @Autowired
    public FireMutationExecution(LogService logService

    ) {
        this.logService = logService;
    }






}
