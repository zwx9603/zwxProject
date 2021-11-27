package com.dscomm.iecs.out;


import com.dscomm.iecs.out.config.IecsOutConfig;
import org.mx.service.SpringBootUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述:警情受理模块服务启动类(jar方式运行)
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:21
 */
@SpringBootApplication
@Configuration

@Import({
        IecsOutConfig.class
})
public class IecsOutApplication {
    private static final Logger logger = LoggerFactory.getLogger(IecsOutApplication.class);
    private static SpringBootUtils utils = new SpringBootUtils();

    public static void main(String[] args) {
        utils.startApplication(IecsOutApplication.class, args);
        logger.info("the server of iecs out is started.");
    }

    public static void stop() {
        utils.stopApplication();
        logger.info("the server of iecs out is stopped.");
    }

}
