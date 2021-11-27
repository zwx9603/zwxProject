package com.dscomm.iecs.accept.tasks;

import com.dscomm.iecs.accept.graphql.firetypebean.FireControlVoiceReturnBean;
import com.dscomm.iecs.accept.service.FireControlVoiceService;
import com.dscomm.iecs.base.service.log.LogService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("fireControlVoiceTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class FireControlVoiceTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(HandleOverTimeTask.class);
    private LogService logService;
    private FireControlVoiceService fireControlVoiceService;
    private String token;

    @Autowired
    public FireControlVoiceTask(LogService logService, FireControlVoiceService fireControlVoiceService) {
        this.logService = logService;
        this.fireControlVoiceService = fireControlVoiceService;
        token = "";
    }


    @Override
    public void invoke() {
        try {
            logService.infoLog(logger, "tasks", "fireControlVoiceTask", "start excute tasks");
            long start = System.currentTimeMillis();

            FireControlVoiceReturnBean res = fireControlVoiceService.getFireControlVoice(token);
            token = res.getToken();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "fireControlVoiceTask", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "fireControlVoiceTask", "fireControlVoiceTask fail", ex);
        }
    }
}
