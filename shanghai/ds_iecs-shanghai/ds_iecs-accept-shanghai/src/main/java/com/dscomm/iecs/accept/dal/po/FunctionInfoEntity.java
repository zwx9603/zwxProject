package com.dscomm.iecs.accept.dal.po;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wl_clgn")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FunctionInfoEntity extends BaseEntity {

    @Column(name = "cldm")
    private String cldm; // 车辆代码

    @Column(name = "gndm")
    private String gndm; // 功能代码

    @Column(name = "gndj")
    private String gndj; // 功能等级

    @Column(name = "gnmc")
    private String gnmc; // 功能名称

    public String getCldm() {
        return cldm;
    }

    public void setCldm(String cldm) {
        this.cldm = cldm;
    }

    public String getGndm() {
        return gndm;
    }

    public void setGndm(String gndm) {
        this.gndm = gndm;
    }

    public String getGndj() {
        return gndj;
    }

    public void setGndj(String gndj) {
        this.gndj = gndj;
    }

    public String getGnmc() {
        return gnmc;
    }

    public void setGnmc(String gnmc) {
        this.gnmc = gnmc;
    }
}
