package com.dscomm.iecs.ext;

import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

public enum RelationObjectBakEnum implements BasicEnum {

    // type(code,name)
    RELATION_OBJECT_XCTP("XCXX","现场图片"),
    RELATION_OBJECT_WBYA("WBYA","文本预案"),
            ;

    private String code;
    private String message;

    RelationObjectBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    RelationObjectBakEnum(String message) {
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


    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getNameByCode(String code) {
        for (RelationObjectBakEnum statusEnum : RelationObjectBakEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}