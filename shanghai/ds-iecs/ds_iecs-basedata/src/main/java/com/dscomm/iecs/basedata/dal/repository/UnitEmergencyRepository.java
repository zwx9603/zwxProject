package com.dscomm.iecs.basedata.dal.repository;


import com.dscomm.iecs.basedata.dal.po.UnitEmergencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:应急联动单位 数据库持久层服务
 *
 */
@Repository
public interface UnitEmergencyRepository extends JpaRepository<UnitEmergencyEntity, String> {
    List<UnitEmergencyEntity> findUnitEmergencyCondition(String keyword, String districtCode, String squadronOrganizationSearchPath, Boolean whetherPage, int page, int size);

    Integer findUnitEmergencyTotal(String keyword, String districtCode, String squadronOrganizationSearchPath);
}
