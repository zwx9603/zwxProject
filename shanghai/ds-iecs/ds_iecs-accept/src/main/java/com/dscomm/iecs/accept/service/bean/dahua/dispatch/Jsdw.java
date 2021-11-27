package com.dscomm.iecs.accept.service.bean.dahua.dispatch;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  接收单位
 */
public class Jsdw {
    @JSONField(name="JSDW_TYWYSBM")
    private String JSDW_TYWYSBM; //通用唯一识别码
    @JSONField(name="JSDW_DWMC")
    private String JSDW_DWMC; //单位名称

    public Jsdw() {
    }

    public Jsdw(String JSDW_TYWYSBM, String JSDW_DWMC) {
        this.JSDW_TYWYSBM = JSDW_TYWYSBM;
        this.JSDW_DWMC = JSDW_DWMC;
    }

    public String getJSDW_TYWYSBM() {
        return JSDW_TYWYSBM;
    }

    public void setJSDW_TYWYSBM(String JSDW_TYWYSBM) {
        this.JSDW_TYWYSBM = JSDW_TYWYSBM;
    }

    public String getJSDW_DWMC() {
        return JSDW_DWMC;
    }

    public void setJSDW_DWMC(String JSDW_DWMC) {
        this.JSDW_DWMC = JSDW_DWMC;
    }
}
