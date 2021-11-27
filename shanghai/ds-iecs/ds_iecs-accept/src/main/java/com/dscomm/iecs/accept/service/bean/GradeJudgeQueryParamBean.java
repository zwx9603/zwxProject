package com.dscomm.iecs.accept.service.bean;

public class GradeJudgeQueryParamBean {

    private String ajxx_czdxdm;//处置对象代码 必传
    private String ajxx_zdsd = "0";//重要时段  必传
    private String ajxx_ajms;//案件描述
    private String xcxx_ywqkdm = "0";//烟雾情况代码  必传
    private String xcxx_huoyan = "0";//是否看到明火  必传
    private String xcxx_rybk="0";//是否被困  必传
    private String xcxx_bkrs="0";//被困人数
    private String xcxx_qzssrs="0";//受伤人数
    private String xcxx_czdxms="";//处置对象描述  必传
    private String xcxx_rsmj="0";//燃烧面积
    private String jcj_zhcs;//灾害场所代码
    private String jcj_zddw;//重点单位uuid
    private Long jcj_yjjq;//夜间警情,传案发时间或报警时间
    private String ajxx_id;//案件编号

    public String getAjxx_id() {
        return ajxx_id;
    }

    public void setAjxx_id(String ajxx_id) {
        this.ajxx_id = ajxx_id;
    }

    public String getAjxx_czdxdm() {
        return ajxx_czdxdm;
    }

    public void setAjxx_czdxdm(String ajxx_czdxdm) {
        this.ajxx_czdxdm = ajxx_czdxdm;
    }

    public String getAjxx_zdsd() {
        return ajxx_zdsd;
    }

    public void setAjxx_zdsd(String ajxx_zdsd) {
        this.ajxx_zdsd = ajxx_zdsd;
    }

    public String getAjxx_ajms() {
        return ajxx_ajms;
    }

    public void setAjxx_ajms(String ajxx_ajms) {
        this.ajxx_ajms = ajxx_ajms;
    }

    public String getXcxx_ywqkdm() {
        return xcxx_ywqkdm;
    }

    public void setXcxx_ywqkdm(String xcxx_ywqkdm) {
        this.xcxx_ywqkdm = xcxx_ywqkdm;
    }

    public String getXcxx_huoyan() {
        return xcxx_huoyan;
    }

    public void setXcxx_huoyan(String xcxx_huoyan) {
        this.xcxx_huoyan = xcxx_huoyan;
    }

    public String getXcxx_rybk() {
        return xcxx_rybk;
    }

    public void setXcxx_rybk(String xcxx_rybk) {
        this.xcxx_rybk = xcxx_rybk;
    }

    public String getXcxx_bkrs() {
        return xcxx_bkrs;
    }

    public void setXcxx_bkrs(String xcxx_bkrs) {
        this.xcxx_bkrs = xcxx_bkrs;
    }

    public String getXcxx_qzssrs() {
        return xcxx_qzssrs;
    }

    public void setXcxx_qzssrs(String xcxx_qzssrs) {
        this.xcxx_qzssrs = xcxx_qzssrs;
    }

    public String getXcxx_czdxms() {
        return xcxx_czdxms;
    }

    public void setXcxx_czdxms(String xcxx_czdxms) {
        this.xcxx_czdxms = xcxx_czdxms;
    }

    public String getXcxx_rsmj() {
        return xcxx_rsmj;
    }

    public void setXcxx_rsmj(String xcxx_rsmj) {
        this.xcxx_rsmj = xcxx_rsmj;
    }

    public String getJcj_zhcs() {
        return jcj_zhcs;
    }

    public void setJcj_zhcs(String jcj_zhcs) {
        this.jcj_zhcs = jcj_zhcs;
    }

    public String getJcj_zddw() {
        return jcj_zddw;
    }

    public void setJcj_zddw(String jcj_zddw) {
        this.jcj_zddw = jcj_zddw;
    }

    public Long getJcj_yjjq() {
        return jcj_yjjq;
    }

    public void setJcj_yjjq(Long jcj_yjjq) {
        this.jcj_yjjq = jcj_yjjq;
    }
}
