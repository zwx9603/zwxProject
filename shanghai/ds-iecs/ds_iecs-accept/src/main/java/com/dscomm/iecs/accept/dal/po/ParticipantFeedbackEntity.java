package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述:参战人员反馈
 *
 */
@Entity
@Table(name = "JCJ_CZRY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ParticipantFeedbackEntity extends BaseEntity {

    @Column(name = "AJID", length = 100)
    private String incidentId ; //案件id

    @Column(name = "CJJLID", length = 100)
    private String handleId;// 处警记录ID

    @Column(name = "XFJGBH", length = 100)
    private String organizationId; // 消防机构编号

    @Column(name = "YSAJBH", length = 100 )
    private String originalIncidentNumber ;  //原始案件编号

    @Column(name = "CLBH", length = 100)
    private String vehicleId; // 车辆编号

    @Column(name = "RYBH", length = 100)
    private String personId; // 人员编号

//    @Column(name = "RYMC", length = 100)
    @Column(name = "DPRY_XM", length = 100)
    private String personName; // todo 字段 人员姓名 调派人员_姓名

    @Column(name = "LXDH", length = 50)
    private String personPhone; // 人员联系电话

    @Column(name = "RYJS", length = 100)
    private String personRole; //人员角色

    @Column(name="PX")
    private Integer sorter;//排序

    @Column(name = "QDSJ" )
    private Long checkTime; //清点时间

    @Column(name = "SFFH" )
    private Integer whetherFeedback; //是否返回

    @Column(name = "FHSJ" )
    private Long feedbackCheckTime; //返回清点时间

    @Column(name = "QDRYBH", length = 100)
    private String checkPersonId; //清点人员编号

    @Column(name = "QDRYXM", length = 100)
    private String checkPersonName; //清点人员姓名

    @Column(name = "BZ", length = 800)
    private String remarks;//备注
    @Column(name = "ZT")
    private Integer state;//状态 10 已下派 20 已进场 30已退场
    @Column(name = "JCSJ")
    private Long entryTime;//进场时间
    @Column(name = "TCSJ")
    private Long exitTime;//退场时间



    public Integer getSorter() {
        return sorter;
    }

    public void setSorter(Integer sorter) {
        this.sorter = sorter;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

    public Long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getWhetherFeedback() {
        return whetherFeedback;
    }

    public void setWhetherFeedback(Integer whetherFeedback) {
        this.whetherFeedback = whetherFeedback;
    }

    public String getCheckPersonId() {
        return checkPersonId;
    }

    public void setCheckPersonId(String checkPersonId) {
        this.checkPersonId = checkPersonId;
    }

    public String getCheckPersonName() {
        return checkPersonName;
    }

    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getFeedbackCheckTime() {
        return feedbackCheckTime;
    }

    public void setFeedbackCheckTime(Long feedbackCheckTime) {
        this.feedbackCheckTime = feedbackCheckTime;
    }


    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Long entryTime) {
        this.entryTime = entryTime;
    }

    public Long getExitTime() {
        return exitTime;
    }

    public void setExitTime(Long exitTime) {
        this.exitTime = exitTime;
    }
}
