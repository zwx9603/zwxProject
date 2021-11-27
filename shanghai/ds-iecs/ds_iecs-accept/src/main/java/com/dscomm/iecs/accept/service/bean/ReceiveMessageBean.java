package com.dscomm.iecs.accept.service.bean;


/**
 *   转警 等其他接收结果 回执信息
 */
public class ReceiveMessageBean {

    private String  receiveMessageId  ; //接收信息id

    private String originalIncidentNumber;// 原始警情记录id

    private String receiveAgentNumber;// 接收处理坐席

    private Integer backResult  ; //回执结果  0 拒绝 1接收

    public Integer getBackResult() {
        return backResult;
    }

    public void setBackResult(Integer backResult) {
        this.backResult = backResult;
    }

    public String getReceiveMessageId() {
        return receiveMessageId;
    }

    public void setReceiveMessageId(String receiveMessageId) {
        this.receiveMessageId = receiveMessageId;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getReceiveAgentNumber() {
        return receiveAgentNumber;
    }

    public void setReceiveAgentNumber(String receiveAgentNumber) {
        this.receiveAgentNumber = receiveAgentNumber;
    }
}
