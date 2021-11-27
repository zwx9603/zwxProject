package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.IncidentImportantRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述：重要警情规则 数据层
 */
public interface IncidentImportantRuleRepository extends JpaRepository<IncidentImportantRuleEntity, String> {


    @Query(" select t from IncidentImportantRuleEntity t where t.valid=1 order by  t.incidentTypeCode asc , t.whetherEnable desc , t.updatedTime desc   ")
    List<IncidentImportantRuleEntity> findIncidentImportant ();


    @Query(" select t from IncidentImportantRuleEntity t where t.valid=1 and t.whetherEnable = 1  ")
    List<IncidentImportantRuleEntity> findIncidentImportantEnable();


    @Query(" select t from IncidentImportantRuleEntity t where t.valid=1 and t.whetherEnable = 1   and t.incidentTypeCode =?1 ")
    List<IncidentImportantRuleEntity> findIncidentImportantEnable(   String incidentTypeCode );




}
