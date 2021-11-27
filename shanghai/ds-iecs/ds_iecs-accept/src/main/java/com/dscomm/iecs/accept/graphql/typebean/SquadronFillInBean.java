package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;


/**
 * 描述:中队填报信息
 *
 */
public class SquadronFillInBean extends BaseBean {

    private String incidentId;  //案件ID

    private String writeOrganizationId; //填写机构编号

    private String writeOrganizationName; //填写机构名称

    private Integer dispatchWaterGunNumber;//出动水枪数

    private Integer deathNumber;//死亡人数

    private Integer injuredNumber;//受伤人数

    private Integer rescueNumber;//抢救人数

    private Long writeTime;//填写时间

    private String remarks; // 备注


    public String getWriteOrganizationName() {
        return writeOrganizationName;
    }

    public void setWriteOrganizationName(String writeOrganizationName) {
        this.writeOrganizationName = writeOrganizationName;
    }

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
