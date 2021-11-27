package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.FightSituationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:作战情况 数据库持久层服务
 */
@Repository
public interface FightSituationRepository extends JpaRepository<FightSituationEntity, String> {

    /**
     * 根据 警情id(案件id)获得 最新作战情况
     *
     * @param incidentId 警情id(案件id)
     * @return 作战情况列表
     */
    @Query("  select t from FightSituationEntity t  where  t.valid = 1 and t.incidentId = ?1  order by t.updatedTime desc      ")
    List<FightSituationEntity> findFightSituationByIncidentId(String incidentId);

}
