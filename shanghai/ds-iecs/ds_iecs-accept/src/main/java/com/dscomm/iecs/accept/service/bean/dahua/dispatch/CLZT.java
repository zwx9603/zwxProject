package com.dscomm.iecs.accept.service.bean.dahua.dispatch;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class CLZT {
    @JSONField(name="CLZT_LBDM")
    private String CLZT_LBDM;
    @JSONField(name="CLZT_RQSJ")
    private Date CLZT_RQSJ;

    public CLZT(String CLZT_LBDM, Date CLZT_RQSJ) {
        this.CLZT_LBDM = CLZT_LBDM;
        this.CLZT_RQSJ = CLZT_RQSJ;
    }
}
