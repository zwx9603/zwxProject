package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述:黑名单保存参数
 *
 */
public class BlacklistSaveInputInfo {

    private String phoneNumber;// 电话号码 主键

    private String alarmNumber;  // 报警编号

    private String blackTimeCode ; //黑名单字典锁定code

    public String getBlackTimeCode() {
        return blackTimeCode;
    }

    public void setBlackTimeCode(String blackTimeCode) {
        this.blackTimeCode = blackTimeCode;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
