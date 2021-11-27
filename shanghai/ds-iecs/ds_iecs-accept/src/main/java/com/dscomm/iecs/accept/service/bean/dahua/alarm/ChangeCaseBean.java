package com.dscomm.iecs.accept.service.bean.dahua.alarm;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ChangeCaseBean {
    @JSONField(name="JQ_TYWYSBM")
    private String JQ_TYWYSBM; //警情_通用唯一识别码
    @JSONField(name="JQZT")
    private JQZT JQZT; //警情状态
    @JSONField(name="JQZT_LBDM")
    private String JQZT_LBDM; //警情状态类别代码
    @JSONField(name="JQZT_RQSJ")
    private Date JQZT_RQSJ; //日期时间

    public ChangeCaseBean(String JQ_TYWYSBM, JQZT JQZT, String JQZT_LBDM, Date JQZT_RQSJ) {
        this.JQ_TYWYSBM = JQ_TYWYSBM;
        this.JQZT = JQZT;
        this.JQZT_LBDM = JQZT_LBDM;
        this.JQZT_RQSJ = JQZT_RQSJ;
    }


    public ChangeCaseBean() {
    }

    public String getJQ_TYWYSBM() {
        return JQ_TYWYSBM;
    }

    public void setJQ_TYWYSBM(String JQ_TYWYSBM) {
        this.JQ_TYWYSBM = JQ_TYWYSBM;
    }

    public com.dscomm.iecs.accept.service.bean.dahua.alarm.JQZT getJQZT() {
        return JQZT;
    }

    public void setJQZT(com.dscomm.iecs.accept.service.bean.dahua.alarm.JQZT JQZT) {
        this.JQZT = JQZT;
    }

    public String getJQZT_LBDM() {
        return JQZT_LBDM;
    }

    public void setJQZT_LBDM(String JQZT_LBDM) {
        this.JQZT_LBDM = JQZT_LBDM;
    }

    public Date getJQZT_RQSJ() {
        return JQZT_RQSJ;
    }

    public void setJQZT_RQSJ(Date JQZT_RQSJ) {
        this.JQZT_RQSJ = JQZT_RQSJ;
    }
}
