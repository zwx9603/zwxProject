package com.dscomm.iecs.schedule.service.basedata.systemAndConfig;

public interface DictionaryScheduleService {

    /**
     * 字典信息 同步服务
     * @param targetTime 同步目标时间
     */
    void transport(Long targetTime);

}
