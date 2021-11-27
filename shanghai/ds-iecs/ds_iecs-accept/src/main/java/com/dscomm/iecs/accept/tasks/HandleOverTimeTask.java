package com.dscomm.iecs.accept.tasks;

import com.dscomm.iecs.accept.service.OverTimeTaskService;
import com.dscomm.iecs.base.service.log.LogService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 描述： iecs 处警单位接收超时提醒更新 任务
 */
@Component("handleOverTimeTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class HandleOverTimeTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(HandleOverTimeTask.class);
    private LogService logService;
    private OverTimeTaskService overTimeTaskService ;


    @Autowired
    public HandleOverTimeTask( LogService logService ,
                               OverTimeTaskService overTimeTaskService

    ) {
        this.logService = logService;
        this.overTimeTaskService = overTimeTaskService ;
    }

    @Override
    public void invoke() {

        try {
            logService.infoLog(logger, "tasks", "handleNoticeOrganizationOverTimeTask", "start excute tasks");
            long start = System.currentTimeMillis();

            overTimeTaskService.handleNoticeOrganizationOverTimeTask();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "handleNoticeOrganizationOverTimeTask", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "handleNoticeOrganizationOverTimeTask", "handleNoticeOrganizationOverTimeTask fail", ex);
        }


        try {
            logService.infoLog(logger, "tasks", "handleOrganizationOverTimeTask", "start excute tasks");
            long start = System.currentTimeMillis();

            overTimeTaskService.handleOrganizationOverTimeTask();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "handleOrganizationOverTimeTask", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "handleOrganizationOverTimeTask", "handleOrganizationOverTimeTask fail", ex);
        }
    }
}
