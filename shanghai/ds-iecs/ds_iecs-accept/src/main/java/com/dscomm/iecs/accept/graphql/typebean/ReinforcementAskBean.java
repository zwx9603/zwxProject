package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 描述： 增援请求
 *
 */
public class ReinforcementAskBean extends BaseBean {

    private String incidentId;//案件ID

    private String type;//信息类型  自定义 1请求增援

    private String content;//内容

    private String askOrganizationId;// 发送机构ID

    private String askOrganizationName;// 发送机构名称

    private String receiveOrganizationId;// 接受机构ID

    private String receiveOrganizationName;// 接受机构名称

    private String reinforcementTypeSign;// 增援类型标志

    private List<ReinforcementAskDetailBean> reinforcementAskDetailBean; //请求详情（车辆类型、数量、装备及要求）

    private String handleSign;// 处理标志

    private String meetAddress;// 接应地点

    private Long meetTime;// 接应时间

    private String meetContactPerson;// 接应联系人

    private String meetContactPersonPhone;// 接应联系人电话

    private String remarks; // 备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }


    public List<ReinforcementAskDetailBean> getReinforcementAskDetailBean() {
        return reinforcementAskDetailBean;
    }

    public void setReinforcementAskDetailBean(List<ReinforcementAskDetailBean> reinforcementAskDetailBean) {
        this.reinforcementAskDetailBean = reinforcementAskDetailBean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAskOrganizationId() {
        return askOrganizationId;
    }

    public void setAskOrganizationId(String askOrganizationId) {
        this.askOrganizationId = askOrganizationId;
    }

    public String getAskOrganizationName() {
        return askOrganizationName;
    }

    public void setAskOrganizationName(String askOrganizationName) {
        this.askOrganizationName = askOrganizationName;
    }

    public String getReceiveOrganizationId() {
        return receiveOrganizationId;
    }

    public void setReceiveOrganizationId(String receiveOrganizationId) {
        this.receiveOrganizationId = receiveOrganizationId;
    }

    public String getReceiveOrganizationName() {
        return receiveOrganizationName;
    }

    public void setReceiveOrganizationName(String receiveOrganizationName) {
        this.receiveOrganizationName = receiveOrganizationName;
    }

    public String getReinforcementTypeSign() {
        return reinforcementTypeSign;
    }

    public void setReinforcementTypeSign(String reinforcementTypeSign) {
        this.reinforcementTypeSign = reinforcementTypeSign;
    }

    public String getHandleSign() {
        return handleSign;
    }

    public void setHandleSign(String handleSign) {
        this.handleSign = handleSign;
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
}
