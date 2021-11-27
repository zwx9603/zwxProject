package com.dscomm.iecs.accept.graphql.typebean;


import java.util.List;

/**
 * 描述:派车websocket消息体
 *
 */
public class HandleWebSocketPushListBean {

    private IncidentBean incidentBean; //案件详情

    private List<HandleOrganizationVehicleBean>  handleOrganizationVehicleBean ; //出动车辆状态变更

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }

    public List<HandleOrganizationVehicleBean> getHandleOrganizationVehicleBean() {
        return handleOrganizationVehicleBean;
    }

    public void setHandleOrganizationVehicleBean(List<HandleOrganizationVehicleBean> handleOrganizationVehicleBean) {
        this.handleOrganizationVehicleBean = handleOrganizationVehicleBean;
    }
}
