package com.dscomm.iecs.integrate.restful.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 现场信息上报VO
 * */
public class SceneInfoDTO {

    @JSONField(name = "ID")
    private String ID ;

    @JSONField(name = "AJID")
    private String AJID ;//案件id

    @JSONField(name = "ZHDJDM")
    private String ZHDJDM ;//灾害等级代码

    @JSONField(name = "CJSJ")
    private String CJSJ ;//采集时间

    @JSONField(name = "XCMS")
    private String XCMS ;//现场描述

    @JSONField(name = "DCRS")
    private String DCRS ;//到场人数

    @JSONField(name = "BKRS")
    private String BKRS ;//被困人数

    @JSONField(name = "QZSWRS")
    private String QZSWRS ;//群众死亡人数

    @JSONField(name = "BDSWRS")
    private String BDSWRS ;//部队死亡人数

    @JSONField(name = "QZSSRS")
    private String QZSSRS ;//群众受伤人数

    @JSONField(name = "XDSD")
    private String XDSD ;//相对湿度

    @JSONField(name = "TQDM")
    private String TQDM ;//天气代码

    @JSONField(name = "QW")
    private String QW ;//气温

    @JSONField(name = "HCWD")
    private String HCWD ;//火场温度

    @JSONField(name = "FX")
    private String FX ;//风向

    @JSONField(name = "FLDJ")
    private String FLDJ ;//风力等级

    @JSONField(name = "RSMJ")
    private String RSMJ ;//燃烧面积

    @JSONField(name = "YWQKDM")
    private String YWQKDM ;//烟雾情况代码

    @JSONField(name = "BDSSRS")
    private String BDSSRS ;//部队受伤人数

    @JSONField(name = "BZ")
    private String BZ ;//备注

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAJID() {
        return AJID;
    }

    public void setAJID(String AJID) {
        this.AJID = AJID;
    }

    public String getZHDJDM() {
        return ZHDJDM;
    }

    public void setZHDJDM(String ZHDJDM) {
        this.ZHDJDM = ZHDJDM;
    }

    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    public String getXCMS() {
        return XCMS;
    }

    public void setXCMS(String XCMS) {
        this.XCMS = XCMS;
    }

    public String getDCRS() {
        return DCRS;
    }

    public void setDCRS(String DCRS) {
        this.DCRS = DCRS;
    }

    public String getBKRS() {
        return BKRS;
    }

    public void setBKRS(String BKRS) {
        this.BKRS = BKRS;
    }

    public String getQZSWRS() {
        return QZSWRS;
    }

    public void setQZSWRS(String QZSWRS) {
        this.QZSWRS = QZSWRS;
    }

    public String getBDSWRS() {
        return BDSWRS;
    }

    public void setBDSWRS(String BDSWRS) {
        this.BDSWRS = BDSWRS;
    }

    public String getQZSSRS() {
        return QZSSRS;
    }

    public void setQZSSRS(String QZSSRS) {
        this.QZSSRS = QZSSRS;
    }

    public String getXDSD() {
        return XDSD;
    }

    public void setXDSD(String XDSD) {
        this.XDSD = XDSD;
    }

    public String getTQDM() {
        return TQDM;
    }

    public void setTQDM(String TQDM) {
        this.TQDM = TQDM;
    }

    public String getQW() {
        return QW;
    }

    public void setQW(String QW) {
        this.QW = QW;
    }

    public String getHCWD() {
        return HCWD;
    }

    public void setHCWD(String HCWD) {
        this.HCWD = HCWD;
    }

    public String getFX() {
        return FX;
    }

    public void setFX(String FX) {
        this.FX = FX;
    }

    public String getFLDJ() {
        return FLDJ;
    }

    public void setFLDJ(String FLDJ) {
        this.FLDJ = FLDJ;
    }

    public String getRSMJ() {
        return RSMJ;
    }

    public void setRSMJ(String RSMJ) {
        this.RSMJ = RSMJ;
    }

    public String getYWQKDM() {
        return YWQKDM;
    }

    public void setYWQKDM(String YWQKDM) {
        this.YWQKDM = YWQKDM;
    }

    public String getBDSSRS() {
        return BDSSRS;
    }

    public void setBDSSRS(String BDSSRS) {
        this.BDSSRS = BDSSRS;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
