package com.dscomm.iecs.integrate.restful.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  tts 播报
 * */
public class TTSSpeechDTO {

    @JSONField(name = "AJID")
    private String AJID ; //案件id

    @JSONField(name = "AJLX")
    private String AJLX ;//案件类型

    @JSONField(name = "AJZT")
    private String AJZT ;//案件状态

    @JSONField(name = "BJSJ")
    private String BJSJ ;//报警时间

    @JSONField(name = "LXDH")
    private String LXDH ;//联系电话

    @JSONField(name = "AFDZ")
    private String AFDZ ;//案发地址

    @JSONField(name = "ZJYHM")
    private String ZJYHM  ;//机构

    @JSONField(name = "BBNR")
    private String BBNR  ;//播放内容


    public String getAJID() {
        return AJID;
    }

    public void setAJID(String AJID) {
        this.AJID = AJID;
    }


    public String getAJLX() {
        return AJLX;
    }

    public void setAJLX(String AJLX) {
        this.AJLX = AJLX;
    }


    public String getAJZT() {
        return AJZT;
    }

    public void setAJZT(String AJZT) {
        this.AJZT = AJZT;
    }

    public String getBJSJ() {
        return BJSJ;
    }

    public void setBJSJ(String BJSJ) {
        this.BJSJ = BJSJ;
    }

    public String getLXDH() {
        return LXDH;
    }

    public void setLXDH(String LXDH) {
        this.LXDH = LXDH;
    }

    public String getAFDZ() {
        return AFDZ;
    }

    public void setAFDZ(String AFDZ) {
        this.AFDZ = AFDZ;
    }

    public String getZJYHM() {
        return ZJYHM;
    }

    public void setZJYHM(String ZJYHM) {
        this.ZJYHM = ZJYHM;
    }

    public String getBBNR() {
        return BBNR;
    }

    public void setBBNR(String BBNR) {
        this.BBNR = BBNR;
    }
}
