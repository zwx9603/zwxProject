package com.dscomm.iecs.accept.enums;

import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

public enum MajorIncidentRuleEnum implements BasicEnum {
    // type(code,name)
    BKRS("bkrs","被困人数"),
    RSMJ("rsmj","燃烧面积"),
    RSDX("rsdx","燃烧对象"),
    ZHCS("zhcs","火灾场所"),
    ZDDW("zddw","重点单位"),
    MGSJ("mgsj","敏感时间"),
    MGDQ("mgdq","敏感地区"),
    AJDJ("ajdj","案件等级"),
    SSRS("ssrs","受伤人数"),
    SWRS("swrs","死亡人数");

    private String code;
    private String message;

    MajorIncidentRuleEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    MajorIncidentRuleEnum(String message) {
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
