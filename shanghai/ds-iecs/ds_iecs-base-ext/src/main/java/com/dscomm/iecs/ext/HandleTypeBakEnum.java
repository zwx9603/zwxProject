package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 案件 处理类型 枚举类
 *
 */
public enum HandleTypeBakEnum implements BasicEnum {
    // type(code,name)
    HANDLE_TYPE_WSL("0","未受理"),
    HANDLE_TYPE_WLA("1","未立案"),
    HANDLE_TYPE_LA("2","立案"),
    HANDLE_TYPE_CFBJ("3","重复报警"),
    HANDLE_TYPE_SR("4","骚扰"),
    HANDLE_TYPE_ZDGJ("5","主动挂机"),
    HANDLE_TYPE_ZX("6","咨询"),
    HANDLE_TYPE_WS("7","无声"),

    ;
    private String code;
    private String message;

    HandleTypeBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    HandleTypeBakEnum(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getType() {
        return super.name();
    }

    @Override
    public String getName() {
        return I18nMessageUtils.getI18nMessage(super.name(), message);
    }


    /**
     * 根据code获取message
     * @param code
     * @return
     */
    public static String getNameByCode(String code) {
        for (HandleTypeBakEnum statusEnum : HandleTypeBakEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}
