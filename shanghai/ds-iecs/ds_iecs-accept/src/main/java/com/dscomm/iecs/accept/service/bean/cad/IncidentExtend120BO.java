package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;

/**
 * 描述:120事件扩展表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/26 16:13
 */
public class IncidentExtend120BO implements Serializable {
    /**
     * 事件单编号
     */
    private String id;
    /**
     * 时间戳
     */
    private Long lastedhandleTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }
}
