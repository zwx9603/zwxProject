package com.dscomm.iecs.integrate.service.udpBean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class UdpHeartContentVO {

    @XmlElement(name = "ID")
    private String ID;//心跳编号
    @XmlElement(name = "HeartTime")
    private String HeartTime;//心跳时间
    @XmlElement(name = "IP")
    private String IP;//心跳位置
    @XmlElement(name = "XZQH")
    private String XZQH;//行政区划

    @XmlTransient
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @XmlTransient
    public String getHeartTime() {
        return HeartTime;
    }

    public void setHeartTime(String heartTime) {
        HeartTime = heartTime;
    }

    @XmlTransient
    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    @XmlTransient
    public String getXZQH() {
        return XZQH;
    }

    public void setXZQH(String XZQH) {
        this.XZQH = XZQH;
    }
}
