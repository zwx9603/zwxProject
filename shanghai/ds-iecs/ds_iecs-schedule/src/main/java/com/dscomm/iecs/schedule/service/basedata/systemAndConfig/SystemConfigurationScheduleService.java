package com.dscomm.iecs.schedule.service.basedata.systemAndConfig;

public interface SystemConfigurationScheduleService {

    /**
     * 系统配置 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);


}
