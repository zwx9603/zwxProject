package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：警情 包围圈实体
 *
 */
@Entity
@Table(name = "T_COC_FIRE_BWQJL")
public class IncidentCircleEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId;//案件ID

    @Column(name = "BWQBJ" , length =  100 )
    private String radius;//半径

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
