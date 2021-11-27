package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 下达安全提示
 * @Author xyy
 * @date 9:25 2020/8/4
 * */
public class SecurityBean extends BaseBean  {

    private String incidentId;//事件ID

    private String commandId;//指挥ID

    private String title;//标题

    private String content;//提示内容

    private Long notificationTime ;//通知时间

    private Long effectiveTime;//有效时间

    private String senderName;//发送人姓名

    private String writerId;//填写人ID

    private String remarks;			//备注

    private List<SecurityReceiverBean> receivers=new ArrayList<>(); //受令对象列表

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

    public Long getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Long notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<SecurityReceiverBean> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<SecurityReceiverBean> receivers) {
        this.receivers = receivers;
    }


}
