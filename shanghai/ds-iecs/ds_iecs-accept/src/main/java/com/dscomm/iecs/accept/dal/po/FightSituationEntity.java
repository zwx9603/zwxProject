package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：作战情况表
 *
 */
@Entity
@Table(name = "JCJ_ZZQK ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FightSituationEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId;//案件id

    @Column(name = "ZDQK", length = 2000 )
    private String combatSituation;//战斗情况

    @Column(name = "XCZZH", length = 100 )
    private String localeCommander ;//现场总指挥

    @Column(name = "XCFZH", length = 100 )
    private String localeDeputyCommander;//现场副指挥

    @Column(name = "DDY119", length = 100 )
    private String dispatcher;//119调度员

    @Column(name = "XCTXY", length = 100 )
    private String localeCorrespondent;//现场通讯员

    @Column(name = "ZDTXY", length = 100 )
    private String detachmentCorrespondent;//支队通讯员

    @Column(name = "ZDZHY", length = 100 )
    private String squadronCommander;//中队指挥员

    @Column(name = "ZDDTXY", length = 100 )
    private String squadronCorrespondent;//中队通讯员

    @Column(name = "LXY", length = 100 )
    private String videographer;//录像人员

    @Column(name = "CDCC" )
    private Integer dispatchTrainNum; // 出动车次

    @Column(name = "CDCS" )
    private Integer dispatchVehicleNum; // 出动车辆数

    @Column(name = "CDRS" )
    private Integer dispatchPersonNum; // 出动人数

    @Column(name = "CDSQS"  )
    private Integer dispatchWaterGunNum;//出动水枪数

    @Column(name = "ZDGCMS" ,length = 800)
    private String combatProcessDesc ; // 战斗过程描述

    @Column(name = "BZ",length = 800)
    private String remarks;//备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCombatSituation() {
        return combatSituation;
    }

    public void setCombatSituation(String combatSituation) {
        this.combatSituation = combatSituation;
    }

    public String getLocaleCommander() {
        return localeCommander;
    }

    public void setLocaleCommander(String localeCommander) {
        this.localeCommander = localeCommander;
    }

    public String getLocaleDeputyCommander() {
        return localeDeputyCommander;
    }

    public void setLocaleDeputyCommander(String localeDeputyCommander) {
        this.localeDeputyCommander = localeDeputyCommander;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getLocaleCorrespondent() {
        return localeCorrespondent;
    }

    public void setLocaleCorrespondent(String localeCorrespondent) {
        this.localeCorrespondent = localeCorrespondent;
    }

    public String getDetachmentCorrespondent() {
        return detachmentCorrespondent;
    }

    public void setDetachmentCorrespondent(String detachmentCorrespondent) {
        this.detachmentCorrespondent = detachmentCorrespondent;
    }

    public String getSquadronCommander() {
        return squadronCommander;
    }

    public void setSquadronCommander(String squadronCommander) {
        this.squadronCommander = squadronCommander;
    }

    public String getSquadronCorrespondent() {
        return squadronCorrespondent;
    }

    public void setSquadronCorrespondent(String squadronCorrespondent) {
        this.squadronCorrespondent = squadronCorrespondent;
    }

    public String getVideographer() {
        return videographer;
    }

    public void setVideographer(String videographer) {
        this.videographer = videographer;
    }

    public Integer getDispatchPersonNum() {
        return dispatchPersonNum;
    }

    public void setDispatchPersonNum(Integer dispatchPersonNum) {
        this.dispatchPersonNum = dispatchPersonNum;
    }

    public Integer getDispatchVehicleNum() {
        return dispatchVehicleNum;
    }

    public void setDispatchVehicleNum(Integer dispatchVehicleNum) {
        this.dispatchVehicleNum = dispatchVehicleNum;
    }

    public Integer getDispatchTrainNum() {
        return dispatchTrainNum;
    }

    public void setDispatchTrainNum(Integer dispatchTrainNum) {
        this.dispatchTrainNum = dispatchTrainNum;
    }

    public String getCombatProcessDesc() {
        return combatProcessDesc;
    }

    public void setCombatProcessDesc(String combatProcessDesc) {
        this.combatProcessDesc = combatProcessDesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDispatchWaterGunNum() {
        return dispatchWaterGunNum;
    }

    public void setDispatchWaterGunNum(Integer dispatchWaterGunNum) {
        this.dispatchWaterGunNum = dispatchWaterGunNum;
    }
}
