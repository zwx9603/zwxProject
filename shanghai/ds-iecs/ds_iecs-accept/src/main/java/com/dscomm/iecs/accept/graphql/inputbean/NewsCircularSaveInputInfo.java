package com.dscomm.iecs.accept.graphql.inputbean;


import java.util.List;

/**
 * 消息通知表
 */

public class NewsCircularSaveInputInfo {

    private String type ; //类型

    private  String  source ; //  来源  1 接处警 2 图上指挥  3 移动 可拓展

    private String title; //标题

    private String content;	//内容

    private String remarks; // 备注

    private List<NewsCircularReceiverSaveInputInfo> newsCircularReceiverSaveInputInfoList ;  //消息接收对象


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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<NewsCircularReceiverSaveInputInfo> getNewsCircularReceiverSaveInputInfoList() {
        return newsCircularReceiverSaveInputInfoList;
    }

    public void setNewsCircularReceiverSaveInputInfoList(List<NewsCircularReceiverSaveInputInfo> newsCircularReceiverSaveInputInfoList) {
        this.newsCircularReceiverSaveInputInfoList = newsCircularReceiverSaveInputInfoList;
    }
}
