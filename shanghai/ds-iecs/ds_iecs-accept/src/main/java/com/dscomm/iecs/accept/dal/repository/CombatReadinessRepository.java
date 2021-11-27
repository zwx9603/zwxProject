package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.CombatReadinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombatReadinessRepository extends JpaRepository<CombatReadinessEntity, String> {

    @Query("select t from CombatReadinessEntity t  where t.type=?1 and t.showEndTime>=?2 and t.showStartTime<?3")
    List<CombatReadinessEntity> findCombatReadiness(String type , Long showStartTime , Long showEndTime);
}
