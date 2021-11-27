package com.dscomm.iecs.schedule.service.mqtt;


import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttPushClient {

    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);

    private MqttClient client ;
    private MqttServiceImpl mqttService ;
    private MqttProperties mqttProperties ;
    private static  volatile MqttPushClient instance = null ;

    public  static  MqttPushClient getInstance( MqttServiceImpl mqttService ){
        if( instance == null ){
            synchronized ( MqttPushClient.class ){
                if( instance == null ){
                    instance = new MqttPushClient( mqttService ) ;
                }
            }
        }
        return  instance ;
    }


    private  MqttPushClient( MqttServiceImpl mqttService ){
        logger.info( "MQTT客户端[{}]连接" , mqttService.getMqttProperties().getClientId() );
        this.mqttService = mqttService ;
        this.mqttProperties = mqttService.getMqttProperties() ;
        connect() ;

    }


    private  void  connect(){
        try {
            String  clientId =  mqttProperties.getClientId()  + "_SCHEDULE" ;
            client = new MqttClient( mqttProperties.getUrl() , clientId , new MemoryPersistence() ) ;
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession( mqttProperties.getCleanSession() );
            options.setUserName( mqttProperties.getUsername() );
            options.setPassword( mqttProperties.getPassword().toCharArray()  );
            options.setConnectionTimeout( mqttProperties.getConnectionTimeOut() );
            options.setKeepAliveInterval( mqttProperties.getKeepAliveInterval() );
            options.setAutomaticReconnect( mqttProperties.getAutomaticReconnect() );
            options.setWill( "SERVER_OFFLINE_TOPIC" , "ServerOffline".getBytes() ,2 , true );
            //将业务处理对象回调类
            client.setCallback( new PushCallback( mqttService ) );
            client.connect( options );
        } catch ( MqttException e){
            logger.error( "MQTT客户端[{}]连接异常:{}" , mqttProperties.getClientId() , e.toString() );
        }
    }


    public void publish ( String topic , String data ){
        publish( topic , data , 1 , false );
    }

    public void publish ( String topic , String data , Integer qos , Boolean retained ){
        try {
            logger.info("----------mqtt produceMessage start ----------");


            logger.info("====topic=="+topic+"========message =" + data);

            MqttMessage mqttMessage = new MqttMessage() ;
            mqttMessage.setPayload( data.getBytes() );
            mqttMessage.setQos( qos );
            mqttMessage.setRetained( retained );
            MqttTopic mqttTopic =  client.getTopic( topic ) ;

            MqttDeliveryToken token = mqttTopic.publish( mqttMessage );
            token.waitForCompletion();
            logger.info( "[{}]发布主题[{}],消息:[{}]" , mqttProperties.getClientId() ,  topic , data  );

            logger.info("----------mqtt produceMessage end ---------- " );

        }catch ( Exception e){
            logger.error( "发布消息异常:{}" ,   e.toString() );
        }


    }


    public void subscribe( String topic ){
        subscribe( topic , 1 );
    }

    public void subscribe( String topic , Integer qos  ){
        try {
             client.subscribe( topic , qos );
            logger.info( "[{}]订阅主题[{}] " , mqttProperties.getClientId() ,  topic    );
        }catch ( Exception e ){
            logger.error( "客户端订阅异常:{}" ,   e.toString() );
        }
    }

    public  void  disconnect(){
        try {
            client.disconnect();
            logger.info( "[{}]主动断开连接 " , mqttProperties.getClientId()     );
        }catch ( Exception e){
            logger.error( "主动断开连接异常:{}" ,   e.toString() );
        }

    }




}
