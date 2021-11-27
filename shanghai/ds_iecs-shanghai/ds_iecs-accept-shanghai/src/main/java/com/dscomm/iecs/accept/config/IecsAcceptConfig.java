package com.dscomm.iecs.accept.config;

import com.dscomm.iecs.accept.service.HandleStatusChangeService;
import com.dscomm.iecs.accept.service.outside.OutsideChangeService;
import com.dscomm.iecs.accept.service.pushData.DockingChangeService;
import com.dscomm.iecs.accept.service.pushData.PushDataChangeService;
import com.dscomm.iecs.accept.service.pushData.PushPoliceChangeService;
import com.dscomm.iecs.accept.service.pushData.ReportChaneService;
import com.dscomm.iecs.base.config.IecsBaseConfig;
import com.dscomm.iecs.base.config.task.TaskConfig;
import com.dscomm.iecs.base.task.factory.IecsScheduleTaskFactory;
import org.mx.dal.config.DalHibernateConfig;
import org.mx.service.server.config.ServerConfig;
import org.mx.spring.config.SpringCacheConfig;
import org.mx.spring.config.SpringConfig;
import org.mx.spring.config.SpringI18nConfig;
import org.mx.spring.task.BaseTask;
import org.mx.spring.task.TaskFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 描述: 整合资源 模块配置类
 *
 * @author YangFuxi
 * Date Time 2020/4/8 12:27
 */
@Configuration
@PropertySource({
        "classpath:graphql.properties",
        "classpath:jpa.properties",
        "classpath:tasks-schedule.properties",
        "classpath:fixed.properties",
        "classpath:changeability.properties",
        "classpath:i18n.properties",
        "classpath:server.properties"
})
@Import({
        SpringConfig.class,
        SpringCacheConfig.class,
        DalHibernateConfig.class,
        ServerConfig.class,
        TaskConfig.class ,
        IecsBaseConfig.class ,
        SpringI18nConfig.class
})
@EnableJpaRepositories({
        "com.dscomm.iecs.**.dal.repository"
})

@ComponentScan({
        "com.dscomm.iecs.**.service.*",
        "com.dscomm.iecs.**.graphql"
})
public class IecsAcceptConfig {



    @Bean(name = "initializeTaskFactory" )
    public TaskFactory getInitialTaskFactory(ApplicationContext context) {
        TaskFactory taskFactory = new TaskFactory();
        BaseTask basedataCacheTask = context.getBean("initializeDataCacheTask", BaseTask.class);
        taskFactory.addSingleAsyncTask(basedataCacheTask);
        BaseTask agentTask = context.getBean("agentCacheInitializeTask", BaseTask.class);
        taskFactory.addSingleAsyncTask(agentTask);
        taskFactory.runSerialTasks();
        return taskFactory;
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


    //车辆状态 影响案件状态逻辑 项目具体实现（ HandleStatusChangeService 接口 ）
    @Bean(name = "handleStatusChange")
    public HandleStatusChangeService handleStatusChange(ApplicationContext context) {
        HandleStatusChangeService method = context.getBean( "handleStatusChangeServiceImpl" , HandleStatusChangeService.class );
        return method ;
    }


    //推送信息
    @Bean(name = "pushDataChange")
    public PushDataChangeService pushDataChange(ApplicationContext context){
        PushDataChangeService method = context.getBean("hangZhouPushDataChangeServiceImpl", PushDataChangeService.class);
        return method ;
    }


    //一体化上报 项目具体实现（ ReportChaneService 接口 ）
    @Bean(name = "reportChange")
    public ReportChaneService reportChange(ApplicationContext context) {
        ReportChaneService method = context.getBean( "reportChangeServiceImpl" , ReportChaneService.class );
        return method ;
    }


    //对接coc 项目具体实现（ DockingService 接口 ）
    @Bean(name = "dockingChange")
    public DockingChangeService dockingChange(ApplicationContext context) {
        DockingChangeService method = context.getBean( "dockingChangeServiceImpl" , DockingChangeService.class );
        return method ;
    }


    //推送公安警情  具体实现（PushPoliceChangeService）
    @Bean(name = "pushPoliceChange")
    public PushPoliceChangeService pushPoliceChange (ApplicationContext context){
        PushPoliceChangeService method = context.getBean("pushPoliceChangeServiceImpl", PushPoliceChangeService.class);
        return method ;
    }

    //转警 错位接警 请求协助
    @Bean(name = "outsideChange")
    public OutsideChangeService outsideChange(ApplicationContext context){
        OutsideChangeService method = context.getBean("outsideChangeServiceImpl", OutsideChangeService.class);
        return method ;
    }

}
