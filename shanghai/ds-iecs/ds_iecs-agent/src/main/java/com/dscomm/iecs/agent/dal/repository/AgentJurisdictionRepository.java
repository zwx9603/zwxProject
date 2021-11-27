package com.dscomm.iecs.agent.dal.repository;

import com.dscomm.iecs.agent.dal.po.DistributeStrategy.AgentJurisdictionPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Zhuqihong
 * Date Time 2019/10/29
 */
@Repository
public interface AgentJurisdictionRepository extends JpaRepository<AgentJurisdictionPO, String> {

    /**
     * 删除处警台与辖区绑定关系
     *
     */
    @Modifying
    @Query("delete  from AgentJurisdictionPO t where t.agentNum=?1 and t.jurisdictionId=?2")
    void deleteJurisdictionForAgent(Integer agentNum, String jurisdictionNum);

    /**
     * 根据坐席号跟辖区号获取绑定关系
     * @param agentNum 坐席号
     * @param jurisdictionNum 辖区号
     * @return 返回查询结果
     */
    @Query("select t from AgentJurisdictionPO t where t.agentNum=?1 and t.jurisdictionId=?2")
    AgentJurisdictionPO getByAgentNumAndJurisdictionId(Integer agentNum, String jurisdictionNum);

}
