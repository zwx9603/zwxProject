package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 案件等级自动升级提醒 查询接口
 */

public class CaseAutoUpdateWarnQueryInputInfo {

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
     * 案件等级
     */
    private String incidentLevelCode;

    /**
     * 关键字 模糊匹配 最小值 最大值  对象字典名称 描述
     */
    private String keyword ;


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

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
