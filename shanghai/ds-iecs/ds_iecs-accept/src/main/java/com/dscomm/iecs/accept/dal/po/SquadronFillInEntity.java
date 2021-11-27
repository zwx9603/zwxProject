package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 描述:中队填报信息
 *
 */
@Entity
@Table(name = "JCJ_ZDTB")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SquadronFillInEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId;  //案件ID

    @Column(name = "TXJGBH", length = 100)
    private String writeOrganizationId; //填写机构编号

    @Column(name = "CDSQS"  )
    private Integer dispatchWaterGunNumber;//出动水枪数

    @Column(name = "SWRS"  )
    private Integer deathNumber;//死亡人数

    @Column(name = "SSRS" )
    private Integer injuredNumber;//受伤人数

    @Column(name = "QJRS"  )
    private Integer rescueNumber;//抢救人数

    @Column(name = "WSSJ")
    private Long writeTime;//填写时间

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public Long getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Long writeTime) {
        this.writeTime = writeTime;
    }

    public String getWriteOrganizationId() {
        return writeOrganizationId;
    }

    public void setWriteOrganizationId(String writeOrganizationId) {
        this.writeOrganizationId = writeOrganizationId;
    }

    public Integer getDispatchWaterGunNumber() {
        return dispatchWaterGunNumber;
    }

    public void setDispatchWaterGunNumber(Integer dispatchWaterGunNumber) {
        this.dispatchWaterGunNumber = dispatchWaterGunNumber;
    }

    public Integer getInjuredNumber() {
        return injuredNumber;
    }

    public void setInjuredNumber(Integer injuredNumber) {
        this.injuredNumber = injuredNumber;
    }

    public Integer getDeathNumber() {
        return deathNumber;
    }

    public void setDeathNumber(Integer deathNumber) {
        this.deathNumber = deathNumber;
    }

    public Integer getRescueNumber() {
        return rescueNumber;
    }

    public void setRescueNumber(Integer rescueNumber) {
        this.rescueNumber = rescueNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
