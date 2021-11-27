package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;

/**
 * 描述 : 录音记录信息bo
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/10/21 14:59
 */
public class SoundRecordBO implements Serializable {
    /**
     * 录音号
     */
    private String recordNo;
    /**
     * 录音id
     */
    private String recordId;
    /**
     * 文件名
     */
    private String filename;
    /**
     * 坐席号
     */
    private String agentId;
    /**
     * 主叫号码
     */
    private String callerId;
    /**
     * 被叫号码
     */
    private String calledId;
    /**
     * 所有成员
     */
    private String numbers;
    /**
     * 开始时间
     */
    private Long beginTime;
    /**
     * remark
     */
    private String remark;
    /**
     * 呼叫类型（0报警，1接警回呼，2处警录音）
     */
    private Integer callRecordType;

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getCalledId() {
        return calledId;
    }

    public void setCalledId(String calledId) {
        this.calledId = calledId;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCallRecordType() { return callRecordType; }

    public void setCallRecordType(Integer callRecordType) { this.callRecordType = callRecordType; }
}
