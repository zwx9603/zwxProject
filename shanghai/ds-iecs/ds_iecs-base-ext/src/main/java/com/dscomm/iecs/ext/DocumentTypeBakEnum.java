package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 文书类型
 *
 */
public enum DocumentTypeBakEnum implements BasicEnum {
    // type(code,name)

    DOCUMENT_TYPE_JQZT("01","警情状态"),
    DOCUMENT_TYPE_YJDP("02","一键调派"),
    DOCUMENT_TYPE_DPYJ("03","调派药剂"),
    DOCUMENT_TYPE_DPZJ("04","调派专家"),
    DOCUMENT_TYPE_DPQC("05","调派器材"),
    DOCUMENT_TYPE_DPYJLDDW("06","调派应急联动单位"),
    DOCUMENT_TYPE_CCD("07","出动单"),
    DOCUMENT_TYPE_JQDJBG("08","警情等级变更"),
    DOCUMENT_TYPE_QQZY("09","请求增援"),
    DOCUMENT_TYPE_WD("10","文电"),
    DOCUMENT_TYPE_WS("11","文书"),
    DOCUMENT_TYPE_AFDZBG("12","案发地址变更"),
    DOCUMENT_TYPE_JQLXBG("13","案件类型变更"),

    DOCUMENT_TYPE_QT("99","其他"),
    ;
    private String code;
    private String message;

    DocumentTypeBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    DocumentTypeBakEnum(String message) {
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
