package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：值班信息实体
 *
 */

@Entity
@Table(name = "SWGL_ZBGL_MRZB")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OnDutyEntity extends BaseEntity {

    @Column(name = "ZBRQ")
    private Long onDutyTime;   //值班日期

    @Column(name = "SSDW" , length =  100 )
    private String subordinateUnit;   //所属单位

    @Column(name = "SSJG", length =  100 )
    private String subordinateOrganization;  //所属机构

    @Column(name = "ZGDW", length =  100 )
    private String chargeUnit;//主管单位

    @Column(name = "YHBH", length =  100 )
    private String personId;  //用户编号

    @Column(name = "YHXM", length =  100 )
    private String personName;  //用户姓名

    @Column(name = "ZBSX")
    private Integer orderNum ;  //值班顺序

    @Column(name = "ZBLX", length =  100 )
    private String onDutyType;//值班类型

    @Column(name = "ZT", length =  10 )
    private String onDutyStatus;     //值班状态

    @Column(name = "SHRID", length =  100 )
    private String reviewerId; //审核人ID

    @Column(name = "SHRXM", length =  100 )
    private String reviewerName;     //审核人姓名

    @Column(name = "SHSJ")
    private Long reviewerTime;//审核时间

    @Column(name = "BJTB", length =  10 )
    private String layoutSynchronization;   //部局同步

    @Column(name = "ZDTB", length =  10 )
    private String TeamSynchronization;   //总队同步

    @Column(name = "ADDIP", length =  100 )
    private String createIp;//创建ip

    @Column(name = "ADDACC", length =  100 )
    private String createPersonId;    //创建人id

    @Column(name = "ADDACCNAME", length =  100 )
    private String createPersonName;   //创建人姓名

    @Column(name = "ADDTIME" )
    private Long createTime;        //创建时间

    @Column(name = "CHGIP", length =  100 )
    private String updateIp;//修改ip

    @Column(name = "CHGACC", length =  100 )
    private String updateId;//修改人id

    @Column(name = "CHGACCNAME", length =  100 )
    private String updateName;//修改人姓名

    @Column(name = "CHGTIME")
    private Long updateTime;   //修改时间

    @Column(name = "DELETED", length =  10 )
    private String deleted;//删除标记

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本

    public Long getOnDutyTime() {
        return onDutyTime;
    }

    public void setOnDutyTime(Long onDutyTime) {
        this.onDutyTime = onDutyTime;
    }

    public String getSubordinateUnit() {
        return subordinateUnit;
    }

    public void setSubordinateUnit(String subordinateUnit) {
        this.subordinateUnit = subordinateUnit;
    }

    public String getSubordinateOrganization() {
        return subordinateOrganization;
    }

    public void setSubordinateOrganization(String subordinateOrganization) {
        this.subordinateOrganization = subordinateOrganization;
    }

    public String getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(String chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

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

    public String getOnDutyType() {
        return onDutyType;
    }

    public void setOnDutyType(String onDutyType) {
        this.onDutyType = onDutyType;
    }

    public String getOnDutyStatus() {
        return onDutyStatus;
    }

    public void setOnDutyStatus(String onDutyStatus) {
        this.onDutyStatus = onDutyStatus;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Long getReviewerTime() {
        return reviewerTime;
    }

    public void setReviewerTime(Long reviewerTime) {
        this.reviewerTime = reviewerTime;
    }

    public String getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getCreatePersonName() {
        return createPersonName;
    }

    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getLayoutSynchronization() {
        return layoutSynchronization;
    }

    public void setLayoutSynchronization(String layoutSynchronization) {
        this.layoutSynchronization = layoutSynchronization;
    }

    public String getTeamSynchronization() {
        return TeamSynchronization;
    }

    public void setTeamSynchronization(String teamSynchronization) {
        TeamSynchronization = teamSynchronization;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}
