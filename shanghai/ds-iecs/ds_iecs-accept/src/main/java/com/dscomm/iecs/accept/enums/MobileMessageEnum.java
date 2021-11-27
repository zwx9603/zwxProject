package com.dscomm.iecs.accept.enums;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述： 移动端推送消息枚举
 *
 */
public enum MobileMessageEnum implements BasicEnum {

    // type(code,name)
    NEW_INCIDENT("001","处置警情"),
    NEW_INSTRUCTION("002","指令信息"),
    NEW_INCIDENT_STATE("003","案件状态变更"),
    UPDATE_INCIDENT("004","案件信息更新"),
    FINISH_INCIDENT("005","案件归档"),

    VEHICLE_STATE_CHANGE("006","车辆状态更新")

    ;

    private String code;
    private String message;

    MobileMessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    MobileMessageEnum(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getType() {
        return super.name();
    }

    @Override
    public String getName() {
        return I18nMessageUtils.getI18nMessage(super.name(), message);
    }


}
