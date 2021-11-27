package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：车载人员表
 *
 */
public class VehiclePersonsBean extends BaseBean {

    private String vehiclePersonId ; //车载人员id

    private String vehicleId;//车辆ID

    private String vehicleName;// 车辆名称

    private String vehicleNumber;//车牌号码

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String personId;//人员ID

    private String personName;//人员姓名

    private String personType;//人员类型(角色)  细类

    private String personTypeName;//人员类型（角色）名称

    private  String  driver ; //驾驶员

    private  String   correspondent ; //通讯员

    private Integer personNum  = 1 ; // 车载人员数 （ 包含指挥员 ） 默认 1

    private String personPhone ; //人员联系方式

    private Integer sorter;//排序

    private  Integer whetherParticipant ; //是否车辆登记  0 未登记  1 已登记

    private String participantId;//人员参战id

    private Integer whetherCommander;//大类  1指挥员 2驾驶员 3通信员  4消防战士


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

    public String getVehiclePersonId() {
        return vehiclePersonId;
    }

    public void setVehiclePersonId(String vehiclePersonId) {
        this.vehiclePersonId = vehiclePersonId;
    }

    public Integer getWhetherCommander() {
        return whetherCommander;
    }

    public void setWhetherCommander(Integer whetherCommander) {
        this.whetherCommander = whetherCommander;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getWhetherParticipant() {
        return whetherParticipant;
    }

    public void setWhetherParticipant(Integer whetherParticipant) {
        this.whetherParticipant = whetherParticipant;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPersonTypeName() {
        return personTypeName;
    }

    public void setPersonTypeName(String personTypeName) {
        this.personTypeName = personTypeName;
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

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Integer getSorter() {
        return sorter;
    }

    public void setSorter(Integer sorter) {
        this.sorter = sorter;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }
}
