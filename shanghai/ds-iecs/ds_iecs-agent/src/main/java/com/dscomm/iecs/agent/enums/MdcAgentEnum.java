package com.dscomm.iecs.agent.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述 : 调度台坐席枚举
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/9/26 18:49
 */
public enum MdcAgentEnum implements BasicEnumNumber {
    // type(code,name)
    MDCAGENT_WORKSTATE_ONLINE(0, "在线"),
    MDCAGENT_WORKSTATE_OFFLINE(1, "离线"),
    MDCAGENT_CALLSTATE_CALLOUT(4, "外呼"),
    MDCAGENT_CALLSTATE_TALKING(5, "通话"),
    MDCAGENT_CALLSTATE_RING(6, "振铃");

    private int code;
    private String message;

    MdcAgentEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    MdcAgentEnum(String message) {
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
