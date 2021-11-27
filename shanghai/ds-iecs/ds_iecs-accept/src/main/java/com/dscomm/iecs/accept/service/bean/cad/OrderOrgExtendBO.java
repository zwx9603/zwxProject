package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;

/**
 * 描述 : 单位指令单扩展信息
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/12/24 16:59
 */
public class OrderOrgExtendBO implements Serializable {
    /**
     * 行政区划（事件单去取）
     */
    private String area;
    /**
     * 行政区划name
     */
    private String areaName;
    /**
     * 终端反馈标记
     */
    private Integer terminalFeedbackMark;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getTerminalFeedbackMark() {
        return terminalFeedbackMark;
    }

    public void setTerminalFeedbackMark(Integer terminalFeedbackMark) {
        this.terminalFeedbackMark = terminalFeedbackMark;
    }
}
