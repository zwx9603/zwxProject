package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 参战人员返回信息
 *
 * */
public class ParticipantBackInputInfo {

    private List<String> ids;//参战人员反馈id集合

    private Long feedbackCheckTime;//返回时间

    private Integer state;//状态 10已调派 20进场 30退场
    private Long changeTime;//进场时间/退场时间  不传取系统时间

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Long getFeedbackCheckTime() {
        return feedbackCheckTime;
    }

    public void setFeedbackCheckTime(Long feedbackCheckTime) {
        this.feedbackCheckTime = feedbackCheckTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
    }
}
