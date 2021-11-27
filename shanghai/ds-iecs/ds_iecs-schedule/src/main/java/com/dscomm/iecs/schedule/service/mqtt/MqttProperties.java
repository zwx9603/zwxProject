package com.dscomm.iecs.schedule.service.mqtt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttProperties {

    @Value("${mqtt.url:tcp://127.0.0.1:61613}")
    private String url;  // mqtt 服务器地址

    @Value("${mqtt.username:admin}")
    private String username;

    @Value("${mqtt.password:password}")
    private String password;

    @Value("${mqtt.clientId:mqttClientId}")
    private String clientId;

    @Value("${mqtt.connectionTimeOut:10}")
    private int connectionTimeOut ;   //连接超时

    @Value("${mqtt.keepAliveInterval:20}")
    private int keepAliveInterval ;   //心跳间隔

    @Value("${mqtt.cleanSession:false}")
    private Boolean cleanSession;

    @Value("${mqtt.automaticReconnect:true}")
    private Boolean automaticReconnect;

    @Value("${mqtt.topic:null}")
    private String  topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public int getKeepAliveInterval() {
        return keepAliveInterval;
    }

    public void setKeepAliveInterval(int keepAliveInterval) {
        this.keepAliveInterval = keepAliveInterval;
    }

    public Boolean getCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(Boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public Boolean getAutomaticReconnect() {
        return automaticReconnect;
    }

    public void setAutomaticReconnect(Boolean automaticReconnect) {
        this.automaticReconnect = automaticReconnect;
    }
}
