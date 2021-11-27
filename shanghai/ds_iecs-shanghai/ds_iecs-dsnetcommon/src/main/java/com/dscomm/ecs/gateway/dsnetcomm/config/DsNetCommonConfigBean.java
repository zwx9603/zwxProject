package com.dscomm.ecs.gateway.dsnetcomm.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * 描述:21配置项类
 *
 * @author YangFuxi
 * Date Time 2020/9/14 19:01
 */

public class DsNetCommonConfigBean {
    @Value("${ds21.available:false}")
    private Boolean available;
    @Value("${ds21.type:}")
    private String type;
    @Value("${ds21.id:}")
    private String id;
    @Value("${ds21.isPrimary:true}")
    private Boolean primary;
    @Value("${ds21.remoteId:0}")
    private String remoteId;
    @Value("${ds21.isLog:true}")
    private Boolean writeLog;
    @Value("${ds21.cfgPath:}")
    private String cfgPath;
    @Value("${ds21.localDev:}")
    private String localDev;
    @Value("${ds21.svrPortAddr:}")
    private String svrPortAddr;
    @Value("${ds21.pollingDelay:1000l}")
    private Long pollingDelay;
    @Value("${ds21.msgType:0}")
    private String msgType;
    @Value("${ds21.msgId:}")
    private String msgId;
    @Value("${ds21.receiveFilterMsgId:}")
    private String receiveFilterMsgId;
    @Value("${charset:utf-8}")
    private String charset;

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public Boolean getWriteLog() {
        return writeLog;
    }

    public void setWriteLog(Boolean writeLog) {
        this.writeLog = writeLog;
    }

    public String getCfgPath() {
        return cfgPath;
    }

    public void setCfgPath(String cfgPath) {
        this.cfgPath = cfgPath;
    }

    public String getLocalDev() {
        return localDev;
    }

    public void setLocalDev(String localDev) {
        this.localDev = localDev;
    }

    public String getSvrPortAddr() {
        return svrPortAddr;
    }

    public void setSvrPortAddr(String svrPortAddr) {
        this.svrPortAddr = svrPortAddr;
    }

    public Long getPollingDelay() {
        return pollingDelay;
    }

    public void setPollingDelay(Long pollingDelay) {
        this.pollingDelay = pollingDelay;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getReceiveFilterMsgId() {
        return receiveFilterMsgId;
    }

    public void setReceiveFilterMsgId(String receiveFilterMsgId) {
        this.receiveFilterMsgId = receiveFilterMsgId;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
