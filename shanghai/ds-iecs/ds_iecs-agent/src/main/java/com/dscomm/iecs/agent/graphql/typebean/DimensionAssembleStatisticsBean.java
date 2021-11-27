package com.dscomm.iecs.agent.graphql.typebean;

import java.util.List;

/**
 * 描述： 统计信息
 *
 */

public class DimensionAssembleStatisticsBean {

    private Long dimensionCount;              // 总数
    private List<DimensionAssembleBean> beans;    //总数构成信息

    public Long getDimensionCount() {
        return dimensionCount;
    }

    public void setDimensionCount(Long dimensionCount) {
        this.dimensionCount = dimensionCount;
    }

    public List<DimensionAssembleBean> getBeans() {
        return beans;
    }

    public void setBeans(List<DimensionAssembleBean> beans) {
        this.beans = beans;
    }

    public  void  builDimensionAssembleStatistics ( DimensionAssembleStatisticsBean dimensionAssembleStatisticsBean ){
        dimensionCount = dimensionAssembleStatisticsBean.getDimensionCount() ;
        beans = dimensionAssembleStatisticsBean.getBeans() ;
    }
}
