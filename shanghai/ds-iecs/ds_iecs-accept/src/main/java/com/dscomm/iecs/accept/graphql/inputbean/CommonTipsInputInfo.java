package com.dscomm.iecs.accept.graphql.inputbean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *  描述: 接警处警提示信息
 */
public class CommonTipsInputInfo extends BaseBean {

    private String type; //代码类型 案件类型

    private String code; //代码值  处置对象

    private String level ; // 案件等级

    private Integer defaultValue ; //是否大类默认提示 0 非默认提示 1默认提示

    private String receiveTips;	//接警提示

    private String handleTips;//处警提示

    private String safetyTips;//安全提示

    private String remarks;			//备注

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
