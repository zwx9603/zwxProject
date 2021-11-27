package com.dscomm.iecs.basedata.tasks;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.BaseDateCacheService;
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
@Component("baseDataCacheDataTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class BaseDataCacheDataTask extends BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(BaseDataCacheDataTask.class);
    private LogService logService;
    private BaseDateCacheService baseDateCacheService ;



    @Autowired
    public BaseDataCacheDataTask(  LogService logService , BaseDateCacheService baseDateCacheService
    ) {
        this.logService = logService ;
        this.baseDateCacheService = baseDateCacheService ;
    }


    @Override
    public void invoke() {

        // 缓存信息强制更新
        try {
            logService.infoLog(logger, "tasks", "initBaseDateCache", "start excute tasks");
            long start = System.currentTimeMillis();

            baseDateCacheService.updateBaseDateCache(   );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "tasks", "initBaseDateCache", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "initBaseDateCache", "force update cache dictionary fail", ex);
        }


    }

}
