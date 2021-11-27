package com.dscomm.iecs.integrate.service.udpBean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.NONE)
public class UdpCar119VO {

    @XmlElement(name="CLID")
    private String CLID;
    @XmlElement(name="CLMC")
    private String CLMC;
    @XmlElement(name="CPH")
    private String CPH;
    @XmlElement(name="CLLX")
    private String CLLX;
    @XmlElement(name="CLLXBM")
    private String CLLXBM;
    @XmlElement(name="XFJG")
    private String XFJG;
    @XmlElement(name="XFJGBM")
    private String XFJGBM;

    @XmlTransient
    public String getCLID() {
        return CLID;
    }

    public void setCLID(String CLID) {
        this.CLID = CLID;
    }

    @XmlTransient
    public String getCLMC() {
        return CLMC;
    }

    public void setCLMC(String CLMC) {
        this.CLMC = CLMC;
    }

    @XmlTransient
    public String getCPH() {
        return CPH;
    }

    public void setCPH(String CPH) {
        this.CPH = CPH;
    }

    @XmlTransient
    public String getCLLX() {
        return CLLX;
    }

    public void setCLLX(String CLLX) {
        this.CLLX = CLLX;
    }

    @XmlTransient
    public String getCLLXBM() {
        return CLLXBM;
    }

    public void setCLLXBM(String CLLXBM) {
        this.CLLXBM = CLLXBM;
    }

    @XmlTransient
    public String getXFJG() {
        return XFJG;
    }

    public void setXFJG(String XFJG) {
        this.XFJG = XFJG;
    }

    @XmlTransient
    public String getXFJGBM() {
        return XFJGBM;
    }

    public void setXFJGBM(String XFJGBM) {
        this.XFJGBM = XFJGBM;
    }
}
