package com.dscomm.iecs.schedule.service.basedata.accout;

/**
 * 描述： 用户信息 同步服务
 *
 */
public interface SystemUserScheduleService {
    /**
     * 用户信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
