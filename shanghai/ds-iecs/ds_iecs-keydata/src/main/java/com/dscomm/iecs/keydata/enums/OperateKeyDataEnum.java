package com.dscomm.iecs.keydata.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述 : 操作关键数据枚举(是否操作了关键数据)
 *
 */
public enum OperateKeyDataEnum implements BasicEnumNumber {
    // type(code,name)
    OPERATEKEYDATA_NO(0, "非关键数据"),
    OPERATEKEYDATA_YES(1, "关键数据");
    private int code;
    private String message;

    OperateKeyDataEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    OperateKeyDataEnum(String message) {
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
