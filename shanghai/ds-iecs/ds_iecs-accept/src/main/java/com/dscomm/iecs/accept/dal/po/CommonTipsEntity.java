package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 接警处警提示信息
 */
@Entity
@Table(name="DM_JCJTSXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CommonTipsEntity extends BaseEntity {

    @Column(name = "DMLX",length=100)
    private String type; //代码类型 案件类型

    @Column(name = "DMZ",length=100)
    private String code; //代码值 处置对象

    @Column(name = "DMMC",length=100)
    private String name ; // 代码值名称 处置对象名称

    @Column(name = "DMDJ",length=100)
    private String level ; // 案件等级

    @Column(name = "SFMR" )
    private Integer defaultValue ; //是否大类默认提示 0 非默认提示 1默认提示

    @Column(name = "JJTS",length=4000)
    private String receiveTips;	//接警提示

    @Column(name = "CJTS",length = 4000)
    private String handleTips;//处警提示

    @Column(name = "AQTS",length = 4000)
    private String safetyTips;//安全提示

    @Column(name = "BZ",length=800)
    private String remarks;			//备注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSafetyTips() {
        return safetyTips;
    }

    public void setSafetyTips(String safetyTips) {
        this.safetyTips = safetyTips;
    }

    public Integer getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReceiveTips() {
        return receiveTips;
    }

    public void setReceiveTips(String receiveTips) {
        this.receiveTips = receiveTips;
    }

    public String getHandleTips() {
        return handleTips;
    }

    public void setHandleTips(String handleTips) {
        this.handleTips = handleTips;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
