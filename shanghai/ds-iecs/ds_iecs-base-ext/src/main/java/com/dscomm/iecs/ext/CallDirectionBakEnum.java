package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 呼叫方向 枚举类
 *
 */
public enum CallDirectionBakEnum implements BasicEnum {
    // type(code,name)

    CALL_DIRECTION_HR("1","呼入"),
    CALL_DIRECTION_HC("2","呼出"),
    CALL_DIRECTION_QT("9","其他"),
    ;
    private String code;
    private String message;

    CallDirectionBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    CallDirectionBakEnum(String message) {
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
        for (CallDirectionBakEnum statusEnum : CallDirectionBakEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}
