package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述： 统计bean
 *
 */

public class DimensionAssembleCodeNameStatisticsBean extends  DimensionAssembleStatisticsBean {

    /**
     * 统计维度编码
     */
    private String dimensionCode ;
    /**
     * 统计维度名称
     */
    private String dimensionName ;

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
}
