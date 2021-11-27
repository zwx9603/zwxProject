package com.dscomm.iecs.garage.restful.vo;

/**
 * 描述： 信息 通知数据对象
 *
 */
public class NotifyMessageVO {


    private String code ; //信息编码

    private String body ; //信息内容

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
