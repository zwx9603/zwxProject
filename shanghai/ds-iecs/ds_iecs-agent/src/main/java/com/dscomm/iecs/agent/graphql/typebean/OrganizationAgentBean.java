package com.dscomm.iecs.agent.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 描述:坐席信息
 *
 */
public class OrganizationAgentBean extends BaseBean {

    private String organizationId; //所属单位

    private String organizationCode; //所属单位代码

    private String organizationName; //所属单位名称

    private String organizationParentId; // 上级机构ID

    private String organizationParentCode; // 上级机构代码

    private String organizationParentName; //所属单位名称

    private String contactPerson;// 单位联系人

    private String contactPhone;// 单位联系电话

    private List<AgentBean> agentBeanList ; //坐席

    private List<AgentBean> onlineAgentBeanList ; //在线坐席

    public List<AgentBean> getOnlineAgentBeanList() {
        return onlineAgentBeanList;
    }

    public void setOnlineAgentBeanList(List<AgentBean> onlineAgentBeanList) {
        this.onlineAgentBeanList = onlineAgentBeanList;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(String organizationParentId) {
        this.organizationParentId = organizationParentId;
    }

    public String getOrganizationParentCode() {
        return organizationParentCode;
    }

    public void setOrganizationParentCode(String organizationParentCode) {
        this.organizationParentCode = organizationParentCode;
    }

    public String getOrganizationParentName() {
        return organizationParentName;
    }

    public void setOrganizationParentName(String organizationParentName) {
        this.organizationParentName = organizationParentName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<AgentBean> getAgentBeanList() {
        return agentBeanList;
    }

    public void setAgentBeanList(List<AgentBean> agentBeanList) {
        this.agentBeanList = agentBeanList;
    }
}
