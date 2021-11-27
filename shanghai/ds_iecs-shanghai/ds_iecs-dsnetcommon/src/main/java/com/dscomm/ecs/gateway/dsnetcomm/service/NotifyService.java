package com.dscomm.ecs.gateway.dsnetcomm.service;

import com.dscomm.ecs.gateway.dsnetcomm.service.bo.SendBodyMessage;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.SendMessageBO;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.TransmitXmlMessage;

/**
 * 描述:消息发送服务
 *
 * @author YangFuxi
 * Date Time 2020/9/29 23:54
 */
public interface NotifyService {
    /**
     * 发送通用消息(string)
     * @param bo 消息参数
     * @return 返回发送结果
     */
    Boolean sendMessage(SendMessageBO bo);
    /**
     * 发送通用消息(xml)
     * @param bo 消息参数
     * @return 返回发送结果
     */

    Boolean sendMessage(SendBodyMessage bo);
    /**
     * 转发通用消息(xml)
     * @param bo 消息参数
     * @return 返回发送结果
     */

    Boolean transmitMessage(TransmitXmlMessage bo);
}
