package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HandleOrganizationVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HandleOrganizationVehicleSHRepository extends JpaRepository<HandleOrganizationVehicleEntity, String> {


    /**根据车辆id，时间节点获取派车信息*/
    @Query("select v.radioCallSign,c.incidentId,c.noticeTime,c.returnTime from HandleOrganizationVehicleEntity c,EquipmentVehicleEntity v where " +
            "c.vehicleId = v.id and c.valid = 1 and v.valid = 1 " +
            "and v.radioCallSign in ?1 and c.noticeTime>?2 and (c.returnTime<?3 or c.returnTime is null )")
    List<Object[]> findHandleOrganizationVehicleByCarAndTime(List<String> radioCallSignList, Long starTime, Long endTime);

}
