package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述： 火场安全监控记录 保存
 *
 */
public class FireSafetySaveInputInfo {

    private String incidentId;//案件ID

    private Integer  fireSafetyType ; //操作类型  0 出火场 1 进火场

    private List<FireSafetyMonitoringSaveInputInfo> fireSafetyMonitoringSaveInputInfo ; //火场进出人员信息

    public Integer getFireSafetyType() {
        return fireSafetyType;
    }

    public void setFireSafetyType(Integer fireSafetyType) {
        this.fireSafetyType = fireSafetyType;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public List<FireSafetyMonitoringSaveInputInfo> getFireSafetyMonitoringSaveInputInfo() {
        return fireSafetyMonitoringSaveInputInfo;
    }

    public void setFireSafetyMonitoringSaveInputInfo(List<FireSafetyMonitoringSaveInputInfo> fireSafetyMonitoringSaveInputInfo) {
        this.fireSafetyMonitoringSaveInputInfo = fireSafetyMonitoringSaveInputInfo;
    }
}
