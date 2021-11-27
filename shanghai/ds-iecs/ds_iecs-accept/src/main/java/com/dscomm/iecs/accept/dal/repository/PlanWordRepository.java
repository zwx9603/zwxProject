package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.PlanWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanWordRepository extends JpaRepository<PlanWordEntity, String> {




    List<PlanWordEntity> findPlanWordCondition(Boolean whetherPage, int page, int size );

    Integer findPlanWordConditionTotal();

    @Query("select t from PlanWordEntity t where t.valid=1 and t.keyUnitId=?1")
    List<PlanWordEntity> findPlanWordByKeyUnitId(String keyUnitId);

    @Query("select t from PlanWordEntity t where t.valid=1 and t.id=?1")
    PlanWordEntity findPlanWordById(String id);
}
