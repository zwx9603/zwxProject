package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.VehicleGarageMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:中队车辆与车库门对应关系 数据库持久层服务
 */
@Repository
public interface VehicleGarageMappingRepository extends JpaRepository<VehicleGarageMappingEntity, String> {

    /**
     * 根据条件获得车辆与车库关系
     *
     * @param organizationIds  机构id集合
     * @param mappingTypes     关系类型集合
     * @param mappingObjectIds 车辆id集合
     * @param mappingGroupIds  车库id集合
     * @return 车辆与车库关系列表
     */
    List<VehicleGarageMappingEntity> findVehicleGarageMappingCondition(List<String> organizationIds, List<String> mappingTypes,
                                                                       List<String> mappingObjectIds, List<String> mappingGroupIds);

}
