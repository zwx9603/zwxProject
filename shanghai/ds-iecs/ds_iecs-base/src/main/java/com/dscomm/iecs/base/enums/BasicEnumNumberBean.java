package com.dscomm.iecs.base.enums;

import java.io.Serializable;

/**
 * 描述:基础枚举Bean
 *
 * @author YangFuxi
 * Date Time 2019/8/14 11:23
 */
public class BasicEnumNumberBean implements Serializable {
    private String type;
    private Integer code;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
