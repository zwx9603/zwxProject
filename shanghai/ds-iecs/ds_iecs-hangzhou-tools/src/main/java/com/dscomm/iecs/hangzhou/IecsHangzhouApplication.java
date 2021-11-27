package com.dscomm.iecs.hangzhou;

import com.dscomm.iecs.hangzhou.config.IecsHangzhouConfig;
import org.mx.service.SpringBootUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述: util 模块程序启动类
 *
 * @author YangFuxi
 * Date Time 2019/7/22 1:53
 */
@SpringBootApplication
@Configuration
@Import({
        IecsHangzhouConfig.class
})
public class IecsHangzhouApplication {
    private static final Logger logger = LoggerFactory.getLogger(IecsHangzhouApplication.class);
    private static SpringBootUtils utils = new SpringBootUtils();

    /**
     * 服务启动方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        utils.startApplication(IecsHangzhouApplication.class, args);
        if (logger.isInfoEnabled()) {
            logger.info("the ds_iecs-hangzhou tools  server is started!");
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
