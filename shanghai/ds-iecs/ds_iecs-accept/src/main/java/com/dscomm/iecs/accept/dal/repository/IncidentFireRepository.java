package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.dal.po.IncidentStatusChangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 案件底层服务
 */
@Repository
public interface IncidentFireRepository extends JpaRepository<IncidentEntity, String> {


    /**
     * 获取警情状态变更记录
     *
     * @param incidentId 警情ID
     * @param statusCode 警情状态
     * @return 返回案件状态变记录
     */
    @Query("select i from IncidentStatusChangeEntity i where i.incidentId=?1 and cast(i.incidentStatusCode as integer)<?2 and i.valid=1")
    List<IncidentStatusChangeEntity> findAllStatusByIncidentId(String incidentId, Integer statusCode);


    /**
     * 警情回溯警情基本信息
     *
     * @param incidentId 警情ID
     * @return 返回基本西南西
     */
    @Query("select incident.incidentTypeCode,incident.registerIncidentTime,"
            + "incident.crimeAddress,incident.incidentLevelCode,incident.alarmModeCode,incident.disasterSites,"
            + "incident.smogSituationCode,incident.buildingStructureCode,incident.trappedCount,incident.burningArea,"
            + "incident.burningFloor,incident.disposalObjectCode,incident.storeysOfBuildings ,incident.squadronOrganizationId," +  //主管机构--主管中队
            "incident.longitude,incident.latitude,incident.districtCode ,incident.supplementInfo,alarm.alarmStartTime," +
            "alarm.alarmModeCode,tel.alarmPersonName,tel.alarmPersonPhone,tel.relayRecordNumber,org.organizationName,"
            + "incident.incidentStatusCode,incident.injuredCount,incident.deathCount,incident.incidentDescribe , " +     //主要情况-案件描述
            "k.unitName,k.securityPerson,k.securityPersonPhone,k.unitLevelCode ,command.id,command.initiatorName,p.mobilePhone "
            + " from IncidentEntity incident left join AcceptanceEntity alarm on alarm.id=incident.acceptanceId " + //受理单id
            "and alarm.incidentId=incident.id and alarm.valid=1 left join TelephoneEntity tel on" +
            " tel.acceptanceId=alarm.id and tel.valid=1 left join KeyUnitEntity k on k.id=incident.keyUnitId and k.valid=1 " +
            " left join  CommandEntity command on command.incidentId=incident.id and command.endTime is null and" +
            " command.valid=1 left join PersonMailEntity p on p.personId=command.initiatorId  " +
            " left join OrganizationEntity org on  org.id=incident.squadronOrganizationId and org.valid=1 where incident.id=?1 and incident.valid=1 ")
    List<Object[]> incidentReplayBaseInfo(String incidentId);

    /**
     * 查询指挥信息
     *
     * @param incidentId 事件ID
     * @return 返回指挥信息
     */
    @Query("select c.id,c.startTime from CommandEntity c where c.incidentId=?1 and c.valid=1")
    List<Object[]> findCommandListByIncident(String incidentId);

    /**
     * 查询包围圈变动记录
     *
     * @param incidentId 事件ID
     * @return 返回包围圈列表
     */
    @Query("select s.createdTime,s.radius from IncidentCircleEntity s where s.incidentId=?1 and s.valid=1")
    List<Object[]> findAllSurroundingCircles(String incidentId);

    /**
     * 车辆状态变动记录表
     *
     * @param incidentId 案件ID
     * @return 返回状态变动记录表
     */
    @Query("select vs.vehicleId, vs.vehicleStatusCode,vs.changeTime,v.vehicleName,v.vehicleNumber,v.locationNumber from VehicleStatusChangeEntity vs ," +
            "EquipmentVehicleEntity v where vs.incidentId=?1 and vs.valid=1 and v.id=vs.vehicleId and v.valid=1")
    List<Object[]> findVehicleStatusByIncident(String incidentId);




}
