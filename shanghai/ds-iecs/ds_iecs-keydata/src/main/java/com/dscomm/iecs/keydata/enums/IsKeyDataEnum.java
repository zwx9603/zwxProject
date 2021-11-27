package com.dscomm.iecs.keydata.enums;


import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述 :  是否关键数据
 */
public enum IsKeyDataEnum implements BasicEnumNumber {
    // type(code,name)
    ISKEYDATA_YES(0,"是"),
    ISKEYDATA_NO(1,"否");
    private int code;
    private String message;

    IsKeyDataEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    IsKeyDataEnum(String message) {
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
