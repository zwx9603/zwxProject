package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;
import java.util.List;

/**
 * 描述 : 指令单信息
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/12/24 16:57
 */
public class OrderInfoBO implements Serializable {
    /**
     * 指令单编号
     */
    private String id;
    /**
     * 事件单编号
     */
    private String incidentId;
    /**
     * 处警单编号
     */
    private String dispatchId;
    /**
     * 出警单位code
     */
    private String handleOrgCode;
    /**
     * 出警单位name
     */
    private String handleOrgName;
    /**
     * 指令下达时间
     */
    private Long orderAssignTime;
    /**
     * 指令到达时间
     */
    private Long orderArriveTime;
    /**
     * 指令接收时间
     */
    private Long orderReceiveTime;
    /**
     * 指令接收单位
     */
    private String orderReceiveOrgCode;
    /**
     * 指令接收单位name
     */
    private String orderReceiveOrgName;
    /**
     * 指令接收人工号
     */
    private String orderReceivePersonAccount;
    /**
     * 指令接收人姓名
     */
    private String orderReceivePersonName;
    /**
     * 出动时间
     */
    private Long departTime;
    /**
     * 到场时间
     */
    private Long sceneArriveTime;
    /**
     * 指令状态
     */
    private Integer orderState;
    /**
     * 指令状态name
     */
    private String orderStateName;
    /**
     * 时间戳
     */
    private Long sjc;
    /**
     * 要求反馈时间
     */
    private Long requiredFeedbackTime;
    /**
     * 反馈超时标记
     */
    private Integer feedbackOvertimeStatus;
    /**
     * 无需反馈标记
     */
    private Integer noNeedFeedbackStatus;
    /**
     * 预案指令编号
     */
    private String modelInstructionId;
    /**
     * IMEI号
     */
    private String imei;
    /**
     * 反馈单
     */
    private List<FeedbackBO> feedbackBOList;
    /**
     * 指令催促次数
     */
    private Integer orderUrgeTimes;
    /**
     * 指令类型（1：单位，2：unit单元，3：终端）
     */
    private Integer orderType;
    /**
     * 指令类型name
     */
    private String orderTypeName;
    /**
     * 单位指令单扩展
     */
    private OrderOrgExtendBO orderOrgExtend;
    /**
     * unit单元指令单扩展
     */
    private OrderUnitExtendBO orderUnitExtend;
    /**
     * 终端指令单扩展
     */
    private OrderTerminalExtendBO orderTerminalExtend;
    /**
     * 主责单位标记 0 否 1 是
     */
    private Integer mainResponsibleOrg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHandleOrgCode() {
        return handleOrgCode;
    }

    public void setHandleOrgCode(String handleOrgCode) {
        this.handleOrgCode = handleOrgCode;
    }

    public String getHandleOrgName() {
        return handleOrgName;
    }

    public void setHandleOrgName(String handleOrgName) {
        this.handleOrgName = handleOrgName;
    }

    public Long getOrderAssignTime() {
        return orderAssignTime;
    }

    public void setOrderAssignTime(Long orderAssignTime) {
        this.orderAssignTime = orderAssignTime;
    }

    public Long getOrderArriveTime() {
        return orderArriveTime;
    }

    public void setOrderArriveTime(Long orderArriveTime) {
        this.orderArriveTime = orderArriveTime;
    }

    public Long getOrderReceiveTime() {
        return orderReceiveTime;
    }

    public void setOrderReceiveTime(Long orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    public String getOrderReceiveOrgCode() {
        return orderReceiveOrgCode;
    }

    public void setOrderReceiveOrgCode(String orderReceiveOrgCode) {
        this.orderReceiveOrgCode = orderReceiveOrgCode;
    }

    public String getOrderReceiveOrgName() {
        return orderReceiveOrgName;
    }

    public void setOrderReceiveOrgName(String orderReceiveOrgName) {
        this.orderReceiveOrgName = orderReceiveOrgName;
    }

    public String getOrderReceivePersonAccount() {
        return orderReceivePersonAccount;
    }

    public void setOrderReceivePersonAccount(String orderReceivePersonAccount) {
        this.orderReceivePersonAccount = orderReceivePersonAccount;
    }

    public String getOrderReceivePersonName() {
        return orderReceivePersonName;
    }

    public void setOrderReceivePersonName(String orderReceivePersonName) {
        this.orderReceivePersonName = orderReceivePersonName;
    }

    public Long getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Long departTime) {
        this.departTime = departTime;
    }

    public Long getSceneArriveTime() {
        return sceneArriveTime;
    }

    public void setSceneArriveTime(Long sceneArriveTime) {
        this.sceneArriveTime = sceneArriveTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderStateName() {
        return orderStateName;
    }

    public void setOrderStateName(String orderStateName) {
        this.orderStateName = orderStateName;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

    public Long getRequiredFeedbackTime() {
        return requiredFeedbackTime;
    }

    public void setRequiredFeedbackTime(Long requiredFeedbackTime) {
        this.requiredFeedbackTime = requiredFeedbackTime;
    }

    public Integer getFeedbackOvertimeStatus() {
        return feedbackOvertimeStatus;
    }

    public void setFeedbackOvertimeStatus(Integer feedbackOvertimeStatus) {
        this.feedbackOvertimeStatus = feedbackOvertimeStatus;
    }

    public Integer getNoNeedFeedbackStatus() {
        return noNeedFeedbackStatus;
    }

    public void setNoNeedFeedbackStatus(Integer noNeedFeedbackStatus) {
        this.noNeedFeedbackStatus = noNeedFeedbackStatus;
    }

    public String getModelInstructionId() {
        return modelInstructionId;
    }

    public void setModelInstructionId(String modelInstructionId) {
        this.modelInstructionId = modelInstructionId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public List<FeedbackBO> getFeedbackBOList() {
        return feedbackBOList;
    }

    public void setFeedbackBOList(List<FeedbackBO> feedbackBOList) {
        this.feedbackBOList = feedbackBOList;
    }

    public Integer getOrderUrgeTimes() {
        return orderUrgeTimes;
    }

    public void setOrderUrgeTimes(Integer orderUrgeTimes) {
        this.orderUrgeTimes = orderUrgeTimes;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public OrderOrgExtendBO getOrderOrgExtend() {
        return orderOrgExtend;
    }

    public void setOrderOrgExtend(OrderOrgExtendBO orderOrgExtend) {
        this.orderOrgExtend = orderOrgExtend;
    }

    public OrderUnitExtendBO getOrderUnitExtend() {
        return orderUnitExtend;
    }

    public void setOrderUnitExtend(OrderUnitExtendBO orderUnitExtend) {
        this.orderUnitExtend = orderUnitExtend;
    }

    public OrderTerminalExtendBO getOrderTerminalExtend() {
        return orderTerminalExtend;
    }

    public void setOrderTerminalExtend(OrderTerminalExtendBO orderTerminalExtend) { this.orderTerminalExtend = orderTerminalExtend; }

    public Integer getMainResponsibleOrg() { return mainResponsibleOrg; }

    public void setMainResponsibleOrg(Integer mainResponsibleOrg) { this.mainResponsibleOrg = mainResponsibleOrg; }
}
