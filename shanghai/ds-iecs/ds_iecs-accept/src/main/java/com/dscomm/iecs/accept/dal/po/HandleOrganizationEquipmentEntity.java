package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 调派单位装备信息
 *
 */
@Entity
@Table(name = "JCJ_DPQC")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandleOrganizationEquipmentEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; //案件ID

    @Column(name = "CJJLID", length = 100)
    private String handleId;// 处警记录ID

    @Column(name = "CJXXID", length = 100)
    private String handlePoliceId; // 处警信息ID

    @Column(name = "XFJGBH", length = 100)
    private String organizationId; // 消防机构编号

    @Column(name = "XFZB", length = 100)
    private String equipment; //todo 字段  装备

    @Column(name = "XFZB_TYWYSBM", length = 100)
    private String equipmentId; //todo 字段  装备 通用唯一识别码

    @Column(name = "XFZB_MC", length = 200)
    private String equipmentName; //todo 字段  装备名称

//    @Column(name = "ZBLXDM", length = 100)
    @Column(name = "XFZB_XFZBLXDM", length = 100)
    private String equipmentTypeCode; //todo 字段  装备类型代码

    @Column(name = "XFZB_KSSJ")
    private Long dispatchStartTime;//todo 字段  调派开始时间

    @Column(name = "XFZB_JSSJ")
    private Long dispatchEndTime;//todo 字段  调派结束时间

    @Column(name = "DPSL")
    private Integer dispatchNum;// 调派数量

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getHandlePoliceId() {
        return handlePoliceId;
    }

    public void setHandlePoliceId(String handlePoliceId) {
        this.handlePoliceId = handlePoliceId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public Integer getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(Integer dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Long getDispatchStartTime() {
        return dispatchStartTime;
    }

    public void setDispatchStartTime(Long dispatchStartTime) {
        this.dispatchStartTime = dispatchStartTime;
    }

    public Long getDispatchEndTime() {
        return dispatchEndTime;
    }

    public void setDispatchEndTime(Long dispatchEndTime) {
        this.dispatchEndTime = dispatchEndTime;
    }
}
