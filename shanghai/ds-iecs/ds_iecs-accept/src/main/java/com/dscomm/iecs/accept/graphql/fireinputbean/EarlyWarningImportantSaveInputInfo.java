package com.dscomm.iecs.accept.graphql.fireinputbean;

public class EarlyWarningImportantSaveInputInfo {

    private String id; //主键

    private String type;           //预警类型

    private String title;           //预警标题

    private String content;         //预警内容

    private Long publishTime;     //发布时间

    private String effectiveTime;         //有效时间

    private String publisherId;       //发布人id

    private String publisher;       //发布人姓名

    private String publishUnitId;     //发布单位id

    private String publishUnit;     //发布单位名称

    private String remarks;         //备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublishUnitId() {
        return publishUnitId;
    }

    public void setPublishUnitId(String publishUnitId) {
        this.publishUnitId = publishUnitId;
    }

    public String getPublishUnit() {
        return publishUnit;
    }

    public void setPublishUnit(String publishUnit) {
        this.publishUnit = publishUnit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
