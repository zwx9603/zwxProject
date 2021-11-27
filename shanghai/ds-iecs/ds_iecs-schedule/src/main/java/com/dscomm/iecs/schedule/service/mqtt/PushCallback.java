package com.dscomm.iecs.schedule.service.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


public class PushCallback implements MqttCallbackExtended {

    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);

    private MqttServiceImpl mqttService ;

    public  PushCallback ( MqttServiceImpl mqttService ){
        this.mqttService = mqttService;
    }

    @Override
    public  void messageArrived(String topic  , MqttMessage mqttMessage ) throws  Exception {
         //订阅主题消费信息
        logger.info("----------mqtt consumeMessage start ----------");

        String  message = new String( mqttMessage.getPayload() )  ;

        logger.info("====topic=="+topic+"========message =" + message);
        //todo 业务逻辑

        logger.info("----------mqtt consumeMessage handle successful --------");

        logger.info("----------mqtt consumeMessage end ---------- " );

    }

    @Override
    public  void deliveryComplete (IMqttDeliveryToken iMqttDeliveryToken )   {
        logger.info("发送完成?--->[{}]" , iMqttDeliveryToken.isComplete()  );
    }

    @Override
    public  void connectionLost( Throwable throwable ){
        logger.error("连接断开,可以重连....");
    }


    @Override
    public  void connectComplete( boolean b, String s ){
        //重连后重新订阅之前的topic
        Set<String> topics  = mqttService.getTopics() ;
        for(  String topic : topics ){
            mqttService.sub( topic ) ;
        }
    }

}
