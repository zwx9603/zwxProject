package com.dscomm.iecs.agent.tasks;

import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.ObtainIPUtils;
import com.dscomm.iecs.basedata.service.BaseDateCacheService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 描述:坐席缓存删除任务
 *
 * @author YangFuxi
 * Date Time 2019/9/25 16:56
 */
@Component("agentCacheDeleteTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class AgentCacheDeleteTask extends BaseTask {
    private static final Logger logger=LoggerFactory.getLogger(AgentCacheDeleteTask.class);
    private LogService logService;
    private AgentService agentService;

    @Autowired
    public AgentCacheDeleteTask(LogService logService, AgentService agentService   ) {
        this.logService = logService;
        this.agentService = agentService;
    }

    @Override
    public void invoke() {
        try {
            logService.infoLog(logger,"tasks","AgentCacheDeleteTask.invoke","start delete agent info to cache");

            agentService.deleteAgent();

            logService.infoLog(logger,"tasks","AgentCacheDeleteTask.invoke","finished delete agent info to cache");

        }catch (Exception ex){
            logService.erorLog(logger,"tasks","AgentCacheDeleteTask.invoke","init agent to cache fail",ex);
        }
    }
}
