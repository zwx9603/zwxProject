package com.dscomm.iecs.basedata.graphql.inputbean;


/**
 * 车辆拓展信息参数
 */
public class EquipmentVehicleExpandInputInfo {

    private String id ; //主键
    private String vehicleName ; //车辆名称
    private String vehicleNumber ; //车牌号码
    private String vehicleTypeCode ; //车辆类型代码 车辆功能

    private Float waterCarrier ; //载水量
    private Float carrierBubble ; //载泡量
    private Float maxLiftingHeight ; //最大举升高度  举升高度
    private Float traction ; //牵引力 牵引
    private Float liftingWeight ; //起吊重量 吊机
    private Float passengersNum ; //载人数
    private Float handLiftPump ; // 手抬泵
    private Float pumpFlow ; // 泵流量
    private Float fireMonitorFlow ; //消防炮流量

    private String foam ; //车载泡沫
    private String equipment ; //车载器材

    private String vehicleShortName;//车辆简称
    private String vehicleNature;//车辆性质代码 CLXZDM_ZZ(主战)  CLXZDM_ZY（支援）

    /**
     * 历史废弃字段
     */
    private String organizationId; // 所属 消防机构ID
    private String vehicleStatusCode ; //车辆状态代码 ( 车辆状态代码_灭火 )
    private String applyExplain; // 申请说明
    private int status; //变更状态
    private int whetherConsent; // 是否同意 0不同意 1同意
    private String refuseReason; //拒绝原因
    private String auditId;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getApplyExplain() {
        return applyExplain;
    }

    public void setApplyExplain(String applyExplain) {
        this.applyExplain = applyExplain;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWhetherConsent() {
        return whetherConsent;
    }

    public void setWhetherConsent(int whetherConsent) {
        this.whetherConsent = whetherConsent;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public Float getPassengersNum() {
        return passengersNum;
    }

    public void setPassengersNum(Float passengersNum) {
        this.passengersNum = passengersNum;
    }

    public String getFoam() {
        return foam;
    }

    public void setFoam(String foam) {
        this.foam = foam;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Float getPumpFlow() {
        return pumpFlow;
    }

    public void setPumpFlow(Float pumpFlow) {
        this.pumpFlow = pumpFlow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Float getWaterCarrier() {
        return waterCarrier;
    }

    public void setWaterCarrier(Float waterCarrier) {
        this.waterCarrier = waterCarrier;
    }

    public Float getCarrierBubble() {
        return carrierBubble;
    }

    public void setCarrierBubble(Float carrierBubble) {
        this.carrierBubble = carrierBubble;
    }

    public Float getMaxLiftingHeight() {
        return maxLiftingHeight;
    }

    public void setMaxLiftingHeight(Float maxLiftingHeight) {
        this.maxLiftingHeight = maxLiftingHeight;
    }

    public Float getLiftingWeight() {
        return liftingWeight;
    }

    public void setLiftingWeight(Float liftingWeight) {
        this.liftingWeight = liftingWeight;
    }

    public Float getTraction() {
        return traction;
    }

    public void setTraction(Float traction) {
        this.traction = traction;
    }

    public Float getHandLiftPump() {
        return handLiftPump;
    }

    public void setHandLiftPump(Float handLiftPump) {
        this.handLiftPump = handLiftPump;
    }

    public Float getFireMonitorFlow() {
        return fireMonitorFlow;
    }

    public void setFireMonitorFlow(Float fireMonitorFlow) {
        this.fireMonitorFlow = fireMonitorFlow;
    }

    public String getVehicleShortName() {
        return vehicleShortName;
    }

    public void setVehicleShortName(String vehicleShortName) {
        this.vehicleShortName = vehicleShortName;
    }

    public String getVehicleNature() {
        return vehicleNature;
    }

    public void setVehicleNature(String vehicleNature) {
        this.vehicleNature = vehicleNature;
    }
}
