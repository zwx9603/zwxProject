package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:事件单状态枚举类
 *
 */
public enum IncidentStatusBak1Enum implements BasicEnum {
    // type(code,name)
//    INCIDENT_STATUS_ZC("01","暂存"),
//    INCIDENT_STATUS_LA("10","立案"),
//    INCIDENT_STATUS_TZ("20","通知"),
//    INCIDENT_STATUS_CD("30","出动"),
//    INCIDENT_STATUS_DC("40","到场"),
//    INCIDENT_STATUS_KSZZ("50","出水"),
//    INCIDENT_STATUS_TZZZ("70","停水"),
//    INCIDENT_STATUS_ZF("100","中返"),
//    INCIDENT_STATUS_FH("110","返回"),
//    INCIDENT_STATUS_FD("120","归队"),
//    INCIDENT_STATUS_JA("130","结案(待审)"),
//    INCIDENT_STATUS_TB("140","填报"),
//    INCIDENT_STATUS_GD("150","归档"),

    INCIDENT_STATUS_ZC("00","暂存"),
    INCIDENT_STATUS_LA("01","立案"),
    INCIDENT_STATUS_TZ("02","通知"),
    INCIDENT_STATUS_CD("03","出动"),
    INCIDENT_STATUS_DC ("04","到场"),
    INCIDENT_STATUS_ZK ("05","展开"),
    INCIDENT_STATUS_KSZZ("06","出水"),
    INCIDENT_STATUS_KZSH("07","控制"),
    INCIDENT_STATUS_TZZZ("08","停水/熄灭"),
    INCIDENT_STATUS_QLXC("09","清理现场"),
    INCIDENT_STATUS_FH("10","返回"),
    INCIDENT_STATUS_FD("11","归队"),
    INCIDENT_STATUS_JA("12","结案"),
    INCIDENT_STATUS_GD("13","归档"),

    INCIDENT_STATUS_ZF("14","中返"),

    INCIDENT_STATUS_QT("99","其他"),

    ;
    private String code;
    private String message;

    IncidentStatusBak1Enum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    IncidentStatusBak1Enum(String message) {
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
        for (IncidentStatusBak1Enum statusEnum : IncidentStatusBak1Enum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}
