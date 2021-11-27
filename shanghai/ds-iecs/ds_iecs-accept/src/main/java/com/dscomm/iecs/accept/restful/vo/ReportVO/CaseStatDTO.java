package com.dscomm.iecs.accept.restful.vo.ReportVO;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 警情状态变更上报VO
 * */
public class CaseStatDTO {

    @JSONField(name = "AJID")
    private String AJID ;
    @JSONField(name = "AJZT")
    private String AJZT ;
    @JSONField(name = "BDSJ")
    private String BDSJ ;

    public String getAJID() {
        return AJID;
    }

    public void setAJID(String AJID) {
        this.AJID = AJID;
    }

    public String getAJZT() {
        return AJZT;
    }

    public void setAJZT(String AJZT) {
        this.AJZT = AJZT;
    }

    public String getBDSJ() {
        return BDSJ;
    }

    public void setBDSJ(String BDSJ) {
        this.BDSJ = BDSJ;
    }
}
