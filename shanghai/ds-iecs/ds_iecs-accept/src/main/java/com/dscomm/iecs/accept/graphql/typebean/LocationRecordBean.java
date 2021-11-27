package com.dscomm.iecs.accept.graphql.typebean;


/**
 * 定位记录
 * */
public class LocationRecordBean {
    private String id;

    private Integer sort;//序号

    private String locationType;//定位类型code
    private String locationTypeName;//定位类型名称

    private Long locationTime;//定位时间

    private Double gis_x;//维度

    private Double gis_y;//经度

    private String remark;//备注

    private String incidentId;//案件id

    private String address;//地址

    private Long createdTime;//创建时间
    private Long updatedTime;//更新时间
    private Integer acquisitionFlag;//采集标志 1已采用 0未采用
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

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationTypeName() {
        return locationTypeName;
    }

    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
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
