package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**水源基本信息bean*/
public class WaterBaseBean extends BaseBean {

    private String number;//编码

    private String waterCode; //水源编号

    private String waterName; //水源名称

    private String waterAddr; //水源地址

    private String waterType; //水源类型

    private String waterAttribute; //水源属性信息ID

    private String organizationId; //机构ID

    private String organizationName; //机构名称

    private String pipeForm;   //管网形式

    private String pipeFormName;   //todo 字段 管网形式名称


    private String waterIntake; //取水形式

    private String waterIntakeName; //todo 字段 取水形式名称

    private String placementForm; //放置形式

    private String placementFormName ; //todo 字段 放置形式名称

    private String availableStatus; //可用状态

    private String availableStatusName; //todo 字段 可用状态名称

    private String waterStatus; //水源状态

    private String waterStatusName; //todo 字段 水源状态  消防设施状况分类与代码名称

    private String interfaceForm;   //接口形式

    private String interfaceFormName;   //todo 字段 接口形式名称

    private String hydrantType; //消防栓种类

    private String hydrantTypeName; //todo 字段 消防栓种类名称

    private String nature; //性质

    private String pipeNetwork;   //所属管网

    private String setTheForm;   //设置形式

    private String waterUnit;   //供水单位

    private String telephone;   //供水单位联系方式

    private String pipeDiameter;   //管网直径

    private String pipePressure;   //管网压力

    private String roadSection;   //所属路段

    private String district ; //所属辖区

    private String longitude; //经度

    private String latitude; //纬度

    private String altitude; //高度

    private String mapLongitude; //上图经度

    private String mapLatitude; //上图纬度

    private String mapAltitude; //上图高度


    private Long waterBuildTime; //建造时间

    private Long enableTime;//启用时间

    private String photoFileURL; //照片文件URL
    private String eastUrl; //方位图东URL
    private String southUrl; //方位图南URL
    private String westUrl; //方位图西URL
    private String northUrl; //方位图北URL
    private String nearEastUrl; //毗邻关系东URL
    private String nearSouthUrl; //毗邻关系南URL
    private String nearWestUrl; //毗邻关系西URL
    private String nearNorthUrl; //毗邻关系北URL

    private String waterHandlersId; //操作员ID

    private String auditStatus; //审核状态

    private Integer recordStatus; //记录状态

    private String pipeNetworkName;   //todo 字段 所属管网名称

    private String manageUnit;   //todo 字段 管理_单位名称

    private String maintenanceUnit;   //todo 字段 维保_单位名称

    private String contact;   //todo 字段 联系人

    private String contactPersonName;   //todo 字段 联系人 姓名

    private String roadSectionDesc;   //todo 字段 道路路口路段_简要情况

    private String url;//todo 字段 方位图

    private String nearUrl ;//todo 字段 实景图


    private String waterCraneHeight;//  水鹤高度

    private String flowrate;   //  流量

    private String inletDiameter;//  进水直径

    private String effluentDiameter;//  出水直径

    private Integer waterDriveway;//加水车道

    private String belongingWaterId; //todo 字段 所在水源ID

    private String belongingWaterName; //todo 字段 所在水源名称

    private String intakeHeight;//  取水_高度

    private String  elevationHeight;//水源标高差_高度

    private String parkingPosition;//停车位置

    private Integer parkingNum;//停车数量

    private String naturalWaterTypeCode ;//天然水源类型　

    private String naturalWaterTypeName ; //天然水源类型名

    private String waterNaturalSourceHeight;//  天然水源高度

    private String storage;// 容积 单位：立方米

    private String area;// 容积 单位：立方米

    private String waterQualityDesc ;//水质情况_简要情况

    private String seasonalChangesDesc;//四季变化_简要情况

    private Integer whetherDrySeason;//有无枯水期_判断标识

    private String drySeasonDesc ;//枯水期跨度_简要情况

    private String waterStorage;//储水量 容积 单位：立方米


    public String getWaterCraneHeight() {
        return waterCraneHeight;
    }

    public void setWaterCraneHeight(String waterCraneHeight) {
        this.waterCraneHeight = waterCraneHeight;
    }

    public String getFlowrate() {
        return flowrate;
    }

    public void setFlowrate(String flowrate) {
        this.flowrate = flowrate;
    }

    public String getInletDiameter() {
        return inletDiameter;
    }

    public void setInletDiameter(String inletDiameter) {
        this.inletDiameter = inletDiameter;
    }

    public String getEffluentDiameter() {
        return effluentDiameter;
    }

    public void setEffluentDiameter(String effluentDiameter) {
        this.effluentDiameter = effluentDiameter;
    }

    public Integer getWaterDriveway() {
        return waterDriveway;
    }

    public void setWaterDriveway(Integer waterDriveway) {
        this.waterDriveway = waterDriveway;
    }

    public String getBelongingWaterId() {
        return belongingWaterId;
    }

    public void setBelongingWaterId(String belongingWaterId) {
        this.belongingWaterId = belongingWaterId;
    }

    public String getBelongingWaterName() {
        return belongingWaterName;
    }

    public void setBelongingWaterName(String belongingWaterName) {
        this.belongingWaterName = belongingWaterName;
    }

    public String getIntakeHeight() {
        return intakeHeight;
    }

    public void setIntakeHeight(String intakeHeight) {
        this.intakeHeight = intakeHeight;
    }

    public String getElevationHeight() {
        return elevationHeight;
    }

    public void setElevationHeight(String elevationHeight) {
        this.elevationHeight = elevationHeight;
    }

    public String getParkingPosition() {
        return parkingPosition;
    }

    public void setParkingPosition(String parkingPosition) {
        this.parkingPosition = parkingPosition;
    }

    public Integer getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(Integer parkingNum) {
        this.parkingNum = parkingNum;
    }

    public String getNaturalWaterTypeCode() {
        return naturalWaterTypeCode;
    }

    public void setNaturalWaterTypeCode(String naturalWaterTypeCode) {
        this.naturalWaterTypeCode = naturalWaterTypeCode;
    }

    public String getNaturalWaterTypeName() {
        return naturalWaterTypeName;
    }

    public void setNaturalWaterTypeName(String naturalWaterTypeName) {
        this.naturalWaterTypeName = naturalWaterTypeName;
    }

    public String getWaterNaturalSourceHeight() {
        return waterNaturalSourceHeight;
    }

    public void setWaterNaturalSourceHeight(String waterNaturalSourceHeight) {
        this.waterNaturalSourceHeight = waterNaturalSourceHeight;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWaterQualityDesc() {
        return waterQualityDesc;
    }

    public void setWaterQualityDesc(String waterQualityDesc) {
        this.waterQualityDesc = waterQualityDesc;
    }

    public String getSeasonalChangesDesc() {
        return seasonalChangesDesc;
    }

    public void setSeasonalChangesDesc(String seasonalChangesDesc) {
        this.seasonalChangesDesc = seasonalChangesDesc;
    }

    public Integer getWhetherDrySeason() {
        return whetherDrySeason;
    }

    public void setWhetherDrySeason(Integer whetherDrySeason) {
        this.whetherDrySeason = whetherDrySeason;
    }

    public String getDrySeasonDesc() {
        return drySeasonDesc;
    }

    public void setDrySeasonDesc(String drySeasonDesc) {
        this.drySeasonDesc = drySeasonDesc;
    }

    public String getWaterStorage() {
        return waterStorage;
    }

    public void setWaterStorage(String waterStorage) {
        this.waterStorage = waterStorage;
    }

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

    public String getPipeFormName() {
        return pipeFormName;
    }

    public void setPipeFormName(String pipeFormName) {
        this.pipeFormName = pipeFormName;
    }

    public String getWaterIntakeName() {
        return waterIntakeName;
    }

    public void setWaterIntakeName(String waterIntakeName) {
        this.waterIntakeName = waterIntakeName;
    }

    public String getPlacementFormName() {
        return placementFormName;
    }

    public void setPlacementFormName(String placementFormName) {
        this.placementFormName = placementFormName;
    }

    public String getAvailableStatusName() {
        return availableStatusName;
    }

    public void setAvailableStatusName(String availableStatusName) {
        this.availableStatusName = availableStatusName;
    }

    public String getWaterStatusName() {
        return waterStatusName;
    }

    public void setWaterStatusName(String waterStatusName) {
        this.waterStatusName = waterStatusName;
    }

    public String getInterfaceFormName() {
        return interfaceFormName;
    }

    public void setInterfaceFormName(String interfaceFormName) {
        this.interfaceFormName = interfaceFormName;
    }

    public String getHydrantTypeName() {
        return hydrantTypeName;
    }

    public void setHydrantTypeName(String hydrantTypeName) {
        this.hydrantTypeName = hydrantTypeName;
    }
}
