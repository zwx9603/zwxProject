package com.dscomm.iecs.accept.graphql.inputbean;

import javax.persistence.Column;

/**
 * 交接班 记录内容 保存信息
 */
public class HandoverRecordSaveInputInfo {

    private String id;

    private String handoverContent;// 交接班内容 值班内容

    private Long handoverTime ;//留言时间

    private String handoverPersonId ;//留言人

    private String handoverPersonName ;//留言人姓名

    private String handoverPersonNumber ;//留言用户工号

    private String handoverSeatNumber ;//留言台号

    private String handoverOrganizationId  ;//机构ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
