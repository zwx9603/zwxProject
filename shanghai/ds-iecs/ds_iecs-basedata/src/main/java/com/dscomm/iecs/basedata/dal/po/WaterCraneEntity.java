package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：水鹤信息表
 *
 * @author YangFuXi Date Time 2018/6/11 16:33
 */
@Entity
@Table(name = "SY_XFSHSXXX")
public class WaterCraneEntity extends BaseEntity {

    @Column(name = "XFSH_TYWYSBM", length = 32)
    private String idCode; //  消防水鹤_通用唯一识别码

    @Column(name = "GD" ,length =  50 )
    private String waterCraneHeight;//  水鹤高度

    @Column(name = "LL" , length =  50 )
    private String flowrate;   //  流量

    @Column(name = "JSGZJ_KD", length =  50 )
    private String inletDiameter;//  进水直径

    @Column(name = "CSGZJ_KD", length =  50 )
    private String effluentDiameter;//  出水直径

    @Column(name = "JSCDS_SL" )
    private Integer waterDriveway;//加水车道

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getWaterCraneHeight() {
        return waterCraneHeight;
    }

    public void setWaterCraneHeight(String waterCraneHeight) {
        this.waterCraneHeight = waterCraneHeight;
    }

    public String getFlowrate() {
        return flowrate;
    }

    public void setFlowrate(String flowrate) {
        this.flowrate = flowrate;
    }

    public String getInletDiameter() {
        return inletDiameter;
    }

    public void setInletDiameter(String inletDiameter) {
        this.inletDiameter = inletDiameter;
    }

    public String getEffluentDiameter() {
        return effluentDiameter;
    }

    public void setEffluentDiameter(String effluentDiameter) {
        this.effluentDiameter = effluentDiameter;
    }

    public Integer getWaterDriveway() {
        return waterDriveway;
    }

    public void setWaterDriveway(Integer waterDriveway) {
        this.waterDriveway = waterDriveway;
    }
}
