package com.dscomm.iecs.basedata.graphql.inputbean;

/**
 * 描述： 保存修改微型消防站
 *
 */
public class MiniatureStationSaveInputInfo  {

    private String id ; //主键

    private String stationName;//    名称

    private String stationAddress;//     机构地址

    private String districtCode;//    行政区代码

    private String squadronOrganizationId;// 主管中队id

    private String brigadeOrganizationId; // 所属大队id

    private String stationStatus; // 微站状态（ 0 正常   1停用    2其他）

    private String stationDutyType ; // 值班类型（ 0 7*24值班  1 非7*24值班 2 不值班）

    private String stationDispatchStatus ; // 调配状态（ 0 不可调派  1可调派 ）

    private String roadSection ; // 所属街道

    private String stationType ; // 微站类型

    private Integer personNum;//    人数

    private String stationEquipmentDesc;//  单位 装备_简要情况

    private String dutyPhone;// 值班_联系电话

    private String contactPerson ;//   单位 负责人

    private String contactPersonName ;//  单位负责人名称

    private String contactPersonPhone;// 单位负责人联系电话

    private String longitude;//  经度

    private String latitude;//  纬度

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }



    public String getSquadronOrganizationId() {
        return squadronOrganizationId;
    }

    public void setSquadronOrganizationId(String squadronOrganizationId) {
        this.squadronOrganizationId = squadronOrganizationId;
    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }



    public String getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(String stationStatus) {
        this.stationStatus = stationStatus;
    }

    public String getStationDutyType() {
        return stationDutyType;
    }

    public void setStationDutyType(String stationDutyType) {
        this.stationDutyType = stationDutyType;
    }

    public String getStationDispatchStatus() {
        return stationDispatchStatus;
    }

    public void setStationDispatchStatus(String stationDispatchStatus) {
        this.stationDispatchStatus = stationDispatchStatus;
    }

    public String getRoadSection() {
        return roadSection;
    }

    public void setRoadSection(String roadSection) {
        this.roadSection = roadSection;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getStationEquipmentDesc() {
        return stationEquipmentDesc;
    }

    public void setStationEquipmentDesc(String stationEquipmentDesc) {
        this.stationEquipmentDesc = stationEquipmentDesc;
    }

    public String getDutyPhone() {
        return dutyPhone;
    }

    public void setDutyPhone(String dutyPhone) {
        this.dutyPhone = dutyPhone;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
