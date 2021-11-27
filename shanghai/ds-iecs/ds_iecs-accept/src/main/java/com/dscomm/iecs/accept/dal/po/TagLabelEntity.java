package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:标记标签
 *
 */
@Entity
@Table(name = "JCJ_BJBQ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TagLabelEntity extends BaseEntity {

    @Column(name = "YWBM", length = 100 )
    private String businessTable;  //业务表名 1电话标签

    @Column(name = "DJID", length = 100)
    private String businessDataId; //单据ID ( 变更数据id  )

    @Column(name = "BJLX", length = 100 )
    private String tagType; // 标记类型

    @Column(name = "BZBT", length = 400)
    private String remarksTitle;// 备注标题

    @Column(name = "BZXX", length = 4000)
    private String remarksContent;// 备注信息

    @Column(name = "JRSJ")
    private Long addTime;  //加入时间

    @Column(name = "CXSJ")
    private Long revokeTime; //撤销时间

    @Column(name = "YHBH", length = 100)
    private String personId;//用户编号

    @Column(name = "YHMC", length = 100)
    private String personName;//用户名称


    @Column(name = "BZ", length = 800)
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
