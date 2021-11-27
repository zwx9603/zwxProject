package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 现场指挥员
 *
 * @author AIguibin Date time 2018/7/16 10:29
 */
@Entity
@Table(name = "T_COC_FIRE_XCZHY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CommanderEntity extends BaseEntity {
    @Column(name = "AJID")
    private String incidentId  ; //案件id

    @Column(name = "ZHID")
    private String commandId  ; //指挥id

    @Column(name = "ZHYQZ")
    private String commanderType ; //指挥员现场类型 (1:总指挥,2:副指挥,3:指挥长 4 指挥员 5车辆指挥员 )

    @Column(name = "CLID ")
    private String vehicleId; // 车辆id

    @Column(name="CDRYS")
    private Integer personNum;//车载人员数 （ 包含指挥员 ） 默认 0

    //    @Column(name = "ZHYID")
    @Column(name = "XCZHR")
    private String commanderId; //指挥员id

//    @Column(name = "ZHYXM")
    @Column(name = "XCZHR_XM")
    private String commanderName; //指挥员名称

//    @Column(name = "ZHYZW")
    @Column(name = "XCZHR_XFGWLBDM")
    private String commanderDuty; //指挥员职务

    @Column(name = "XCZHR_XFGWLBMC")
    private String commanderDutyName; //指挥员职务名称

    @Column(name="JSY",length=200)
    private  String  driver ; //驾驶员

    @Column(name="TXY",length=200)
    private  String   correspondent ; //通讯员

//    @Column(name = "YDDH")
    @Column(name = "XCZHR_LXDH")
    private String mobilePhone;//指挥员联系电话

    @Column(name = "GDDH")
    private String telephone; //指挥员联系 固定电话

    @Column(name = "JGID")
    private String organizationId; //指挥员所在机构id

    @Column(name = "JGMC")
    private String organizationName; //指挥员所在机构名称

    @Column(name = "DCSJ")
    private Long arriveTime;// 指令下达人员到场时间

    @Column(name = "FHSJ")
    private Long backTime;// 指令下达人员返回时间

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(String correspondent) {
        this.correspondent = correspondent;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCommanderType() {
        return commanderType;
    }

    public void setCommanderType(String commanderType) {
        this.commanderType = commanderType;
    }

    public String getCommanderId() {
        return commanderId;
    }

    public void setCommanderId(String commanderId) {
        this.commanderId = commanderId;
    }

    public String getCommanderName() {
        return commanderName;
    }

    public void setCommanderName(String commanderName) {
        this.commanderName = commanderName;
    }

    public String getCommanderDuty() {
        return commanderDuty;
    }

    public void setCommanderDuty(String commanderDuty) {
        this.commanderDuty = commanderDuty;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getCommanderDutyName() {
        return commanderDutyName;
    }

    public void setCommanderDutyName(String commanderDutyName) {
        this.commanderDutyName = commanderDutyName;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Long getBackTime() {
        return backTime;
    }

    public void setBackTime(Long backTime) {
        this.backTime = backTime;
    }
}
