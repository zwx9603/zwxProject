package com.dscomm.iecs.accept.service.bean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:定位轨迹
 *
 * @author LiLu
 * Date Time 2019/8/14 0014 9:39
 */
public class LocationBean extends BaseBean {

    /**
     * 事件单编号
     */
    private String incidentId;
    /**
     * 受理单编号
     */
    private String acceptId;
    /**
     * 定位类型
     */
    private String locationType;
    /**
     * 定位类型name
     */
    private String locationTypeName;
    /**
     * 坐标类型
     */
    private String coordinateType;
    /**
     * 坐标类型name
     */
    private String coordinateTypeName;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 定位时间
     */
    private Long locationTime;
    /**
     * 定位内容
     */
    private String locationContent;
    /**
     * 序号
     */
    private Integer order;
    /**
     * 地址
     */
    private String address;
    /**
     * 轨迹类型 1:案件轨迹 2:报警人轨迹
     */
    private Integer trajectoryType;
    /**
     * 轨迹类型name
     */
    private String trajectoryTypeName;
    /**
     * 反馈单编号
     */
    private String feedbackId;
    /**
     * 地址类型 1:主地址 2:辅助地址2 3:辅助地址3
     */
    private Integer addressType;


    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(String acceptId) {
        this.acceptId = acceptId;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocationTypeName() {
        return locationTypeName;
    }

    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    public String getCoordinateTypeName() {
        return coordinateTypeName;
    }

    public void setCoordinateTypeName(String coordinateTypeName) {
        this.coordinateTypeName = coordinateTypeName;
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

    public Long getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(Long locationTime) {
        this.locationTime = locationTime;
    }

    public String getLocationContent() {
        return locationContent;
    }

    public void setLocationContent(String locationContent) {
        this.locationContent = locationContent;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTrajectoryType() {
        return trajectoryType;
    }

    public void setTrajectoryType(Integer trajectoryType) {
        this.trajectoryType = trajectoryType;
    }

    public String getTrajectoryTypeName() {
        return trajectoryTypeName;
    }

    public void setTrajectoryTypeName(String trajectoryTypeName) {
        this.trajectoryTypeName = trajectoryTypeName;
    }

    public String getFeedbackId() { return feedbackId; }

    public void setFeedbackId(String feedbackId) { this.feedbackId = feedbackId; }

    public Integer getAddressType() { return addressType; }

    public void setAddressType(Integer addressType) { this.addressType = addressType; }
}
