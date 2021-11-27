package com.dscomm.iecs.accept.service.bean.dahua.alarm;

import com.alibaba.fastjson.annotation.JSONField;

public class DhAlarmBean {
    /**
     * 警情_通用唯一识别码
     */
    @JSONField(name="JQ_TYWYSBM")
    private String JQ_TYWYSBM;

    /**
     * 警情状态
     */
    @JSONField(name="JQZT")
    private JQZT JQZT;

    /**
     * 报警_通用唯一识别码
     */
    @JSONField(name="BJ_TYWYSBM")
    private String BJ_TYWYSBM;

    /**
     * 名称
     */
    @JSONField(name="MC")
    private String MC;

    /**
     * 警情分类与代码
     */
    @JSONField(name="JQFLYDM")
    private String JQFLYDM;

    /**
     * 地点名称
     */
    @JSONField(name="DDMC")
    private String DDMC;

    /**
     * 警情对象
     */
    @JSONField(name="JQDX")
    private JQDX JQDX;

    /**
     * 警情等级代码
     */
    @JSONField(name="JQDJDM")
    private String JQDJDM;


    /**
     * 现场指挥人
     */
    @JSONField(name="XCZHR")
    private XCZHR XCZHR;

    public String getJQ_TYWYSBM() {
        return JQ_TYWYSBM;
    }

    public void setJQ_TYWYSBM(String JQ_TYWYSBM) {
        this.JQ_TYWYSBM = JQ_TYWYSBM;
    }



    public String getBJ_TYWYSBM() {
        return BJ_TYWYSBM;
    }

    public void setBJ_TYWYSBM(String BJ_TYWYSBM) {
        this.BJ_TYWYSBM = BJ_TYWYSBM;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getJQFLYDM() {
        return JQFLYDM;
    }

    public void setJQFLYDM(String JQFLYDM) {
        this.JQFLYDM = JQFLYDM;
    }

    public String getDDMC() {
        return DDMC;
    }

    public void setDDMC(String DDMC) {
        this.DDMC = DDMC;
    }

    public com.dscomm.iecs.accept.service.bean.dahua.alarm.JQZT getJQZT() {
        return JQZT;
    }

    public void setJQZT(com.dscomm.iecs.accept.service.bean.dahua.alarm.JQZT JQZT) {
        this.JQZT = JQZT;
    }

    public com.dscomm.iecs.accept.service.bean.dahua.alarm.JQDX getJQDX() {
        return JQDX;
    }

    public void setJQDX(com.dscomm.iecs.accept.service.bean.dahua.alarm.JQDX JQDX) {
        this.JQDX = JQDX;
    }

    public String getJQDJDM() {
        return JQDJDM;
    }

    public void setJQDJDM(String JQDJDM) {
        this.JQDJDM = JQDJDM;
    }

    public com.dscomm.iecs.accept.service.bean.dahua.alarm.XCZHR getXCZHR() {
        return XCZHR;
    }

    public void setXCZHR(com.dscomm.iecs.accept.service.bean.dahua.alarm.XCZHR XCZHR) {
        this.XCZHR = XCZHR;
    }
}
