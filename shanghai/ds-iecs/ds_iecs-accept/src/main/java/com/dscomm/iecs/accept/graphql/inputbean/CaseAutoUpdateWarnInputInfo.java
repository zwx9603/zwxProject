package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 案件等级自动升级提醒
 */

public class CaseAutoUpdateWarnInputInfo {
    /**
     * 主键 id
     */
    private String id;

    /**
     * 案件类型
     */
    private String caseType;
    /**
     * 提醒类型
     * BKRS（被困人数），RSMJ（燃烧面积），RSDX（燃烧对象），ZHCS（火灾场所），ZDDW（重点单位），YJJQ（夜间警情）
     */
    private String warnType;
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
     * 案件等级
     */
    private String incidentLevelCode;
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

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    @Override
    public String toString() {
        return "CaseAutoUpdateWarnInputInfo{" +
                "id='" + id + '\'' +
                ", caseType='" + caseType + '\'' +
                ", warnType='" + warnType + '\'' +
                ", minimum='" + minimum + '\'' +
                ", maximum='" + maximum + '\'' +
                ", disposalObjectCode='" + disposalObjectCode + '\'' +
                ", incidentLevelCode='" + incidentLevelCode + '\'' +
                '}';
    }
}
