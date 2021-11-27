package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 微型消防站
 *
 */
@Entity
@Table(name = "JGXX_WXXFZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MiniatureStationEntity extends BaseEntity {

    @Column(name = "WXXFZ_TYWYSBM", length = 32)
    private String idCode; //    微型消防站_通用唯一识别码

    @Column(name = "MC", length = 200)
    private String stationName;//    名称

    @Column(name = "DZMC", length = 400)
    private String stationAddress;//     机构地址

    @Column(name = "XZQHDM", length = 100)
    private String districtCode;//    行政区代码

    @Column(name = "sszd", length = 100)
    private String squadronOrganizationId;// 主管中队id

    @Column(name = "sjjgid", length = 100)
    private String brigadeOrganizationId; // 所属大队id

    @Column(name = "wzzt", length = 100)
    private String stationStatus; // 微站状态（ 1正常   0停用   2其他）

    @Column(name = "zblx", length = 100)
    private String stationDutyType ; // 值班类型（0 7*24值班 1 非7*24值班 2 不值班）

    @Column(name = "dpzt", length = 100)
    private String stationDispatchStatus ; // 调配状态（0 不可调派 1可调派 ）

    @Column(name = "ssjd", length = 200)
    private String roadSection ; // 所属街道

    @Column(name = "wzlx", length = 100)
    private String stationType ; // 微站类型

    @Column(name = "RS" )
    private Integer personNum;//    人数

    @Column(name = "ZB_JYQK", length = 1000)
    private String stationEquipmentDesc;//  单位 装备_简要情况

    @Column(name = "ZB_LXDH", length = 50)
    private String dutyPhone;// 值班_联系电话

//    @Column(name = "FZR", length = 100)
//    private String contactPerson ;//   单位 负责人

    @Column(name = "FZR_XM", length = 200)
    private String contactPersonName ;//  单位负责人名称

    @Column(name = "FZR_LXDH", length = 50)
    private String contactPersonPhone;// 单位负责人联系电话

    @Column(name = "DQJD", length = 50)
    private String longitude;//  经度

    @Column(name = "DQWD", length = 50)
    private String latitude;//  纬度

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
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

//    public String getContactPerson() {
//        return contactPerson;
//    }
//
//    public void setContactPerson(String contactPerson) {
//        this.contactPerson = contactPerson;
//    }

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

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }
}
