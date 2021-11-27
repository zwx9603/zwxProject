package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.accept.service.bean.DeviceLocationBean;
import com.dscomm.iecs.base.service.bean.BaseBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleLoadBean;
import com.dscomm.iecs.basedata.graphql.typebean.VehiclePersonsBean;

import java.util.List;

/**
 *  描述: 调派单位车辆信息 （ 作战车辆信息 ）
 *
 */
public class  HandleOrganizationVehicleBean extends BaseBean {

    private String incidentId ; //案件id

    private String handleId; // 处警记录ID

    private String handleBatch;// 处警批次

    private Long handleStartTime;// 处警开始时间

    private Long handleEndTime;// 处警结束时间

    private String handlePoliceId; // 处警信息ID

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String vehicle; //   车辆编号

    private String vehicleId; // 车辆编号

    private String vehicleNumber; //   车牌编号  机动车号牌号码

    private String vehicleTypeCode; // 装备类型代码 （车辆类型代码）

    private String vehicleTypeName; // 装备类型名称

    private String fightFunctionCode; // 作战功能代码

    private String fightFunctionName; // 作战功能名称

    private String vehicleStatusCode;// 车辆状态代码 ( 车辆状态代码_灭火 )

    private String vehicleStatusName;// 车辆状态名称 ( 车辆状态代码_灭火 )

    private String  vehicleCode;//车辆编号

    private String vehicleName;// 装备名称 车辆名称

    private String gpsNumber;// GPS编号

    private String locationNumber;// 定位编号

    private String radioCallSign;// 电台呼号

    private Integer vehicleOrderNum = 99 ; // 车辆顺序 默认99

    private String incidentNumber;// 案件编号

    private Long noticeTime;// 通知时间

    private Long setOutTime;// 出动时间

    private Long arriveTime;// 到达时间

    private Long sendWaterTime;// 出水时间

    private Long stopWaterTime;// 停水时间

    private Long returnLoadTime;//todo 字段 返回时间

    private Long halfwayReturnTime;//中返时间

    private Long returnTime;// 归队时间

    private String remarks;//备注

    public Long getReturnLoadTime() {
        return returnLoadTime;
    }

    public void setReturnLoadTime(Long returnLoadTime) {
        this.returnLoadTime = returnLoadTime;
    }

    private List<EquipmentVehicleLoadBean> equipmentVehicleLoadBeanList; //车载装备列表

    private List<ParticipantFeedbackBean> participantFeedbackBeanList; //车载人员列表
    private List<VehiclePersonsBean> vehiclePersonsBeans;//计划的车载人员信息列表(wl_clryxx)

    //设置车辆指挥员
    private String vehicleCommanderId ; // 车辆指挥员id
    private String vehicleCommander ; // 车辆指挥员
    private String vehicleCommanderPhone; ; // 车辆指挥员

    private String  mappingGroupId  ; //车库号

    //车辆位置信息
    private DeviceLocationBean deviceLocationBean ;

    private  String vehicleIdentification;  //车辆标识 0 头车 1指挥车 2尾车  可拓展

    public String getVehicleIdentification() {
        return vehicleIdentification;
    }

    public void setVehicleIdentification(String vehicleIdentification) {
        this.vehicleIdentification = vehicleIdentification;
    }

    public DeviceLocationBean getDeviceLocationBean() {
        return deviceLocationBean;
    }

    public void setDeviceLocationBean(DeviceLocationBean deviceLocationBean) {
        this.deviceLocationBean = deviceLocationBean;
    }

    public String getMappingGroupId() {
        return mappingGroupId;
    }

    public void setMappingGroupId(String mappingGroupId) {
        this.mappingGroupId = mappingGroupId;
    }

    public String getVehicleCommanderId() {
        return vehicleCommanderId;
    }

    public void setVehicleCommanderId(String vehicleCommanderId) {
        this.vehicleCommanderId = vehicleCommanderId;
    }

    public String getVehicleCommanderPhone() {
        return vehicleCommanderPhone;
    }

    public void setVehicleCommanderPhone(String vehicleCommanderPhone) {
        this.vehicleCommanderPhone = vehicleCommanderPhone;
    }

    public String getVehicleCommander() {
        return vehicleCommander;
    }

    public void setVehicleCommander(String vehicleCommander) {
        this.vehicleCommander = vehicleCommander;
    }

    public List<EquipmentVehicleLoadBean> getEquipmentVehicleLoadBeanList() {
        return equipmentVehicleLoadBeanList;
    }

    public void setEquipmentVehicleLoadBeanList(List<EquipmentVehicleLoadBean> equipmentVehicleLoadBeanList) {
        this.equipmentVehicleLoadBeanList = equipmentVehicleLoadBeanList;
    }

    public List<ParticipantFeedbackBean> getParticipantFeedbackBeanList() {
        return participantFeedbackBeanList;
    }

    public void setParticipantFeedbackBeanList(List<ParticipantFeedbackBean> participantFeedbackBeanList) {
        this.participantFeedbackBeanList = participantFeedbackBeanList;
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

    public String getHandleBatch() {
        return handleBatch;
    }

    public void setHandleBatch(String handleBatch) {
        this.handleBatch = handleBatch;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getFightFunctionCode() {
        return fightFunctionCode;
    }

    public void setFightFunctionCode(String fightFunctionCode) {
        this.fightFunctionCode = fightFunctionCode;
    }

    public String getFightFunctionName() {
        return fightFunctionName;
    }

    public void setFightFunctionName(String fightFunctionName) {
        this.fightFunctionName = fightFunctionName;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getVehicleStatusName() {
        return vehicleStatusName;
    }

    public void setVehicleStatusName(String vehicleStatusName) {
        this.vehicleStatusName = vehicleStatusName;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getGpsNumber() {
        return gpsNumber;
    }

    public void setGpsNumber(String gpsNumber) {
        this.gpsNumber = gpsNumber;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getRadioCallSign() {
        return radioCallSign;
    }

    public void setRadioCallSign(String radioCallSign) {
        this.radioCallSign = radioCallSign;
    }

    public Integer getVehicleOrderNum() {
        return vehicleOrderNum;
    }

    public void setVehicleOrderNum(Integer vehicleOrderNum) {
        this.vehicleOrderNum = vehicleOrderNum;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
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

    public List<VehiclePersonsBean> getVehiclePersonsBeans() {
        return vehiclePersonsBeans;
    }

    public void setVehiclePersonsBeans(List<VehiclePersonsBean> vehiclePersonsBeans) {
        this.vehiclePersonsBeans = vehiclePersonsBeans;
    }
}
