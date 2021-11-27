package com.dscomm.iecs.schedule.tasks.basedata;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.schedule.service.basedata.systemAndConfig.DictionaryScheduleService;
import com.dscomm.iecs.schedule.service.basedata.systemAndConfig.SystemConfigurationScheduleService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("systemConfigTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SystemConfigTask extends BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(PersonTask.class);
    private LogService logService;
    private ServletService servletService ;

    private DictionaryScheduleService dictionaryScheduleService;
    private SystemConfigurationScheduleService systemConfigurationScheduleService;

    @Autowired
    public SystemConfigTask(LogService logService, ServletService servletService, DictionaryScheduleService dictionaryScheduleService, SystemConfigurationScheduleService systemConfigurationScheduleService) {
        this.logService = logService;
        this.servletService = servletService;
        this.dictionaryScheduleService = dictionaryScheduleService;
        this.systemConfigurationScheduleService = systemConfigurationScheduleService;
    }

    @Override
    public void invoke() {
        long targetTime = servletService.getSystemTime();

        //同步系统配置信息
        try {
            logService.infoLog(logger, "tasks", "systemConfigurationScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            systemConfigurationScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "systemConfigurationScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "systemConfigurationScheduleService", "schedule task  fail", ex);
        }

        //同步字典信息
        try {
            logService.infoLog(logger, "tasks", "dictionaryScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            dictionaryScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "dictionaryScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "dictionaryScheduleService", "schedule task  fail", ex);
        }
    }

}
