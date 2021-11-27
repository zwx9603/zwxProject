package com.dscomm.iecs.accept.service.bean.dahua.alarm;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 警情状态
 */
public class JQZT {
    /**
     * 警情状态类别代码
     */
    @JSONField(name="JQZT_LBDM")
    private String JQZT_LBDM;

    /**
     *  日期时间
     */
    @JSONField(name="JQZT_RQSJ")
    private Date JQZT_RQSJ;

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

    public JQZT(String JQZT_LBDM, Date JQZT_RQSJ) {
        this.JQZT_LBDM = JQZT_LBDM;
        this.JQZT_RQSJ = JQZT_RQSJ;
    }
}
