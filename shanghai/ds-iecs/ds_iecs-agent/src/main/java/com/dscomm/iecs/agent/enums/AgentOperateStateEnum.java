package com.dscomm.iecs.agent.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:坐席操作状态
 *
 * @author YangFuxi
 * Date Time 2019/10/29 17:18
 */
public enum AgentOperateStateEnum implements BasicEnumNumber {
    AGENT_OPERATE_STATE_OFFLINE(1, "未登录"),
    AGENT_OPERATE_STATE_LOGIN(2, "登录"),
    AGENT_OPERATE_STATE_IDLE(3, "空闲"),
    AGENT_OPERATE_STATE_ACCEPT(4, "接警"),
    AGENT_OPERATE_STATE_HANGINGUP(5, "挂起"),
    AGENT_OPERATE_STATE_SLEEP(6, "休眠"),
    AGENT_OPERATE_STATE_HANDLE(7, "再处警"),

    ;

    private int code;
    private String message;

    AgentOperateStateEnum(int code, String message) {
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
