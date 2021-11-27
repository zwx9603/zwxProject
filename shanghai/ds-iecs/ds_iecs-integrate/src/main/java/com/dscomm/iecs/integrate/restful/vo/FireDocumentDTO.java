package com.dscomm.iecs.integrate.restful.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 火场文书VO
 * */
public class FireDocumentDTO {

    @JSONField(name = "AJID")
    private String AJID ;//案件id
    @JSONField(name = "WSID")
    private String WSID ;//文书id
    @JSONField(name = "PDAID")
    private String PDAID ;//终端ID
    @JSONField(name = "FKR")
    private String FKR ;//反馈人
    @JSONField(name = "FKXFJG")
    private String FKXFJG ;//反馈消防机构
    @JSONField(name = "WSSJ")
    private String WSSJ ;//文书时间
    @JSONField(name = "WSNR")
    private String WSNR ;//文书内容
    @JSONField(name = "BZ")
    private String BZ ;//备注

    public String getAJID() {
        return AJID;
    }

    public void setAJID(String AJID) {
        this.AJID = AJID;
    }

    public String getWSID() {
        return WSID;
    }

    public void setWSID(String WSID) {
        this.WSID = WSID;
    }

    public String getPDAID() {
        return PDAID;
    }

    public void setPDAID(String PDAID) {
        this.PDAID = PDAID;
    }

    public String getFKR() {
        return FKR;
    }

    public void setFKR(String FKR) {
        this.FKR = FKR;
    }

    public String getFKXFJG() {
        return FKXFJG;
    }

    public void setFKXFJG(String FKXFJG) {
        this.FKXFJG = FKXFJG;
    }

    public String getWSSJ() {
        return WSSJ;
    }

    public void setWSSJ(String WSSJ) {
        this.WSSJ = WSSJ;
    }

    public String getWSNR() {
        return WSNR;
    }

    public void setWSNR(String WSNR) {
        this.WSNR = WSNR;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
