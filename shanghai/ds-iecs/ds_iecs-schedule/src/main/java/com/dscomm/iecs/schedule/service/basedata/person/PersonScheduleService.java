package com.dscomm.iecs.schedule.service.basedata.person;

public interface PersonScheduleService {

    /**
     * 人员信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);


}
