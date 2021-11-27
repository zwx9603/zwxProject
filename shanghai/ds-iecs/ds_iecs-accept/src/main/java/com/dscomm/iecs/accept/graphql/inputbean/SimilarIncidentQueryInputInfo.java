package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 机构查询参数
 */
public class SimilarIncidentQueryInputInfo {

    private String longitude  ; //经度

    private String latitude ; //纬度

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
