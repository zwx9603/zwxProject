package com.dscomm.iecs.accept.graphql.fireinputbean;

/**
 * 重大警情规则 保存参数
 */
public class IncidentImportantRuleSaveInputInfo {

    private String id; //主键

    private String title ; //模板标题

    private String incidentTypeCode;//案件类型维度

    private String incidentLevelCode;//案件等级维度

    private String disposalObject ; //处置对象

    private Integer trappedEnable ;//人员被困启用标识 0不启用 1 启用
    private Integer trappedNumber;// 人员被困数

    private Integer injuredEnable;// 人员受伤启用标识  0不启用 1 启用
    private Integer injuredNumber ;//受伤人数

    private Integer deathsEnable ;//人员死亡启用标识  0不启用 1 启用
    private Integer deathsNumber ;//死亡人数

    private Integer combatVehicleNum;//出动车辆数量维度

    private String keyword;//关键字维度 逗号分隔

    private Long  overtime ;//超时时间 毫秒为计量单位

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getDisposalObject() {
        return disposalObject;
    }

    public void setDisposalObject(String disposalObject) {
        this.disposalObject = disposalObject;
    }

    public Integer getTrappedEnable() {
        return trappedEnable;
    }

    public void setTrappedEnable(Integer trappedEnable) {
        this.trappedEnable = trappedEnable;
    }

    public Integer getTrappedNumber() {
        return trappedNumber;
    }

    public void setTrappedNumber(Integer trappedNumber) {
        this.trappedNumber = trappedNumber;
    }

    public Integer getInjuredEnable() {
        return injuredEnable;
    }

    public void setInjuredEnable(Integer injuredEnable) {
        this.injuredEnable = injuredEnable;
    }

    public Integer getInjuredNumber() {
        return injuredNumber;
    }

    public void setInjuredNumber(Integer injuredNumber) {
        this.injuredNumber = injuredNumber;
    }

    public Integer getDeathsEnable() {
        return deathsEnable;
    }

    public void setDeathsEnable(Integer deathsEnable) {
        this.deathsEnable = deathsEnable;
    }

    public Integer getDeathsNumber() {
        return deathsNumber;
    }

    public void setDeathsNumber(Integer deathsNumber) {
        this.deathsNumber = deathsNumber;
    }

    public Integer getCombatVehicleNum() {
        return combatVehicleNum;
    }

    public void setCombatVehicleNum(Integer combatVehicleNum) {
        this.combatVehicleNum = combatVehicleNum;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getOvertime() {
        return overtime;
    }

    public void setOvertime(Long overtime) {
        this.overtime = overtime;
    }
}
