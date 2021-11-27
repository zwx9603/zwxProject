package com.dscomm.iecs.accept.service.bean.cad;

/**
 * 描述:122反馈扩展表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/26 16:13
 */
public class FeedbackExtend122BO {
    /**
     * 反馈单编号
     */
    private String id;
    /**
     * 出警人type（1：单位，2：unit，3：人员）
     */
    private String responsePoliceType;
    /**
     * 出警人（存的id）
     */
    private String responsePolice;
    /**
     * 出警人name（如果是unit就是unit name）
     */
    private String responsePersonName;
    /**
     * 出动时间
     */
    private Long startTime;
    /**
     * 到场时间
     */
    private Long arriveTime;
    /**
     * 事发地址分类
     */
    private String incidentAddressType;
    /**
     * 事发地址分类name
     */
    private String incidentAddressTypeName;
    /**
     * 一级事发地址分类
     */
    private String parentIncidentAddressType;
    /**
     * 一级地址分类name
     */
    private String parentIncidentAddressTypeName;
    /**
     * 出动车次
     */
    private Integer numberOfDispatchVehicle;
    /**
     * 出动人次
     */
    private Integer numberOfDispatchPeople;
    /**
     * 轻伤人数
     */
    private Integer minorInjuriesNumber;
    /**
     * 重伤人数
     */
    private Integer seriousInjuryNumber;
    /**
     * 死亡人数
     */
    private Integer deathNumber;
    /**
     * 时间戳
     */
    private Long lastedhandleTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponsePoliceType() {
        return responsePoliceType;
    }

    public void setResponsePoliceType(String responsePoliceType) {
        this.responsePoliceType = responsePoliceType;
    }

    public String getResponsePolice() {
        return responsePolice;
    }

    public void setResponsePolice(String responsePolice) {
        this.responsePolice = responsePolice;
    }

    public String getResponsePersonName() {
        return responsePersonName;
    }

    public void setResponsePersonName(String responsePersonName) {
        this.responsePersonName = responsePersonName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getIncidentAddressType() {
        return incidentAddressType;
    }

    public void setIncidentAddressType(String incidentAddressType) {
        this.incidentAddressType = incidentAddressType;
    }

    public String getIncidentAddressTypeName() {
        return incidentAddressTypeName;
    }

    public void setIncidentAddressTypeName(String incidentAddressTypeName) {
        this.incidentAddressTypeName = incidentAddressTypeName;
    }

    public String getParentIncidentAddressType() {
        return parentIncidentAddressType;
    }

    public void setParentIncidentAddressType(String parentIncidentAddressType) {
        this.parentIncidentAddressType = parentIncidentAddressType;
    }

    public String getParentIncidentAddressTypeName() {
        return parentIncidentAddressTypeName;
    }

    public void setParentIncidentAddressTypeName(String parentIncidentAddressTypeName) {
        this.parentIncidentAddressTypeName = parentIncidentAddressTypeName;
    }

    public Integer getNumberOfDispatchVehicle() {
        return numberOfDispatchVehicle;
    }

    public void setNumberOfDispatchVehicle(Integer numberOfDispatchVehicle) {
        this.numberOfDispatchVehicle = numberOfDispatchVehicle;
    }

    public Integer getNumberOfDispatchPeople() {
        return numberOfDispatchPeople;
    }

    public void setNumberOfDispatchPeople(Integer numberOfDispatchPeople) {
        this.numberOfDispatchPeople = numberOfDispatchPeople;
    }

    public Integer getMinorInjuriesNumber() {
        return minorInjuriesNumber;
    }

    public void setMinorInjuriesNumber(Integer minorInjuriesNumber) {
        this.minorInjuriesNumber = minorInjuriesNumber;
    }

    public Integer getSeriousInjuryNumber() {
        return seriousInjuryNumber;
    }

    public void setSeriousInjuryNumber(Integer seriousInjuryNumber) {
        this.seriousInjuryNumber = seriousInjuryNumber;
    }

    public Integer getDeathNumber() {
        return deathNumber;
    }

    public void setDeathNumber(Integer deathNumber) {
        this.deathNumber = deathNumber;
    }

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }
}
