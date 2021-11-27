package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.VehicleOutBean;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/20 11:45
 * @Describe 车辆外部数据对接
 */
public interface VehicleOutService {

    /**
     * 根据时间获取车辆列表
     * @return
     */
    List<VehicleOutBean> getVehicleByTime(String username);
}
