package com.dscomm.ecs.gateway.dsnetcomm.service;

import com.dscomm.ecs.gateway.dsnetcomm.service.bo.SendMessageBO;

/**
 * description: 测试服务
 *
 * @author YangFuxi
 * Date Time 2020/8/28 11:18
 */
public interface TestNotifyService {
    Boolean sendMessage(SendMessageBO bo);
}
