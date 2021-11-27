package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 *  描述: 处警记录（调派记录）
 *
 */
public class HandleBean extends BaseBean {

    private String incidentId; // 案件信息ID

    private String handleNumber;// 处警编号

    private String originalIncidentNumber ;  //原始案件编号

    private Long handleStartTime;// 处警开始时间

    private Long handleEndTime;// 处警结束时间

    private String handleBatch;// 处警批次

    private String dispatchOrganization; //出动机构

    private String dispatchVehicle; // 出动车辆

    private String handleOrganization ; //  处警单位编号 发送单位

    private String handleOrganizationId; // 处警单位编号

    private String handleOrganizationName; // 处警单位名称

    private String handleSeatNumber; // 处警台号（坐席号）

    private String handlePersonNumber; // 处警员工号

    private String handlePersonName; //处警员姓名

    private Long waitHandleDuration; //等待处警时长

    private Long handleDuration; //处警时长

    private String handleStatus; //处警单状态  STATUS_GIVEN已通知 STATUS_SIGNED已签收

    private String handleStatusName; //处警单状态

    private String handleSource; // 处警来源（1接处警/2实战指挥/3移动终端）

    private Long feedbackTime ; //   反馈_日期时间

    private Integer handleExpertNum ; //  调派专家_人数

    private Integer handlePersonNum ; //  调派人员_人数

    private Integer dispatchVehicleNum ; // 调派车辆_数量

    private Integer dispatchEquipmentNum ; //  调派装备_数量

    private String handleTypeCode  ; //  警情调派形式类别代码

    private String handleTypeName  ; //  警情调派形式类别名称

    private String handleContent ; //  调派指令_文件内容


    private String remarks;//备注

    private List<HandleOrganizationBean> handleOrganizationBean ; //调派单位信息 （ 处警信息 ）

    private List<HandleMiniatureStationBean>  handleMiniatureStationBean ; //调派微站信息

    private List<HandleExpertBean> handleExpertBean;//调派专家信息

    public String getHandleOrganization() {
        return handleOrganization;
    }

    public void setHandleOrganization(String handleOrganization) {
        this.handleOrganization = handleOrganization;
    }

    public Long getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Long feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public Integer getHandleExpertNum() {
        return handleExpertNum;
    }

    public void setHandleExpertNum(Integer handleExpertNum) {
        this.handleExpertNum = handleExpertNum;
    }

    public Integer getHandlePersonNum() {
        return handlePersonNum;
    }

    public void setHandlePersonNum(Integer handlePersonNum) {
        this.handlePersonNum = handlePersonNum;
    }

    public Integer getDispatchVehicleNum() {
        return dispatchVehicleNum;
    }

    public void setDispatchVehicleNum(Integer dispatchVehicleNum) {
        this.dispatchVehicleNum = dispatchVehicleNum;
    }

    public Integer getDispatchEquipmentNum() {
        return dispatchEquipmentNum;
    }

    public void setDispatchEquipmentNum(Integer dispatchEquipmentNum) {
        this.dispatchEquipmentNum = dispatchEquipmentNum;
    }

    public String getHandleTypeCode() {
        return handleTypeCode;
    }

    public void setHandleTypeCode(String handleTypeCode) {
        this.handleTypeCode = handleTypeCode;
    }

    public String getHandleTypeName() {
        return handleTypeName;
    }

    public void setHandleTypeName(String handleTypeName) {
        this.handleTypeName = handleTypeName;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
    }

    public String getHandleStatusName() {
        return handleStatusName;
    }

    public void setHandleStatusName(String handleStatusName) {
        this.handleStatusName = handleStatusName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleNumber() {
        return handleNumber;
    }

    public void setHandleNumber(String handleNumber) {
        this.handleNumber = handleNumber;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public Long getHandleStartTime() {
        return handleStartTime;
    }

    public void setHandleStartTime(Long handleStartTime) {
        this.handleStartTime = handleStartTime;
    }

    public Long getHandleEndTime() {
        return handleEndTime;
    }

    public void setHandleEndTime(Long handleEndTime) {
        this.handleEndTime = handleEndTime;
    }

    public String getHandleBatch() {
        return handleBatch;
    }

    public void setHandleBatch(String handleBatch) {
        this.handleBatch = handleBatch;
    }

    public String getDispatchOrganization() {
        return dispatchOrganization;
    }

    public void setDispatchOrganization(String dispatchOrganization) {
        this.dispatchOrganization = dispatchOrganization;
    }

    public String getDispatchVehicle() {
        return dispatchVehicle;
    }

    public void setDispatchVehicle(String dispatchVehicle) {
        this.dispatchVehicle = dispatchVehicle;
    }

    public String getHandleOrganizationId() {
        return handleOrganizationId;
    }

    public void setHandleOrganizationId(String handleOrganizationId) {
        this.handleOrganizationId = handleOrganizationId;
    }

    public String getHandleOrganizationName() {
        return handleOrganizationName;
    }

    public void setHandleOrganizationName(String handleOrganizationName) {
        this.handleOrganizationName = handleOrganizationName;
    }

    public String getHandleSeatNumber() {
        return handleSeatNumber;
    }

    public void setHandleSeatNumber(String handleSeatNumber) {
        this.handleSeatNumber = handleSeatNumber;
    }

    public String getHandlePersonNumber() {
        return handlePersonNumber;
    }

    public void setHandlePersonNumber(String handlePersonNumber) {
        this.handlePersonNumber = handlePersonNumber;
    }

    public String getHandlePersonName() {
        return handlePersonName;
    }

    public void setHandlePersonName(String handlePersonName) {
        this.handlePersonName = handlePersonName;
    }

    public Long getWaitHandleDuration() {
        return waitHandleDuration;
    }

    public void setWaitHandleDuration(Long waitHandleDuration) {
        this.waitHandleDuration = waitHandleDuration;
    }

    public Long getHandleDuration() {
        return handleDuration;
    }

    public void setHandleDuration(Long handleDuration) {
        this.handleDuration = handleDuration;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleSource() {
        return handleSource;
    }

    public void setHandleSource(String handleSource) {
        this.handleSource = handleSource;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<HandleOrganizationBean> getHandleOrganizationBean() {
        return handleOrganizationBean;
    }

    public void setHandleOrganizationBean(List<HandleOrganizationBean> handleOrganizationBean) {
        this.handleOrganizationBean = handleOrganizationBean;
    }

    public List<HandleExpertBean> getHandleExpertBean() {
        return handleExpertBean;
    }

    public void setHandleExpertBean(List<HandleExpertBean> handleExpertBean) {
        this.handleExpertBean = handleExpertBean;
    }

    public List<HandleMiniatureStationBean> getHandleMiniatureStationBean() {
        return handleMiniatureStationBean;
    }

    public void setHandleMiniatureStationBean(List<HandleMiniatureStationBean> handleMiniatureStationBean) {
        this.handleMiniatureStationBean = handleMiniatureStationBean;
    }
}
