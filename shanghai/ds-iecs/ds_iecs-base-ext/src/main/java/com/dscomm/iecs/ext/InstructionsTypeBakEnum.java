package com.dscomm.iecs.ext;

import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述：指令类型
 *
 */
public enum InstructionsTypeBakEnum implements BasicEnum {
    //指令类型
    INSTRUCTION("INSTRUCTION","指令"),
    ASK_FOR_LEADER("ASK_FOR_LEADER","请示领导"),
    CONSULT_EXPERT("CONSULT_EXPERT","咨询专家"),
    NOTIFIED("NOTIFIED","通报"),
    CONTACT("CONTACT","联系"),
    APPROVAL("APPROVAL","批示"),
    //指令下达方式
    TEXT("TEXT","文本"),
    SMS("SMS","短信"),
    VOICE("VOICE","语音"),
    MICRO_GROUP("MICRO_GROUP","微群"),
    //指令单状态
    STATUS_GIVEN("STATUS_GIVEN","已通知"),
    STATUS_SIGNED("STATUS_SIGNED","已签收"),
    //受令对象类型
    VEHICLE("VEHICLE","车辆"),
    PERSON("PERSON","人员"),
    SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
    EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
    TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
    //单位类型
    SQUADRON("SQUADRON","中队"),
    UNIT("UNIT","单位");

    private String code;
    private String message;

    InstructionsTypeBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    InstructionsTypeBakEnum(String message) {
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
        for (InstructionsTypeBakEnum statusEnum : InstructionsTypeBakEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}
