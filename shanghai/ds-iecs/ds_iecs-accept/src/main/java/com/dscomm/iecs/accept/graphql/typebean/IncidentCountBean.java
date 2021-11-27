package com.dscomm.iecs.accept.graphql.typebean;


import java.util.List;

/**
 *警情信息以及趋势统计信息
 *
 * */
public class IncidentCountBean {

    private DimensionAssembleStatisticsBean incidentCount;//警情统计信息

    private List<TimeTrendBean> incidentTrend;//警情趋势统计

    public DimensionAssembleStatisticsBean getIncidentCount() {
        return incidentCount;
    }

    public void setIncidentCount(DimensionAssembleStatisticsBean incidentCount) {
        this.incidentCount = incidentCount;
    }

    public List<TimeTrendBean> getIncidentTrend() {
        return incidentTrend;
    }

    public void setIncidentTrend(List<TimeTrendBean> incidentTrend) {
        this.incidentTrend = incidentTrend;
    }
}
