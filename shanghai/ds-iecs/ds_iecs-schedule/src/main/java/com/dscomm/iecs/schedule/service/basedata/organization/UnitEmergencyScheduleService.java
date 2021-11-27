package com.dscomm.iecs.schedule.service.basedata.organization;

public interface UnitEmergencyScheduleService {

    /**
     * 应急联动单位信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);


}
