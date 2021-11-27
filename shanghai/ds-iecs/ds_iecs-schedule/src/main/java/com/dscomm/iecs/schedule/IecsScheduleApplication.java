package com.dscomm.iecs.schedule;

import com.dscomm.iecs.schedule.config.IecsScheduleConfig;
import org.mx.service.SpringBootUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述：
 *
 * @author : john date : 2018/7/15 下午3:51
 */
@SpringBootApplication
@Configuration
@Import({
        IecsScheduleConfig.class,
})
public class IecsScheduleApplication {

    private static final Logger logger = LoggerFactory.getLogger(IecsScheduleApplication.class);
    private static SpringBootUtils utils = new SpringBootUtils();


    public static void main(String[] args) {
        utils.startApplication(IecsScheduleApplication.class, args);

    }

    public static void stop() {
        utils.stopApplication();
        logger.info("the server of iecs schedule is stopped.");
    }
}
