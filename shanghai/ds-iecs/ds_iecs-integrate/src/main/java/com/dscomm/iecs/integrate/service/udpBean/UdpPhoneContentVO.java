package com.dscomm.iecs.integrate.service.udpBean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class UdpPhoneContentVO {

    @XmlElement(name = "DHHM")
    private String DHHM;//电话号码
    @XmlElement(name = "YHXM")
    private String YHXM;//用户姓名
    @XmlElement(name = "ZJDZ")
    private String ZJDZ;//装机地址
    @XmlElement(name = "HRSJ")
    private String HRSJ;//呼入时间
    @XmlElement(name = "X")
    private String X;//经度
    @XmlElement(name = "Y")
    private String Y;//纬度
    @XmlElement(name = "BJHM")
    private String BJHM;//被叫号码
    @XmlElement(name = "ZXBH")
    private String ZXBH;//坐席分机号


    @XmlElement(name = "XZQH")
    private String XZQH;//行政区划

    @XmlTransient
    public String getXZQH() {
        return XZQH;
    }

    public void setXZQH(String XZQH) {
        this.XZQH = XZQH;
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
    public String getBJHM() {
        return BJHM;
    }

    public void setBJHM(String BJHM) {
        this.BJHM = BJHM;
    }

    @XmlTransient
    public String getZXBH() {
        return ZXBH;
    }

    public void setZXBH(String ZXBH) {
        this.ZXBH = ZXBH;
    }



    public String toStringReceive(){
        return  "\t\t<DHHM>"+ DHHM +"</DHHM>\n"+
                "\t\t<YHXM>"+ YHXM +"</YHXM>\n"+
                "\t\t<ZJDZ>"+ ZJDZ +"</ZJDZ>\n"+
                "\t\t<HRSJ>"+ HRSJ +"</HRSJ>\n"+
                "\t\t<X>"+ X +"</X>\n"+
                "\t\t<Y>"+ Y +"</Y>\n"+
                "\t\t<BJHM>"+ BJHM +"</BJHM>\n"+
                "\t\t<ZXBH>"+ ZXBH +"</ZXBH>\n"+
                "\t\t<XZQH>"+ XZQH +"</XZQH>\n";
    }

    public String toStringSend(){
        return  "\t\t<DHHM>"+ DHHM +"</DHHM>\n"+
                "\t\t<YHXM>"+ YHXM +"</YHXM>\n"+
                "\t\t<ZJDZ>"+ ZJDZ +"</ZJDZ>\n"+
                "\t\t<HRSJ>"+ HRSJ +"</HRSJ>\n"+
                "\t\t<X>"+ X +"</X>\n"+
                "\t\t<Y>"+ Y +"</Y>\n"+
                "\t\t<BJHM>"+ BJHM +"</BJHM>\n"+
                "\t\t<ZXBH>"+ ZXBH +"</ZXBH>\n";
    }
}
