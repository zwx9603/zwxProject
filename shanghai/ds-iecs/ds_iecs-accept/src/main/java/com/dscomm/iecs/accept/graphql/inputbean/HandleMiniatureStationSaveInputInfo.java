package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 微站调派单信息
 *
 */
public class HandleMiniatureStationSaveInputInfo   {


    private String  handleType  ;//调派类型（微站、联勤、联动、专家）

    private String handleWay ;//调派方式（电话、短信、IVR、系统）

    private String handleObjectId  ;//调派对象id

    private String handleObjectName;// 调派对象名称

    private String districtCode  ;//调派对象所属辖区

    private String organizationId  ;//调派对象所属消防机构id

    private Long handleTime ;// 调派时间

    private String relayRecordNumber;//录音号（电话方式）

    private String handleContent ; //  内容（短信调派后IVR时用）

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
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


}
