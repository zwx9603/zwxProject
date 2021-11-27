package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 常用短语
 */
@Entity
@Table(name="JCJ_CYDY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CommonPhraseEntity extends BaseEntity {

    @Column(name = "DYLX",length=100)
    private String phraseType;		//短语类型 AQTS安全提示

    @Column(name = "SJLX",length=100)
    private String incidentType;	//事件类型

    @Column(name = "BT",length=800)
    private String phraseTitle;		//短语标题

    @Column(name = "NR",length=4000)
    private String phraseContent;	//短语内容

    @Column(name = "SSDWID",length = 100)
    private String organizationId;//所属单位ID

    @Column(name = "BZ",length=800)
    private String remarks;			//备注


    public String getPhraseType() {
        return phraseType;
    }

    public void setPhraseType(String phraseType) {
        this.phraseType = phraseType;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getPhraseTitle() {
        return phraseTitle;
    }

    public void setPhraseTitle(String phraseTitle) {
        this.phraseTitle = phraseTitle;
    }

    public String getPhraseContent() {
        return phraseContent;
    }

    public void setPhraseContent(String phraseContent) {
        this.phraseContent = phraseContent;
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
}
