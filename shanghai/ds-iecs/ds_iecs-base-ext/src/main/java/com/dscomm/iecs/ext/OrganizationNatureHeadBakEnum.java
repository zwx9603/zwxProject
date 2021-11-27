package com.dscomm.iecs.ext;

import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述 : 机构性质 头两位
 *
 */
public enum OrganizationNatureHeadBakEnum implements BasicEnum {

    // type(code,name)
    ORGANIZATION_NATURE_HEAD_SJZD("02", "省级总队") , //省级总队
    ORGANIZATION_NATURE_HEAD_DSZD("03", "地市支队") , //地市支队
    ORGANIZATION_NATURE_HEAD_XXDD("05", "区县大队") , //区县大队
    ORGANIZATION_NATURE_HEAD_XJZD("09", "县级中队") , //乡镇中队
    ;
    private String code;
    private String message;

    OrganizationNatureHeadBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    OrganizationNatureHeadBakEnum(String message) {
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
}
