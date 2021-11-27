package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 指令记录保存参数
 *
 */
public class InstructionRecordSaveInputInfo {


    private String receivingObjectType;// 受令对象类型 可参考InstructionsTypeEnum 枚举
    /**
     *     VEHICLE("VEHICLE","车辆"),
     *     PERSON("PERSON","人员"),
     *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
     *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
     *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
     */
    private String receivingObjectId;// 受令对象ID

    private String receivingObjectName;// 受令对象名称

    private String receivingObjectOrganizationId;//   受令对象单位ID

    private String receivingObjectOrganizationName;//  受令对象单位名称

    private Long timeout;//超时时间

    private String remarks;// 备注


    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getReceivingObjectType() {
        return receivingObjectType;
    }

    public void setReceivingObjectType(String receivingObjectType) {
        this.receivingObjectType = receivingObjectType;
    }

    public String getReceivingObjectId() {
        return receivingObjectId;
    }

    public void setReceivingObjectId(String receivingObjectId) {
        this.receivingObjectId = receivingObjectId;
    }

    public String getReceivingObjectName() {
        return receivingObjectName;
    }

    public void setReceivingObjectName(String receivingObjectName) {
        this.receivingObjectName = receivingObjectName;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReceivingObjectOrganizationId() {
        return receivingObjectOrganizationId;
    }

    public void setReceivingObjectOrganizationId(String receivingObjectOrganizationId) {
        this.receivingObjectOrganizationId = receivingObjectOrganizationId;
    }

    public String getReceivingObjectOrganizationName() {
        return receivingObjectOrganizationName;
    }

    public void setReceivingObjectOrganizationName(String receivingObjectOrganizationName) {
        this.receivingObjectOrganizationName = receivingObjectOrganizationName;
    }

}
