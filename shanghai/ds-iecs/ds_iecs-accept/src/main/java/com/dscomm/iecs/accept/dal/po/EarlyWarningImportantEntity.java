package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 预警信息实体 （重要信息预警 ）
 *
 */

@Entity
@Table(name = "T_COC_FIRE_YJXX")
@DynamicUpdate(true)
@DynamicInsert(true)
public class EarlyWarningImportantEntity extends BaseEntity {

    @Column(name = "LX", length = 100)
    private String type;           //预警类型

    @Column(name = "BT", length = 200)
    private String title;           //预警标题

    @Column(name = "NR", length = 4000)
    private String content;         //预警内容

    @Column(name = "FBSJ")
    private Long publishTime;     //发布时间

    @Column(name = "YXSJ")
    private String effectiveTime;         //有效时间

    @Column(name = "FBRID", length = 100)
    private String publisherId;       //发布人id

    @Column(name = "FBRXM", length = 100)
    private String publisher;       //发布人姓名

    @Column(name = "FBDWID", length =50)
    private String publishUnitId  ;    //发布单位id

    @Column(name = "FBDWMC", length = 200)
    private String publishUnit;     //发布单位名称

    @Column(name = "BZ", length = 500)
    private String remarks;         //备注


    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
