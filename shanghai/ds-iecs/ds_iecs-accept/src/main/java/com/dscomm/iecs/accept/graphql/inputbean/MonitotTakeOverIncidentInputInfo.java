package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;

/**
 * 描述:班长警情接管警情信息
 *
 * @author YangFuxi
 * Date Time 2019/11/11 11:23
 */
public class MonitotTakeOverIncidentInputInfo {
    private IncidentBean incidentBean ;//警单基本信息
    private String extendContent;//扩展信息 前端存放自定义信息

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }

    public String getExtendContent() {
        return extendContent;
    }

    public void setExtendContent(String extendContent) {
        this.extendContent = extendContent;
    }
}
