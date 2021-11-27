package com.dscomm.iecs.accept.service.bean.dahua.dispatch;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 消防装备
 */
public class Xfzb {
    @JSONField(name="XFZB_TYWYSBM")
    private String XFZB_TYWYSBM; //通用唯一识别码
    @JSONField(name="XFZB_MC")
    private String XFZB_MC; //名称
    @JSONField(name="XFZB_XFZBLXDM")
    private String XFZB_XFZBLXDM; //消防装备类型代码
    @JSONField(name="XFZB_KSSJ")
    private Date XFZB_KSSJ; //开始时间
    @JSONField(name="XFZB_JSSJ")
    private Date XFZB_JSSJ; //结束时间

    public String getXFZB_TYWYSBM() {
        return XFZB_TYWYSBM;
    }

    public void setXFZB_TYWYSBM(String XFZB_TYWYSBM) {
        this.XFZB_TYWYSBM = XFZB_TYWYSBM;
    }

    public String getXFZB_MC() {
        return XFZB_MC;
    }

    public void setXFZB_MC(String XFZB_MC) {
        this.XFZB_MC = XFZB_MC;
    }

    public String getXFZB_XFZBLXDM() {
        return XFZB_XFZBLXDM;
    }

    public void setXFZB_XFZBLXDM(String XFZB_XFZBLXDM) {
        this.XFZB_XFZBLXDM = XFZB_XFZBLXDM;
    }

    public Date getXFZB_KSSJ() {
        return XFZB_KSSJ;
    }

    public void setXFZB_KSSJ(Date XFZB_KSSJ) {
        this.XFZB_KSSJ = XFZB_KSSJ;
    }

    public Date getXFZB_JSSJ() {
        return XFZB_JSSJ;
    }

    public void setXFZB_JSSJ(Date XFZB_JSSJ) {
        this.XFZB_JSSJ = XFZB_JSSJ;
    }
}
