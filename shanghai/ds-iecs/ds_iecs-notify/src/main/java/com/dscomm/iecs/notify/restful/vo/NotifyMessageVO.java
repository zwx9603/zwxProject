package com.dscomm.iecs.notify.restful.vo;

import java.util.List;

/**
 * 描述： 信息 通知数据对象
 *
 */
public class NotifyMessageVO {

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
