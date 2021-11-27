package com.dscomm.iecs.schedule.service.mqtt;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("mqttServiceImpl")
public class MqttServiceImpl implements  MqttService {

    private static final Logger logger = LoggerFactory.getLogger(MqttServiceImpl.class);

    private Environment env ;
    private MqttProperties mqttProperties ;
    private Set<String> topics = new HashSet<>();
    private Boolean mqttEnabled = false ;

    private MqttPushClient client ;


    public MqttProperties getMqttProperties() {
        return mqttProperties;
    }

    public Set<String> getTopics() {
        return topics;
    }

    @Autowired
    public MqttServiceImpl( Environment env ,  MqttProperties mqttProperties ){
        this.env = env ;
        this.mqttProperties = mqttProperties ;

        mqttEnabled = "true".equals( env.getProperty( "mqtt.enabled" )  ) ;

        init() ;
    }

    private void  init(){
        if( mqttEnabled ){
            //服务器客户端初始化, 连接MQTT代理
            client  = MqttPushClient.getInstance( this ) ;
            //初始化订阅主题
            String topic = mqttProperties.getTopic() ;
            if(Strings.isNotBlank( topic ) ){
                String [] topics = topic.split(",") ;
                for( String  top : topics ){
                    sub(  top ) ;
                }
            }
        }
    }

    @Override
    public Boolean sub( String topic ) {
        if( mqttEnabled ){
            if( topics.contains( topic )){
                topics.add( topic ) ;
            }
            client.subscribe( topic );
            return  true ;
        }
        return  false ;
    }


    @Override
    public Boolean pub(  String topic , String msg ){
        if( mqttEnabled ){
            client.publish( topic , msg );
            return  true ;
        }
        return  false ;
    }

    @Override
    public Boolean disconnect(   ){
        if( mqttEnabled ){
            client.disconnect(   );
            return  true ;
        }
        return  false ;
    }


}
