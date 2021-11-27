package com.dscomm.iecs.accept.restful.vo.UdpVO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class UdpIncidentReceiveStateContentVO {

    @XmlElement(name = "ZQBH")
    private String ZQBH;//119接警单编号
    @XmlElement(name = "ZT")
    private String ZT;//状态（YDD：已到达，YJS：已接收）
    @XmlElement(name = "JJDBH")
    private String JJDBH;// 110接警单编号
    @XmlElement(name = "XZQH")
    private String XZQH;//行政区划

    @XmlTransient
    public String getJJDBH() {
        return JJDBH;
    }

    public void setJJDBH(String JJDBH) {
        this.JJDBH = JJDBH;
    }

    @XmlTransient
    public String getZQBH() {
        return ZQBH;
    }

    public void setZQBH(String ZQBH) {
        this.ZQBH = ZQBH;
    }

    @XmlTransient
    public String getZT() {
        return ZT;
    }

    public void setZT(String ZT) {
        this.ZT = ZT;
    }

    @XmlTransient
    public String getXZQH() {
        return XZQH;
    }

    public void setXZQH(String XZQH) {
        this.XZQH = XZQH;
    }
}
