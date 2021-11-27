package com.dscomm.iecs.schedule.tasks.basedata;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.schedule.service.basedata.water.*;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("waterTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class WaterTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(WaterTask.class);
    private LogService logService;

    private ServletService servletService ;
    private WaterCraneScheduleService waterCraneScheduleService ;
    private WaterFireHydrantScheduleService waterFireHydrantScheduleService ;
    private WaterIntakeWharfScheduleService waterIntakeWharfScheduleService ;
    private WaterNaturalSourceScheduleService waterNaturalSourceScheduleService;
    private WaterPoolScheduleService waterPoolScheduleService;
    private WaterSourceBaseScheduleService waterSourceBaseScheduleService;

    @Autowired
    public WaterTask(LogService logService, ServletService servletService, WaterCraneScheduleService waterCraneScheduleService,
                     WaterFireHydrantScheduleService waterFireHydrantScheduleService, WaterIntakeWharfScheduleService waterIntakeWharfScheduleService,
                     WaterNaturalSourceScheduleService waterNaturalSourceScheduleService, WaterPoolScheduleService waterPoolScheduleService,
                     WaterSourceBaseScheduleService waterSourceBaseScheduleService) {
        this.logService = logService;
        this.servletService = servletService;
        this.waterCraneScheduleService = waterCraneScheduleService;
        this.waterFireHydrantScheduleService = waterFireHydrantScheduleService;
        this.waterIntakeWharfScheduleService = waterIntakeWharfScheduleService;
        this.waterNaturalSourceScheduleService = waterNaturalSourceScheduleService;
        this.waterPoolScheduleService = waterPoolScheduleService;
        this.waterSourceBaseScheduleService = waterSourceBaseScheduleService;
    }

    @Override
    public void invoke() {
        long targetTime = servletService.getSystemTime();

        //TODO 数据中心没有数据

        //同步水鹤信息
        try {
            logService.infoLog(logger, "tasks", "waterCraneScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            waterCraneScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "waterCraneScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "waterCraneScheduleService", "schedule task  fail", ex);
        }

        //同步消防栓信息
        try {
            logService.infoLog(logger, "tasks", "waterFireHydrantScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            waterFireHydrantScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "waterFireHydrantScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "waterFireHydrantScheduleService", "schedule task fail", ex);
        }

        //同步消防码头信息
        try {
            logService.infoLog(logger, "tasks", "waterIntakeWharfScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            waterIntakeWharfScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "waterIntakeWharfScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "waterIntakeWharfScheduleService", "schedule task fail", ex);
        }
        //同步天然水源信息
        try {
            logService.infoLog(logger, "tasks", "waterNaturalSourceScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            waterNaturalSourceScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "waterNaturalSourceScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "waterNaturalSourceScheduleService", "schedule task  fail", ex);
        }

        //同步消防水池信息
        try {
            logService.infoLog(logger, "tasks", "waterPoolScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            waterPoolScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "waterPoolScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "waterPoolScheduleService", "schedule task fail", ex);
        }

        //同步水源基本信息
        try {
            logService.infoLog(logger, "tasks", "waterSourceBaseScheduleService", "start excute tasks");
            long start = System.currentTimeMillis();

            waterSourceBaseScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "waterSourceBaseScheduleService", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "waterSourceBaseScheduleService", "schedule task fail", ex);
        }
    }
}
