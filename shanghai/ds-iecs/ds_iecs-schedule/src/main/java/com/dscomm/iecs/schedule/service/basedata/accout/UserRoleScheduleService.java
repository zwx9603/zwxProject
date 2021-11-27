package com.dscomm.iecs.schedule.service.basedata.accout;


/**
 * 描述： 用户角色信息 同步服务
 *
 */
public interface UserRoleScheduleService {

    /**
     * 用户角色信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);


    /**
     * 用户角色信息 删除服务
     */
    void  transportDelete() ;


}
