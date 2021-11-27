package com.dscomm.iecs.accept.service.bean.dahua.alarm;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 现场指挥人
 */
public class XCZHR {
    @JSONField(name="XCZHR_XM")
    private String XCZHR_XM; // 姓名
    @JSONField(name="XFJYJG_TYWYSBM")
    private String XFJYJG_TYWYSBM; //消防救援机构_通用唯一识别码
    @JSONField(name="XZQHDM")
    private String XZQHDM; // 行政区划代码
    @JSONField(name="XCZHR_LXDH")
    private String XCZHR_LXDH; // 联系电话
    @JSONField(name="XCZHR_XFGWLBDM")
    private String XCZHR_XFGWLBDM; //消防岗位类别代码
    @JSONField(name="JZJGLXDM")
    private String JZJGLXDM; // 建筑结构类型代码
    @JSONField(name="YWQKDM")
    private String YWQKDM; // 烟雾情况代码
    @JSONField(name="XCZHR_RQSJ")
    private Date XCZHR_RQSJ; // 日期时间

    public String getXCZHR_XM() {
        return XCZHR_XM;
    }

    public void setXCZHR_XM(String XCZHR_XM) {
        this.XCZHR_XM = XCZHR_XM;
    }

    public String getXFJYJG_TYWYSBM() {
        return XFJYJG_TYWYSBM;
    }

    public void setXFJYJG_TYWYSBM(String XFJYJG_TYWYSBM) {
        this.XFJYJG_TYWYSBM = XFJYJG_TYWYSBM;
    }

    public String getXZQHDM() {
        return XZQHDM;
    }

    public void setXZQHDM(String XZQHDM) {
        this.XZQHDM = XZQHDM;
    }

    public String getXCZHR_LXDH() {
        return XCZHR_LXDH;
    }

    public void setXCZHR_LXDH(String XCZHR_LXDH) {
        this.XCZHR_LXDH = XCZHR_LXDH;
    }

    public String getXCZHR_XFGWLBDM() {
        return XCZHR_XFGWLBDM;
    }

    public void setXCZHR_XFGWLBDM(String XCZHR_XFGWLBDM) {
        this.XCZHR_XFGWLBDM = XCZHR_XFGWLBDM;
    }

    public String getJZJGLXDM() {
        return JZJGLXDM;
    }

    public void setJZJGLXDM(String JZJGLXDM) {
        this.JZJGLXDM = JZJGLXDM;
    }

    public String getYWQKDM() {
        return YWQKDM;
    }

    public void setYWQKDM(String YWQKDM) {
        this.YWQKDM = YWQKDM;
    }

    public Date getXCZHR_RQSJ() {
        return XCZHR_RQSJ;
    }

    public void setXCZHR_RQSJ(Date XCZHR_RQSJ) {
        this.XCZHR_RQSJ = XCZHR_RQSJ;
    }
}
