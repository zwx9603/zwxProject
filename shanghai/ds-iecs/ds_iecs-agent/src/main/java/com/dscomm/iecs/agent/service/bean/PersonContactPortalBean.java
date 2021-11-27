package com.dscomm.iecs.agent.service.bean;

public class PersonContactPortalBean {

    private String    id;             //人员联系方式ID
    private String    code;           //人员联系方式编码
    private String    contactLabel;   //人员联系方式标题
    private String    contactValue;   //人员联系方式值
    private String    contactType;    //人员联系方式
    private Integer   order;          //排序
    private PersonPortalBean  owner;          //所属人员，此处为null
    private Long createTime;     //创建时间
    private Long      updateTime;     //更新时间
    private boolean   valid;	      //是否有效
    private String    remark;		  //备注
    private boolean   defaultFlag;	  //是否默认联系方式

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContactLabel() {
        return contactLabel;
    }

    public void setContactLabel(String contactLabel) {
        this.contactLabel = contactLabel;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public PersonPortalBean getOwner() {
        return owner;
    }

    public void setOwner(PersonPortalBean owner) {
        this.owner = owner;
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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}
