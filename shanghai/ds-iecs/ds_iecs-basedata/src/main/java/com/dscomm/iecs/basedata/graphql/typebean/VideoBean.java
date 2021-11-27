package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 视频监控
 *
 */
public class VideoBean extends BaseBean {

    private String idCode; // 视频监控_通用唯一识别码

    private String videoCode ;//视频编号

    private String videoName;//视频名称

    private String videoTypeCode ;//视频监控类型代码

    private String videoTypeName ;//视频监控类型名称

    private String videoAddress ;//地点名称

    private String videoURL ;//视频连接地址

    private String videoDesc ;//简要情况

    private String videoDefinitionCode;//视频清晰度类型代码

    private String videoDefinitionName;//视频清晰度类型名称

    private String videoAccessCode ;//视频接入方式代码

    private String videoAccessName ;//视频接入方式名称

    private String state;//视频状态

    private String longitude;//经度

    private String latitude;//纬度

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoTypeCode() {
        return videoTypeCode;
    }

    public void setVideoTypeCode(String videoTypeCode) {
        this.videoTypeCode = videoTypeCode;
    }

    public String getVideoTypeName() {
        return videoTypeName;
    }

    public void setVideoTypeName(String videoTypeName) {
        this.videoTypeName = videoTypeName;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoDefinitionCode() {
        return videoDefinitionCode;
    }

    public void setVideoDefinitionCode(String videoDefinitionCode) {
        this.videoDefinitionCode = videoDefinitionCode;
    }

    public String getVideoDefinitionName() {
        return videoDefinitionName;
    }

    public void setVideoDefinitionName(String videoDefinitionName) {
        this.videoDefinitionName = videoDefinitionName;
    }

    public String getVideoAccessCode() {
        return videoAccessCode;
    }

    public void setVideoAccessCode(String videoAccessCode) {
        this.videoAccessCode = videoAccessCode;
    }

    public String getVideoAccessName() {
        return videoAccessName;
    }

    public void setVideoAccessName(String videoAccessName) {
        this.videoAccessName = videoAccessName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}
