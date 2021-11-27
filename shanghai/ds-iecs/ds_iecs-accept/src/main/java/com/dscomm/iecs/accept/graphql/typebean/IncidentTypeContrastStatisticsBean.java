package com.dscomm.iecs.accept.graphql.typebean;


/**
 * 描述： 案件类型同环比统计
 *
 */

public class IncidentTypeContrastStatisticsBean {

    private DimensionAssembleStatisticsBean todayStatistics;    //今天数据
    private DimensionAssembleStatisticsBean yesterdayStatistics;    //昨天数据
    private DimensionAssembleStatisticsBean lastMonthTodayStatistics;    //上月今天数据

    private DimensionAssembleStatisticsBean currentMonthStatistics;    //本月数据
    private DimensionAssembleStatisticsBean lastMonthStatistics;    //上月数据
    private DimensionAssembleStatisticsBean lastYearCurrentMonthStatistics;    //上年本月数据



    public DimensionAssembleStatisticsBean getTodayStatistics() {
        return todayStatistics;
    }

    public void setTodayStatistics(DimensionAssembleStatisticsBean todayStatistics) {
        this.todayStatistics = todayStatistics;
    }

    public DimensionAssembleStatisticsBean getYesterdayStatistics() {
        return yesterdayStatistics;
    }

    public void setYesterdayStatistics(DimensionAssembleStatisticsBean yesterdayStatistics) {
        this.yesterdayStatistics = yesterdayStatistics;
    }

    public DimensionAssembleStatisticsBean getCurrentMonthStatistics() {
        return currentMonthStatistics;
    }

    public void setCurrentMonthStatistics(DimensionAssembleStatisticsBean currentMonthStatistics) {
        this.currentMonthStatistics = currentMonthStatistics;
    }

    public DimensionAssembleStatisticsBean getLastMonthStatistics() {
        return lastMonthStatistics;
    }

    public void setLastMonthStatistics(DimensionAssembleStatisticsBean lastMonthStatistics) {
        this.lastMonthStatistics = lastMonthStatistics;
    }

    public DimensionAssembleStatisticsBean getLastMonthTodayStatistics() {
        return lastMonthTodayStatistics;
    }

    public void setLastMonthTodayStatistics(DimensionAssembleStatisticsBean lastMonthTodayStatistics) {
        this.lastMonthTodayStatistics = lastMonthTodayStatistics;
    }

    public DimensionAssembleStatisticsBean getLastYearCurrentMonthStatistics() {
        return lastYearCurrentMonthStatistics;
    }

    public void setLastYearCurrentMonthStatistics(DimensionAssembleStatisticsBean lastYearCurrentMonthStatistics) {
        this.lastYearCurrentMonthStatistics = lastYearCurrentMonthStatistics;
    }



}
