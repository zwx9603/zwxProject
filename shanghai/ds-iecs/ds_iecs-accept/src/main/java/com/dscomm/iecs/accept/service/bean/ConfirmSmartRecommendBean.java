package com.dscomm.iecs.accept.service.bean;

import java.util.List;

/**
 * 描述:确认推荐结果以及差异原因
 *
 * @author YangFuxi
 * Date Time 2021/8/9 11:12
 */
public class ConfirmSmartRecommendBean {
    private String id;//推荐记录id
    private String causeOfDifference;//差异原因
    private List<VehicleTypeAndNumBean> dispatchVehicles;//实际调派力量
    private String level;//实际级别

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCauseOfDifference() {
        return causeOfDifference;
    }

    public void setCauseOfDifference(String causeOfDifference) {
        this.causeOfDifference = causeOfDifference;
    }

    public List<VehicleTypeAndNumBean> getDispatchVehicles() {
        return dispatchVehicles;
    }

    public void setDispatchVehicles(List<VehicleTypeAndNumBean> dispatchVehicles) {
        this.dispatchVehicles = dispatchVehicles;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
