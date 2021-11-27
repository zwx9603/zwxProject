package com.dscomm.iecs.agent.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:坐席状态
 *
 * @author YangFuxi
 * Date Time 2019/10/29 16:29
 */
public enum AgentStateEnum implements BasicEnumNumber {
    AGENT_STATE_OFFLINE(0, "未登录"),
    AGENT_STATE_INVALID(1, "无效"),
    AGENT_STATE_CLOSED(2, "闭席"), //关班
    AGENT_STATE_IDLE(3, "空闲"),//开班
    AGENT_STATE_BUSY(4, "忙碌"), //致忙
    AGENT_STATE_SLEEP(5, "休眠"),
    AGENT_STATE_HOLD(6, "事后整理"),
    AGENT_STATE_LOGIN_IDLE(7,"闭席且置闲");

    private int code;
    private String message;

    AgentStateEnum(int code, String message) {
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
