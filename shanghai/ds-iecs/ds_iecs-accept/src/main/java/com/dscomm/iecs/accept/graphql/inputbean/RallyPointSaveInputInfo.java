package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述： 集结点保存修改参数
 *
 */
public class RallyPointSaveInputInfo {

    private String id;// 主键

    private String rallyPointCode; //集结点编号

    private String rallyPointType;// 集结点类型
    // SCENE_HEADQUARTERS( 现场指挥部 )  OPERATIONAL_TASK( 作战任务点 )
    // VEHICLE_AGGREGATION( 车辆集结点 )   EQUIPMENT_AGGREGATION( 器材集结点 ) LOGISTIC_SERVICE( 后勤保障点 )

    private String rallyPointName;// 集结点名称

    private String rallyPointDesc;// 集结点描述

    private String rallyPointStatus;//集结点状态 SAVED 保存  SENT下达

    private Long rallyPointTime;//集结时间

    private String rallyPointContacts;// 联系人

    private String rallyPointPhone;// 联系人电话

    private String rallyPointAddress ;// 集结点地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String customcredit1;// 保留字段1

    private String customcredit2;// 保留字段2

    private String remarks;// 备注

    private List<RallyItemSaveInputInfo> rallyItems ; // 集结项（集结力量）


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRallyPointCode() {
        return rallyPointCode;
    }

    public void setRallyPointCode(String rallyPointCode) {
        this.rallyPointCode = rallyPointCode;
    }

    public String getRallyPointType() {
        return rallyPointType;
    }

    public void setRallyPointType(String rallyPointType) {
        this.rallyPointType = rallyPointType;
    }

    public String getRallyPointName() {
        return rallyPointName;
    }

    public void setRallyPointName(String rallyPointName) {
        this.rallyPointName = rallyPointName;
    }

    public String getRallyPointDesc() {
        return rallyPointDesc;
    }

    public void setRallyPointDesc(String rallyPointDesc) {
        this.rallyPointDesc = rallyPointDesc;
    }

    public String getRallyPointStatus() {
        return rallyPointStatus;
    }

    public void setRallyPointStatus(String rallyPointStatus) {
        this.rallyPointStatus = rallyPointStatus;
    }

    public Long getRallyPointTime() {
        return rallyPointTime;
    }

    public void setRallyPointTime(Long rallyPointTime) {
        this.rallyPointTime = rallyPointTime;
    }

    public String getRallyPointContacts() {
        return rallyPointContacts;
    }

    public void setRallyPointContacts(String rallyPointContacts) {
        this.rallyPointContacts = rallyPointContacts;
    }

    public String getRallyPointPhone() {
        return rallyPointPhone;
    }

    public void setRallyPointPhone(String rallyPointPhone) {
        this.rallyPointPhone = rallyPointPhone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCustomcredit1() {
        return customcredit1;
    }

    public void setCustomcredit1(String customcredit1) {
        this.customcredit1 = customcredit1;
    }

    public String getCustomcredit2() {
        return customcredit2;
    }

    public void setCustomcredit2(String customcredit2) {
        this.customcredit2 = customcredit2;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<RallyItemSaveInputInfo> getRallyItems() {
        return rallyItems;
    }

    public void setRallyItems(List<RallyItemSaveInputInfo> rallyItems) {
        this.rallyItems = rallyItems;
    }
}
