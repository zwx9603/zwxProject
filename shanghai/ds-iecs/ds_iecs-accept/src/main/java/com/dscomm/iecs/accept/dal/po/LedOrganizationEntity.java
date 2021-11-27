package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * led机构（表名不能改）
 */
@Entity
@Table(name = "jgxx_xfjg_led")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LedOrganizationEntity extends BaseEntity {

    @Column(name = "px" , length =  10  )
    private String order  ;//排序

    @Column(name = "mc", length = 200)
    private String name;//机构对应名称

    @Column(name = "ysmc", length = 200)
    private String  oldName ; //  原始机构对应名称
    @Column(name = "sfxs")
    private Integer  isDisPlay ; //  是否显示 0 不显示 1 显示

    @Column(name = "sjjgid", length = 50)
    private String  organizationParentId ; //  上级机构id
    @Column(name = "sfzcq")
    private Integer  isImportantCity ; //  是否主城区  0不是  1是


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

    public String getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(String organizationParentId) {
        this.organizationParentId = organizationParentId;
    }

    public Integer getIsImportantCity() {
        return isImportantCity;
    }

    public void setIsImportantCity(Integer isImportantCity) {
        this.isImportantCity = isImportantCity;
    }
}
