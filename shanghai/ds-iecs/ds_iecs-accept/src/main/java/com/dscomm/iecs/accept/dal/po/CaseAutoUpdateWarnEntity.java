package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 案件等级自动升级提醒
 */
@Entity
@Table(name = "jcj_ajzdsjtx")
public class CaseAutoUpdateWarnEntity extends BaseEntity {
    /**
     * 案件类型
     */
    @Column(name = "AJLX",length =  40 )
    private String caseType;
    /**
     * 提醒类型
     * BKRS（被困人数），RSMJ（燃烧面积），RSDX（燃烧对象），ZHCS（火灾场所），ZDDW（重点单位），YJJQ（夜间警情）
     */
    @Column(name = "TXLX",length =  40 )
    private String warnType;
    /**
     * 最小值
     */
    @Column(name = "ZXZ",length =  40 )
    private String minimum;
    /**
     * 最大值
     */
    @Column(name = "ZDZ",length =  40 )
    private String maximum;
    /**
     * 对象字典值
     */
    @Column(name = "DX",length =  40 )
    private String disposalObjectCode;
    /**
     * 案件等级
     */
    @Column(name = "AJDJ",length =  40 )
    private String incidentLevelCode;
    /**
     * 描述
     */
    @Column(name = "MS",length =  255 )
    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }
}
