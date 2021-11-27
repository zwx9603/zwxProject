package com.dscomm.iecs.keydata.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;

/**
 * 描述:审计日志操作类型枚举类
 *
 * @author YangFuxi
 * Date Time 2019/9/6 14:12
 */
public enum AuditOperateTypeEnum implements BasicEnumNumber {
    INCIDENTCIRCULATE(0,"警情接管"),//警情接管操作
    INCIDENTCIRCULATEOK(1,"警情接管成功"),//警情接管成功
    ;
    private int code;
    private String message;

    AuditOperateTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getType() {
        return this.name();
    }

    @Override
    public String getName() {
        return this.message;
    }
}
