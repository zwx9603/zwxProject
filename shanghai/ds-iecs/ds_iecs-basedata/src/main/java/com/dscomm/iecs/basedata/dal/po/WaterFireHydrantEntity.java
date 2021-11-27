package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 消火栓
 *
 * @author AIguibin
 * Date time 2018年4月27日 下午2:01:20
 */
@Entity
@Table(name = "SY_XHSSXXX")
public class WaterFireHydrantEntity extends BaseEntity {

    @Column(name = "XHS_TYWYSBM", length = 32)
    private String idCode; // 消火栓_通用唯一识别码

    @Column(name = "LL" , length =  50 )
    private String flowrate;   //流量

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getFlowrate() {
        return flowrate;
    }

    public void setFlowrate(String flowrate) {
        this.flowrate = flowrate;
    }
}
