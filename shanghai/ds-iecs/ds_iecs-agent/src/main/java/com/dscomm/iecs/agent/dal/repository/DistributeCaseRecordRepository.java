package com.dscomm.iecs.agent.dal.repository;

import com.dscomm.iecs.agent.dal.po.DistributeStrategy.DistributeCaseRecordPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhuqihong
 * Date Time 2019/8/31
 */
@Repository
public interface DistributeCaseRecordRepository extends JpaRepository<DistributeCaseRecordPO, String> {
    /**
     * 根据坐席获取事件单编号集合
     *
     * @param agentNum 坐席号
     * @param distributeStatus 分配状态
     * @return 返回事件单编号集合
     */
    @Query("select t from DistributeCaseRecordPO t where t.distributeTargetStationId=?1 and t.distributeStatus=?2")
    List<String> findAllIncidentIdFromDistributeRecord(String agentNum, Integer distributeStatus);
}
