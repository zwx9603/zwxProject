package com.dscomm.iecs.ext;

import com.dscomm.iecs.base.enums.BaseEnum;

/**
 * 描述： 集结点状态
 *
 */
public enum RallyPointStatusBakEnum implements BaseEnum {

    SAVED("已保存"),
    SENT("已下达");

    private String title;

    RallyPointStatusBakEnum(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
