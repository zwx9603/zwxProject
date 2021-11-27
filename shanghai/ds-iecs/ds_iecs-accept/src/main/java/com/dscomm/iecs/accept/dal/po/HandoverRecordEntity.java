package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 交接班 记录内容
 */

@Entity
@Table(name = "JCJ_ZBRZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandoverRecordEntity   extends BaseEntity {

    @Column(name = "ZBRZ", length = 1000 )
    private String handoverContent;// 交接班内容 值班内容

    @Column(name = "LYSJ" )
    private Long handoverTime ;//留言时间

    @Column(name = "LYR" , length = 100)
    private String handoverPersonId ;//留言人

    @Column(name = "LYRXM" , length = 100)
    private String handoverPersonName ;//留言人姓名

    @Column(name = "LYYH"  , length = 100)
    private String handoverPersonNumber ;//留言用户工号

    @Column(name = "LYTH"  , length = 100)
    private String handoverSeatNumber ;//留言台号

    @Column(name = "LYJG"  , length = 100)
    private String handoverOrganizationId  ;//机构ID

    @Column(name = "SCBZ" )
    private Integer  deleted  ;// 删除标志

    @Column(name = "SJC" )
    private Long SJC ;//时间戳

    public String getHandoverContent() {
        return handoverContent;
    }

    public void setHandoverContent(String handoverContent) {
        this.handoverContent = handoverContent;
    }

    public Long getHandoverTime() {
        return handoverTime;
    }

    public void setHandoverTime(Long handoverTime) {
        this.handoverTime = handoverTime;
    }

    public String getHandoverPersonId() {
        return handoverPersonId;
    }

    public void setHandoverPersonId(String handoverPersonId) {
        this.handoverPersonId = handoverPersonId;
    }

    public String getHandoverPersonName() {
        return handoverPersonName;
    }

    public void setHandoverPersonName(String handoverPersonName) {
        this.handoverPersonName = handoverPersonName;
    }

    public String getHandoverPersonNumber() {
        return handoverPersonNumber;
    }

    public void setHandoverPersonNumber(String handoverPersonNumber) {
        this.handoverPersonNumber = handoverPersonNumber;
    }

    public String getHandoverSeatNumber() {
        return handoverSeatNumber;
    }

    public void setHandoverSeatNumber(String handoverSeatNumber) {
        this.handoverSeatNumber = handoverSeatNumber;
    }

    public String getHandoverOrganizationId() {
        return handoverOrganizationId;
    }

    public void setHandoverOrganizationId(String handoverOrganizationId) {
        this.handoverOrganizationId = handoverOrganizationId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }
}
