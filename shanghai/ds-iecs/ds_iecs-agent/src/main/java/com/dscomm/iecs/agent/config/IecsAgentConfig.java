package com.dscomm.iecs.agent.config;

import com.dscomm.iecs.base.config.task.TaskConfig;
import org.mx.dal.config.DalHibernateConfig;
import org.mx.service.server.config.ServerConfig;
import org.mx.spring.config.SpringCacheConfig;
import org.mx.spring.config.SpringConfig;
import org.mx.spring.task.BaseTask;
import org.mx.spring.task.TaskFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 描述:agnet模块配置文件
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:09
 */
@Configuration
@PropertySource({
        "classpath:graphql.properties",
        "classpath:jpa.properties",
        "classpath:task-schedule.properties",
        "classpath:fixed.properties",
        "classpath:changeability.properties",
        "classpath:server.properties"
})
@Import({
        SpringConfig.class,
        SpringCacheConfig.class,
        DalHibernateConfig.class,
        ServerConfig.class,
        TaskConfig.class ,
//        SpringI18nConfig.class
})
@EnableJpaRepositories({
        "com.dscomm.iecs.**.dal.repository"
})

@ComponentScan({
        "com.dscomm.iecs.**.service",
        "com.dscomm.iecs.**.graphql"
})

public class IecsAgentConfig {



    @Bean(name = "agentTaskFactory" )
    public TaskFactory getInitialTaskFactory(ApplicationContext context) {
        TaskFactory taskFactory = new TaskFactory();
        BaseTask agentTask = context.getBean("agentCacheInitializeTask", BaseTask.class);
        taskFactory.addSingleAsyncTask(agentTask);
        taskFactory.runSerialTasks();
        return taskFactory;
    }

}
