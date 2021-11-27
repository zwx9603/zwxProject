package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 范围信息
 *
 */
public class RegionBean extends BaseBean {

    private String regionName ;// 范围名称

    private String longitude;// 经度

    private String latitude;// 纬度

    private String regionRange; // 范围信息

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

    public String getRegionRange() {
        return regionRange;
    }

    public void setRegionRange(String regionRange) {
        this.regionRange = regionRange;
    }

    
}
