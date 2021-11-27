package com.dscomm.iecs.accept.service.bean.cad;


import java.io.Serializable;
import java.util.List;

/**
 * 描述:反馈单表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/29 11:08
 */
public class FeedbackBO implements Serializable {
    /**
     * 反馈单编号
     */
    private String id;
    /**
     * 行政区划
     */
    private String area;
    /**
     * 行政区划name
     */
    private String areaName;
    /**
     * 事件单编号
     */
    private String incidentId;
    /**
     * 处警单编号
     */
    private String dispatchId;
    /**
     * 指令单编号
     */
    private String orderId;
    /**
     * 指令接收时间
     */
    private Long orderReceiveTime;
    /**
     * 反馈类型
     */
    private String feedbackType;
    /**
     * 反馈类型name
     */
    private String feedbackTypeName;
    /**
     * 反馈开始时间
     */
    private Long feedbackStartTime;
    /**
     * 反馈单位
     */
    private String feedbackUnit;
    /**
     * 反馈单位id
     */
    private String feedbackUnitId;
    /**
     * 反馈单位name
     */
    private String feedbackUnitName;
    /**
     * 反馈台号
     */
    private Integer feedbackAgentNum;
    /**
     * 反馈人工号
     */
    private String feedbackPersonAccount;
    /**
     * 反馈人姓名
     */
    private String feedbackPersonName;
    /**
     * 反馈填写单位(userinfo)
     */
    private String feedbackWriteUnit;
    /**
     * 反馈填写单位(userinfo)id
     */
    private String feedbackWriteUnitId;
    /**
     * 反馈填写单位(userinfo)name
     */
    private String feedbackWriteUnitName;
    /**
     * 反馈保存时间
     */
    private Long feedbackSaveTime;
    /**
     * 反馈时长
     */
    private Integer feedbackDuration;
    /**
     * 反馈接收时间
     */
    private Long feedbackReceiveTime;
    /**
     * 反馈接收单位
     */
    private String feedbackReceiveUnit;
    /**
     * 反馈接收单位id
     */
    private String feedbackReceiveUnitId;
    /**
     * 反馈接收单位name
     */
    private String feedbackReceiveUnitName;
    /**
     * 反馈接收人工号
     */
    private String feedbackReceivePersonAccount;
    /**
     * 反馈接收人姓名
     */
    private String feedbackReceivePersonName;
    /**
     * 反馈接收台号
     */
    private Integer feedbackReceiveAgentNum;
    /**
     * 反馈接收状态
     */
    private Integer feedbackReceiveState;
    /**
     * 反馈接收状态name
     */
    private String feedbackReceiveStateName;
    /**
     * 终结标志
     */
    private Integer endMark;
    /**
     * 终结标志name
     */
    private String endMarkName;
    /**
     * 审核标志
     */
    private Integer auditMark;
    /**
     * 审核标志name
     */
    private String auditMarkName;
    /**
     * 处理类型
     */
    private String processType;
    /**
     * 处理类型name
     */
    private String processTypeName;
    /**
     * 关联事件单编号
     */
    private String relatedIncidentId;
    /**
     * 案由
     */
    private String incidentType;
    /**
     * 案由name
     */
    private String incidentTypeName;
    /**
     * 一级案由
     */
    private String incidentParentType;
    /**
     * 一级案由name
     */
    private String incidentParentTypeName;
    /**
     * 警情级别
     */
    private String incidentLevel;
    /**
     * 警情级别name
     */
    private String incidentLevelName;
    /**
     * 处理结果
     */
    private String processResult;

    /**
     * 身份证号
     */
    private String alarmIDCard;
    /**
     * 处理结果name
     */
    private String processResultName;
    /**
     * 出警情况
     */
    private String policeDepartStatus;
    /**
     * 时间戳
     */
    private Long sjc;
    /**
     * 语音文件
     */
    private String voiceFile;
    /**
     * X坐标/经度
     */
    private String longitude;
    /**
     * Y坐标/维度
     */
    private String latitude;
    /**
     * 来源
     */
    private String source;
    /**
     * unit编号
     */
    private String unitId;
    /**
     * 处警对象
     */
    private String dispatchObject;
    /**
     * unit指令单id
     */
    private String unitOrderId;

    /**
     * 证据列表
     */
    private List<EvidenceBO> evidenceList;
    /**
     * 到场反馈扩展
     */
    private FeedbackExtendArriveBO feedbackExtendArriveBO;
    /**
     * 终端反馈扩展
     */
    private FeedbackExtendTerminalBO feedbackExtendTerminalBO;
    /**
     * 110反馈扩展
     */
    private FeedbackExtend110BO feedbackExtend110BO;
    /**
     * 119反馈扩展
     */
    private FeedbackExtend119BO feedbackExtend119BO;
    /**
     * 120反馈扩展
     */
    private FeedbackExtend120BO feedbackExtend120BO;
    /**
     * 122反馈扩展
     */
    private FeedbackExtend122BO feedbackExtend122BO;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getOrderReceiveTime() {
        return orderReceiveTime;
    }

    public void setOrderReceiveTime(Long orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackTypeName() {
        return feedbackTypeName;
    }

    public void setFeedbackTypeName(String feedbackTypeName) {
        this.feedbackTypeName = feedbackTypeName;
    }

    public Long getFeedbackStartTime() {
        return feedbackStartTime;
    }

    public void setFeedbackStartTime(Long feedbackStartTime) {
        this.feedbackStartTime = feedbackStartTime;
    }

    public String getFeedbackUnit() {
        return feedbackUnit;
    }

    public void setFeedbackUnit(String feedbackUnit) {
        this.feedbackUnit = feedbackUnit;
    }

    public String getFeedbackUnitId() {
        return feedbackUnitId;
    }

    public void setFeedbackUnitId(String feedbackUnitId) {
        this.feedbackUnitId = feedbackUnitId;
    }

    public String getFeedbackUnitName() {
        return feedbackUnitName;
    }

    public void setFeedbackUnitName(String feedbackUnitName) {
        this.feedbackUnitName = feedbackUnitName;
    }

    public Integer getFeedbackAgentNum() {
        return feedbackAgentNum;
    }

    public void setFeedbackAgentNum(Integer feedbackAgentNum) {
        this.feedbackAgentNum = feedbackAgentNum;
    }

    public String getFeedbackPersonAccount() {
        return feedbackPersonAccount;
    }

    public void setFeedbackPersonAccount(String feedbackPersonAccount) {
        this.feedbackPersonAccount = feedbackPersonAccount;
    }

    public String getFeedbackPersonName() {
        return feedbackPersonName;
    }

    public void setFeedbackPersonName(String feedbackPersonName) {
        this.feedbackPersonName = feedbackPersonName;
    }

    public String getFeedbackWriteUnit() {
        return feedbackWriteUnit;
    }

    public void setFeedbackWriteUnit(String feedbackWriteUnit) {
        this.feedbackWriteUnit = feedbackWriteUnit;
    }

    public String getFeedbackWriteUnitId() {
        return feedbackWriteUnitId;
    }

    public void setFeedbackWriteUnitId(String feedbackWriteUnitId) {
        this.feedbackWriteUnitId = feedbackWriteUnitId;
    }

    public String getFeedbackWriteUnitName() {
        return feedbackWriteUnitName;
    }

    public void setFeedbackWriteUnitName(String feedbackWriteUnitName) {
        this.feedbackWriteUnitName = feedbackWriteUnitName;
    }

    public Long getFeedbackSaveTime() {
        return feedbackSaveTime;
    }

    public void setFeedbackSaveTime(Long feedbackSaveTime) {
        this.feedbackSaveTime = feedbackSaveTime;
    }

    public Integer getFeedbackDuration() {
        return feedbackDuration;
    }

    public void setFeedbackDuration(Integer feedbackDuration) {
        this.feedbackDuration = feedbackDuration;
    }

    public Long getFeedbackReceiveTime() {
        return feedbackReceiveTime;
    }

    public void setFeedbackReceiveTime(Long feedbackReceiveTime) {
        this.feedbackReceiveTime = feedbackReceiveTime;
    }

    public String getFeedbackReceiveUnit() {
        return feedbackReceiveUnit;
    }

    public void setFeedbackReceiveUnit(String feedbackReceiveUnit) {
        this.feedbackReceiveUnit = feedbackReceiveUnit;
    }

    public String getFeedbackReceiveUnitId() {
        return feedbackReceiveUnitId;
    }

    public void setFeedbackReceiveUnitId(String feedbackReceiveUnitId) {
        this.feedbackReceiveUnitId = feedbackReceiveUnitId;
    }

    public String getFeedbackReceiveUnitName() {
        return feedbackReceiveUnitName;
    }

    public void setFeedbackReceiveUnitName(String feedbackReceiveUnitName) {
        this.feedbackReceiveUnitName = feedbackReceiveUnitName;
    }

    public String getFeedbackReceivePersonAccount() {
        return feedbackReceivePersonAccount;
    }

    public void setFeedbackReceivePersonAccount(String feedbackReceivePersonAccount) {
        this.feedbackReceivePersonAccount = feedbackReceivePersonAccount;
    }

    public String getFeedbackReceivePersonName() {
        return feedbackReceivePersonName;
    }

    public void setFeedbackReceivePersonName(String feedbackReceivePersonName) {
        this.feedbackReceivePersonName = feedbackReceivePersonName;
    }

    public Integer getFeedbackReceiveAgentNum() {
        return feedbackReceiveAgentNum;
    }

    public void setFeedbackReceiveAgentNum(Integer feedbackReceiveAgentNum) {
        this.feedbackReceiveAgentNum = feedbackReceiveAgentNum;
    }

    public Integer getFeedbackReceiveState() {
        return feedbackReceiveState;
    }

    public void setFeedbackReceiveState(Integer feedbackReceiveState) {
        this.feedbackReceiveState = feedbackReceiveState;
    }

    public String getFeedbackReceiveStateName() {
        return feedbackReceiveStateName;
    }

    public void setFeedbackReceiveStateName(String feedbackReceiveStateName) {
        this.feedbackReceiveStateName = feedbackReceiveStateName;
    }

    public Integer getEndMark() {
        return endMark;
    }

    public void setEndMark(Integer endMark) {
        this.endMark = endMark;
    }

    public String getEndMarkName() {
        return endMarkName;
    }

    public void setEndMarkName(String endMarkName) {
        this.endMarkName = endMarkName;
    }

    public Integer getAuditMark() {
        return auditMark;
    }

    public void setAuditMark(Integer auditMark) {
        this.auditMark = auditMark;
    }

    public String getAuditMarkName() {
        return auditMarkName;
    }

    public void setAuditMarkName(String auditMarkName) {
        this.auditMarkName = auditMarkName;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getProcessTypeName() {
        return processTypeName;
    }

    public void setProcessTypeName(String processTypeName) {
        this.processTypeName = processTypeName;
    }

    public String getRelatedIncidentId() {
        return relatedIncidentId;
    }

    public void setRelatedIncidentId(String relatedIncidentId) {
        this.relatedIncidentId = relatedIncidentId;
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

    public String getIncidentLevel() {
        return incidentLevel;
    }

    public String getAlarmIDCard(){ return  alarmIDCard;}

    public void setAlarmIDCard(String alarmIDCard){this.alarmIDCard=alarmIDCard;}

    public void setIncidentLevel(String incidentLevel) {
        this.incidentLevel = incidentLevel;
    }

    public String getIncidentLevelName() {
        return incidentLevelName;
    }

    public void setIncidentLevelName(String incidentLevelName) {
        this.incidentLevelName = incidentLevelName;
    }

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult;
    }

    public String getProcessResultName() {
        return processResultName;
    }

    public void setProcessResultName(String processResultName) {
        this.processResultName = processResultName;
    }

    public String getPoliceDepartStatus() {
        return policeDepartStatus;
    }

    public void setPoliceDepartStatus(String policeDepartStatus) {
        this.policeDepartStatus = policeDepartStatus;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

    public String getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(String voiceFile) {
        this.voiceFile = voiceFile;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<EvidenceBO> getEvidenceList() {
        return evidenceList;
    }

    public void setEvidenceList(List<EvidenceBO> evidenceList) {
        this.evidenceList = evidenceList;
    }

    public FeedbackExtendArriveBO getFeedbackExtendArriveBO() {
        return feedbackExtendArriveBO;
    }

    public void setFeedbackExtendArriveBO(FeedbackExtendArriveBO feedbackExtendArriveBO) {
        this.feedbackExtendArriveBO = feedbackExtendArriveBO;
    }

    public FeedbackExtendTerminalBO getFeedbackExtendTerminalBO() {
        return feedbackExtendTerminalBO;
    }

    public void setFeedbackExtendTerminalBO(FeedbackExtendTerminalBO feedbackExtendTerminalBO) {
        this.feedbackExtendTerminalBO = feedbackExtendTerminalBO;
    }

    public FeedbackExtend110BO getFeedbackExtend110BO() {
        return feedbackExtend110BO;
    }

    public void setFeedbackExtend110BO(FeedbackExtend110BO feedbackExtend110BO) {
        this.feedbackExtend110BO = feedbackExtend110BO;
    }

    public FeedbackExtend119BO getFeedbackExtend119BO() {
        return feedbackExtend119BO;
    }

    public void setFeedbackExtend119BO(FeedbackExtend119BO feedbackExtend119BO) {
        this.feedbackExtend119BO = feedbackExtend119BO;
    }

    public FeedbackExtend120BO getFeedbackExtend120BO() {
        return feedbackExtend120BO;
    }

    public void setFeedbackExtend120BO(FeedbackExtend120BO feedbackExtend120BO) {
        this.feedbackExtend120BO = feedbackExtend120BO;
    }

    public FeedbackExtend122BO getFeedbackExtend122BO() {
        return feedbackExtend122BO;
    }

    public void setFeedbackExtend122BO(FeedbackExtend122BO feedbackExtend122BO) {
        this.feedbackExtend122BO = feedbackExtend122BO;
    }


    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getDispatchObject() {
        return dispatchObject;
    }

    public void setDispatchObject(String dispatchObject) {
        this.dispatchObject = dispatchObject;
    }

    public String getUnitOrderId() {
        return unitOrderId;
    }

    public void setUnitOrderId(String unitOrderId) {
        this.unitOrderId = unitOrderId;
    }

}
