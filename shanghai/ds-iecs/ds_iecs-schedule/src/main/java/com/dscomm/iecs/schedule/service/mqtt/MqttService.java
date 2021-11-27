package com.dscomm.iecs.schedule.service.mqtt;

public interface MqttService {

    /**
     *  订阅主题
     * @param topic
     */
    Boolean   sub ( String topic ) ;

    /**
     *  发布消息
     * @param topic
     * @param msg
     * @return
     */
    Boolean pub(  String topic , String msg ) ;

    /**
     * 关闭连接
     */
    Boolean  disconnect() ;

}
