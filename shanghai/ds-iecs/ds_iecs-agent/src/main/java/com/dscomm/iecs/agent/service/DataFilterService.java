package com.dscomm.iecs.agent.service;

import com.dscomm.iecs.agent.graphql.typebean.AgentBean;

import java.util.List;
import java.util.Map;

/**
 * 描述:数据过滤服务
 *
 * @author YangFuxi
 * Date Time 2020/1/3 10:48
 */
public interface DataFilterService {
    /**
     * 分权分域获取全部坐席列表(向下)
     *
     * @return 坐席集合
     */
    Map<String,AgentBean> findAllOnlineAgentByAuthorization();

    /**
     * 多条件获取在线用户账号
     * @param orgIds 机构集合,不传不过滤
     * @param direction 机构递归方向  0不递归 1向上递归（包含自身） 2向下递归（包含自身）
     * @param roles 人员角色 不传不过滤
     * @param incidentTypes 警种类型 不传不过滤
     * @return 返回账号列表
     */
    List<String> findAllOnlineAccountByCondition(List<String> orgIds, String direction,List<Integer> roles,List<String> incidentTypes);
}
