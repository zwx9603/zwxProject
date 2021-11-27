package com.dscomm.iecs.notify;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.dscomm.iecs.notify.config.IecsNotifyConfig;
import org.mx.service.SpringBootUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述: notify 模块程序启动类
 *
 * @author YangFuxi
 * Date Time 2019/7/22 1:53
 */
@SpringBootApplication
@Configuration
@EnableApolloConfig
@Import({
        IecsNotifyConfig.class
})
public class IecsNotifyApplication {
    private static final Logger logger = LoggerFactory.getLogger(IecsNotifyApplication.class);
    private static SpringBootUtils utils = new SpringBootUtils();

    /**
     * 服务启动方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        utils.startApplication(IecsNotifyApplication.class, args);
        if (logger.isInfoEnabled()) {
            logger.info("the ds_iecs-garage server is started!");
        }
    }

    /**
     * 服务停止方法
     */
    public static void stopServer() {
        utils.stopApplication();
        if (logger.isInfoEnabled()) {
            logger.info("the ds_iecs-garage server is started!");
        }
    }
}
