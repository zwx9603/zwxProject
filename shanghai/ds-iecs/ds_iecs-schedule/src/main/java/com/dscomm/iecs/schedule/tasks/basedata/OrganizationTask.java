package com.dscomm.iecs.schedule.tasks.basedata;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.schedule.service.basedata.organization.OrganizationScheduleService;
import com.dscomm.iecs.schedule.service.basedata.organization.UnitEmergencyScheduleService;
import com.dscomm.iecs.schedule.service.basedata.organization.UnitJointScheduleService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("organizationTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrganizationTask extends BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationTask.class);
    private LogService logService;
    private ServletService servletService ;

    private OrganizationScheduleService organizationScheduleService;
    private UnitEmergencyScheduleService unitEmergencyScheduleService;
    private UnitJointScheduleService unitJointScheduleService;

    @Autowired
    public OrganizationTask( LogService logService , ServletService servletService,
                            OrganizationScheduleService organizationScheduleService, UnitEmergencyScheduleService unitEmergencyScheduleService, UnitJointScheduleService unitJointScheduleService) {
        this.logService = logService;
        this.servletService = servletService;
        this.organizationScheduleService = organizationScheduleService;
        this.unitEmergencyScheduleService = unitEmergencyScheduleService;
        this.unitJointScheduleService = unitJointScheduleService;
    }

    @Override
    public void invoke() {
        long targetTime = servletService.getSystemTime();

        //应急联动信息
        try {
            logService.infoLog(logger, "tasks", "unitEmergencyScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            unitEmergencyScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "unitEmergencyScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "unitEmergencyScheduleService", "schedule task  fail", ex);
        }

        //联勤信息
        try {
            logService.infoLog(logger, "tasks", "unitJointScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            unitJointScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "unitJointScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "unitJointScheduleService", "schedule task  fail", ex);
        }

        //机构信息
        try {
            logService.infoLog(logger, "tasks", "organizationScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            organizationScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "organizationScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "organizationScheduleService", "schedule task  fail", ex);
        }
    }

}
