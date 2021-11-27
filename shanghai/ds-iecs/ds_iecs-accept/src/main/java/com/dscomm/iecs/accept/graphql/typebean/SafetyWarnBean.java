package com.dscomm.iecs.accept.graphql.typebean;

public class SafetyWarnBean {
    private String id;
    /**
     * 提示内容
     */
    private String hintContent;
    /**
     * 案件类型代码
     */
    private String incidentTypeCode;
    /**
     * 案件类型名称
     */
    private String incidentTypeName;
    /**
     * 处置对象代码
     */
    private String disposalObjectCode;
    /**
     * 处置对象名称
     */
    private String disposalObjectName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHintContent() {
        return hintContent;
    }

    public void setHintContent(String hintContent) {
        this.hintContent = hintContent;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getDisposalObjectName() {
        return disposalObjectName;
    }

    public void setDisposalObjectName(String disposalObjectName) {
        this.disposalObjectName = disposalObjectName;
    }
}
