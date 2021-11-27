package com.dscomm.iecs.accept.service.bean.cad;

/**
 * 描述:受理单表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/29 8:44
 */
public class AcceptBO {
    /**
     * 受理单编号
     */
    private String id;
    /**
     * 行政区划
     */
    private String administrativeDivision;
    /**
     * 事件单编号
     */
    private String incidentId;
    /**
     * 处理类型
     */
    private String dealType;
    /**
     * 处理类型name
     */
    private String dealTypeName;
    /**
     * 接警开始时间
     */
    private Long acceptStartTime;
    /**
     * 接警单位
     */
    private String recAlarmOrgCode;
    /**
     * 接警单位编号id (t_bd_dw)
     */
    private String recAlarmOrgId;
    /**
     * 接警单位名称name
     */
    private String recAlarmOrgName;
    /**
     * 接警员工号
     */
    private String recAlarmPersonId;
    /**
     * 接警员姓名
     */
    private String recAlarmPerson;
    /**
     * 接警台号
     */
    private Integer acceptAgentId;
    /**
     * 接警台IP
     */
    private String acceptAgentIP;
    /**
     * 报警人姓名
     */
    private String alarmPersonName;
    /**
     * 报警人性别  ---length = 400???性别字段---
     */
    private String alarmPersonSex;
    /**
     * 联系电话
     */
    private String alarmPersonContact;
    /**
     * 联系地址
     */
    private String alarmPersonAddress;
    /**
     * 事发地址
     */
    private String address;
    /**
     * 标志物
     */
    private String locateMark;
    /**
     * X坐标/经度
     */
    private String longitude;
    /**
     * Y坐标/维度
     */
    private String latitude;
    /**
     * 辖区单位id (t_bd_dw)
     */
    private String policeOfficeId;
    /**
     * 辖区单位code
     */
    private String policeOfficeCode;
    /**
     * 辖区单位name
     */
    private String policeOfficeName;
    /**
     * 所属分局id (t_bd_dw)
     */
    private String policeStationId;
    /**
     * 所属分局code
     */
    private String policeStationCode;
    /**
     * 所属分局name
     */
    private String policeStationName;
    /**
     * 报警方式 (t_dm_bjfs)
     */
    private String alarmWay;
    /**
     * 报警方式name
     */
    private String alarmWayName;
    /**
     * 报警警种
     */
    private String alarmTypeFirst;
    /**
     * 报警警种name
     */
    private String alarmTypeFirstName;
    /**
     * 接警警种
     */
    private String alarmType;
    /**
     * 接警警种name
     */
    private String alarmTypeName;
    /**
     * 案由
     */
    private String incidentType;
    /**
     * 案由name
     */
    private String incidentTypeName;
    /**
     * 警情级别
     */
    private String level;
    /**
     * 警情级别name
     */
    private String levelName;
    /**
     * 报警内容
     */
    private String content;
    /**
     * 接警结束时间
     */
    private Long acceptEndTime;
    /**
     * 接警时长
     */
    private Long acceptDuration;
    /**
     * 受理状态
     */
    private Integer acceptStatus;
    /**
     * 受理状态name
     */
    private String acceptStatusName;
    /**
     * 是否匿名
     */
    private Integer isAnonymous;
    /**
     * 是否匿名name
     */
    private String isAnonymousName;
    /**
     * 时间戳
     */
    private Long lastedhandleTime;
    /**
     * 坐标类型
     */
    private String coordinateType;
    /**
     * 报警人证件号
     */
    private String alarmPersonIdentityNumber;
    /**
     * 车牌号码
     */
    private String carNumber;
    /**
     * 微信警单编号
     */
    private String wechatIncidentNumber;
    /**
     * 微信号
     */
    private String wechatNumber;
    /**
     * 是否受害人（报警人是否是受害人:0不是;1是）
     */
    private Integer isVictim;
    /**
     * 是否受害人name
     */
    private String isVictimName;

    private AcceptExtend110BO acceptExtend110BO;

    private AcceptExtend119BO acceptExtend119BO;

    private AcceptExtend120BO acceptExtend120BO;

    private AcceptExtend122BO acceptExtend122BO;

    /**
     * 坐席录音号
     */
    private String agentCallRecordNum;
    /**
     * 主叫号码
     */
    private String callingTelephone;
    /**
     * 被叫号码
     */
    private String calledTelephone;
    /**
     * 装机用户
     */
    private String callingTelephoneInstallationUser;
    /**
     * 装机地址
     */
    private String callingTelephoneInstallationAddress;
    /**
     * 呼入时间
     */
    private Long callStartTime;
    /**
     * 摘机时间
     */
    private Long callPickUpTime;
    /**
     * 话终时间
     */
    private Long callEndTime;
    /**
     * 振铃时长(单位秒)
     */
    private Integer ringTime;
    /**
     * 通话时长(单位秒)
     */
    private Integer talkTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdministrativeDivision() {
        return administrativeDivision;
    }

    public void setAdministrativeDivision(String administrativeDivision) {
        this.administrativeDivision = administrativeDivision;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealTypeName() {
        return dealTypeName;
    }

    public void setDealTypeName(String dealTypeName) {
        this.dealTypeName = dealTypeName;
    }

    public Long getAcceptStartTime() {
        return acceptStartTime;
    }

    public void setAcceptStartTime(Long acceptStartTime) {
        this.acceptStartTime = acceptStartTime;
    }

    public String getRecAlarmOrgCode() {
        return recAlarmOrgCode;
    }

    public void setRecAlarmOrgCode(String recAlarmOrgCode) {
        this.recAlarmOrgCode = recAlarmOrgCode;
    }

    public String getRecAlarmOrgId() {
        return recAlarmOrgId;
    }

    public void setRecAlarmOrgId(String recAlarmOrgId) {
        this.recAlarmOrgId = recAlarmOrgId;
    }

    public String getRecAlarmOrgName() {
        return recAlarmOrgName;
    }

    public void setRecAlarmOrgName(String recAlarmOrgName) {
        this.recAlarmOrgName = recAlarmOrgName;
    }

    public String getRecAlarmPersonId() {
        return recAlarmPersonId;
    }

    public void setRecAlarmPersonId(String recAlarmPersonId) {
        this.recAlarmPersonId = recAlarmPersonId;
    }

    public String getRecAlarmPerson() {
        return recAlarmPerson;
    }

    public void setRecAlarmPerson(String recAlarmPerson) {
        this.recAlarmPerson = recAlarmPerson;
    }

    public Integer getAcceptAgentId() {
        return acceptAgentId;
    }

    public void setAcceptAgentId(Integer acceptAgentId) {
        this.acceptAgentId = acceptAgentId;
    }

    public String getAcceptAgentIP() {
        return acceptAgentIP;
    }

    public void setAcceptAgentIP(String acceptAgentIP) {
        this.acceptAgentIP = acceptAgentIP;
    }

    public String getAlarmPersonName() {
        return alarmPersonName;
    }

    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName;
    }

    public String getAlarmPersonSex() {
        return alarmPersonSex;
    }

    public void setAlarmPersonSex(String alarmPersonSex) {
        this.alarmPersonSex = alarmPersonSex;
    }

    public String getAlarmPersonContact() {
        return alarmPersonContact;
    }

    public void setAlarmPersonContact(String alarmPersonContact) {
        this.alarmPersonContact = alarmPersonContact;
    }

    public String getAlarmPersonAddress() {
        return alarmPersonAddress;
    }

    public void setAlarmPersonAddress(String alarmPersonAddress) {
        this.alarmPersonAddress = alarmPersonAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocateMark() {
        return locateMark;
    }

    public void setLocateMark(String locateMark) {
        this.locateMark = locateMark;
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

    public String getPoliceOfficeId() {
        return policeOfficeId;
    }

    public void setPoliceOfficeId(String policeOfficeId) {
        this.policeOfficeId = policeOfficeId;
    }

    public String getPoliceOfficeCode() {
        return policeOfficeCode;
    }

    public void setPoliceOfficeCode(String policeOfficeCode) {
        this.policeOfficeCode = policeOfficeCode;
    }

    public String getPoliceOfficeName() {
        return policeOfficeName;
    }

    public void setPoliceOfficeName(String policeOfficeName) {
        this.policeOfficeName = policeOfficeName;
    }

    public String getPoliceStationId() {
        return policeStationId;
    }

    public void setPoliceStationId(String policeStationId) {
        this.policeStationId = policeStationId;
    }

    public String getPoliceStationCode() {
        return policeStationCode;
    }

    public void setPoliceStationCode(String policeStationCode) {
        this.policeStationCode = policeStationCode;
    }

    public String getPoliceStationName() {
        return policeStationName;
    }

    public void setPoliceStationName(String policeStationName) {
        this.policeStationName = policeStationName;
    }

    public String getAlarmWay() {
        return alarmWay;
    }

    public void setAlarmWay(String alarmWay) {
        this.alarmWay = alarmWay;
    }

    public String getAlarmWayName() {
        return alarmWayName;
    }

    public void setAlarmWayName(String alarmWayName) {
        this.alarmWayName = alarmWayName;
    }

    public String getAlarmTypeFirst() {
        return alarmTypeFirst;
    }

    public void setAlarmTypeFirst(String alarmTypeFirst) {
        this.alarmTypeFirst = alarmTypeFirst;
    }

    public String getAlarmTypeFirstName() {
        return alarmTypeFirstName;
    }

    public void setAlarmTypeFirstName(String alarmTypeFirstName) {
        this.alarmTypeFirstName = alarmTypeFirstName;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAcceptEndTime() {
        return acceptEndTime;
    }

    public void setAcceptEndTime(Long acceptEndTime) {
        this.acceptEndTime = acceptEndTime;
    }

    public Long getAcceptDuration() {
        return acceptDuration;
    }

    public void setAcceptDuration(Long acceptDuration) {
        this.acceptDuration = acceptDuration;
    }

    public Integer getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(Integer acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getAcceptStatusName() {
        return acceptStatusName;
    }

    public void setAcceptStatusName(String acceptStatusName) {
        this.acceptStatusName = acceptStatusName;
    }

    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public String getIsAnonymousName() {
        return isAnonymousName;
    }

    public void setIsAnonymousName(String isAnonymousName) {
        this.isAnonymousName = isAnonymousName;
    }

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    public String getAlarmPersonIdentityNumber() {
        return alarmPersonIdentityNumber;
    }

    public void setAlarmPersonIdentityNumber(String alarmPersonIdentityNumber) {
        this.alarmPersonIdentityNumber = alarmPersonIdentityNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getWechatIncidentNumber() {
        return wechatIncidentNumber;
    }

    public void setWechatIncidentNumber(String wechatIncidentNumber) {
        this.wechatIncidentNumber = wechatIncidentNumber;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public Integer getIsVictim() {
        return isVictim;
    }

    public void setIsVictim(Integer isVictim) {
        this.isVictim = isVictim;
    }

    public String getIsVictimName() {
        return isVictimName;
    }

    public void setIsVictimName(String isVictimName) {
        this.isVictimName = isVictimName;
    }

    public AcceptExtend110BO getAcceptExtend110BO() {
        return acceptExtend110BO;
    }

    public void setAcceptExtend110BO(AcceptExtend110BO acceptExtend110BO) {
        this.acceptExtend110BO = acceptExtend110BO;
    }

    public AcceptExtend119BO getAcceptExtend119BO() {
        return acceptExtend119BO;
    }

    public void setAcceptExtend119BO(AcceptExtend119BO acceptExtend119BO) {
        this.acceptExtend119BO = acceptExtend119BO;
    }

    public AcceptExtend120BO getAcceptExtend120BO() {
        return acceptExtend120BO;
    }

    public void setAcceptExtend120BO(AcceptExtend120BO acceptExtend120BO) {
        this.acceptExtend120BO = acceptExtend120BO;
    }

    public AcceptExtend122BO getAcceptExtend122BO() {
        return acceptExtend122BO;
    }

    public void setAcceptExtend122BO(AcceptExtend122BO acceptExtend122BO) {
        this.acceptExtend122BO = acceptExtend122BO;
    }

    public String getAgentCallRecordNum() {
        return agentCallRecordNum;
    }

    public void setAgentCallRecordNum(String agentCallRecordNum) {
        this.agentCallRecordNum = agentCallRecordNum;
    }

    public String getCallingTelephone() {
        return callingTelephone;
    }

    public void setCallingTelephone(String callingTelephone) {
        this.callingTelephone = callingTelephone;
    }

    public String getCalledTelephone() {
        return calledTelephone;
    }

    public void setCalledTelephone(String calledTelephone) {
        this.calledTelephone = calledTelephone;
    }

    public String getCallingTelephoneInstallationUser() {
        return callingTelephoneInstallationUser;
    }

    public void setCallingTelephoneInstallationUser(String callingTelephoneInstallationUser) {
        this.callingTelephoneInstallationUser = callingTelephoneInstallationUser;
    }

    public String getCallingTelephoneInstallationAddress() {
        return callingTelephoneInstallationAddress;
    }

    public void setCallingTelephoneInstallationAddress(String callingTelephoneInstallationAddress) {
        this.callingTelephoneInstallationAddress = callingTelephoneInstallationAddress;
    }

    public Long getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(Long callStartTime) {
        this.callStartTime = callStartTime;
    }

    public Long getCallPickUpTime() {
        return callPickUpTime;
    }

    public void setCallPickUpTime(Long callPickUpTime) {
        this.callPickUpTime = callPickUpTime;
    }

    public Long getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(Long callEndTime) {
        this.callEndTime = callEndTime;
    }

    public Integer getRingTime() {
        return ringTime;
    }

    public void setRingTime(Integer ringTime) {
        this.ringTime = ringTime;
    }

    public Integer getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Integer talkTime) {
        this.talkTime = talkTime;
    }
}
