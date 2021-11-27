package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *   集结项信息 ( 集结力量信息)
 *
 */
@Entity
@Table(name = "T_COC_FIRE_JJX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RallyItemEntity extends BaseEntity {

    @Column(name = "SJID"   , length = 100 )
    private String incidentId;// 事件ID

    @Column(name = "ZHID" , length = 100)
    private String commandId;// 指挥ID

    @Column(name = "JJDID" , length = 100 )
    private String rallyPointId;// 集结点ID

    @Column(name = "JJXLX", length = 100)
    private String rallyPowerType;// 集结力量类型 VEHICLE ( 车辆 ) ORGANIZATION 机构 可拓展

    @Column(name = "JJXID", length = 100)
    private String rallyPowerId;//   集结力量ID

    @Column(name = "JJXMC", length = 200)
    private String rallyPowerName;// 集结力量名称

    @Column(name = "SSDW", length = 100)
    private String organizationId;// 所属消防机构ID

    @Column(name = "SSDWMC", length = 100)
    private String organizationName;// 所属消防机构名称

    @Column(name = "TXT1", length = 800)
    private String customcredit1;// 保留字段1

    @Column(name = "TXT2", length = 800)
    private String customcredit2;// 保留字段2

    @Column(name = "BZ", length = 800)
    private String remarks;// 备注


    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(String rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public String getRallyPowerType() {
        return rallyPowerType;
    }

    public void setRallyPowerType(String rallyPowerType) {
        this.rallyPowerType = rallyPowerType;
    }

    public String getRallyPowerId() {
        return rallyPowerId;
    }

    public void setRallyPowerId(String rallyPowerId) {
        this.rallyPowerId = rallyPowerId;
    }

    public String getRallyPowerName() {
        return rallyPowerName;
    }

    public void setRallyPowerName(String rallyPowerName) {
        this.rallyPowerName = rallyPowerName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getCustomcredit1() {
        return customcredit1;
    }

    public void setCustomcredit1(String customcredit1) {
        this.customcredit1 = customcredit1;
    }

    public String getCustomcredit2() {
        return customcredit2;
    }

    public void setCustomcredit2(String customcredit2) {
        this.customcredit2 = customcredit2;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
