package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 增援请求
 *
 */
@Entity
@Table(name = "JCJ_QQZY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ReinforcementAskEntity extends BaseEntity {

    @Column(name = "AJID" , length =  100 )
    private String incidentId;//案件ID

    @Column(name = "MSGTYPE" , length =  100 )
    private String type;//信息类型  自定义 1请求增援

    @Column(name = "NR" , length =  1000 )
    private String content;//内容

    @Column(name = "FSJGID", length = 100)
    private String askOrganizationId;// 发送机构ID

    @Column(name = "JSJGID", length = 100)
    private String receiveOrganizationId;// 接受机构ID

    @Column(name = "LXBZ", length = 100)
    private String reinforcementTypeSign;// 增援类型标志

    @Column(name = "CLBZ", length = 100)
    private String handleSign;// 处理标志

    @Column(name = "JJDD", length = 400)
    private String meetAddress;// 接应地点

    @Column(name = "JJSJ" )
    private Long meetTime;// 接应时间

    @Column(name = "JJLXR", length = 100)
    private String meetContactPerson;// 接应联系人

    @Column(name = "JJLXRDH" , length = 50)
    private String meetContactPersonPhone;// 接应联系人电话

    @Column(name = "QQZYXX" , length =  4000 )
    private String reinforcementAskContent ;//请求增援内容

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getReinforcementAskContent() {
        return reinforcementAskContent;
    }

    public void setReinforcementAskContent(String reinforcementAskContent) {
        this.reinforcementAskContent = reinforcementAskContent;
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
