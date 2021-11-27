package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 描述:案件合并记录
 *
 */
@Entity
@Table(name = "JCJ_AJHBJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class IncidentMergeEntity extends BaseEntity {

    @Column(name = "ZAJID", length = 100)
    private String mainIncidentId; // 主案件ID

    @Column(name = "BHBAJID", length = 100)
    private String mergeIncidentId; // 被合并案件ID

    @Column(name = "HBSJ")
    private Long mergeTime ;//合并时间

    @Column(name = "ZXH", length = 50)
    private String mergeSeatNumber; // 合并坐席编号

    @Column(name = "JJYGH", length = 100)
    private String acceptancePersonNumber;// 接警员工号

    @Column(name = "BZ",length = 800)
    private String remarks;//备注

    public String getMainIncidentId() {
        return mainIncidentId;
    }

    public void setMainIncidentId(String mainIncidentId) {
        this.mainIncidentId = mainIncidentId;
    }

    public String getMergeIncidentId() {
        return mergeIncidentId;
    }

    public void setMergeIncidentId(String mergeIncidentId) {
        this.mergeIncidentId = mergeIncidentId;
    }

    public Long getMergeTime() {
        return mergeTime;
    }

    public void setMergeTime(Long mergeTime) {
        this.mergeTime = mergeTime;
    }

    public String getMergeSeatNumber() {
        return mergeSeatNumber;
    }

    public void setMergeSeatNumber(String mergeSeatNumber) {
        this.mergeSeatNumber = mergeSeatNumber;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
