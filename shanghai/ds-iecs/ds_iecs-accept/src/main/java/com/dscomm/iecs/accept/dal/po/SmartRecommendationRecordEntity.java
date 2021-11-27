package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:智能推荐服务调用记录
 *
 * @author YangFuxi
 * Date Time 2021/8/9 9:14
 */
@Entity
@Table(name = "JCJ_ZNTJJL")
public class SmartRecommendationRecordEntity extends BaseEntity {

    @Column(name = "ajid", length = 40)
    private String  incidentId; //  字段 案件id

    @Column(name = "qqsj", length = 2000)
    private String requestData ; //  字段 请求数据

    @Column(name = "fhjg", length = 2000)
    private String result ; //  字段 返回结果

    @Column(name = "lx", length = 10)
    private String type ; //  字段 类型,DJTJ,LLDP

    @Column(name = "dyrgh", length = 40)
    private String callManualNumber ; //  字段 调用人工号

    @Column(name = "dyrxm", length = 200)
    private String callManualName ; //  调用人姓名

    @Column(name = "dydwid", length = 40)
    private String callUnitId ; //  字段 调用单位id

    @Column(name = "dydwmc", length = 200)
    private String callUnitName ; // 字段 调用单位名称

    @Column(name = "hfsj")
    private Long wasteTime;//耗费时间(智能推荐服务消耗时间)

    @Column(name = "dysj")
    private Long callTime;//调用时间

    @Column(name = "sjc")
    private Long sjc;

    @Column(name = "sjjg")
    private String actualResults;//实际结果

    @Column(name = "cyyy")
    private String causeOfDifference;//差异原因
    @Column(name = "zt")
    private Integer state=0;//状态 代表是否使用，0用户未使用 1用户使用了

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCallManualNumber() {
        return callManualNumber;
    }

    public void setCallManualNumber(String callManualNumber) {
        this.callManualNumber = callManualNumber;
    }

    public String getCallManualName() {
        return callManualName;
    }

    public void setCallManualName(String callManualName) {
        this.callManualName = callManualName;
    }

    public String getCallUnitId() {
        return callUnitId;
    }

    public void setCallUnitId(String callUnitId) {
        this.callUnitId = callUnitId;
    }

    public String getCallUnitName() {
        return callUnitName;
    }

    public void setCallUnitName(String callUnitName) {
        this.callUnitName = callUnitName;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

    public String getActualResults() {
        return actualResults;
    }

    public void setActualResults(String actualResults) {
        this.actualResults = actualResults;
    }

    public String getCauseOfDifference() {
        return causeOfDifference;
    }

    public void setCauseOfDifference(String causeOfDifference) {
        this.causeOfDifference = causeOfDifference;
    }

    public Long getWasteTime() {
        return wasteTime;
    }

    public void setWasteTime(Long wasteTime) {
        this.wasteTime = wasteTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
