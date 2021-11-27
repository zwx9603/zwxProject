package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:
 * author:YangFuXi
 * Date:2021/6/8 Time:15:17
 **/
@Table(name = "JCJ_ZZDYMX")
@Entity
public class CombatUnitItemEntity extends BaseEntity {
    @Column(name = "zzdyid")
    private String combatUnitId;//作战单元id
    @Column(name = "clid")
    private String vehicleId;//车辆id

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
}
