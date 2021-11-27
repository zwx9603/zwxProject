package com.dscomm.iecs.basedata.graphql.typebean;

/**
 * 联系方式bean
 */
public class ContactNumberBean {

    private String contactObjectId ; //联系对象id

    private String contactObjectName ; //联系对象名称

    /** 人员 */

    private String mobilePhone; // 移动电话

    private String homePhone; // 家庭电话

    private String officePhone; // 办公电话

    private String mobilePhoneTwo; // 移动电话2

    private String mobilePhoneThree; // 移动电话3

    private String internetEmail; // 因特网电子邮箱

    private String securityEmail; // 公安网邮箱

    private String shortNumber; // 短号


    /** 单位 */

    private String dispatchPhone;// 调度电话

    private String mailCode;// 邮政编码

    private String faxNumber;// 传真号码

    private String contactPerson;// 单位联系人

    private String contactPhone;// 单位联系电话

    public String getContactObjectId() {
        return contactObjectId;
    }

    public void setContactObjectId(String contactObjectId) {
        this.contactObjectId = contactObjectId;
    }

    public String getContactObjectName() {
        return contactObjectName;
    }

    public void setContactObjectName(String contactObjectName) {
        this.contactObjectName = contactObjectName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getMobilePhoneTwo() {
        return mobilePhoneTwo;
    }

    public void setMobilePhoneTwo(String mobilePhoneTwo) {
        this.mobilePhoneTwo = mobilePhoneTwo;
    }

    public String getMobilePhoneThree() {
        return mobilePhoneThree;
    }

    public void setMobilePhoneThree(String mobilePhoneThree) {
        this.mobilePhoneThree = mobilePhoneThree;
    }

    public String getInternetEmail() {
        return internetEmail;
    }

    public void setInternetEmail(String internetEmail) {
        this.internetEmail = internetEmail;
    }

    public String getSecurityEmail() {
        return securityEmail;
    }

    public void setSecurityEmail(String securityEmail) {
        this.securityEmail = securityEmail;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    public String getDispatchPhone() {
        return dispatchPhone;
    }

    public void setDispatchPhone(String dispatchPhone) {
        this.dispatchPhone = dispatchPhone;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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
}
