package com.dscomm.iecs.accept.service.bean.dahua.dispatch;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 车辆类型
 */
public class Xfcl {
    @JSONField(name="XFCL_CLLXDM")
    private String XFCL_CLLXDM; //车辆类型代码
    @JSONField(name="XFCL_JDCHPHM")
    private String XFCL_JDCHPHM; //机动车号牌号码
    @JSONField(name="XFCL_TYWYSBM")
    private String XFCL_TYWYSBM; //通用唯一识别码
    @JSONField(name="orgCode")
    private String orgCode; //所属单位编号（需要对方补充对应字段）
    @JSONField(name="XFCL_CDSJ")
    private Date XFCL_CDSJ; // 出动时间
    @JSONField(name="XFCL_CSSJ")
    private Date XFCL_CSSJ; // 出水时间
    @JSONField(name="XFCL_DDSJ")
    private Date XFCL_DDSJ; // 到达时间
    @JSONField(name="XFCL_GDSJ")
    private Date XFCL_GDSJ; //归队时间
    @JSONField(name="XFCL_TSSJ")
    private Date XFCL_TSSJ; //停水时间
    @JSONField(name="XFCL_ZTFHSJ")
    private Date XFCL_ZTFHSJ; //中途返回时间

    public String getXFCL_CLLXDM() {
        return XFCL_CLLXDM;
    }

    public void setXFCL_CLLXDM(String XFCL_CLLXDM) {
        this.XFCL_CLLXDM = XFCL_CLLXDM;
    }

    public String getXFCL_JDCHPHM() {
        return XFCL_JDCHPHM;
    }

    public void setXFCL_JDCHPHM(String XFCL_JDCHPHM) {
        this.XFCL_JDCHPHM = XFCL_JDCHPHM;
    }

    public String getXFCL_TYWYSBM() {
        return XFCL_TYWYSBM;
    }

    public void setXFCL_TYWYSBM(String XFCL_TYWYSBM) {
        this.XFCL_TYWYSBM = XFCL_TYWYSBM;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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
}
