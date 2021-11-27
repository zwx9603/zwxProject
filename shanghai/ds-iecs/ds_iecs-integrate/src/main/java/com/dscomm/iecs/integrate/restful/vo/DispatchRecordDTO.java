package com.dscomm.iecs.integrate.restful.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 调派记录VO
 * */
public class DispatchRecordDTO {

    @JSONField(name = "AJID")
    private String AJID ;//案件id
    @JSONField(name = "DPFAID")
    private String DPFAID ;//调派方案id
    @JSONField(name = "FSDWDM")
    private String FSDWDM ;//发送单位代码
    @JSONField(name = "FSDWZH")
    private String FSDWZH ;//发送单位账号
    @JSONField(name = "FSSJ")
    private String FSSJ ;//发送时间
    @JSONField(name = "JSSJ")
    private String JSSJ ;//结束时间
    @JSONField(name = "BZ")
    private String BZ ;//结束

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

    public String getFSDWDM() {
        return FSDWDM;
    }

    public void setFSDWDM(String FSDWDM) {
        this.FSDWDM = FSDWDM;
    }

    public String getFSDWZH() {
        return FSDWZH;
    }

    public void setFSDWZH(String FSDWZH) {
        this.FSDWZH = FSDWZH;
    }

    public String getFSSJ() {
        return FSSJ;
    }

    public void setFSSJ(String FSSJ) {
        this.FSSJ = FSSJ;
    }

    public String getJSSJ() {
        return JSSJ;
    }

    public void setJSSJ(String JSSJ) {
        this.JSSJ = JSSJ;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
