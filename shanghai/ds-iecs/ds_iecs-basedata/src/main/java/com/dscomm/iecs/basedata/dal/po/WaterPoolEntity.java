package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：消防水池信息表
 *
 * @author YangFuXi Date Time 2018/6/11 15:25
 */
@Entity
@Table(name = "SY_XFSCSXXX")
public class WaterPoolEntity extends BaseEntity {

    @Column(name = "XFSC_TYWYSBM", length = 32)
    private String idCode; // 消防水池_通用唯一识别码

    @Column(name = "LL" , length =  50 )
    private String flowrate;   //   流量

    @Column(name = "RL" , length = 50)
    private String storage;// 容积 单位：立方米

    @Column(name = "CSL_RJ", length = 50)
    private String waterStorage;//储水量 容积 单位：立方米

    @Column(name = "QS_GD" ,length =  50 )
    private String intakeHeight;//  取水_高度

    @Column(name = "SYBGC_GD",length =  50 )
    private String  elevationHeight;//水源标高差_高度

    @Column(name = "TCWZ_DDMC", length = 400)
    private String parkingPosition;//停车位置

    @Column(name = "TC_SL")
    private Integer parkingNum;//停车数量

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

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getWaterStorage() {
        return waterStorage;
    }

    public void setWaterStorage(String waterStorage) {
        this.waterStorage = waterStorage;
    }

    public String getIntakeHeight() {
        return intakeHeight;
    }

    public void setIntakeHeight(String intakeHeight) {
        this.intakeHeight = intakeHeight;
    }

    public String getElevationHeight() {
        return elevationHeight;
    }

    public void setElevationHeight(String elevationHeight) {
        this.elevationHeight = elevationHeight;
    }

    public String getParkingPosition() {
        return parkingPosition;
    }

    public void setParkingPosition(String parkingPosition) {
        this.parkingPosition = parkingPosition;
    }

    public Integer getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(Integer parkingNum) {
        this.parkingNum = parkingNum;
    }
}
