package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 警情状态变动表
 *
 */
@Entity
@Table(name = "JCJ_AJZTBDJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class IncidentStatusChangeEntity extends BaseEntity {

    @Column(name = "AJID" , length =  100 )
    private String incidentId;//案件ID

    @Column(name = "CJJLID", length = 100)
    private String handleId; // 处警记录ID

    @Column(name = "CJXXID", length = 100)
    private String handlePoliceId; // 处警信息ID

//    @Column(name = "AJZTDM" , length =  100 )
    @Column(name = "JQZT_LBDM" , length =  100 )
    private String incidentStatusCode; //todo 字段 案件状态代码

//    @Column(name = "BDSJ")
    @Column(name = "JQZT_RQSJ")
    private Long changeTime;//todo 字段 变动时间

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getHandlePoliceId() {
        return handlePoliceId;
    }

    public void setHandlePoliceId(String handlePoliceId) {
        this.handlePoliceId = handlePoliceId;
    }


    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public Long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
    }


}
