package com.dscomm.iecs.accept.restful.vo.UdpVO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class UdpHelpContentVO {

    @XmlElement(name = "JJDBH")
    private String JJDBH;//110接警单编号
    @XmlElement(name = "XZNR")
    private String XZNR;//协作内容
    @XmlElement(name = "QQSJ")
    private String QQSJ;//请求时间
    @XmlElement(name = "QQR")
    private String QQR;//请求人
    @XmlElement(name = "QQZX")
    private String QQZX;//请求坐席
    @XmlElement(name = "XZQH")
    private String XZQH;//行政区划
    @XmlElement(name = "ZQBH")
    private String ZQBH;//119灾情编号

    @XmlTransient
    public String getJJDBH() {
        return JJDBH;
    }

    public void setJJDBH(String JJDBH) {
        this.JJDBH = JJDBH;
    }

    @XmlTransient
    public String getXZNR() {
        return XZNR;
    }

    public void setXZNR(String XZNR) {
        this.XZNR = XZNR;
    }

    @XmlTransient
    public String getQQSJ() {
        return QQSJ;
    }

    public void setQQSJ(String QQSJ) {
        this.QQSJ = QQSJ;
    }

    @XmlTransient
    public String getQQR() {
        return QQR;
    }

    public void setQQR(String QQR) {
        this.QQR = QQR;
    }

    @XmlTransient
    public String getQQZX() {
        return QQZX;
    }

    public void setQQZX(String QQZX) {
        this.QQZX = QQZX;
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
}
