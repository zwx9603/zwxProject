package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 人员通讯录
 *
 */
@Entity
@Table(name = "RYXX_RYTXL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PersonMailEntity extends BaseEntity {

    @Column(name = "XM", length = 100)
    private String personName; // 姓名

    @Column(name = "RYID", length = 100)
    private String personId; // 人员ID

    @Column(name = "YHID", length = 100)
    private String userId; // 用户ID

//    @Column(name = "YDDH", length = 50)
    @Column(name = "YD_LXDH", length = 50)
    private String mobilePhone; //todo 字段 移动电话

//    @Column(name = "JTDH", length = 50)
    @Column(name = "JT_LXDH", length = 50)
    private String homePhone; //todo 字段 家庭电话

//    @Column(name = "BGDH", length = 50)
    @Column(name = "BG_LXDH", length = 50)
    private String officePhone; //todo 字段 办公电话

//    @Column(name = "YDDH2", length = 50)
    @Column(name = "YD_LXDH2", length = 50)
    private String mobilePhoneTwo; //todo 字段  移动电话2

//    @Column(name = "YDDH3", length = 50)
    @Column(name = "YD_LXDH3", length = 50)
    private String mobilePhoneThree; //todo 字段  移动电话3

//    @Column(name = "YTWDZYX", length = 50)
    @Column(name = "HLW_DZXX", length = 50)
    private String internetEmail; //todo 字段 互联网_电子信箱

//    @Column(name = "GAWYX", length = 50)
    @Column(name = "NW_DZXX", length = 50)
    private String securityEmail; //todo 字典 内网_电子信箱

    @Column(name = "DH", length = 50)
    private String shortNumber; // 短号

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本




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

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}
