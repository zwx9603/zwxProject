package com.dscomm.iecs.accept.service.bean.dahua.alarm; //

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 警情对象
 */
public class JQDX {
    @JSONField(name="JQ_JYQK")
    private String JQ_JYQK;  // 警情要情况
    @JSONField(name="DQJD")
    private String DQJD;  // 地球经度
    @JSONField(name="DQWD")
    private String DQWD;  // 地球纬度
    @JSONField(name="JQDX_JQDXLBDM")
    private String JQDX_JQDXLBDM;  // 警情对象类别代码
    @JSONField(name="JQBSLBDM")
    private String JQBSLBDM;  // 警情标识类别代码
    @JSONField(name="BJDH")
    private String BJDH;  // 报警电话
    @JSONField(name="JQDX_MC")
    private String JQDX_MC;  // 名称
    @JSONField(name="BJFSLBDM")
    private String BJFSLBDM;  //
    @JSONField(name="JQDX_JYQK")
    private String JQDX_JYQK;  // 简要情况
    @JSONField(name="JQDX_TYWYSBM")
    private String JQDX_TYWYSBM;  //  通用唯一识别码
    @JSONField(name="LC")
    private String LC;  // 楼层
    @JSONField(name="BJSJ")
    private Date BJSJ;  // 报警时间
    @JSONField(name="HSKZSJ")
    private Date HSKZSJ;  //  火势控制时间
    @JSONField(name="JBPMSJ")
    private Date JBPMSJ; // 基本扑灭时间
    @JSONField(name="JSMLSJ")
    private Date JSMLSJ; // 接受命令时间
    @JSONField(name="CDSJ")
    private Date CDSJ; // 出动时间
    @JSONField(name="ZTFHSJ")
    private Date ZTFHSJ; // 中途返回时间
    @JSONField(name="DCSJ")
    private Date DCSJ; // 到场时间
    @JSONField(name="JASJ")
    private Date JASJ; //
    @JSONField(name="ZDZKSJ")
    private Date ZDZKSJ; // 战斗展开时间
    @JSONField(name="LASJ")
    private Date LASJ; // 立案时间
    @JSONField(name="TSSJ")
    private Date TSSJ; // 停水时间
    @JSONField(name="GDSJ")
    private Date GDSJ; // 归队时间
    @JSONField(name="CSSJ")
    private Date CSSJ; // 出水时间

    public String getJQ_JYQK() {
        return JQ_JYQK;
    }

    public void setJQ_JYQK(String JQ_JYQK) {
        this.JQ_JYQK = JQ_JYQK;
    }

    public String getDQJD() {
        return DQJD;
    }

    public void setDQJD(String DQJD) {
        this.DQJD = DQJD;
    }

    public String getDQWD() {
        return DQWD;
    }

    public void setDQWD(String DQWD) {
        this.DQWD = DQWD;
    }

    public String getJQDX_JQDXLBDM() {
        return JQDX_JQDXLBDM;
    }

    public void setJQDX_JQDXLBDM(String JQDX_JQDXLBDM) {
        this.JQDX_JQDXLBDM = JQDX_JQDXLBDM;
    }

    public String getJQBSLBDM() {
        return JQBSLBDM;
    }

    public void setJQBSLBDM(String JQBSLBDM) {
        this.JQBSLBDM = JQBSLBDM;
    }

    public String getBJDH() {
        return BJDH;
    }

    public void setBJDH(String BJDH) {
        this.BJDH = BJDH;
    }

    public String getJQDX_MC() {
        return JQDX_MC;
    }

    public void setJQDX_MC(String JQDX_MC) {
        this.JQDX_MC = JQDX_MC;
    }

    public String getBJFSLBDM() {
        return BJFSLBDM;
    }

    public void setBJFSLBDM(String BJFSLBDM) {
        this.BJFSLBDM = BJFSLBDM;
    }

    public String getJQDX_JYQK() {
        return JQDX_JYQK;
    }

    public void setJQDX_JYQK(String JQDX_JYQK) {
        this.JQDX_JYQK = JQDX_JYQK;
    }

    public String getJQDX_TYWYSBM() {
        return JQDX_TYWYSBM;
    }

    public void setJQDX_TYWYSBM(String JQDX_TYWYSBM) {
        this.JQDX_TYWYSBM = JQDX_TYWYSBM;
    }

    public String getLC() {
        return LC;
    }

    public void setLC(String LC) {
        this.LC = LC;
    }

    public Date getBJSJ() {
        return BJSJ;
    }

    public void setBJSJ(Date BJSJ) {
        this.BJSJ = BJSJ;
    }

    public Date getHSKZSJ() {
        return HSKZSJ;
    }

    public void setHSKZSJ(Date HSKZSJ) {
        this.HSKZSJ = HSKZSJ;
    }

    public Date getJBPMSJ() {
        return JBPMSJ;
    }

    public void setJBPMSJ(Date JBPMSJ) {
        this.JBPMSJ = JBPMSJ;
    }

    public Date getJSMLSJ() {
        return JSMLSJ;
    }

    public void setJSMLSJ(Date JSMLSJ) {
        this.JSMLSJ = JSMLSJ;
    }

    public Date getCDSJ() {
        return CDSJ;
    }

    public void setCDSJ(Date CDSJ) {
        this.CDSJ = CDSJ;
    }

    public Date getZTFHSJ() {
        return ZTFHSJ;
    }

    public void setZTFHSJ(Date ZTFHSJ) {
        this.ZTFHSJ = ZTFHSJ;
    }

    public Date getDCSJ() {
        return DCSJ;
    }

    public void setDCSJ(Date DCSJ) {
        this.DCSJ = DCSJ;
    }

    public Date getJASJ() {
        return JASJ;
    }

    public void setJASJ(Date JASJ) {
        this.JASJ = JASJ;
    }

    public Date getZDZKSJ() {
        return ZDZKSJ;
    }

    public void setZDZKSJ(Date ZDZKSJ) {
        this.ZDZKSJ = ZDZKSJ;
    }

    public Date getLASJ() {
        return LASJ;
    }

    public void setLASJ(Date LASJ) {
        this.LASJ = LASJ;
    }

    public Date getTSSJ() {
        return TSSJ;
    }

    public void setTSSJ(Date TSSJ) {
        this.TSSJ = TSSJ;
    }

    public Date getGDSJ() {
        return GDSJ;
    }

    public void setGDSJ(Date GDSJ) {
        this.GDSJ = GDSJ;
    }

    public Date getCSSJ() {
        return CSSJ;
    }

    public void setCSSJ(Date CSSJ) {
        this.CSSJ = CSSJ;
    }
}
