package com.dscomm.iecs.accept.service.bean;

import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;

/**
 * 描述:作战单元明细
 * author:YangFuXi
 * Date:2021/6/8 Time:16:18
 **/
public class CombatUnitItemBean {
    private String id;//明细id
    private String combatUnitId;//作战单元id
    private String vehicleId;//车辆id
    private Integer valid;//有效性 默认为1
    private Integer available=0;//是否可用 0不可用 1可用
    private EquipmentVehicleBean vehicleBean;//车辆详细信息


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCombatUnitId() {
        return combatUnitId;
    }

    public void setCombatUnitId(String combatUnitId) {
        this.combatUnitId = combatUnitId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public EquipmentVehicleBean getVehicleBean() {
        return vehicleBean;
    }

    public void setVehicleBean(EquipmentVehicleBean vehicleBean) {
        this.vehicleBean = vehicleBean;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
