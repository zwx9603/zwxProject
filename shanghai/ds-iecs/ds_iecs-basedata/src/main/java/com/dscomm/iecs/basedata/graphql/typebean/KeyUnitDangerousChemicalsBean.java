package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:灭火重点单位 危化品信息
 */
public class KeyUnitDangerousChemicalsBean extends BaseBean {

    private String keyUnitId; // 重点单位id

    private String dangerousChemicalsId ; // 危化品id

    private String dangerousChemicalsIdCode; //  危险化学品_通用唯一识别码

    private String dangerousChemicalsStatusCode ; //  化学品状态类别代码

    private String dangerousChemicalsStatusName ; //  化学品状态类别名称

    private String dangerousChemicalsCategoryCode; //  化学品危险性类别代码

    private String dangerousChemicalsCategoryName; //  化学品危险性类别名称

    private String dangerousChemicalsTypeCode; //  危险化学品分类与代码

    private String dangerousChemicalsTypeName; //  危险化学品分类名称

    private String keyUnitDangerousChemicalsDesc ; //重点单位 危化品 简要情况

    private Integer keyUnitDangerousChemicalsNum; //重点单位 危化品 数量

    private String keyUnitDangerousChemicalsAddress; //重点单位 危化品 地址名称

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getDangerousChemicalsId() {
        return dangerousChemicalsId;
    }

    public void setDangerousChemicalsId(String dangerousChemicalsId) {
        this.dangerousChemicalsId = dangerousChemicalsId;
    }

    public String getDangerousChemicalsIdCode() {
        return dangerousChemicalsIdCode;
    }

    public void setDangerousChemicalsIdCode(String dangerousChemicalsIdCode) {
        this.dangerousChemicalsIdCode = dangerousChemicalsIdCode;
    }

    public String getDangerousChemicalsStatusCode() {
        return dangerousChemicalsStatusCode;
    }

    public void setDangerousChemicalsStatusCode(String dangerousChemicalsStatusCode) {
        this.dangerousChemicalsStatusCode = dangerousChemicalsStatusCode;
    }

    public String getDangerousChemicalsCategoryCode() {
        return dangerousChemicalsCategoryCode;
    }

    public void setDangerousChemicalsCategoryCode(String dangerousChemicalsCategoryCode) {
        this.dangerousChemicalsCategoryCode = dangerousChemicalsCategoryCode;
    }

    public String getDangerousChemicalsTypeCode() {
        return dangerousChemicalsTypeCode;
    }

    public void setDangerousChemicalsTypeCode(String dangerousChemicalsTypeCode) {
        this.dangerousChemicalsTypeCode = dangerousChemicalsTypeCode;
    }

    public String getKeyUnitDangerousChemicalsDesc() {
        return keyUnitDangerousChemicalsDesc;
    }

    public void setKeyUnitDangerousChemicalsDesc(String keyUnitDangerousChemicalsDesc) {
        this.keyUnitDangerousChemicalsDesc = keyUnitDangerousChemicalsDesc;
    }

    public Integer getKeyUnitDangerousChemicalsNum() {
        return keyUnitDangerousChemicalsNum;
    }

    public void setKeyUnitDangerousChemicalsNum(Integer keyUnitDangerousChemicalsNum) {
        this.keyUnitDangerousChemicalsNum = keyUnitDangerousChemicalsNum;
    }

    public String getKeyUnitDangerousChemicalsAddress() {
        return keyUnitDangerousChemicalsAddress;
    }

    public void setKeyUnitDangerousChemicalsAddress(String keyUnitDangerousChemicalsAddress) {
        this.keyUnitDangerousChemicalsAddress = keyUnitDangerousChemicalsAddress;
    }

    public String getDangerousChemicalsStatusName() {
        return dangerousChemicalsStatusName;
    }

    public void setDangerousChemicalsStatusName(String dangerousChemicalsStatusName) {
        this.dangerousChemicalsStatusName = dangerousChemicalsStatusName;
    }

    public String getDangerousChemicalsCategoryName() {
        return dangerousChemicalsCategoryName;
    }

    public void setDangerousChemicalsCategoryName(String dangerousChemicalsCategoryName) {
        this.dangerousChemicalsCategoryName = dangerousChemicalsCategoryName;
    }

    public String getDangerousChemicalsTypeName() {
        return dangerousChemicalsTypeName;
    }

    public void setDangerousChemicalsTypeName(String dangerousChemicalsTypeName) {
        this.dangerousChemicalsTypeName = dangerousChemicalsTypeName;
    }
}
