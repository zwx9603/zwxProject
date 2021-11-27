package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *  描述: 调派 联勤保障单位
 *
 */
public class HandleUnitJointServiceBean extends BaseBean {

    private String incidentId; //案件ID

    private String handleId;// 处警记录ID

    private String safeguardUnitId ;// 联勤保障单位ID

    private String safeguardTypeCode ;// 保障类型代码

    private Integer dispatchNum;//调派数量

    private Integer safeguardAbilityTypeName;//保障能力类型名称

    private Long dispatchTime;// 调派时间

    private String relayRecordNumber;//录音号

    private String remarks;//备注

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

    public String getSafeguardUnitId() {
        return safeguardUnitId;
    }

    public void setSafeguardUnitId(String safeguardUnitId) {
        this.safeguardUnitId = safeguardUnitId;
    }

    public String getSafeguardTypeCode() {
        return safeguardTypeCode;
    }

    public void setSafeguardTypeCode(String safeguardTypeCode) {
        this.safeguardTypeCode = safeguardTypeCode;
    }

    public Integer getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(Integer dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public Integer getSafeguardAbilityTypeName() {
        return safeguardAbilityTypeName;
    }

    public void setSafeguardAbilityTypeName(Integer safeguardAbilityTypeName) {
        this.safeguardAbilityTypeName = safeguardAbilityTypeName;
    }

    public Long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
