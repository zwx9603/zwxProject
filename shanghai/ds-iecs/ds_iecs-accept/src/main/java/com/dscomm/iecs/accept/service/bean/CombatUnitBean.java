package com.dscomm.iecs.accept.service.bean;

import java.util.List;

/**
 * 描述:作战单元信息
 * author:YangFuXi
 * Date:2021/6/8 Time:16:13
 **/
public class CombatUnitBean {
    private String id;
    private String unitName;//作战单元名称
    private String content;//作战单元说明
    private String orgId;//所属单位id
    private String orgName;//所属单位名称
    private String unitPersonId; //队长id
    private String unitPersonName;//队长姓名
    private String unitPersonPhone;//队长联系电话
    private String authorAccount;//编写人账号
    private String authorName;//编写人名称
    private Integer valid;//默认为true
    private Integer orgNum;//单位数量
    private Integer vehicleNum;//车辆数
    private List<CombatUnitItemBean> itemBeans;//明细(车辆)

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

    public String getAuthorAccount() {
        return authorAccount;
    }

    public void setAuthorAccount(String authorAccount) {
        this.authorAccount = authorAccount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public List<CombatUnitItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<CombatUnitItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }

    public Integer getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(Integer orgNum) {
        this.orgNum = orgNum;
    }

    public Integer getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(Integer vehicleNum) {
        this.vehicleNum = vehicleNum;
    }
}
