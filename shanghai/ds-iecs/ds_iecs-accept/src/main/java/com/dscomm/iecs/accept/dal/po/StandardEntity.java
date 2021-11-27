package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:
 *
 */
@Entity
@Table(name = "JCJ_TS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class StandardEntity extends BaseEntity {
    @Column(name = "TSLX", length = 50 )
    private String tipsType;  //提示类型（行为规范、禁止情形、语音标准、应对话术、禁止行为、投诉应对、特殊情况、禁语、接警提示、处警提示、安全提示）
    @Column(name = "TSNR")
    private String tipsContent;  //提示内容
    @Column(name = "TSGJZ" )
    private String tipsKeyword;  //提示关键字
    @Column(name = "AJDL", length = 100 )
    private String incidentBigType;  //对应案件大类（火灾、救援）
    @Column(name = "AJLX", length = 100 )
    private String incidentType;  //对应案件类型（灾害事故，公共突发事件、遇险紧急求助）
    @Column(name = "CDLX", length = 100 )
    private String placeType;  //受灾场地类型，重点单位的类型
    @Column(name = "AJDJ", length = 100 )
    private String incidentLevel;  //案件等级
    @Column(name = "GFLX", length = 50 )
    private String standardType;  //规范类型

    public String getTipsType() {
        return tipsType;
    }

    public void setTipsType(String tipsType) {
        this.tipsType = tipsType;
    }

    public String getTipsContent() {
        return tipsContent;
    }

    public void setTipsContent(String tipsContent) {
        this.tipsContent = tipsContent;
    }

    public String getTipsKeyword() {
        return tipsKeyword;
    }

    public void setTipsKeyword(String tipsKeyword) {
        this.tipsKeyword = tipsKeyword;
    }

    public String getIncidentBigType() {
        return incidentBigType;
    }

    public void setIncidentBigType(String incidentBigType) {
        this.incidentBigType = incidentBigType;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getIncidentLevel() {
        return incidentLevel;
    }

    public void setIncidentLevel(String incidentLevel) {
        this.incidentLevel = incidentLevel;
    }

    public String getStandardType() {
        return standardType;
    }

    public void setStandardType(String standardType) {
        this.standardType = standardType;
    }
}
