package com.dscomm.iecs.agent.tasks;

import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 描述:用户访问心跳控制检查任务
 *
 * @author YangFuxi
 * Date Time 2019/11/28 9:39
 */
@Component("loginCheckTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class LoginHeartCheckTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(LoginHeartCheckTask.class);
    private LogService logService;
    private UserService userService;

    @Autowired
    public LoginHeartCheckTask(LogService logService, UserService userService) {
        this.logService = logService;
        this.userService = userService;
    }

    @Override
    public void invoke() {
        try {
            logService.infoLog(logger, "task", "LoginCheckTask", "start task");
            long start = System.currentTimeMillis();

            userService.checkHeart();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "task", "LoginCheckTask", String.format("end task,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "task", "LoginCheckTask", "task fail", ex);
        }
    }
}
