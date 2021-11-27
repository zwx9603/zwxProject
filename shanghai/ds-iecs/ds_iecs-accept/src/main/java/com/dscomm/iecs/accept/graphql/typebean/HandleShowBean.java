package com.dscomm.iecs.accept.graphql.typebean;

import java.util.ArrayList;
import java.util.List;

/**
 *  描述: 处警记录 简要显示
 *
 */
public class HandleShowBean   {

    private String incidentId; // 案件信息ID

    private String handleId; // 处警信息id

    private Long handleTime;// 处警时间

    private String handleBatch;// 处警批次

    private List<String> dispatchOrganizationName = new ArrayList<>()  ; //出动机构名称

    private  Integer dispatchParticipant =0  ; // 出动人员数

    private List<String> dispatchVehicle = new ArrayList<>() ; // 出动车辆id

    private List<HandleShowVehicleBean> handleShowVehicleBeans  = new ArrayList<>() ; //车辆 类型分布

    public List<HandleShowVehicleBean> getHandleShowVehicleBeans() {
        return handleShowVehicleBeans;
    }

    public void setHandleShowVehicleBeans(List<HandleShowVehicleBean> handleShowVehicleBeans) {
        this.handleShowVehicleBeans = handleShowVehicleBeans;
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

    public Long getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Long handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleBatch() {
        return handleBatch;
    }

    public void setHandleBatch(String handleBatch) {
        this.handleBatch = handleBatch;
    }


    public List<String> getDispatchOrganizationName() {
        return dispatchOrganizationName;
    }

    public void setDispatchOrganizationName(List<String> dispatchOrganizationName) {
        this.dispatchOrganizationName = dispatchOrganizationName;
    }

    public Integer getDispatchParticipant() {
        return dispatchParticipant;
    }

    public void setDispatchParticipant(Integer dispatchParticipant) {
        this.dispatchParticipant = dispatchParticipant;
    }

    public List<String> getDispatchVehicle() {
        return dispatchVehicle;
    }

    public void setDispatchVehicle(List<String> dispatchVehicle) {
        this.dispatchVehicle = dispatchVehicle;
    }





}
