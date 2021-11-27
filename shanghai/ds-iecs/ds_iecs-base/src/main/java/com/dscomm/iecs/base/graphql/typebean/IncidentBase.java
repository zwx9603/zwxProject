package com.dscomm.iecs.base.graphql.typebean;

import java.io.Serializable;

/**
 * 描述:案件基础敏感信息类，用于websocket打印日志时脱敏
 *
 * @author YangFuxi
 * Date Time 2020/2/13 21:29
 */
public class IncidentBase implements Serializable {
    /**
     * 事发地址
     */
    private String address;
    /**
     * 报警人
     */
    private String alarmPersonName;
    /**
     * 报警人联系电话
     */
    private String alarmPersonContact;
    /**
     * 报警人联系地址
     */
    private String alarmPersonAddress;
    /**
     * 事件详情
     */
    private String content;
    /**
     * 报警人证件号
     */
    private String alarmPersonIdentityNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlarmPersonName() {
        return alarmPersonName;
    }

    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName;
    }

    public String getAlarmPersonContact() {
        return alarmPersonContact;
    }

    public void setAlarmPersonContact(String alarmPersonContact) {
        this.alarmPersonContact = alarmPersonContact;
    }

    public String getAlarmPersonAddress() {
        return alarmPersonAddress;
    }

    public void setAlarmPersonAddress(String alarmPersonAddress) {
        this.alarmPersonAddress = alarmPersonAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAlarmPersonIdentityNumber() {
        return alarmPersonIdentityNumber;
    }

    public void setAlarmPersonIdentityNumber(String alarmPersonIdentityNumber) {
        this.alarmPersonIdentityNumber = alarmPersonIdentityNumber;
    }
}
