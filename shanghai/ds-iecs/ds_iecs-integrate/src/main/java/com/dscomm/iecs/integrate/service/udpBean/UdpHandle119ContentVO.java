package com.dscomm.iecs.integrate.service.udpBean;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public class UdpHandle119ContentVO {

    @XmlElement(name = "ZQBH")
    private String ZQBH;//119灾情编号
    @XmlElement(name = "CJBH")
    private String CJBH;//119处警编号
    @XmlElement(name = "CJSJ")
    private String CJSJ;//处警时间
    @XmlElement(name = "JJDBH110")
    private String JJDBH110;// 110接警单单编号
    @XmlElement(name = "XZQH")
    private String XZQH;//行政区划

    @XmlElementWrapper(name="CLXX")
    @XmlElement(name="CL")
    private List<UdpCar119VO> CJDW;



    @XmlTransient
    public String getJJDBH110() {
        return JJDBH110;
    }

    public void setJJDBH110(String JJDBH110) {
        this.JJDBH110 = JJDBH110;
    }

    @XmlTransient
    public String getXZQH() {
        return XZQH;
    }

    public void setXZQH(String XZQH) {
        this.XZQH = XZQH;
    }

    @XmlTransient
    public List<UdpCar119VO> getCJDW() {
        return CJDW;
    }

    public void setCJDW(List<UdpCar119VO> CJDW) {
        this.CJDW = CJDW;
    }

    @XmlTransient
    public String getZQBH() {
        return ZQBH;
    }

    public void setZQBH(String ZQBH) {
        this.ZQBH = ZQBH;
    }

    @XmlTransient
    public String getCJBH() {
        return CJBH;
    }

    public void setCJBH(String CJBH) {
        this.CJBH = CJBH;
    }

    @XmlTransient
    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    @Override
    public String toString() {
        return "{" +
                "CJDW=" + CJDW +
                '}';
    }
}
