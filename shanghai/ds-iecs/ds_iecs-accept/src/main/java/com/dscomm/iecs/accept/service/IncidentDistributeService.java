package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;

/**
 * 描述:警情分配服务
 *
 * @author YangFuxi
 * Date Time 2019/9/20 8:35
 */
public interface IncidentDistributeService {
    /**
     * 警情分配
     *
     * @param incidentBean 警情信息
     */
    void distribute( IncidentBean incidentBean );

    /**
     * 判断是否可以接受分配该警单
     *
     * @param incidentBean 警情信息
     * @param agent     坐席信息
     * @return 返回判断结果
     */
    Boolean judgeDistribute( IncidentBean  incidentBean  , AgentBean agent);

    /**
     * 接处分开分配策略漏管检查
     *
     * @param agent
     */
    String missDispatchCheck(AgentBean agent);



    /**
     * 接处分开分配策略漏管检查
     */
    void missDispatchCheckForMonitor();

}
