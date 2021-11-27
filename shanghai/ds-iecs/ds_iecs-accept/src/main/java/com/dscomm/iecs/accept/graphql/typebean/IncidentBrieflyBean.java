package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;


/**
 * 描述:案件简要信息 主要用以列表展示
 *
 */
public class IncidentBrieflyBean extends BaseBean {



    /**  end 警情电话信息 */

    private String incidentNumber; // 案件编号（日期坐席工号）

    private String squadronOrganizationId;// 主管中队

    private String squadronOrganizationName;// 主管中队名称

    private String brigadeOrganizationId; // 所属大队

    private String brigadeOrganizationName; // 所属大队名称

    private String districtCode;//  行政区编码

    private String districtName;//  行政区名称


    private String registerModeCode;// 立案方式代码

    private String registerModeName;// 立案方式代码

    private  Long registerIncidentTime;// 立案时间

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度


    private String height;// 高度

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeName;// 案件类型名称

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentTypeSubitemName;// 案件类型子项名称（细类）（案件类型备用）

    private String incidentLevelCode;// 案件等级代码

    private String incidentLevelName;// 案件等级名称

    private String disposalObjectCode;// 处置对象代码

    private String disposalObjectName;// 处置对象名称




    private String incidentStatusCode;// 案件状态代码

    private String incidentStatusName;// 案件状态名称

    private String incidentNatureCode;// 案件性质代码 1 实警；0 虚警

    private String incidentNatureName;// 案件性质名称

    private Integer whetherImportSign;// 是否重大案件标识

    private Integer mergeStatus;// 合并状态

    private Integer whetherMainMerge ;// 是否为合并主案件


    private String registerSeatNumber; // 立案接警坐席号

    private String acceptancePersonNumber;// 接警员工号

    private String acceptancePersonName;// 接警员姓名

    private String dispatchOrganization; //出动机构

    private String dispatchVehicle; // 出动车辆



    private String incidentGroupId;//案件群组id 如果有多个群组 多个群组逗号分隔

    private Integer whetherFocusOn = 0 ;//是否关注 0不关注 1 关注 默认 0 不关注

    private Integer whetherImportant  = 0 ;//重要警情标识 0非重要 1重要 默认0非重要

    private Integer attachmentNum = 0  ; //附件数量

    private String alarmPhone;//报警电话



    private String relayRecordNumber;//录音号 （案件主案件录音号）

    private String alarmModeCode;  //报警方式代码

    private String alarmModeName;  //报警方式名称

    private String alarmPersonPhone;//报警人联系电话

    private String alarmPersonName;//报警人姓名



    /** 简要信息 补充数据 **/
    private List<SoundRecordBean> soundRecordList ; //录音文件信息


    public String getAlarmPersonName() {
        return alarmPersonName;
    }

    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName;
    }

    public String getAlarmPersonPhone() {
        return alarmPersonPhone;
    }

    public void setAlarmPersonPhone(String alarmPersonPhone) {
        this.alarmPersonPhone = alarmPersonPhone;
    }

    public String getAlarmModeCode() {
        return alarmModeCode;
    }

    public void setAlarmModeCode(String alarmModeCode) {
        this.alarmModeCode = alarmModeCode;
    }

    public String getAlarmModeName() {
        return alarmModeName;
    }

    public void setAlarmModeName(String alarmModeName) {
        this.alarmModeName = alarmModeName;
    }

    public String getAlarmPhone() {
        return alarmPhone;
    }

    public void setAlarmPhone(String alarmPhone) {
        this.alarmPhone = alarmPhone;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getSquadronOrganizationId() {
        return squadronOrganizationId;
    }

    public void setSquadronOrganizationId(String squadronOrganizationId) {
        this.squadronOrganizationId = squadronOrganizationId;
    }

    public String getSquadronOrganizationName() {
        return squadronOrganizationName;
    }

    public void setSquadronOrganizationName(String squadronOrganizationName) {
        this.squadronOrganizationName = squadronOrganizationName;
    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }

    public String getBrigadeOrganizationName() {
        return brigadeOrganizationName;
    }

    public void setBrigadeOrganizationName(String brigadeOrganizationName) {
        this.brigadeOrganizationName = brigadeOrganizationName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getRegisterModeCode() {
        return registerModeCode;
    }

    public void setRegisterModeCode(String registerModeCode) {
        this.registerModeCode = registerModeCode;
    }

    public String getRegisterModeName() {
        return registerModeName;
    }

    public void setRegisterModeName(String registerModeName) {
        this.registerModeName = registerModeName;
    }

    public Long getRegisterIncidentTime() {
        return registerIncidentTime;
    }

    public void setRegisterIncidentTime(Long registerIncidentTime) {
        this.registerIncidentTime = registerIncidentTime;
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

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
    }

    public String getIncidentTypeSubitemName() {
        return incidentTypeSubitemName;
    }

    public void setIncidentTypeSubitemName(String incidentTypeSubitemName) {
        this.incidentTypeSubitemName = incidentTypeSubitemName;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getIncidentLevelName() {
        return incidentLevelName;
    }

    public void setIncidentLevelName(String incidentLevelName) {
        this.incidentLevelName = incidentLevelName;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getDisposalObjectName() {
        return disposalObjectName;
    }

    public void setDisposalObjectName(String disposalObjectName) {
        this.disposalObjectName = disposalObjectName;
    }

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public String getIncidentStatusName() {
        return incidentStatusName;
    }

    public void setIncidentStatusName(String incidentStatusName) {
        this.incidentStatusName = incidentStatusName;
    }

    public String getIncidentNatureCode() {
        return incidentNatureCode;
    }

    public void setIncidentNatureCode(String incidentNatureCode) {
        this.incidentNatureCode = incidentNatureCode;
    }

    public String getIncidentNatureName() {
        return incidentNatureName;
    }

    public void setIncidentNatureName(String incidentNatureName) {
        this.incidentNatureName = incidentNatureName;
    }

    public Integer getWhetherImportSign() {
        return whetherImportSign;
    }

    public void setWhetherImportSign(Integer whetherImportSign) {
        this.whetherImportSign = whetherImportSign;
    }

    public Integer getMergeStatus() {
        return mergeStatus;
    }

    public void setMergeStatus(Integer mergeStatus) {
        this.mergeStatus = mergeStatus;
    }

    public Integer getWhetherMainMerge() {
        return whetherMainMerge;
    }

    public void setWhetherMainMerge(Integer whetherMainMerge) {
        this.whetherMainMerge = whetherMainMerge;
    }

    public String getRegisterSeatNumber() {
        return registerSeatNumber;
    }

    public void setRegisterSeatNumber(String registerSeatNumber) {
        this.registerSeatNumber = registerSeatNumber;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getAcceptancePersonName() {
        return acceptancePersonName;
    }

    public void setAcceptancePersonName(String acceptancePersonName) {
        this.acceptancePersonName = acceptancePersonName;
    }

    public String getDispatchOrganization() {
        return dispatchOrganization;
    }

    public void setDispatchOrganization(String dispatchOrganization) {
        this.dispatchOrganization = dispatchOrganization;
    }

    public String getDispatchVehicle() {
        return dispatchVehicle;
    }

    public void setDispatchVehicle(String dispatchVehicle) {
        this.dispatchVehicle = dispatchVehicle;
    }

    public Integer getWhetherFocusOn() {
        return whetherFocusOn;
    }

    public void setWhetherFocusOn(Integer whetherFocusOn) {
        this.whetherFocusOn = whetherFocusOn;
    }

    public Integer getWhetherImportant() {
        return whetherImportant;
    }

    public void setWhetherImportant(Integer whetherImportant) {
        this.whetherImportant = whetherImportant;
    }

    public String getIncidentGroupId() {
        return incidentGroupId;
    }

    public void setIncidentGroupId(String incidentGroupId) {
        this.incidentGroupId = incidentGroupId;
    }

    public Integer getAttachmentNum() {
        return attachmentNum;
    }

    public void setAttachmentNum(Integer attachmentNum) {
        this.attachmentNum = attachmentNum;
    }

    public List<SoundRecordBean> getSoundRecordList() {
        return soundRecordList;
    }

    public void setSoundRecordList(List<SoundRecordBean> soundRecordList) {
        this.soundRecordList = soundRecordList;
    }
}
