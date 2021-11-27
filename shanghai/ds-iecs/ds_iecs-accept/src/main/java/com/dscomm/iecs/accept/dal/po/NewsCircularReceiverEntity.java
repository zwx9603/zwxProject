package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 消息通知 接收者
 */
@Entity
@Table(name = "JCJ_XXTZJSDX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class NewsCircularReceiverEntity extends BaseEntity {

    @Column(name = "XXTZID",length=100)
    private String newsCircularId;		//消息通知id

    @Column(name = "TZDXLX", length = 100)
    private String receivingObjectType;// 受令对象类型 可参考InstructionsTypeEnum 枚举
    /**
     *     ORGANIZATION("ORGANIZATION","机构"),
     *     VEHICLE("VEHICLE","车辆"),
     *     PERSON("PERSON","人员"),
     *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
     *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
     *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
     */

    @Column(name = "TZDXID" )
    private String  receiverObjectId  ;	//通知对象id

    @Column(name = "TZDXMC" )
    private String receivingObjectName;// 受令对象名称

    @Column(name = "ZT"  )
    private Integer newsCircularStatus ; //状态 0已通知 1已接收

    @Column(name = "TZSJ" )
    private Long circularTime ;	//通知时间

    @Column(name = "JSSJ" )
    private Long operateTime ;	//接收时间

    @Column(name = "JSRID",length=100)
    private String operatePersonId ;	//接收者id

    @Column(name = "JSRXM" ,length=100)
    private String operatePersonName ;	//接收者姓名

    @Column(name = "BZ", length = 800)
    private String remarks; // 备注


    public String getReceivingObjectName() {
        return receivingObjectName;
    }

    public void setReceivingObjectName(String receivingObjectName) {
        this.receivingObjectName = receivingObjectName;
    }

    public String getReceivingObjectType() {
        return receivingObjectType;
    }

    public void setReceivingObjectType(String receivingObjectType) {
        this.receivingObjectType = receivingObjectType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNewsCircularId() {
        return newsCircularId;
    }

    public void setNewsCircularId(String newsCircularId) {
        this.newsCircularId = newsCircularId;
    }

    public String getReceiverObjectId() {
        return receiverObjectId;
    }

    public void setReceiverObjectId(String receiverObjectId) {
        this.receiverObjectId = receiverObjectId;
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
}
