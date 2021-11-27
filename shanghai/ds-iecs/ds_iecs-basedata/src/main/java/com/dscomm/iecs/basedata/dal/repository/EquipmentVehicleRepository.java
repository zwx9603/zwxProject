package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * 描述： 车辆的数据底层服务
 */
@Repository
public interface EquipmentVehicleRepository extends JpaRepository<EquipmentVehicleEntity, String> {


    @Query(" select max (t.updatedTime) from EquipmentVehicleEntity t        ")
    Long findDataLatestTime(  Long lastTime ) ;

    @Query(" select max (t.updatedTime) from VehicleGarageMappingEntity t         ")
    Long findDataLatestTime1(  Long lastTime ) ;

    @Query(" select t.id  from EquipmentVehicleEntity t  where  t.updatedTime >?1 and t.updatedTime <=?2  ")
    List<String>   findDataLatestTime(Long lastTime , Long  latestTime ) ;

    @Query(" select t.mappingObjectId  from VehicleGarageMappingEntity t  where  t.updatedTime >?1 and t.updatedTime <=?2  ")
    List<String>   findDataLatestTime1(Long lastTime , Long  latestTime ) ;


    /**
     *
     * @param orgId 机构id
     * @param orgSearchPath 机构查询码只有在cascade=true时才生效
     * @param cascade 是否级联查询
     * @param keyword 关键字
     * @param vehicleTypeCodes 车辆类型
     * @param vehicleStatusCodes 车辆状态
     * @param vehicleId 车辆id
     * @param vehicleIds 车辆id集合
     * @param vehicleNumber 车牌号
     * @param natureLike 车辆性质
     * @param page 页码
     * @param size 每页数量
     * @return 返回结果
     */
    List<Object [] > findEquipmentVehicleCondition(String orgId, String orgSearchPath,Boolean cascade,String keyword, List<String> vehicleTypeCodes,
                                                   List<String> vehicleStatusCodes,
                                                   String vehicleId ,List<String> vehicleIds , String  vehicleNumber ,
                                                   String  natureLike , int page, int size);

    /**
     * 根据条件获取车辆总数
     *
     * @param orgId
     * @param orgSearchPath         机构查询码
     * @param keyword            关键字
     * @param vehicleTypeCodes   车辆类型代码 列表
     * @param vehicleStatusCodes 车辆状态代码 列表
     * @return 车辆信息列表
     */
    Integer findEquipmentVehicleConditionTotal(String orgId, String orgSearchPath,Boolean cascade,String keyword, List<String> vehicleTypeCodes,
                                               List<String> vehicleStatusCodes,
                                               String vehicleId ,List<String> vehicleIds , String  vehicleNumber ,
                                               String  natureLike );




    /**
     * 根据车辆拓展属性获得车辆id
     *
     * @return 返回结果
     */
    List<String> findVehicleIdByExpandCondition  (
              String  keyword ,
              Float maxNum1 ,  //载液量 高值
              Float minNum1,   //载液量 低值
              Float maxNum2 ,   //泵流量 高值
              Float minNum2 ,   //泵流量 低值
              Float maxNum3 ,  //举升高度 高值
              Float minNum3 ,   //举升高度 低值
              Float maxNum4 ,   //消防炮流量 高值
              Float minNum4 ,  //消防炮流量 低值
              Float maxNum5 ,   //牵引力 高值
              Float minNum5 ,   //牵引力 低值
              Float maxNum6 ,   //吊机 高值
              Float minNum6     //吊机 低值
  );


    /**
     * 车辆信息缓存
     *
     * @return 返回结果
     */
    @Query( "  select vehicle   from EquipmentVehicleEntity vehicle  " +
            "  where vehicle.id  in  ( ?1 ) " )
    List<EquipmentVehicleEntity> findEquipmentVehicleByIds  ( List<String> vehicleIds  );

    /**
     *  根据车辆ids  车辆状态 查询车辆信息
     *
     * @return 返回结果
     */
    @Query( "  select vehicle   from EquipmentVehicleEntity vehicle  " +
            "  where vehicle.id  in  ( ?1 ) and  vehicle.vehicleStatusCode in ( ?2 ) " )
    List<EquipmentVehicleEntity> findVehicleByIdsAndVehicleStatus  ( List<String> vehicleIds  ,  List<String> vehicleStatusList );

    /**
     * 查询指定单位的可用车辆
     * @param orgSearchPath 单位查询码路径
     * @param status 车辆状态集合
     * @return 返回值
     */
    @Query("select t.organizationId,t.id,t.vehicleTypeCode from EquipmentVehicleEntity t where t.valid=1 and t.organizationId in (select o.id from OrganizationEntity o where o.valid=1 and o.searchPath like ?1% ) and t.vehicleStatusCode in ?2 and t.vehicleTypeCode in ?3")
    List<Object[]> findAvailableVehicleByOrgSearchPathAndVehicleStatus(String orgSearchPath,List<String> status,Set<String> vehicleTypes);


}
