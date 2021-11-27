package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.UnitJointServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:联勤保障单位 数据库持久层服务
 *
 */
@Repository
public interface UnitJointServiceRepository extends JpaRepository<UnitJointServiceEntity, String> {
    List<UnitJointServiceEntity> findUnitJointServiceCondition(String keyword, String districtCode, String squadronOrganizationSearchPath, Boolean whetherPage, int page, int size);
    Integer findUnitJointServiceTotal(String keyword, String districtCode, String squadronOrganizationSearchPath);
}
