package com.dscomm.iecs.accept.service.bean.cad;

import java.util.List;

/**
 * 描述:历史警情BO（事件单信息+补充项）
 * 某些字典ID或CODE 查出来完整信息
 *
 * @author ZhaiYanTao
 * Date Time 2019/8/21 15:13
 */
public class IncidentInfoBO  {
    /**
     * 事件单编号
     */
    private String id;
    /**
     * 行政区划code  ()
     */
    private String administrativeDivision;
    /**
     * 行政区划name
     */
    private String administrativeDivisionName;
    /**
     * 接警单位编号id (t_bd_dw)
     */
    private String recAlarmOrgId;
    /**
     * 接警单位代码code
     */
    private String recAlarmOrgCode;
    /**
     * 接警单位名称name
     */
    private String recAlarmOrgName;
//    /**
//     * 事发地址
//     */
//    private String address;
    /**
     * 定位标志物
     */
    private String locateMark;
    /**
     * 一案一码（安义县使用字段）
     */
    private String caseCode;
    /**
     * X坐标/经度
     */
    private String longitude;
    /**
     * Y坐标/纬度
     */
    private String latitude;
    /**
     * 首次报警时间
     */
    private Long recAlarmTime;
    /**
     * 报警电话
     */
    private String alarmPhone;
//    /**
//     * 报警人
//     */
//    private String alarmPersonName;
//    /**
//     * 报警人联系电话
//     */
//    private String alarmPersonContact;
//    /**
//     * 报警人联系地址
//     */
//    private String alarmPersonAddress;
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
     * 警种id (t_dm_jz)
     */
    private String alarmType;
    /**
     * 警种code
     */
    private String alarmTypeCode;
    /**
     * 警种name
     */
    private String alarmTypeName;
    /**
     * 处理类型id (t_dm_cllx)
     */
    private String dealType;
    /**
     * 处理类型code
     */
    private String dealTypeCode;
    /**
     * 处理类型name
     */
    private String dealTypeName;
    /**
     * 警情级别code (t_dm_jqjb)
     */
    private String level;
    /**
     * 警情级别code (t_dm_jqjb)
     */
    private String levelCode;
    /**
     * 警情级别name
     */
    private String levelName;
    /**
     * 级别排序
     */
    private Integer levelOrder;
    /**
     * 案由code (t_dm_ay)
     */
    private String incidentType;
    /**
     * 案由name
     */
    private String incidentTypeName;
    /**
     * 一级案由code (t_dm_ay)
     */
    private String incidentParentType;
    /**
     * 一级案由name
     */
    private String incidentParentTypeName;
    /**
     * 关联事件单编号
     */
    private String relatedIncidentId;
//    /**
//     * 事件详情
//     */
//    private String content;
    /**
     * 报警内容
     */
    private String alarmContent;
    /**
     * 出警单位id(t_bd_dw)
     */
    private String dealIncidentUnitId;
    /**
     * 出警单位code
     */
    private String dealIncidentUnitCode;
    /**
     * 出警单位name
     */
    private String dealIncidentUnitName;
    /**
     * 首次出警单位名称
     */
    private String firstHandleOrgName;
    /**
     * 处理结果
     */
    private String dealResult;
    /**
     * 处理结果name
     */
    private String dealResultName;
    /**
     * 事件处理情况
     */
    private String incidentDealDetail;
    /**
     * 事件状态code (t_dm_xtdm :fl=100为事件单状态) (t_dm_xtdm多语言，暂时用t_dm_xtdm_en)
     */
    private Integer cadState;
    /**
     * 案件状态
     */
    private Integer cadStateCode;
    /**
     * 事件状态name
     */
    private String cadStateName;
    /**
     * 首次受理结束时间
     */
    private Long firstDealEndTime;
    /**
     * 报警人性别
     */
    private String alarmPersonSex;
    /**
     * 事发地址分类id (t_dm_dzfl)
     */
    private String addressType;
    /**
     * 事发地址分类code
     */
    private String addressTypeCode;
    /**
     * 事发地址分类name
     */
    private String addressTypeName;
    /**
     * 首次受理单编号
     */
    private String firstDealId;
    /**
     * 是否匿名 ( 0 不匿名;1 匿名 )
     */
    private Integer isAnonymous;
    /**
     * 是否匿名name
     */
    private String isAnonymousName;
    /**
     * 接警员工号
     */
    private String recAlarmPersonId;
    /**
     * 接警员姓名
     */
    private String recAlarmPerson;
    /**
     * 时间戳
     */
    private Long lastedhandleTime;
    /**
     * 重点单位编号id (t_dm_zddw)
     */
    private String keyUnitNumber;
    /**
     * 重点单位code 没有code字段
     */
    private String keyUnitCode;
    /**
     * 重点单位name
     */
    private String keyUnitName;
    /**
     * 报警方式code (t_dm_bjfs)
     */
    private String alarmWay;
    /**
     * 报警方式name
     */
    private String alarmWayName;
    /**
     * 案件处置人
     */
    private String incidentOperator;
    /**
     * 语音文件
     */
    private String voiceFile;
    /**
     * 所属人员工号
     */
    private String belongPersonAccount;
    /**
     * 所属人员姓名
     */
    private String belongPersonName;
    /**
     * 警情升级状态code (0:未升级 1已升级)
     */
    private Integer incidentUpgradeStateCode=0;
    /**
     * 警情类型(0:普通警请 1:安保警情)
     */
    private Integer type=0;
    /**
     * 警情升级状态名称name
     */
    private String incidentUpgradeStateName;
    /**
     * 是否关注（1关注,0不关注）
     */
    private Integer attention;
    /**
     * 是否置顶（班长关注）（1置顶，0不置顶）
     */
    private Integer top;
    /**
     * 关注排序字段
     */
    private Integer sortField;
    /**
     * 警情关注人账号
     */
    private List<String> attentionPersonAccount;
    /**
     * 坐标类型
     */
    private String coordinateType;
//    /**
//     * 报警人证件号
//     */
//    private String alarmPersonIdentityNumber;
    /**
     * 车牌号码
     */
    private String carNumber;
    /**
     *
     */
    private Integer yjzt;
    /**
     * 来源
     */
    private String source;
    /**
     * 微信警单编号
     */
    private String wechatIncidentNumber;
    /**
     * 钉办ID
     */
    private String xdTaskId;
    /**
     * 钉办状态
     */
    private String xdTaskState;
    /**
     * 警情群组id
     */
    private String groupId;
    /**
     * 重大标志
     */
    private Integer largeSign;
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
    /**
     * 未处警类型（1：已受理未处警，2：上级单位派发未处警）
     */
    private Integer unDispatchType;
    /**
     * 指令单id（用于未处警）
     */
    private String unDispatchOrderId;
    /**
     * 指令单状态（用于未处警）
     */
    private Integer unDispatchOrderState;
    /**
     * 指令单状态名称（用于未处警）
     */
    private String unDispatchOrderStateName;
    /**
     * 处警单编号
     */
    private String dispatchFormId;
    /**
     * 附件资料列表
     */
    private List<AttachmentBO> attachmentList;
    /**
     * 录音记录信息列表
     */
    private List<SoundRecordBO> soundRecordList;
    /**
     * 证据列表
     */
    private List<EvidenceBO> evidenceList;

    /**
     * 辅助电话列表
     */
    private List<AuxiliaryInformationBO> auxiliaryPhoneBOS;
    /**
     * 110事件扩展
     */
    private IncidentExtend110BO incidentExtend110;
    /**
     * 119事件扩展
     */
    private IncidentExtend119BO incidentExtend119;
    /**
     * 120事件扩展
     */
    private IncidentExtend120BO incidentExtend120;
    /**
     * 122事件扩展
     */
    private IncidentExtend122BO incidentExtend122;


    private String incidentMark;//警情有效标志  1代表有效 0代表无效

    private Integer attentionType;//关注类型

    private Integer cancelAttentionType;//取消关注类型 1代表可以取消 0代表不可取消

    /**
     * 是否预先介入 1 是 0 否
     */
    private Integer isPreAttentForce=0;
    /**
     * 警情补充信息 冗余字段，产品用，不能修改补充内容时使用
     */
    private String addInfo;
    /**
     * 警情补充记录
     */
    private List<IncidentAddInfoBO> addInfoRecords;

    /**
     * 是否签收超时 1 是 0 否
     */
    private Integer isSignTimeOut=0;
    /**
     * 是否到场超时 1 是 0 否
     */
    private Integer isArriveTimeOut=0;
    /**
     * 是否反馈超时 1 是 0 否
     */
    private Integer isFeedBackTimeOut=0;
    /**
     * 是否已反馈 1 是 0 否
     */
    private Integer isFeedBack=0;
    /**
     * 警务平台接警编号
     */
    private String jwptbh;

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

    public String getAdministrativeDivisionName() {
        return administrativeDivisionName;
    }

    public void setAdministrativeDivisionName(String administrativeDivisionName) {
        this.administrativeDivisionName = administrativeDivisionName;
    }

    public String getRecAlarmOrgId() {
        return recAlarmOrgId;
    }

    public void setRecAlarmOrgId(String recAlarmOrgId) {
        this.recAlarmOrgId = recAlarmOrgId;
    }

    public String getRecAlarmOrgCode() {
        return recAlarmOrgCode;
    }

    public void setRecAlarmOrgCode(String recAlarmOrgCode) {
        this.recAlarmOrgCode = recAlarmOrgCode;
    }

    public String getRecAlarmOrgName() {
        return recAlarmOrgName;
    }

    public void setRecAlarmOrgName(String recAlarmOrgName) {
        this.recAlarmOrgName = recAlarmOrgName;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

    public String getLocateMark() {
        return locateMark;
    }

    public void setLocateMark(String locateMark) {
        this.locateMark = locateMark;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
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

    public Long getRecAlarmTime() {
        return recAlarmTime;
    }

    public void setRecAlarmTime(Long recAlarmTime) {
        this.recAlarmTime = recAlarmTime;
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

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmTypeCode() {
        return alarmTypeCode;
    }

    public void setAlarmTypeCode(String alarmTypeCode) {
        this.alarmTypeCode = alarmTypeCode;
    }

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealTypeCode() {
        return dealTypeCode;
    }

    public void setDealTypeCode(String dealTypeCode) {
        this.dealTypeCode = dealTypeCode;
    }

    public String getDealTypeName() {
        return dealTypeName;
    }

    public void setDealTypeName(String dealTypeName) {
        this.dealTypeName = dealTypeName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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

    public String getIncidentParentType() {
        return incidentParentType;
    }

    public void setIncidentParentType(String incidentParentType) {
        this.incidentParentType = incidentParentType;
    }

    public String getIncidentParentTypeName() {
        return incidentParentTypeName;
    }

    public void setIncidentParentTypeName(String incidentParentTypeName) {
        this.incidentParentTypeName = incidentParentTypeName;
    }

    public String getRelatedIncidentId() {
        return relatedIncidentId;
    }

    public void setRelatedIncidentId(String relatedIncidentId) {
        this.relatedIncidentId = relatedIncidentId;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

    public String getDealIncidentUnitId() {
        return dealIncidentUnitId;
    }

    public void setDealIncidentUnitId(String dealIncidentUnitId) {
        this.dealIncidentUnitId = dealIncidentUnitId;
    }

    public String getDealIncidentUnitCode() {
        return dealIncidentUnitCode;
    }

    public void setDealIncidentUnitCode(String dealIncidentUnitCode) {
        this.dealIncidentUnitCode = dealIncidentUnitCode;
    }

    public String getDealIncidentUnitName() {
        return dealIncidentUnitName;
    }

    public void setDealIncidentUnitName(String dealIncidentUnitName) {
        this.dealIncidentUnitName = dealIncidentUnitName;
    }

    public String getFirstHandleOrgName() {
        return firstHandleOrgName;
    }

    public void setFirstHandleOrgName(String firstHandleOrgName) {
        this.firstHandleOrgName = firstHandleOrgName;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getDealResultName() {
        return dealResultName;
    }

    public void setDealResultName(String dealResultName) {
        this.dealResultName = dealResultName;
    }

    public String getIncidentDealDetail() {
        return incidentDealDetail;
    }

    public void setIncidentDealDetail(String incidentDealDetail) {
        this.incidentDealDetail = incidentDealDetail;
    }

    public Integer getCadState() {
        return cadState;
    }

    public void setCadState(Integer cadState) {
        this.cadState = cadState;
    }

    public Integer getCadStateCode() {
        return cadStateCode;
    }

    public void setCadStateCode(Integer cadStateCode) {
        this.cadStateCode = cadStateCode;
    }

    public String getCadStateName() {
        return cadStateName;
    }

    public void setCadStateName(String cadStateName) {
        this.cadStateName = cadStateName;
    }

    public Long getFirstDealEndTime() {
        return firstDealEndTime;
    }

    public void setFirstDealEndTime(Long firstDealEndTime) {
        this.firstDealEndTime = firstDealEndTime;
    }

    public String getAlarmPersonSex() {
        return alarmPersonSex;
    }

    public void setAlarmPersonSex(String alarmPersonSex) {
        this.alarmPersonSex = alarmPersonSex;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressTypeCode() {
        return addressTypeCode;
    }

    public void setAddressTypeCode(String addressTypeCode) {
        this.addressTypeCode = addressTypeCode;
    }

    public String getAddressTypeName() {
        return addressTypeName;
    }

    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

    public String getFirstDealId() {
        return firstDealId;
    }

    public void setFirstDealId(String firstDealId) {
        this.firstDealId = firstDealId;
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

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }

    public String getKeyUnitNumber() {
        return keyUnitNumber;
    }

    public void setKeyUnitNumber(String keyUnitNumber) {
        this.keyUnitNumber = keyUnitNumber;
    }

    public String getKeyUnitCode() {
        return keyUnitCode;
    }

    public void setKeyUnitCode(String keyUnitCode) {
        this.keyUnitCode = keyUnitCode;
    }

    public String getKeyUnitName() {
        return keyUnitName;
    }

    public void setKeyUnitName(String keyUnitName) {
        this.keyUnitName = keyUnitName;
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

    public String getIncidentOperator() {
        return incidentOperator;
    }

    public void setIncidentOperator(String incidentOperator) {
        this.incidentOperator = incidentOperator;
    }

    public String getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(String voiceFile) {
        this.voiceFile = voiceFile;
    }

    public String getBelongPersonAccount() {
        return belongPersonAccount;
    }

    public void setBelongPersonAccount(String belongPersonAccount) {
        this.belongPersonAccount = belongPersonAccount;
    }

    public String getBelongPersonName() {
        return belongPersonName;
    }

    public void setBelongPersonName(String belongPersonName) {
        this.belongPersonName = belongPersonName;
    }

    public Integer getIncidentUpgradeStateCode() {
        return incidentUpgradeStateCode;
    }

    public void setIncidentUpgradeStateCode(Integer incidentUpgradeStateCode) {
        this.incidentUpgradeStateCode = incidentUpgradeStateCode;
    }

    public String getIncidentUpgradeStateName() {
        return incidentUpgradeStateName;
    }

    public void setIncidentUpgradeStateName(String incidentUpgradeStateName) {
        this.incidentUpgradeStateName = incidentUpgradeStateName;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getSortField() {
        return sortField;
    }

    public void setSortField(Integer sortField) {
        this.sortField = sortField;
    }

    public List<String> getAttentionPersonAccount() {
        return attentionPersonAccount;
    }

    public void setAttentionPersonAccount(List<String> attentionPersonAccount) {
        this.attentionPersonAccount = attentionPersonAccount;
    }

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

//    public String getAlarmPersonIdentityNumber() {
//        return alarmPersonIdentityNumber;
//    }
//
//    public void setAlarmPersonIdentityNumber(String alarmPersonIdentityNumber) {
//        this.alarmPersonIdentityNumber = alarmPersonIdentityNumber;
//    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getYjzt() {
        return yjzt;
    }

    public void setYjzt(Integer yjzt) {
        this.yjzt = yjzt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWechatIncidentNumber() {
        return wechatIncidentNumber;
    }

    public void setWechatIncidentNumber(String wechatIncidentNumber) {
        this.wechatIncidentNumber = wechatIncidentNumber;
    }

    public Integer getLargeSign() {
        return largeSign;
    }

    public void setLargeSign(Integer largeSign) {
        this.largeSign = largeSign;
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

    public Integer getUnDispatchType() {
        return unDispatchType;
    }

    public void setUnDispatchType(Integer unDispatchType) {
        this.unDispatchType = unDispatchType;
    }

    public String getUnDispatchOrderId() {
        return unDispatchOrderId;
    }

    public void setUnDispatchOrderId(String unDispatchOrderId) {
        this.unDispatchOrderId = unDispatchOrderId;
    }

    public Integer getUnDispatchOrderState() {
        return unDispatchOrderState;
    }

    public void setUnDispatchOrderState(Integer unDispatchOrderState) {
        this.unDispatchOrderState = unDispatchOrderState;
    }

    public String getUnDispatchOrderStateName() { return unDispatchOrderStateName; }

    public void setUnDispatchOrderStateName(String unDispatchOrderStateName) { this.unDispatchOrderStateName = unDispatchOrderStateName; }

    public List<AttachmentBO> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentBO> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<SoundRecordBO> getSoundRecordList() {
        return soundRecordList;
    }

    public void setSoundRecordList(List<SoundRecordBO> soundRecordList) {
        this.soundRecordList = soundRecordList;
    }

    public List<EvidenceBO> getEvidenceList() {
        return evidenceList;
    }

    public void setEvidenceList(List<EvidenceBO> evidenceList) {
        this.evidenceList = evidenceList;
    }

    public IncidentExtend110BO getIncidentExtend110() {
        return incidentExtend110;
    }

    public void setIncidentExtend110(IncidentExtend110BO incidentExtend110) {
        this.incidentExtend110 = incidentExtend110;
    }

    public IncidentExtend119BO getIncidentExtend119() {
        return incidentExtend119;
    }

    public void setIncidentExtend119(IncidentExtend119BO incidentExtend119) {
        this.incidentExtend119 = incidentExtend119;
    }

    public IncidentExtend120BO getIncidentExtend120() {
        return incidentExtend120;
    }

    public void setIncidentExtend120(IncidentExtend120BO incidentExtend120) {
        this.incidentExtend120 = incidentExtend120;
    }

    public IncidentExtend122BO getIncidentExtend122() {
        return incidentExtend122;
    }

    public void setIncidentExtend122(IncidentExtend122BO incidentExtend122) {
        this.incidentExtend122 = incidentExtend122;
    }



    public String getAlarmPhone() {
        return alarmPhone;
    }

    public void setAlarmPhone(String alarmPhone) {
        this.alarmPhone = alarmPhone;
    }

    public String getIncidentMark() {
        return incidentMark;
    }

    public void setIncidentMark(String incidentMark) {
        this.incidentMark = incidentMark;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public Integer getLevelOrder() {
        return levelOrder;
    }

    public void setLevelOrder(Integer levelOrder) {
        this.levelOrder = levelOrder;
    }

    public Integer getAttentionType() { return attentionType; }

    public void setAttentionType(Integer attentionType) { this.attentionType = attentionType; }

    public Integer getCancelAttentionType() { return cancelAttentionType; }

    public void setCancelAttentionType(Integer cancelAttentionType) { this.cancelAttentionType = cancelAttentionType; }



    public List<AuxiliaryInformationBO> getAuxiliaryPhoneBOS(){return auxiliaryPhoneBOS;}

    public void setAuxiliaryPhoneBOS(List<AuxiliaryInformationBO> auxiliaryPhoneBOS){ this.auxiliaryPhoneBOS = auxiliaryPhoneBOS;}

    public String getDispatchFormId() {
        return dispatchFormId;
    }

    public void setDispatchFormId(String dispatchFormId) {
        this.dispatchFormId = dispatchFormId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }



    public Integer getIsPreAttentForce() { return isPreAttentForce; }

    public void setIsPreAttentForce(Integer isPreAttentForce) { this.isPreAttentForce = isPreAttentForce; }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public Integer getIsSignTimeOut() { return isSignTimeOut; }

    public void setIsSignTimeOut(Integer isSignTimeOut) { this.isSignTimeOut = isSignTimeOut; }

    public Integer getIsArriveTimeOut() { return isArriveTimeOut; }

    public void setIsArriveTimeOut(Integer isArriveTimeOut) { this.isArriveTimeOut = isArriveTimeOut; }

    public Integer getIsFeedBackTimeOut() { return isFeedBackTimeOut; }

    public void setIsFeedBackTimeOut(Integer isFeedBackTimeOut) { this.isFeedBackTimeOut = isFeedBackTimeOut; }

    public Integer getIsFeedBack() { return isFeedBack; }

    public void setIsFeedBack(Integer isFeedBack) { this.isFeedBack = isFeedBack; }

    public List<IncidentAddInfoBO> getAddInfoRecords() {
        return addInfoRecords;
    }

    public void setAddInfoRecords(List<IncidentAddInfoBO> addInfoRecords) {
        this.addInfoRecords = addInfoRecords;
    }

    public String getJwptbh() {
        return jwptbh;
    }

    public void setJwptbh(String jwptbh) {
        this.jwptbh = jwptbh;
    }

    public String getXdTaskId() {
        return xdTaskId;
    }

    public void setXdTaskId(String xdTaskId) {
        this.xdTaskId = xdTaskId;
    }

    public String getXdTaskState() {
        return xdTaskState;
    }

    public void setXdTaskState(String xdTaskState) {
        this.xdTaskState = xdTaskState;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
