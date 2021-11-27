package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:预案车辆调集 （预案调派）
 */
@Entity
@Table(name = "YAGL_CLDJ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PlanDispatchEntity extends BaseEntity {

    @Column(name = "YAID", length = 100)
    private String planId; //预案ID

    @Column(name = "SSDWID", length = 100)
    private String subordinateUnitId; //所属单位ID

    @Column(name = "CLID", length = 100)
    private String vehicleId; //车辆ID

    @Column(name = "CDZBDM", length = 100)
    private String dispatchGroup; //出动组别

    @Column(name = "CPHM", length = 100)
    private String vehicleNumber; //车牌号码

    @Column(name = "CLLXDM", length = 100)
    private String vehicleTypeCode; //车辆类型代码

    @Column(name = "CLDJDM", length = 100)
    private String vehicleLevelCode; //车辆等级代码

    @Column(name = "ZZGNDM", length = 100)
    private String operationalFunctionCode; //作战功能代码

    @Column(name = "CDSX")
    private Integer dispatchOrderNum; //出动顺序

    @Column(name = "DCSJ")
    private Long attendanceTime; //到场时间

    @Column(name = "CZYID", length = 100)
    private String operatorId; //数据操作人编号

    @Column(name = "CZYXM", length = 200)
    private String operatorName; //数据操作人姓名

    @Column(name = "JGID", length = 100)
    private String organizationId; //机构ID

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
    private String YWXTBSID; //业务系统部署

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB; //基库数据版本

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getSubordinateUnitId() {
        return subordinateUnitId;
    }

    public void setSubordinateUnitId(String subordinateUnitId) {
        this.subordinateUnitId = subordinateUnitId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDispatchGroup() {
        return dispatchGroup;
    }

    public void setDispatchGroup(String dispatchGroup) {
        this.dispatchGroup = dispatchGroup;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleLevelCode() {
        return vehicleLevelCode;
    }

    public void setVehicleLevelCode(String vehicleLevelCode) {
        this.vehicleLevelCode = vehicleLevelCode;
    }

    public String getOperationalFunctionCode() {
        return operationalFunctionCode;
    }

    public void setOperationalFunctionCode(String operationalFunctionCode) {
        this.operationalFunctionCode = operationalFunctionCode;
    }

    public Integer getDispatchOrderNum() {
        return dispatchOrderNum;
    }

    public void setDispatchOrderNum(Integer dispatchOrderNum) {
        this.dispatchOrderNum = dispatchOrderNum;
    }

    public Long getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Long attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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