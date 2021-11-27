package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 人员通讯录
 *
 */
public class PersonMailBean extends BaseBean {

    private String personName; // 姓名

    private String personId; // 人员ID

    private String userId; // 用户ID

    private String mobilePhone; // 移动电话

    private String homePhone; // 家庭电话

    private String officePhone; // 办公电话

    private String mobilePhoneTwo; // 移动电话2

    private String mobilePhoneThree; // 移动电话3

    private String internetEmail; // 因特网电子邮箱

    private String securityEmail; // 公安网邮箱

    private String shortNumber; // 短号

    private String remarks; // 备注



    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
