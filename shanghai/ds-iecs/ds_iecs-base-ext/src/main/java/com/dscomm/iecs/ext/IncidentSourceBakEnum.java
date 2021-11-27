package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 案件来源
 *
 */
public enum IncidentSourceBakEnum implements BasicEnum {
    // type(code,name)

    INCIDENT_SOURCE_XF("100","消防"),
    INCIDENT_SOURCE_GA("200","公安"),
    INCIDENT_SOURCE_FAS("300","FAS"),
    INCIDENT_SOURCE_QT("999","其他"),
    ;
    private String code;
    private String message;

    IncidentSourceBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    IncidentSourceBakEnum(String message) {
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
     * @param code
     * @return
     */
    public static String getNameByCode(String code) {
        for (IncidentSourceBakEnum statusEnum : IncidentSourceBakEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}
