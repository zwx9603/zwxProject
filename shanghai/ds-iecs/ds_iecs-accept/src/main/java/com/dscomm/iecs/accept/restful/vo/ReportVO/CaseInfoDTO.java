package com.dscomm.iecs.accept.restful.vo.ReportVO;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 警情上报vo
 * */
public class CaseInfoDTO {

    @JSONField(name = "AJID")
    private String AJID ; //案件id

    @JSONField(name = "AJBH")
    private String AJBH ;//案件编号

    @JSONField(name = "BJSJ")
    private String BJSJ ;//编辑时间

    @JSONField(name = "BJRXM")
    private String BJRXM ;//编辑人姓名

    @JSONField(name = "ZJHM")
    private String ZJHM ;//报警电话

    @JSONField(name = "ZJYHM")
    private String ZJYHM ;//主叫用户名

    @JSONField(name = "BJFS")
    private String BJFS ;//报警方式

    @JSONField(name = "ZJDZ")
    private String ZJDZ ;//装机地址

    @JSONField(name = "XZQYBH")
    private String XZQYBH ;//行政区域编号

    @JSONField(name = "ZGJGBH")
    private String ZGJGBH ;//机构编号

    @JSONField(name = "LXDH")
    private String LXDH ;//联系电话

    @JSONField(name = "LXR")
    private String LXR ;//联系人

    @JSONField(name = "AJLXDM")
    private String AJLXDM ;//案件类型代码

    @JSONField(name = "CZDXDM")
    private String CZDXDM ;//处置对象代码

    @JSONField(name = "AJDJDM")
    private String AJDJDM ;//案件等级代码

    @JSONField(name = "AFDZ")
    private String AFDZ ;//案发地址

    @JSONField(name = "AJMS")
    private String AJMS ;//案件描述

    @JSONField(name = "AJZTDM")
    private String AJZTDM ;//案件状态代码

    @JSONField(name = "IFSB")
    private String IFSB ;//是否上报

    @JSONField(name = "ZDDWID")
    private String ZDDWID ;//重点单位id

    @JSONField(name = "BSXFJGDM")
    private String BSXFJGDM ;//报送消防机构

    @JSONField(name = "ZQLYXFJGDM")
    private String ZQLYXFJGDM ;//灾情来源消防机构

    @JSONField(name = "RYBGS")
    private String RYBGS ;//人员被困数

    @JSONField(name = "SWRS")
    private String SWRS ;//死亡人数

    @JSONField(name = "SSRS")
    private String SSRS ;//受伤人数

    @JSONField(name = "BDSWRS")
    private String BDSWRS ;//部队死亡人数

    @JSONField(name = "BDSSRS")
    private String BDSSRS ;//部队受伤人数

    @JSONField(name = "RSMJ")
    private String RSMJ ;//燃烧面积

    @JSONField(name = "GIS_X")
    private String GIS_X ;//经度

    @JSONField(name = "GIS_Y")
    private String GIS_Y ;//纬度

    @JSONField(name = "GIS_H")
    private String GIS_H ;//高度

    @JSONField(name = "LASJ")
    private String LASJ ;//立案时间

    @JSONField(name = "ZDDWMC")
    private String ZDDWMC ;//重点单位名称

    @JSONField(name = "JZJGLXDM")
    private String JZJGLXDM ;//建筑结构类型代码

    @JSONField(name = "RSLC")
    private String RSLC ;//燃烧楼层

    @JSONField(name = "ZQBS")
    private String ZQBS ;//灾情标志（案件性质代码）

    @JSONField(name = "BZ")
    private String BZ ;//备注

    public String getAJID() {
        return AJID;
    }

    public void setAJID(String AJID) {
        this.AJID = AJID;
    }

    public String getAJBH() {
        return AJBH;
    }

    public void setAJBH(String AJBH) {
        this.AJBH = AJBH;
    }

    public String getBJSJ() {
        return BJSJ;
    }

    public void setBJSJ(String BJSJ) {
        this.BJSJ = BJSJ;
    }

    public String getBJRXM() {
        return BJRXM;
    }

    public void setBJRXM(String BJRXM) {
        this.BJRXM = BJRXM;
    }

    public String getZJHM() {
        return ZJHM;
    }

    public void setZJHM(String ZJHM) {
        this.ZJHM = ZJHM;
    }

    public String getZJYHM() {
        return ZJYHM;
    }

    public void setZJYHM(String ZJYHM) {
        this.ZJYHM = ZJYHM;
    }

    public String getBJFS() {
        return BJFS;
    }

    public void setBJFS(String BJFS) {
        this.BJFS = BJFS;
    }

    public String getZJDZ() {
        return ZJDZ;
    }

    public void setZJDZ(String ZJDZ) {
        this.ZJDZ = ZJDZ;
    }

    public String getXZQYBH() {
        return XZQYBH;
    }

    public void setXZQYBH(String XZQYBH) {
        this.XZQYBH = XZQYBH;
    }

    public String getZGJGBH() {
        return ZGJGBH;
    }

    public void setZGJGBH(String ZGJGBH) {
        this.ZGJGBH = ZGJGBH;
    }

    public String getLXDH() {
        return LXDH;
    }

    public void setLXDH(String LXDH) {
        this.LXDH = LXDH;
    }

    public String getLXR() {
        return LXR;
    }

    public void setLXR(String LXR) {
        this.LXR = LXR;
    }

    public String getAJLXDM() {
        return AJLXDM;
    }

    public void setAJLXDM(String AJLXDM) {
        this.AJLXDM = AJLXDM;
    }

    public String getCZDXDM() {
        return CZDXDM;
    }

    public void setCZDXDM(String CZDXDM) {
        this.CZDXDM = CZDXDM;
    }

    public String getAJDJDM() {
        return AJDJDM;
    }

    public void setAJDJDM(String AJDJDM) {
        this.AJDJDM = AJDJDM;
    }

    public String getAFDZ() {
        return AFDZ;
    }

    public void setAFDZ(String AFDZ) {
        this.AFDZ = AFDZ;
    }

    public String getAJMS() {
        return AJMS;
    }

    public void setAJMS(String AJMS) {
        this.AJMS = AJMS;
    }

    public String getAJZTDM() {
        return AJZTDM;
    }

    public void setAJZTDM(String AJZTDM) {
        this.AJZTDM = AJZTDM;
    }

    public String getIFSB() {
        return IFSB;
    }

    public void setIFSB(String IFSB) {
        this.IFSB = IFSB;
    }

    public String getZDDWID() {
        return ZDDWID;
    }

    public void setZDDWID(String ZDDWID) {
        this.ZDDWID = ZDDWID;
    }

    public String getBSXFJGDM() {
        return BSXFJGDM;
    }

    public void setBSXFJGDM(String BSXFJGDM) {
        this.BSXFJGDM = BSXFJGDM;
    }

    public String getZQLYXFJGDM() {
        return ZQLYXFJGDM;
    }

    public void setZQLYXFJGDM(String ZQLYXFJGDM) {
        this.ZQLYXFJGDM = ZQLYXFJGDM;
    }

    public String getRYBGS() {
        return RYBGS;
    }

    public void setRYBGS(String RYBGS) {
        this.RYBGS = RYBGS;
    }

    public String getSWRS() {
        return SWRS;
    }

    public void setSWRS(String SWRS) {
        this.SWRS = SWRS;
    }

    public String getSSRS() {
        return SSRS;
    }

    public void setSSRS(String SSRS) {
        this.SSRS = SSRS;
    }

    public String getBDSWRS() {
        return BDSWRS;
    }

    public void setBDSWRS(String BDSWRS) {
        this.BDSWRS = BDSWRS;
    }

    public String getBDSSRS() {
        return BDSSRS;
    }

    public void setBDSSRS(String BDSSRS) {
        this.BDSSRS = BDSSRS;
    }

    public String getRSMJ() {
        return RSMJ;
    }

    public void setRSMJ(String RSMJ) {
        this.RSMJ = RSMJ;
    }

    public String getGIS_X() {
        return GIS_X;
    }

    public void setGIS_X(String GIS_X) {
        this.GIS_X = GIS_X;
    }

    public String getGIS_Y() {
        return GIS_Y;
    }

    public void setGIS_Y(String GIS_Y) {
        this.GIS_Y = GIS_Y;
    }

    public String getGIS_H() {
        return GIS_H;
    }

    public void setGIS_H(String GIS_H) {
        this.GIS_H = GIS_H;
    }

    public String getLASJ() {
        return LASJ;
    }

    public void setLASJ(String LASJ) {
        this.LASJ = LASJ;
    }

    public String getZDDWMC() {
        return ZDDWMC;
    }

    public void setZDDWMC(String ZDDWMC) {
        this.ZDDWMC = ZDDWMC;
    }

    public String getJZJGLXDM() {
        return JZJGLXDM;
    }

    public void setJZJGLXDM(String JZJGLXDM) {
        this.JZJGLXDM = JZJGLXDM;
    }

    public String getRSLC() {
        return RSLC;
    }

    public void setRSLC(String RSLC) {
        this.RSLC = RSLC;
    }

    public String getZQBS() {
        return ZQBS;
    }

    public void setZQBS(String ZQBS) {
        this.ZQBS = ZQBS;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
