package com.dscomm.iecs.accept.graphql.fireinputbean;

public class ConsensusInformationInputInfo {
    private String id; //主键

    private String title;//舆情标题

    private String content;//舆情内容

    private Long publishedTime;//发布时间

    private String publishedUnit;//发布单位名称

    private String publisher;//发布人名称

    private String remarks;//备注

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Long publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getPublishedUnit() {
        return publishedUnit;
    }

    public void setPublishedUnit(String publishedUnit) {
        this.publishedUnit = publishedUnit;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
