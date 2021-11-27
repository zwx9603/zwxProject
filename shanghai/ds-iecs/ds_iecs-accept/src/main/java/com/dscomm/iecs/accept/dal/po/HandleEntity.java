package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述:调派记录 处警记录
 *
 */
@Entity
@Table(name = "JCJ_CJJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandleEntity extends BaseEntity {


    @Column(name = "JQCJ_TYWYSBM", length = 40)
    private String idCode; //todo 字段  警情指令_通用唯一识别码

//    @Column(name = "AJID", length = 100 )
    @Column(name = "JQ_TYWYSBM", length = 100)
    private String incidentId; // todo 字段  案件信息ID

    @Column(name = "CJBH", length = 100 )
    private String handleNumber;// 处警编号

    @Column(name = "YSAJBH", length = 100 )
    private String originalIncidentNumber ;  //原始案件编号

    @Column(name = "CJKSSJ")
    private Long handleStartTime;// 处警开始时间

//    @Column(name = "CJJSSJ")
    @Column(name = "FS_RQSJ")
    private Long handleEndTime;// todo 字段 处警结束时间 发送_日期时间

    @Column(name = "CJPC", length = 100 )
    private String handleBatch;// 处警批次

    @Column(name = "CJDWS", length = 1000)
    private String dispatchOrganization; //出动机构

    @Column(name = "CDCLS", length = 2000)
    private String dispatchVehicle; // 出动车辆

    @Column(name = "FSDW", length = 100)
    private String handleOrganization ; //todo 字段 处警单位编号 发送单位

//    @Column(name = "CJDWBH", length = 100)
    @Column(name = "FSDW_TYWYSBM", length = 100)
    private String handleOrganizationId; // todo 字段 处警单位编号 发送单位通用唯一识别码

    @Column(name = "FSDW_DWMC", length = 200)
    private String handleOrganizationName; // todo 字段 处警单位 发送单位单位名称

    @Column(name = "CJTH", length = 100)
    private String handleSeatNumber; // 处警台号（坐席号）

    @Column(name = "CJYGH", length = 100)
    private String handlePersonNumber; // 处警员工号

    @Column(name = "CJYXM", length = 100)
    private String handlePersonName; //处警员姓名

    @Column(name = "DDCJSJ" )
    private Long waitHandleDuration; //等待处警时长

    @Column(name = "CJSC" )
    private Long handleDuration; //处警时长

    @Column(name = "CJDZT", length = 100)
    private String handleStatus; //处警单状态

    @Column(name = "LY", length = 100)
    private String handleSource; // 处警来源（接处警/实战指挥/移动终端）

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    @Column(name = "FK_RQSJ" )
    private Long feedbackTime ; //todo 字段 反馈_日期时间

    @Column(name = "DPZJ_RS" )
    private Integer handleExpertNum ; //todo 字段 调派专家_人数

    @Column(name = "DPRY_RS" )
    private Integer handlePersonNum ; //todo 字段 调派人员_人数

    @Column(name = "DPCL_SL" )
    private Integer dispatchVehicleNum ; //todo 字段 调派车辆_数量

    @Column(name = "DPZB_SL" )
    private Integer dispatchEquipmentNum ; //todo 字段 调派装备_数量

    @Column(name = "JQDPXSLBDM" ,length =  100)
    private String handleTypeCode  ; //todo 字段 警情调派形式类别代码

    @Column(name = "DPZL_WJNR" ,length =  1000)
    private String handleContent ; //todo 字段 调派指令_文件内容


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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getHandleOrganization() {
        return handleOrganization;
    }

    public void setHandleOrganization(String handleOrganization) {
        this.handleOrganization = handleOrganization;
    }

    public String getHandleOrganizationName() {
        return handleOrganizationName;
    }

    public void setHandleOrganizationName(String handleOrganizationName) {
        this.handleOrganizationName = handleOrganizationName;
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

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
    }
}
