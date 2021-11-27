package com.dscomm.iecs.schedule.service.basedata.agent;

/**
 * 描述： ACD组信息 同步服务
 *
 */
public interface AcdScheduleService {


    /**
     * ACD组信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);



}
