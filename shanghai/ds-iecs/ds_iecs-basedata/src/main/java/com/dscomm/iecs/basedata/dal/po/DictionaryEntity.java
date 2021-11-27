package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：字典表
 *
 */
@Entity
@Table(name="JCJ_DM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DictionaryEntity extends BaseEntity {

    @Column(name = "ZDLX",   length = 100)
    private String typeCode; //字典类型编码

    @Column(name = "ZDLXMS", length = 400)
    private String typeDesc; //字典类型描述

    @Column(name = "BM",   length = 100)
    private String code; //字典编码

    @Column(name = "MC", length = 200)
    private String name; //字典名称

    @Column(name = "MS", length = 400 )
    private String desc; // 描述

    @Column(name = "SX")
    private Integer orderNum;  //顺序 ( 排序 )

    @Column(name = "SJBM", length = 100 )
    private String parentCode; //上级编码

    @Column(name = "AJLX",   length = 100)
    private String incidentTypeCode ;  //案件类型编码 或者 字典分类其他分类 信息

    //与外部系统 存在差异时  对应信息（ 编码  名称 ）
    @Column(name = "DYBM",   length = 100)
    private String convertCode; // 对应编码

    @Column(name = "DYMC", length = 200)
    private String convertName; //对应名称


    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }


    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getConvertCode() {
        return convertCode;
    }

    public void setConvertCode(String convertCode) {
        this.convertCode = convertCode;
    }

    public String getConvertName() {
        return convertName;
    }

    public void setConvertName(String convertName) {
        this.convertName = convertName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
