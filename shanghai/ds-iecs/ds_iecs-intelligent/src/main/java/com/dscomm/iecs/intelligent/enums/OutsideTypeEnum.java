package com.dscomm.iecs.intelligent.enums;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 请求协助类型
 *
 */
public enum OutsideTypeEnum implements BasicEnum {
    // type(code,name)
    OUTSIDE_TYPE_110("1","110"),
    OUTSIDE_TYPE_122("2","122"),
    OUTSIDE_TYPE_YJ("3","应急"),


    ;
    private String code;
    private String message;

    OutsideTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    OutsideTypeEnum(String message) {
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
