package com.dscomm.iecs.garage.service;


import com.dscomm.iecs.garage.service.bean.*;

import java.util.List;

/**
 * 描述:统计 服务接口
 *
 */
public interface StatisticsService {

    /**
     * 统计车辆状态数
     * @param organizationId 机构id
     * @param whetherOnlySquadron 是否只查询本机构，  true：只查询本机构、false：查询机构id及其辖区
     * @return 统计信息
     */
    DimensionAssembleNestingStatisticsBean findStatisticsVehicleStatus(String organizationId, Boolean whetherOnlySquadron);

    /**
     * 根据机构id获取车辆详情
     * @param organizationId 机构id
     * @return 车辆车库门详情
     */
    List<EquipmentVehicleBean> findVehicle(String organizationId);



    /**
     * 统计车辆数量
     * @param organizationId 机构id
     * @return 统计信息
     */
    List<VehicleNumBean> findStatisticsVehicleNum(String organizationId );

    /**
     * 根据开始时间和结束时间查询 获得出动车辆时间趋势图
     *
     * @return 出动车辆时间趋势
     */
    List<TimeTrendBean> findStatisticsVehicleOnDutyTrend(  String organizationId ,   String timeType  ,  int time    );


    /**
     * 根据开始时间和结束时间查询 获得出动车辆时间趋势图
     *
     * @return 出动车辆时间趋势
     */
    List<VehicleNumBean> findStatisticsOrganizationGoOutAvgTime(  String organizationId  , int type  ,int top   );


    /**
     * 获取机构详情
     * @param organizationId 机构id
     * @return 机构详情
     */
    OrganizationBean findOrganization(String organizationId);

    /**
     * 获取最新警情
     * @param organizationId 机构id
     * @param limit 前limit条
     * @return 案件详情列表
     */
    List<IncidentBean> findLatestIncident(String organizationId,Integer limit);

    /**
     * 根据 机构id 获得辖区中队 出动车辆排行
     * @param organizationId 机构id
     * @param type 排行类别 1 本周数据 2 为本月数据 其他默认本周
     * @param limit 前limit
     * @return 出动车辆排行
     */
    List<VehicleNumBean> findStatisticsDispatchVehicleCount(  String organizationId , Integer type,Integer limit);


}
