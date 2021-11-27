package com.dscomm.iecs.base.graphql.typebean;


import java.util.List;

/**
 * 描述： notify 推送消息格式
 */
public class NotifyMessageBean {


    private  Integer filterType = 0 ; //过滤类型 默认全部在线

    private List<String> filterKeys ; //过滤条件

    private String code ; //信息编码

    private String body ; //信息内容

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public List<String> getFilterKeys() {
        return filterKeys;
    }

    public void setFilterKeys(List<String> filterKeys) {
        this.filterKeys = filterKeys;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
