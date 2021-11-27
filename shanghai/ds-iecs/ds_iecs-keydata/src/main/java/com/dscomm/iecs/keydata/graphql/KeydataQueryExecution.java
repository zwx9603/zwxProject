package com.dscomm.iecs.keydata.graphql;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.AES256Helper;
import com.dscomm.iecs.keydata.exception.UserInterfaceKeydataException;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.KeyDataChangeRecordService;
import com.dscomm.iecs.keydata.service.ServletService;
import graphql.schema.DataFetchingEnvironment;
import org.mx.StringUtils;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:敏感数据变更模块graphql查询操作执行类
 *
 * @author YangFuxi
 * Date Time 2019/7/21 22:18
 */
@Component("keydataQueryExecution")
public class KeydataQueryExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(KeydataQueryExecution.class);
    private LogService logService;
    private AuditLogService auditLogService;
    private KeyDataChangeRecordService keyDataChangeRecordService;
    private ServletService servletService ;

    @Autowired
    public KeydataQueryExecution(LogService logService, AuditLogService auditLogService, KeyDataChangeRecordService keyDataChangeRecordService ,
                                 ServletService servletService
    ) {
        this.logService = logService;
        this.auditLogService = auditLogService;
        this.keyDataChangeRecordService = keyDataChangeRecordService;
        this.servletService = servletService ;
    }

    @Override
    public String getTypeName() {
        return "Query";
    }


    /**
     * 获得系统时间
     *
     * @param env 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("getSystemTime")
    public Long getSystemTime(DataFetchingEnvironment env) {
        try {
            logService.infoLog(logger, "graphql", "getHexString", "graphql is started...");
            Long start = System.currentTimeMillis();

            Long res = servletService.getSystemTime(   ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "graphql", "getHexString", String.format("graphql is finished,execute time is :%sms", end - start));
            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "graphql", "getHexString", "get HexSring key fail", ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.GET_HEXSRING_KEY_FAIL);
        }
    }



    /**
     * 获得系统时间
     *
     * @param env 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("getSystemTimeType")
    public Long getSystemTimeType(DataFetchingEnvironment env) {
        try {
            logService.infoLog(logger, "graphql", "getHexString", "graphql is started...");
            Long start = System.currentTimeMillis();


            Integer type = (Integer) env.getArgument("type");
            Long res = servletService.getSystemTime( type   ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "graphql", "getHexString", String.format("graphql is finished,execute time is :%sms", end - start));
            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "graphql", "getHexString", "get HexSring key fail", ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.GET_HEXSRING_KEY_FAIL);
        }
    }



    /**
     * 获取加密的key值
     *
     * @param env 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("getHexString")
    public String getHexString(DataFetchingEnvironment env) {
        try {
            logService.infoLog(logger, "graphql", "getHexString", "graphql is started...");
            Long start = System.currentTimeMillis();
            String key = StringUtils.byte2HexString(AES256Helper.generateKeyByte());
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "graphql", "getHexString", String.format("graphql is finished,execute time is :%sms", end - start));
            return key;
        } catch (Exception ex) {
            logService.erorLog(logger, "graphql", "getHexString", "get HexSring key fail", ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.GET_HEXSRING_KEY_FAIL);
        }
    }
}
