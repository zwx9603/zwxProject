package com.dscomm.iecs.base.service.log;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述:log日志输出管理类
 *
 * @author YangFuxi
 * Date Time 2019/8/19 20:54
 */
@Component
public class LogService {
    @Value("${system.version}")
    private String version;

    /**
     * info级别日志输出
     *
     * @param logger     日之类
     * @param layer      层级名称，graphql，service..
     * @param methodName 方法名
     * @param content    日志内容
     */
    public void infoLog(Logger logger, String layer, String methodName, String content) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format("version:%s,laver:%s,method:%s,describe:%s", version, layer, methodName, content));
        }
    }

    /**
     * error 级别  正常日志输出
     *
     * @param logger     日之类
     * @param layer      层级
     * @param methodName 方法名
     * @param content    日志内容
     */
    public void writeLog(Logger logger, String layer, String methodName, String content ) {
        if (logger.isErrorEnabled()) {
            logger.error( String.format("version:%s,layer:%s,method:%s,describe:%s", version, layer, methodName, content)  );
        }
    }


    /**
     * error 级别日志输出 ( 异常输出 )
     *
     * @param logger     日之类
     * @param layer      层级
     * @param methodName 方法名
     * @param content    日志内容
     * @param ex         异常信息
     */
    public void erorLog(Logger logger, String layer, String methodName, String content, Exception ex) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format("version:%s,layer:%s,method:%s,describe:%s", version, layer, methodName, content), ex);
        }
    }


}
