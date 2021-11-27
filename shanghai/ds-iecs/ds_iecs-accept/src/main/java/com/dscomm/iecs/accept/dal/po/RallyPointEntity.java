package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  集结点信息
 *
 */
@Entity
@Table(name = "T_COC_FIRE_JJD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RallyPointEntity extends BaseEntity {

    @Column(name = "SJID"   , length = 100 )
    private String incidentId;// 事件ID

    @Column(name = "ZHID" , length = 100)
    private String commandId;// 指挥ID

    @Column(name = "BH" ,length = 100)
    private String rallyPointCode; //集结点编号

    @Column(name = "JJDLX" , length = 100)
    private String rallyPointType;// 集结点类型   SCENE_HEADQUARTERS( 现场指挥部 )  OPERATIONAL_TASK( 作战任务点 )  VEHICLE_AGGREGATION( 车辆集结点 )   EQUIPMENT_AGGREGATION( 器材集结点 ) LOGISTIC_SERVICE( 后勤保障点 )

    @Column(name = "JJDMC" , length = 400 )
    private String rallyPointName;// 集结点名称

    @Column(name = "JJDMS", length = 4000)
    private String rallyPointDesc;// 集结点描述

    @Column(name = "JJDZT" , length = 100)
    private String rallyPointStatus;//集结点状态 SAVED 保存  SENT下达

    @Column(name = "JJSJ")
    private Long rallyPointTime;//集结时间

    @Column(name = "LXR", length = 100)
    private String rallyPointContacts;// 联系人

    @Column(name = "LXDH", length = 50)
    private String rallyPointPhone;// 联系人电话

    @Column(name = "GIS_X", length = 50)
    private String longitude;// 经度

    @Column(name = "GIS_Y" , length = 50)
    private String latitude;// 纬度

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

    public String getRallyPointCode() {
        return rallyPointCode;
    }

    public void setRallyPointCode(String rallyPointCode) {
        this.rallyPointCode = rallyPointCode;
    }

    public String getRallyPointType() {
        return rallyPointType;
    }

    public void setRallyPointType(String rallyPointType) {
        this.rallyPointType = rallyPointType;
    }

    public String getRallyPointName() {
        return rallyPointName;
    }

    public void setRallyPointName(String rallyPointName) {
        this.rallyPointName = rallyPointName;
    }

    public String getRallyPointDesc() {
        return rallyPointDesc;
    }

    public void setRallyPointDesc(String rallyPointDesc) {
        this.rallyPointDesc = rallyPointDesc;
    }

    public String getRallyPointStatus() {
        return rallyPointStatus;
    }

    public void setRallyPointStatus(String rallyPointStatus) {
        this.rallyPointStatus = rallyPointStatus;
    }

    public Long getRallyPointTime() {
        return rallyPointTime;
    }

    public void setRallyPointTime(Long rallyPointTime) {
        this.rallyPointTime = rallyPointTime;
    }

    public String getRallyPointContacts() {
        return rallyPointContacts;
    }

    public void setRallyPointContacts(String rallyPointContacts) {
        this.rallyPointContacts = rallyPointContacts;
    }

    public String getRallyPointPhone() {
        return rallyPointPhone;
    }

    public void setRallyPointPhone(String rallyPointPhone) {
        this.rallyPointPhone = rallyPointPhone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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
