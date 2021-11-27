package com.dscomm.iecs.notify.eunms;

import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述 : 过滤类型
 *  1, "机构过滤"
 *  4, "角色过滤"
 *  7, "机构角色过滤"
 *  9, "用户过滤"
 */
public enum FilterTypeDataEnum    {
    // type(code,name)
    FILTER_TYPE_DATA_ORGANIZATION(1, "机构过滤"),

    FILTER_TYPE_DATA_ORGANIZATION_ROLE (6, "机构角色过滤"),

    FILTER_TYPE_DATA_USER (9, "用户过滤") ;

    private int code;
    private String message;

    FilterTypeDataEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    FilterTypeDataEnum(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return super.name();
    }

    public String getName() {
        return I18nMessageUtils.getI18nMessage(super.name(), message);
    }
}
