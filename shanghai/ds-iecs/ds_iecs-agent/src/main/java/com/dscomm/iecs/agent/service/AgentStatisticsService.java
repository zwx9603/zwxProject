package com.dscomm.iecs.agent.service;

import com.dscomm.iecs.agent.graphql.inputbean.MonitorQueryInputInfo;
import com.dscomm.iecs.agent.graphql.typebean.DimensionAssembleStatisticsBean;
import com.dscomm.iecs.agent.graphql.typebean.KPIStatisticsBean;
import com.dscomm.iecs.agent.graphql.typebean.LogRecordBean;
import com.dscomm.iecs.agent.graphql.typebean.TimeTrendBean;

import java.util.List;

/**
 * 坐席统计服务
 */
public interface AgentStatisticsService {



    /**
     * 根据机构id 获得坐席状态统计数信息
     *
     * @return 坐席状态统计数
     */
    DimensionAssembleStatisticsBean findSeatStatusStatistics(String organizationId);

    /**
     * 根据时间段 辖区机构id 坐席号 人员工号 获得接警量时间趋势
     *
     * @return 接警量时间趋势
     */
    List<TimeTrendBean> findSeatAcceptanceTimeTrend(MonitorQueryInputInfo queryBean);


    /**
     * 根据时间段  辖区机构id 坐席号 人员工号 获得警情量时间趋势
     *
     * @return 警情量时间趋势
     */
    List<TimeTrendBean> findSeatIncidentTimeTrend(MonitorQueryInputInfo queryBean);

    /**
     * 根据时间段  辖区机构id 坐席号 人员工号 获得违规量时间趋势
     *
     * @return 警情量时间趋势
     */
    List<TimeTrendBean> findSeatViolationTrend(MonitorQueryInputInfo queryBean);

    /**
     * 根据时间段 坐席号 人员工号 获得KPI信息包含(接警量、警情量、平均接警时长、平均处警时长、违规量)
     *
     * @return KPI信息
     */
    KPIStatisticsBean findKPIStatistics(MonitorQueryInputInfo queryBean);


    /**
     * 根据时间段 坐席号 人员工号 获得上下岗信息
     *
     * @return KPI信息
     */
    List<LogRecordBean> findSeatLogRecord(MonitorQueryInputInfo queryBean);







}
