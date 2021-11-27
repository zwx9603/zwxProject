package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 等级调度
 *
 */
@Entity
@Table(name = "JCJ_DJDD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HierarchicalDispatchEntity extends BaseEntity {

    @Column(name = "XFJG", length = 100)
    private String organizationId; // 消防机构编号

    @Column(name = "MC", length = 100)
    private String title; // 等级调派名称

    @Column(name = "AJLX", length = 100)
    private String incidentTypeCode;// 案件类型代码

    @Column(name = "CZDX", length = 100)
    private String disposalObjectCode;// 处置对象代码

    @Column(name = "AJDJ", length = 100)
    private String incidentLevelCode;// 案件等级代码

    @Column(name = "CLLX", length = 4000)
    private String vehicleTypeCode;// 车辆类型代码  多个逗号分隔

    @Column(name = "SL", length = 2000)
    private String dispatchNum;// 调派数量 与车辆类型对应 多个逗号分隔

    @Column(name = "ZZSJ" )
    private Long makeTime  ; //制作时间

    @Column(name = "ZZRID" )
    private String makePersonId ;  //制作人id

    @Column(name = "ZZRXM" )
    private String makePersonName ; //制作人姓名


    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注


    public Long getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Long makeTime) {
        this.makeTime = makeTime;
    }

    public String getMakePersonId() {
        return makePersonId;
    }

    public void setMakePersonId(String makePersonId) {
        this.makePersonId = makePersonId;
    }

    public String getMakePersonName() {
        return makePersonName;
    }

    public void setMakePersonName(String makePersonName) {
        this.makePersonName = makePersonName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(String dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
