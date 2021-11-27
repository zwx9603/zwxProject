package com.dscomm.iecs.agent;

import com.dscomm.iecs.agent.config.IecsAgentConfig;
import org.mx.service.SpringBootUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述:接警受理模块程序启动类
 *
 * @author YangFuxi
 * Date Time 2019/7/22 1:53
 */
@Configuration
@Import({
        IecsAgentConfig.class
})
public class AgentApplication {
    private static final Logger logger = LoggerFactory.getLogger(AgentApplication.class);
    private static SpringBootUtils utils = new SpringBootUtils();

    /**
     * 服务启动方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        utils.startApplication(AgentApplication.class, args);
        if (logger.isInfoEnabled()) {
            logger.info("the es_ecs-agent server is started!");
        }
    }

    /**
     * 服务停止方法
     */
    public static void stopServer() {
        utils.stopApplication();
        if (logger.isInfoEnabled()) {
            logger.info("the es_ecs-agent server is started!");
        }
    }
}
