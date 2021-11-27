package com.dscomm.iecs.base.service.bean;

/**
 * 计算 经纬度 距离 范围类
 */
public class LocationRangeBean {

    private double Lat ;  //纬度
    private double Lng  ; //经度
    private Integer radius ; //距离

    private double maxLat  ; //最大纬度
    private double minLat  ; //最小纬度

    private double maxLng  ;//最大经度
    private double minLng  ;//最小经度

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public double getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(double maxLng) {
        this.maxLng = maxLng;
    }

    public double getMinLng() {
        return minLng;
    }

    public void setMinLng(double minLng) {
        this.minLng = minLng;
    }
}
