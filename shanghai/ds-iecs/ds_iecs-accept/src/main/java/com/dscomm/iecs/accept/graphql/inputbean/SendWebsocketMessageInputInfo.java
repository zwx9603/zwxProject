package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述:发送websocket参数
 *
 * @author YangFuxi
 * Date Time 2021/8/5 9:36
 */
public class SendWebsocketMessageInputInfo {
    private String code="HJSL.MSG";//消息号，如果不传，默认为HJSL.MSG
    private String system="HJSL";//系统号,要发给哪个系统，消防接处警为HJSL
    private Object msg;//消息内容，前端可以自定义对象格式
    private List<String> receivers;//消息接收者，可以是单位id，用户账号
    private String receiverType="user";//消息接收者类型,只可以填 user 代表发给用户，businessOrg.role 发送给业务单位下的所有人，如果为空，则默认为user

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }
}
