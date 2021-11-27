/*
 * @(#)WebSocket.java
 *
 * Copyright 2016, 迪爱斯通信设备有限公司保留.
 */
package com.dscomm.iecs.base.service.impl;


import com.dscomm.iecs.base.exception.UserInterfaceWebsocketException;
import com.dscomm.iecs.base.graphql.typebean.CLMessageBean;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.utils.JaxbUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.util.Strings;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 描述:websocket连接
 *
 * @author YangFuxi
 * Date Time 2019/7/19 17:27
 */
@Component
public class WebSocketConnector implements InitializingBean {

    /**
     * 静态变量：系统日志
     */
    private final Log logger = LogFactory.getLog(WebSocketConnector.class);
    /**
     * 加載配置类
     */

    private WebSocketClient socket;
    /**
     * 心跳timer
     */
    private Timer timer;
    private Thread heartThread;
    private Thread reconnectThread;
    /**
     * webSocket服务器的地址
     */
    @Value("${websocket.enable:true}")
    private Boolean webSocketEnable;
    @Value("${websocket.server.ip:ws://127.0.0.1:10002}")
    private String webSocketServerIP;
    @Value("${websocket.fromUser:HJSL}")
    private String webSocketFromUser;
    @Value("${websocket.system:HJSL}")
    private String websocketSysem;
    public final static String MACCODE = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);


    /**
     * 链接websocket
     */
    private synchronized WebSocketClient openWebSocket() {
        try {
            URI serverUrl = new URI(webSocketServerIP);
            try {
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (Exception e) {
                logger.error("openWebSocket when close websocket", e);
            } finally {
                socket = null;
            }
            socket = new WebSocketClient(serverUrl) {
                @Override
                public void onClose(int code, String reason, boolean remote) {
                    logger.error("websocket on close");
                    stopHeartThread();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException ex) {
                        logger.error("websocket onClose",ex);
                    }
                    startReconnectThread();
                }

                @Override
                public void onError(Exception ex) {
                    logger.error("websocket on error", ex);
                }

                @Override
                public void onMessage(String message) {

                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    CLMessageBean clMessageBO = new CLMessageBean("2");// 固定值
                    clMessageBO.setBody("login");// 固定值
                    clMessageBO.getHead().setFrom(new ReceiverMessageBean("user", String.format("%s_%s", webSocketFromUser, MACCODE)));
                    clMessageBO.getHead().setTos(null);
                    String notify = JaxbUtil.convertToXml(clMessageBO);
                    socket.send(notify);
                    startHearThread();
                    logger.error("socket open");
                }

            };
            logger.error("socket start connect");
//            if (!StringUtils.isBlank(webSocketServerIP)&&webSocketServerIP.contains("wss")){
//                SSLClient.trustAllHosts(socket);
//            }
            socket.connectBlocking();
            logger.error("socket connect");
            // webs 注册 COCsvr
            return socket;

        } catch (Exception e) {
            logger.error("InnerError: fail to init socket.", e);
            return null;
        }
    }

    public synchronized WebSocketClient reOpen() {
        try {
            socket = openWebSocket();
            return socket;
        } catch (Exception ex) {
            logger.error("reopen websocket fail", ex);
            return null;
        }
    }


    /*
     * 每3秒发送心跳消息，确认在线状态
     */
    private void startTimer() {
        CLMessageBean clMessage = new CLMessageBean("0");// 固定值
        clMessage.setBody("heart");// 固定值
        clMessage.getHead().setFrom(new ReceiverMessageBean("user", String.format("%s_%s", webSocketFromUser, MACCODE)));
        clMessage.getHead().setTos(null);

        final String notify = JaxbUtil.convertToXml(clMessage);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    logger.info("send");
                    send(notify, true);
                } catch (Exception ex) {
                    logger.error("send websocket message fail", ex);
                }

            }

        };
        timer = new Timer();
        // 每10秒发一次心跳 10*1000
        timer.schedule(task, 0, 3 * 1000);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            if(webSocketEnable){
                start();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);

        }
    }

    /**
     * 关闭socket连接
     */
    @PreDestroy
    public void destory() {
        try {
            if (timer != null) {
                timer.cancel();
            }
        } catch (Exception ex) {
            logger.error("error to destory on websocket", ex);
        } finally {
        }

    }

    /**
     * 发送消息
     *
     * @param text 消息内容
     */
    public synchronized void send(String text, Boolean isHeart) {
        try {
            socket.send(text);
        } catch (Exception e) {
            logger.error("InnerError: fail to notify web client notify server.error is:", e);
        }

    }

    /**
     * 连接socket
     */
    public void start() {
        try {
            // 获取websocket服务器的ip与port
            if (Strings.isBlank(webSocketServerIP)) {
                logger.error("websocket ip is null");
                throw new UserInterfaceWebsocketException(UserInterfaceWebsocketException.WebsocketErrors.WEBSOCKET_CONNECT_URL_NULL);
            }
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
//         链接websocket
        reOpen();
//         启动心跳（10s）
//        startTimer();
    }


    private void startHearThread() {
        heartThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // 发送心跳报文
                CLMessageBean clMessage = new CLMessageBean("0");// 固定值
                clMessage.setBody("heart");// 固定值
                clMessage.getHead().setFrom(new ReceiverMessageBean("user", String.format("%s_%s", webSocketFromUser, MACCODE)));
                clMessage.getHead().setTos(null);
                final String notify = JaxbUtil.convertToXml(clMessage);
                socket.send(notify);
                try {
                    TimeUnit.SECONDS.sleep(12);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "WebsocketHeartThread");
        heartThread.start();
    }

    private void startReconnectThread() {
        reconnectThread = new Thread(() -> {
            if (logger.isInfoEnabled()) {
                logger.info("Retry reconnect websocket service.");
            }
            socket.reconnect();
        }, "WebsocketReconnectThread");
        reconnectThread.start();
    }

    private void stopHeartThread() {
        if (heartThread != null && heartThread.isAlive()) {
            heartThread.interrupt();
        }
    }
}
