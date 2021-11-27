package com.dscomm.iecs.schedule.service.basedata.keyunit;

public interface KeyUnitDangerousChemicalsScheduleService {

    /**
     * 重点单位危化品信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
