package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微站调派单信息
 *
 */
@Entity
@Table(name = "jcj_wzdp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandleMiniatureStationEntity extends BaseEntity {

    @Column(name = "ajid" , length =  40 )
    private String incidentId; //案件ID

    @Column(name = "YSAJBH", length = 100 )
    private String originalIncidentNumber ;  //原始案件编号

    @Column(name = "cjdid" , length =  40  )
    private String handleId ;//处警单ID

    @Column(name = "dplx" , length =  20 )
    private String  handleType  ;//调派类型（微站、联勤、联动、专家）

    @Column(name = "dpfs" , length =  20 )
    private String handleWay ;//调派方式（电话、短信、IVR、系统）

    @Column(name = "dpdxbh"  , length =  40 )
    private String handleObjectId  ;//调派对象id

    @Column(name = "dpdxmc", length = 200)
    private String handleObjectName;// 调派对象名称

    @Column(name = "ssxq"  , length =  100  )
    private String districtCode  ;//调派对象 行政区划

    @Column(name = "jgid"  , length =  100  )
    private String organizationId   ;//调派对象所属消防机构id

    @Column(name = "dpsj" )
    private Long handleTime ;// 调派时间

    @Column(name = "lyh", length = 100)
    private String relayRecordNumber;//录音号（电话方式）

    @Column(name = "nr", length = 100)
    private String handleContent ; //  内容（短信调派后IVR时用）

    @Column(name = "dpdzt", length = 20)
    private String handleStatus ; //  调派单状态

    @Column(name = "cjdwbh", length = 40 )
    private String handleOrganizationId ; // 处警单位编号

    @Column(name = "cjrbh", length = 100)
    private String handlePersonNumber; // 处警人员编号

    @Column(name = "cjzxh", length = 100)
    private String handleSeatNumber; // 处警坐席号

    @Column(name = "zxfksj" )
    private Long latestFeedbackTime ;// 最新反馈时间

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public Long getLatestFeedbackTime() {
        return latestFeedbackTime;
    }

    public void setLatestFeedbackTime(Long latestFeedbackTime) {
        this.latestFeedbackTime = latestFeedbackTime;
    }
}
