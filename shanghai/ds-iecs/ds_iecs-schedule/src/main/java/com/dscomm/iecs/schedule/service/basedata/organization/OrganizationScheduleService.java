package com.dscomm.iecs.schedule.service.basedata.organization;

public interface OrganizationScheduleService {

    /**
     * 机构信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);


}
