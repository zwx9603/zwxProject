package com.zwx.basedata.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class FuJianEntity {
    @JSONField(name = "JGID")
    private Integer JGID;

    @JSONField(name = "JGMC")
    private String JGMC;

    @JSONField(name = "JGLX")
    private String JGLX;

    @JSONField(name = "LXDH")
    private String LXDH;

}
