package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:指挥类
 *
         */
    @Entity
    @Table(name = "T_COC_FIRE_SJZH")
    public class CommandEntity extends BaseEntity {

        @Column(name = "AJID",length = 100)
        private String incidentId;//案件ID

        @Column(name = "QDDWID",length = 100)
        private String startOrganizationId;        //启动单位ID

        @Column(name = "QDDWMC",length = 200)
        private String startOrganizationName;    //启动单位名称

        @Column(name = "QDRID",length = 100)
        private String initiatorId ;        //启动人ID
        @Column(name = "QDRMC",length = 100)
        private String initiatorName;    //启动人名字

        @Column(name = "QDSJ")
        private Long startTime;        //启动时间

        @Column(name = "JSSJ")
        private Long endTime;            //结束时间

        @Column(name = "SFDXAL" ,length = 100)
        private String typicalIncident ;        //是否典型案例

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getStartOrganizationId() {
        return startOrganizationId;
    }

    public void setStartOrganizationId(String startOrganizationId) {
        this.startOrganizationId = startOrganizationId;
    }

    public String getStartOrganizationName() {
        return startOrganizationName;
    }

    public void setStartOrganizationName(String startOrganizationName) {
        this.startOrganizationName = startOrganizationName;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getTypicalIncident() {
        return typicalIncident;
    }

    public void setTypicalIncident(String typicalIncident) {
        this.typicalIncident = typicalIncident;
    }
}
