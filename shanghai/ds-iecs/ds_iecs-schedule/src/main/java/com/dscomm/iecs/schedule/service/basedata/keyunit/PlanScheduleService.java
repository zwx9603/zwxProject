package com.dscomm.iecs.schedule.service.basedata.keyunit;

public interface PlanScheduleService {

    /**
     * 预案信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
