package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述： 增援请求 保存参数
 */
public class ReinforcementAskSaveInputInfo {

    private String incidentId;//案件ID

    private String type;//信息类型  自定义 1请求增援

    private String content;//内容

    private String askOrganizationId;// 发送机构ID

    private String receiveOrganizationId;// 接受机构ID

    private String reinforcementTypeSign;// 增援类型标志

    private String meetAddress;// 接应地点

    private Long meetTime;// 接应时间

    private String meetContactPerson;// 接应联系人

    private String meetContactPersonPhone;// 接应联系人电话

    private String remarks; // 备注

    private List<ReinforcementAskDetailInputInfo> reinforcementAskDetailInputInfo; //请求详情（车辆类型、数量、装备及要求）

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAskOrganizationId() {
        return askOrganizationId;
    }

    public void setAskOrganizationId(String askOrganizationId) {
        this.askOrganizationId = askOrganizationId;
    }

    public String getReceiveOrganizationId() {
        return receiveOrganizationId;
    }

    public void setReceiveOrganizationId(String receiveOrganizationId) {
        this.receiveOrganizationId = receiveOrganizationId;
    }

    public String getReinforcementTypeSign() {
        return reinforcementTypeSign;
    }

    public void setReinforcementTypeSign(String reinforcementTypeSign) {
        this.reinforcementTypeSign = reinforcementTypeSign;
    }

    public String getMeetAddress() {
        return meetAddress;
    }

    public void setMeetAddress(String meetAddress) {
        this.meetAddress = meetAddress;
    }

    public Long getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(Long meetTime) {
        this.meetTime = meetTime;
    }

    public String getMeetContactPerson() {
        return meetContactPerson;
    }

    public void setMeetContactPerson(String meetContactPerson) {
        this.meetContactPerson = meetContactPerson;
    }

    public String getMeetContactPersonPhone() {
        return meetContactPersonPhone;
    }

    public void setMeetContactPersonPhone(String meetContactPersonPhone) {
        this.meetContactPersonPhone = meetContactPersonPhone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ReinforcementAskDetailInputInfo> getReinforcementAskDetailInputInfo() {
        return reinforcementAskDetailInputInfo;
    }

    public void setReinforcementAskDetailInputInfo(List<ReinforcementAskDetailInputInfo> reinforcementAskDetailInputInfo) {
        this.reinforcementAskDetailInputInfo = reinforcementAskDetailInputInfo;
    }
}
