package com.dscomm.iecs.schedule.tasks.basedata;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.schedule.service.basedata.person.PersonScheduleService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("personTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PersonTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(PersonTask.class);
    private LogService logService;
    private ServletService servletService ;

    private PersonScheduleService personScheduleService;

    @Autowired
    public PersonTask(LogService logService, ServletService servletService, PersonScheduleService personScheduleService) {
        this.logService = logService;
        this.servletService = servletService;
        this.personScheduleService = personScheduleService;
    }

    @Override
    public void invoke() {
        long targetTime = servletService.getSystemTime();

        //同步人员信息
        try {
            logService.infoLog(logger, "tasks", "personScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            personScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "personScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "personScheduleService", "schedule task  fail", ex);
        }
    }
}
