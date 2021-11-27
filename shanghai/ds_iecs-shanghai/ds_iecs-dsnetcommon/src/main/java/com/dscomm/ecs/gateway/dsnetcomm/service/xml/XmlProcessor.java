package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import com.dscomm.ecs.gateway.dsnetcomm.service.bo.DsMsgHeader;

/**
 * 描述:xml处理器
 *
 * @author YangFuxi
 * Date Time 2020/9/29 0:01
 */
public interface XmlProcessor {
    String getCharset();

    void setCharset(String charset);

    /**
     * 处理收到的消息
     *
     * @param header  消息头
     * @param mainMsg 消息体
     */
    void process(DsMsgHeader header, MainMsg mainMsg);
}
