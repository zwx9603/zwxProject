package com.dscomm.iecs.accept.graphql.typebean;

public class FindSmartRecommendationBean {

    private String id;

    private Long createdTime = -1L;

    private Long updatedTime = -1L;

    private String operator;

    private int valid =  1 ;

    private String  incidentId; //  字段 案件id

    private String requestData ; //  字段 请求数据

    private String result ; //  字段 返回结果

    private String type ; //  字段 类型,DJTJ,LLDP

    private String callManualNumber ; //  字段 调用人工号

    private String callManualName ; //  调用人姓名

    private String callUnitId ; //  字段 调用单位id

    private String callUnitName ; // 字段 调用单位名称

    private Long wasteTime;//耗费时间(智能推荐服务消耗时间)

    private Long callTime;//调用时间

    private Long sjc;

    private String actualResults;//实际结果

    private String causeOfDifference;//差异原因

    private Integer state;//状态 代表是否使用，0用户未使用 1用户使用了

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCallManualNumber() {
        return callManualNumber;
    }

    public void setCallManualNumber(String callManualNumber) {
        this.callManualNumber = callManualNumber;
    }

    public String getCallManualName() {
        return callManualName;
    }

    public void setCallManualName(String callManualName) {
        this.callManualName = callManualName;
    }

    public String getCallUnitId() {
        return callUnitId;
    }

    public void setCallUnitId(String callUnitId) {
        this.callUnitId = callUnitId;
    }

    public String getCallUnitName() {
        return callUnitName;
    }

    public void setCallUnitName(String callUnitName) {
        this.callUnitName = callUnitName;
    }

    public Long getWasteTime() {
        return wasteTime;
    }

    public void setWasteTime(Long wasteTime) {
        this.wasteTime = wasteTime;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

    public String getActualResults() {
        return actualResults;
    }

    public void setActualResults(String actualResults) {
        this.actualResults = actualResults;
    }

    public String getCauseOfDifference() {
        return causeOfDifference;
    }

    public void setCauseOfDifference(String causeOfDifference) {
        this.causeOfDifference = causeOfDifference;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
