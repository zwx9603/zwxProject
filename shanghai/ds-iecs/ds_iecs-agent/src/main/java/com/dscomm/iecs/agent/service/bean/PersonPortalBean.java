package com.dscomm.iecs.agent.service.bean;

import java.util.List;

public class PersonPortalBean {

    private String                personId;            //人员ID
    private String                personCode;          //人员编码
    private String                personName;          //人员名称
    private Long                  birthday;            //生日
    private String				  employeeNumber;
    private String                identityNo;          //身份证号码
    private String                sex;                 //性别
    private String                address;             //地址
    private String                political;	       //政治面貌
    private boolean               systemPerson;        //是否系统管理人员
    private boolean               valid;               //是否有效
    private Integer               order;               //人员排序
    private Long                  createTime;          //创建时间
    private Long                  updateTime;          //更新时间
    private String                headPicture;         //头像

    private String                rankCode;	           //职务编码 此处为null
    private String                rankName;	           //职务名称 此处为null
    private Integer                rankOrder;		   //职务排序
    private String                jzrankCode;	       //兼职编码 此处为null
    private String                jzrankName;	       //兼职名称 此处为null
    private Integer                jzrankOrder;	       //兼职排序
    private String                remark;		       //备注
    private String                policeNumber;        //警号

    private List<OrganizationPortalBean> personOrg; // 所属单位 此处为null
    private List<PersonContactPortalBean> personContacts;      //联系方式

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

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public boolean isSystemPerson() {
        return systemPerson;
    }

    public void setSystemPerson(boolean systemPerson) {
        this.systemPerson = systemPerson;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getRankCode() {
        return rankCode;
    }

    public void setRankCode(String rankCode) {
        this.rankCode = rankCode;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Integer getRankOrder() {
        return rankOrder;
    }

    public void setRankOrder(Integer rankOrder) {
        this.rankOrder = rankOrder;
    }

    public String getJzrankCode() {
        return jzrankCode;
    }

    public void setJzrankCode(String jzrankCode) {
        this.jzrankCode = jzrankCode;
    }

    public String getJzrankName() {
        return jzrankName;
    }

    public void setJzrankName(String jzrankName) {
        this.jzrankName = jzrankName;
    }

    public Integer getJzrankOrder() {
        return jzrankOrder;
    }

    public void setJzrankOrder(Integer jzrankOrder) {
        this.jzrankOrder = jzrankOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public List<OrganizationPortalBean> getPersonOrg() {
        return personOrg;
    }

    public void setPersonOrg(List<OrganizationPortalBean> personOrg) {
        this.personOrg = personOrg;
    }

    public List<PersonContactPortalBean> getPersonContacts() {
        return personContacts;
    }

    public void setPersonContacts(List<PersonContactPortalBean> personContacts) {
        this.personContacts = personContacts;
    }
}
