package com.dscomm.iecs.agent.dal.repository;

import com.dscomm.iecs.agent.dal.po.DistributeStrategy.AgentIncidentTypePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 描述:案由坐席repository
 *
 * @author YangFuxi
 * Date Time 2019/11/21 15:33
 */
@Repository
public interface AgentIncidentTypeRepository extends JpaRepository<AgentIncidentTypePO, String> {
    /**
     * 根据案由和坐席查询
     *
     * @param ay    案由
     * @param agent 坐席
     */
    @Query("select t from AgentIncidentTypePO t where t.incidentType=?1 and t.agentNum=?2")
    AgentIncidentTypePO findByAyAndAgentNum(String ay, Integer agent);
}
