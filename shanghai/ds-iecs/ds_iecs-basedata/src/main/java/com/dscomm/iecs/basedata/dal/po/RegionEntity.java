package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 范围信息
 *
 */
@Entity
@Table(name = "JCJ_FWXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RegionEntity extends BaseEntity {

    @Column(name="MC", length = 100)
    private String regionName ;// 范围名称

    @Column(name="ZXDJD", length = 50 )
    private String longitude;// 经度

    @Column(name="ZXDWD", length = 50)
    private String latitude;// 纬度

    @Column(name = "FWXX", length = 1000 )
    private String regionRange; // 范围信息

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRegionRange() {
        return regionRange;
    }

    public void setRegionRange(String regionRange) {
        this.regionRange = regionRange;
    }


}
