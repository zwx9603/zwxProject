package com.dscomm.iecs.agent.service.distribute.bean;

/**
 * 描述:警情分配配置项
 *
 * @author YangFuxi
 * Date Time 2019/9/19 10:00
 */
public class DistributeConfigBean {

    private Boolean alarmTypeFilter=false;

    private String distributeMode;

    public Boolean getAlarmTypeFilter() {
        return alarmTypeFilter;
    }

    public void setAlarmTypeFilter(Boolean alarmTypeFilter) {
        this.alarmTypeFilter = alarmTypeFilter;
    }

    public String getDistributeMode() {
        return distributeMode;
    }

    public void setDistributeMode(String distributeMode) {
        this.distributeMode = distributeMode;
    }
}
