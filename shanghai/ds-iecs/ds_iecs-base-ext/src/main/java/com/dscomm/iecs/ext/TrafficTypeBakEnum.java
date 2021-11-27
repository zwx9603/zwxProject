package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 请求协助类型 枚举
 *
 */
public enum TrafficTypeBakEnum implements BasicEnum {
    // type(code,name)

    TRAFFICE_HANDLE_TYPE_ZJ("10","转警"),
    TRAFFICE_HANDLE_TYPE_CWJZ("20","错位接警"),
    TRAFFICE_HANDLE_TYPE_QQXZ("30","请求协助"),

    INCIDENT_TRAFFICE_TYPE_QQXZ110("110","110"),
    INCIDENT_TRAFFICE_TYPE_QQXZ120("120","120"),
    INCIDENT_TRAFFICE_TYPE_QQXZ122("122","122"),
    INCIDENT_TRAFFICE_TYPE_QQXZYJ("130","应急中心"),


    ;
    private String code;
    private String message;

    TrafficTypeBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    TrafficTypeBakEnum(String message) {
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
        for (TrafficTypeBakEnum statusEnum : TrafficTypeBakEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}
