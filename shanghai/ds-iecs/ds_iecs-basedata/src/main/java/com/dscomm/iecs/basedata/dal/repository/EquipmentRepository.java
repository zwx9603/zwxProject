package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.EquipmentEntity;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleLoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 描述： 装备（消防设备） 数据底层服务
 */
@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, String> {

    @Query(" select max (t.updatedTime) from EquipmentVehicleLoadEntity t        ")
    Long findDataLatestTime(  Long lastTime ) ;

    @Query(" select t.id ,  t.vehicleId , t.equipmentTypeCode    from EquipmentVehicleLoadEntity t  " +
            " where   t.valid = 1  and  t.updatedTime >?1 and t.updatedTime <=?2  ")
    List<Object[]>   findDataLatestTime(Long lastTime , Long  latestTime ) ;


    /**
     * 根据车辆id获得 车辆装载 设备信息
     */
    @Query(" select t  from EquipmentVehicleLoadEntity t   where t.valid = 1  and  t.id in ( ?1  )   " )
    List<EquipmentVehicleLoadEntity> findEquipmentVehicleLoadByIds( List<String> ids  );

    /**
     * 根据车辆id获得 车辆装载 设备信息
     */
    @Query(" select t  from EquipmentVehicleLoadEntity t   where t.valid = 1  and  t.vehicleId = ?1   " )
    List<EquipmentVehicleLoadEntity> findEquipmentVehicleLoadByVehicleId( String vehicleId  );


    /**
     * 根据车辆ids获得 车辆装载 设备信息
     */
    @Query(" select t  from EquipmentVehicleLoadEntity t   where t.valid = 1  and  t.vehicleId in (?1)   " )
    List<EquipmentVehicleLoadEntity> findEquipmentVehicleLoadByVehicleIds( List<String> vehicleIds  );




    /**
     * 根据条件获取所有 装备(可分页)
     *
     * @param searchPath           机构查询码
     * @param organizationIds      装备器材所在机构编码集合
     * @param keyword              模糊匹配关键字（装备器材名称模糊）
     * @param equipmentStatusCodes 装备器材状态编码集合
     * @param equipmentTypeCodes   装备器材类型编码集合
     * @param whetherPage          是否分页查询 默认分页
     * @param page                 页数
     * @param size                 每页数量
     * @return 装备列表
     */
    List<EquipmentEntity> findEquipmentCondition(String searchPath, String keyword,
                                                 List<String> organizationIds, List<String> equipmentStatusCodes,
                                                 List<String> equipmentTypeCodes, Boolean whetherPage, Boolean whetherConsumptiveEquipment,
                                                 int page, int size);

    /**
     * 根据条件获取装备总数
     *
     * @param searchPath           机构查询码
     * @param organizationIds      装备器材所在机构编码集合
     * @param keyword              模糊匹配关键字（装备器材名称模糊）
     * @param equipmentStatusCodes 装备器材状态编码集合
     * @param equipmentTypeCodes   装备器材类型编码集合
     * @return 装备总数
     */
    Integer findEquipmentConditionTotal(String searchPath, List<String> organizationIds, String keyword,
                                        List<String> equipmentStatusCodes, List<String> equipmentTypeCodes, Boolean whetherConsumptiveEquipment);

}
