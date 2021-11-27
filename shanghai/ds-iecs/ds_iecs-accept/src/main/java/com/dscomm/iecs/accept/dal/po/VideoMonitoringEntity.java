package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:视频监控
 *
 */
@Entity
@Table(name = "ZBZB_SPJKD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VideoMonitoringEntity extends BaseEntity {

    @Column(name = "BH", length = 100)
    private String videoCode;//视频编号

    @Column(name = "MC", length = 200)
    private String videoName;//视频名称

    @Column(name = "LX", length = 100)
    private String type; // 视频大类型

    @Column(name = "DW", length = 200)
    private String videoUnit;//维护单位名称

    @Column(name = "WZ", length = 400)
    private String videoAddress;//视频所在地址

    @Column(name = "GIS_X", length = 50 )
    private String longitude;//经度

    @Column(name = "GIS_Y", length = 50)
    private  String latitude;//纬度

    @Column(name = "GIS_H", length = 50)
    private String height;// 高度

    @Column(name = "JRFS", length = 100)
    private String videoAccess;//视频接入方式

    @Column(name = "LJDZ", length = 200)
    private String videoOpenUrl;//视频链接地址

    @Column(name = "SM", length = 400)
    private String videoExplain;//视频说明

    @Column(name = "SPLX", length = 100)
    private String videoType;//视频小类型

    @Column(name = "SPID", length = 100)
    private String videoId ;//视频ID

    @Column(name = "SJSPID", length = 100)
    private String videoParentId ;//上级视频ID

    @Column(name = "SSJGID", length = 100)
    private String videoOrganizationId;//所属机构ID

    @Column(name = "GLID", length = 100)
    private String videoGisRelationId ;//视频GIS关联ID

    @Column(name = "SFCKM", length = 10)
    private String whetherGarageVideo ;//是否车库视频

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本

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

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}
