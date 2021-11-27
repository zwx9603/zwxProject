package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 消防机构 非消防机构车辆
 *
 */
@Entity
@Table(name = "ZBGL_FXFJGCL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OrganizationLocalFullTimeUnitEntity extends BaseEntity {

    @Column(name = "JGID", length = 100)
    private String organizationId;// 所属消防机构ID

    @Column(name = "JGMC", length = 200)
    private String organizationName;// 所属消防机构名称

    @Column(name = "ZBFLBM", length = 100)
    private String equipmentSortCode;// 装备分类编码

    @Column(name = "ZBFLMC", length = 200)
    private String equipmentSortName;// 装备分类名称

    @Column(name = "CLDJ", length = 100)
    private String vehicleLevel;// 车辆等级

    @Column(name = "GGXH", length = 100)
    private String specificationsNumber;// 规格型号

    @Column(name = "CPH", length = 50)
    private String vehicleNumber;// 车牌号

    @Column(name = "SFYX", length = 10)
    private String whetherValid;// 是否有效

    @Column(name = "ZBZT", length = 100)
    private String equipmentStatus;// 装备状态

    @Column(name = "PBSJ" )
    private Long equipmentTime;// 配备时间

    @Column(name = "JJ", length = 1000 )
    private String synopsis;// 简介

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本


    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }


    public String getEquipmentSortCode() {
        return equipmentSortCode;
    }

    public void setEquipmentSortCode(String equipmentSortCode) {
        this.equipmentSortCode = equipmentSortCode;
    }

    public String getEquipmentSortName() {
        return equipmentSortName;
    }

    public void setEquipmentSortName(String equipmentSortName) {
        this.equipmentSortName = equipmentSortName;
    }

    public String getVehicleLevel() {
        return vehicleLevel;
    }

    public void setVehicleLevel(String vehicleLevel) {
        this.vehicleLevel = vehicleLevel;
    }

    public String getSpecificationsNumber() {
        return specificationsNumber;
    }

    public void setSpecificationsNumber(String specificationsNumber) {
        this.specificationsNumber = specificationsNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getWhetherValid() {
        return whetherValid;
    }

    public void setWhetherValid(String whetherValid) {
        this.whetherValid = whetherValid;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public Long getEquipmentTime() {
        return equipmentTime;
    }

    public void setEquipmentTime(Long equipmentTime) {
        this.equipmentTime = equipmentTime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}
