package com.dscomm.iecs.basedata.service.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述 : 通讯信息类型
 *
 */
public enum ContactTypeEnum implements BasicEnumNumber {
    // type(code,name)
    ORGANIZATION(1, "单位"),
    PERSON(2, "人员");
    private int code;
    private String message;

    ContactTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    ContactTypeEnum(String message) {
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
