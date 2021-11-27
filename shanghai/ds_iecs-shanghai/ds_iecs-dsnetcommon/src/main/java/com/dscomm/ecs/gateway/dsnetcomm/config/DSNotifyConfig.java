package com.dscomm.ecs.gateway.dsnetcomm.config;

import com.dscomm.ecs.gateway.dsnetcomm.service.DsNetCommonManger;
import com.dscomm.ecs.gateway.dsnetcomm.service.ProcessChain;
import com.dscomm.ecs.gateway.dsnetcomm.service.ReceiveChain;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.BodyFactory;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.XMLProcessChain;
import org.mx.spring.config.SpringConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * 描述:21通信web模块tomcat启动类配置文件
 *
 * @author YangFuxi
 * Date Time 2020/8/18 14:50
 */
@Configuration

@PropertySource({
        "classpath:applicationConfig-dsnetcommex.properties"
})
@Import({
        SpringConfig.class
})

@ComponentScan({
        "com.dscomm.ecs.gateway.**.service"
})
public class DSNotifyConfig {
    private static final Logger logger=LoggerFactory.getLogger(DSNotifyConfig.class);
    @Bean
    public DsNetCommonConfigBean dsNetCommonConfig() {
        return new DsNetCommonConfigBean();
    }

    @Bean(initMethod = "init")
    public BodyFactory getBodyFactory(ApplicationContext context) {
        try {
            return new BodyFactory(context);
        } catch (Exception ex) {
            logger.error("init BodyFactory fail",ex);
            return null;
        }
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ProcessChain processChain(ApplicationContext context, DsNetCommonConfigBean configBean) {
        try {
            return new ProcessChain(context, configBean);
        } catch (Exception ex) {
            logger.error("init ProcessChain fail",ex);
            return null;
        }
    }

    @Bean(initMethod = "init")
    public XMLProcessChain getXmlProcessChain(ApplicationContext ctx, DsNetCommonConfigBean configBean) {
        try {
            return new XMLProcessChain(ctx, null, configBean);
        } catch (Exception ex) {
            logger.error("init XMLProcessChain fail",ex);
            return null;
        }
    }

    @Bean
    public ReceiveChain getReceiverChain(ProcessChain processChain, XMLProcessChain xmlProcessChain) {
        try {
            return new ReceiveChain(processChain, xmlProcessChain);
        } catch (Exception ex) {
            logger.error("init ReceiveChain fail",ex);
            return null;
        }
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DsNetCommonManger getManger(ApplicationContext context, DsNetCommonConfigBean configBean, ReceiveChain receiveChain) {
        try {
            DsNetCommonManger manger = new DsNetCommonManger(context, configBean, receiveChain);
            return manger;
        } catch (Exception ex) {
            logger.error("init DsNetCommonManger fail",ex);
            return null;
        }
    }


}
