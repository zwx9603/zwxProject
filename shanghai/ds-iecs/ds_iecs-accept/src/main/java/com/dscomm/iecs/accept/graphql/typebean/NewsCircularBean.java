package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 消息通知 记录表
 */

public class NewsCircularBean extends BaseBean {

    private String type ; //类型

    private  String  source ; //  来源  1 接处警 2 图上指挥  3 移动 可拓展

    private String title; //标题

    private String content;	//内容

    private Long circularTime ;	//通知时间

    private String circularPersonId ;	//通知下达者id

    private String circularPersonName ;	//通知下达者姓名

    private String circularOrganizationId  ;	//通知下达单位id

    private String circularOrganizationName  ;	//通知下达单位名称

    private String remarks; // 备注

    private List<NewsCircularReceiverBean> newsCircularReceiverBeanList ; //消息接收对象

    public String getCircularOrganizationName() {
        return circularOrganizationName;
    }

    public void setCircularOrganizationName(String circularOrganizationName) {
        this.circularOrganizationName = circularOrganizationName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<NewsCircularReceiverBean> getNewsCircularReceiverBeanList() {
        return newsCircularReceiverBeanList;
    }

    public void setNewsCircularReceiverBeanList(List<NewsCircularReceiverBean> newsCircularReceiverBeanList) {
        this.newsCircularReceiverBeanList = newsCircularReceiverBeanList;
    }
}
