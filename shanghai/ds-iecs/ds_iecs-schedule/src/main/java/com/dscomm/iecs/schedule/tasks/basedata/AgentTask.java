package com.dscomm.iecs.schedule.tasks.basedata;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.schedule.service.basedata.agent.AcdScheduleService;
import com.dscomm.iecs.schedule.service.basedata.agent.AgentScheduleService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *  坐席 信息同步任务 （ 包含 坐席 acd组  ）
 */
@Component("agentTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AgentTask extends BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(AgentTask.class);
    private LogService logService;

    private ServletService servletService ;
    private AgentScheduleService  agentScheduleService ;
    private AcdScheduleService acdScheduleService ;

    @Autowired
    public AgentTask(  LogService logService , ServletService servletService , AgentScheduleService  agentScheduleService ,
                       AcdScheduleService acdScheduleService ) {
        this.logService = logService ;
        this.servletService = servletService;
        this.agentScheduleService = agentScheduleService ;
        this.acdScheduleService = acdScheduleService ;
    }


    @Override
    public void invoke() {

        long targetTime = servletService.getSystemTime();

        // 坐席 信息
        try {
            logService.infoLog(logger, "tasks", "agent", "start excute tasks");
            long start = System.currentTimeMillis();

            agentScheduleService.transport( targetTime );
            agentScheduleService.transportDelete();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "agent", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "agent", "schedule task  fail", ex);
        }

        // acd组信息
        try {
            logService.infoLog(logger, "tasks", "acd", "start excute tasks");
            long start = System.currentTimeMillis();

            acdScheduleService.transport(targetTime );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "acd", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "acd", "schedule task fail", ex);
        }


    }

}
