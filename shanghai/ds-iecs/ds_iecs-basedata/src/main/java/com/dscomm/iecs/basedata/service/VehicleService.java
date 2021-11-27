package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;

import java.util.List;
import java.util.Set;

/**
 * 描述：车辆服务
 */
public interface VehicleService {


    /**
     * 描述：根据条件获得车辆信息(分页)
     *
     * @return 返回结果
     */
    PaginationBean<EquipmentVehicleBean> findEquipmentVehicleCondition(EquipmentVehicleQueryInputInfo inputInfo);



    /**
     * 描述：根据机构id查询车辆信
     *
     * @return 返回结果
     */
    List<EquipmentVehicleBean> findEquipmentVehicleByOrganizationId( String  organizationId );

    /**
     * 描述：根据车辆id查询车辆信息
     * 缓存获取
     * @param vehicleId 车辆id
     * @return 车辆信息Bean
     */
    EquipmentVehicleBean findVehicle(String vehicleId);


    /**
     * 描述：根据车辆ids查询车辆信息
     * 缓存获取
     * @param vehicleIds 车辆id
     * @return 车辆信息Bean
     */
    List<EquipmentVehicleBean> findVehicleByIds( List<String> vehicleIds );


    /**
     * 描述：根据车辆ids 车辆状态  查询车辆信息
     * 缓存获取
     * @return 车辆信息Bean
     */
    List<EquipmentVehicleBean> findVehicleByIdsAndVehicleStatus ( List<String> vehicleIds ,  List<String> vehicleStatusList );


    /**
     * 描述：根据车辆车牌号查询车辆信息
     * 缓存获取
     * @param vehicleNumber 车辆车牌号
     * @return 车辆信息Bean
     */
    EquipmentVehicleBean findVehicleNumber(String vehicleNumber);



    /**
     * 根据车辆id 修改车辆状态
     * @return
     */
    Boolean updateVehicleStatus( String vehicleId, String vehicleStatusCode );




    /**
     * 描述：修改车辆拓展信息
     *
     * @param inputInfo 车辆拓展信息参数
     * @return 车辆信息
     */
    EquipmentVehicleBean updateVehicleExpandInfo(EquipmentVehicleExpandInputInfo inputInfo);


    /**
     * 根据车辆拓展字典 与 泡沫类型 查询车辆信息
     * @return
     */
    Set<String> findVehicleIdsByExpandCondition(EquipmentVehicleExpandQueryInputInfo inputInfo );



    /**
     * 更新 车辆缓信息 ( 缓存基本信息)
     */
    void forceUpdateCacheVehicle() ;

    /**
     * 描述：根据车辆id列表 查询车辆信息列表 ( 缓存 )
     *
     * @param vehicleId 车辆id列表
     * @return 车辆信息Bean列表
     */
     EquipmentVehicleBean  findVehicleCache(  String vehicleId );


    /**
     * 描述：根据车辆id列表 查询车辆信息列表 ( 缓存 )
     *
     * @param vehicleIds 车辆id列表
     * @return 车辆信息Bean列表
     */
    List<EquipmentVehicleBean> findVehicleCacheList(List<String> vehicleIds);


    /**
     * 根据车辆ids 获得车牌号 ( 缓存 )
     * @return
     */
    List<String>  findVehicleNumberCacheByIds (List<String>  vehicleIds );


    /**
     * 根据车辆ids 获得车架号 （ 缓存 )
     * @return
     */
    List<String>  findframeNoCacheByIds (List<String>  vehicleIds );



}
