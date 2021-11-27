package com.dscom.iecs.schedule.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.dscomm.iecs.base.config.IecsBaseConfig;
import com.dscomm.iecs.base.config.cas.CasFilterConfig;
import com.dscomm.iecs.base.config.task.TaskConfig;
import com.dscomm.iecs.base.exception.UserInterfaceBaseException;
import com.dscomm.iecs.base.task.factory.IecsScheduleTaskFactory;
import com.dscomm.iecs.base.utils.AES256Helper;
import com.dscomm.iecs.schedule.factory.ConnectionsFactory;
import com.dscomm.iecs.schedule.factory.UdpReceiveTaskFactory;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.config.DalHibernateConfig;
import org.mx.dal.config.PasswordReader;
import org.mx.service.tomcat.config.TomcatServerConfig;
import org.mx.spring.config.SpringCacheConfig;
import org.mx.spring.config.SpringConfig;
import org.mx.spring.task.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 描述:接警受理模块tomcat配置类
 *
 * @author YangFuxi
 * Date Time 2020/4/8 12:41
 */
@Configuration
@EnableApolloConfig({"changeability"})
@PropertySource({
        "classpath:cors.properties",
        "classpath:jpa.properties",
        "classpath:connections.properties",
        "classpath:task-schedule.properties",
        "classpath:udp-schedule.properties",
       "classpath:cache.properties",

        "classpath:kafka.properties",
        "classpath:mqtt.properties",


        "classpath:server.properties",
        "classpath:fixed.properties",
        "classpath:changeability.properties"
})
@Import({
        SpringConfig.class,
        SpringCacheConfig.class,
        DalHibernateConfig.class,
        TomcatServerConfig.class,
        TaskConfig.class ,
        CasFilterConfig.class,
        IecsBaseConfig.class ,
//        SpringI18nConfig.class
})


@EnableJpaRepositories({
        "com.dscomm.iecs.**.dal.repository"
})

@ComponentScan({
        "com.dscomm.iecs.**.service",
        "com.dscomm.iecs.**.tasks"
})
public class IecsScheduleTomcatConfig {

    private static final Logger logger = LoggerFactory.getLogger(IecsScheduleTomcatConfig.class);


    @Bean(name = "dsDecryptionReader")
    public PasswordReader customPasswordReader(Environment env, ApplicationContext context) {
        logger.info("general.password.reader:" + env.getProperty("general.password.reader"));
        String dbNeedDecryption = env.getProperty("dbNeedDecryption");
        Boolean dbNeedDecrypt = Strings.isBlank(dbNeedDecryption) ? false : Boolean.valueOf(dbNeedDecryption);
        logger.info("dbNeedDecryption:" + dbNeedDecrypt);
        return new PasswordReader() {
            @Override
            public String read(String password) {
                try {
                    if (dbNeedDecrypt) {
                        String result = AES256Helper.decrypt(password);
                        logger.info(String.format("success read word:%s", password));
                        return result;
                    } else {
                        logger.info(String.format("no read word:%s","***"));
                        return password;
                    }
                } catch (Exception ex) {
                    logger.error(String.format("data decryption fail"), ex);
                    throw new UserInterfaceBaseException(UserInterfaceBaseException.BaseErrors.DECRYPT_FAIL);
                }
            }
        };
    }


    //初始化数据库连接
    @Bean(name = "connectionsFactory", initMethod = "init", destroyMethod = "destroy")
    public ConnectionsFactory connectionsFactory(Environment env) {
        return new ConnectionsFactory(env);
    }


    //udp 消息接收
    @Bean(name =  "initUdpTaskFactory", initMethod = "init", destroyMethod = "destroy")
    public UdpReceiveTaskFactory initializeTaskFactory(ApplicationContext context, Environment env ) {
        return  new UdpReceiveTaskFactory(context, env);
    }

    /**
     * 定时调度任务工厂
     *
     * @param context spring容器
     * @param env     上下文环境变量
     * @return 返回任务调度工厂
     */
    @Bean(name = "iecsScheduleTaskFactory", initMethod = "init", destroyMethod = "destroy")
    public IecsScheduleTaskFactory getScheduleTaskFactory(ApplicationContext context, Environment env) {
        return new IecsScheduleTaskFactory(context, env);
    }


    @Bean(name = "taskFactory")
    public TaskFactory getTaskFactory(ApplicationContext context) {
        TaskFactory factory = new TaskFactory();
        return factory;
    }




}
