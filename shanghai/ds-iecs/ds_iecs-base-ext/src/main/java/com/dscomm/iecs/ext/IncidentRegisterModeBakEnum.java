package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:事件立案方式枚举
 *
 */
public enum IncidentRegisterModeBakEnum implements BasicEnum {
    // type(code,name)
    INCIDENT_REGISTER_MODE_XFLA("1","消防立案"),
    INCIDENT_REGISTER_MODE_110LA("2","110立案"),
    INCIDENT_REGISTER_MODE_XFHBLA("3","消防后补立案"),
    INCIDENT_REGISTER_MODE_XFFASLA("4","消防FAS立案"),



    ;
    private String code;
    private String message;

    IncidentRegisterModeBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    IncidentRegisterModeBakEnum(String message) {
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
        for (IncidentRegisterModeBakEnum statusEnum : IncidentRegisterModeBakEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}
