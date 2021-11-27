package com.dscomm.iecs.accept.graphql.typebean;

/**
 * [描述信息]:集结项实体( 集结力量信息)
 *
 */
public class RallyItemBean {

    private String id ;

    private String incidentId;// 事件ID

    private String commandId;// 指挥ID

    private String rallyPointId;// 集结点ID

    private String rallyPowerType;// 集结力量类型 VEHICLE ( 车辆 ) ORGANIZATION 机构 可拓展

    private String rallyPowerId;//   集结力量ID

    private String rallyPowerName;// 集结力量名称

    private String organizationId;// 所属消防机构ID

    private String organizationName;// 所属消防机构名称

    private String customcredit1;// 保留字段1

    private String customcredit2;// 保留字段2

    private String remarks;// 备注

    private  RallyPointBean rallyPointBean ; //集结点信息

    public RallyPointBean getRallyPointBean() {
        return rallyPointBean;
    }

    public void setRallyPointBean(RallyPointBean rallyPointBean) {
        this.rallyPointBean = rallyPointBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(String rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public String getRallyPowerType() {
        return rallyPowerType;
    }

    public void setRallyPowerType(String rallyPowerType) {
        this.rallyPowerType = rallyPowerType;
    }

    public String getRallyPowerId() {
        return rallyPowerId;
    }

    public void setRallyPowerId(String rallyPowerId) {
        this.rallyPowerId = rallyPowerId;
    }

    public String getRallyPowerName() {
        return rallyPowerName;
    }

    public void setRallyPowerName(String rallyPowerName) {
        this.rallyPowerName = rallyPowerName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getCustomcredit1() {
        return customcredit1;
    }

    public void setCustomcredit1(String customcredit1) {
        this.customcredit1 = customcredit1;
    }

    public String getCustomcredit2() {
        return customcredit2;
    }

    public void setCustomcredit2(String customcredit2) {
        this.customcredit2 = customcredit2;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
