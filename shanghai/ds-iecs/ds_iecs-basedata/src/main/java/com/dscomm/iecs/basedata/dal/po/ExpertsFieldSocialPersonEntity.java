package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:社会专家领域 社会专家人员与专家领域关联关系
 *
 */
@Entity
@Table(name = "RYXX_SHZJLY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ExpertsFieldSocialPersonEntity extends BaseEntity {

    @Column(name = "ZJID", length = 100)
    private String sociaPersonId;//人员ID

    @Column(name = "ZJLYDM", length = 100)
    private String expertFieldId;//专家领域代码

    @Column(name = "WHYWXT", length = 100)
    private String maintainBusiness;//维护业务系统

    @Column(name = "WHXFJG", length = 100)
    private String maintainOrganization;//维护消防机构

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本


    public String getSociaPersonId() {
        return sociaPersonId;
    }

    public void setSociaPersonId(String sociaPersonId) {
        this.sociaPersonId = sociaPersonId;
    }

    public String getExpertFieldId() {
        return expertFieldId;
    }

    public void setExpertFieldId(String expertFieldId) {
        this.expertFieldId = expertFieldId;
    }

    public String getMaintainBusiness() {
        return maintainBusiness;
    }

    public void setMaintainBusiness(String maintainBusiness) {
        this.maintainBusiness = maintainBusiness;
    }

    public String getMaintainOrganization() {
        return maintainOrganization;
    }

    public void setMaintainOrganization(String maintainOrganization) {
        this.maintainOrganization = maintainOrganization;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}
