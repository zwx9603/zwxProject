package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:辅助地址
 *
 * @author XuHao
 * Date Time 2020/2/21 11:46
 */
@Entity
@Table(name = "jcj_fzdz")
public class AddressEntity extends BaseEntity {

    /**
     * 事件单编号
     */
    @Column(name = "sjdbh", length = 40)
    private String incidentId;
    /**
     * 地址
     */
    @Column(name = "dz", length = 200)
    private String address;
    /**
     * 经度
     */
    @Column(name = "xzb", length = 50)
    private String longitude;
    /**
     * 纬度
     */
    @Column(name = "yzb", length = 50)
    private String latitude;
    /**
     * 地址类型 0:定位标志物 1:主地址 2:辅助地址2 3:辅助地址3(默认为1)
     */
    @Column(name = "dzlx")
    private Integer addressType;
    /**
     * 保存时间
     */
    @Column(name = "bcsj")
    private Long saveTime;
    /**
     * 时间戳
     */
    @Column(name = "sjc")
    private Long sjc;

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
