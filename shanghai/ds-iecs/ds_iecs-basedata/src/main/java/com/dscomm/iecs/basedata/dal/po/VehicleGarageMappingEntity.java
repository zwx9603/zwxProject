package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 中队车辆与车库门对应关系
 *
 */
@Entity
@Table(name = "JCJ_CLYCKMDYGX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VehicleGarageMappingEntity extends BaseEntity {

    @Column(name = "XFJGBH", length = 100)
    private String organizationId;// 消防机构编号

    @Column(name = "GXLX", length = 100)
    private String mappingType;// 关系类型

    @Column(name = "BZBH", length = 100)
    private String mappingObjectId ;// 车辆编号/班组号

    @Column(name = "DYBH", length = 100)
    private String mappingGroupId;// 车库门编号/房间号

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }

    public String getMappingObjectId() {
        return mappingObjectId;
    }

    public void setMappingObjectId(String mappingObjectId) {
        this.mappingObjectId = mappingObjectId;
    }

    public String getMappingGroupId() {
        return mappingGroupId;
    }

    public void setMappingGroupId(String mappingGroupId) {
        this.mappingGroupId = mappingGroupId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
