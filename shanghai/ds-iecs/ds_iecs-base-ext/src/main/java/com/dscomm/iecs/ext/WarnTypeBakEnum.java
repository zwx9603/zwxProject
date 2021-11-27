package com.dscomm.iecs.ext;


public enum WarnTypeBakEnum {
    WARN_TYPE_BKRS("BKRS","被困人数"),
    WARN_TYPE_RSMJ("RSMJ","燃烧面积"),
    WARN_TYPE_RSDX("RSDX","燃烧对象"),
    WARN_TYPE_ZHCS("ZHCS","火灾场所"),
    WARN_TYPE_ZDDW("ZDDW","重点单位"),
    WARN_TYPE_YJJQ("YJJQ","夜间警情"),
    WARN_TYPE_UNKNOWN("UNKNOWN","未知")
    ;
    private  String code;
    private  String name;

    public static String getEnumName(String code){
        WarnTypeBakEnum[] values = WarnTypeBakEnum.values();
        for (WarnTypeBakEnum value : values) {
            if (value.getCode().equals(code)){
                return value.getName();
            }
        }
        return null;
    }

    WarnTypeBakEnum(String code, String name) {
        this.code = code;
        this.name = name;
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
