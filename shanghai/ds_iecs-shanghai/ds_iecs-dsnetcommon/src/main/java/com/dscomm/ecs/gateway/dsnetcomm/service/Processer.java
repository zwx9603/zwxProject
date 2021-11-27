package com.dscomm.ecs.gateway.dsnetcomm.service;

import com.dscomm.ecs.gateway.dsnetcomm.service.bo.MessageBO;

/**
 * 描述:消息处理器接口
 *
 * @author YangFuxi
 * Date Time 2020/9/17 18:41
 */
public interface Processer {

    String getmsgID();

    void setmsgID(String msgID);

    String getCharset();

    void setCharset(String charset);


    /**
     * 通用的消息处理器
     *
     * @param messageBO 消息体
     */
    void process(MessageBO messageBO);
}
