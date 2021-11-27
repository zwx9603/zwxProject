package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 消息通知 接收者
 */
public class NewsCircularReceiverBean extends BaseBean {

    private String newsCircularId;		//消息通知id

    private String receivingObjectType;// 受令对象类型 可参考InstructionsTypeEnum 枚举
    /**
     *     ORGANIZATION("ORGANIZATION","机构"),
     *     VEHICLE("VEHICLE","车辆"),
     *     PERSON("PERSON","人员"),
     *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
     *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
     *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
     */

    private String  receivingObjectId ;	//通知对象id（单位id）

    private String  receivingObjectName ;	//通知对象名称（单位名称）

    private Integer newsCircularStatus ;		//状态 0已通知 1已接收

    private Long circularTime ;	//通知时间

    private Long operateTime ;	//接收时间

    private String operatePersonId ;	//接收者id

    private String operatePersonName ;	//接收者姓名

    private String remarks; // 备注

    private NewsCircularBean newsCircularBean ; //通知消息

    public String getReceivingObjectType() {
        return receivingObjectType;
    }

    public void setReceivingObjectType(String receivingObjectType) {
        this.receivingObjectType = receivingObjectType;
    }

    public String getReceivingObjectName() {
        return receivingObjectName;
    }

    public void setReceivingObjectName(String receivingObjectName) {
        this.receivingObjectName = receivingObjectName;
    }

    public String getNewsCircularId() {
        return newsCircularId;
    }

    public void setNewsCircularId(String newsCircularId) {
        this.newsCircularId = newsCircularId;
    }

    public String getReceivingObjectId() {
        return receivingObjectId;
    }

    public void setReceivingObjectId(String receivingObjectId) {
        this.receivingObjectId = receivingObjectId;
    }

    public Integer getNewsCircularStatus() {
        return newsCircularStatus;
    }

    public void setNewsCircularStatus(Integer newsCircularStatus) {
        this.newsCircularStatus = newsCircularStatus;
    }

    public Long getCircularTime() {
        return circularTime;
    }

    public void setCircularTime(Long circularTime) {
        this.circularTime = circularTime;
    }


    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperatePersonId() {
        return operatePersonId;
    }

    public void setOperatePersonId(String operatePersonId) {
        this.operatePersonId = operatePersonId;
    }

    public String getOperatePersonName() {
        return operatePersonName;
    }

    public void setOperatePersonName(String operatePersonName) {
        this.operatePersonName = operatePersonName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public NewsCircularBean getNewsCircularBean() {
        return newsCircularBean;
    }

    public void setNewsCircularBean(NewsCircularBean newsCircularBean) {
        this.newsCircularBean = newsCircularBean;
    }
}
