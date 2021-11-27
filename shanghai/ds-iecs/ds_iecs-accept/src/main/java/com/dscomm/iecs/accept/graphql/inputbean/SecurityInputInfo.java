package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 特别警示输入信息
 * @Author xyy
 * @Date 14:30 2020/8/3
 * */
public class SecurityInputInfo {

    private String incidentId;// 事件ID

    private String commandId;// 指挥ID

    private String title;//标题

    private String content;//提示内容

    private Long effectiveTime;// 有效时间

    private String senderName;// 发送人姓名

    private String writerId;// 填写人ID

    private String remark;//备注

    private List<SecurityReceiverInputInfo> receivers;// 接收者

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SecurityReceiverInputInfo> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<SecurityReceiverInputInfo> receivers) {
        this.receivers = receivers;
    }

}
