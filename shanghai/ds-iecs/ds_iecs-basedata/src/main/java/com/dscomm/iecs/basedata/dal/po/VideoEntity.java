package com.dscomm.iecs.basedata.dal.po;



import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 视频监控
 *
 */
@Entity
@Table(name = "JCJ_SPJK")
public class VideoEntity extends BaseEntity {

    @Column(name = "SPJK_TYWYSBM", length = 32 )
    private String idCode; //todo  字段 视频监控_通用唯一识别码

    @Column(name="SPBH", length =  100 )
    private String videoCode ;//视频编号

    @Column(name="MC", length =  200 )
    private String videoName;//视频名称

    @Column(name="SPJKLXDM" , length =  100 )
    private String videoTypeCode ;//视频监控类型代码

    @Column(name="DDMC" , length =  400 )
    private String videoAddress ;//地点名称

    @Column(name="SPLJDZ", length =  100 )
    private String videoURL ;//视频连接地址

    @Column(name="JYQK", length =  1000 )
    private String videoDesc ;//简要情况

    @Column(name="SPLXDM", length =  100 )
    private String videoDefinitionCode;//视频清晰度类型代码

    @Column(name="SPJRFSDM", length =  100 )
    private String videoAccessCode ;//视频接入方式代码

    @Column(name="ZT", length =  100 )
    private String state;//视频状态

    @Column(name="DQJD", length =  50 )
    private String longitude;//经度

    @Column(name="DQWD", length =  50 )
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

    public String getVideoAccessCode() {
        return videoAccessCode;
    }

    public void setVideoAccessCode(String videoAccessCode) {
        this.videoAccessCode = videoAccessCode;
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
