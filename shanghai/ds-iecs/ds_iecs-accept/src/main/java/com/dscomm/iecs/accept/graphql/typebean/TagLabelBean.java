package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:标记标签
 *
 */
public class TagLabelBean extends BaseBean {

    private String businessTable;  //业务表名

    private String businessTableName;  //业务表名

    private String businessDataId; //单据ID ( 变更数据id  )

    private String tagType; // 标记类型

    private String tagTypeName; // 标记类型名称

    private String remarksTitle;// 备注标题

    private String remarksContent;// 备注信息

    private Long addTime;  //加入时间

    private Long revokeTime; //撤销时间

    private String personId;//用户编号

    private String personName;//用户名称

    private String remarks;//备注

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getBusinessTable() {
        return businessTable;
    }

    public void setBusinessTable(String businessTable) {
        this.businessTable = businessTable;
    }

    public String getTagTypeName() {
        return tagTypeName;
    }

    public void setTagTypeName(String tagTypeName) {
        this.tagTypeName = tagTypeName;
    }

    public String getBusinessTableName() {
        return businessTableName;
    }

    public void setBusinessTableName(String businessTableName) {
        this.businessTableName = businessTableName;
    }

    public String getBusinessDataId() {
        return businessDataId;
    }

    public void setBusinessDataId(String businessDataId) {
        this.businessDataId = businessDataId;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getRemarksTitle() {
        return remarksTitle;
    }

    public void setRemarksTitle(String remarksTitle) {
        this.remarksTitle = remarksTitle;
    }

    public String getRemarksContent() {
        return remarksContent;
    }

    public void setRemarksContent(String remarksContent) {
        this.remarksContent = remarksContent;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getRevokeTime() {
        return revokeTime;
    }

    public void setRevokeTime(Long revokeTime) {
        this.revokeTime = revokeTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
