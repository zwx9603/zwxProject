package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 消息通知 记录表
 */
@Entity
@Table(name = "JCJ_XXTZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class NewsCircularEntity extends BaseEntity  {

    @Column(name = "LX",length=100 )
    private String type ; //类型

    @Column(name = "BT",length=800)
    private String title;		//标题

    @Column(name = "NR",length=4000)
    private String content;	//内容

    @Column(name = "LY",length=100)
    private  String  source ; //  来源  1 接处警 2 图上指挥  3 移动 可拓展

    @Column(name = "TZSJ" )
    private Long circularTime ;	//通知时间

    @Column(name = "TZRID",length=100)
    private String circularPersonId ;	//通知下达者id

    @Column(name = "TZRXM" ,length=100)
    private String circularPersonName ;	//通知下达者姓名

    @Column(name = "TXDWID",length=100)
    private String circularOrganizationId  ;	//通知下达单位id

    @Column(name = "BZ", length = 800)
    private String remarks; // 备注



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Long getCircularTime() {
        return circularTime;
    }

    public void setCircularTime(Long circularTime) {
        this.circularTime = circularTime;
    }

    public String getCircularPersonId() {
        return circularPersonId;
    }

    public void setCircularPersonId(String circularPersonId) {
        this.circularPersonId = circularPersonId;
    }

    public String getCircularPersonName() {
        return circularPersonName;
    }

    public void setCircularPersonName(String circularPersonName) {
        this.circularPersonName = circularPersonName;
    }

    public String getCircularOrganizationId() {
        return circularOrganizationId;
    }

    public void setCircularOrganizationId(String circularOrganizationId) {
        this.circularOrganizationId = circularOrganizationId;
    }




}
