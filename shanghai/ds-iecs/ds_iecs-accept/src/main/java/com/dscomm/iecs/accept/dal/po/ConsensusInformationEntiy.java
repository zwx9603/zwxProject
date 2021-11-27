package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：舆情信息
 *
 */
@Entity
@Table(name = "T_COC_FIRE_YQXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ConsensusInformationEntiy extends BaseEntity {

    @Column(name = "YQBT", length = 200  )
    private String title;//舆情标题

    @Column(name = "YQNR", length = 4000  )
    private String content;//舆情内容
    @Column(name = "FBSJ")
    private Long publishedTime;//发布时间

    @Column(name = "FBDW", length = 200)
    private String publishedUnit;//发布单位名称

    @Column(name = "FBR", length = 100)
    private String publisher;//发布人名称

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

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

