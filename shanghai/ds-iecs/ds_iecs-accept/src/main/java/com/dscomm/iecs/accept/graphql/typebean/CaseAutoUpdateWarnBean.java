package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 案件等级自动升级提醒
 */

public class CaseAutoUpdateWarnBean  {

    private String id;
    /**
     * 案件类型
     */
    private String caseType;
    /**
     * 案件类型名称
     */
    private String caseTypeName;
    /**
     * 提醒类型
     * BKRS（被困人数），RSMJ（燃烧面积），RSDX（燃烧对象），ZHCS（火灾场所），ZDDW（重点单位），YJJQ（夜间警情）
     */
    private String warnType;
    /**
     * 提醒类型名称
     * BKRS（被困人数），RSMJ（燃烧面积），RSDX（燃烧对象），ZHCS（火灾场所），ZDDW（重点单位），YJJQ（夜间警情）
     */
    private String warnTypeName;
    /**
     * 最小值
     */
    private String minimum;
    /**
     * 最大值
     */
    private String maximum;
    /**
     * 对象字典值
     */
    private String disposalObjectCode;
    /**
     * 对象字典值
     */
    private String disposalObjectCodeName;
    /**
     * 案件等级
     */
    private String incidentLevelCode;
    /**
     * 案件等级名称
     */
    private String incidentLevelCodeName;

    /**
     * 创建时间
     */
    private Long createdTime  ;

    /**
     * 更新时间
     */
    private Long updatedTime  ;

    /**
     * 描述
     */
    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }

    public String getWarnTypeName() {
        return warnTypeName;
    }

    public void setWarnTypeName(String warnTypeName) {
        this.warnTypeName = warnTypeName;
    }



    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }



    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public String getIncidentLevelCodeName() {
        return incidentLevelCodeName;
    }

    public void setIncidentLevelCodeName(String incidentLevelCodeName) {
        this.incidentLevelCodeName = incidentLevelCodeName;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getDisposalObjectCodeName() {
        return disposalObjectCodeName;
    }

    public void setDisposalObjectCodeName(String disposalObjectCodeName) {
        this.disposalObjectCodeName = disposalObjectCodeName;
    }
}
