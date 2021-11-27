package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述:案件级别为维度统计车辆调派频次
 * author:YangFuXi
 * Date:2021/6/21 Time:9:19
 **/
public class IncidentLevelDispatchVehicleBean {
    private String disposalObjectCode;// 处置对象代码
    private String disposalObjectName;// 处置对象名称
    private Integer vehicleTotal;//车辆总数
    private List<IncidentVehicleStatisticsBean> staticItems;

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getDisposalObjectName() {
        return disposalObjectName;
    }

    public void setDisposalObjectName(String disposalObjectName) {
        this.disposalObjectName = disposalObjectName;
    }

    public Integer getVehicleTotal() {
        return vehicleTotal;
    }

    public void setVehicleTotal(Integer vehicleTotal) {
        this.vehicleTotal = vehicleTotal;
    }

    public List<IncidentVehicleStatisticsBean> getStaticItems() {
        return staticItems;
    }

    public void setStaticItems(List<IncidentVehicleStatisticsBean> staticItems) {
        this.staticItems = staticItems;
    }
}
