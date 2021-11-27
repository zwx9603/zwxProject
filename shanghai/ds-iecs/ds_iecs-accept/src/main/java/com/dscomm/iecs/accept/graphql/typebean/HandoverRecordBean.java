package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 交接班 记录内容
 */
public class HandoverRecordBean extends BaseBean {

    private String handoverContent;// 交接班内容 值班内容

    private Long handoverTime ;//留言时间

    private String handoverPersonId ;//留言人

    private String handoverPersonName ;//留言人姓名

    private String handoverPersonNumber ;//留言用户工号

    private String handoverSeatNumber ;//留言台号

    private String handoverOrganizationId  ;//机构ID

    private Integer  deleted  ;// 删除标志

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
