package com.dscomm.iecs.schedule.service.basedata.water;

public interface WaterIntakeWharfScheduleService {

    /**
     * 消防码头基本信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
