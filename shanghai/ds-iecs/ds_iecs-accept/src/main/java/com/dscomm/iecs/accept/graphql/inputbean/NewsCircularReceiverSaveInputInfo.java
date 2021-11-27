package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 消息通知 接收者
 */
public class NewsCircularReceiverSaveInputInfo {

    private String receivingObjectType;// 受令对象类型 可参考InstructionsTypeEnum 枚举
    /**
     *     ORGANIZATION("ORGANIZATION","机构"),
     *     VEHICLE("VEHICLE","车辆"),
     *     PERSON("PERSON","人员"),
     *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
     *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
     *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
     */

    private String  receiverObjectId ;	//通知对象id（单位id）

    private String receivingObjectName;// 受令对象名称

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

    public String getReceiverObjectId() {
        return receiverObjectId;
    }

    public void setReceiverObjectId(String receiverObjectId) {
        this.receiverObjectId = receiverObjectId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
