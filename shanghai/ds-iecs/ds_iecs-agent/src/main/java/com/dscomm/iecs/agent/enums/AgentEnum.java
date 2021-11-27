package com.dscomm.iecs.agent.enums;


import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:坐席枚举类
 *
 * @author YangFuxi
 * Date Time 2019/8/8 20:05
 */
public enum AgentEnum implements BasicEnumNumber {
    // type(code,name)
    AGENT_TYPE_CALLTAKING(1, "接警"),
    AGENT_TYPE_DISPATCH(2, "处警"),
    AGENT_TYPE_INTELLIGENT(3, "技防"),
    AGENT_TYPE_SUPERVISOR(4, "班长"),
    AGENT_WORKSTATE_ON(5, "开班"),
    AGENT_WORKSTATE_OFF(6, "关班"),
    AGENT_WORKSTATE_SLEEP(7, "休眠"),
    AGENT_BUSYSTATE_BUSY(8, "忙"),
    AGENT_BUSYSTATE_LEISURE(9, "闲"),
    AGENT_CALLTYPE_VIRTUAL(10, "虚拟电话"),
    AGENT_CALLTYPE_AUTHENTIC(11, "物理电话"),
    AGENT_CALLSTATE_RINGING(12, "振铃"),
    AGENT_CALLSTATE_TALKING(13, "通话"),
    AGENT_CALLSTATE_LEISURE(14, "闲"),

    ;

    private int code;
    private String message;

    AgentEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    AgentEnum(String message) {
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
