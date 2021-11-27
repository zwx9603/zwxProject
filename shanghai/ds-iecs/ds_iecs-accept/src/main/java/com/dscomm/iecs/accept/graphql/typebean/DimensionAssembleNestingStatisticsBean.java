package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述：  多级嵌套统计bean
 *
 */

public class DimensionAssembleNestingStatisticsBean  {

    private Long dimensionCount;              // 多级总数
    private List<DimensionAssembleNestingBean> beans;    //多级总数构成信息

    public Long getDimensionCount() {
        return dimensionCount;
    }

    public void setDimensionCount(Long dimensionCount) {
        this.dimensionCount = dimensionCount;
    }

    public List<DimensionAssembleNestingBean> getBeans() {
        return beans;
    }

    public void setBeans(List<DimensionAssembleNestingBean> beans) {
        this.beans = beans;
    }
}
