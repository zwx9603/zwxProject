package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 定位记录
 * */

@Entity
@Table(name = "JCJ_DWJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LocationRecordEntity extends BaseEntity {

    @Column(name = "xh")
    private Integer sort;//序号

    @Column(name = "dwlx")
    private String locationType;//定位类型

    @Column(name = "dwsj")
    private Long locationTime;//定位时间

    @Column(name = "gis_x")
    private Double gis_x;//维度

    @Column(name = "gis_y")
    private Double gis_y;//经度

    @Column(name = "bz")
    private String remark;//备注

    @Column(name = "ajid")
    private String incidentId;//案件id

    @Column(name = "dz")
    private String address;//地址
    @Column(name = "cjbz")
    private Integer acquisitionFlag;//采集标志 1已采用 0未采用
    @Column(name = "ksst")
    private String quickPicture;//快速上图

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Long getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(Long locationTime) {
        this.locationTime = locationTime;
    }

    public Double getGis_x() {
        return gis_x;
    }

    public void setGis_x(Double gis_x) {
        this.gis_x = gis_x;
    }

    public Double getGis_y() {
        return gis_y;
    }

    public void setGis_y(Double gis_y) {
        this.gis_y = gis_y;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAcquisitionFlag() {
        return acquisitionFlag;
    }

    public void setAcquisitionFlag(Integer acquisitionFlag) {
        this.acquisitionFlag = acquisitionFlag;
    }

    public String getQuickPicture() {
        return quickPicture;
    }

    public void setQuickPicture(String quickPicture) {
        this.quickPicture = quickPicture;
    }
}
