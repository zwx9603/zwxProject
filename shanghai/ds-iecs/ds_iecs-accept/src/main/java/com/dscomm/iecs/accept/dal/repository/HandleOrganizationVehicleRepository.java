package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HandleOrganizationVehicleEntity;
import com.dscomm.iecs.accept.dal.po.VehicleStatusChangeEntity;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:调派单位车辆信息 （ 作战车辆信息 ） 数据库持久层服务
 */
@Repository
public interface HandleOrganizationVehicleRepository extends JpaRepository<HandleOrganizationVehicleEntity, String> {

    /**
     * 根据案件ID获取 调派单位 信息（ 处警信息 ）
     *
     * @param incidentId 案件ID
     * @return 调派单位装备信息（ 作战车辆信息 ） 列表
     */
    @Query(" select handleOrganizationVehicle , handle  from HandleOrganizationVehicleEntity handleOrganizationVehicle " +
            " left join HandleEntity handle on handle.id = handleOrganizationVehicle.handleId  and handle.valid = 1   " +
            " where  handleOrganizationVehicle.valid = 1 and handleOrganizationVehicle.incidentId = ?1 " +
            " order by   handle.handleBatch  asc ,  handle.handleStartTime asc  , " +
            "  handleOrganizationVehicle.organizationId asc ,   handleOrganizationVehicle.vehicleTypeCode asc   ")
    List<Object[]> findHandleOrganizationVehicleByIncidentId(String incidentId);


    /**
     * 根据案件ID获取 调派单位 信息（ 处警信息 ）
     *
     * @param incidentId 案件ID
     * @return 调派单位装备信息（ 作战车辆信息 ） 列表
     */
    @Query(" select handleOrganizationVehicle , handle  from HandleOrganizationVehicleEntity handleOrganizationVehicle " +
            " left join HandleEntity handle on handle.id = handleOrganizationVehicle.handleId  and handle.valid = 1   " +
            " where  handleOrganizationVehicle.valid = 1 and handleOrganizationVehicle.incidentId = ?1  and handleOrganizationVehicle.organizationId = ?2 " +
            " order by    handle.handleBatch  asc ,  handle.handleStartTime asc  , " +
            "  handleOrganizationVehicle.organizationId asc ,   handleOrganizationVehicle.vehicleTypeCode asc  ")
    List<Object[]> findHandleOrganizationVehicleByIncidentIdAndOrganizationId(String incidentId  , String organizationId );



     /**
     * 根据处警记录ID获取 调派单位车辆信息（ 作战车辆信息 ）
     *
     * @param handleId 处警记录ID
     * @return 调派单位装备信息（ 作战车辆信息 ） 列表
     */
    @Query(" select t from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.handleId = ?1 order by t.createdTime desc  ")
    List<HandleOrganizationVehicleEntity> findHandleOrganizationVehicleByHandleId(String handleId);


    /**
     * 根据处警记录ID获取 车辆状态变动记录
     *
     * @param handleId 处警记录ID
     * @return 车辆状态变动记录
     */
    @Query(" select t from VehicleStatusChangeEntity t  where  t.valid = 1 and t.handleId = ?1 order by t.updatedTime desc  ")
    List<VehicleStatusChangeEntity> findVehicleStatusChangeByHandleId(String handleId);


    /**
     * 根据案件id  车辆id  查询作战车辆信息
     *
     * @param incidentId 车辆id
     * @param vehicleId  案件id
     * @return 调派单位车辆信息（ 作战车辆信息 ） 列表
     */
    @Query(" select t from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1 and t.handleId = ?2 and t.vehicleId = ?3 order by t.createdTime desc  ")
    List<HandleOrganizationVehicleEntity> findHandleOrganizationVehicleByIncidentIdAndVehicleId(String incidentId ,String handleId ,  String vehicleId);


    /**
     * 根据案件id  车辆id  查询作战车辆信息
     *
     * @param incidentId 车辆id
     * @param vehicleId  案件id
     * @return 调派单位车辆信息（ 作战车辆信息 ） 列表
     */
    @Query(" select t from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1   and t.vehicleId = ?2 order by t.createdTime desc  ")
    List<HandleOrganizationVehicleEntity> findHandleOrganizationVehicleByIncidentIdAndVehicleId( String incidentId   ,  String vehicleId);



    /**
     * 根据案件ID获取 出动车辆 id 列表
     *
     * @param incidentId 案件ID
     * @return 出动车辆 id 列表
     */
    @Query(" select distinct  t.vehicleId   from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1  order by  t.vehicleId desc  ")
    List<String> findVehicleIdByIncidentId(String incidentId);


    /**
     * 根据案件ID获取 出动车辆 id 列表
     *
     * @param incidentId 案件ID
     * @return 出动车辆 id 列表
     */
    @Query(" select distinct  t.vehicleId   from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1  and t.organizationId = ?2  order by  t.vehicleId desc  ")
    List<String> findVehicleIdByIncidentIdAndOrganizationId(String incidentId , String organizationId );

    /**
     * 根据案件ID获取 出动车辆ids
     *
     * @param incidentId 案件ID
     * @return 出动单位id 列表
     */
    @Query(" select distinct  t.vehicleId  from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1  and t.vehicleId in ( ?2 ) and t.vehicleStatusCode not in ( ?3 )  ")
    List<String> findIncidentFightVehicleIds(String incidentId , List<String> vehicle , List<String> vehicleStutsCodes );


    /**
     *  根据车辆id集合  更新车辆案件编号
     *
     * @param vehicleIds 车辆id接好
     * @param incidentId 警情ID
     */
    @Modifying(clearAutomatically = true)
    @Query( "update EquipmentVehicleEntity t set t.incidentNumber=?2 , t.updatedTime = ?3    where t.id in ( ?1 )  and t.valid=1")
    void updateEquipmentVehicleIncidentNumber( List<String>  vehicleIds ,  String incidentId  , Long  currentTime   );


    /**
     * 根据案件id  作战车辆状态编码 查询数量
     *
     * @param incidentId 车辆id
     * @param vehicleStatusCode  车辆状态编码
     * @return 数量
     */
    @Query(" select count(t.id ) from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1 " +
            "and t.vehicleStatusCode = ?2    ")
    Integer findHandleOrganizationVehicleStatusCountNum (String incidentId   ,  String vehicleStatusCode );

    /**
     * 根据案件ID获取 调派单位车辆信息（ 作战车辆信息 ）
     *
     * @param incidentId 案件ID
     * @return 调派单位装备信息（ 作战车辆信息 ） 列表
     */
    @Query(" select t from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1 order by t.createdTime desc  ")
    List<HandleOrganizationVehicleEntity> findHandleOrganizationVehiclesByIncidentId(String incidentId);

    /**
     * 根据车辆id集合获取车辆列表
     * @param vehicleIds 车辆id集合
     * @return 返回结果
     */
    @Query("select t,v.mappingGroupId from EquipmentVehicleEntity t left join VehicleGarageMappingEntity v on v.mappingObjectId=t.id where t.id in ?1")
    List<Object[]> findVehicles(List<String> vehicleIds);

}
