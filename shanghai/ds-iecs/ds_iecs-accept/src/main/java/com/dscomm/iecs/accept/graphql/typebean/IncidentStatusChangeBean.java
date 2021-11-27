package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 警情状态变动表
 *
 */
public class IncidentStatusChangeBean extends BaseBean {

    private String incidentId;//案件ID

    private String incidentStatusCode; //案件状态代码

    private String incidentStatusName; //案件状态名称

    private Long changeTime;//变动时间

    private String remarks ; //备注


    public String getIncidentStatusName() {
        return incidentStatusName;
    }

    public void setIncidentStatusName(String incidentStatusName) {
        this.incidentStatusName = incidentStatusName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public Long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
