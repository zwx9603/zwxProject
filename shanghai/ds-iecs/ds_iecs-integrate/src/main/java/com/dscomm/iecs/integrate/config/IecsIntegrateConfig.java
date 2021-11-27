package com.dscomm.iecs.integrate.config;

import com.dscomm.iecs.base.config.task.TaskConfig;
import org.mx.service.server.config.ServerConfig;
import org.mx.spring.config.SpringCacheConfig;
import org.mx.spring.config.SpringConfig;
import org.mx.spring.config.SpringI18nConfig;
import org.mx.spring.task.BaseTask;
import org.mx.spring.task.TaskFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 描述:接警受理模块tomcat配置类
 *
 * @author YangFuxi
 * Date Time 2020/4/8 12:41
 */
@Configuration
@PropertySource({
        "classpath:fixed.properties",
        "classpath:changeability.properties"
})
@Import({
        SpringConfig.class,
        SpringCacheConfig.class,
//        DalHibernateConfig.class,
        ServerConfig.class,
        TaskConfig.class ,
        SpringI18nConfig.class
})
@EnableJpaRepositories({
        "com.dscomm.iecs.**.dal.repository"
})

@ComponentScan({
        "com.dscomm.iecs.**.service",
})
public class IecsIntegrateConfig {


    @Bean(name = "initializeTaskFactory" )
    public TaskFactory getInitialTaskFactory(ApplicationContext context) {
        TaskFactory taskFactory = new TaskFactory();
        BaseTask agentTask = context.getBean("udpPoliceReceiveTask", BaseTask.class);
        taskFactory.addSingleAsyncTask(agentTask);
        taskFactory.runSerialTasks();
        return taskFactory;
    }


}
