package com.dscomm.iecs.accept.graphql.inputbean;

/**
 *  描述: 立案与派车同接口 保存
 *
 */
public class IncidentHandleSaveInputInfo {

    private  IncidentSaveInputInfo incidentSaveInputInfo ; //立案信息

    private HandleSaveInputInfo handleSaveInputInfo ; //派车信息

    public IncidentSaveInputInfo getIncidentSaveInputInfo() {
        return incidentSaveInputInfo;
    }

    public void setIncidentSaveInputInfo(IncidentSaveInputInfo incidentSaveInputInfo) {
        this.incidentSaveInputInfo = incidentSaveInputInfo;
    }

    public HandleSaveInputInfo getHandleSaveInputInfo() {
        return handleSaveInputInfo;
    }

    public void setHandleSaveInputInfo(HandleSaveInputInfo handleSaveInputInfo) {
        this.handleSaveInputInfo = handleSaveInputInfo;
    }
}
