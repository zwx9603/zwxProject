package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述:案件出动力量使用频率统计
 * author:YangFuXi
 * Date:2021/6/21 Time:9:14
 **/
public class StatisticsBean {
    private String incidentLevelCode;//案件等级代码
    private String incidentLevelName;//案件等级名称
    private Integer vehicleTotal;//车辆总数
    private List<IncidentLevelDispatchVehicleBean> levelItems;//按照案件级别维度统计项

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getIncidentLevelName() {
        return incidentLevelName;
    }

    public void setIncidentLevelName(String incidentLevelName) {
        this.incidentLevelName = incidentLevelName;
    }

    public Integer getVehicleTotal() {
        return vehicleTotal;
    }

    public void setVehicleTotal(Integer vehicleTotal) {
        this.vehicleTotal = vehicleTotal;
    }

    public List<IncidentLevelDispatchVehicleBean> getLevelItems() {
        return levelItems;
    }

    public void setLevelItems(List<IncidentLevelDispatchVehicleBean> levelItems) {
        this.levelItems = levelItems;
    }
}
