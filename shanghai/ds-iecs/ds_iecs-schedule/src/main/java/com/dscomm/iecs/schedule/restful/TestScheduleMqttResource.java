package com.dscomm.iecs.schedule.restful;

import com.dscomm.iecs.schedule.service.mqtt.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 描述：单位预案服务
 *
 * @author AIguibin Date time 2018/5/25 13:41
 */

@Component
@Path("rest/iecs/v1.0/schedule")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestScheduleMqttResource {

    private Environment env ;
    private MqttService mqttService ;

    @Autowired
    public TestScheduleMqttResource(Environment env  , MqttService mqttService  ) {
        this.env = env ;
        this.mqttService = mqttService ;
    }


    @Path("mqtt/sendMessage")
    @GET
    public void  sendKafkaMessage(  ) {
        try {
            //判断 是否发送 kafka 消息
            if( "true".equals( env.getProperty( "mqtt.enabled" )  )  ){
                String topic = "testTopic";
                String message = "test  mqtt  producer ";
                mqttService.pub( topic , message );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
