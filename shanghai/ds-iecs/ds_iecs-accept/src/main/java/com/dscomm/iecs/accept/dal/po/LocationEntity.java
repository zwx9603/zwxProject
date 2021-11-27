package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:定位轨迹
 *
 * @author LiLu
 * Date Time 2019/8/14 0014 9:18
 */
@Entity
@Table(name = "jcj_dwgj")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LocationEntity extends BaseEntity {

    /**
     * 事件单编号
     */
    @Column(name = "sjdbh", length = 40)
    private String incidentId;
    /**
     * 受理单编号
     */
    @Column(name = "sldbh", length = 40)
    private String acceptId;
    /**
     * 定位类型
     */
    @Column(name = "dwlx", length = 50)
    private String locationType;
    /**
     * 坐标类型
     */
    @Column(name = "zblx", length = 2)
    private String coordinateType;
    /**
     * 经度
     */
    @Column(name = "xzb", length = 50)
    private String longitude;
    /**
     * 纬度
     */
    @Column(name = "yzb", length = 50)
    private String latitude;
    /**
     * 定位时间
     */
    @Column(name = "dwsj")
    private Long locationTime;
    /**
     * 定位内容
     */
    @Column(name = "dwnr", length = 400)
    private String locationContent;
    /**
     * 序号
     */
    @Column(name = "xh")
    private Integer order;
    /**
     * 地址
     */
    @Column(name = "dz", length = 200)
    private String address;
    /**
     * 轨迹类型 1:案件轨迹 2:报警人轨迹
     */
    @Column(name = "gjlx")
    private Integer trajectoryType;
    /**
     * 反馈单编号
     */
    @Column(name = "fkdbh", length = 40)
    private String feedbackId;
    /**
     * 地址类型 1:主地址 2:辅助地址2 3:辅助地址3
     */
    @Column(name = "dzlx")
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

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
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

    public String getFeedbackId() { return feedbackId; }

    public void setFeedbackId(String feedbackId) { this.feedbackId = feedbackId; }

    public Integer getAddressType() { return addressType; }

    public void setAddressType(Integer addressType) { this.addressType = addressType; }
}
