package com.dscomm.iecs.schedule.service.basedata.keyunit;

public interface KeyUnitScheduleService {

    /**
     * 重点单位信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
