package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.VehicleIncidentStatusMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述： 车辆案件状态关系 车辆状态对案件状态的影响 数据库持久层服务
 *
 */
@Repository
public interface  VehicleIncidentStatusMappingRepository extends JpaRepository<VehicleIncidentStatusMappingEntity, String> {


    /**
     * 根据案件类型 车辆状态  获得车辆案件状态关系
     *
     * @param incidentTypeCode  案件类型
     * @param vehicleStatusCode 车辆状态
     * @return 标签列表
     */
    @Query(" select t from VehicleIncidentStatusMappingEntity t   where  t.valid = 1   and t.incidentTypeCode = ?1  and t.vehicleStatusCode = ?2     " )
    List<VehicleIncidentStatusMappingEntity> findVehicleIncidentStatusMapping ( String incidentTypeCode , String vehicleStatusCode );


}
