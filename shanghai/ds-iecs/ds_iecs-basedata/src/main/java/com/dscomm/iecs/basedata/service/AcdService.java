package com.dscomm.iecs.basedata.service;



import com.dscomm.iecs.basedata.graphql.typebean.AcdBean;
import com.dscomm.iecs.basedata.graphql.typebean.AgentAcdBean;
import com.dscomm.iecs.basedata.graphql.typebean.UserAcdBean;

import java.util.List;

/**
 * acd 服务接口
 */
public interface AcdService {


    /**
     * 获取ACD组
     *
     * @return 数据列表
     */
    List<AcdBean> findAllAcd();

    /**
     * 查找用户acd
     *
     * @param userId 用户账号（为空返回全部）
     * @return 用户acd列表
     */
    List<UserAcdBean> findUserAcdBO(String userId);


    /**
     * 查找坐席acd
     *
     * @param agentNumber 坐席台号（为空返回全部）
     * @return 坐席acd列表
     */
    List<AgentAcdBean> findAgentAcdBO(String agentNumber);




}
