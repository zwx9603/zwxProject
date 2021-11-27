package com.dscomm.iecs.accept.tasks;

import com.dscomm.iecs.accept.service.EarlyWarningService;
import com.dscomm.iecs.accept.service.HandleService;
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
 * 描述： iecs  tts播放路径更新 任务
 */
@Component("speakFileUpdateTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class SpeakFileUpdateTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(SpeakFileUpdateTask.class);
    private LogService logService;
    private HandleService handleService ;
    private EarlyWarningService earlyWarningService ;


    @Autowired
    public SpeakFileUpdateTask(LogService logService ,
                               HandleService handleService ,
                               EarlyWarningService earlyWarningService

    ) {
        this.logService = logService;
        this.handleService = handleService ;
        this.earlyWarningService = earlyWarningService ;

    }

    @Override
    public void invoke() {

        try {
            logService.infoLog(logger, "tasks", "updateEarlyWarningSpeakFile", "start excute tasks");
            long start = System.currentTimeMillis();

            earlyWarningService.updateEarlyWarningSpeakFile();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "updateEarlyWarningSpeakFile", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "updateEarlyWarningSpeakFile", "updateHandleOrganizationSpeakFile fail", ex);
        }


        try {
            logService.infoLog(logger, "tasks", "updateHandleOrganizationSpeakFile", "start excute tasks");
            long start = System.currentTimeMillis();

            handleService.updateHandleOrganizationSpeakFile();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "updateHandleOrganizationSpeakFile", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "updateHandleOrganizationSpeakFile", "updateHandleOrganizationSpeakFile fail", ex);
        }


    }
}
