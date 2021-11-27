package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 描述:案件合并 保存参数
 *
 */
public class IncidentMergeSaveInputInfo  {

    private String mainIncidentId; // 主案件ID

    private String mergeIncidentId; // 被合并案件ID

    private Long mergeTime ;//合并时间

    private String mergeSeatNumber; // 合并坐席编号

    private String acceptancePersonNumber;// 接警员工号

    private String remarks;//备注

    public String getMainIncidentId() {
        return mainIncidentId;
    }

    public void setMainIncidentId(String mainIncidentId) {
        this.mainIncidentId = mainIncidentId;
    }

    public String getMergeIncidentId() {
        return mergeIncidentId;
    }

    public void setMergeIncidentId(String mergeIncidentId) {
        this.mergeIncidentId = mergeIncidentId;
    }

    public Long getMergeTime() {
        return mergeTime;
    }

    public void setMergeTime(Long mergeTime) {
        this.mergeTime = mergeTime;
    }

    public String getMergeSeatNumber() {
        return mergeSeatNumber;
    }

    public void setMergeSeatNumber(String mergeSeatNumber) {
        this.mergeSeatNumber = mergeSeatNumber;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
