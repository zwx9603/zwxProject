package com.dscomm.iecs.agent.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * @author Zhuqihong
 * 角色枚举 50 - 100  火警受理业务角色
 * Date Time 2019/9/18
 */
public enum AgentRoleEnum implements BasicEnumNumber {
    // type(code,name)
    AGENT_ZONGDUI_JIEJINGTAI(1,"总队接警台"),
    AGENT_ZHIDUI_CHUJINGTAI(2,"支队处警台"),
    AGENT_ZONGDUI_JIANKONGTAI(3,"总队监控台"),
    AGENT_BANZHANGTAI(4,"班长台"),
    AGENT_ZHONGDUI(5,"中队台"),
    AGENT_ZHIDUI_DIAODUTAI(10,"支队调度台");

    private int code;
    private String message;

    AgentRoleEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    AgentRoleEnum(String message) {
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
        for (AgentRoleEnum roleEnum : AgentRoleEnum.values()) {
            if (code == roleEnum.getCode()) {
                return roleEnum.getName();
            }
        }
        return null;
    }
}
