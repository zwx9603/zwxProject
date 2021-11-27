package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 调派单位车辆信息 （ 作战车辆信息 ）
 *
 */
@Entity
@Table(name = "JCJ_ZZCLXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class  HandleOrganizationVehicleEntity extends BaseEntity {

    @Column(name = "AJID", length = 100)
    private String incidentId ; //案件id

    @Column(name = "CJJLID", length = 100)
    private String handleId; // 处警记录ID

    @Column(name = "CJXXID", length = 100)
    private String handlePoliceId; // 处警信息ID

    @Column(name = "YSAJBH", length = 100 )
    private String originalIncidentNumber ;  //原始案件编号

    @Column(name = "XFJGBH", length = 100)
    private String organizationId; // 消防机构编号

    @Column(name = "XFCL", length = 100)
    private String vehicle; // todo 字段 车辆编号

//    @Column(name = "CLBH", length = 100)
    @Column(name = "XFCL_TYWYSBM", length = 100)
    private String vehicleId; //todo 字段 车辆编号  车辆通用唯一识别码

    @Column(name = "XFCL_JDCHPHM", length = 100)
    private String vehicleNumber; // todo 字段 车辆编号  机动车号牌号码

//    @Column(name = "ZBLXDM", length = 100)
    @Column(name = "XFCL_CLLXDM", length = 100)
    private String vehicleTypeCode; // todo 字段 装备类型代码 （车辆类型代码）

    @Column(name = "ZZGNDM", length = 100)
    private String fightFunctionCode; // 作战功能代码

    @Column(name = "CLZTDM_MH", length = 100)
    private String vehicleStatusCode;//  车辆状态代码 ( 车辆状态代码_灭火 )

    @Column(name = "TZSJ")
    private Long noticeTime;// 通知时间

//    @Column(name = "CDSJ")
    @Column(name = "XFCL_CDSJ")
    private Long setOutTime;//todo 字段 出动时间

//    @Column(name = "DDXCSJ")
    @Column(name = "XFCL_DDSJ")
    private Long arriveTime;//todo 字段 到达时间

//    @Column(name = "CSSJ")
    @Column(name = "XFCL_CSSJ")
    private Long sendWaterTime;//todo 字段 出水时间

//    @Column(name = "TSSJ")
    @Column(name = "XFCL_TSSJ")
    private Long stopWaterTime;//todo 字段 停水时间

    @Column(name = "FHSJ")
    private Long returnLoadTime;//todo 字段 返回时间

//    @Column(name = "ZFSJ")
    @Column(name = "XFCL_ZTFHSJ")
    private Long halfwayReturnTime;//todo 字段 中返时间

//    @Column(name = "GDSJ")
    @Column(name = "XFCL_GDSJ")
    private Long returnTime;//todo 字段 归队时间

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    @Column(name = "CLBS", length = 20 )
    private String vehicleIdentification;//车辆标识 0 头车 1指挥车 2尾车  可拓展


    public String getVehicleIdentification() {
        return vehicleIdentification;
    }

    public void setVehicleIdentification(String vehicleIdentification) {
        this.vehicleIdentification = vehicleIdentification;
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

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getHandlePoliceId() {
        return handlePoliceId;
    }

    public void setHandlePoliceId(String handlePoliceId) {
        this.handlePoliceId = handlePoliceId;
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

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getFightFunctionCode() {
        return fightFunctionCode;
    }

    public void setFightFunctionCode(String fightFunctionCode) {
        this.fightFunctionCode = fightFunctionCode;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public Long getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Long noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Long getSetOutTime() {
        return setOutTime;
    }

    public void setSetOutTime(Long setOutTime) {
        this.setOutTime = setOutTime;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Long getSendWaterTime() {
        return sendWaterTime;
    }

    public void setSendWaterTime(Long sendWaterTime) {
        this.sendWaterTime = sendWaterTime;
    }

    public Long getStopWaterTime() {
        return stopWaterTime;
    }

    public void setStopWaterTime(Long stopWaterTime) {
        this.stopWaterTime = stopWaterTime;
    }

    public Long getHalfwayReturnTime() {
        return halfwayReturnTime;
    }

    public void setHalfwayReturnTime(Long halfwayReturnTime) {
        this.halfwayReturnTime = halfwayReturnTime;
    }

    public Long getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Long returnTime) {
        this.returnTime = returnTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Long getReturnLoadTime() {
        return returnLoadTime;
    }

    public void setReturnLoadTime(Long returnLoadTime) {
        this.returnLoadTime = returnLoadTime;
    }
}
