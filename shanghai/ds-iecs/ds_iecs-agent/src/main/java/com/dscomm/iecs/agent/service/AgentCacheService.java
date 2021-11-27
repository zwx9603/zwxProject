package com.dscomm.iecs.agent.service;

import com.dscomm.iecs.agent.graphql.typebean.AgentBean;

import java.util.Map;

/**
 * 描述:坐席缓存服务
 *
 * @author YangFuxi
 * Date Time 2019/8/9 8:49
 */
public interface AgentCacheService {
    /**
     * 坐席缓存管理
     *
     * @param operation      操作类型 put，delete
     * @param bo             坐席信息
     * @param cacheKeyPrefix 缓存前缀
     * @param transmit       是否传递 用于通知其他节点是否同步更新缓存,一般应设置为true
     * @return 返回更新后的所有缓存
     */
    Map<String, AgentBean> mergeAgentCache(String operation, AgentBean bo, String cacheKeyPrefix, Boolean transmit);

    /**
     * 获取所有缓存
     *
     * @param cacheKeyPrefix 缓存前缀
     * @return 坐席缓存
     */
    Map<String, AgentBean> findAllAgentCache(String cacheKeyPrefix);

    /**
     * 清空所有缓存
     *
     * @param cacheKeyPrefix 缓存前缀
     * @return 坐席缓存
     */
    Map<String, AgentBean> clearAllAgentCache(String cacheKeyPrefix);


    /**
     * 根据坐席台号获取坐席
     *
     * @param agentNumber 坐席台号
     * @return 坐席缓存
     */
    AgentBean findAgentByAgentNumber(String agentNumber);

}
