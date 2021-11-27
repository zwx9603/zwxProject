package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.DispatchDailyRecordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.SendSMSSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DispatchDailyRecordBean;
import com.dscomm.iecs.accept.graphql.typebean.SendSMSBean;

/**
 * 描述：调度日志 服务
 */
public interface DispatchDailyRecordService {

    /**
     * 保存调度日志
     *
     * @param inputInfo 保存调度日志参数
     * @return 返回结果
     */
    DispatchDailyRecordBean saveDispatchDailyRecord(DispatchDailyRecordSaveInputInfo inputInfo);

    /**
     * 保存短信记录
     *
     * @param inputInfo 保存短信记录参数
     * @return 返回结果
     */
    SendSMSBean saveSMS(SendSMSSaveInputInfo inputInfo);

}
