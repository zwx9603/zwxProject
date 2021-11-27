package com.dscomm.iecs.agent.graphql.typebean;

import com.dscomm.iecs.agent.service.distribute.bean.DistributeModeBean;
import com.dscomm.iecs.base.enums.BasicEnumNumberBean;
import com.dscomm.iecs.base.service.bean.BaseBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.AccessBean;

import java.util.List;

/**
 * 描述:坐席信息
 *
 */
public class AgentShanghaiBean extends BaseBean {

    private String agentNumber; //台号

    private String extensionNumber; //分机号

    private String emerPhone; //应急电话号码

    private String organizationCode; //所属单位

    private String organizationName; //所属单位

    private Integer organizationOrderNum; //所属单位排序

    private String ip; //IP

    private String loginAcd; //登陆ACD

    private String extensionType; //分机模式

    private String agentType; //坐席类型 接警 处警 技防(归接警班长) 班长

    private String skillLevel; //技能级别

    private Integer loginState; //登陆状态

    private String loginNum; //登陆工号

    private String order; //排序字段 用于后端排序

    private BasicEnumNumberBean agentState;//坐席状态

    private BasicEnumNumberBean agentOperateState;//坐席操作状态

    private BasicEnumNumberBean agentPhoneType;//坐席话务类型

    private BasicEnumNumberBean agentPhoneState;//坐席话务状态

    private AccessBean accessBean ; //访问信息

    private long  loginTime; // 登录时间

    private Integer onlineSign = 0 ;// 在线标志  0 不在线 1 在线

    private AgentPersonBean personBean;//坐席人员信息

    private ViolationBean violateInfo;//违规信息

    private String phone;//坐席电话

    private Integer jdlx21; //21节点类型

    private String room; //房间号

    private String dccode; //dc编码

    private String dcpassword; //dc密码

    private Long latesttime; //最新时间

    private String icpip; //icpip

    private String speakerid; //扬声器id

    private String wluserid; //wl用户id

    private String wluserpassword; //wl用户密码

    private String wlserverinfo; //wl服务详情

    private String talkinggroup; //讨论组

    private String wldeviceid; //wl设备id

    private String wlconferenceid; //wl会议id

    private Integer elteproxymode; //宽带代理模式

    private String remarks;//备注

    private String meetingMark=""; //会议标志

    private String remotePhone=""; //远程电话

    private Boolean needAuthenticate=false;//是否分权分域

    private List<BasicEnumNumberBean> agentRole;//坐席权限


    /**
     * proxyip
     */
    private String proxyip;


    public Integer getOrganizationOrderNum() {
        return organizationOrderNum;
    }

    public void setOrganizationOrderNum(Integer organizationOrderNum) {
        this.organizationOrderNum = organizationOrderNum;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public Boolean getNeedAuthenticate() {
        return needAuthenticate;
    }

    public void setNeedAuthenticate(Boolean needAuthenticate) {
        this.needAuthenticate = needAuthenticate;
    }

    private DistributeModeBean distribute;

    public DistributeModeBean getDistribute() {
        return distribute;
    }

    public void setDistribute(DistributeModeBean distribute) {
        this.distribute = distribute;
    }


    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public AccessBean getAccessBean() {
        return accessBean;
    }

    public void setAccessBean(AccessBean accessBean) {
        this.accessBean = accessBean;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getMeetingMark() {
        return meetingMark;
    }

    public void setMeetingMark(String meetingMark) {
        this.meetingMark = meetingMark;
    }

    public String getRemotePhone() {
        return remotePhone;
    }

    public void setRemotePhone(String remotePhone) {
        this.remotePhone = remotePhone;
    }

    public Integer getOnlineSign() {
        return onlineSign;
    }

    public void setOnlineSign(Integer onlineSign) {
        this.onlineSign = onlineSign;
    }

    public BasicEnumNumberBean getAgentState() {
        return agentState;
    }

    public void setAgentState(BasicEnumNumberBean agentState) {
        this.agentState = agentState;
    }

    public BasicEnumNumberBean getAgentOperateState() {
        return agentOperateState;
    }

    public void setAgentOperateState(BasicEnumNumberBean agentOperateState) {
        this.agentOperateState = agentOperateState;
    }

    public BasicEnumNumberBean getAgentPhoneType() {
        return agentPhoneType;
    }

    public void setAgentPhoneType(BasicEnumNumberBean agentPhoneType) {
        this.agentPhoneType = agentPhoneType;
    }

    public BasicEnumNumberBean getAgentPhoneState() {
        return agentPhoneState;
    }

    public void setAgentPhoneState(BasicEnumNumberBean agentPhoneState) {
        this.agentPhoneState = agentPhoneState;
    }

    public AgentPersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(AgentPersonBean personBean) {
        this.personBean = personBean;
    }

    public ViolationBean getViolateInfo() {
        return violateInfo;
    }

    public void setViolateInfo(ViolationBean violateInfo) {
        this.violateInfo = violateInfo;
    }

    public String getProxyip() {
        return proxyip;
    }

    public void setProxyip(String proxyip) {
        this.proxyip = proxyip;
    }

    public List<BasicEnumNumberBean> getAgentRole() {
        return agentRole;
    }

    public void setAgentRole(List<BasicEnumNumberBean> agentRole) {
        this.agentRole = agentRole;
    }
}
