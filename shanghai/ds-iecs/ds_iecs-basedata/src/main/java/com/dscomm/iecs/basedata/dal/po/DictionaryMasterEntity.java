package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 一体化 字典数据主表
 */

@Entity
@Table(name = "DM_SJZDZB")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DictionaryMasterEntity extends BaseEntity {

    @Column(name = "ZDDM", length = 100 )
    private String ZDDM;  //字典代码

    @Column(name = "ZDMC", length = 200 )
    private String ZDMC;  //字典名称

    @Column(name = "MS", length = 400 )
    private String MS;    //描述

    @Column(name = "ZDFL", length = 100 )
    private String ZDFL;  //字典分类

    @Column(name = "YHJB", length = 100 )
    private String YHJB;  //应用级别

    @Column(name = "ZTA" , length = 10 )
    private String ZTA;   //状态

    @Column(name = "SFXFBZ", length = 10  )
    private String SFXFBZ;//是否消防标准

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


    public String getZDDM() {
        return ZDDM;
    }

    public void setZDDM(String ZDDM) {
        this.ZDDM = ZDDM;
    }

    public String getZDMC() {
        return ZDMC;
    }

    public void setZDMC(String ZDMC) {
        this.ZDMC = ZDMC;
    }

    public String getMS() {
        return MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }

    public String getZTA() {
        return ZTA;
    }

    public void setZTA(String ZTA) {
        this.ZTA = ZTA;
    }

    public String getZDFL() {
        return ZDFL;
    }

    public void setZDFL(String ZDFL) {
        this.ZDFL = ZDFL;
    }

    public String getYHJB() {
        return YHJB;
    }

    public void setYHJB(String YHJB) {
        this.YHJB = YHJB;
    }

    public String getSFXFBZ() {
        return SFXFBZ;
    }

    public void setSFXFBZ(String SFXFBZ) {
        this.SFXFBZ = SFXFBZ;
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
