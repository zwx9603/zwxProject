package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：车载人员表
 *@author YangFuXi
 *   Date: 2018年5月8日  下午2:16:19
 *
 */
@Entity
@Table(name="WL_CLRYXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VehiclePersonsEntity extends BaseEntity {

    @Column(name="CLID" , length =  100 )
    private String vehicleId;//车辆ID

    @Column(name="RYID" , length =  100 )
    private String personId;//指挥人员ID

    @Column(name="RYXM",length=200)
    private String personName;//指挥人员姓名

    @Column(name="RYLX",length=100)
    private String personType;//指挥人员类型 细类

    @Column(name="JSY",length=200)
    private  String  driver ; //驾驶员

    @Column(name="TXY",length=200)
    private  String   correspondent ; //通讯员

    //    @Column(name="SFZHY")
//    private Integer whetherCommander;//大类  1指挥员 2驾驶员 3通信员  4消防战士

    @Column(name="CDRYS")
    private Integer personNum;//车载人员数 （ 包含指挥员 ） 默认 1

    @Column(name="PX")
    private Integer sorter;//排序

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(String correspondent) {
        this.correspondent = correspondent;
    }

    //    //备用
//    @Column(name="ZHY", length =  100 )
//    private String ZHY;// 指挥员id
//
//    @Column(name="JSY", length =  100 )
//    private String JSY;// 驾驶员id
//
//    @Column(name="TXY", length =  100 )
//    private String TXY;// 通讯员id
//
//    @Column(name="XFJYRY1", length =  100 )
//    private String XFJYRY1;// 消防救援人员1ID
//
//    @Column(name="XFJYRY2", length =  100 )
//    private String XFJYRY2;//消防救援人员2ID
//
//    @Column(name="XFJYRY3", length =  100 )
//    private String XFJYRY3;//消防救援人员3ID
//
//    @Column(name="XFJYRY4", length =  100 )
//    private String XFJYRY4;//消防救援人员4ID
//
//    @Column(name="XFJYRY5", length =  100 )
//    private String XFJYRY5;//消防救援人员5ID
//
//    @Column(name="XFJYRY6", length =  100 )
//    private String XFJYRY6;//消防救援人员6ID
//
//    @Column(name="XFJYRY7", length =  100 )
//    private String XFJYRY7;//消防救援人员7ID
//
//    @Column(name="XFJYRY8", length =  100 )
//    private String XFJYRY8;//消防救援人员8ID
//
//    @Column(name="XFJYRY9", length =  100 )
//    private String XFJYRY9;//消防救援人员9ID
//
//    @Column(name="XFJYRY10", length =  100 )
//    private String XFJYRY10;//消防救援人员10ID


    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Integer getSorter() {
        return sorter;
    }

    public void setSorter(Integer sorter) {
        this.sorter = sorter;
    }

//    public Integer getWhetherCommander() {
//        return whetherCommander;
//    }
//
//    public void setWhetherCommander(Integer whetherCommander) {
//        this.whetherCommander = whetherCommander;
//    }

    //    public String getZHY() {
//        return ZHY;
//    }
//
//    public void setZHY(String ZHY) {
//        this.ZHY = ZHY;
//    }
//
//    public String getJSY() {
//        return JSY;
//    }
//
//    public void setJSY(String JSY) {
//        this.JSY = JSY;
//    }
//
//    public String getTXY() {
//        return TXY;
//    }
//
//    public void setTXY(String TXY) {
//        this.TXY = TXY;
//    }
//
//    public String getXFJYRY1() {
//        return XFJYRY1;
//    }
//
//    public void setXFJYRY1(String XFJYRY1) {
//        this.XFJYRY1 = XFJYRY1;
//    }
//
//    public String getXFJYRY2() {
//        return XFJYRY2;
//    }
//
//    public void setXFJYRY2(String XFJYRY2) {
//        this.XFJYRY2 = XFJYRY2;
//    }
//
//    public String getXFJYRY3() {
//        return XFJYRY3;
//    }
//
//    public void setXFJYRY3(String XFJYRY3) {
//        this.XFJYRY3 = XFJYRY3;
//    }
//
//    public String getXFJYRY4() {
//        return XFJYRY4;
//    }
//
//    public void setXFJYRY4(String XFJYRY4) {
//        this.XFJYRY4 = XFJYRY4;
//    }
//
//    public String getXFJYRY5() {
//        return XFJYRY5;
//    }
//
//    public void setXFJYRY5(String XFJYRY5) {
//        this.XFJYRY5 = XFJYRY5;
//    }
//
//    public String getXFJYRY6() {
//        return XFJYRY6;
//    }
//
//    public void setXFJYRY6(String XFJYRY6) {
//        this.XFJYRY6 = XFJYRY6;
//    }
//
//    public String getXFJYRY7() {
//        return XFJYRY7;
//    }
//
//    public void setXFJYRY7(String XFJYRY7) {
//        this.XFJYRY7 = XFJYRY7;
//    }
//
//    public String getXFJYRY8() {
//        return XFJYRY8;
//    }
//
//    public void setXFJYRY8(String XFJYRY8) {
//        this.XFJYRY8 = XFJYRY8;
//    }
//
//    public String getXFJYRY9() {
//        return XFJYRY9;
//    }
//
//    public void setXFJYRY9(String XFJYRY9) {
//        this.XFJYRY9 = XFJYRY9;
//    }
//
//    public String getXFJYRY10() {
//        return XFJYRY10;
//    }
//
//    public void setXFJYRY10(String XFJYRY10) {
//        this.XFJYRY10 = XFJYRY10;
//    }
}
