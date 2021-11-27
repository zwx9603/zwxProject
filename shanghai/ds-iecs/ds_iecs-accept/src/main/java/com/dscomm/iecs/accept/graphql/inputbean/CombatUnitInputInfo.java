package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述:作战单元参数
 * author:YangFuXi
 * Date:2021/6/8 Time:16:50
 **/
public class CombatUnitInputInfo {
    private String id;//id 新增时不传,修改、删除时传
    private String unitName;//作战单元名称
    private String content;//作战单元说明
    private String orgId;//所属单位id  如果不传则取登陆人所属单位
    private String orgName;//所属单位名称
    private String unitPersonId; //队长id 可以为空
    private String unitPersonName;//队长姓名
    private String unitPersonPhone;//队长联系电话
    private Integer valid=1;//有效性，新增时可以不填 1有效 0无效代表删除 如果valid为0 则除id之外其他参数都不用传了
    private List<String> vehicleIds;//车辆id集合，修改时也需要传该单元所有的车辆id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUnitPersonId() {
        return unitPersonId;
    }

    public void setUnitPersonId(String unitPersonId) {
        this.unitPersonId = unitPersonId;
    }

    public String getUnitPersonName() {
        return unitPersonName;
    }

    public void setUnitPersonName(String unitPersonName) {
        this.unitPersonName = unitPersonName;
    }

    public String getUnitPersonPhone() {
        return unitPersonPhone;
    }

    public void setUnitPersonPhone(String unitPersonPhone) {
        this.unitPersonPhone = unitPersonPhone;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
