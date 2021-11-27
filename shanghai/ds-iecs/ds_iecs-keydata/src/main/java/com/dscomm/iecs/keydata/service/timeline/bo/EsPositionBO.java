package com.dscomm.iecs.keydata.service.timeline.bo;

/**
 * 描述:坐标点位
 *
 * @author YangFuxi
 * Date Time 2019/8/15 17:05
 */
public class EsPositionBO {
    /**
     * 经度
     */
    private String jd;

    /**
     * 维度
     */
    private String wd;

    /**
     * 坐标系
     */
    private String zbx;

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getZbx() {
        return zbx;
    }

    public void setZbx(String zbx) {
        this.zbx = zbx;
    }
}
