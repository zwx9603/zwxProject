package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微站调派 反馈信息
 *
 */
@Entity
@Table(name = "jcj_wzdpfk")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandleMiniatureStationFeedbackEntity extends BaseEntity {

    @Column(name = "ajid" , length =  40 )
    private String incidentId; //案件ID

    @Column(name = "cjdid" , length =  40  )
    private String handleId ;//处警单ID

    @Column(name = "dpdid" , length =  40  )
    private String handleMiniatureStationId ;//微站调派单ID

    @Column(name = "fksj" )
    private Long feedbackTime ;// 调派时间

    @Column(name = "czqk", length = 1000)
    private String localeDesc ; //  处置情况



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

    public String getHandleMiniatureStationId() {
        return handleMiniatureStationId;
    }

    public void setHandleMiniatureStationId(String handleMiniatureStationId) {
        this.handleMiniatureStationId = handleMiniatureStationId;
    }

    public Long getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Long feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getLocaleDesc() {
        return localeDesc;
    }

    public void setLocaleDesc(String localeDesc) {
        this.localeDesc = localeDesc;
    }
}
