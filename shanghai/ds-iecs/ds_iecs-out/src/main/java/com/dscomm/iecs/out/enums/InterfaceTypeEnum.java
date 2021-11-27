package com.dscomm.iecs.out.enums;


import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述： 接口名称枚举
 *
 */
public enum InterfaceTypeEnum implements BasicEnum {

    // type(code,name)
    BJJL("getAlarmBjjlListByTime","推送报警记录信息"),
    JQXX("getAlarmJqListByTime","推送警情信息"),
    JQDPXX("getAlarmJqcjDpListByTime","推送警情调派信息"),
    //JQCZDPTJFA("getAlarmJqcjDpTjfaListByTime","推送警情处警调派推荐方案"),
    JQXLList("getAlarmJqclList","推送车辆列表"),
    JQLYList("getAlarmJqlyListByTime","推送警情录音信息"),
    JQWSXX("getAlarmJqwsListByTime","推送警情文书信息"),
    JQXCXX("getAlarmJqxcListByTime","推送警情现场信息"),
    THJLList("getAlarmThjlListByTime","推送通话记录信息"),


    ;

    private String code;
    private String message;

    InterfaceTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    InterfaceTypeEnum(String message) {
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
