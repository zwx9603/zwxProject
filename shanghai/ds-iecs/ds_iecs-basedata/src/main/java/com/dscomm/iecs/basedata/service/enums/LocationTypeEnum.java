package com.dscomm.iecs.basedata.service.enums;

import com.dscomm.iecs.base.enums.BasicEnum;

/**
 * 描述:定位类型
 * author:YangFuXi
 * Date:2021/6/2 Time:19:15
 **/
public enum LocationTypeEnum implements BasicEnum {
    LOCATION_LBS("LOCATION_LBS","基站定位"),//基站定位
    LOCATION_GPS("LOCATION_GPS","gps定位"),
    LOCATION_MOBILE("LOCATION_MOBILE","手机定位"),
    LOCATION_WECHAT("LOCATION_WECHAT","微信定位"),
    LOCATION_SMS("LOCATION_SMS","短信定位"),
    LOCATION_MAP("LOCATION_MAP","手动辅助定位"),
    LOCATION_GHSZD("LOCATION_GHSZD","固话三字段定位"),
    LOCATION_NETWORK("LOCATION_NETWORK","互联网定位"),
    LOCATION_KEYUNIT("LOCATION_KEYUNIT","重点单位定位"),
    LOCATION_ADDRESS("LOCATION_ADDRESS","地址定位"),
    LOCATION_OTHER("LOCATION_OTHER","其它定位"),
    ;
    private String code;
    private String content;

    LocationTypeEnum(String code, String content) {
        this.code = code;
        this.content = content;
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
        return this.content;
    }
}
