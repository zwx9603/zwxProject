package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 消防机构信息
 *
 */
@Entity
@Table(name = "JGXX_XFJG")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OrganizationEntity extends BaseEntity {

    @Column(name = "XFJYJG_TYWYSBM", length = 32)
    private String idCode; //todo 字段 消防救援机构_通用唯一识别码

    @Column(name = "JGDM", length = 100)
    private String organizationCode;// 机构代码

//    @Column(name = "JGMC", length = 200)
    @Column(name = "DWMC", length = 200)
    private String organizationName;//todo 字段  机构名称

//    @Column(name = "JGJC", length = 200)
    @Column(name = "DWJC", length = 200)
    private String organizationShortName;//todo 字段 机构简称

//    @Column(name = "JGDZ", length = 400)
    @Column(name = "TXDZ", length = 400)
    private String organizationAddress;//todo 字段  机构地址

//    @Column(name = "JGMS", length = 1000)
    @Column(name = "ZHZ_JYQK", length = 1000)
    private String organizationDesc;//todo 字段 机构描述 职责_简要情况

    @Column(name = "SJJGID", length = 100)
    private String organizationParentId; // 上级机构ID

    @Column(name = "JGLB", length = 100)
    private String organizationCategoryCode;// 机构类别

    @Column(name = "JGLXDM", length = 100)
    private String organizationTypeCode;// 机构类型代码

//    @Column(name = "JGXZDM", length = 100)
    @Column(name = "XFJYJGXZDM", length = 100)
    private String organizationNatureCode;//todo 字段 机构性质代码

//    @Column(name = "XZQDM", length = 100)
    @Column(name = "XZQHDM", length = 100)
    private String districtCode;//todo 字段 行政区代码

//    @Column(name = "GIS_X", length = 50)
    @Column(name = "DQJD", length = 50)
    private String longitude;//todo 字段 经度

//    @Column(name = "GIS_Y", length = 50)
    @Column(name = "DQWD", length = 50)
    private String latitude;//todo 字段 纬度

//    @Column(name = "GIS_H", length = 50)
    @Column(name = "DQGD", length = 50)
    private String height;//todo 字段 高度

    @Column(name = "DDDH", length = 50)
    private String dispatchPhone;// 调度电话

    @Column(name = "YZBM", length = 50)
    private String postalCode;// todo 属性 邮政编码

    @Column(name = "CZHM", length = 50)
    private String faxNumber;// 传真号码

    @Column(name = "DZXX", length = 50)
    private String email;//todo 字段 属性 电子信箱


    @Column(name = "LXR", length = 100)
    private String contactPerson ;// todo 字段 单位联系人

    @Column(name = "LXR_XM", length = 200)
    private String contactPersonName ;//todo 字段 属性 单位联系人名称

    @Column(name = "LXDH", length = 50)
    private String contactPhone;// 单位联系电话

    @Column(name = "JGQZ")
    private Integer organizationWeight;// 机构权重

    @Column(name = "JGNBID", length = 100)
    private String organizationInsideId;// 机构内部ID

    @Column(name = "JGTREE", length = 800)
    private String organizationTree;// 机构Tree

    @Column(name = "CXMLJ", length = 800 )
    private String searchPath;//查询码路径

    @Column(name = "GLID", length = 100)
    private String relationId;// 关联ID

    @Column(name = "XQGLID", length = 100)
    private String precinctRelevanceId;// 辖区管理ID

    @Column(name = "SSYM", length = 200)
    private String domainName;// 所属域名

    @Column(name = "ZP", length = 400)
    private String picture;// 照片

    @Column(name = "ZQDWBZ" )
    private Integer symbolOfDutyUnit;// 执勤单位标志

    @Column(name = "XQMJ", length = 10)
    private String precinctArea;// 辖区面积

    @Column(name = "XQFW", length = 10)
    private String precinctRange;// 辖区范围

    @Column(name = "JGCXZT", length = 10)
    private String organizationRepealStatus;// 机构撤销状态

    @Column(name = "JGCXSJ")
    private  Long organizationRepealTime; // 机构撤销时间

    @Column(name = "PX")
    private Integer organizationOrderNum ; // 机构排序

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





    public String getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationShortName() {
        return organizationShortName;
    }

    public void setOrganizationShortName(String organizationShortName) {
        this.organizationShortName = organizationShortName;
    }

    public String getOrganizationDesc() {
        return organizationDesc;
    }

    public void setOrganizationDesc(String organizationDesc) {
        this.organizationDesc = organizationDesc;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationInsideId() {
        return organizationInsideId;
    }

    public void setOrganizationInsideId(String organizationInsideId) {
        this.organizationInsideId = organizationInsideId;
    }



    public String getOrganizationTree() {
        return organizationTree;
    }

    public void setOrganizationTree(String organizationTree) {
        this.organizationTree = organizationTree;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getOrganizationNatureCode() {
        return organizationNatureCode;
    }

    public void setOrganizationNatureCode(String organizationNatureCode) {
        this.organizationNatureCode = organizationNatureCode;
    }

    public String getOrganizationTypeCode() {
        return organizationTypeCode;
    }

    public void setOrganizationTypeCode(String organizationTypeCode) {
        this.organizationTypeCode = organizationTypeCode;
    }

    public String getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(String organizationParentId) {
        this.organizationParentId = organizationParentId;
    }

    public String getOrganizationCategoryCode() {
        return organizationCategoryCode;
    }

    public void setOrganizationCategoryCode(String organizationCategoryCode) {
        this.organizationCategoryCode = organizationCategoryCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setSymbolOfDutyUnit(Integer symbolOfDutyUnit) {
        this.symbolOfDutyUnit = symbolOfDutyUnit;
    }



    public Integer getOrganizationWeight() {
        return organizationWeight;
    }

    public void setOrganizationWeight(Integer organizationWeight) {
        this.organizationWeight = organizationWeight;
    }



    public String getDispatchPhone() {
        return dispatchPhone;
    }

    public void setDispatchPhone(String dispatchPhone) {
        this.dispatchPhone = dispatchPhone;
    }




    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getOrganizationRepealStatus() {
        return organizationRepealStatus;
    }

    public void setOrganizationRepealStatus(String organizationRepealStatus) {
        this.organizationRepealStatus = organizationRepealStatus;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    public String getPrecinctRelevanceId() {
        return precinctRelevanceId;
    }

    public void setPrecinctRelevanceId(String precinctRelevanceId) {
        this.precinctRelevanceId = precinctRelevanceId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public Integer getSymbolOfDutyUnit() {
        return symbolOfDutyUnit;
    }



    public Long getOrganizationRepealTime() {
        return organizationRepealTime;
    }

    public void setOrganizationRepealTime(Long organizationRepealTime) {
        this.organizationRepealTime = organizationRepealTime;
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

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPrecinctArea() {
        return precinctArea;
    }

    public void setPrecinctArea(String precinctArea) {
        this.precinctArea = precinctArea;
    }

    public String getPrecinctRange() {
        return precinctRange;
    }

    public void setPrecinctRange(String precinctRange) {
        this.precinctRange = precinctRange;
    }


    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Integer getOrganizationOrderNum() {
        return organizationOrderNum;
    }

    public void setOrganizationOrderNum(Integer organizationOrderNum) {
        this.organizationOrderNum = organizationOrderNum;
    }
}
