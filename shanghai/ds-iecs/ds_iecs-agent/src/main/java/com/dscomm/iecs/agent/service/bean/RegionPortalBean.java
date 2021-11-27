package com.dscomm.iecs.agent.service.bean;

public class RegionPortalBean {

    private String        id;             //行政区域ID
    private String        code;           //行政区划编码
    private String        name;           //行政区划名称
    private Boolean       valid;          //是否有效，此处为null
    private String        parentId;       //上级行政区划ID，此处为null
    private Long createTime;     //创建时间，此处为null
    private Long          updateTime;     //更新时间，此处为null
    private String        memo;           //备注，此处为null
    private String        queryCode;      //查询码，此处为null
    private String         queryCodePath;    //查询码路径，此处为null
    private OrganizationPortalBean competentOrg; // 主管单位，此处为null

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getQueryCode() {
        return queryCode;
    }

    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode;
    }

    public String getQueryCodePath() {
        return queryCodePath;
    }

    public void setQueryCodePath(String queryCodePath) {
        this.queryCodePath = queryCodePath;
    }

    public OrganizationPortalBean getCompetentOrg() {
        return competentOrg;
    }

    public void setCompetentOrg(OrganizationPortalBean competentOrg) {
        this.competentOrg = competentOrg;
    }
}
