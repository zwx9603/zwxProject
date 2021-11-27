package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 微站调派单信息
 *
 */
public class HandleMiniatureStationBean extends BaseBean {

    private String incidentId; //案件ID

    private String handleId ;//处警单ID

    private String handleBatch;// 处警批次

    private String  handleType  ;//调派类型（01微站、02联勤、03联动、04专家）

    private String  handleTypeName  ;//调派类型（01微站、02联勤、03联动、04专家）

    private String handleWay ;//调派方式（01电话、02短信、03IVR、04系统）

    private String handleWayName ;//调派方式（01电话、02短信、03IVR、04系统）

    private String handleObjectId  ;//调派对象id

    private String handleObjectName;// 调派对象名称

    private String districtCode  ;//调派对象 行政区划

    private String districtName  ;//调派对象 所行政区划 名称

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private Long handleTime ;// 调派时间

    private String relayRecordNumber;//录音号（电话方式）

    private String handleContent ; //  内容（短信调派后IVR时用）

    private String handleStatus ; //  调派单状态

    private String handleStatusName ; //  调派单状态

    private String handleOrganizationId ; // 处警单位编号

    private String handleOrganizationName ; // 处警单位名称

    private String handlePersonNumber; // 处警人员编号

    private String handleSeatNumber; // 处警坐席号

    private Long latestFeedbackTime ; //最新反馈时间


    public String getHandleTypeName() {
        return handleTypeName;
    }

    public void setHandleTypeName(String handleTypeName) {
        this.handleTypeName = handleTypeName;
    }

    public String getHandleWayName() {
        return handleWayName;
    }

    public void setHandleWayName(String handleWayName) {
        this.handleWayName = handleWayName;
    }

    public String getHandleStatusName() {
        return handleStatusName;
    }

    public void setHandleStatusName(String handleStatusName) {
        this.handleStatusName = handleStatusName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getHandleWay() {
        return handleWay;
    }

    public void setHandleWay(String handleWay) {
        this.handleWay = handleWay;
    }

    public String getHandleObjectId() {
        return handleObjectId;
    }

    public void setHandleObjectId(String handleObjectId) {
        this.handleObjectId = handleObjectId;
    }

    public String getHandleObjectName() {
        return handleObjectName;
    }

    public void setHandleObjectName(String handleObjectName) {
        this.handleObjectName = handleObjectName;
    }

    public Long getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Long handleTime) {
        this.handleTime = handleTime;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleOrganizationId() {
        return handleOrganizationId;
    }

    public void setHandleOrganizationId(String handleOrganizationId) {
        this.handleOrganizationId = handleOrganizationId;
    }

    public String getHandlePersonNumber() {
        return handlePersonNumber;
    }

    public void setHandlePersonNumber(String handlePersonNumber) {
        this.handlePersonNumber = handlePersonNumber;
    }

    public String getHandleSeatNumber() {
        return handleSeatNumber;
    }

    public void setHandleSeatNumber(String handleSeatNumber) {
        this.handleSeatNumber = handleSeatNumber;
    }

    public String getHandleOrganizationName() {
        return handleOrganizationName;
    }

    public void setHandleOrganizationName(String handleOrganizationName) {
        this.handleOrganizationName = handleOrganizationName;
    }

    public String getHandleBatch() {
        return handleBatch;
    }

    public void setHandleBatch(String handleBatch) {
        this.handleBatch = handleBatch;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getLatestFeedbackTime() {
        return latestFeedbackTime;
    }

    public void setLatestFeedbackTime(Long latestFeedbackTime) {
        this.latestFeedbackTime = latestFeedbackTime;
    }
}
