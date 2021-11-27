package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.HandleDateBean;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 22:37
 * @Describe 调派信息服务
 */
public interface HandleOutService {

    /**
     * 获取调派信息列表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<HandleDateBean> getDispatchListByTime(Long startTime, Long endTime,String username);
}
