package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

import java.util.List;

/**
 * 描述:  消息通知查询参数
 *
 */
public class NewsCircularQueryInputInfo {

    private  Long   startTime ; //查询开始时间

    private  Long   endTime ;  //查询结束时间

    private List<String> type;//  类型

    private String receivingObjectId ; // 接收对象id

    private List<Integer>   newsCircularStatus ; //接收对象状态集合  状态 0已通知 1已接收

    private List<String> source ; // 指令来源  1 接处警 2 图上指挥  3 移动 可拓展

    private String keyword ; // 关键字查询   标题/ 内容  模糊

    private String titleKeyword ; // 关键字查询   标题模糊

    private String contentKeyword ; // 关键字查询   内容模糊

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件


    public String getReceivingObjectId() {
        return receivingObjectId;
    }

    public void setReceivingObjectId(String receivingObjectId) {
        this.receivingObjectId = receivingObjectId;
    }

    public List<Integer> getNewsCircularStatus() {
        return newsCircularStatus;
    }

    public void setNewsCircularStatus(List<Integer> newsCircularStatus) {
        this.newsCircularStatus = newsCircularStatus;
    }

    public String getTitleKeyword() {
        return titleKeyword;
    }

    public void setTitleKeyword(String titleKeyword) {
        this.titleKeyword = titleKeyword;
    }

    public String getContentKeyword() {
        return contentKeyword;
    }

    public void setContentKeyword(String contentKeyword) {
        this.contentKeyword = contentKeyword;
    }

    public Boolean getWhetherPage() {
        return whetherPage;
    }

    public void setWhetherPage(Boolean whetherPage) {
        this.whetherPage = whetherPage;
    }

    public PaginationInputInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInputInfo pagination) {
        this.pagination = pagination;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
