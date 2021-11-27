package com.dscomm.iecs.accept.service.bean.dahua.dispatch;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 发送单位
 */
public class FSDW {
    @JSONField(name="FSDW_TYWYSBM")
    private String FSDW_TYWYSBM; //通用唯一识别码
    @JSONField(name="FSDW_DWMC")
    private String FSDW_DWMC; //单位名称

    public FSDW(String FSDW_TYWYSBM, String FSDW_DWMC) {
        this.FSDW_TYWYSBM = FSDW_TYWYSBM;
        this.FSDW_DWMC = FSDW_DWMC;
    }
}
