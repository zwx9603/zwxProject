package com.dscomm.iecs.accept.service.bean.dahua.dispatch;

import com.alibaba.fastjson.annotation.JSONField;

public class CarchangeBean {
    @JSONField(name="CL_TYWYSBM")
    private String CL_TYWYSBM; //车辆_通用唯一识别码
    @JSONField(name="CLZT")
    private CLZT CLZT; // 车辆状态
    @JSONField(name="CLZT_LBDM")
    private String CLZT_LBDM; //车辆状态类别代码
    @JSONField(name="CLZT_RQSJ")
    private String CLZT_RQSJ; //日期时间

    public String getCL_TYWYSBM() {
        return CL_TYWYSBM;
    }

    public void setCL_TYWYSBM(String CL_TYWYSBM) {
        this.CL_TYWYSBM = CL_TYWYSBM;
    }

    public com.dscomm.iecs.accept.service.bean.dahua.dispatch.CLZT getCLZT() {
        return CLZT;
    }

    public void setCLZT(com.dscomm.iecs.accept.service.bean.dahua.dispatch.CLZT CLZT) {
        this.CLZT = CLZT;
    }

    public String getCLZT_LBDM() {
        return CLZT_LBDM;
    }

    public void setCLZT_LBDM(String CLZT_LBDM) {
        this.CLZT_LBDM = CLZT_LBDM;
    }

    public String getCLZT_RQSJ() {
        return CLZT_RQSJ;
    }

    public void setCLZT_RQSJ(String CLZT_RQSJ) {
        this.CLZT_RQSJ = CLZT_RQSJ;
    }

    public CarchangeBean(String CL_TYWYSBM, com.dscomm.iecs.accept.service.bean.dahua.dispatch.CLZT CLZT, String CLZT_LBDM, String CLZT_RQSJ) {
        this.CL_TYWYSBM = CL_TYWYSBM;
        this.CLZT = CLZT;
        this.CLZT_LBDM = CLZT_LBDM;
        this.CLZT_RQSJ = CLZT_RQSJ;
    }
}
