package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述:坐席数据服务层
 *
 * @author YangFuxi
 * Date Time 2019/8/31 11:23
 */
public interface AgentRepository extends JpaRepository<AgentEntity,String> {
    /**
     * 根据ip匹配坐席
     * @param ips ip信息
     * @return 返回坐席信息
     */
    @Query("select t from AgentEntity t where t.ip in ?1 and length(t.ip)>=7 ")
    AgentEntity findAgentByIp(String[] ips);

    /**
     * 根据 太好匹配坐席
     * @param agentNumber ip信息
     * @return 返回坐席信息
     */
    @Query("select t from AgentEntity t where t.agentNumber = ?1 and length(t.ip)>=7 ")
    AgentEntity findAgentByAgentNumber(String  agentNumber );


    /**
     * 坐席
     *
     * @return 数据列表
     */
    @Query("select t from AgentEntity t order by t.agentNumber asc  ")
    List<AgentEntity> findAllZx();


    /**
     * 获得全部坐席台号信息
     *
     * @return 数据列表
     */
    @Query("select t.agentNumber from AgentEntity t order by t.agentNumber asc  ")
    List<String> findAllAgentNumber();

}
