package com.dscomm.iecs.keydata.graphql;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.exception.UserInterfaceKeydataException;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.inputbean.KeyDataChangeRecordSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.typebean.AuditLogBean;
import com.dscomm.iecs.keydata.graphql.typebean.KeyDataChangeRecordBean;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.KeyDataChangeRecordService;
import graphql.schema.DataFetchingEnvironment;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.mx.service.rest.graphql.GraphQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:敏感数据变更graphql增删改操作执行类
 *
 * @author YangFuxi
 * Date Time 2019/7/21 23:12
 */
@Component("keydataMutationExecution")
public class KeydataMutationExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(KeydataMutationExecution.class);
    private LogService logService;
    private AuditLogService auditLogService;
    private KeyDataChangeRecordService keyDataChangeRecordService;

    @Autowired
    public KeydataMutationExecution(LogService logService, AuditLogService auditLogService,
                                    KeyDataChangeRecordService keyDataChangeRecordService) {
        this.logService = logService;
        this.auditLogService = auditLogService;
        this.keyDataChangeRecordService = keyDataChangeRecordService;
    }

    @Override
    public String getTypeName() {
        return "Mutation";
    }

    /**
     * 保存审计日志
     *
     * @param env 上下文环境变量
     * @return 保存成功的审计日志BO
     */
    @GraphQLFieldAnnotation("saveAuditLog")
    public AuditLogBean saveAuditLog(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "saveAuditLog", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> info = env.getArgument("auditLogInput");
        AuditLogSaveInputInfo queryBean = GraphQLUtils.parse(info, AuditLogSaveInputInfo.class);
        if( null == queryBean){
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.DATA_NULL);
        }
        AuditLogBean res = auditLogService.saveAuditLog(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveAuditLog", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存关键数据变更记录
     *
     * @param env 上下文环境变量
     * @return 保存成功的关键数据变更记录BO
     */
    @GraphQLFieldAnnotation("saveKeyDataChangeRecord")
    public KeyDataChangeRecordBean saveKeyDataChangeRecord(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "saveKeyDataChangeRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        KeyDataChangeRecordBean res = new KeyDataChangeRecordBean();

        Map<String, Object> info = env.getArgument("keyDataChangeRecordInput");
        KeyDataChangeRecordSaveInputInfo queryBean  = GraphQLUtils.parse(info, KeyDataChangeRecordSaveInputInfo.class);

        if( null == queryBean){
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.DATA_NULL);
        }

        List<KeyDataChangeRecordSaveInputInfo> keyDataChangeRecordSaveInputInfoList =  new ArrayList<>() ;
        keyDataChangeRecordSaveInputInfoList.add(  queryBean ) ;

        List<KeyDataChangeRecordBean> keyDataChangeRecordBeanList  = keyDataChangeRecordService.saveKeyDataChangeRecord( keyDataChangeRecordSaveInputInfoList );
        if( null != keyDataChangeRecordBeanList && keyDataChangeRecordBeanList.size() > 0   ){
            res = keyDataChangeRecordBeanList.get(0) ;
        }


        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveKeyDataChangeRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res ;
    }

}
