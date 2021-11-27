package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;

/**
 * 描述 : unit单元指令单扩展信息
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/12/24 17:00
 */
public class OrderUnitExtendBO implements Serializable {
    /**
     * UNIT单元编号
     */
    private String unitNum;
    /**
     * UNIT单元name
     */
    private String unitName;
    /**
     * 指令单编号
     */
    private String orderId;
    /**
     * 简称??? ---库里是时间戳类型---
     */
    private Long jc;
    /**
     * LTEID（签收LTEID）
     */
    private String receiverLteid;
    /**
     * 处置时间
     */
    private Long operationTime;
    /**
     * 是否被签收
     */
    private Integer isReceivedMark;
    /**
     * 申请时间
     */
    private Long applyTime;
    /**
     * 申请人工号
     */
    private String applyPersonNum;
    /**
     * 申请人姓名
     */
    private String applyPersonName;
    /**
     * 审核单位
     */
    private String auditOrg;
    /**
     * 审核人工号
     */
    private String auditPersonNum;
    /**
     * 审核人姓名
     */
    private String auditPersonName;
    /**
     * 审核结果
     */
    private Integer auditResult;
    /**
     * 审核结论
     */
    private String auditConclusion;
    /**
     * 作废类型
     */
    private String cancelReasonType;
    /**
     * 作废类型name（t_dm_zflx）
     */
    private String cancelReasonTypeName;
    /**
     * 作废原因
     */
    private String cancelReason;
    /**
     * 分类
     */
    private Integer cancelType;
    /**
     * 分类name
     */
    private String cancelTypeName;
    /**
     * 解绑时间
     */
    private Long unbundingTime;

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getJc() {
        return jc;
    }

    public void setJc(Long jc) {
        this.jc = jc;
    }

    public String getReceiverLteid() {
        return receiverLteid;
    }

    public void setReceiverLteid(String receiverLteid) {
        this.receiverLteid = receiverLteid;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getIsReceivedMark() {
        return isReceivedMark;
    }

    public void setIsReceivedMark(Integer isReceivedMark) {
        this.isReceivedMark = isReceivedMark;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyPersonNum() {
        return applyPersonNum;
    }

    public void setApplyPersonNum(String applyPersonNum) {
        this.applyPersonNum = applyPersonNum;
    }

    public String getApplyPersonName() {
        return applyPersonName;
    }

    public void setApplyPersonName(String applyPersonName) {
        this.applyPersonName = applyPersonName;
    }

    public String getAuditOrg() {
        return auditOrg;
    }

    public void setAuditOrg(String auditOrg) {
        this.auditOrg = auditOrg;
    }

    public String getAuditPersonNum() {
        return auditPersonNum;
    }

    public void setAuditPersonNum(String auditPersonNum) {
        this.auditPersonNum = auditPersonNum;
    }

    public String getAuditPersonName() {
        return auditPersonName;
    }

    public void setAuditPersonName(String auditPersonName) {
        this.auditPersonName = auditPersonName;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditConclusion() {
        return auditConclusion;
    }

    public void setAuditConclusion(String auditConclusion) {
        this.auditConclusion = auditConclusion;
    }

    public String getCancelReasonType() {
        return cancelReasonType;
    }

    public void setCancelReasonType(String cancelReasonType) {
        this.cancelReasonType = cancelReasonType;
    }

    public String getCancelReasonTypeName() {
        return cancelReasonTypeName;
    }

    public void setCancelReasonTypeName(String cancelReasonTypeName) {
        this.cancelReasonTypeName = cancelReasonTypeName;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelTypeName() {
        return cancelTypeName;
    }

    public void setCancelTypeName(String cancelTypeName) {
        this.cancelTypeName = cancelTypeName;
    }

    public Long getUnbundingTime() {
        return unbundingTime;
    }

    public void setUnbundingTime(Long unbundingTime) {
        this.unbundingTime = unbundingTime;
    }
}
