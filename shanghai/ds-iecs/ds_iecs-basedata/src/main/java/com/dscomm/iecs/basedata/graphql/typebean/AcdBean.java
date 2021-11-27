package com.dscomm.iecs.basedata.graphql.typebean;

/**
 * 描述:system bo
 *
 * @author LiLu
 * Date Time 2019/7/25 0025 13:46
 */
public class AcdBean {

    /**
     * 字典项id
     */
    private String id;
    /**
     * 序号
     */
    private String number;
    /**
     * system
     */
    private String acd;
    /**
     * 描述
     */
    private String describe;
    /**
     * 警种
     */
    private String policeType;
    /**
     * 话务类型
     */
    private String callType;
    /**
     * 报警号码
     */
    private String alarmNumber;
    /**
     * 时间戳
     */
    private Long sjc;

    /**
     * 单位code
     */
    private String orgCode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAcd() {
        return acd;
    }

    public void setAcd(String acd) {
        this.acd = acd;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
