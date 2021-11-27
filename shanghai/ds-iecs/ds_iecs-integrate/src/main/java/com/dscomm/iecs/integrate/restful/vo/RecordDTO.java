package com.dscomm.iecs.integrate.restful.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 录音号VO
 * */
public class RecordDTO {

    @JSONField(name = "AJID")
    private String AJID ;//案件id
    @JSONField(name = "THHM")
    private String THHM ;//通话号码
    @JSONField(name = "LYBH")
    private String LYBH ;//录音编号
    @JSONField(name = "LYBFDZ")
    private String LYBFDZ ;//录音播放地址
    @JSONField(name = "LYSJ")
    private String LYSJ ;//录音时间

    public String getAJID() {
        return AJID;
    }

    public void setAJID(String AJID) {
        this.AJID = AJID;
    }

    public String getTHHM() {
        return THHM;
    }

    public void setTHHM(String THHM) {
        this.THHM = THHM;
    }

    public String getLYBH() {
        return LYBH;
    }

    public void setLYBH(String LYBH) {
        this.LYBH = LYBH;
    }

    public String getLYBFDZ() {
        return LYBFDZ;
    }

    public void setLYBFDZ(String LYBFDZ) {
        this.LYBFDZ = LYBFDZ;
    }

    public String getLYSJ() {
        return LYSJ;
    }

    public void setLYSJ(String LYSJ) {
        this.LYSJ = LYSJ;
    }

    @Override
    public String toString() {
        return "{\\\"AJID\\\":\\\""+AJID+"\\\",\\\"LYBH\\\":\\\""+LYBH+"\\\",\\\"THHM\\\":\\\""+THHM+"\\\"";
    }
}
