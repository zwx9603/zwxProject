package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 一体化 字典数据
 */


@Entity
@Table(name = "DM_SJZD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DictionarySublistEntity  extends BaseEntity {

    @Column(name = "DM_SJZD_ID", length = 40 )
    private String DM_SJZD_ID;   //数据字典主表ID

    @Column(name = "DMZ", length = 100 )
    private String DMZ;          //代码值

    @Column(name = "DMMC", length = 200 )
    private String DMMC;         //代码名称

    @Column(name = "DMMPY", length = 400 )
    private String DMMPY;        //代码拼音

    @Column(name = "FDMID", length = 40 )
    private String FDMID;        //父字典ID

    @Column(name = "ZTA", length = 10  )
    private String ZTA;  //状态

    @Column(name = "MS", length = 400 )
    private String MS;           //描述

    @Column(name = "XSXH"  )
    private Integer XSXH;   //显示序号

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "CJZID" , length = 100 )
    private String CJZID;   //创建者ID

    @Column(name = "CZYID" , length = 100 )
    private String CZYID;   //操作者ID

    public String getDM_SJZD_ID() {
        return DM_SJZD_ID;
    }

    public void setDM_SJZD_ID(String DM_SJZD_ID) {
        this.DM_SJZD_ID = DM_SJZD_ID;
    }

    public String getDMZ() {
        return DMZ;
    }

    public void setDMZ(String DMZ) {
        this.DMZ = DMZ;
    }

    public String getDMMC() {
        return DMMC;
    }

    public void setDMMC(String DMMC) {
        this.DMMC = DMMC;
    }

    public String getDMMPY() {
        return DMMPY;
    }

    public void setDMMPY(String DMMPY) {
        this.DMMPY = DMMPY;
    }

    public String getFDMID() {
        return FDMID;
    }

    public void setFDMID(String FDMID) {
        this.FDMID = FDMID;
    }

    public String getZTA() {
        return ZTA;
    }

    public void setZTA(String ZTA) {
        this.ZTA = ZTA;
    }

    public String getMS() {
        return MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }

    public Integer getXSXH() {
        return XSXH;
    }

    public void setXSXH(Integer XSXH) {
        this.XSXH = XSXH;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getCJZID() {
        return CJZID;
    }

    public void setCJZID(String CJZID) {
        this.CJZID = CJZID;
    }

    public String getCZYID() {
        return CZYID;
    }

    public void setCZYID(String CZYID) {
        this.CZYID = CZYID;
    }
}
