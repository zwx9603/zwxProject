package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.MajorIncidentRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 重大案件判定规则 数据库持久层服务
 */
@Repository
public interface MajorIncidentRuleRepository extends JpaRepository<MajorIncidentRuleEntity, String> {
    @Query(" select mar from MajorIncidentRuleEntity  mar where mar.valid = 1 ")
    List<MajorIncidentRuleEntity> findAllMajor();

    @Modifying
    @Query(" update MajorIncidentRuleEntity  set valid =0 where id in ?1")
    void deleteMajorIncidentRule(List<String> ids);

    List<MajorIncidentRuleEntity> findMajorIncidentRule(String describe, String incidentType, int priority, String ruleType, Boolean whetherPage, int page, int size);

    Integer findMajorIncidentRuleTotal(String describe, String incidentType, int priority, String ruleType);
    @Query(" select mar from MajorIncidentRuleEntity  mar where mar.valid = 1 and mar.ruleType = ?1")
    MajorIncidentRuleEntity getByRuleType(String ruleType);
}
