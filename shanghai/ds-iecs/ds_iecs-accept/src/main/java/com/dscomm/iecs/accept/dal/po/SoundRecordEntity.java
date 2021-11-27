package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述 : 录音记录信息
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/10/21 14:59
 */
@Entity
@Table(name = "JCJ_LYJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SoundRecordEntity extends BaseEntity  {

    /**
     * 案件id
     */
    @Column(name = "AJID", length = 40)
    private String  incidentId  ;


    /**
     * 录音类型 1接警录音 2处警录音  9其他
     */
    @Column(name = "LYLX" )
    private Integer type ;


    /**
     * 录音id
     */
    @Column(name = "LYXTID", length = 50 )
    private String recordId;

    /**
     * 录音号
     */
    @Column(name = "LYH", length = 50 )
    private String recordNo;

    /**
     * 录音播放路径
     */
    @Column(name = "LYBFLJ", length = 200 )
    private String  recordUrl;

    /**
     * 文件名
     */
    @Column(name = "LYWJM", length = 200 )
    private String filename;

    /**
     * 坐席号
     */
    @Column(name = "ZXH", length = 50 )
    private String seatNumber;
    /**
     * 主叫号码
     */
    @Column(name = "ZJHM", length = 50 )
    private String callerId;
    /**
     * 被叫号码
     */
    @Column(name = "BJHM", length = 50)
    private String calledId;
    /**
     * 所有成员
     */
    @Column(name = "LYCY", length = 2000)
    private String numbers;
    /**
     * 开始时间
     */
    @Column(name = "LYKSSJ"  )
    private Long beginTime;
    /**
     *  备注
     */
    @Column(name = "BZ" ,length =  800 )
    private String remark;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
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
}
