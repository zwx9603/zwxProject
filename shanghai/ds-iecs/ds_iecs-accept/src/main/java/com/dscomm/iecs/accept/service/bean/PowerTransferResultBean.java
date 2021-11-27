package com.dscomm.iecs.accept.service.bean;

import java.util.List;

public class PowerTransferResultBean {

    private String success;
    private String force_name_nums;
    private Integer time;
    private List<RecommendItemBean> equipment;//推荐装备(车辆)

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getForce_name_nums() {
        return force_name_nums;
    }

    public void setForce_name_nums(String force_name_nums) {
        this.force_name_nums = force_name_nums;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<RecommendItemBean> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<RecommendItemBean> equipment) {
        this.equipment = equipment;
    }
}
