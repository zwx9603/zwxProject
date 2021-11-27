package com.dscomm.iecs.accept.graphql.firetypebean;

import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationVehicleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitBean;

import java.util.List;
/**
 * 描述:警情详情类
 *
 * @author YangFuxi
 * Date time 2018年4月26日 下午11:21:44
 */
public class IncidentSummaryBean {

    private String incidentId ; //案件id

    //案件基础信息
    private IncidentBean incidentBean;

    //指挥员信息
    private List<CommanderBean> commanderBeanList;

    //重点单位信息
    private KeyUnitBean keyUnitBean;

    // 警情出动车辆信息（包含车载装备、出动人员)
    private List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList ;

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }

    public List<CommanderBean> getCommanderBeanList() {
        return commanderBeanList;
    }

    public void setCommanderBeanList(List<CommanderBean> commanderBeanList) {
        this.commanderBeanList = commanderBeanList;
    }

    public KeyUnitBean getKeyUnitBean() {
        return keyUnitBean;
    }

    public void setKeyUnitBean(KeyUnitBean keyUnitBean) {
        this.keyUnitBean = keyUnitBean;
    }


    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public List<HandleOrganizationVehicleBean> getHandleOrganizationVehicleBeanList() {
        return handleOrganizationVehicleBeanList;
    }

    public void setHandleOrganizationVehicleBeanList(List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList) {
        this.handleOrganizationVehicleBeanList = handleOrganizationVehicleBeanList;
    }
}
