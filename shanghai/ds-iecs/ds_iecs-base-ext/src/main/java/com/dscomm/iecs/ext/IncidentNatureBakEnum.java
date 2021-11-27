package com.dscomm.iecs.ext;

import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述 : 案件类型标识(案件性质)枚举
 */
public enum IncidentNatureBakEnum implements BasicEnum {

    // type(code,name)
    INCIDENT_NATURE_JJ("1", "假警"), //假警
    INCIDENT_NATURE_ZJ("2", "真警"), //真警
    INCIDENT_NATURE_CWJJ("3", "错位接警"), //错位接警
    INCIDENT_NATURE_GLJQ("4", "关联警情"), //关联警情
    INCIDENT_NATURE_QT("9", "其他"), //其他

    ;
    private String code;
    private String message;

    IncidentNatureBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    IncidentNatureBakEnum(String message) {
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
        for (IncidentNatureBakEnum incidentNatureEnum : IncidentNatureBakEnum.values()) {
            if (code.equals(incidentNatureEnum.getCode())) {
                return incidentNatureEnum.getName();
            }
        }
        return null;
    }
}
