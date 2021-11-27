package com.dscomm.iecs.accept.service;

/**
 * 描述:检查调派力量是否满足预先维护的调派方案，如果不满足，则给接警员推送消息提醒
 * author:YangFuXi
 * Date:2021/6/11 Time:10:11
 **/
public interface CheckHandleService {
    /**
     * 检查调派的力量是否满足
     * @param incidentId 案件id
     */
    void checkHandle(String incidentId);
}
