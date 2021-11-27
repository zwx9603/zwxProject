package com.dscomm.iecs.agent.dal.po.DistributeStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述:分配辖区表
 *
 * @author YangFuxi
 * Date Time 2019/9/18 19:46
 */
@Table(name = "t_fp_cjxq")
@Entity
public class JurisdictionPO implements Serializable {
    /**
     * 辖区id
     */
    @Id
    @Column(name = "bh")
    private String id;
    /**
     * 辖区名称
     */
    @Column(name = "mc")
    private String name;
    /**
     * 有效性
     */
    @Column(name = "yxbz")
    private Integer valid;
    /**
     * 删除标志
     */
    @Column(name = "scbz")
    private Integer deleteFlag;
    /**
     * 所属单位
     */
    @Column(name = "ssdw")
    private String orgid;
    /**
     * 时间戳
     */
    @Column(name = "sjc")
    private Long sjc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

}
