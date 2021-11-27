package com.dscomm.iecs.notify.service;

import java.util.List;

/**
 * 描述：  信息推送服务接口定义
 *
 * @author john peng
 * Date time 2018/7/15 上午10:08
 */
public interface DeviceNotifyService {

    /**
     * 向在线终端推送信息
     * @param code 消息编码
     * @param body 消息内容
     */
    void pushNotifyMessage(String code, String body);

    /**
     * 根据条件过滤在线终端推送信息
     * @param filterType 过滤类型
     * @param filterKeys 过滤条件
     * @param code 消息编码
     * @param body 消息内容
     */
    void pushNotifyMessageFilter  ( Integer filterType  ,  List<String> filterKeys , String code, String body);


}
