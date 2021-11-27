package com.dscomm.iecs.base.enums;


import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 是否 枚举
 *
 */
public enum EnableEnum implements BasicEnumNumber {
    // type(code,name)
    ENABLE_FALSE(0,"否"),
    ENABLE_TRUE(1,"是")

    ;

    private int code;
    private String message;

    EnableEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    EnableEnum(String message) {
        this.message = message;
    }

    @Override
    public int getCode() {
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
