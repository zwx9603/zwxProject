package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * @Author: zs
 * @Date: 10:52 2020/7/23
 * desc: 常用短语
 */
public class  CommonPhraseSaveInputInfo {

    private String id ; // 主键

    private String phraseType;		//短语类型

    private String incidentType;	//事件类型

    private String phraseTitle;		//短语标题

    private String phraseContent;	//短语内容

    private String organizationId;//所属单位ID

    private String remarks;			//备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhraseType() {
        return phraseType;
    }

    public void setPhraseType(String phraseType) {
        this.phraseType = phraseType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPhraseContent() {
        return phraseContent;
    }

    public void setPhraseContent(String phraseContent) {
        this.phraseContent = phraseContent;
    }

    public String getPhraseTitle() {
        return phraseTitle;
    }

    public void setPhraseTitle(String phraseTitle) {
        this.phraseTitle = phraseTitle;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }
}
