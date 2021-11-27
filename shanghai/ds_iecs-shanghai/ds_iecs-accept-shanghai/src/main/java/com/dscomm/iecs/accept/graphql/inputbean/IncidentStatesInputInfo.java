package com.dscomm.iecs.accept.graphql.inputbean;

public class IncidentStatesInputInfo {
    private String ajid; // 案件ID
    private String bz; // 备注
    private String cjjlid; // 出警记录id
    private String cjxxid; // 出境信息id
    private String ajztdm; // 案件状态代码
    private String ajztdmmc; // 案件状态代码对应的名称
    private String bdsjs; // 变动时间

    public String getAjid() {
        return ajid;
    }

    public void setAjid(String ajid) {
        this.ajid = ajid;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCjjlid() {
        return cjjlid;
    }

    public void setCjjlid(String cjjlid) {
        this.cjjlid = cjjlid;
    }

    public String getCjxxid() {
        return cjxxid;
    }

    public void setCjxxid(String cjxxid) {
        this.cjxxid = cjxxid;
    }

    public String getAjztdm() {
        return ajztdm;
    }

    public void setAjztdm(String ajztdm) {
        this.ajztdm = ajztdm;
    }

    public String getAjztdmmc() {
        return ajztdmmc;
    }

    public void setAjztdmmc(String ajztdmmc) {
        this.ajztdmmc = ajztdmmc;
    }

    public String getBdsjs() {
        return bdsjs;
    }

    public void setBdsjs(String bdsjs) {
        this.bdsjs = bdsjs;
    }
}
