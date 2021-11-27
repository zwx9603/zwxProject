package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 安全提醒
 */

public class SafetyWarnInputInfo  {

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
     * 处置对象代码
     */

    private String  disposalObjectCode;

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

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }
}
