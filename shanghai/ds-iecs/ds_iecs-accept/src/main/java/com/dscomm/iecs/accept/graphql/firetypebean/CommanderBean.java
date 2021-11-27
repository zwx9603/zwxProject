package com.dscomm.iecs.accept.graphql.firetypebean;


/**
 * 描述： 现场指挥员bean
 *
 * @author AIguibin Date time 2018/7/16 10:29
 */
public class CommanderBean {

    private String id; //主键

    private String incidentId  ; //案件id

    private String commandId  ; //指挥id

    private String commanderType ; //指挥员现场类型 (1:总指挥,2:副指挥,3:指挥长 4 指挥员 5车辆指挥员 )

    private String vehicleId; // 车辆id

    private String vehicleName;// 车辆名称

    private String vehicleNumber;//车牌号码

    private Integer personNum;//车载人员数 （ 包含指挥员 ） 默认 0

    private String commanderId; //指挥员id

    private String commanderName; //指挥员名称

    private String commanderDuty; //指挥员职务

    private String commanderDutyName; //指挥员职务名称

    private  String  driver ; //驾驶员

    private  String   correspondent ; //通讯员

    private String mobilePhone;//指挥员联系电话

    private String telephone; //指挥员联系 固定电话

    private String organizationId; //指挥员所在机构id

    private String organizationName; //指挥员所在机构名称

    private Long arriveTime;// 指令下达人员到场时间

    private Long backTime;// 指令下达人员返回时间

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(String correspondent) {
        this.correspondent = correspondent;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getCommanderType() {
        return commanderType;
    }

    public void setCommanderType(String commanderType) {
        this.commanderType = commanderType;
    }

    public String getCommanderId() {
        return commanderId;
    }

    public void setCommanderId(String commanderId) {
        this.commanderId = commanderId;
    }

    public String getCommanderName() {
        return commanderName;
    }

    public void setCommanderName(String commanderName) {
        this.commanderName = commanderName;
    }

    public String getCommanderDuty() {
        return commanderDuty;
    }

    public void setCommanderDuty(String commanderDuty) {
        this.commanderDuty = commanderDuty;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getCommanderDutyName() {
        return commanderDutyName;
    }

    public void setCommanderDutyName(String commanderDutyName) {
        this.commanderDutyName = commanderDutyName;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Long getBackTime() {
        return backTime;
    }

    public void setBackTime(Long backTime) {
        this.backTime = backTime;
    }
}
