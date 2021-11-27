package com.dscomm.iecs.integrate.service.udpBean;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public class UdpHandle110ContentVO {

    @XmlElement(name = "JJDBH")
    private String JJDBH;//接警单编号
    @XmlElement(name = "CJDBH")
    private String CJDBH;//处警单编号
    @XmlElement(name = "CJSJ")
    private String CJSJ;//处警时间
    @XmlElement(name = "CJNR")
    private String CJNR;//处警内容

    @XmlElementWrapper(name="CJDW")
    @XmlElement(name="DW")
    private List<UdpUnit110VO> CJDW;

    @XmlTransient
    public List<UdpUnit110VO> getCJDW() {
        return CJDW;
    }

    public void setCJDW(List<UdpUnit110VO> CJDW) {
        this.CJDW = CJDW;
    }

    @XmlTransient
    public String getJJDBH() {
        return JJDBH;
    }

    public void setJJDBH(String JJDBH) {
        this.JJDBH = JJDBH;
    }

    @XmlTransient
    public String getCJDBH() {
        return CJDBH;
    }

    public void setCJDBH(String CJDBH) {
        this.CJDBH = CJDBH;
    }

    @XmlTransient
    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    @XmlTransient
    public String getCJNR() {
        return CJNR;
    }

    public void setCJNR(String CJNR) {
        this.CJNR = CJNR;
    }

    @Override
    public String toString() {
        return "{" +
                "JJDBH=" + JJDBH+"\t"+
                "CJDBH="+CJDBH+"\t"+
                "CJSJ="+CJSJ+"\t"+
                "CJNR="+CJNR+"\t"+
                "CJDW=" + CJDW +"\t"+
                '}';
    }
}
