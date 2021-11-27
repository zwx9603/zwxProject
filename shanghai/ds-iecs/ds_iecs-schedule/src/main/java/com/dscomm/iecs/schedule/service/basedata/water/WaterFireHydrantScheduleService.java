package com.dscomm.iecs.schedule.service.basedata.water;

public interface WaterFireHydrantScheduleService {

    /**
     * 消防栓基本信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
