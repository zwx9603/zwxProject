package com.dscomm.iecs.schedule.service.basedata.agent;

/**
 * 描述： 坐席信息 同步服务
 *
 */
public interface AgentScheduleService {


    /**
     * 坐席信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

    /**
     * 坐席信息 删除服务
     */
    void  transportDelete() ;


}
