package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述: 电话报警信息（呼入记录）
 *
 */
@Entity
@Table(name = "JCJ_DHBJXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TelephoneEntity extends BaseEntity {

    @Column(name = "THJL_TYWYSBM", length = 40)
    private String idCode; //todo  字段 通话记录_通用唯一识别码

    @Column(name = "SLDID", length = 100 )
    private String acceptanceId; // 受理单ID

    @Column(name = "AJID", length = 100 )
    private String incidentId;  //案件ID

    @Column(name = "YSAJBH", length = 100 )
    private String originalIncidentNumber ;  //原始案件编号

//    @Column(name = "HJFX", length = 100 )
    @Column(name = "DHHJLXDM", length = 100 )
    private String callDirection   ; ;  //todo 字段 电话呼叫类型代码 1呼入 2呼出 9其他

    @Column(name = "ZXH", length = 50 )
    private String seatNumber; // 坐席号

    @Column(name = "XFRY_TYWYSBM", length = 100)
    private String personId; //todo 字段  警员id 消防人员_通用唯一识别码

    @Column(name = "JYXM", length = 100)
    private String personName;// 警员姓名

    @Column(name = "JYGH", length = 100)
    private String personNumber;// 警员工号

//    @Column(name = "ZJHM", length = 50 )
    @Column(name = "ZJHM_LXDH", length = 50 )
    private String callingNumber;//todo 字段 主叫号码 主叫号码_联系电话

//    @Column(name = "BJHM", length = 50)
    @Column(name = "BJHM_LXDH", length = 50)
    private String calledNumber;//todo 字段 被叫号码 被叫号码_联系电话

    @Column(name = "ACD", length = 100)
    private String acdGroupNumber;//ACD组号

//    @Column(name = "ZLSJ"  )
    @Column(name = "ZL_RQSJ"  )
    private Long ringingTime;  //todo 字段 振铃时间 ( 报警开始时间 )

//    @Column(name = "JTSJ"  )
    @Column(name = "JIT_RQSJ"  )
    private Long answerTime;  //todo 字段 接听时间 ( 接警开始时间 )

//    @Column(name = "JSSJ"  )
    @Column(name = "GJ_RQSJ"  )
    private Long endTime;  //todo 字段 结束时间 ( 报警结束时间  挂机时间)

    @Column(name = "ZLSC"  )
    private Long ringingDuration ;  //振铃时长

    @Column(name = "THSC"  )
    private Long conversationDuration;  //通话时长

    @Column(name = "LYH", length = 100 )
    private String relayRecordNumber;//录音号

    @Column(name = "ZJYHM", length = 100)
    private String installedUserName;//装机用户名

    @Column(name = "ZJDZ", length = 400)
    private String installedAddress;//装机地址

    @Column(name = "BJRXM", length = 100)
    private String alarmPersonName;//报警人姓名

    @Column(name = "BJRXB", length = 100 )
    private String alarmPersonSex;//报警人性别

    @Column(name = "BJRLXDH", length = 50)
    private String alarmPersonPhone;//报警人联系电话

    @Column(name = "BJDZ", length = 400)
    private String alarmAddress;//报警地址

    @Column(name = "GIS_X", length = 50)
    private String longitude;// 报警坐标 经度

    @Column(name = "GIS_Y", length = 50)
    private String latitude;// 报警坐标 纬度

    @Column(name = "GIS_H", length = 50)
    private String height;// 报警坐标 高度

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    @Column(name = "SFBK"  )
    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员


    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
    }

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getCallDirection() {
        return callDirection;
    }

    public void setCallDirection(String callDirection) {
        this.callDirection = callDirection;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getAcdGroupNumber() {
        return acdGroupNumber;
    }

    public void setAcdGroupNumber(String acdGroupNumber) {
        this.acdGroupNumber = acdGroupNumber;
    }

    public String getCallingNumber() {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public Long getRingingTime() {
        return ringingTime;
    }

    public void setRingingTime(Long ringingTime) {
        this.ringingTime = ringingTime;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getRingingDuration() {
        return ringingDuration;
    }

    public void setRingingDuration(Long ringingDuration) {
        this.ringingDuration = ringingDuration;
    }

    public Long getConversationDuration() {
        return conversationDuration;
    }

    public void setConversationDuration(Long conversationDuration) {
        this.conversationDuration = conversationDuration;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getInstalledUserName() {
        return installedUserName;
    }

    public void setInstalledUserName(String installedUserName) {
        this.installedUserName = installedUserName;
    }

    public String getInstalledAddress() {
        return installedAddress;
    }

    public void setInstalledAddress(String installedAddress) {
        this.installedAddress = installedAddress;
    }

    public String getAlarmPersonName() {
        return alarmPersonName;
    }

    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName;
    }

    public String getAlarmPersonSex() {
        return alarmPersonSex;
    }

    public void setAlarmPersonSex(String alarmPersonSex) {
        this.alarmPersonSex = alarmPersonSex;
    }

    public String getAlarmPersonPhone() {
        return alarmPersonPhone;
    }

    public void setAlarmPersonPhone(String alarmPersonPhone) {
        this.alarmPersonPhone = alarmPersonPhone;
    }

    public String getAlarmAddress() {
        return alarmAddress;
    }

    public void setAlarmAddress(String alarmAddress) {
        this.alarmAddress = alarmAddress;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
