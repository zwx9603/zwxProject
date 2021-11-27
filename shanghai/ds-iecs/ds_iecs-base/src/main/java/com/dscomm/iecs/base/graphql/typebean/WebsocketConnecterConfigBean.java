package com.dscomm.iecs.base.graphql.typebean;

import org.springframework.beans.factory.annotation.Value;

/**
 * 描述:websocket连接配置信息
 *
 * @author YangFuxi
 * Date Time 2019/7/22 10:14
 */
public class WebsocketConnecterConfigBean {
    @Value("${}")
    private String websocketServerIp;
    private String websocketFromUser;
    private String websocketSystem;
}
