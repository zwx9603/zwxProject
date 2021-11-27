package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.AlarmRecordBean;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 13:50
 * @Describe 获取报警记录信息服务
 */
public interface AcceptanceOutService {


    /**
     * 根据时间获取报警记录列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 结果集
     */
    List<AlarmRecordBean> getAlarmRecordListByTime(Long startTime,Long endTime,String username);
}
