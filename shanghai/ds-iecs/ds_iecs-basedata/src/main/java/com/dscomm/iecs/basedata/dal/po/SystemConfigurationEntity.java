package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：系统配置
 *
 */
@Entity
@Table(name="JCJ_XTPZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SystemConfigurationEntity extends BaseEntity {

    @Column(name = "PZX",   length = 100)
    private String configType; //配置项

    @Column(name = "PZMS", length = 400)
    private String configDesc; //配置描述

    @Column(name = "PZSZ",   length = 800)
    private String configValue; //配置数值

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注


    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }



}
