package com.dscomm.iecs.accept.graphql.inputbean;

/**
 *  描述:参战人员明细设置 保存参数
 *
 */
public class ParticipantFeedbackSaveInputInfo   {

    private  String   id ; // 主键

    private String vehicleId;//车辆编号

    private String personId; // 人员编号

    private String personName; // 人员名称

    private Integer sorter  ; //排序

    private Long checkTime; //清点时间

    private Integer whetherFeedback = 0; //是否返回（ 0未返回 1 返回 默认0未返回）

    private Long feedbackCheckTime; //返回清点时间

    private String remarks;//备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

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

    public Integer getSorter() {
        return sorter;
    }

    public void setSorter(Integer sorter) {
        this.sorter = sorter;
    }

    public Long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getWhetherFeedback() {
        return whetherFeedback;
    }

    public void setWhetherFeedback(Integer whetherFeedback) {
        this.whetherFeedback = whetherFeedback;
    }

    public Long getFeedbackCheckTime() {
        return feedbackCheckTime;
    }

    public void setFeedbackCheckTime(Long feedbackCheckTime) {
        this.feedbackCheckTime = feedbackCheckTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
