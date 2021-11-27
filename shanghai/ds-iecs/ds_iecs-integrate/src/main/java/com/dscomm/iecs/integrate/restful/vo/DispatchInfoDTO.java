package com.dscomm.iecs.integrate.restful.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 处警上报信息
 * */
public class DispatchInfoDTO {

    @JSONField(name = "AJID")
    private String AJID ;//案件id
    @JSONField(name = "DPFAID")
    private String DPFAID ;//调派方案id
    @JSONField(name = "CDDID")
    private String CDDID ;//处警单id
    @JSONField(name = "CDDBM")
    private String CDDBM ;//处警单编码
    @JSONField(name = "FSDWDM")
    private String FSDWDM ;//发送单位编码
    @JSONField(name = "JSDWDM")
    private String JSDWDM ;//接受单位编码
    @JSONField(name = "LXBZ")
    private String LXBZ ;//类型标志
    @JSONField(name = "FSSJ")
    private String FSSJ ;//发送时间
    @JSONField(name = "CDRS")
    private String CDRS ;//出动人数
    @JSONField(name = "BZ")
    private String BZ ;//备注

    public String getAJID() {
        return AJID;
    }

    public void setAJID(String AJID) {
        this.AJID = AJID;
    }

    public String getDPFAID() {
        return DPFAID;
    }

    public void setDPFAID(String DPFAID) {
        this.DPFAID = DPFAID;
    }

    public String getCDDID() {
        return CDDID;
    }

    public void setCDDID(String CDDID) {
        this.CDDID = CDDID;
    }

    public String getCDDBM() {
        return CDDBM;
    }

    public void setCDDBM(String CDDBM) {
        this.CDDBM = CDDBM;
    }

    public String getFSDWDM() {
        return FSDWDM;
    }

    public void setFSDWDM(String FSDWDM) {
        this.FSDWDM = FSDWDM;
    }

    public String getJSDWDM() {
        return JSDWDM;
    }

    public void setJSDWDM(String JSDWDM) {
        this.JSDWDM = JSDWDM;
    }

    public String getLXBZ() {
        return LXBZ;
    }

    public void setLXBZ(String LXBZ) {
        this.LXBZ = LXBZ;
    }

    public String getFSSJ() {
        return FSSJ;
    }

    public void setFSSJ(String FSSJ) {
        this.FSSJ = FSSJ;
    }

    public String getCDRS() {
        return CDRS;
    }

    public void setCDRS(String CDRS) {
        this.CDRS = CDRS;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
