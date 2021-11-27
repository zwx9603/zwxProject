package com.dscomm.iecs.accept.graphql.fireinputbean;

public class ledInputInfo {

    private String id;
    private String name;
    private String oldName;
    private String order;
    private Integer isDisPlay;
    private Integer isImportantCity;
    private String organizationParentId;


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

    public Integer getIsImportantCity() {
        return isImportantCity;
    }

    public void setIsImportantCity(Integer isImportantCity) {
        this.isImportantCity = isImportantCity;
    }

    public String getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(String organizationParentId) {
        this.organizationParentId = organizationParentId;
    }
}
