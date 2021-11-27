package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.firetypebean.InjuriesAndDeathsBean;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;

import java.util.List;

/**
 * 警情统计服务
 */
public interface StatisticsService {


    /**
     * 根据条件 统车辆状态信息
     *
     * @param inputInfo      车辆统计查询条件
     * @return
     */
    DimensionAssembleNestingStatisticsBean findVehicleStatusStatistics(VehicleStatisticsQueryInputInfo inputInfo);


    /**
     * 根据条件 统计车辆类型信息
     *
     * @param inputInfo      车辆统计查询条件
     * @return
     */
    DimensionAssembleNestingStatisticsBean findVehicleTypeStatistics( VehicleStatisticsQueryInputInfo inputInfo );

    /**
     * 根据条件 统计机构车辆信息
     *
     * @param inputInfo      车辆统计查询条件
     * @return
     */
    DimensionAssembleNestingStatisticsBean findVehicleOrganizationStatistics( VehicleStatisticsQueryInputInfo inputInfo );

    /**
     * 根据开始时间和结束时间查询 获得警情时间趋势图
     *
     * @return 警情时间趋势
     */
    List<TimeTrendBean> findIncidentTimeTrend(IncidentTrendQueryInputInfo queryBean);


    /**
     * 根据辖区ID集合 警情地址模糊匹配 警情类型 警情等级 警情状态 警情时间段统计警情类型信息
     *
     * @return 警情类型信息
     */
    DimensionAssembleStatisticsBean findIncidentTypeStatistics(IncidentStatisticsQueryInputInfo queryBean);



    /**
     * 根据辖区ID集合 统计处警量信息
     *
     * @return 处警量统计信息
     */
   DimensionAssembleStatisticsBean findHandleStatistics(HandleStatisticsQueryInputInfo queryBean);



    /**
     * 根据 案件id 获得 出动力量 统计信息
     *
     * @param incidentId 案件id
     */
    IncidentPowerStatisticsBean findHandlePowerStatistics(String incidentId    ) ;



    /**
     * 根据辖区ID集合 统计案件同环比（案件类型分类）
     * @param scopeSquadronId 机构id
     * @param scopeType 查询范围辖区类型 0根机构 1非根机构
     * @return 统计结果
     */
    IncidentTypeContrastStatisticsBean findIncidentTypeContrastStatistics(String scopeSquadronId,Integer scopeType) ;


    /**
     * 根据机构id，查询时间段内本机构以及子级机构警情分类统计
     * @param inputInfo 查询条件
     * @return 统计结果
     */
    List<OrganizationDisasterStatisticsBean> findOrganizationDisasterStatistics(IncidentStatisticsQueryInputInfo inputInfo) ;



    /**
     * 辖区警情统计
     * @param startTime 起止时间
     * @param endTime
     * @param districtCode 行政区代码
     * @return
     */
    IncidentDistrictStatisticsBean findIncidentDistrictStatistics( Long startTime, Long endTime, String districtCode);
    /**
     * 伤亡人员统计
     * @param queryBean
     * @return
     */
    List<InjuriesAndDeathsBean> findInjuriesAndDeaths(IncidentTrendQueryInputInfo queryBean);

    /**
     * 统计一段时间内的车辆调度频次
     * @param inputInfo
     * @return 返回结果
     */
    List<StatisticsBean> staticsIncidentVehicles(IncidentVehicleStatisticsInputInfo inputInfo);



}
