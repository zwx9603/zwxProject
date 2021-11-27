package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述： 车辆状态分布统计
 *
 */

public class DimensionVehicleStatusStatisticsBean {

    private Long dimensionCount;              // 总数
    private List<DimensionVehicleStatusBean> beans;    //总数构成信息

    public Long getDimensionCount() {
        return dimensionCount;
    }

    public void setDimensionCount(Long dimensionCount) {
        this.dimensionCount = dimensionCount;
    }

    public List<DimensionVehicleStatusBean> getBeans() {
        return beans;
    }

    public void setBeans(List<DimensionVehicleStatusBean> beans) {
        this.beans = beans;
    }
}
