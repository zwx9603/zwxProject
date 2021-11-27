package com.dscomm.iecs.keydata.service.timeline.bo;

import java.util.List;

/**
 * 时间轴请求对象
 *
 * @author YangFuxi
 * Date Time 2019/8/15 17:05
 */
public class EsTimelineRequestBO {

    /**
     * 标识
     */
    private String bs;

    /**
     * 标识代码（可空）
     */
    private String bscode;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 操作代码
     */
    private String czcode;

    /**
     * 操作人姓名
     */
    private String czrxm;

    /**
     * 操作人用户名
     */
    private String czryhm;

    /**
     * 定位
     */
    private EsPositionBO dw;

    /**
     * 发起单位代码
     */
    private String fqdw;

    /**
     * 发起单位名
     */
    private String fqdwm;


    /**
     * 信息来源编号
     */
    private String lybh;

    /**
     * 录音号
     */
    private String lyh;

    /**
     * 信息来源名
     */
    private String lylx;

    /**
     * 流转编号
     */
    private String lzbh;

    /**
     * 目的单位名（多个以逗号分隔）
     */
    private String mddwms;

    /**
     * 目的单位代码（多个以逗号分隔）
     */
    private String mddws;

    /**
     * 时间 格式：yyyy-MM-dd HH:mm:ss
     */
    private String sj;

    /**
     * 事件名
     */
    private String sjm;

    /**
     * 视频附件
     */
    private List<EsTimelineAttachmentBO> spfj;

    /**
     * 信息梗概
     */
    private String title;

    /**
     * 图片附件
     */
    private List<EsTimelineAttachmentBO> tpfj;

    /**
     * 文件附件
     */
    private List<EsTimelineAttachmentBO> wjfj;

    /**
     * 显示详细页面URL地址
     */
    private String xqdz;

    /**
     * 音频附件
     */
    private List<EsTimelineAttachmentBO> ypfj;
    /**
     * 指挥ID(可为空)
     */
    private String commandId;
    /**
     * 页面模板类型，默认值default，其他值可自定义(可为空)
     */
    private String templateType;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getBs() {
        if ("".equals(bs) || bs == null) {
            return " ";// 去除该属性的前后空格并进行非空非null判断
        }
        return bs;
    }

    public String getBscode() {
        return bscode;
    }

    public String getContent() {
        return content;
    }

    public String getCzcode() {
        return czcode;
    }

    public String getCzrxm() {
        return czrxm;
    }

    public String getCzryhm() {
        return czryhm;
    }

    public EsPositionBO getDw() {
        return dw;
    }

    public String getFqdw() {
        return fqdw;
    }

    public String getFqdwm() {
        return fqdwm;
    }

//    public String getFqdws() {
//        return fqdws;
//    }

    public String getLybh() {
        return lybh;
    }

    public String getLyh() {
        return lyh;
    }

    public String getLylx() {
        return lylx;
    }

    public String getLzbh() {
        return lzbh;
    }

    public String getMddwms() {
        return mddwms;
    }

    public String getMddws() {
        return mddws;
    }

    public String getSj() {
        return sj;
    }

    public String getSjm() {
        return sjm;
    }

    public List<EsTimelineAttachmentBO> getSpfj() {
        return spfj;
    }

    public String getTitle() {
        return title;
    }

    public List<EsTimelineAttachmentBO> getTpfj() {
        return tpfj;
    }

    public List<EsTimelineAttachmentBO> getWjfj() {
        return wjfj;
    }

    public String getXqdz() {
        return xqdz;
    }

    /**
     * 获取类成员ypfj
     *
     * @return {@link #ypfj}
     */
    public List<EsTimelineAttachmentBO> getYpfj() {
        return this.ypfj;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public void setBscode(String bscode) {
        this.bscode = bscode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCzcode(String czcode) {
        this.czcode = czcode;
    }

    public void setCzrxm(String czrxm) {
        this.czrxm = czrxm;
    }

    public void setCzryhm(String czryhm) {
        this.czryhm = czryhm;
    }

    public void setDw(EsPositionBO dw) {
        this.dw = dw;
    }

    public void setFqdw(String fqdw) {
        this.fqdw = fqdw;
    }

    public void setFqdwm(String fqdwm) {
        this.fqdwm = fqdwm;
    }

//    public void setFqdws(String fqdws) {
//        this.fqdws = fqdws;
//    }

    public void setLybh(String lybh) {
        this.lybh = lybh;
    }

    public void setLyh(String lyh) {
        this.lyh = lyh;
    }

    public void setLylx(String lylx) {
        this.lylx = lylx;
    }

    public void setLzbh(String lzbh) {
        this.lzbh = lzbh;
    }

    public void setMddwms(String mddwms) {
        this.mddwms = mddwms;
    }

    public void setMddws(String mddws) {
        this.mddws = mddws;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public void setSjm(String sjm) {
        this.sjm = sjm;
    }

    public void setSpfj(List<EsTimelineAttachmentBO> spfj) {
        this.spfj = spfj;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTpfj(List<EsTimelineAttachmentBO> tpfj) {
        this.tpfj = tpfj;
    }

    public void setWjfj(List<EsTimelineAttachmentBO> wjfj) {
        this.wjfj = wjfj;
    }

    public void setXqdz(String xqdz) {
        this.xqdz = xqdz;
    }

    /**
     * 设定类成员ypfj
     *
     * @param ypfj 要设定的{@link #ypfj}
     */
    public void setYpfj(List<EsTimelineAttachmentBO> ypfj) {
        this.ypfj = ypfj;
    }

}
