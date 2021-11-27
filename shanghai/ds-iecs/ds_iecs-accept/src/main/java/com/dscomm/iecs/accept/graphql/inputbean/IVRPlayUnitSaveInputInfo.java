package com.dscomm.iecs.accept.graphql.inputbean;


/**
 *
 * 播放录音机构（表名不能改）
 */
public class IVRPlayUnitSaveInputInfo {

    private String playPhoneNumber;//放音接听电话

    private String  playStatus ; //  放音状态

    public String getPlayPhoneNumber() {
        return playPhoneNumber;
    }

    public void setPlayPhoneNumber(String playPhoneNumber) {
        this.playPhoneNumber = playPhoneNumber;
    }

    public String getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(String playStatus) {
        this.playStatus = playStatus;
    }

}
