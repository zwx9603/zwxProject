package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：预警信息表 ( 警情预警 )
 *
 */

@Entity
@Table(name = "JCJ_YJXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EarlyWarningEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; //案件ID

    @Column(name = "JSDWBH", length = 100 )
    private String receiveOrganizationId; //预警接收单位编号

    @Column(name = "YJLX", length = 100 )
    private String earlyWarningType ; //预警类型 0预警前置 1预警

    @Column(name = "YJZT", length = 100 )
    private String earlyWarningStatus ; //预警状态 0已通知 1已接收

    @Column(name = "ZGZD", length = 100 )
    private String organizationId; //主管中队

    @Column(name = "XZQH", length = 100 )
    private String districtCode;//  行政区划编码

    @Column(name = "BJSJ"  )
    private Long alarmTime;  //报警时间

    @Column(name = "AFDZ", length = 400)
    private String crimeAddress;// 案发地址

    @Column(name = "GIS_X", length = 50)
    private String longitude;// 经度

    @Column(name = "GIS_Y", length = 50)
    private String latitude;// 纬度

    @Column(name = "GIS_H", length = 50)
    private String height;// 高度

    @Column(name = "AJLX", length = 100)
    private String incidentTypeCode;// 案件类型

    @Column(name = "AJLXZX", length = 100)
    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    @Column(name = "AJDJ", length = 100)
    private String incidentLevelCode;// 案件等级

    @Column(name = "BJDH", length = 50)
    private String alarmPhone;// 报警电话

    @Column(name = "FSDWBH", length = 100)
    private String sendOrganizationId;// 发送单位编号

    @Column(name = "FSRGH", length = 50)
    private String sendPersonNumber;// 发送人工号

    @Column(name = "FSZXH", length = 50)
    private String sendSeatNumber;// 发送坐席号

    @Column(name = "FSSJ")
    private Long sendTime;  //发送时间

    @Column(name = "CXSJ")
    private Long revokeTime; //撤销时间

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    @Column(name = "SFCSJ"  )
    private Integer whetherTestSign;//是否测试警情 0非测试警 1 测试警

    @Column(name = "TTSBFWJLJ", length = 400 )
    private String speakToFileUrl ; //新增 tts播放文件路径

    public String getSpeakToFileUrl() {
        return speakToFileUrl;
    }

    public void setSpeakToFileUrl(String speakToFileUrl) {
        this.speakToFileUrl = speakToFileUrl;
    }

    public Integer getWhetherTestSign() {
        return whetherTestSign;
    }

    public void setWhetherTestSign(Integer whetherTestSign) {
        this.whetherTestSign = whetherTestSign;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
    }

    public String getEarlyWarningStatus() {
        return earlyWarningStatus;
    }

    public void setEarlyWarningStatus(String earlyWarningStatus) {
        this.earlyWarningStatus = earlyWarningStatus;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getReceiveOrganizationId() {
        return receiveOrganizationId;
    }

    public void setReceiveOrganizationId(String receiveOrganizationId) {
        this.receiveOrganizationId = receiveOrganizationId;
    }

    public String getEarlyWarningType() {
        return earlyWarningType;
    }

    public void setEarlyWarningType(String earlyWarningType) {
        this.earlyWarningType = earlyWarningType;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Long getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getCrimeAddress() {
        return crimeAddress;
    }

    public void setCrimeAddress(String crimeAddress) {
        this.crimeAddress = crimeAddress;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getAlarmPhone() {
        return alarmPhone;
    }

    public void setAlarmPhone(String alarmPhone) {
        this.alarmPhone = alarmPhone;
    }

    public String getSendOrganizationId() {
        return sendOrganizationId;
    }

    public void setSendOrganizationId(String sendOrganizationId) {
        this.sendOrganizationId = sendOrganizationId;
    }

    public String getSendPersonNumber() {
        return sendPersonNumber;
    }

    public void setSendPersonNumber(String sendPersonNumber) {
        this.sendPersonNumber = sendPersonNumber;
    }

    public String getSendSeatNumber() {
        return sendSeatNumber;
    }

    public void setSendSeatNumber(String sendSeatNumber) {
        this.sendSeatNumber = sendSeatNumber;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Long getRevokeTime() {
        return revokeTime;
    }

    public void setRevokeTime(Long revokeTime) {
        this.revokeTime = revokeTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
