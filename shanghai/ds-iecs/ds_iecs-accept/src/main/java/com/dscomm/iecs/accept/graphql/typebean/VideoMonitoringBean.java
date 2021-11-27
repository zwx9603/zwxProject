package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:视频监控
 *
 */
public class VideoMonitoringBean extends BaseBean {

    private String videoCode;//视频编号

    private String videoName;//视频名称

    private String type; // 视频大类型

    private String videoUnit;//维护单位名称

    private String videoAddress;//视频所在地址

    private String longitude;//经度

    private  String latitude;//纬度

    private String height;// 高度

    private String videoAccess;//视频接入方式

    private String videoOpenUrl;//视频链接地址

    private String videoExplain;//视频说明

    private String videoType;//视频小类型

    private String videoId ;//视频ID

    private String videoParentId ;//上级视频ID

    private String videoOrganizationId;//所属机构ID

    private String videoOrganizationName;//所属机构名称

    private String videoGisRelationId ;//视频GIS关联ID

    private String whetherGarageVideo ;//是否车库视频

    private String remarks; // 备注


    public String getVideoOrganizationName() {
        return videoOrganizationName;
    }

    public void setVideoOrganizationName(String videoOrganizationName) {
        this.videoOrganizationName = videoOrganizationName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideoUnit() {
        return videoUnit;
    }

    public void setVideoUnit(String videoUnit) {
        this.videoUnit = videoUnit;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getVideoAccess() {
        return videoAccess;
    }

    public void setVideoAccess(String videoAccess) {
        this.videoAccess = videoAccess;
    }

    public String getVideoOpenUrl() {
        return videoOpenUrl;
    }

    public void setVideoOpenUrl(String videoOpenUrl) {
        this.videoOpenUrl = videoOpenUrl;
    }

    public String getVideoExplain() {
        return videoExplain;
    }

    public void setVideoExplain(String videoExplain) {
        this.videoExplain = videoExplain;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoParentId() {
        return videoParentId;
    }

    public void setVideoParentId(String videoParentId) {
        this.videoParentId = videoParentId;
    }

    public String getVideoOrganizationId() {
        return videoOrganizationId;
    }

    public void setVideoOrganizationId(String videoOrganizationId) {
        this.videoOrganizationId = videoOrganizationId;
    }

    public String getVideoGisRelationId() {
        return videoGisRelationId;
    }

    public void setVideoGisRelationId(String videoGisRelationId) {
        this.videoGisRelationId = videoGisRelationId;
    }

    public String getWhetherGarageVideo() {
        return whetherGarageVideo;
    }

    public void setWhetherGarageVideo(String whetherGarageVideo) {
        this.whetherGarageVideo = whetherGarageVideo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
