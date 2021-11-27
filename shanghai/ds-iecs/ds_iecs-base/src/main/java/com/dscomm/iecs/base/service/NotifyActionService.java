package com.dscomm.iecs.base.service;

import com.dscomm.iecs.base.graphql.typebean.CLMessageBean;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;

import java.util.List;
import java.util.Set;

/**
 * 描述: notify websocket 通知推送行为
 *
 */
public interface  NotifyActionService {

    /**
     * 推送消息服务
     *
     * @param clMessage 推送内容
     * @param body      消息内容，用于打日志安全红线脱敏
     */
    void clientNotify(CLMessageBean clMessage, Object body);


    /**
     * 推送消息服务 默认发送者为系统用户
     *
     * @param code      websocket code
     * @param body      消息内容
     * @param receivers 接收方
     */
    void pushMessage(String code, Object body, List<ReceiverMessageBean> receivers);


    /**
     * 推送消息服务
     *
     * @param code         websocket消息号
     * @param body         消息内容
     * @param fromUserCode 发送者账号
     * @param receivers    接收者
     */
    void pushMessage(String code, Object body, String fromUserCode, List<ReceiverMessageBean> receivers);


    /**
     * 给本系统用户推送信息
     *
     * @param code      消息号
     * @param body      消息内容
     * @param receivers 接收者(用户)
     */
    void pushMessageToUsers(String code, Object body, List<String> receivers);

    /**
     * 给本系统业务单位发送websocket信息
     *
     * @param code 编码，消息头
     * @param body 内容，消息体
     * @param orgs 接收单位
     */
    void pushMessageToDefaultSystemBusinessOrg(String code, Object body, Set<String> orgs);


    /**
     * 给指定系统业务单位发送websocket信息
     *
     * @param code   编码，消息头
     * @param body   内容，消息体
     * @param orgs   接收单位
     * @param system 接收系统
     */
    void pushMessageToBusinessOrg(String code, Object body, Set<String> orgs, String system);


    /**
     * 给本系统业务单位 业务角色 发送websocket信息
     *
     * @param code 编码，消息头
     * @param body 内容，消息体
     * @param orgs 接收单位
     */
    void pushMessageToDefaultSystemBusinessOrgRole(String code, Object body, Set<String> orgs, Set<String> roles);



    /**
     * 给指定系统业务单位 业务角色  发送websocket信息
     *
     * @param code 编码，消息头
     * @param body 内容，消息体
     * @param orgs 接收单位
     */
    void pushMessageToBusinessOrgRole(String code, Object body, Set<String> orgs, Set<String> roles, String system);

}
