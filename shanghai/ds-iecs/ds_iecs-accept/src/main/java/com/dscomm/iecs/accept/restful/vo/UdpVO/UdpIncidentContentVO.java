package com.dscomm.iecs.accept.restful.vo.UdpVO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class UdpIncidentContentVO {

    @XmlElement(name = "ZQBH")
    private String ZQBH;//119灾情编号
    @XmlElement(name = "JJDBH")
    private String JJDBH;//发送端接警单编号
    @XmlElement(name = "DHHM")
    private String DHHM;//电话号码
    @XmlElement(name = "YHXM")
    private String YHXM;//用户姓名
    @XmlElement(name = "ZJDZ")
    private String ZJDZ;//装机地址
    @XmlElement(name = "HRSJ")
    private String HRSJ;//呼入时间
    @XmlElement(name = "HZDZ")
    private String HZDZ;//火灾地址
    @XmlElement(name = "HZLX")
    private String HZLX;//火灾类型
    @XmlElement(name = "RSWZ")
    private String RSWZ;//燃烧物质
    @XmlElement(name = "ZHDJ")
    private String ZHDJ;//灾害等级
    @XmlElement(name = "JZLX")
    private String JZLX;//建筑类型
    @XmlElement(name = "QHLC")
    private String QHLC;//起火楼层
    @XmlElement(name = "SFRYBK")
    private String SFRYBK;//是否人员被困
    @XmlElement(name = "X")
    private String X;//经度
    @XmlElement(name = "Y")
    private String Y;//纬度
    @XmlElement(name = "JYJQ")
    private String JYJQ;//简要警情
    @XmlElement(name = "XZQH")
    private String XZQH;//行政区划


    @XmlTransient
    public String getZQBH() {
        return ZQBH;
    }

    public void setZQBH(String ZQBH) {
        this.ZQBH = ZQBH;
    }

    @XmlTransient
    public String getJJDBH() {
        return JJDBH;
    }

    public void setJJDBH(String JJDBH) {
        this.JJDBH = JJDBH;
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
    public String getRSWZ() {
        return RSWZ;
    }

    public void setRSWZ(String RSWZ) {
        this.RSWZ = RSWZ;
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
    public String getJYJQ() {
        return JYJQ;
    }

    public void setJYJQ(String JYJQ) {
        this.JYJQ = JYJQ;
    }

    @XmlTransient
    public String getXZQH() {
        return XZQH;
    }

    public void setXZQH(String XZQH) {
        this.XZQH = XZQH;
    }

    public String toStringReceive(){
        return  "\t\t<JJDBH>"+ JJDBH +"</JJDBH>\n"+
                "\t\t<DHHM>"+ DHHM +"</DHHM>\n"+
                "\t\t<YHXM>"+ YHXM +"</YHXM>\n"+
                "\t\t<ZJDZ>"+ ZJDZ +"</ZJDZ>\n"+
                "\t\t<HRSJ>"+ HRSJ +"</HRSJ>\n"+
                "\t\t<HZDZ>"+ HZDZ +"</HZDZ>\n"+
                "\t\t<HZLX>"+ HZLX +"</HZLX>\n"+
                "\t\t<RSWZ>"+ RSWZ +"</RSWZ>\n"+
                "\t\t<ZHDJ>"+ ZHDJ +"</ZHDJ>\n"+
                "\t\t<JZLX>"+ JZLX +"</JZLX>\n"+
                "\t\t<QHLC>"+ QHLC +"</QHLC>\n"+
                "\t\t<SFRYBK>"+ SFRYBK +"</SFRYBK>\n"+
                "\t\t<X>"+ X +"</X>\n"+
                "\t\t<Y>"+ Y +"</Y>\n"+
                "\t\t<JYJQ>"+ JYJQ +"</JYJQ>\n"+
                "\t\t<XZQH>"+ XZQH +"</XZQH>\n";
    }

    public String toStringSend(){
        return  "\t\t<JJDBH>"+ JJDBH +"</JJDBH>\n"+
                "\t\t<DHHM>"+ DHHM +"</DHHM>\n"+
                "\t\t<YHXM>"+ YHXM +"</YHXM>\n"+
                "\t\t<ZJDZ>"+ ZJDZ +"</ZJDZ>\n"+
                "\t\t<HRSJ>"+ HRSJ +"</HRSJ>\n"+
                "\t\t<HZDZ>"+ HZDZ +"</HZDZ>\n"+
                "\t\t<HZLX>"+ HZLX +"</HZLX>\n"+
                "\t\t<RSWZ>"+ RSWZ +"</RSWZ>\n"+
                "\t\t<ZHDJ>"+ ZHDJ +"</ZHDJ>\n"+
                "\t\t<JZLX>"+ JZLX +"</JZLX>\n"+
                "\t\t<QHLC>"+ QHLC +"</QHLC>\n"+
                "\t\t<SFRYBK>"+ SFRYBK +"</SFRYBK>\n"+
                "\t\t<X>"+ X +"</X>\n"+
                "\t\t<Y>"+ Y +"</Y>\n"+
                "\t\t<JYJQ>"+ JYJQ +"</JYJQ>\n";
    }
}
