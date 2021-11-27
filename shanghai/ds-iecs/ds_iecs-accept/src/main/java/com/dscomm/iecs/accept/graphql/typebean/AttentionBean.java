package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:关注信息
 *
 */
public class AttentionBean extends BaseBean {

    private Integer type; //1 警情关注 2 重要警情关注

    private String incidentId; //关注信息id

    private Integer attentionType; //关注类型 1 普通关注；2班长关注

    private String attentionPersonId; //关注人账号

    private Integer attentionWay;  //  关注方式  1 系统关注  2 人工关注

    private Long attentionTime; //关注时间

    private String attentionOrganizationId ; //关注单位

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
}
