package com.dscomm.iecs.accept.graphql.typebean;

/**
 *  描述: 立案与调派同接口 返回结果
 *
 */
public class HandleIncidentBean  {

    private IncidentBean incidentBean ; //警情返回结果

    private HandleBean handleBean ; //调派返回结果

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }

    public HandleBean getHandleBean() {
        return handleBean;
    }

    public void setHandleBean(HandleBean handleBean) {
        this.handleBean = handleBean;
    }
}
