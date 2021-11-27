package com.dscomm.iecs.schedule.service.basedata.accout;

/**
 * 描述： 角色信息 同步服务
 *
 */
public interface SystemRoleScheduleService {


    /**
     * 角色信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

    /**
     * 角色信息 删除服务
     */
    void  transportDelete() ;



}
