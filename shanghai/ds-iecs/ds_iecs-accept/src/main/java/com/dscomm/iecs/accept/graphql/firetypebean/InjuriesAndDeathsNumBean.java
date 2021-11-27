package com.dscomm.iecs.accept.graphql.firetypebean;

public class InjuriesAndDeathsNumBean {
    /**
     * 统计维度编码
     */
    private String dimensionCode ;
    /**
     * 统计维度名称
     */
    private String dimensionName ;
    private Long injuriesNum ;//受伤人数d
    private Long deathsNum ;//死亡人数

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

    public Long getInjuriesNum() {
        return injuriesNum;
    }

    public void setInjuriesNum(Long injuriesNum) {
        this.injuriesNum = injuriesNum;
    }

    public Long getDeathsNum() {
        return deathsNum;
    }

    public void setDeathsNum(Long deathsNum) {
        this.deathsNum = deathsNum;
    }
}
