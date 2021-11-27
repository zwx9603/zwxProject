package com.dscomm.iecs.schedule.service.basedata.water;

public interface WaterCraneScheduleService {

    /**
     * 水鹤基本信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
