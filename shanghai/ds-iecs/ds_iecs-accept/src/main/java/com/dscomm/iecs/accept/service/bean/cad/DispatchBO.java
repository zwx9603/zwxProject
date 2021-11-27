package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;
import java.util.List;

/**
 * 描述:处警单（上级单位调度单）表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/29 13:44
 */
public class DispatchBO implements Serializable {
    /**
     * 处警单编号（上级单位调度单）
     */
    private String id;
    /**
     * 行政区划
     */
    private String area;
    /**
     * 行政区划name
     */
    private String areaName;
    /**
     * 事件单编号
     */
    private String incidentId;
    /**
     * 处警类型（默认10）
     */
    private String dispatchType;
    /**
     * 处警类型name
     */
    private String dispatchTypeName;
    /**
     * 处警开始时间
     */
    private Long dispatchStartTime;
    /**
     * 处警单位code
     */
    private String dispatchOrgCode;
    /**
     * 处警单位name
     */
    private String dispatchOrgName;
    /**
     * 处警员工号
     */
    private String dispatchPersonAccount;
    /**
     * 处警员姓名
     */
    private String dispatchPersonName;
    /**
     * 处警员角色code
     */
    private String dispatchPersonRoleCode;
    /**
     * 处警员角色name
     */
    private String dispatchPersonRoleName;
    /**
     * 处警措施(调派单内容)
     */
    private String content;
    /**
     * 处警结束时间
     */
    private Long dispatchEndTime;
    /**
     * 等待处警时长
     */
    private Integer dispatchWaitTime;
    /**
     * 处警时长
     */
    private Integer dispatchDuration;
    /**
     * 处警单状态
     */
    private Integer dispatchState;
    /**
     * 处警单状态name
     */
    private String dispatchStateName;
    /**
     * 处警台号
     */
    private Integer dispatchAgentNum;
    /**
     * 时间戳
     */
    private Long sjc;
    /**
     * 来源
     */
    private String source;
    /**
     * 指令单
     */
    private List<OrderInfoBO> orderInfoList;
    /**
     * 录音信息列表
     */
    private List<SoundRecordBO> soundRecordList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getDispatchTypeName() {
        return dispatchTypeName;
    }

    public void setDispatchTypeName(String dispatchTypeName) {
        this.dispatchTypeName = dispatchTypeName;
    }

    public Long getDispatchStartTime() {
        return dispatchStartTime;
    }

    public void setDispatchStartTime(Long dispatchStartTime) {
        this.dispatchStartTime = dispatchStartTime;
    }

    public String getDispatchOrgCode() {
        return dispatchOrgCode;
    }

    public void setDispatchOrgCode(String dispatchOrgCode) {
        this.dispatchOrgCode = dispatchOrgCode;
    }

    public String getDispatchOrgName() {
        return dispatchOrgName;
    }

    public void setDispatchOrgName(String dispatchOrgName) {
        this.dispatchOrgName = dispatchOrgName;
    }

    public String getDispatchPersonAccount() {
        return dispatchPersonAccount;
    }

    public void setDispatchPersonAccount(String dispatchPersonAccount) {
        this.dispatchPersonAccount = dispatchPersonAccount;
    }

    public String getDispatchPersonName() {
        return dispatchPersonName;
    }

    public void setDispatchPersonName(String dispatchPersonName) {
        this.dispatchPersonName = dispatchPersonName;
    }

    public String getDispatchPersonRoleCode() {
        return dispatchPersonRoleCode;
    }

    public void setDispatchPersonRoleCode(String dispatchPersonRoleCode) {
        this.dispatchPersonRoleCode = dispatchPersonRoleCode;
    }

    public String getDispatchPersonRoleName() {
        return dispatchPersonRoleName;
    }

    public void setDispatchPersonRoleName(String dispatchPersonRoleName) {
        this.dispatchPersonRoleName = dispatchPersonRoleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDispatchEndTime() {
        return dispatchEndTime;
    }

    public void setDispatchEndTime(Long dispatchEndTime) {
        this.dispatchEndTime = dispatchEndTime;
    }

    public Integer getDispatchWaitTime() {
        return dispatchWaitTime;
    }

    public void setDispatchWaitTime(Integer dispatchWaitTime) {
        this.dispatchWaitTime = dispatchWaitTime;
    }

    public Integer getDispatchDuration() {
        return dispatchDuration;
    }

    public void setDispatchDuration(Integer dispatchDuration) {
        this.dispatchDuration = dispatchDuration;
    }

    public Integer getDispatchState() {
        return dispatchState;
    }

    public void setDispatchState(Integer dispatchState) {
        this.dispatchState = dispatchState;
    }

    public String getDispatchStateName() {
        return dispatchStateName;
    }

    public void setDispatchStateName(String dispatchStateName) {
        this.dispatchStateName = dispatchStateName;
    }

    public Integer getDispatchAgentNum() {
        return dispatchAgentNum;
    }

    public void setDispatchAgentNum(Integer dispatchAgentNum) {
        this.dispatchAgentNum = dispatchAgentNum;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<OrderInfoBO> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfoBO> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public List<SoundRecordBO> getSoundRecordList() {
        return soundRecordList;
    }

    public void setSoundRecordList(List<SoundRecordBO> soundRecordList) {
        this.soundRecordList = soundRecordList;
    }

//    /**
//     * 处警单编号（上级单位调度单）
//     */
//    private String id;
//    /**
//     * 行政区划
//     */
//    private String area;
//    /**
//     * 行政区划name
//     */
//    private String areaName;
//    /**
//     * 事件单编号
//     */
//    private String incidentId;
//    /**
//     * 处警类型（默认10：首次处警;20：再次处警）
//     */
//    private String alarmType;
//    /**
//     * 处警类型name
//     */
//    private String alarmTypeName;
//    /**
//     * 处警开始时间
//     */
//    private Long giveTime;
//    /**
//     * 处警单位code
//     */
//    private String giveOrgCode;
//    /**
//     * 处警单位id
//     */
//    private String giveOrgId;
//    /**
//     * 处警单位name
//     */
//    private String giveOrgName;
//    /**
//     * 处警员工号
//     */
//    private String givePerId;
//    /**
//     * 处警员姓名
//     */
//    private String givePerName;
//    /**
//     * 处警措施(调派单内容)
//     */
//    private String content;
//    /**
//     * 处警结束时间
//     */
//    private Long alarmEndTime;
//    /**
//     * 等待处警时长
//     */
//    private Integer alarmWaitTime;
//    /**
//     * 处警时长
//     */
//    private Integer alarmTime;
//    /**
//     * 处警单状态
//     */
//    private Integer alarmStatus;
//    /**
//     * 处警单状态name
//     */
//    private String alarmStatusName;
//    /**
//     * 处警台号
//     */
//    private Integer alarmStationCode;
//    /**
//     * 时间戳
//     */
//    private Long sjc;
//    /**
//     * 指令单
//     */
//    private List<OrderBO> orderBOList;
//    /**
//     * 录音信息列表
//     */
//    private List<SoundRecordBO> soundRecordList;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getArea() {
//        return area;
//    }
//
//    public void setArea(String area) {
//        this.area = area;
//    }
//
//    public String getIncidentId() {
//        return incidentId;
//    }
//
//    public String getAreaName() {
//        return areaName;
//    }
//
//    public void setAreaName(String areaName) {
//        this.areaName = areaName;
//    }
//
//    public void setIncidentId(String incidentId) {
//        this.incidentId = incidentId;
//    }
//
//    public String getAlarmType() {
//        return alarmType;
//    }
//
//    public void setAlarmType(String alarmType) {
//        this.alarmType = alarmType;
//    }
//
//    public String getAlarmTypeName() {
//        return alarmTypeName;
//    }
//
//    public void setAlarmTypeName(String alarmTypeName) {
//        this.alarmTypeName = alarmTypeName;
//    }
//
//    public Long getGiveTime() {
//        return giveTime;
//    }
//
//    public void setGiveTime(Long giveTime) {
//        this.giveTime = giveTime;
//    }
//
//    public String getGiveOrgCode() {
//        return giveOrgCode;
//    }
//
//    public void setGiveOrgCode(String giveOrgCode) {
//        this.giveOrgCode = giveOrgCode;
//    }
//
//    public String getGiveOrgId() {
//        return giveOrgId;
//    }
//
//    public void setGiveOrgId(String giveOrgId) {
//        this.giveOrgId = giveOrgId;
//    }
//
//    public String getGiveOrgName() {
//        return giveOrgName;
//    }
//
//    public void setGiveOrgName(String giveOrgName) {
//        this.giveOrgName = giveOrgName;
//    }
//
//    public String getGivePerId() {
//        return givePerId;
//    }
//
//    public void setGivePerId(String givePerId) {
//        this.givePerId = givePerId;
//    }
//
//    public String getGivePerName() {
//        return givePerName;
//    }
//
//    public void setGivePerName(String givePerName) {
//        this.givePerName = givePerName;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Long getAlarmEndTime() {
//        return alarmEndTime;
//    }
//
//    public void setAlarmEndTime(Long alarmEndTime) {
//        this.alarmEndTime = alarmEndTime;
//    }
//
//    public Integer getAlarmWaitTime() {
//        return alarmWaitTime;
//    }
//
//    public void setAlarmWaitTime(Integer alarmWaitTime) {
//        this.alarmWaitTime = alarmWaitTime;
//    }
//
//    public Integer getAlarmTime() {
//        return alarmTime;
//    }
//
//    public void setAlarmTime(Integer alarmTime) {
//        this.alarmTime = alarmTime;
//    }
//
//    public Integer getAlarmStatus() {
//        return alarmStatus;
//    }
//
//    public void setAlarmStatus(Integer alarmStatus) {
//        this.alarmStatus = alarmStatus;
//    }
//
//    public String getAlarmStatusName() {
//        return alarmStatusName;
//    }
//
//    public void setAlarmStatusName(String alarmStatusName) {
//        this.alarmStatusName = alarmStatusName;
//    }
//
//    public Integer getAlarmStationCode() {
//        return alarmStationCode;
//    }
//
//    public void setAlarmStationCode(Integer alarmStationCode) {
//        this.alarmStationCode = alarmStationCode;
//    }
//
//    public Long getSjc() {
//        return sjc;
//    }
//
//    public void setSjc(Long sjc) {
//        this.sjc = sjc;
//    }
//
//    public List<OrderBO> getOrderBOList() {
//        return orderBOList;
//    }
//
//    public void setOrderBOList(List<OrderBO> orderBOList) {
//        this.orderBOList = orderBOList;
//    }
//
//    public List<SoundRecordBO> getSoundRecordList() {
//        return soundRecordList;
//    }
//
//    public void setSoundRecordList(List<SoundRecordBO> soundRecordList) {
//        this.soundRecordList = soundRecordList;
//    }
}
