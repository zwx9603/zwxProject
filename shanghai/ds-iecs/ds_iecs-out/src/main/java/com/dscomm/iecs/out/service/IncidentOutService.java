package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.IncidentOutBean;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 19:22
 * @Describe 获取警情信息服务
 */
public interface IncidentOutService {

    /**
     * 根据时间获取案件列表
     * @param startTime
     * @param endTime
     * @return
     */
    List<IncidentOutBean> getIncidentListByTime(Long startTime,Long endTime,String username);

}
