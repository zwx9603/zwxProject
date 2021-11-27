package com.dscomm.iecs.accept;

import com.dscomm.iecs.accept.config.IecsAcceptConfig;
import org.mx.service.SpringBootUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述:警情受理模块服务启动类(jar方式运行)
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:21
 */
@Configuration
@Import({
        IecsAcceptConfig.class
})
public class IecsAcceptApplication {
    private static final Logger logger = LoggerFactory.getLogger(IecsAcceptApplication.class);
    private static SpringBootUtils utils = new SpringBootUtils();

    public static void main(String[] args) {
        utils.startApplication(IecsAcceptApplication.class, args);
        logger.info("the server of iecs accept is started.");
    }

    public static void stop() {
        utils.stopApplication();
        logger.info("the server of iecs accept is stopped.");
    }

}
