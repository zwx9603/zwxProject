package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;

/**
 * 描述 : 终端指令单扩展信息
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/12/24 17:01
 */
public class OrderTerminalExtendBO implements Serializable {
    /**
     * 指令单编号
     */
    private String orderId;
    /**
     * UNIT调度指令单号
     */
    private String unitDispatchId;
    /**
     * 签收人员账号
     */
    private String receivePersonAccount;
    /**
     * 签收时间
     */
    private Long receiveTime;
    /**
     * 签收终端IMEI
     */
    private String receiveTeminalIMEI;
    /**
     * 签收LTEID
     */
    private String receiverLteid;
    /**
     * 处置时间
     */
    private Long operationTime;
    /**
     * 分类（退单还是作废）
     */
    private Integer cancelType;
    /**
     * 分类name
     */
    private String cancelTypeName;
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
     * 审核单位
     */
    private String auditOrg;
    /**
     * 审核单位name
     */
    private String auditOrgName;
    /**
     * 审核人工号
     */
    private String auditPersonNum;
    /**
     * 审核人姓名
     */
    private String auditPersonName;
    /**
     * 来源
     */
    private String source;
    /**
     * 审核单位机构代码
     */
    private String auditOrgCode;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUnitDispatchId() {
        return unitDispatchId;
    }

    public void setUnitDispatchId(String unitDispatchId) {
        this.unitDispatchId = unitDispatchId;
    }

    public String getReceivePersonAccount() {
        return receivePersonAccount;
    }

    public void setReceivePersonAccount(String receivePersonAccount) {
        this.receivePersonAccount = receivePersonAccount;
    }

    public Long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveTeminalIMEI() {
        return receiveTeminalIMEI;
    }

    public void setReceiveTeminalIMEI(String receiveTeminalIMEI) {
        this.receiveTeminalIMEI = receiveTeminalIMEI;
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

    public String getAuditOrg() {
        return auditOrg;
    }

    public void setAuditOrg(String auditOrg) {
        this.auditOrg = auditOrg;
    }

    public String getAuditOrgName() {
        return auditOrgName;
    }

    public void setAuditOrgName(String auditOrgName) {
        this.auditOrgName = auditOrgName;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuditOrgCode() {
        return auditOrgCode;
    }

    public void setAuditOrgCode(String auditOrgCode) {
        this.auditOrgCode = auditOrgCode;
    }
}
