package com.dscomm.iecs.accept.service.bean;


/**
 *  终端设备 实时位置信息
 */
public class DeviceLocationBean {

    private  String  deviceCode  ; //终端设备编号

    private String lon;// 经度

    private String lat;// 纬度

    private String  speed  ; //终端速度

    private String status ; //终端状态 0 下线  1 上线

    private String  time ; //时间


    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
