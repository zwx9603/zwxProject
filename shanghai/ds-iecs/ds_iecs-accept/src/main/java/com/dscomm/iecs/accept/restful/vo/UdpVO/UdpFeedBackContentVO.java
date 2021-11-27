package com.dscomm.iecs.accept.restful.vo.UdpVO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class UdpFeedBackContentVO {

    @XmlElement(name = "ZQBH")
    private String ZQBH;
    @XmlElement(name = "DHHM")
    private String DHHM;
    @XmlElement(name = "YHXM")
    private String YHXM;
    @XmlElement(name = "ZJDZ")
    private String ZJDZ;
    @XmlElement(name = "HRSJ")
    private String HRSJ;
    @XmlElement(name = "HZDZ")
    private String HZDZ;
    @XmlElement(name = "HZLX")
    private String HZLX;
    @XmlElement(name = "ZHDJ")
    private String ZHDJ;
    @XmlElement(name = "JZLX")
    private String JZLX;
    @XmlElement(name = "QHLC")
    private String QHLC;
    @XmlElement(name = "SFRYBK")
    private String SFRYBK;
    @XmlElement(name = "X")
    private String X;
    @XmlElement(name = "Y")
    private String Y;
    @XmlElement(name = "CDSJ")
    private String CDSJ;
    @XmlElement(name = "DCSJ")
    private String DCSJ;
    @XmlElement(name = "JSSJ")
    private String JSSJ;
    @XmlElement(name = "RSMJ")
    private String RSMJ;
    @XmlElement(name = "RSWZ")
    private String RSWZ;
    @XmlElement(name = "SSRS")
    private String SSRS;
    @XmlElement(name = "SWRS")
    private String SWRS;
    @XmlElement(name = "JJDBH110")
    private String JJDBH110;// 110接警单单编号
    @XmlElement(name = "XZQH")
    private String XZQH;//行政区划

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
    public String getZQBH() {
        return ZQBH;
    }

    public void setZQBH(String ZQBH) {
        this.ZQBH = ZQBH;
    }

    @XmlTransient
    public String getDHHM() {
        return DHHM;
    }

    public void setDHHM(String DHHM) {
        this.DHHM = DHHM;
    }

    @XmlTransient
    public String getYHXM() {
        return YHXM;
    }

    public void setYHXM(String YHXM) {
        this.YHXM = YHXM;
    }

    @XmlTransient
    public String getZJDZ() {
        return ZJDZ;
    }

    public void setZJDZ(String ZJDZ) {
        this.ZJDZ = ZJDZ;
    }

    @XmlTransient
    public String getHRSJ() {
        return HRSJ;
    }

    public void setHRSJ(String HRSJ) {
        this.HRSJ = HRSJ;
    }

    @XmlTransient
    public String getHZDZ() {
        return HZDZ;
    }

    public void setHZDZ(String HZDZ) {
        this.HZDZ = HZDZ;
    }

    @XmlTransient
    public String getHZLX() {
        return HZLX;
    }

    public void setHZLX(String HZLX) {
        this.HZLX = HZLX;
    }

    @XmlTransient
    public String getZHDJ() {
        return ZHDJ;
    }

    public void setZHDJ(String ZHDJ) {
        this.ZHDJ = ZHDJ;
    }

    @XmlTransient
    public String getJZLX() {
        return JZLX;
    }

    public void setJZLX(String JZLX) {
        this.JZLX = JZLX;
    }

    @XmlTransient
    public String getQHLC() {
        return QHLC;
    }

    public void setQHLC(String QHLC) {
        this.QHLC = QHLC;
    }

    @XmlTransient
    public String getSFRYBK() {
        return SFRYBK;
    }

    public void setSFRYBK(String SFRYBK) {
        this.SFRYBK = SFRYBK;
    }

    @XmlTransient
    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    @XmlTransient
    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    @XmlTransient
    public String getCDSJ() {
        return CDSJ;
    }

    public void setCDSJ(String CDSJ) {
        this.CDSJ = CDSJ;
    }

    @XmlTransient
    public String getDCSJ() {
        return DCSJ;
    }

    public void setDCSJ(String DCSJ) {
        this.DCSJ = DCSJ;
    }

    @XmlTransient
    public String getJSSJ() {
        return JSSJ;
    }

    public void setJSSJ(String JSSJ) {
        this.JSSJ = JSSJ;
    }

    @XmlTransient
    public String getRSMJ() {
        return RSMJ;
    }

    public void setRSMJ(String RSMJ) {
        this.RSMJ = RSMJ;
    }

    @XmlTransient
    public String getRSWZ() {
        return RSWZ;
    }

    public void setRSWZ(String RSWZ) {
        this.RSWZ = RSWZ;
    }

    @XmlTransient
    public String getSSRS() {
        return SSRS;
    }

    public void setSSRS(String SSRS) {
        this.SSRS = SSRS;
    }

    @XmlTransient
    public String getSWRS() {
        return SWRS;
    }

    public void setSWRS(String SWRS) {
        this.SWRS = SWRS;
    }
}
