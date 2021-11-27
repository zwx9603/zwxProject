package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:灾害作战功能
 */
@Entity
@Table(name = "ZBZB_ZHZZGN")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DisastersOperationalFunctionEntity extends BaseEntity {

    @Column(name = "ZHLX", length = 100)
    private String disasterType; //灾害类型

    @Column(name = "TZZZGN", length = 100)
    private String specialOperationalFunctionCode; //特种作战功能编码

    @Column(name = "YXCX")
    private Integer priority; //优先次序

    @Column(name = "JGID", length = 100)
    private String organizationId; //机构ID

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getSpecialOperationalFunctionCode() {
        return specialOperationalFunctionCode;
    }

    public void setSpecialOperationalFunctionCode(String specialOperationalFunctionCode) {
        this.specialOperationalFunctionCode = specialOperationalFunctionCode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}