package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：应急联动单位
 *
 */
@Entity
@Table(name = "JGXX_YJLDDW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UnitEmergencyEntity extends BaseEntity {

    @Column(name = "YJLDDW_TYWYSBM", length = 32)
    private String idCode; //todo 字段 应急联动单位_通用唯一识别码

    @Column(name = "DWMC", length = 200)
    private String unitName; //单位名称

//    @Column(name = "DWDZ", length = 400)
    @Column(name = "DZMC", length = 400)
    private String unitAddress; //todo 字段 单位地址

//    @Column(name = "DWLX", length = 100)
    @Column(name = "YJLDDWLBDM", length = 100)
    private String unitType; //todo 字段 应急联动单位类别代码

    @Column(name = "CZHM", length = 50)
    private String faxNumber;// 传真号码

//    @Column(name = "YJFWNR", length = 800)
    @Column(name = "YJFWNR_JYQK", length = 800)
    private String emergencyContent; //todo 字段 应急服务内容_简要情况

    @Column(name = "DW_JYQK", length = 1000)
    private String unitDesc;//todo 字段 单位_简要情况

    @Column(name = "LXR", length = 100)
    private String contactPerson ;// 联系人

    @Column(name = "LXR_XM", length = 200)
    private String contactPersonName ;//todo 字段 联系人 姓名

//    @Column(name = "LXDH", length = 50)
    @Column(name = "LXR_LXDH", length = 50)
    private String contactPhone;//todo  联系人 联系电话

//    @Column(name = "GIS_X", length = 50)
    @Column(name = "DQJD", length = 50)
    private String longitude;//todo 字段 经度

//    @Column(name = "GIS_Y", length = 50)
    @Column(name = "DQWD", length = 50)
    private String latitude;//todo 字段 纬度

//    @Column(name = "GIS_H", length = 50)
    @Column(name = "DQGD", length = 50)
    private String height;//todo 字段 高度

//    @Column(name = "SSXQ", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String organizationId; // todo 字段 维护消防机构ID 消防救援机构_通用唯一识别码

//    @Column(name = "SSXZQDM", length = 100)
    @Column(name = "XZQHDM", length = 100)
    private String districtCode;//todo 字段 行政区代码

    @Column(name = "GLID", length = 100)
    private String gisRelationId ;//GIS关联ID

    @Column(name = "ZP", length = 400)
    private String picture;// 照片

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本



    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getEmergencyContent() {
        return emergencyContent;
    }

    public void setEmergencyContent(String emergencyContent) {
        this.emergencyContent = emergencyContent;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }



    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGisRelationId() {
        return gisRelationId;
    }

    public void setGisRelationId(String gisRelationId) {
        this.gisRelationId = gisRelationId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
