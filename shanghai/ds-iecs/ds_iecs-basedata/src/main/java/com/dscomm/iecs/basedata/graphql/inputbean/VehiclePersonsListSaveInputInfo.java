package com.dscomm.iecs.basedata.graphql.inputbean;


/**
 * 描述：车载人员表
 *
 */
public class VehiclePersonsListSaveInputInfo {

    private String vehiclePersonId ; //车载人员id

    private String vehicleId;//车辆ID

    private String personId;//指挥人员ID

    private String personName;//指挥人员姓名

    private String personType;//指挥人员类型(角色) 细类

    private  String  driver ; //驾驶员

    private  String   correspondent ; //通讯员

//    private Integer whetherCommander = 1 ;//大类  1指挥员 2驾驶员 3通信员  4消防战士 默认指挥员

    private Integer personNum  ; // 车载人员数 （ 包含驾驶员 ） 默认 1

    private Integer sorter = 99 ;//排序

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

//    public Integer getWhetherCommander() {
//        return whetherCommander;
//    }
//
//    public void setWhetherCommander(Integer whetherCommander) {
//        this.whetherCommander = whetherCommander;
//    }

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
}
