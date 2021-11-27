package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *  描述:参战人员反馈
 *
 */
public class ParticipantFeedbackBean extends BaseBean {

    private String incidentId ; //案件id

    private String handleId;// 处警记录ID

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String vehicleId; // 车辆编号

    private String personId; // 人员编号

    private String personName; // 人员姓名

    private String personPhone; // 人员联系电话

    private String personRole; //人员角色

    private String personRoleName; //人员角色名称

    private Integer sorter;//排序

    private Long checkTime; //清点时间

    private Integer whetherFeedback; //是否返回 0 非返回 1 返回

    private Long feedbackCheckTime; //返回清点时间

    private String checkPersonId; //清点人员编号

    private String checkPersonName; //清点人员姓名

    private String remarks;//备注

    //额外字段
    private Integer whetherEnterFireSafety = 0  ; //是否进入火场 1 进火场 0 不在火场 默认0 不在火场

    private Long enterFireTime ; //  人员进入火场时间

    private Long  withdrawFireTime ; //人员撤出火场时间
    private Integer stateCode; //状态 10已调派 20进场 30退场
    private String stateName;

    public Long getWithdrawFireTime() {
        return withdrawFireTime;
    }

    public void setWithdrawFireTime(Long withdrawFireTime) {
        this.withdrawFireTime = withdrawFireTime;
    }

    public Integer getWhetherEnterFireSafety() {
        return whetherEnterFireSafety;
    }

    public void setWhetherEnterFireSafety(Integer whetherEnterFireSafety) {
        this.whetherEnterFireSafety = whetherEnterFireSafety;
    }

    public Long getEnterFireTime() {
        return enterFireTime;
    }

    public void setEnterFireTime(Long enterFireTime) {
        this.enterFireTime = enterFireTime;
    }

    public String getPersonRoleName() {
        return personRoleName;
    }

    public void setPersonRoleName(String personRoleName) {
        this.personRoleName = personRoleName;
    }

    public Integer getSorter() {
        return sorter;
    }

    public void setSorter(Integer sorter) {
        this.sorter = sorter;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
