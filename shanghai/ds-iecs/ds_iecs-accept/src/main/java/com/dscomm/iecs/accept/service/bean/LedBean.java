package com.dscomm.iecs.accept.service.bean;

import java.util.ArrayList;
import java.util.List;

public class LedBean {

    private String id;
    private String name;
    private String oldName;
    private String order;
    private Integer isDisPlay;
    private String organizationParentId;
    private String organizationParentName;
    private Integer isImportantCity;

    private List<LedBean> list=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getIsDisPlay() {
        return isDisPlay;
    }

    public void setIsDisPlay(Integer isDisPlay) {
        this.isDisPlay = isDisPlay;
    }

    public List<LedBean> getList() {
        return list;
    }

    public void setList(List<LedBean> list) {
        this.list = list;
    }

    public String getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(String organizationParentId) {
        this.organizationParentId = organizationParentId;
    }

    public String getOrganizationParentName() {
        return organizationParentName;
    }

    public void setOrganizationParentName(String organizationParentName) {
        this.organizationParentName = organizationParentName;
    }

    public Integer getIsImportantCity() {
        return isImportantCity;
    }

    public void setIsImportantCity(Integer isImportantCity) {
        this.isImportantCity = isImportantCity;
    }
}
