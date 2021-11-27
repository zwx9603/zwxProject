package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:坐席
 */
@Entity
@Table(name = "jcj_bd_zx")
public class AgentEntity extends BaseEntity {

    @Column(name = "th")
    private String agentNumber; //台号

    @Column(name = "fjh")
    private String extensionNumber; //分机号

    @Column(name = "yjdhhm")
    private String emerPhone; //应急电话号码

    @Column(name = "ssdw")
    private String organizationCode; //所属单位

    @Column(name = "ip")
    private String ip; //IP

    @Column(name = "dlacd")
    private String loginAcd; //登陆ACD

    @Column(name = "ftms")
    private String extensionType; //分机模式

    @Column(name = "zxlx")
    private String agentType; //坐席类型

    @Column(name = "jnjb")
    private String skillLevel; //技能级别

    @Column(name = "dlzt")
    private Integer loginState; //登陆状态

    @Column(name = "dlgh")
    private String loginNum; //登陆工号

    @Column(name = "xh")
    private String order; //排序字段

    @Column(name = "jdlx21")
    private Integer jdlx21;//21节点类型

    @Column(name = "room")
    private String room; //房间号

    @Column(name = "dccode")
    private String dccode; //dc编码

    @Column(name = "dcpassword")
    private String dcpassword; //dc密码

    @Column(name = "latesttime")
    private Long latesttime; //最新时间

    @Column(name = "icpip")
    private String icpip; //icpip

    @Column(name = "speakerid")
    private String speakerid; //扬声器id

    @Column(name = "wluserid")
    private String wluserid; //wl用户id

    @Column(name = "wluserpassword")
    private String wluserpassword; //wl用户密码

    @Column(name = "wlserverinfo")
    private String wlserverinfo; //wl服务详情

    @Column(name = "talkinggroup")
    private String talkinggroup; //讨论组

    @Column(name = "wldeviceid")
    private String wldeviceid; //wl设备id

    @Column(name = "wlconferenceid")
    private String wlconferenceid; //wl会议id

    @Column(name = "elteproxymode")
    private Integer elteproxymode; //宽带代理模式

    @Column(name = "bz", length = 800)
    private String remarks;//备注

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public String getEmerPhone() {
        return emerPhone;
    }

    public void setEmerPhone(String emerPhone) {
        this.emerPhone = emerPhone;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginAcd() {
        return loginAcd;
    }

    public void setLoginAcd(String loginAcd) {
        this.loginAcd = loginAcd;
    }

    public String getExtensionType() {
        return extensionType;
    }

    public void setExtensionType(String extensionType) {
        this.extensionType = extensionType;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    public String getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getJdlx21() {
        return jdlx21;
    }

    public void setJdlx21(Integer jdlx21) {
        this.jdlx21 = jdlx21;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDccode() {
        return dccode;
    }

    public void setDccode(String dccode) {
        this.dccode = dccode;
    }

    public String getDcpassword() {
        return dcpassword;
    }

    public void setDcpassword(String dcpassword) {
        this.dcpassword = dcpassword;
    }

    public Long getLatesttime() {
        return latesttime;
    }

    public void setLatesttime(Long latesttime) {
        this.latesttime = latesttime;
    }

    public String getIcpip() {
        return icpip;
    }

    public void setIcpip(String icpip) {
        this.icpip = icpip;
    }

    public String getSpeakerid() {
        return speakerid;
    }

    public void setSpeakerid(String speakerid) {
        this.speakerid = speakerid;
    }

    public String getWluserid() {
        return wluserid;
    }

    public void setWluserid(String wluserid) {
        this.wluserid = wluserid;
    }

    public String getWluserpassword() {
        return wluserpassword;
    }

    public void setWluserpassword(String wluserpassword) {
        this.wluserpassword = wluserpassword;
    }

    public String getWlserverinfo() {
        return wlserverinfo;
    }

    public void setWlserverinfo(String wlserverinfo) {
        this.wlserverinfo = wlserverinfo;
    }

    public String getTalkinggroup() {
        return talkinggroup;
    }

    public void setTalkinggroup(String talkinggroup) {
        this.talkinggroup = talkinggroup;
    }

    public String getWldeviceid() {
        return wldeviceid;
    }

    public void setWldeviceid(String wldeviceid) {
        this.wldeviceid = wldeviceid;
    }

    public String getWlconferenceid() {
        return wlconferenceid;
    }

    public void setWlconferenceid(String wlconferenceid) {
        this.wlconferenceid = wlconferenceid;
    }

    public Integer getElteproxymode() {
        return elteproxymode;
    }

    public void setElteproxymode(Integer elteproxymode) {
        this.elteproxymode = elteproxymode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
