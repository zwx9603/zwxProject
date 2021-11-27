package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * led车辆（表名不能改）
 */
@Entity
@Table(name = "wl_clxx_led")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LedVehicleEntity extends BaseEntity {

    @Column(name = "px" , length =  10  )
    private String order  ;//排序

    @Column(name = "mc", length = 200)
    private String name;//车辆对应名称

    @Column(name = "ysmc", length = 200)
    private String  oldName ; //  原始车辆对应名称

    @Column(name = "sfxs")
    private Integer  isDisPlay ; //  是否显示 0 不显示 1 显示

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Integer getIsDisPlay() {
        return isDisPlay;
    }

    public void setIsDisPlay(Integer isDisPlay) {
        this.isDisPlay = isDisPlay;
    }
}
