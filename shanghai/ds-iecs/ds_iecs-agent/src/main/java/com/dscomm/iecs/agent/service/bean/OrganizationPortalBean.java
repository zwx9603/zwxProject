package com.dscomm.iecs.agent.service.bean;

import java.util.List;

public class OrganizationPortalBean {

    private String architectureType;// 单位架构类型独立单位

    private Long createTime;// 创建时间

    private String orgNumber;// 组织机构代码

    private RegionPortalBean orgRegion;// 行政区域

    private String orgType;// 单位类型

    private String policeType;// 警种类型

    private List<OrganizationPortalBean> childrenOrg;// 下级单位

    private String memo; // 备注

    private String code; // 单位编码

    private String communicationType;// 沟通类型

    private String fullKeyId; // 单位完全ID

    private String fullName; // 单位全称

    private String id; // 单位ID

    private boolean innerSysUnit;// 是否内部体系（标识是否是系统内组织机构）

    private String name; // 单位名称

    private boolean netStatus;// 联网状态

    private int order;// 单位排序

    private String orgKey; // 单位Key

    private String parentCode; // 上级单位编码

    private String parentId;// 上级单位ID

    private String pinyinHeader;// 拼音开头

    private String searchCode;// 查询码

    private String searchCodePath;

    private Long updateTime; // 更新时间

    private boolean valid;// 是否有效

    private String postCode; // 邮政编码

    private String address; // 地址

    private String orgLongitude; // 经度

    private String orgLatitude; // 纬度

    private String businessUnit; // 业务体系标志

    private boolean authToUnitNode; // 配置成直属独立组织机构
    /** 成员变量：联系方式 */
    private List<ContactsSelfPortalBean> orgContacts;
    /** 成员变量：负责人 */
    private PersonPortalBean orgPrincipal;
    /** 成员变量：上级机构信息 */
    private OrganizationPortalBean parentOrg;

    public String getArchitectureType() {
        return architectureType;
    }

    public void setArchitectureType(String architectureType) {
        this.architectureType = architectureType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public RegionPortalBean getOrgRegion() {
        return orgRegion;
    }

    public void setOrgRegion(RegionPortalBean orgRegion) {
        this.orgRegion = orgRegion;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }

    public List<OrganizationPortalBean> getChildrenOrg() {
        return childrenOrg;
    }

    public void setChildrenOrg(List<OrganizationPortalBean> childrenOrg) {
        this.childrenOrg = childrenOrg;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public String getFullKeyId() {
        return fullKeyId;
    }

    public void setFullKeyId(String fullKeyId) {
        this.fullKeyId = fullKeyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInnerSysUnit() {
        return innerSysUnit;
    }

    public void setInnerSysUnit(boolean innerSysUnit) {
        this.innerSysUnit = innerSysUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNetStatus() {
        return netStatus;
    }

    public void setNetStatus(boolean netStatus) {
        this.netStatus = netStatus;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getOrgKey() {
        return orgKey;
    }

    public void setOrgKey(String orgKey) {
        this.orgKey = orgKey;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPinyinHeader() {
        return pinyinHeader;
    }

    public void setPinyinHeader(String pinyinHeader) {
        this.pinyinHeader = pinyinHeader;
    }

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }

    public String getSearchCodePath() {
        return searchCodePath;
    }

    public void setSearchCodePath(String searchCodePath) {
        this.searchCodePath = searchCodePath;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrgLongitude() {
        return orgLongitude;
    }

    public void setOrgLongitude(String orgLongitude) {
        this.orgLongitude = orgLongitude;
    }

    public String getOrgLatitude() {
        return orgLatitude;
    }

    public void setOrgLatitude(String orgLatitude) {
        this.orgLatitude = orgLatitude;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public boolean isAuthToUnitNode() {
        return authToUnitNode;
    }

    public void setAuthToUnitNode(boolean authToUnitNode) {
        this.authToUnitNode = authToUnitNode;
    }

    public List<ContactsSelfPortalBean> getOrgContacts() {
        return orgContacts;
    }

    public void setOrgContacts(List<ContactsSelfPortalBean> orgContacts) {
        this.orgContacts = orgContacts;
    }

    public PersonPortalBean getOrgPrincipal() {
        return orgPrincipal;
    }

    public void setOrgPrincipal(PersonPortalBean orgPrincipal) {
        this.orgPrincipal = orgPrincipal;
    }

    public OrganizationPortalBean getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(OrganizationPortalBean parentOrg) {
        this.parentOrg = parentOrg;
    }
}
