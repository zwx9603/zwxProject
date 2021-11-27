package com.dscomm.iecs.accept.restful.vo.ReportVO;

/**
 * 现场信息上报VO
 * */
public class SceneInfoDTO {

    private String ID ;

    private String AJID ;//案件id

    private String ZHDJDM ;//灾害等级代码

    private String CJSJ ;//采集时间


    private String XCMS ;//现场描述


    private String DCRS ;//到场人数


    private String BKRS ;//被困人数


    private String QZSWRS ;//群众死亡人数


    private String BDSWRS ;//部队死亡人数


    private String QZSSRS ;//群众受伤人数


    private String XDSD ;//相对湿度


    private String TQDM ;//天气代码


    private String QW ;//气温


    private String HCWD ;//火场温度


    private String FX ;//风向


    private String FLDJ ;//风力等级


    private String RSMJ ;//燃烧面积


    private String YWQKDM ;//烟雾情况代码


    private String BDSSRS ;//部队受伤人数


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
