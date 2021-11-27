package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：取水码头
 *
 * @author YangFuXi Date Time 2018/6/11 18:31
 */
@Entity
@Table(name = "SY_XFMTSXXX")
public class WaterIntakeWharfEntity extends BaseEntity {

    @Column(name = "QSMT_TYWYSBM", length = 32)
    private String idCode; // 取水码头_通用唯一识别码

    @Column(name = "SZSY_ID", length = 200)
    private String belongingWaterId; //todo 字段 所在水源ID

    @Column(name = "SZSY_MC", length = 200)
    private String belongingWaterName; //todo 字段 所在水源名称

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

    public String getBelongingWaterId() {
        return belongingWaterId;
    }

    public void setBelongingWaterId(String belongingWaterId) {
        this.belongingWaterId = belongingWaterId;
    }

    public String getBelongingWaterName() {
        return belongingWaterName;
    }

    public void setBelongingWaterName(String belongingWaterName) {
        this.belongingWaterName = belongingWaterName;
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
