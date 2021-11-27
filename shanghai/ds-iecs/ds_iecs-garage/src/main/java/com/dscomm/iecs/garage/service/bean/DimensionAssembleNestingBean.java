package com.dscomm.iecs.garage.service.bean;

import com.dscomm.iecs.base.service.tree.TreeNode;

/**
 *
 * 多级统计维度
 *
 */
public class DimensionAssembleNestingBean   extends TreeNode {


    private String nestingDimensionCode ; //多级级嵌套编码

    private String nestingDimensionName ; //多级嵌套名称

    private String nestingDimensionMainNun = "0" ;//多级嵌套主要统计数

    private String nestingDimensionSecondaryNum  = "0"  ;//多级嵌套次要统计数

    private String nestingParentDimensionCode ; //多级嵌套父编码

    public String getNestingDimensionMainNun() {
        return nestingDimensionMainNun;
    }

    public void setNestingDimensionMainNun(String nestingDimensionMainNun) {
        this.nestingDimensionMainNun = nestingDimensionMainNun;
    }

    public String getNestingDimensionSecondaryNum() {
        return nestingDimensionSecondaryNum;
    }

    public void setNestingDimensionSecondaryNum(String nestingDimensionSecondaryNum) {
        this.nestingDimensionSecondaryNum = nestingDimensionSecondaryNum;
    }

    public String getNestingParentDimensionCode() {
        return nestingParentDimensionCode;
    }

    public void setNestingParentDimensionCode(String nestingParentDimensionCode) {
        this.nestingParentDimensionCode = nestingParentDimensionCode;
    }

    public String getNestingDimensionCode() {
        return nestingDimensionCode;
    }

    public void setNestingDimensionCode(String nestingDimensionCode) {
        this.nestingDimensionCode = nestingDimensionCode;
    }

    public String getNestingDimensionName() {
        return nestingDimensionName;
    }

    public void setNestingDimensionName(String nestingDimensionName) {
        this.nestingDimensionName = nestingDimensionName;
    }
}
