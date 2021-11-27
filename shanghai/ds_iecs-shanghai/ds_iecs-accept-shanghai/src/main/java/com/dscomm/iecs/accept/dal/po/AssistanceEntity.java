package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
* @author Zhao Wenxue
* */
@Entity
@Table(name="zbzb_xfjgplyxj")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AssistanceEntity extends BaseEntity {

    @Column(name = "cszt")
    private Integer cszt; //传输状态

    @Column(name = "jksjbb")
    private String jksjbb; //基础数据版本

    @Column(name = "jlzt")
    private Integer jlzt; //记录状态

    @Column(name = "sjbb")
    private String sjbb; //数据库版本

    @Column(name = "sjc")
    private Integer sjc; //时间戳

    @Column(name = "ywxtbsid", length = 100)
    private String ywxtbsid; //业务系统部署ID

    @Column(name = "jb")
    private String jb; //毗邻级别

    @Column(name = "zgdwdm", length = 32)
    private String zgdwdm; //主管单位代码

    @Column(name = "pldwdm", length = 32)
    private String pldwdm; //毗邻单位代码

    @Column(name = "xfjgdm", length = 32)
    private String xfjgdm; //消防机构代码

    @Column(name = "yxj")
    private Float yxj; //优先级

    @Column(name = "bz", length = 400)
    private String bz; //备注

    @Column(name = "xcjl", length = 50)
    private String xcjl; // 行车距离

    public Integer getCszt() {
        return cszt;
    }

    public void setCszt(Integer cszt) {
        this.cszt = cszt;
    }

    public String getJksjbb() {
        return jksjbb;
    }

    public void setJksjbb(String jksjbb) {
        this.jksjbb = jksjbb;
    }

    public Integer getJlzt() {
        return jlzt;
    }

    public void setJlzt(Integer jlzt) {
        this.jlzt = jlzt;
    }

    public String getSjbb() {
        return sjbb;
    }

    public void setSjbb(String sjbb) {
        this.sjbb = sjbb;
    }

    public Integer getSjc() {
        return sjc;
    }

    public void setSjc(Integer sjc) {
        this.sjc = sjc;
    }

    public String getYwxtbsid() {
        return ywxtbsid;
    }

    public void setYwxtbsid(String ywxtbsid) {
        this.ywxtbsid = ywxtbsid;
    }

    public String getJb() {
        return jb;
    }

    public void setJb(String jb) {
        this.jb = jb;
    }

    public String getZgdwdm() {
        return zgdwdm;
    }

    public void setZgdwdm(String zgdwdm) {
        this.zgdwdm = zgdwdm;
    }

    public String getPldwdm() {
        return pldwdm;
    }

    public void setPldwdm(String pldwdm) {
        this.pldwdm = pldwdm;
    }

    public String getXfjgdm() {
        return xfjgdm;
    }

    public void setXfjgdm(String xfjgdm) {
        this.xfjgdm = xfjgdm;
    }

    public Float getYxj() {
        return yxj;
    }

    public void setYxj(Float yxj) {
        this.yxj = yxj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getXcjl() {
        return xcjl;
    }

    public void setXcjl(String xcjl) {
        this.xcjl = xcjl;
    }
}

