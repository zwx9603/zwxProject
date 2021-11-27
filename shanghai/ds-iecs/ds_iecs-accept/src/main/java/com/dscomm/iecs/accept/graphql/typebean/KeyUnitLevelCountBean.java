package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 各个等级重点单位数量显示信息
 * */
public class KeyUnitLevelCountBean {

    private String unitLevelCode;//重点单位等级
    private Integer keyUnitCount;//重点单位数量
    private String unitLevelName;//重点单位等级名称


    public String getUnitLevelCode() {
        return unitLevelCode;
    }

    public void setUnitLevelCode(String unitLevelCode) {
        this.unitLevelCode = unitLevelCode;
    }

    public Integer getKeyUnitCount() {
        return keyUnitCount;
    }

    public void setKeyUnitCount(Integer keyUnitCount) {
        this.keyUnitCount = keyUnitCount;
    }

    public String getUnitLevelName() {
        return unitLevelName;
    }

    public void setUnitLevelName(String unitLevelName) {
        this.unitLevelName = unitLevelName;
    }
}
