package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 文书类型
 *
 */
public enum AttachmentTypeBakEnum implements BasicEnum {
    // type(code,name)

    ATTACHMENT_TYPE_SP("1","视频"),
    ATTACHMENT_TYPE_TP("2","图片"),
    ATTACHMENT_TYPE_WD("3","文档"),
    ATTACHMENT_TYPE_QT("9","其他"),

    ;
    private String code;
    private String message;

    AttachmentTypeBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    AttachmentTypeBakEnum(String message) {
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
