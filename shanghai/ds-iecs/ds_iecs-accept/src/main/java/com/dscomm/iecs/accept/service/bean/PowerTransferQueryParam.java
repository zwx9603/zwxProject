package com.dscomm.iecs.accept.service.bean;

/**
 * 智能推荐调派力量参数
 */
public class PowerTransferQueryParam {


    private String ajxx_czdxdm;//处置对象代码   必传
    private String ajxx_lasj;//立案时间   必传
    private String ajxx_ajlxdm;//灾害类型代码（一级）   必传
    private String ajxx_gis_x;//报警点x坐标   必传*
    private String ajxx_gis_y;//       报警点y坐标   必传*
    private String ajxx_ajdjdm;//灾害等级代码   必传
    private String ajxx_lfcs;//       楼房层数
    private String ajxx_xfjg_gis_x ;//消防机构x坐标   必传*
    private String ajxx_xfjg_gis_y;//       消防机构y坐标   必传*
    private String ajxx_ajms;//案件描述   必传
    private String ajxx_ajly;//        案件来源   必传
    private String ajxx_mgqy="0";//敏感区域
    private String ajxx_zdwxy="0";//        重点危险源
    private String ajxx_swqy="0";//涉外区域
    private String ajxx_dfyx="0";//        大风影响
    private String ajxx_yhyx="0" ;//严寒影响
    private String ajxx_zdsd = "0";//        重点时段   必传
    private String ajxx_qsqy="0";//缺水区域
    private String ajxx_id;//接警警情ID，来自于接处警系统，全局唯一   必传 推荐服务不需要此字段
    private String ajxx_qxxxms = "多云  温度：9 ～ 19℃  风向：东 转 东北  风力：3 ～ 4级";//        气象信息描述   必传
    private String ajxx_xfjgid;//主管中队消防机构id 推荐服务不需要此字段



    public String getAjxx_czdxdm() {
        return ajxx_czdxdm;
    }

    public void setAjxx_czdxdm(String ajxx_czdxdm) {
        this.ajxx_czdxdm = ajxx_czdxdm;
    }

    public String getAjxx_lasj() {
        return ajxx_lasj;
    }

    public void setAjxx_lasj(String ajxx_lasj) {
        this.ajxx_lasj = ajxx_lasj;
    }

    public String getAjxx_ajlxdm() {
        return ajxx_ajlxdm;
    }

    public void setAjxx_ajlxdm(String ajxx_ajlxdm) {
        this.ajxx_ajlxdm = ajxx_ajlxdm;
    }

    public String getAjxx_gis_x() {
        return ajxx_gis_x;
    }

    public void setAjxx_gis_x(String ajxx_gis_x) {
        this.ajxx_gis_x = ajxx_gis_x;
    }

    public String getAjxx_gis_y() {
        return ajxx_gis_y;
    }

    public void setAjxx_gis_y(String ajxx_gis_y) {
        this.ajxx_gis_y = ajxx_gis_y;
    }

    public String getAjxx_ajdjdm() {
        return ajxx_ajdjdm;
    }

    public void setAjxx_ajdjdm(String ajxx_ajdjdm) {
        this.ajxx_ajdjdm = ajxx_ajdjdm;
    }

    public String getAjxx_lfcs() {
        return ajxx_lfcs;
    }

    public void setAjxx_lfcs(String ajxx_lfcs) {
        this.ajxx_lfcs = ajxx_lfcs;
    }

    public String getAjxx_xfjg_gis_x() {
        return ajxx_xfjg_gis_x;
    }

    public void setAjxx_xfjg_gis_x(String ajxx_xfjg_gis_x) {
        this.ajxx_xfjg_gis_x = ajxx_xfjg_gis_x;
    }

    public String getAjxx_xfjg_gis_y() {
        return ajxx_xfjg_gis_y;
    }

    public void setAjxx_xfjg_gis_y(String ajxx_xfjg_gis_y) {
        this.ajxx_xfjg_gis_y = ajxx_xfjg_gis_y;
    }

    public String getAjxx_ajms() {
        return ajxx_ajms;
    }

    public void setAjxx_ajms(String ajxx_ajms) {
        this.ajxx_ajms = ajxx_ajms;
    }

    public String getAjxx_ajly() {
        return ajxx_ajly;
    }

    public void setAjxx_ajly(String ajxx_ajly) {
        this.ajxx_ajly = ajxx_ajly;
    }

    public String getAjxx_mgqy() {
        return ajxx_mgqy;
    }

    public void setAjxx_mgqy(String ajxx_mgqy) {
        this.ajxx_mgqy = ajxx_mgqy;
    }

    public String getAjxx_zdwxy() {
        return ajxx_zdwxy;
    }

    public void setAjxx_zdwxy(String ajxx_zdwxy) {
        this.ajxx_zdwxy = ajxx_zdwxy;
    }

    public String getAjxx_swqy() {
        return ajxx_swqy;
    }

    public void setAjxx_swqy(String ajxx_swqy) {
        this.ajxx_swqy = ajxx_swqy;
    }

    public String getAjxx_dfyx() {
        return ajxx_dfyx;
    }

    public void setAjxx_dfyx(String ajxx_dfyx) {
        this.ajxx_dfyx = ajxx_dfyx;
    }

    public String getAjxx_yhyx() {
        return ajxx_yhyx;
    }

    public void setAjxx_yhyx(String ajxx_yhyx) {
        this.ajxx_yhyx = ajxx_yhyx;
    }

    public String getAjxx_zdsd() {
        return ajxx_zdsd;
    }

    public void setAjxx_zdsd(String ajxx_zdsd) {
        this.ajxx_zdsd = ajxx_zdsd;
    }

    public String getAjxx_qsqy() {
        return ajxx_qsqy;
    }

    public void setAjxx_qsqy(String ajxx_qsqy) {
        this.ajxx_qsqy = ajxx_qsqy;
    }

    public String getAjxx_id() {
        return ajxx_id;
    }

    public void setAjxx_id(String ajxx_id) {
        this.ajxx_id = ajxx_id;
    }

    public String getAjxx_qxxxms() {
        return ajxx_qxxxms;
    }

    public void setAjxx_qxxxms(String ajxx_qxxxms) {
        this.ajxx_qxxxms = ajxx_qxxxms;
    }

    public String getAjxx_xfjgid() {
        return ajxx_xfjgid;
    }

    public void setAjxx_xfjgid(String ajxx_xfjgid) {
        this.ajxx_xfjgid = ajxx_xfjgid;
    }
}
