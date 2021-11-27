package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 出动力量车辆统计
 *
 * */
public class DispatchAgencyCarBean {

    private String stateCode;//车辆状态代码（到场、途中、返回）
    private String stateName;//车辆状态名称
    private Integer count;//数量

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
