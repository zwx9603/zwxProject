package com.dscomm.iecs.accept.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.dscomm.iecs.accept.service.HandleStatusChangeService;
import com.dscomm.iecs.accept.service.outside.OutsideChangeService;
import com.dscomm.iecs.accept.service.pushData.DockingChangeService;
import com.dscomm.iecs.accept.service.pushData.PushDataChangeService;
import com.dscomm.iecs.accept.service.pushData.PushPoliceChangeService;
import com.dscomm.iecs.accept.service.pushData.ReportChaneService;
import com.dscomm.iecs.base.config.IecsBaseConfig;
import com.dscomm.iecs.base.config.cas.CasFilterConfig;
import com.dscomm.iecs.base.config.task.TaskConfig;
import com.dscomm.iecs.base.exception.UserInterfaceBaseException;
import com.dscomm.iecs.base.task.factory.IecsScheduleTaskFactory;
import com.dscomm.iecs.base.utils.AES256Helper;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.config.DalHibernateConfig;
import org.mx.dal.config.PasswordReader;
import org.mx.service.tomcat.config.TomcatServerConfig;
import org.mx.spring.config.SpringCacheConfig;
import org.mx.spring.config.SpringConfig;
import org.mx.spring.task.BaseTask;
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
        "classpath:graphql.properties",
        "classpath:jpa.properties",
        "classpath:task-schedule.properties",
//        "classpath:i18n.properties",
        "classpath:fixed.properties",
        "classpath:cache.properties",
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
        "com.dscomm.iecs.**.graphql",
        "com.dscomm.iecs.**.tasks"
})
public class IecsAcceptTomcatConfig {

    private static final Logger logger = LoggerFactory.getLogger(IecsAcceptTomcatConfig.class);

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


    @Bean(name = "agentTaskFactory" )
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
    public PushDataChangeService reportUdpChangeService(ApplicationContext context){
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
    public PushPoliceChangeService pushPoliceChangeService(ApplicationContext context){
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
