package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.tree.TreeNode;

/**
 * 描述：字典表
 *
 */
public class DictionaryBean extends TreeNode {

    private String typeCode; //字典类型编码

    private String typeDesc; //字典类型描述

    private String code; //字典编码

    private String name; //字典名称

    private String pinyinHeader; //字典名称拼音头

    private String desc; // 描述

    private Integer orderNum;  //顺序 ( 排序 )

    private String parentCode; //上级编码

    private String incidentTypeCode ;  //案件类型编码

    //与外部系统 存在差异时  对应信息（ 编码  名称 ）
    private String convertCode; // 对应编码

    private String convertName; //对应名称

    private String remarks; // 备注


    public String getPinyinHeader() {
        return pinyinHeader;
    }

    public void setPinyinHeader(String pinyinHeader) {
        this.pinyinHeader = pinyinHeader;
    }

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
