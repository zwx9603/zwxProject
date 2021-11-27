package com.dscomm.iecs.accept.graphql.typebean;

/**
 *
 * 统计维度
 *
 */
public class DimensionAssembleBean {

    /**
     * 统计维度编码
     */
    private String dimensionCode ;
    /**
     * 统计维度名称
     */
    private String dimensionName ;
    /**
     * 统计维度主要统计数
     */
    private String dimensionMainNun = "0" ;

    /**
     * 统计维度次要统计数
     */
    private String dimensionSecondaryNum  = "0"  ;


    public DimensionAssembleBean() {
    }

    public DimensionAssembleBean(String dimensionCode, String dimensionName, String dimensionMainNun, String dimensionSecondaryNum) {
        this.dimensionCode = dimensionCode;
        this.dimensionName = dimensionName;
        this.dimensionMainNun = dimensionMainNun;
        this.dimensionSecondaryNum = dimensionSecondaryNum;
    }

    public DimensionAssembleBean(String dimensionCode, String dimensionName, String dimensionMainNun ) {
        this.dimensionCode = dimensionCode;
        this.dimensionName = dimensionName;
        this.dimensionMainNun = dimensionMainNun;
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

    public String getDimensionMainNun() {
        return dimensionMainNun;
    }

    public void setDimensionMainNun(String dimensionMainNun) {
        this.dimensionMainNun = dimensionMainNun;
    }

    public String getDimensionSecondaryNum() {
        return dimensionSecondaryNum;
    }

    public void setDimensionSecondaryNum(String dimensionSecondaryNum) {
        this.dimensionSecondaryNum = dimensionSecondaryNum;
    }
}
