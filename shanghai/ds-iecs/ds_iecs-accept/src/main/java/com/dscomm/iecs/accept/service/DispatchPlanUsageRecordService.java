package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.DispachPlanUsageRecordInputInfo;

import java.util.Map;

/**
 * 描述:调派方案使用记录服务
 * author:YangFuXi
 * Date:2021/6/21 Time:15:11
 **/
public interface DispatchPlanUsageRecordService {
    Boolean saveDispatchPlanUsageRecord(DispachPlanUsageRecordInputInfo inputInfo);

    /**
     * 统计调派方案使用次数
     * @return 返回结果
     */
    Map<String,Integer> countDispatchPlanUsageRecord();
}
