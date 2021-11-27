package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:灭火重点单位 危化品信息 todo 新增
 */
@Entity
@Table(name = "YAGL_MHDW_WHP")
@DynamicInsert(true)
@DynamicUpdate(true)
public class KeyUnitDangerousChemicalsEntity extends BaseEntity {

    @Column(name = "ZDDWID", length = 100)
    private String keyUnitId; //todo 字段重点单位id

    @Column(name = "WXHXP", length = 100)
    private String dangerousChemicalsId ; //todo 字段危化品id

    @Column(name = "WXHXP_TYWYSBM", length = 40)
    private String dangerousChemicalsIdCode; //todo 字段 危险化学品_通用唯一识别码

    @Column(name = "WXHXP_HXPZTLBDM", length = 40)
    private String dangerousChemicalsStatusCode ; //todo 字段 化学品状态类别代码

    @Column(name = "WXHXP_HXPWXXLBDM", length = 100)
    private String dangerousChemicalsCategoryCode; //todo 字段 化学品危险性类别代码

    @Column(name = "WXHXP_WXHXPFLYDM", length = 100)
    private String dangerousChemicalsTypeCode; //todo 字段 危险化学品分类与代码

    @Column(name = "WXHXP_JYQK", length = 800)
    private String keyUnitDangerousChemicalsDesc ; //重点单位 危化品 简要情况

    @Column(name = "WXHXP_SL" )
    private Integer keyUnitDangerousChemicalsNum; //重点单位 危化品 数量

    @Column(name = "WXHXP_DZMC", length = 400)
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
}