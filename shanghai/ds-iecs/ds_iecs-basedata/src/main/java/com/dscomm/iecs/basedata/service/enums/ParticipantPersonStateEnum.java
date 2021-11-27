package com.dscomm.iecs.basedata.service.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;

/**
 * 描述:车辆人员状态
 * author:YangFuXi
 * Date:2021/6/7 Time:18:54
 **/
public enum  ParticipantPersonStateEnum implements BasicEnumNumber {
    PERSON_DISPATCHED(10,"已调派"),
    PERSON_APPROACH(20,"进场"),
    PERSON_EXIT(30,"退场"),
            ;
    private int code;
    private String content;

    ParticipantPersonStateEnum(int code, String content) {
        this.code = code;
        this.content = content;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
