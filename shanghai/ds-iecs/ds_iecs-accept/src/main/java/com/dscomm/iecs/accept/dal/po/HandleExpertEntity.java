package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 调派专家
 *
 */
@Entity
@Table(name = "JCJ_DPZJ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class  HandleExpertEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; //案件ID

    @Column(name = "CJJLID", length = 100)
    private String handleId;// 处警记录ID

    @Column(name = "ZJID", length = 100 )
    private String expertId ;// 专家ID

//    @Column(name="ZJXM", length = 100)
    @Column(name="DPZJ_XM", length = 100)
    private String expertName;//todo 字段 专家姓名

    @Column(name="ZJLX", length = 100)
    private String expertType;//专家类型

    @Column(name = "DPSJ")
    private Long dispatchTime;// 调派时间

    @Column(name = "LYH", length = 100 )
    private String relayRecordNumber;//录音号

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

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


    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }

    public Long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
