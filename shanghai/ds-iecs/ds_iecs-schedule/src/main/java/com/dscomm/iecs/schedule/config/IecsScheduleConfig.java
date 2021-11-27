package com.dscomm.iecs.schedule.config;

import com.dscomm.iecs.base.exception.UserInterfaceBaseException;
import com.dscomm.iecs.base.task.factory.IecsScheduleTaskFactory;
import com.dscomm.iecs.base.utils.AES256Helper;
import com.dscomm.iecs.schedule.factory.ConnectionsFactory;
import com.dscomm.iecs.schedule.factory.UdpReceiveTaskFactory;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.config.DalHibernateConfig;
import org.mx.dal.config.PasswordReader;
import org.mx.service.server.config.ServerConfig;
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
 * 描述： 消防任务调度器应用配置定义
 *
 * @author john peng
 * Date time 2018/7/15 下午3:59
 */
@Configuration
@Import({
        SpringConfig.class,
        SpringCacheConfig.class,
        DalHibernateConfig.class,
        ServerConfig.class,
})

@PropertySource({
        "classpath:jpa.properties",
        "classpath:connections.properties",
        "classpath:task-schedule.properties",
        "classpath:udp-schedule.properties",
//        "classpath:i18n.properties",

        "classpath:kafka.properties",
        "classpath:mqtt.properties",


        "classpath:server.properties",
        "classpath:fixed.properties",
        "classpath:changeability.properties"
})


@EnableJpaRepositories({
        "com.dscomm.iecs.**.dal.repository"
})

@ComponentScan({
        "com.dscomm.iecs.**.service",
        "com.dscomm.iecs.**.tasks"
})
public class IecsScheduleConfig {

    private static final Logger logger = LoggerFactory.getLogger(IecsScheduleConfig.class);


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
