package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述： 车辆状态分布
 *
 */

public class DimensionVehicleStatusBean {

    private Long dimensionCount;              // 总数
    private String dimensionCode;              // 分类编码
    private String dimensionName;              // 描述
    private List<DimensionAssembleBean> beans;    //总数构成信息

    public DimensionVehicleStatusBean() {
    }

    public DimensionVehicleStatusBean(Long dimensionCount, String dimensionCode, String dimensionName, List<DimensionAssembleBean> beans) {
        this.dimensionCount = dimensionCount;
        this.dimensionCode = dimensionCode;
        this.dimensionName = dimensionName;
        this.beans = beans;
    }

    public Long getDimensionCount() {
        return dimensionCount;
    }

    public void setDimensionCount(Long dimensionCount) {
        this.dimensionCount = dimensionCount;
    }

    public String getDimensionCode() {
        return dimensionCode;
    }

    public void setDimensionCode(String dimensionCode) {
        this.dimensionCode = dimensionCode;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public List<DimensionAssembleBean> getBeans() {
        return beans;
    }

    public void setBeans(List<DimensionAssembleBean> beans) {
        this.beans = beans;
    }
}
