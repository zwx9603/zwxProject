package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.SoundRecordOutBean;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/20 18:57
 * @Describe 录音信息服务
 */
public interface SoundRecordOutService {


    /**
     * 根据时间获取录音列表
     * @param startTime
     * @param endTime
     * @return
     */
    List<SoundRecordOutBean> getSoundRecordListByTime(Long startTime,Long endTime ,String username);
}
