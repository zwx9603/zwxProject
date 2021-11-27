package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:集合演练方案调度信息
 *
 */
@Entity
@Table(name = "JCJ_JHYLFADD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DrillPlanDispatchEntity extends BaseEntity {

    @Column(name = "YLFAID", length = 100)
    private String drillPlanId ;//演练方案ID

    @Column(name = "XFJGBH", length = 200)
    private String organizationId ;//消防机构编号

    @Column(name = "CLBH", length = 100)
    private String  vehicleId;//车辆编号

    @Column(name = "CLMC", length = 200)
    private String vehicleName;//   车辆名称

    @Column(name = "CPHM", length = 50)
    private String vehicleNumber;// 车牌号码

    @Column(name = "CLLX", length = 100)
    private String vehicleTypeCode;// 车辆类型代码

    @Column(name = "CCSX" )
    private Integer orderNum;// 出车顺序

    @Column(name = "SFYX", length = 100)
    private Integer whetherValid;// 是否有效

    @Column(name = "BZ", length = 800)
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

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
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
