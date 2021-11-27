package com.dscomm.iecs.accept.graphql.inputbean;


import java.util.Date;

/**
 * 描述:案件基本信息
 *
 */
public class IncidentgradeputInfo {

    private  String id  ; //警情id

    private String incidentSource;  //案件来源

    private String acceptanceId ; //受理单id

    private String incidentNumber; // 案件编号（日期坐席工号） //后端 生成

    private String registerModeCode;// 立案方式代码

    private String squadronOrganizationId;// 主管中队

    private String brigadeOrganizationId; // 所属大队

    private Date registerIncidentTime;// 立案时间

    private String title; //  名称

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentLevelCode;// 案件等级代码

    private String disposalObjectCode;// 处置对象代码

    private String incidentNatureCode;// 案件性质代码

    private String incidentStatusCode;// 案件状态代码

    private Integer whetherImportSign;// 是否重大案件标识

    private String keyUnitId;// 重点单位

    private String supplementInfo;// 补充信息

    private String attentions;// 注意事项

    private String registerOrganizationId; // 立案机构ID 服务器填充

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
    }

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getRegisterModeCode() {
        return registerModeCode;
    }

    public void setRegisterModeCode(String registerModeCode) {
        this.registerModeCode = registerModeCode;
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

    public Date getRegisterIncidentTime() {
        return registerIncidentTime;
    }

    public void setRegisterIncidentTime(Date registerIncidentTime) {
        this.registerIncidentTime = registerIncidentTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCrimeAddress() {
        return crimeAddress;
    }

    public void setCrimeAddress(String crimeAddress) {
        this.crimeAddress = crimeAddress;
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

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getIncidentNatureCode() {
        return incidentNatureCode;
    }

    public void setIncidentNatureCode(String incidentNatureCode) {
        this.incidentNatureCode = incidentNatureCode;
    }

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public Integer getWhetherImportSign() {
        return whetherImportSign;
    }

    public void setWhetherImportSign(Integer whetherImportSign) {
        this.whetherImportSign = whetherImportSign;
    }

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getSupplementInfo() {
        return supplementInfo;
    }

    public void setSupplementInfo(String supplementInfo) {
        this.supplementInfo = supplementInfo;
    }

    public String getAttentions() {
        return attentions;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions;
    }

    public String getRegisterOrganizationId() {
        return registerOrganizationId;
    }

    public void setRegisterOrganizationId(String registerOrganizationId) {
        this.registerOrganizationId = registerOrganizationId;
    }
}
