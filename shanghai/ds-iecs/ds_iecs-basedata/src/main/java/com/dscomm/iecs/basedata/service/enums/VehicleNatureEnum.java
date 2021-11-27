package com.dscomm.iecs.basedata.service.enums;

import com.dscomm.iecs.base.enums.BasicEnum;

/**
 * 描述:车辆性质枚举 作战或支援
 * author:YangFuXi
 * Date:2021/6/2 Time:14:17
 **/
public enum VehicleNatureEnum implements BasicEnum {
    CLXZDM_ZZ("CLXZDM_ZZ","作战"),
    CLXZDM_ZY("CLXZDM_ZY","支援");
    private String code;
    private String message;

    VehicleNatureEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
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
