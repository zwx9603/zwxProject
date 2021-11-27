package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.accept.graphql.firetypebean.ActionSummaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitBean;

import java.util.List;

/**
 * 描述：警情信息基本信
 *
 * @author YangFuXi Date Time 2018/7/4 14:05
 */
public class IncidentInfoBaseInfoBean {
    private String incidentId;//警情ID
    private String incidentType;//警情类型
    private Long registerEventTime;//立案时间
    private String address;//案发地址
    private String incidentStatus;//警情状态名称
    private Long occur;//案发时间
    private String incidentSource;//事件来源（立案方式）
    private String incidentLevel;    //警情等级
    private String alarmMode;//报警方式类型
    private String alarmPerson;//报警人
    private String alarmPersonPhone;//联系方式
    private String alarmTapeNo;//录音号
    private String disasterSites;//灾害场所
    private String smogSituation;//烟雾情况
    private String buildingStructure;//建筑结构
    private String trappedPersonsNumber;//被困人数
    private String injuredCount;//受伤情况
    private String deathCount;//死亡
    private String burningArea;//燃烧面积
    private String burnFloor;//燃烧楼层
    private String storeysOfBuildings;//楼房层数
    private String fireSquadronId;//主管中队ID
    private String fireSquadronName;//主管中队名称
    private String disposalObject;//燃烧物 处置对象名称
    private String risk;//危险性
    private String notes;//注意事项
    private String majorSituation;//事故主要情况
    private String longitude; //经度
    private String latitude; //纬度
    private String district; //行政区
    private String description;//描述，补充信息
    private KeyUnitBean keyUnitBean;
    //private KeyUnitSBean unitBean;//重点单位类
    private Boolean isStartCommand;//是否启动指挥
    private String commanderName;//指挥姓名
    private String commanderPhone;//指挥电话
    //    private Boolean getisFocus;//是否关注
    private Long endTime;//案件结束时间
    private List<ActionSummaryBean> actionSummarys;

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public Long getRegisterEventTime() {
        return registerEventTime;
    }

    public void setRegisterEventTime(Long registerEventTime) {
        this.registerEventTime = registerEventTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentStatus(String incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    public Long getOccur() {
        return occur;
    }

    public void setOccur(Long occur) {
        this.occur = occur;
    }

    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
    }

    public String getIncidentLevel() {
        return incidentLevel;
    }

    public void setIncidentLevel(String incidentLevel) {
        this.incidentLevel = incidentLevel;
    }

    public String getAlarmMode() {
        return alarmMode;
    }

    public void setAlarmMode(String alarmMode) {
        this.alarmMode = alarmMode;
    }

    public String getAlarmPerson() {
        return alarmPerson;
    }

    public void setAlarmPerson(String alarmPerson) {
        this.alarmPerson = alarmPerson;
    }

    public String getAlarmPersonPhone() {
        return alarmPersonPhone;
    }

    public void setAlarmPersonPhone(String alarmPersonPhone) {
        this.alarmPersonPhone = alarmPersonPhone;
    }

    public String getAlarmTapeNo() {
        return alarmTapeNo;
    }

    public void setAlarmTapeNo(String alarmTapeNo) {
        this.alarmTapeNo = alarmTapeNo;
    }

    public String getDisasterSites() {
        return disasterSites;
    }

    public void setDisasterSites(String disasterSites) {
        this.disasterSites = disasterSites;
    }

    public String getSmogSituation() {
        return smogSituation;
    }

    public void setSmogSituation(String smogSituation) {
        this.smogSituation = smogSituation;
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure;
    }

    public String getTrappedPersonsNumber() {
        return trappedPersonsNumber;
    }

    public void setTrappedPersonsNumber(String trappedPersonsNumber) {
        this.trappedPersonsNumber = trappedPersonsNumber;
    }

    public String getInjuredCount() {
        return injuredCount;
    }

    public void setInjuredCount(String injuredCount) {
        this.injuredCount = injuredCount;
    }

    public String getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(String deathCount) {
        this.deathCount = deathCount;
    }

    public String getBurningArea() {
        return burningArea;
    }

    public void setBurningArea(String burningArea) {
        this.burningArea = burningArea;
    }

    public String getBurnFloor() {
        return burnFloor;
    }

    public void setBurnFloor(String burnFloor) {
        this.burnFloor = burnFloor;
    }

    public String getStoreysOfBuildings() {
        return storeysOfBuildings;
    }

    public void setStoreysOfBuildings(String storeysOfBuildings) {
        this.storeysOfBuildings = storeysOfBuildings;
    }

    public String getFireSquadronId() {
        return fireSquadronId;
    }

    public void setFireSquadronId(String fireSquadronId) {
        this.fireSquadronId = fireSquadronId;
    }

    public String getFireSquadronName() {
        return fireSquadronName;
    }

    public void setFireSquadronName(String fireSquadronName) {
        this.fireSquadronName = fireSquadronName;
    }

    public String getDisposalObject() {
        return disposalObject;
    }

    public void setDisposalObject(String disposalObject) {
        this.disposalObject = disposalObject;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMajorSituation() {
        return majorSituation;
    }

    public void setMajorSituation(String majorSituation) {
        this.majorSituation = majorSituation;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KeyUnitBean getKeyUnitBean() {
        return keyUnitBean;
    }

    public void setKeyUnitBean(KeyUnitBean keyUnitBean) {
        this.keyUnitBean = keyUnitBean;
    }

    public Boolean getStartCommand() {
        return isStartCommand;
    }

    public void setStartCommand(Boolean startCommand) {
        isStartCommand = startCommand;
    }

    public String getCommanderName() {
        return commanderName;
    }

    public void setCommanderName(String commanderName) {
        this.commanderName = commanderName;
    }

    public String getCommanderPhone() {
        return commanderPhone;
    }

    public void setCommanderPhone(String commanderPhone) {
        this.commanderPhone = commanderPhone;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<ActionSummaryBean> getActionSummarys() {
        return actionSummarys;
    }

    public void setActionSummarys(List<ActionSummaryBean> actionSummarys) {
        this.actionSummarys = actionSummarys;
    }


	public Boolean getIsStartCommand() {
		return isStartCommand;
	}

	public void setIsStartCommand(Boolean isStartCommand) {
		this.isStartCommand = isStartCommand;
	}

	


}
