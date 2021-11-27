package com.dscomm.iecs.accept.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 天气现象
 */
public enum WeatherPhenomenaEnum {
    QING("01", "晴"),
    DY("02", "多云"),
    YIN("05", "阴"),
    ZNY("22", "阵雨"),
    LZY("24", "雷阵雨"),
    LZYBYBB("26", "雷阵雨伴有冰雹"),
    YJX("48", "雨夹雪"),
    XY("27", "小雨"),
    ZGYU("28", "中雨"),
    DAYU("29", "大雨"),
    BYU("34", "暴雨"),
    DBYU("35", "大暴雨"),
    TDBYU("36", "特大暴雨"),
    ZNX("51", "阵雪"),
    XX("44", "小雪"),
    ZGX("45", "中雪"),
    DX("46", "大雪"),
    BX("47", "暴雪"),
    WU("57", "雾"),
    DGYU("37", "冻雨"),
    SCB("61", "沙尘暴"),
    XDZY("38", "小到中雨"),
    ZDDY("39", "中到大雨"),
    DDBY("40", "大到暴雨"),
    BYDDBY("41", "暴雨到大暴雨"),
    DBYDTDBY("42", "大暴雨到特大暴雨"),
    XDZX("52", "小到中雪"),
    ZDDX("53", "中到大雪"),
    DDBX("54", "大到暴雪"),
    FC("60", "浮尘"),
    YS("59", "扬沙"),
    QSCB("62", "强沙尘暴"),
    MAI("58", "霾"),
    UNKNOWN(null, "未知");

    private String code;
    private String name;

    WeatherPhenomenaEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        List<WeatherPhenomenaEnum> list = Arrays.asList(WeatherPhenomenaEnum.values());
        for (WeatherPhenomenaEnum weatherPhenomenaEnum : list) {
            if (Objects.nonNull(code) && code.equals(weatherPhenomenaEnum.getCode())){
                return weatherPhenomenaEnum.getName();
            }
        }
        return WeatherPhenomenaEnum.UNKNOWN.getName();
    }

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
