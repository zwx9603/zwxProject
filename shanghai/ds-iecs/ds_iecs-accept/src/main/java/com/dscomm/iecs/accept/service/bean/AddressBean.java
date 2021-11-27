package com.dscomm.iecs.accept.service.bean;

/**
 * 描述:辅助地址
 *
 * @author XuHao
 * Date Time 2020/2/21 11:54
 */
public class AddressBean {
    /**
     * 流水号
     */
    private String id;
    /**
     * 事件单编号
     */
    private String incidentId;
    /**
     * 地址
     */
    private String address;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 地址类型 0:定位标志物 1:主地址 2:辅助地址2 3:辅助地址3(默认为1)
     */
    private Integer addressType;
    /**
     * 保存时间
     */
    private Long saveTime;
    /**
     * 时间戳
     */
    private Long sjc;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getIncidentId() { return incidentId; }

    public void setIncidentId(String incidentId) { this.incidentId = incidentId; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getLatitude() { return latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public Integer getAddressType() { return addressType; }

    public void setAddressType(Integer addressType) { this.addressType = addressType; }

    public Long getSaveTime() { return saveTime; }

    public void setSaveTime(Long saveTime) { this.saveTime = saveTime; }

    public Long getSjc() { return sjc; }

    public void setSjc(Long sjc) { this.sjc = sjc; }
}
