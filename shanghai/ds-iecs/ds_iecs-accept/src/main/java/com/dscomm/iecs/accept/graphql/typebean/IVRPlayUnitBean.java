package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *
 * 播放录音机构（表名不能改）
 */
public class IVRPlayUnitBean  extends BaseBean  {


    private String ivrPlayRecordId  ;//播放录音记录编号

    private String playPhoneNumber;//放音接听电话

    private String  playStatus ; //  放音状态

    private Long playTime ;// 放音时间

    public String getIvrPlayRecordId() {
        return ivrPlayRecordId;
    }

    public void setIvrPlayRecordId(String ivrPlayRecordId) {
        this.ivrPlayRecordId = ivrPlayRecordId;
    }

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

    public Long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Long playTime) {
        this.playTime = playTime;
    }
}
