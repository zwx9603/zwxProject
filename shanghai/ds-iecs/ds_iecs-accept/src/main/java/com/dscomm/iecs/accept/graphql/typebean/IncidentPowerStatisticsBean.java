package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述:案件出动力量统计
 */
public class IncidentPowerStatisticsBean {

    private String handleBatchNum;  //调派次数

    private String handleOrganizationNum;//出动单位数

    private String handleVehicleNum ; //出动车辆数

    private String handlePersonNum ; //出动人员数

    private String totalWater;//携带水的总量
    private String totalFoam;//携带的泡沫的总量

    public String getHandleBatchNum() {
        return handleBatchNum;
    }

    public void setHandleBatchNum(String handleBatchNum) {
        this.handleBatchNum = handleBatchNum;
    }

    public String getHandleOrganizationNum() {
        return handleOrganizationNum;
    }

    public void setHandleOrganizationNum(String handleOrganizationNum) {
        this.handleOrganizationNum = handleOrganizationNum;
    }

    public String getHandleVehicleNum() {
        return handleVehicleNum;
    }

    public void setHandleVehicleNum(String handleVehicleNum) {
        this.handleVehicleNum = handleVehicleNum;
    }

    public String getHandlePersonNum() {
        return handlePersonNum;
    }

    public void setHandlePersonNum(String handlePersonNum) {
        this.handlePersonNum = handlePersonNum;
    }

    public String getTotalWater() {
        return totalWater;
    }

    public void setTotalWater(String totalWater) {
        this.totalWater = totalWater;
    }

    public String getTotalFoam() {
        return totalFoam;
    }

    public void setTotalFoam(String totalFoam) {
        this.totalFoam = totalFoam;
    }
}
