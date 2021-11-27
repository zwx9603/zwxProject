package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述:标记标签
 */
public class TagLabelSaveInputInfo {


    private String businessTable;  //业务表名

    private String businessDataId; //单据ID ( 变更数据id  )

    private String tagType; // 标记类型

    private String remarksTitle;// 备注标题

    private String remarksContent;// 备注信息

    private String remarks;//备注


    public String getBusinessTable() {
        return businessTable;
    }

    public void setBusinessTable(String businessTable) {
        this.businessTable = businessTable;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
