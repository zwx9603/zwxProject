package com.dscomm.iecs.agent.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:话机状态
 *
 * @author YangFuxi
 * Date Time 2019/10/29 16:29
 */
public enum AgentPhoneStateEnum implements BasicEnumNumber {
    AGENT_PHONE_STATE_ALL(0,"全部"),
    AGENT_PHONE_STATE_INVALID(1,"无效"),
    AGENT_PHONE_STATE_IDLE(2,"空闲"),
    AGENT_PHONE_STATE_IDLE_OFFHOOK(3,"空摘机"),
    AGENT_PHONE_STATE_ALERT(4,"振铃"),
    AGENT_PHONE_STATE_TALKING(5,"通话"),
    AGENT_PHONE_STATE_REMOTE_ALERT(6,"对方振铃"),
    ;

    private int code;
    private String message;

    AgentPhoneStateEnum(int code, String message) {
        this.code = code;
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
}
