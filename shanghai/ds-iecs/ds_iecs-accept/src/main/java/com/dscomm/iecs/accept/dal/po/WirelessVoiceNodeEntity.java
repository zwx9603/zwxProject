package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:无线语音节点
 */
@Entity
@Table(name = "YYXT_WXYYJD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WirelessVoiceNodeEntity extends BaseEntity {

    @Column(name = "JDMC", length = 200)
    private String nodeName; //节点名称

    @Column(name = "JDJC", length = 200)
    private String nodeShortName; //节点简称

    @Column(name = "SSJGID", length = 100)
    private String organizationId; //所属机构ID

    @Column(name = "JDNBID", length = 100)
    private String nodeInsideId; //节点内部ID

    @Column(name = "JDLXMC", length = 200)
    private String nodeTypeName; //节点类型名称

    @Column(name = "JDLX", length = 100)
    private Double nodeType; //节点类型

    @Column(name = "JDZT")
    private Integer nodeStatus; //节点状态

    @Column(name = "BJSJ")
    private Long editTime; //编辑时间

    @Column(name = "BZ", length = 400)
    private String remarks; //备注

    @Column(name = "JLZT")
    private Integer JLZT; //记录状态

    @Column(name = "CSZT")
    private Integer CSZT; //传输状态

    @Column(name = "SJC")
    private Long SJC; //时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB; //数据版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID; //业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB; //基库数据版本

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeShortName() {
        return nodeShortName;
    }

    public void setNodeShortName(String nodeShortName) {
        this.nodeShortName = nodeShortName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getNodeInsideId() {
        return nodeInsideId;
    }

    public void setNodeInsideId(String nodeInsideId) {
        this.nodeInsideId = nodeInsideId;
    }

    public String getNodeTypeName() {
        return nodeTypeName;
    }

    public void setNodeTypeName(String nodeTypeName) {
        this.nodeTypeName = nodeTypeName;
    }

    public Double getNodeType() {
        return nodeType;
    }

    public void setNodeType(Double nodeType) {
        this.nodeType = nodeType;
    }

    public Long getEditTime() {
        return editTime;
    }

    public void setEditTime(Long editTime) {
        this.editTime = editTime;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public Integer getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(Integer nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}