package com.dscomm.iecs.agent.enums;

import java.io.Serializable;

/**
 * 描述:枚举类信息bean
 *
 * @author YangFuxi
 * Date Time 2019/8/8 20:37
 */
public class EnumBO implements Serializable {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
