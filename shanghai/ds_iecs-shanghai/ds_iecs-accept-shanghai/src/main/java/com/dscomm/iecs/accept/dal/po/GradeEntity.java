package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
* @author Zhao Wenxue
* */
@Entity
@Table(name = "jcj_djdd")
public class GradeEntity extends BaseEntity {

    @Column(name = "sl")
    private String sl; //调派数量

    @Column(name = "czdx")
    private String czdx; //处置对象

    @Column(name = "ajdj")
    private String ajdj; //案件等级

    @Column(name = "ajlx")
    private String ajlx; //案件类型

    @Column(name = "xfjg")
    private String xfjg; //消防机构

    @Column(name = "bz")
    private String bz; //备注

    @Column(name = "cllx")
    private String cllx; //车辆类型

    @Column(name = "mc")
    private String mc; //等级调派名称

    @Column(name = "zzsj")
    private String zzsj; //制作时间

    @Column(name = "zzrid")
    private String zzrid; //制作人id

    @Column(name = "zzrxm")
    private String zzrxm; //制作人姓名

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getCzdx() {
        return czdx;
    }

    public void setCzdx(String czdx) {
        this.czdx = czdx;
    }

    public String getAjdj() {
        return ajdj;
    }

    public void setAjdj(String ajdj) {
        this.ajdj = ajdj;
    }

    public String getAjlx() {
        return ajlx;
    }

    public void setAjlx(String ajlx) {
        this.ajlx = ajlx;
    }

    public String getXfjg() {
        return xfjg;
    }

    public void setXfjg(String xfjg) {
        this.xfjg = xfjg;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getZzsj() {
        return zzsj;
    }

    public void setZzsj(String zzsj) {
        this.zzsj = zzsj;
    }

    public String getZzrid() {
        return zzrid;
    }

    public void setZzrid(String zzrid) {
        this.zzrid = zzrid;
    }

    public String getZzrxm() {
        return zzrxm;
    }

    public void setZzrxm(String zzrxm) {
        this.zzrxm = zzrxm;
    }
}
