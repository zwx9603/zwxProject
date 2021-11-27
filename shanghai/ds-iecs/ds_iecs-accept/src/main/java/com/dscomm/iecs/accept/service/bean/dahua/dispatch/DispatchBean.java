package com.dscomm.iecs.accept.service.bean.dahua.dispatch;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class DispatchBean {
    @JSONField(name="JQCJ_TYWYSBM")
    private String JQCJ_TYWYSBM; //警情处警_通用唯一识别码
    @JSONField(name="JQ_TYWYSBM")
    private String JQ_TYWYSBM; //警情_通用唯一识别码
    @JSONField(name="DPZJ_RS")
    private String DPZJ_RS; //调派专家_人数
    @JSONField(name="DPZJ_XM")
    private String DPZJ_XM; //调派专家_姓名
    @JSONField(name="DPRY_RS")
    private String DPRY_RS; //调派人员_人数
    @JSONField(name="DPRY_XM")
    private String DPRY_XM; //调派人员_姓名
    @JSONField(name="DPCL_SL")
    private String DPCL_SL; //调派车辆_数量
    @JSONField(name="DPZB_SL")
    private String DPZB_SL; //调派装备_数量
    @JSONField(name="JQDPXSLBDM")
    private String JQDPXSLBDM; //警情调派形式类别代码
    @JSONField(name="DPZL_WJNR")
    private String DPZL_WJNR; //调派指令_文件内容
    @JSONField(name="FK_RQSJ")
    private Date FK_RQSJ; //反馈_日期时间
    @JSONField(name="FS_RQSJ")
    private Date FS_RQSJ; //发送_日期时间
    @JSONField(name="JS_RQSJ")
    private Date JS_RQSJ; //接收_日期时间
    @JSONField(name="XFCL_CDSJ")
    private Date XFCL_CDSJ; //
    @JSONField(name="XFCL_CSSJ")
    private Date XFCL_CSSJ; //
    @JSONField(name="XFCL_DDSJ")
    private Date XFCL_DDSJ; //
    @JSONField(name="XFCL_GDSJ")
    private Date XFCL_GDSJ; //
    @JSONField(name="XFCL_TSSJ")
    private Date XFCL_TSSJ; //
    @JSONField(name="XFCL_ZTFHSJ")
    private Date XFCL_ZTFHSJ; //

    @JSONField(name="FSWD")
    private FSDW FSDW; // 发送单位
    @JSONField(name="XFCL")
    private List<Xfcl> XFCL; // 车辆类型
    @JSONField(name="XFZB")
    private List<Xfzb> XFZB; // 消防装备
    @JSONField(name="JSDW")
    private List<Jsdw> JSDW; // 接收单位

    public String getJQCJ_TYWYSBM() {
        return JQCJ_TYWYSBM;
    }

    public void setJQCJ_TYWYSBM(String JQCJ_TYWYSBM) {
        this.JQCJ_TYWYSBM = JQCJ_TYWYSBM;
    }

    public String getJQ_TYWYSBM() {
        return JQ_TYWYSBM;
    }

    public void setJQ_TYWYSBM(String JQ_TYWYSBM) {
        this.JQ_TYWYSBM = JQ_TYWYSBM;
    }

    public String getDPZJ_RS() {
        return DPZJ_RS;
    }

    public void setDPZJ_RS(String DPZJ_RS) {
        this.DPZJ_RS = DPZJ_RS;
    }

    public String getDPZJ_XM() {
        return DPZJ_XM;
    }

    public void setDPZJ_XM(String DPZJ_XM) {
        this.DPZJ_XM = DPZJ_XM;
    }

    public String getDPRY_RS() {
        return DPRY_RS;
    }

    public void setDPRY_RS(String DPRY_RS) {
        this.DPRY_RS = DPRY_RS;
    }

    public String getDPRY_XM() {
        return DPRY_XM;
    }

    public void setDPRY_XM(String DPRY_XM) {
        this.DPRY_XM = DPRY_XM;
    }

    public String getDPCL_SL() {
        return DPCL_SL;
    }

    public void setDPCL_SL(String DPCL_SL) {
        this.DPCL_SL = DPCL_SL;
    }

    public String getDPZB_SL() {
        return DPZB_SL;
    }

    public void setDPZB_SL(String DPZB_SL) {
        this.DPZB_SL = DPZB_SL;
    }

    public String getJQDPXSLBDM() {
        return JQDPXSLBDM;
    }

    public void setJQDPXSLBDM(String JQDPXSLBDM) {
        this.JQDPXSLBDM = JQDPXSLBDM;
    }

    public String getDPZL_WJNR() {
        return DPZL_WJNR;
    }

    public void setDPZL_WJNR(String DPZL_WJNR) {
        this.DPZL_WJNR = DPZL_WJNR;
    }

    public Date getFK_RQSJ() {
        return FK_RQSJ;
    }

    public void setFK_RQSJ(Date FK_RQSJ) {
        this.FK_RQSJ = FK_RQSJ;
    }

    public Date getFS_RQSJ() {
        return FS_RQSJ;
    }

    public void setFS_RQSJ(Date FS_RQSJ) {
        this.FS_RQSJ = FS_RQSJ;
    }

    public Date getJS_RQSJ() {
        return JS_RQSJ;
    }

    public void setJS_RQSJ(Date JS_RQSJ) {
        this.JS_RQSJ = JS_RQSJ;
    }

    public Date getXFCL_CDSJ() {
        return XFCL_CDSJ;
    }

    public void setXFCL_CDSJ(Date XFCL_CDSJ) {
        this.XFCL_CDSJ = XFCL_CDSJ;
    }

    public Date getXFCL_CSSJ() {
        return XFCL_CSSJ;
    }

    public void setXFCL_CSSJ(Date XFCL_CSSJ) {
        this.XFCL_CSSJ = XFCL_CSSJ;
    }

    public Date getXFCL_DDSJ() {
        return XFCL_DDSJ;
    }

    public void setXFCL_DDSJ(Date XFCL_DDSJ) {
        this.XFCL_DDSJ = XFCL_DDSJ;
    }

    public Date getXFCL_GDSJ() {
        return XFCL_GDSJ;
    }

    public void setXFCL_GDSJ(Date XFCL_GDSJ) {
        this.XFCL_GDSJ = XFCL_GDSJ;
    }

    public Date getXFCL_TSSJ() {
        return XFCL_TSSJ;
    }

    public void setXFCL_TSSJ(Date XFCL_TSSJ) {
        this.XFCL_TSSJ = XFCL_TSSJ;
    }

    public Date getXFCL_ZTFHSJ() {
        return XFCL_ZTFHSJ;
    }

    public void setXFCL_ZTFHSJ(Date XFCL_ZTFHSJ) {
        this.XFCL_ZTFHSJ = XFCL_ZTFHSJ;
    }

    public com.dscomm.iecs.accept.service.bean.dahua.dispatch.FSDW getFSDW() {
        return FSDW;
    }

    public void setFSDW(com.dscomm.iecs.accept.service.bean.dahua.dispatch.FSDW FSDW) {
        this.FSDW = FSDW;
    }

    public List<Xfcl> getXFCL() {
        return XFCL;
    }

    public void setXFCL(List<Xfcl> XFCL) {
        this.XFCL = XFCL;
    }

    public List<Xfzb> getXFZB() {
        return XFZB;
    }

    public void setXFZB(List<Xfzb> XFZB) {
        this.XFZB = XFZB;
    }

    public List<Jsdw> getJSDW() {
        return JSDW;
    }

    public void setJSDW(List<Jsdw> JSDW) {
        this.JSDW = JSDW;
    }
}
