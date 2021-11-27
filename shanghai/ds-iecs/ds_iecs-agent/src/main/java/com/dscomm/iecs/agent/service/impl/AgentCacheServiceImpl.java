package com.dscomm.iecs.agent.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.SafetyRedLineUtils;
import com.dscomm.iecs.basedata.service.NotifyNotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:坐席缓存管理
 *
 * @author YangFuxi
 * Date Time 2019/8/9 9:40
 */
@CacheConfig(cacheNames = "agents")
@Component
public class AgentCacheServiceImpl implements AgentCacheService {
    private static final Logger logger = LoggerFactory.getLogger(AgentCacheServiceImpl.class);
    private LogService logService;
    private static Map<String, AgentBean> cache = new ConcurrentHashMap<>();
    private NotifyNotesService notifyNotesService;

    @Autowired
    public AgentCacheServiceImpl(LogService logService, NotifyNotesService notifyNotesService) {
        this.logService = logService;
        this.notifyNotesService = notifyNotesService;
    }

    /**
     * {@inheritDoc}
     *
     * @see #mergeAgentCache(String, AgentBean, String, Boolean)
     */
    @Override
    public Map<String, AgentBean> mergeAgentCache(String operation, AgentBean bo, String cacheKeyPrefix, Boolean transmit) {
        try {
            Object logInfo=SafetyRedLineUtils.transformObjectForSafety(bo);
            logService.infoLog(logger, "cache service", "mergeAgentCache", String.format("the requers data:'operation':%s,info:%s", operation, JSONObject.toJSONString(logInfo)));
            if ("put".equals(operation)) {
                cache.put(bo.getAgentNumber(), bo);
            } else if ("delete".equals(operation)) {
                cache.remove(bo.getAgentNumber());
            }
            String path="rest/iecs/v1.0/notifyOtherNodes/agentCache/"+operation;
            notifyNotesService.notifyNodes(path,transmit,bo);
            return cache;
        } catch (Exception ex) {
            logService.erorLog(logger, "cache service", "mergeAgentCache", String.format("merge cache fail,operation " +
                    "type is:%s,agentNum is :%s,the exception is: ", operation, bo.getAgentNumber()), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AGENT_CACHE_OPERATE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAllAgentCache(String)
     */
    @Override
    public Map<String, AgentBean> findAllAgentCache(String cacheKeyPrefix) {
        try {
            logService.infoLog(logger, "service", "findAllAgentCache", "now start get all agent cache.");
            return cache;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllAgentCache", "get all agent cache fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AGENT_CACHE_GET_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #clearAllAgentCache(String)
     */
    public Map<String, AgentBean> clearAllAgentCache(String cacheKeyPrefix) {
        try {
            logService.infoLog(logger, "service", "clearAllAgentCache", "now start clear all agent cache.");
            cache.clear();
            return cache;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "clearAllAgentCache", "clear all agent cache fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AGENT_CACHE_CLEAR_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAgentByAgentNumber(String)
     */
    @Override
    public AgentBean findAgentByAgentNumber(String agentNumber) {
        try {
            logService.infoLog(logger, "service", "findAgentByAgentNumber", "now start get all agent cache.");


            return cache.get(agentNumber);
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAgentByAgentNumber", "get all agent cache fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AGENT_CACHE_GET_FAIL);
        }
    }

}
