package com.dscomm.iecs.basedata.graphql.typebean.user;


import com.dscomm.iecs.base.enums.BasicEnumNumberBean;

import java.io.Serializable;
import java.util.List;

/**
 * 描述:用户信息
 *
 * @author YangFuxi
 * Date Time 2019/8/27 20:11
 */
public class UserInfo implements Serializable {
    private String account;//用户账号
    private String userId;//用户ID
    private String userCode;//用户编号
    private String userName;//用户名称
    private String personId;//人员ID
    private String personCode;//人员Code
    private String personName;//人员姓名
    private String policeNum;//警号 消防人员id
    private String policeType;//警种
    private String orgId;//单位ID
    private String orgType;//单位类别
    private String orgNature;//单位性质
    private String orgCode;//单位Code
    private String orgName;//单位名称
    private String orgFullName;//单位全称
    private String orgFullKeyId;//单位全ID
    private Integer orgOrderNum  ; // 机构排序
    private String orgParentId;//上级单位ID
    private String orgParentName;//上级单位ID
    private String agentId;//坐席ID
    private String agentNum;//坐席号
    private String agentOrder;//坐席序号，用于排序
    private String agentRoom;//坐席room
    private String deskphone;//坐席分机号
    private String acdGroupNumber;//acd组号
    private String clientIp;//机器IP
    private List<BasicEnumNumberBean> userRole;//人员角色(接处警角色)
    private List<RoleBean> businessRoles;//业务(警种)角色
    private String areaCode;//行政区划
    private String belongSystem;//所属系统，冗余字段，用于标记第三方系统
    private String fileExplain;//文件说明

    private Integer scopeType = 0 ; //机构是否为根机构  0根机构 1非根机构
    private Boolean needAuthenticate=false;//是否需要分权分域

    private String icpType ; //用户icpType类型   0接处警( 接警台 ) 需要认证坐席与账号 1接处警( 受理台 )需要账号 2 实战指挥  3 移动端imei   9 无认证账号
    private int  onlineSign = 0  ; // 是否在线 0 不在 1 在线
    //2021-04-21 新增 最后登录时间
    private Long latesttime; //最新时间

    public Long getLatesttime() {
        return latesttime;
    }

    public void setLatesttime(Long latesttime) {
        this.latesttime = latesttime;
    }

    public int getOnlineSign() {
        return onlineSign;
    }

    public void setOnlineSign(int onlineSign) {
        this.onlineSign = onlineSign;
    }

    public String getOrgParentName() {
        return orgParentName;
    }

    public void setOrgParentName(String orgParentName) {
        this.orgParentName = orgParentName;
    }

    public Integer getOrgOrderNum() {
        return orgOrderNum;
    }

    public void setOrgOrderNum(Integer orgOrderNum) {
        this.orgOrderNum = orgOrderNum;
    }

    public String getIcpType() {
        return icpType;
    }

    public void setIcpType(String icpType) {
        this.icpType = icpType;
    }

    public Boolean getNeedAuthenticate() {
        return needAuthenticate;
    }

    public void setNeedAuthenticate(Boolean needAuthenticate) {
        this.needAuthenticate = needAuthenticate;
    }

    public Integer getScopeType() {
        return scopeType;
    }

    public void setScopeType(Integer scopeType) {
        this.scopeType = scopeType;
    }

    public String getOrgNature() {
        return orgNature;
    }

    public void setOrgNature(String orgNature) {
        this.orgNature = orgNature;
    }

    public String getAcdGroupNumber() {
        return acdGroupNumber;
    }

    public void setAcdGroupNumber(String acdGroupNumber) {
        this.acdGroupNumber = acdGroupNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPoliceNum() {
        return policeNum;
    }

    public void setPoliceNum(String policeNum) {
        this.policeNum = policeNum;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public String getOrgFullKeyId() {
        return orgFullKeyId;
    }

    public void setOrgFullKeyId(String orgFullKeyId) {
        this.orgFullKeyId = orgFullKeyId;
    }

    public String getOrgParentId() {
        return orgParentId;
    }

    public void setOrgParentId(String orgParentId) {
        this.orgParentId = orgParentId;
    }

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }

    public String getDeskphone() {
        return deskphone;
    }

    public void setDeskphone(String deskphone) {
        this.deskphone = deskphone;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public List<BasicEnumNumberBean> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<BasicEnumNumberBean> userRole) {
        this.userRole = userRole;
    }

    public List<RoleBean> getBusinessRoles() {
        return businessRoles;
    }

    public void setBusinessRoles(List<RoleBean> businessRoles) {
        this.businessRoles = businessRoles;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }


    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAgentOrder() {
        return agentOrder;
    }

    public void setAgentOrder(String agentOrder) {
        this.agentOrder = agentOrder;
    }

    public String getAgentRoom() {
        return agentRoom;
    }

    public void setAgentRoom(String agentRoom) {
        this.agentRoom = agentRoom;
    }

    public String getBelongSystem() {
        return belongSystem;
    }

    public void setBelongSystem(String belongSystem) {
        this.belongSystem = belongSystem;
    }

    public String getFileExplain() {
        return fileExplain;
    }

    public void setFileExplain(String fileExplain) {
        this.fileExplain = fileExplain;
    }

}
