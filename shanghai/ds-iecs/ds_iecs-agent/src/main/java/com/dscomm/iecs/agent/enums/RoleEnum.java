package com.dscomm.iecs.agent.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * @author Zhuqihong
 * 角色枚举 50 - 100  火警受理业务角色
 * Date Time 2019/9/18
 */
public enum RoleEnum implements BasicEnumNumber {
    // type(code,name)
    AGENT_PERSONROLE_CALLTAKING(55, "接警员"), //使用
    AGENT_PERSONROLE_CALLTAKING_SUPERVISOR(60, "接警班长"),  //使用


    AGENT_PERSONROLE_DISPATCH(56, "处警员"),
    AGENT_PERSONROLE_DISPATCH_SUPERVISOR(61, "处警班长"),


    AGENT_PERSONROLE_CALLTAKING_DISPATCH(57, "接处合一"),
    AGENT_PERSONROLE_ALL_SUPERVISOR(58, "高级班长"),
    AGENT_PERSONROLE_INTELLIGENT(59, "技防接警员"),

    AGENT_PERSONROLE_SENIOR_DISPATCH(62, "高级处警员"),
    AGENT_PERSONROLE_ADMINISTRATOR(63, "Administrator"),


    AGENT_NO_CALLTAKING(80, "非接警角色 非处警角色");

    private int code;
    private String message;

    RoleEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    RoleEnum(String message) {
        this.message = message;
    }

    @Override
    public int getCode() {
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
    public static String getNameByCode(int code) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (code == roleEnum.getCode()) {
                return roleEnum.getName();
            }
        }
        return null;
    }
}
