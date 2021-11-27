package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:警情关注信息po
 *
 */
@Entity
@Table(name = "JCJ_GZXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AttentionEntity extends BaseEntity {

    /**
     * 类型
     */
    @Column(name = "lx",length = 64)//1 警情关注 2 重要警情关注
    private Integer type;

    /**
     * 关注信息id
     */
    @Column(name = "ajid",length = 50)
    private String incidentId;

    /**
     * 关注类型
     */
    @Column(name = "gzlx",length = 64 )//1 普通关注；2班长关注
    private Integer attentionType;

    /**
     * 关注人账号
     */
    @Column(name = "gzrzh",length = 100)
    private String attentionPersonId;

    /**
     * 关注方式
     */
    @Column(name = "gzfs",length = 2)
    private Integer attentionWay;  //  1 系统关注  2 人工关注


    /**
     * 关注原因
     */
    @Column(name = "gzyy",length = 800 )
    private String attentionReason ;  //  关注原因

    /**
     * 关注时间
     */
    @Column(name = "gzsj",length = 64)
    private Long attentionTime;

    /**
     * 关注单位
     */
    @Column(name = "gzdw",length = 100)
    private String attentionOrganizationId ;



    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getAttentionPersonId() {
        return attentionPersonId;
    }

    public void setAttentionPersonId(String attentionPersonId) {
        this.attentionPersonId = attentionPersonId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getAttentionTime() {
        return attentionTime;
    }

    public void setAttentionTime(Long attentionTime) {
        this.attentionTime = attentionTime;
    }

    public Integer getAttentionType() {
        return attentionType;
    }

    public void setAttentionType(Integer attentionType) {
        this.attentionType = attentionType;
    }

    public Integer getAttentionWay() {
        return attentionWay;
    }

    public void setAttentionWay(Integer attentionWay) {
        this.attentionWay = attentionWay;
    }

    public String getAttentionOrganizationId() {
        return attentionOrganizationId;
    }

    public void setAttentionOrganizationId(String attentionOrganizationId) {
        this.attentionOrganizationId = attentionOrganizationId;
    }

    public String getAttentionReason() {
        return attentionReason;
    }

    public void setAttentionReason(String attentionReason) {
        this.attentionReason = attentionReason;
    }
}
