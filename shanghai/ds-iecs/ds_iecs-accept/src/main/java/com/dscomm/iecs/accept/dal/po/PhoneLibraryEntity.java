package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 电话库
 *
 */
@Entity
@Table(name = "DHK")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PhoneLibraryEntity extends BaseEntity {

    @Column(name = "DHHM" , length =  50 )
    private String phoneNumber;//电话号码

    @Column(name = "YHXM", length = 100)
    private String userName; // 用户姓名

    @Column(name = "YHDZ", length = 400)
    private String userAddress; // 用户地址

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
