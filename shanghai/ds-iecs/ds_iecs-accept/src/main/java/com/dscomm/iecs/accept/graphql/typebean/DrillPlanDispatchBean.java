package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:集合演练方案调度信息
 *
 */
public class DrillPlanDispatchBean extends BaseBean {

    private String drillPlanId ;//演练方案ID

    private String organizationId ;//消防机构编号

    private String organizationName ;//消防机构名称

    private String  vehicleId;//车辆编号

    private String vehicleName;//   车辆名称

    private String vehicleNumber;// 车牌号码

    private String vehicleTypeCode;// 车辆类型代码

    private String vehicleTypeName;// 车辆类型名称

    private Integer orderNum;// 出车顺序

    private Integer whetherValid;// 是否有效

    private String remarks;//备注

    public String getDrillPlanId() {
        return drillPlanId;
    }

    public void setDrillPlanId(String drillPlanId) {
        this.drillPlanId = drillPlanId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getWhetherValid() {
        return whetherValid;
    }

    public void setWhetherValid(Integer whetherValid) {
        this.whetherValid = whetherValid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
