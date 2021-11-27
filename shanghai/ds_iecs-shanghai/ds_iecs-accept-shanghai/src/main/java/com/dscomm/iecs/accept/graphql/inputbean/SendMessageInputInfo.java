package com.dscomm.iecs.accept.graphql.inputbean;

public class SendMessageInputInfo {
    private String id; // id
    private String dhhm; // 电话号码
    private String ldmc; // 被发送人姓名
    private String issend; // 是否发送
    private String ssjgbh; // 所属机构编号
    private String ryid; // 人员id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDhhm() {
        return dhhm;
    }

    public void setDhhm(String dhhm) {
        this.dhhm = dhhm;
    }

    public String getLdmc() {
        return ldmc;
    }

    public void setLdmc(String ldmc) {
        this.ldmc = ldmc;
    }

    public String getIssend() {
        return issend;
    }

    public void setIssend(String issend) {
        this.issend = issend;
    }

    public String getSsjgbh() {
        return ssjgbh;
    }

    public void setSsjgbh(String ssjgbh) {
        this.ssjgbh = ssjgbh;
    }

    public String getRyid() {
        return ryid;
    }

    public void setRyid(String ryid) {
        this.ryid = ryid;
    }
}
