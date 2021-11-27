package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/1 12:58
 * @Describe 车辆状态变更查询
 */
public class VehicleStateChangeQueryInputInfo {

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    private String brigadeOrganizationId;//所属大队
    private String organizationId;//所属机构
    private String vehicleState;//车辆状态


    public Boolean getWhetherPage() {
        return whetherPage;
    }

    public void setWhetherPage(Boolean whetherPage) {
        this.whetherPage = whetherPage;
    }

    public PaginationInputInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInputInfo pagination) {
        this.pagination = pagination;
    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(String vehicleState) {
        this.vehicleState = vehicleState;
    }
}
