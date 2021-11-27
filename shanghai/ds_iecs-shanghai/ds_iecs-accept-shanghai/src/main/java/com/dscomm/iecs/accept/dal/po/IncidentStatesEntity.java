package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jcj_ajztbdjl")
public class IncidentStatesEntity extends BaseEntity {

    @Column(name = "ajid")
    private String ajid; // 案件ID

    @Column(name = "bz")
    private String bz; // 备注

    @Column(name = "cjjlid")
    private String cjjlid; // 出警记录id

    @Column(name = "cjxxid")
    private String cjxxid; // 出境信息id

    @Column(name = "jqzt_lbdm")
    private String ajztdm; // 案件状态代码

    @Column(name = "jqzt_rqsj")
    private String bdsj; // 变动时间

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

    public String getBdsj() {
        return bdsj;
    }

    public void setBdsj(String bdsj) {
        this.bdsj = bdsj;
    }
}
