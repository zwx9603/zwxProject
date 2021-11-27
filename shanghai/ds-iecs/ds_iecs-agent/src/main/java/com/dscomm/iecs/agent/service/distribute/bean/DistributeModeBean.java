package com.dscomm.iecs.agent.service.distribute.bean;

import java.util.List;

/**
 * 描述:警情分配策略类
 *
 */
public class DistributeModeBean {


    private Boolean alarmTypeFilteration;//是否过滤警种

    private List<String> alarmTypes;//接管的警种集合

    private String distributeMode;//分配方式

    private List<String> accepts;//接管大项 一级案由，辖区等

    private List<String> acceptItems;//接管细项列表，可以存放接管的单位、全案由列表等等


    public Boolean getAlarmTypeFilteration() {
        return alarmTypeFilteration;
    }

    public void setAlarmTypeFilteration(Boolean alarmTypeFilteration) {
        this.alarmTypeFilteration = alarmTypeFilteration;
    }

    public List<String> getAccepts() {
        return accepts;
    }

    public void setAccepts(List<String> accepts) {
        this.accepts = accepts;
    }

    public List<String> getAlarmTypes() {
        return alarmTypes;
    }

    public void setAlarmTypes(List<String> alarmTypes) {
        this.alarmTypes = alarmTypes;
    }

    public String getDistributeMode() {
        return distributeMode;
    }

    public void setDistributeMode(String distributeMode) {
        this.distributeMode = distributeMode;
    }

    public List<String> getAcceptItems() {
        return acceptItems;
    }

    public void setAcceptItems(List<String> acceptItems) {
        this.acceptItems = acceptItems;
    }
}
