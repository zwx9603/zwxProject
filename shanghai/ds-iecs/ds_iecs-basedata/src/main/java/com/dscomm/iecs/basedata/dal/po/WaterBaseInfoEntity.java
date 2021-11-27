package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 水源基本信息
 *
 */
@Entity
@Table(name = "SY_SYJBXX")
@DynamicUpdate(true)
@DynamicInsert(true)
public class WaterBaseInfoEntity extends BaseEntity {


    @Column(name = "BM", length =100 )
    private String number;//编码

    @Column(name = "SYBH", length =100 )
    private String waterCode; //水源编号

//    @Column(name = "SYMC", length = 200)
    @Column(name = "MC", length = 200)
    private String waterName; //todo 字段 水源名称

//    @Column(name = "SYDZ", length = 400)
    @Column(name = "DZMC", length = 400)
    private String waterAddr; //todo 字段 水源地址

    @Column(name = "SYLX", length = 100)
    private String waterType; //  水源类型 大类 主要 区分 5 水源类型

    @Column(name = "SYSXXXID", length =100)
    private String waterAttribute; //水源属性信息ID

//    @Column(name = "JGID", length =100)
    @Column(name = "XFJYJG_TYWYSBM", length =100)
    private String organizationId; //todo 字段 消防机构ID

//    @Column(name = "JGMC" ,length = 200 )
    @Column(name = "XFJYJG_TYWYSMC" ,length = 200 )
    private String organizationName; //todo 字段 消防机构名称

//    @Column(name = "SSXQ" ,length = 100 )
    @Column(name = "XZQHDM", length = 100 )
    private String district ; // todo 字段 所属辖区

//    @Column(name = "QSXS", length = 800)
    @Column(name = "QSXS_JYQK", length = 800)
    private String waterIntake; //todo 字段 取水形式

//    @Column(name = "FZXS", length = 100)
    @Column(name = "XHSFZXSLBDM", length = 100)
    private String placementForm; //todo 字段 放置形式

//    @Column(name = "KYZT", length = 100)
    @Column(name = "SYKYZTLBDM", length = 100)
    private String availableStatus; //todo 字段 可用状态

//    @Column(name = "SYZT", length = 100)
    @Column(name = "XFSSZKFLYDM" , length =  100 )
    private String waterStatus; //todo 字段 水源状态  消防设施状况分类与代码

    @Column(name = "XZ", length = 100)
    private String nature; //性质

    @Column(name = "GWID", length =100 )
    private String pipeNetwork;   //   所属管网

//    @Column(name = "GWMC", length =200)
    @Column(name = "SSGW_MC", length =200)
    private String pipeNetworkName;   //todo 字段 所属管网名称

    @Column(name = "SZXS", length = 800)
    private String setTheForm;   //设置形式

//    @Column(name = "GSDW", length = 200)
    @Column(name = "GS_DWMC", length = 200)
    private String waterUnit;   //todo 字段 供水单位

    @Column(name = "GL_DWMC", length = 200)
    private String manageUnit;   //todo 字段 管理_单位名称

    @Column(name = "WB_DWMC", length = 200)
    private String maintenanceUnit;   //todo 字段 维保_单位名称

    @Column(name = "LXR", length = 100)
    private String contact;   //todo 字段 联系人

    @Column(name = "LXR_XM", length = 100)
    private String contactPersonName;   //todo 字段 联系人 姓名

//    @Column(name = "LXFS", length = 50)
    @Column(name = "LXR_LXDH", length = 50)
    private String telephone;   //todo 字段 联系人 联系方式


//    @Column(name = "GWXS", length = 100)
    @Column(name = "XFJSGWXSLXDM", length = 100)
    private String pipeForm;   //todo 字段 管网形式

//    @Column(name = "GWZJ" ,length = 50 )
    @Column(name = "GWZJ_KD" ,length = 50 )
    private String pipeDiameter;   //todo 字段 管网直径

//    @Column(name = "GWYL" ,length = 50 )
    @Column(name = "GW_YL" ,length = 50 )
    private String pipePressure;   //todo 字段 管网压力

//    @Column(name = "JKXS", length = 100)
    @Column(name = "XFSDJKLXDM", length = 100)
    private String interfaceForm;   //todo 字段 接口形式

//    @Column(name = "SSLD"  ,length = 200 )
    @Column(name = "SSLD_MC"  ,length = 200 )
    private String roadSection;   //todo 字段 所属路段

    @Column(name = "DLLKLD_JYQK"  ,length = 800 )
    private String roadSectionDesc;   //todo 字段 道路路口路段_简要情况

//    @Column(name ="XFSLX" ,length = 100 )
    @Column(name = "XHSLBDM" , length =  100 )
    private String hydrantType; //todo 字段 消防栓种类


//    @Column(name = "GIS_X" ,length = 50 )
    @Column(name = "DQJD" ,length = 50 )
    private String longitude; //todo 字段 经度

//    @Column(name = "GIS_Y" ,length = 50 )
    @Column(name = "DQWD" ,length = 50 )
    private String latitude; //todo 字段 纬度

//    @Column(name = "GIS_H" ,length = 50 )
    @Column(name = "DQGD" ,length = 50 )
    private String altitude; //todo 字段 高度

    @Column(name = "GIS_X_MAP" ,length = 50 )
    private String mapLongitude; //上图经度

    @Column(name = "GIS_Y_MAP" ,length = 50 )
    private String mapLatitude; //上图纬度

    @Column(name = "GIS_H_MAP" ,length = 50 )
    private String mapAltitude; //上图高度


//    @Column(name = "JZSJ")
    @Column(name = "JIZ_RQ")
    private Long waterBuildTime; //todo 字段 建造时间

//    @Column(name = "QYSJ")
    @Column(name = "QY_RQ")
    private Long enableTime;//todo 字段 启用时间







    @Column(name = "ZPWJURL", length = 200)
    private String photoFileURL; //照片文件URL

    @Column(name = "FWT", length = 100 )
    private String url;//todo 字段 方位图

    @Column(name = "FWTD", length =200)
    private String eastUrl; //方位图东URL
    @Column(name = "FWTN", length = 200)
    private String southUrl; //方位图南URL
    @Column(name = "FWTX", length = 200)
    private String westUrl; //方位图西URL
    @Column(name = "FWTB", length = 200)
    private String northUrl; //方位图北URL

    @Column(name = "SJT" , length = 100 )
    private String nearUrl ;//todo 字段 实景图

    @Column(name = "BLGXD", length = 200)
    private String nearEastUrl; //毗邻关系东URL
    @Column(name = "BLGXN", length = 200)
    private String nearSouthUrl; //毗邻关系南URL
    @Column(name = "BLGXX", length = 200)
    private String nearWestUrl; //毗邻关系西URL
    @Column(name = "BLGXB", length = 200)
    private String nearNorthUrl; //毗邻关系北URL

    @Column(name = "CZYID", length =50)
    private String waterHandlersId; //操作员ID

    @Column(name = "SHZT", length = 100)
    private String auditStatus; //审核状态

    @Column(name = "JLZT"   )
    private Integer recordStatus; //记录状态


    public String getPipeNetworkName() {
        return pipeNetworkName;
    }

    public void setPipeNetworkName(String pipeNetworkName) {
        this.pipeNetworkName = pipeNetworkName;
    }

    public String getManageUnit() {
        return manageUnit;
    }

    public void setManageUnit(String manageUnit) {
        this.manageUnit = manageUnit;
    }

    public String getMaintenanceUnit() {
        return maintenanceUnit;
    }

    public void setMaintenanceUnit(String maintenanceUnit) {
        this.maintenanceUnit = maintenanceUnit;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getRoadSectionDesc() {
        return roadSectionDesc;
    }

    public void setRoadSectionDesc(String roadSectionDesc) {
        this.roadSectionDesc = roadSectionDesc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWaterCode() {
        return waterCode;
    }

    public void setWaterCode(String waterCode) {
        this.waterCode = waterCode;
    }

    public String getWaterName() {
        return waterName;
    }

    public void setWaterName(String waterName) {
        this.waterName = waterName;
    }

    public String getWaterAddr() {
        return waterAddr;
    }

    public void setWaterAddr(String waterAddr) {
        this.waterAddr = waterAddr;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getWaterAttribute() {
        return waterAttribute;
    }

    public void setWaterAttribute(String waterAttribute) {
        this.waterAttribute = waterAttribute;
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

    public String getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(String waterIntake) {
        this.waterIntake = waterIntake;
    }

    public String getPlacementForm() {
        return placementForm;
    }

    public void setPlacementForm(String placementForm) {
        this.placementForm = placementForm;
    }

    public String getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(String availableStatus) {
        this.availableStatus = availableStatus;
    }

    public String getWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(String waterStatus) {
        this.waterStatus = waterStatus;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getPipeNetwork() {
        return pipeNetwork;
    }

    public void setPipeNetwork(String pipeNetwork) {
        this.pipeNetwork = pipeNetwork;
    }

    public String getSetTheForm() {
        return setTheForm;
    }

    public void setSetTheForm(String setTheForm) {
        this.setTheForm = setTheForm;
    }

    public String getWaterUnit() {
        return waterUnit;
    }

    public void setWaterUnit(String waterUnit) {
        this.waterUnit = waterUnit;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPipeForm() {
        return pipeForm;
    }

    public void setPipeForm(String pipeForm) {
        this.pipeForm = pipeForm;
    }

    public String getPipeDiameter() {
        return pipeDiameter;
    }

    public void setPipeDiameter(String pipeDiameter) {
        this.pipeDiameter = pipeDiameter;
    }

    public String getPipePressure() {
        return pipePressure;
    }

    public void setPipePressure(String pipePressure) {
        this.pipePressure = pipePressure;
    }

    public String getInterfaceForm() {
        return interfaceForm;
    }

    public void setInterfaceForm(String interfaceForm) {
        this.interfaceForm = interfaceForm;
    }

    public String getRoadSection() {
        return roadSection;
    }

    public void setRoadSection(String roadSection) {
        this.roadSection = roadSection;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHydrantType() {
        return hydrantType;
    }

    public void setHydrantType(String hydrantType) {
        this.hydrantType = hydrantType;
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

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getMapLongitude() {
        return mapLongitude;
    }

    public void setMapLongitude(String mapLongitude) {
        this.mapLongitude = mapLongitude;
    }

    public String getMapLatitude() {
        return mapLatitude;
    }

    public void setMapLatitude(String mapLatitude) {
        this.mapLatitude = mapLatitude;
    }

    public String getMapAltitude() {
        return mapAltitude;
    }

    public void setMapAltitude(String mapAltitude) {
        this.mapAltitude = mapAltitude;
    }

    public Long getWaterBuildTime() {
        return waterBuildTime;
    }

    public void setWaterBuildTime(Long waterBuildTime) {
        this.waterBuildTime = waterBuildTime;
    }

    public Long getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Long enableTime) {
        this.enableTime = enableTime;
    }

    public String getPhotoFileURL() {
        return photoFileURL;
    }

    public void setPhotoFileURL(String photoFileURL) {
        this.photoFileURL = photoFileURL;
    }

    public String getEastUrl() {
        return eastUrl;
    }

    public void setEastUrl(String eastUrl) {
        this.eastUrl = eastUrl;
    }

    public String getSouthUrl() {
        return southUrl;
    }

    public void setSouthUrl(String southUrl) {
        this.southUrl = southUrl;
    }

    public String getWestUrl() {
        return westUrl;
    }

    public void setWestUrl(String westUrl) {
        this.westUrl = westUrl;
    }

    public String getNorthUrl() {
        return northUrl;
    }

    public void setNorthUrl(String northUrl) {
        this.northUrl = northUrl;
    }

    public String getNearEastUrl() {
        return nearEastUrl;
    }

    public void setNearEastUrl(String nearEastUrl) {
        this.nearEastUrl = nearEastUrl;
    }

    public String getNearSouthUrl() {
        return nearSouthUrl;
    }

    public void setNearSouthUrl(String nearSouthUrl) {
        this.nearSouthUrl = nearSouthUrl;
    }

    public String getNearWestUrl() {
        return nearWestUrl;
    }

    public void setNearWestUrl(String nearWestUrl) {
        this.nearWestUrl = nearWestUrl;
    }

    public String getNearNorthUrl() {
        return nearNorthUrl;
    }

    public void setNearNorthUrl(String nearNorthUrl) {
        this.nearNorthUrl = nearNorthUrl;
    }

    public String getWaterHandlersId() {
        return waterHandlersId;
    }

    public void setWaterHandlersId(String waterHandlersId) {
        this.waterHandlersId = waterHandlersId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNearUrl() {
        return nearUrl;
    }

    public void setNearUrl(String nearUrl) {
        this.nearUrl = nearUrl;
    }
}
