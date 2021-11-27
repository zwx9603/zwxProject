package com.dscomm.iecs.basedata.service.enums;

public enum  AttachmentRelationEnum {

    // type(code,name)
    LOCAL("XCXX", "现场信息"),
    PLAN("YAJBXX", "预案基本信息");
    private String code;
    private String name;

    AttachmentRelationEnum(String code, String message) {
        this.code = code;
        this.name = message;
    }

    public static String getEnumName(String code){
        AttachmentRelationEnum[] values = AttachmentRelationEnum.values();
        for (AttachmentRelationEnum value : values) {
            if (value.getCode().equals(code)){
                return value.getName();
            }
        }
        return null;
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
