package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 描述:案件拆分 保存参数
 *
 */
public class IncidentSplitSaveInputInfo {

    private String mainIncidentId; // 主案件ID

    private String splitIncidentId; // 被拆分案件ID

    private String splitSeatNumber; // 分拆坐席编号

    private String acceptancePersonNumber;// 接警员工号

    private String remarks;//备注

    public String getMainIncidentId() {
        return mainIncidentId;
    }

    public void setMainIncidentId(String mainIncidentId) {
        this.mainIncidentId = mainIncidentId;
    }

    public String getSplitIncidentId() {
        return splitIncidentId;
    }

    public void setSplitIncidentId(String splitIncidentId) {
        this.splitIncidentId = splitIncidentId;
    }

    public String getSplitSeatNumber() {
        return splitSeatNumber;
    }

    public void setSplitSeatNumber(String splitSeatNumber) {
        this.splitSeatNumber = splitSeatNumber;
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
