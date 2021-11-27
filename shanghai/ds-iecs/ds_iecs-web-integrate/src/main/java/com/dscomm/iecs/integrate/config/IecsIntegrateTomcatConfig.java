package com.dscomm.iecs.integrate.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.dscomm.iecs.base.config.task.TaskConfig;
import org.mx.service.tomcat.config.TomcatServerConfig;
import org.mx.spring.config.SpringCacheConfig;
import org.mx.spring.config.SpringConfig;
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
@EnableApolloConfig({"changeability"})

@PropertySource({
        "classpath:cors.properties",
        "classpath:jpa.properties",
        "classpath:cache.properties",
        "classpath:fixed.properties",
        "classpath:changeability.properties",
        "classpath:task-schedule.properties"
})
@Import({
        SpringConfig.class,
        SpringCacheConfig.class,
//        DalHibernateConfig.class,
        TomcatServerConfig.class,
        TaskConfig.class ,
})
@EnableJpaRepositories({
        "com.dscomm.iecs.**.dal.repository"
})

@ComponentScan({
        "com.dscomm.iecs.**.service",
        "com.dscomm.iecs.**.graphql",
        "com.dscomm.iecs.**.tasks",
        "com.dscomm.iecs.**.utils"
})
public class IecsIntegrateTomcatConfig {


    @Bean(name = "initializeTaskFactory" )
    public TaskFactory getInitialTaskFactory(ApplicationContext context) {
        TaskFactory taskFactory = new TaskFactory();
        BaseTask agentTask = context.getBean("udpPoliceReceiveTask", BaseTask.class);
        taskFactory.addSingleAsyncTask(agentTask);
        taskFactory.runSerialTasks();
        return taskFactory;
    }


}
