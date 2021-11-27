package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 *
 * 播放录音记录（表名不能改）
 */
public class IVRPlayRecordBean extends BaseBean {

    private String handleMiniatureStationId ;//调派单ID

    private String relayRecordNumber;//录音编号

    private Long playTime ;// 放音时间

    private String smsContent ;//通知短信内容

    private Long    visitTime ;// 回访登记时间

    private String  recordStatus ; //  状态（默认值0）

    private List<IVRPlayUnitBean> ivrPlayUnitBean ; //播放单位信息

    public List<IVRPlayUnitBean> getIvrPlayUnitBean() {
        return ivrPlayUnitBean;
    }

    public void setIvrPlayUnitBean(List<IVRPlayUnitBean> ivrPlayUnitBean) {
        this.ivrPlayUnitBean = ivrPlayUnitBean;
    }

    public String getHandleMiniatureStationId() {
        return handleMiniatureStationId;
    }

    public void setHandleMiniatureStationId(String handleMiniatureStationId) {
        this.handleMiniatureStationId = handleMiniatureStationId;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public Long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Long playTime) {
        this.playTime = playTime;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public Long getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Long visitTime) {
        this.visitTime = visitTime;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }
}
