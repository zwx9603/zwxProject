package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述:调派方案使用记录
 * author:YangFuXi
 * Date:2021/6/21 Time:14:29
 **/
public class DispachPlanUsageRecordInputInfo {
    private String planId;//方案id 必填字段
    private String planType;//方案类型 0预案调派 1方案调派 2等级调派 3作战单元调派
    private String incidentId;//案件编号 必填字段
    private String handleId;//处警记录id 如果取不到则不填
    private String remark;//备注

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
