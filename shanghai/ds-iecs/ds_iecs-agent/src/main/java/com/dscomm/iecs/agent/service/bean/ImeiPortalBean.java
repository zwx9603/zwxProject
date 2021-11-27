package com.dscomm.iecs.agent.service.bean;

/**
 * 终端信息
 */
public class ImeiPortalBean {


    private String         id;           //终端id
    private String         imei;          //终端imei号
    private String         name;       //终端名称
    private String         type;       //终端类型 （区别终端大类 SCZD 手持终端 （人员 ） CZZD 车载终端 （车辆） ）
    private String         category;       //终端类别 大类中小雷

    private Boolean         majorTerminal;   //是否主终端
    private String         orgId;       //终端 所在机构id
    private String         orgCode;   //终端 所在机构code
    private String         orgName;       //终端 所在机构名称
    private String         orgQueryCode;   //终端 所在机构查询码

    // 手持终端存在
    private String         personId;           //人员ID
    private String         personName;          //人员名

    //车载终端存在
    private ImeiVehiclePortalBean vehicle ; //车辆信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getMajorTerminal() {
        return majorTerminal;
    }

    public void setMajorTerminal(Boolean majorTerminal) {
        this.majorTerminal = majorTerminal;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }



    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgQueryCode() {
        return orgQueryCode;
    }

    public void setOrgQueryCode(String orgQueryCode) {
        this.orgQueryCode = orgQueryCode;
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

    public ImeiVehiclePortalBean getVehicle() {
        return vehicle;
    }

    public void setVehicle(ImeiVehiclePortalBean vehicle) {
        this.vehicle = vehicle;
    }
}
