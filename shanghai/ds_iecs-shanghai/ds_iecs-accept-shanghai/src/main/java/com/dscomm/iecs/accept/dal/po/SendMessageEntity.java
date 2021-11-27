package com.dscomm.iecs.accept.dal.po;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.mx.dal.entity.PO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ldlb")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SendMessageEntity implements PO {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "dhhm")
    private String dhhm; //电话号码

    @Column(name = "ldmc")
    private String ldmc; //

    @Column(name = "issend")
    private String issend; //是否发送

    @Column(name = "ssjgbh")
    private String ssjgbh; // 所属机构编号

    @Column(name = "ryid")
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
