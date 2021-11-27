package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 描述:案件基本信息 保存接口 （外部警情）
 *
 */
public class SubIncidentSaveInputInfo {

    private SubUserSaveInputInfo userInfo ;

    private IncidentSaveInputInfo incident ;

    private Boolean distribute;//是否分配到处警席,true代表分配,false代表不分配

    public SubUserSaveInputInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SubUserSaveInputInfo userInfo) {
        this.userInfo = userInfo;
    }

    public IncidentSaveInputInfo getIncident() {
        return incident;
    }

    public void setIncident(IncidentSaveInputInfo incident) {
        this.incident = incident;
    }

    public Boolean getDistribute() {
        return distribute;
    }

    public void setDistribute(Boolean distribute) {
        this.distribute = distribute;
    }
}
