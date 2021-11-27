package com.dscomm.iecs.agent.tasks;

import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.ObtainIPUtils;
import com.dscomm.iecs.basedata.service.BaseDateCacheService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 描述:坐席初始化缓存任务
 *
 * @author YangFuxi
 * Date Time 2019/9/25 16:56
 */
@Component("agentCacheInitializeTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class AgentCacheInitializeTask extends BaseTask {
    private static final Logger logger=LoggerFactory.getLogger(AgentCacheInitializeTask.class);
    private LogService logService;
    private AgentService agentService;
    private UserService userService ;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService ;

    @Autowired
    public AgentCacheInitializeTask(LogService logService, AgentService agentService , UserService userService ,
                                    DictionaryService dictionaryService , OrganizationService organizationService    ) {
        super("Initialize the AgentCacheInitializeTask data into cache.", true);
        this.logService = logService;
        this.agentService = agentService;
        this.userService = userService ;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService ;
    }

    @Override
    public void invoke() {
        try {
            logService.infoLog(logger,"tasks","AgentCacheInitializeTask.invoke","start init agent info to cache");

            ObtainIPUtils.localTomcatIpAndPort();

            dictionaryService.forceUpdateCacheDictionary(); ;

            organizationService.forceUpdateCacheOrganization(); ;


            Thread.currentThread().sleep(2 * 1000l );

            agentService.initAgentCache();

            Thread.currentThread().sleep(3 * 1000l );

            userService.initUserInfoCache() ;

            logService.infoLog(logger,"tasks","AgentCacheInitializeTask.invoke","finished init agent info to cache");


        }catch (Exception ex){
            logService.erorLog(logger,"tasks","AgentCacheInitializeTask.invoke","init agent to cache fail",ex);
        }
    }
}
