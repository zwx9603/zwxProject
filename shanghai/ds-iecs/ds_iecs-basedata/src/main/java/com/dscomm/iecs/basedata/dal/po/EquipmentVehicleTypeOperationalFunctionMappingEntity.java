package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:车辆类型与作战功能关系
 */
@Entity
@Table(name = "ZBZB_ZZGN")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EquipmentVehicleTypeOperationalFunctionMappingEntity extends BaseEntity {

    @Column(name = "XFZBLXDM", length = 100)
    private String vehicleTypeCode; //todo 字段  车辆类型代码

    @Column(name = "XFCLZZGNDM", length = 100)
    private String operationalFunctionCode; //todo 字段 消防车辆作战功能代码

    @Column(name = "ZZGNJB")
    private Integer operationalFunctionLevel; //作战功能级别

    @Column(name = "XFJGDM", length = 100)
    private String organizationId; //消防机构代码

    @Column(name = "BZ", length = 400)
    private String remarks; //备注

    @Column(name = "JLZT")
    private Integer JLZT; //记录状态

    @Column(name = "CSZT")
    private Integer CSZT; //传输状态

    @Column(name = "SJC")
    private Long SJC; //时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB; //数据版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID; //业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB; //基库数据版本

    public String getOperationalFunctionCode() {
        return operationalFunctionCode;
    }

    public void setOperationalFunctionCode(String operationalFunctionCode) {
        this.operationalFunctionCode = operationalFunctionCode;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public Integer getOperationalFunctionLevel() {
        return operationalFunctionLevel;
    }

    public void setOperationalFunctionLevel(Integer operationalFunctionLevel) {
        this.operationalFunctionLevel = operationalFunctionLevel;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
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