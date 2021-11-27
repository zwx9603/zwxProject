package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：联勤保障单位 装备保障能力
 *
 */
@Entity
@Table(name = "JGXX_ZBBZNL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UnitJoinServiceSupportabilityEntity extends BaseEntity {

    @Column(name = "BZDWID", length = 100)
    private String joinServiceUnitId; //保障单位ID 联勤保障单位ID

    @Column(name = "ZBBM", length = 100)
    private String equipmentCode; //装备编码

    @Column(name = "BZZYMC", length = 200)
    private String supportResourceName; //保障资源名称

    @Column(name = "GGXH", length = 100)
    private String specificationsNumber;// 规格型号

    @Column(name = "MRSCNL", length = 100)
    private Float dailyProductionNum; //每日生产能力

    @Column(name = "JLDWDM", length = 100)
    private String measuringUnitCode; //计量单位代码

    @Column(name = "BZLBDM", length = 100)
    private String supportCategoryCode; //保障类别代码

    @Column(name = "ZDBZSL", length = 100)
    private String maxSupportNum; //最大保障数量

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本


    public String getJoinServiceUnitId() {
        return joinServiceUnitId;
    }

    public void setJoinServiceUnitId(String joinServiceUnitId) {
        this.joinServiceUnitId = joinServiceUnitId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getSupportResourceName() {
        return supportResourceName;
    }

    public void setSupportResourceName(String supportResourceName) {
        this.supportResourceName = supportResourceName;
    }

    public String getSpecificationsNumber() {
        return specificationsNumber;
    }

    public void setSpecificationsNumber(String specificationsNumber) {
        this.specificationsNumber = specificationsNumber;
    }

    public Float getDailyProductionNum() {
        return dailyProductionNum;
    }

    public void setDailyProductionNum(Float dailyProductionNum) {
        this.dailyProductionNum = dailyProductionNum;
    }

    public String getMeasuringUnitCode() {
        return measuringUnitCode;
    }

    public void setMeasuringUnitCode(String measuringUnitCode) {
        this.measuringUnitCode = measuringUnitCode;
    }

    public String getSupportCategoryCode() {
        return supportCategoryCode;
    }

    public void setSupportCategoryCode(String supportCategoryCode) {
        this.supportCategoryCode = supportCategoryCode;
    }

    public String getMaxSupportNum() {
        return maxSupportNum;
    }

    public void setMaxSupportNum(String maxSupportNum) {
        this.maxSupportNum = maxSupportNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}
