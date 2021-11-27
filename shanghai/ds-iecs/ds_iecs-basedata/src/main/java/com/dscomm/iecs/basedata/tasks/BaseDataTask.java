package com.dscomm.iecs.basedata.tasks;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * 描述： iecs basedate 缓存信息强制更新 任务
 *
 */
@Component("baseDataTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class BaseDataTask extends BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(BaseDataTask.class);
    private LogService logService;
    private ServletService servletService ;



    @Autowired
    public BaseDataTask(LogService logService ,
                        ServletService servletService
    ) {
        this.logService = logService ;
        this.servletService  = servletService ;
    }


    @Override
    public void invoke() {

        // 缓存信息强制更新
        try {
            logService.infoLog(logger, "tasks", "initBaseDateCache", "start excute tasks");
            long start = System.currentTimeMillis();

            //定时更新时间差
            servletService.updateSystemTime();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "initBaseDateCache", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "initBaseDateCache", "force update cache dictionary fail", ex);
        }


    }

}
