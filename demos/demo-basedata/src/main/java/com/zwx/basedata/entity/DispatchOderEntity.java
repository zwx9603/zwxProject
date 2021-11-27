package com.zwx.basedata.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class DispatchOderEntity {
    @JSONField(name = "DPDID")
    private String DPDID;

    @JSONField(name = "CDLX")
    private String CDLX;

    @JSONField(name = "AJBH")
    private String AJBH;

    @JSONField(name = "AFDZ")
    private String AFDZ;

    @JSONField(name = "LASJ")
    private String LASJ;

    @JSONField(name = "AJLX")
    private String AJLX;

    @JSONField(name = "AJLXDM")
    private Integer AJLXDM;

    @JSONField(name = "AJDJ")
    private String AJDJ;

    @JSONField(name = "GISX")
    private String GISX;

    @JSONField(name = "GISY")
    private String GISY;

    @JSONField(name = "ZGJGID")
    private String ZGJGID;

    @JSONField(name = "ZGJGMC")
    private String ZGJGMC;

    @JSONField(name = "RYBK")
    private Integer RYBK;

    @JSONField(name = "PCSL")
    private Integer PCSL;

    @JSONField(name = "CJSJ")
    private String CJSJ;

    @JSONField(name = "CJXXLIST")
    private List<FuJianEntity> CJXXLIST;
}
