package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：值班汇总信息 由 值班信息实体 值班类型 以及机构 人员 关联组成 视图
 */
@Entity
@Table(name = "V_ZBGL_ZBXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OnDutySummaryEntity extends BaseEntity {

    @Column(name = "ZHB_TYWYSBM", length = 100)
    private String idCode; //todo  字段 值班_通用唯一识别码

    @Column(name = "SSDWYTHID", length = 100)
    private String organizationIntegrated; //单位一体化id

//    @Column(name = "SSDW", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String organizationId; //todo 字段 机构Id 消防救援机构_通用唯一识别码

//    @Column(name = "SSDWMC", length = 200)
    @Column(name = "XFJYJG_TYWYSMC", length = 200)
    private String organizationName; //机构名称

    @Column(name = "XZQHDM", length = 100)
    private String districtCode; //todo 字段 行政区划代码

//    @Column(name = "ZBZZ", length = 400)
    @Column(name = "ZBRZ_JYQK", length = 400)
    private String responsibilities;  //todo 字段 值班职责 值班日志_简要情况

    @Column(name = "BZ", length = 400)
    private String remarks;    //todo 字段 备注

//    @Column(name = "ZBRQ")
    @Column(name = "ZHB_RQ")
    private Long onDutyTime; //值班日期

//    @Column(name = "ZBRID", length = 100)
    @Column(name = "ZBRY", length = 100)
    private String onDutyPersonId; //todo 字段 值班人id

    @Column(name = "ZBRBH", length = 100)
    private String onDutyPersonNumber; //值班人编号

//    @Column(name = "ZBRXM", length = 200)
    @Column(name = "ZBRY_XM", length = 200)
    private String onDutyPersonName; //todo 字段 值班人姓名

//    @Column(name = "LXDH", length = 50)
    @Column(name = "ZBRY_LXDH", length = 50)
    private String contactNumber; // todo 字段 联系电话

//    @Column(name = "ZBLX", length = 100)
    @Column(name = "ZBRY_XFZBJSLBDM", length = 100)
    private String onDutyType; //todo 字段 值班类型 消防值班角色类别代码

    //    @Column(name = "ZBLX", length = 100)
    @Column(name = "ZBRY_XFZBJSLBMC", length = 100)
    private String onDutyTypeName; //todo 字段 值班类型 消防值班角色类别名称

//    @Column(name = "GWMC", length = 200)
    @Column(name = "ZBRY_XFGWFLYDM", length = 100)
    private String onDutyCode ;  //todo 字段  消防岗位分类与代码

    @Column(name = "ZBRY_DWMC", length = 200)
    private String onDutyPersonUnitName ;  //todo 字段 值班人员 单位名称

    @Column(name = "ZFB_PDBS", length = 100)
    private String whetherDeputy ;  //todo 字段 正副班_判断标识


    @Column(name = "ZBRC", length = 10)
    private String onDutyNumber;   //值班人次

    @Column(name = "XSXH")
    private Integer showNumber;    //

    public String getOnDutyTypeName() {
        return onDutyTypeName;
    }

    public void setOnDutyTypeName(String onDutyTypeName) {
        this.onDutyTypeName = onDutyTypeName;
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

    public String getOrganizationIntegrated() {
        return organizationIntegrated;
    }

    public void setOrganizationIntegrated(String organizationIntegrated) {
        this.organizationIntegrated = organizationIntegrated;
    }

    public Long getOnDutyTime() {
        return onDutyTime;
    }

    public void setOnDutyTime(Long onDutyTime) {
        this.onDutyTime = onDutyTime;
    }

    public String getOnDutyType() {
        return onDutyType;
    }

    public void setOnDutyType(String onDutyType) {
        this.onDutyType = onDutyType;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOnDutyCode() {
        return onDutyCode;
    }

    public void setOnDutyCode(String onDutyCode) {
        this.onDutyCode = onDutyCode;
    }

    public String getOnDutyPersonUnitName() {
        return onDutyPersonUnitName;
    }

    public void setOnDutyPersonUnitName(String onDutyPersonUnitName) {
        this.onDutyPersonUnitName = onDutyPersonUnitName;
    }

    public String getWhetherDeputy() {
        return whetherDeputy;
    }

    public void setWhetherDeputy(String whetherDeputy) {
        this.whetherDeputy = whetherDeputy;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getOnDutyPersonId() {
        return onDutyPersonId;
    }

    public void setOnDutyPersonId(String onDutyPersonId) {
        this.onDutyPersonId = onDutyPersonId;
    }

    public String getOnDutyPersonNumber() {
        return onDutyPersonNumber;
    }

    public void setOnDutyPersonNumber(String onDutyPersonNumber) {
        this.onDutyPersonNumber = onDutyPersonNumber;
    }

    public String getOnDutyPersonName() {
        return onDutyPersonName;
    }

    public void setOnDutyPersonName(String onDutyPersonName) {
        this.onDutyPersonName = onDutyPersonName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOnDutyNumber() {
        return onDutyNumber;
    }

    public void setOnDutyNumber(String onDutyNumber) {
        this.onDutyNumber = onDutyNumber;
    }

    public Integer getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(Integer showNumber) {
        this.showNumber = showNumber;
    }
}
