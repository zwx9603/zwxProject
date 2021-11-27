package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：天然水源
 *
 * @author YangFuXi Date Time 2018/6/11 18:47
 */
@Entity
@Table(name = "SY_TRSYXX")
@DynamicUpdate(true)
@DynamicInsert(true)
public class WaterNaturalSourceEntity extends BaseEntity {

    @Column(name = "TRSY_TYWYSBM", length = 32)
    private String idCode; // 天然水源_通用唯一识别码

    @Column(name = "SYLXDM", length = 100)
    private String naturalWaterTypeCode ;//天然水源类型　

    @Column(name = "GD" ,length =  50 )
    private String waterNaturalSourceHeight;//  天然水源高度

    @Column(name = "RL" , length = 50)
    private String storage;// 容积 单位：立方米

    @Column(name = "MJ" , length = 50)
    private String area;// 面积 单位：立方米

    @Column(name = "SZQK_JYQK", length = 800 )
    private String waterQualityDesc ;//水质情况_简要情况

    @Column(name = "SJBH_JYQK", length = 50)
    private String seasonalChangesDesc;//四季变化_简要情况

    @Column(name = "YWKSQ_PDBZ" )
    private Integer whetherDrySeason;//有无枯水期_判断标识

    @Column(name = "KSQKD_JYQK", length = 800)
    private String drySeasonDesc ;//枯水期跨度_简要情况

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getNaturalWaterTypeCode() {
        return naturalWaterTypeCode;
    }

    public void setNaturalWaterTypeCode(String naturalWaterTypeCode) {
        this.naturalWaterTypeCode = naturalWaterTypeCode;
    }

    public String getWaterNaturalSourceHeight() {
        return waterNaturalSourceHeight;
    }

    public void setWaterNaturalSourceHeight(String waterNaturalSourceHeight) {
        this.waterNaturalSourceHeight = waterNaturalSourceHeight;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWaterQualityDesc() {
        return waterQualityDesc;
    }

    public void setWaterQualityDesc(String waterQualityDesc) {
        this.waterQualityDesc = waterQualityDesc;
    }

    public String getSeasonalChangesDesc() {
        return seasonalChangesDesc;
    }

    public void setSeasonalChangesDesc(String seasonalChangesDesc) {
        this.seasonalChangesDesc = seasonalChangesDesc;
    }

    public Integer getWhetherDrySeason() {
        return whetherDrySeason;
    }

    public void setWhetherDrySeason(Integer whetherDrySeason) {
        this.whetherDrySeason = whetherDrySeason;
    }

    public String getDrySeasonDesc() {
        return drySeasonDesc;
    }

    public void setDrySeasonDesc(String drySeasonDesc) {
        this.drySeasonDesc = drySeasonDesc;
    }
}
