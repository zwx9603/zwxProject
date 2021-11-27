package com.dscomm.iecs.accept.graphql.fireinputbean;

import java.util.List;

/**
 * 重大警情规则 保存参数
 */
public class IncidentImportantSaveInputInfo {

    private String id; //主键

    private Integer combatVehicleNum;//出动车辆数量维度

    private Integer deathsNumber ;//死亡人数

    private Integer deathsState ;//死亡人数标识

    private String disposalObject;// 处置对象维度

    private String incidentLevelCode;//案件等级维度

    private String incidentTypeCode;//案件类型维度

    private Integer injuredNumber ;//受伤人数

    private Integer injuredState;// 受伤标识

    private List<String> keyword ; //规则关键字

    private Long  overtime ;//超时时间 毫秒为计量单位

    private String title;// 模板标题

    private Integer trappedCount;// 人员被困数

    private Integer trappedState ;//人员被困标识

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCombatVehicleNum() {
        return combatVehicleNum;
    }

    public void setCombatVehicleNum(Integer combatVehicleNum) {
        this.combatVehicleNum = combatVehicleNum;
    }

    public Integer getDeathsNumber() {
        return deathsNumber;
    }

    public void setDeathsNumber(Integer deathsNumber) {
        this.deathsNumber = deathsNumber;
    }

    public Integer getDeathsState() {
        return deathsState;
    }

    public void setDeathsState(Integer deathsState) {
        this.deathsState = deathsState;
    }

    public String getDisposalObject() {
        return disposalObject;
    }

    public void setDisposalObject(String disposalObject) {
        this.disposalObject = disposalObject;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public Integer getInjuredNumber() {
        return injuredNumber;
    }

    public void setInjuredNumber(Integer injuredNumber) {
        this.injuredNumber = injuredNumber;
    }

    public Integer getInjuredState() {
        return injuredState;
    }

    public void setInjuredState(Integer injuredState) {
        this.injuredState = injuredState;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public Long getOvertime() {
        return overtime;
    }

    public void setOvertime(Long overtime) {
        this.overtime = overtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTrappedCount() {
        return trappedCount;
    }

    public void setTrappedCount(Integer trappedCount) {
        this.trappedCount = trappedCount;
    }

    public Integer getTrappedState() {
        return trappedState;
    }

    public void setTrappedState(Integer trappedState) {
        this.trappedState = trappedState;
    }
}
