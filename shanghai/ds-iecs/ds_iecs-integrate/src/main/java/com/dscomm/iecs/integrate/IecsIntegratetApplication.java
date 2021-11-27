package com.dscomm.iecs.integrate;

import com.dscomm.iecs.integrate.config.IecsIntegrateConfig;
import org.mx.service.SpringBootUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述: 整合资源 模块服务启动类(jar方式运行)
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:21
 */
@Configuration
@Import({
        IecsIntegrateConfig.class
})
public class IecsIntegratetApplication {
    private static final Logger logger = LoggerFactory.getLogger(IecsIntegratetApplication.class);
    private static SpringBootUtils utils = new SpringBootUtils();

    public static void main(String[] args) {
        utils.startApplication(IecsIntegratetApplication.class, args);
        logger.info("the server of iecs accept is started.");
    }

    public static void stop() {
        utils.stopApplication();
        logger.info("the server of iecs accept is stopped.");
    }

}
