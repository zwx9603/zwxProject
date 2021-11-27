package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述 : 录音记录信息bean
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/10/21 14:59
 */
public class SoundRecordBean extends BaseBean {

    /**
     * 案件id
     */
    private String incidentId  ;


    /**
     * 录音类型 1接警录音 2处警录音  9其他
     */
    private Integer type ;

    /**
     * 录音id
     */
    private String recordId;

    /**
     * 录音号
     */
    private String recordNo;

    /**
     * 录音播放路径
     */
    private String  recordUrl;


    /**
     * 文件名
     */
    private String filename;

    /**
     * 坐席号
     */
    private String seatNumber;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
