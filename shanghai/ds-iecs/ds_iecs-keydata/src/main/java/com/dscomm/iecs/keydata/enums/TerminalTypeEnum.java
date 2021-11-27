package com.dscomm.iecs.keydata.enums;

import com.dscomm.iecs.base.enums.BasicEnumNumber;

/**
 * 描述:终端类型
 *
 * @author YangFuxi
 * Date Time 2019/9/7 9:22
 */
public enum TerminalTypeEnum implements BasicEnumNumber {
    CADAGENT(0,"cadAgent"),//cad接处警坐席
    BRANCHOFFICE(1,"branchOffice"),//科所队
    HANDHELDTERMINAL(2,"handheldTerminal"),//手持终端
    ;
    private int code;
    private String message;

    TerminalTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getType() {
        return super.name();
    }

    @Override
    public String getName() {
        return this.message;
    }
}
