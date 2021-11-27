package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：联勤保障单位
 *
 */
@Entity
@Table(name = "JGXX_LQBZDW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UnitJointServiceEntity extends BaseEntity {

    @Column(name = "LQBZDW_TYWYSBM", length = 32)
    private String idCode; //todo 字段 联勤保障单位_通用唯一识别码

    @Column(name = "DWMC", length = 200)
    private String unitName; //单位名称

    @Column(name = "DZMC", length = 400)
    private String unitAddress; //todo 字段 单位地址

    @Column(name = "CZHM", length = 50)
    private String faxNumber;//todo 字段 传真号码

    @Column(name = "DW_JYQK", length = 1000)
    private String unitDesc;//todo 字段 单位_简要情况

//    @Column(name = "ZYBZLBDM", length = 100)
    @Column(name = "LQBZLBDM", length = 100)
    private String mainSupportCategoryCode; //todo 字段 主要保障类别代码

//    @Column(name = "BZNL", length = 400 )
    @Column(name = "BZNL_JYQK", length = 800 )
    private String supportability; //todo 字段 保障能力_简要情况

    @Column(name = "BZGS_JYQK", length = 800 )
    private String  supportDesc; //todo 字段 保障概述_简要情况

    @Column(name = "LXR", length = 100)
    private String contactPerson ;//todo 字段 联系人

    @Column(name = "LXR_XM", length = 200)
    private String contactPersonName ;//todo 字段 联系人 姓名

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

//    @Column(name = "WHXFJGID", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String organizationId; // todo 字段 维护消防机构ID 消防救援机构_通用唯一识别码

    @Column(name = "XZQHDM", length = 100)
    private String districtCode;//todo 字段 行政区代码

    @Column(name = "YJZBDH", length = 50)
    private String dutyPhone; //应急值班电话

    @Column(name = "GLID", length = 100)
    private String relationId; //关联ID

    @Column(name = "SFWNBJG", length = 10)
    private String whetherInnerOrganization; //是否为内部机构

    @Column(name = "NBJGID", length = 100 )
    private String innerOrganizationId; //内部机构ID

    @Column(name = "WBDWID", length = 100 )
    private String outerOrganizationId; //外部机构ID

    @Column(name = "YYDJDM", length = 100 )
    private String hospitalLevelCode ; //医院等级代码

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


    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public String getSupportDesc() {
        return supportDesc;
    }

    public void setSupportDesc(String supportDesc) {
        this.supportDesc = supportDesc;
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

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

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

    public String getSupportability() {
        return supportability;
    }

    public void setSupportability(String supportability) {
        this.supportability = supportability;
    }

    public String getMainSupportCategoryCode() {
        return mainSupportCategoryCode;
    }

    public void setMainSupportCategoryCode(String mainSupportCategoryCode) {
        this.mainSupportCategoryCode = mainSupportCategoryCode;
    }

    public String getDutyPhone() {
        return dutyPhone;
    }

    public void setDutyPhone(String dutyPhone) {
        this.dutyPhone = dutyPhone;
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

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }


    public String getWhetherInnerOrganization() {
        return whetherInnerOrganization;
    }

    public void setWhetherInnerOrganization(String whetherInnerOrganization) {
        this.whetherInnerOrganization = whetherInnerOrganization;
    }

    public String getInnerOrganizationId() {
        return innerOrganizationId;
    }

    public void setInnerOrganizationId(String innerOrganizationId) {
        this.innerOrganizationId = innerOrganizationId;
    }

    public String getOuterOrganizationId() {
        return outerOrganizationId;
    }

    public void setOuterOrganizationId(String outerOrganizationId) {
        this.outerOrganizationId = outerOrganizationId;
    }

    public String getHospitalLevelCode() {
        return hospitalLevelCode;
    }

    public void setHospitalLevelCode(String hospitalLevelCode) {
        this.hospitalLevelCode = hospitalLevelCode;
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



}
