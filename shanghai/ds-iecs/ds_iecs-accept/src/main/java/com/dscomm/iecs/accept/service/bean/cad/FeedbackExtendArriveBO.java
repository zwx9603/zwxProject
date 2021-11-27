package com.dscomm.iecs.accept.service.bean.cad;

/**
 * 描述:到场反馈扩展表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/29 16:43
 */
public class FeedbackExtendArriveBO {
    /**
     * 反馈单编号
     */
    private String id;
    /**
     * 出警人type（1：单位，2：unit，3：人员）
     */
    private String responsePoliceType;
    /**
     * 出警人（存的id）
     */
    private String responsePolice;
    /**
     * 反馈人name（如果是unit就是unit name）
     * （另外5个反馈扩展是cjr出警人，此处应该是同一个意思）
     */
    private String feedbackArtifact;
    /**
     * 出动时间
     */
    private Long startTime;
    /**
     * 到场时间
     */
    private Long arriveTime;
    /**
     * 时间戳
     */
    private Long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponsePoliceType() {
        return responsePoliceType;
    }

    public void setResponsePoliceType(String responsePoliceType) {
        this.responsePoliceType = responsePoliceType;
    }

    public String getResponsePolice() {
        return responsePolice;
    }

    public void setResponsePolice(String responsePolice) {
        this.responsePolice = responsePolice;
    }

    public String getFeedbackArtifact() {
        return feedbackArtifact;
    }

    public void setFeedbackArtifact(String feedbackArtifact) {
        this.feedbackArtifact = feedbackArtifact;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
