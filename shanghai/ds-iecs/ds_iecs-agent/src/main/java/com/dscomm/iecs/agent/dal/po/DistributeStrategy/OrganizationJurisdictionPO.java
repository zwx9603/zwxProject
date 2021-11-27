package com.dscomm.iecs.agent.dal.po.DistributeStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述:分配单位辖区对应表
 *
 * @author YangFuxi
 * Date Time 2019/9/18 20:00
 */
@Table(name = "t_fp_dwcjxqdzb")
@Entity
public class OrganizationJurisdictionPO implements Serializable {
    /**
     * 单位id
     */
    @Id
    @Column(name = "dwbh")
    private String orgId;
    /**
     * 辖区id
     */
    @Column(name = "cjxqbh")
    private String jurisdictionId;
    /**
     * 时间戳
     */
    @Column(name = "sjc")
    private Long sjc;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }
}
