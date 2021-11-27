package com.dscomm.iecs.schedule.service.basedata.person;

public interface ExpertScheduleService {

    /**
     * 专家信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
