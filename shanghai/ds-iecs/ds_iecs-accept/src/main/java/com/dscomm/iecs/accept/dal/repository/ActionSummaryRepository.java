package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.ActionSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionSummaryRepository extends JpaRepository<ActionSummaryEntity, String> {


    @Query("select t from ActionSummaryEntity t where t.valid=1 and t.incidentId=?1")
    List<ActionSummaryEntity> findActionSummary(String incidentId);

    @Query("select t from ActionSummaryEntity t where t.valid=1 and t.id=?1")
    ActionSummaryEntity findActionSummaryById(String id);
}
