package com.dscomm.iecs.schedule.tasks.basedata;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.schedule.service.basedata.accout.SystemRoleScheduleService;
import com.dscomm.iecs.schedule.service.basedata.accout.SystemUserScheduleService;
import com.dscomm.iecs.schedule.service.basedata.accout.UserRoleScheduleService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *  用户信息同步任务 （ 包含 用户 角色 角色用户关系  ）
 */
@Component("accountTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AccountTask extends BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(AccountTask.class);
    private LogService logService;

    private ServletService servletService ;
    private SystemUserScheduleService systemUserScheduleService ;
    private SystemRoleScheduleService systemRoleScheduleService ;
    private UserRoleScheduleService userRoleScheduleService ;

    @Autowired
    public AccountTask(  LogService logService , ServletService servletService , SystemUserScheduleService systemUserScheduleService  ,
                         SystemRoleScheduleService systemRoleScheduleService , UserRoleScheduleService userRoleScheduleService ) {
        this.logService = logService ;
        this.servletService = servletService;
        this.systemUserScheduleService = systemUserScheduleService ;
        this.systemRoleScheduleService = systemRoleScheduleService ;
        this.userRoleScheduleService = userRoleScheduleService;
    }


    @Override
    public void invoke() {

        long targetTime = servletService.getSystemTime();

        //同步用户信息
        try {
            logService.infoLog(logger, "tasks", "systemUserSchedule", "start excute tasks");
            long start = System.currentTimeMillis();

            systemUserScheduleService.transport( targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "systemUserSchedule", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "systemUserSchedule", "schedule task  fail", ex);
        }

        //同步角色信息 ( 包含删除无效数据  )
        try {
            logService.infoLog(logger, "tasks", "systemRole", "start excute tasks");
            long start = System.currentTimeMillis();

            systemRoleScheduleService.transport( targetTime );
            systemRoleScheduleService.transportDelete();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "systemRole", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "systemRole", "schedule task fail", ex);
        }

        //同步用户角色关系信息 ( 包含删除无效数据  )
        try {
            logService.infoLog(logger, "tasks", "userRole", "start excute tasks");
            long start = System.currentTimeMillis();

            userRoleScheduleService.transport( targetTime );
            userRoleScheduleService.transportDelete();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "userRole", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "userRole", "schedule task fail", ex);
        }


    }

}
