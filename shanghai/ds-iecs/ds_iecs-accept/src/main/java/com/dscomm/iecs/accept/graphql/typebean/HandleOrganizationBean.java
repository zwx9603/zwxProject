package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 描述: 调派单位信息 （ 处警信息 ）
 *
 */
public class HandleOrganizationBean extends BaseBean {

    private String incidentId ; //案件ID

    private String handleId;// 处警记录ID

    private String handleBatch;// 处警批次

    private Long handleStartTime;// 处警开始时间

    private Long handleEndTime;// 处警结束时间

    private String organization; //  接收单位

    private String organizationId; //消防机构编号

    private String organizationName; //消防机构名称

    private Long noticeTime;// 通知时间

    private Long systemFeedbackTime;// 系统反馈时间

    private Long personFeedbackPersonTime;// 人工反馈时间

    private String handlePoliceStatus ;// 指令状态 （处警信息状态）  STATUS_GIVEN已通知 STATUS_SIGNED已签收

    private String handlePoliceStatusName ;// 指令状态 （处警信息状态）

    private String feedbackPersonId;// 反馈用户编号

    private String feedbackPersonName;// 反馈人姓名

    private String remarks;//备注

    private String speakToFileUrl ; //  tts播放文件路径

    private String organizationCode;// 机构代码

    private String pinyinHeader; //机构名称拼音头

    private Integer organizationOrderNum ; // 机构排序

    private String organizationParentId; // 上级机构ID

    private String districtCode;// 行政区代码

    private String districtName;// 行政区名称

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String dispatchPhone;// 调度电话

    private String mailCode;// 邮政编码

    private String faxNumber;// 传真号码

    private String email;//电子信箱

    private String contactPerson;// 单位联系人

    private String contactPersonName ;//单位联系人

    private String contactPhone;// 单位联系电话

    private List<HandleOrganizationVehicleBean>  handleOrganizationVehicleBean ; // 调派单位车辆信息 （ 作战车辆信息 ）

    private List<HandleOrganizationEquipmentBean> handleOrganizationEquipmentBean ; //调派单位装备信息

    public String getSpeakToFileUrl() {
        return speakToFileUrl;
    }

    public void setSpeakToFileUrl(String speakToFileUrl) {
        this.speakToFileUrl = speakToFileUrl;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getPinyinHeader() {
        return pinyinHeader;
    }

    public void setPinyinHeader(String pinyinHeader) {
        this.pinyinHeader = pinyinHeader;
    }

    public String getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(String organizationParentId) {
        this.organizationParentId = organizationParentId;
    }

    public Integer getOrganizationOrderNum() {
        return organizationOrderNum;
    }

    public void setOrganizationOrderNum(Integer organizationOrderNum) {
        this.organizationOrderNum = organizationOrderNum;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDispatchPhone() {
        return dispatchPhone;
    }

    public void setDispatchPhone(String dispatchPhone) {
        this.dispatchPhone = dispatchPhone;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }


    public String getHandleBatch() {
        return handleBatch;
    }

    public void setHandleBatch(String handleBatch) {
        this.handleBatch = handleBatch;
    }

    public Long getHandleStartTime() {
        return handleStartTime;
    }

    public void setHandleStartTime(Long handleStartTime) {
        this.handleStartTime = handleStartTime;
    }

    public Long getHandleEndTime() {
        return handleEndTime;
    }

    public void setHandleEndTime(Long handleEndTime) {
        this.handleEndTime = handleEndTime;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getHandlePoliceStatusName() {
        return handlePoliceStatusName;
    }

    public void setHandlePoliceStatusName(String handlePoliceStatusName) {
        this.handlePoliceStatusName = handlePoliceStatusName;
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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Long getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Long noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Long getSystemFeedbackTime() {
        return systemFeedbackTime;
    }

    public void setSystemFeedbackTime(Long systemFeedbackTime) {
        this.systemFeedbackTime = systemFeedbackTime;
    }

    public Long getPersonFeedbackPersonTime() {
        return personFeedbackPersonTime;
    }

    public void setPersonFeedbackPersonTime(Long personFeedbackPersonTime) {
        this.personFeedbackPersonTime = personFeedbackPersonTime;
    }

    public String getHandlePoliceStatus() {
        return handlePoliceStatus;
    }

    public void setHandlePoliceStatus(String handlePoliceStatus) {
        this.handlePoliceStatus = handlePoliceStatus;
    }

    public String getFeedbackPersonId() {
        return feedbackPersonId;
    }

    public void setFeedbackPersonId(String feedbackPersonId) {
        this.feedbackPersonId = feedbackPersonId;
    }

    public String getFeedbackPersonName() {
        return feedbackPersonName;
    }

    public void setFeedbackPersonName(String feedbackPersonName) {
        this.feedbackPersonName = feedbackPersonName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<HandleOrganizationEquipmentBean> getHandleOrganizationEquipmentBean() {
        return handleOrganizationEquipmentBean;
    }

    public void setHandleOrganizationEquipmentBean(List<HandleOrganizationEquipmentBean> handleOrganizationEquipmentBean) {
        this.handleOrganizationEquipmentBean = handleOrganizationEquipmentBean;
    }

    public List<HandleOrganizationVehicleBean> getHandleOrganizationVehicleBean() {
        return handleOrganizationVehicleBean;
    }

    public void setHandleOrganizationVehicleBean(List<HandleOrganizationVehicleBean> handleOrganizationVehicleBean) {
        this.handleOrganizationVehicleBean = handleOrganizationVehicleBean;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

}
