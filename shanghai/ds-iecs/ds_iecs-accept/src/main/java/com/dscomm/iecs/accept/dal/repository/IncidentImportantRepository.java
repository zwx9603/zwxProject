package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.IncidentImportantRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述：重要警情规则 数据层
 */
public interface IncidentImportantRepository extends JpaRepository<IncidentImportantRuleEntity, String> {
    /**
     * 根据案件类型，返回重要警情规则列表
     * @return 返回查询结果
     */
    @Query("select t from IncidentImportantRuleEntity t where t.valid = 1 and t.incidentTypeCode = ?1 order by t.updatedTime desc ")
    List<IncidentImportantRuleEntity> findIncidentImportant(String incidentType);

}
