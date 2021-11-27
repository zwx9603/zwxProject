package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 指令记录保存参数
 *
 */
public class InstructionRecordHandleSaveInputInfo {

    private String id ; //主键

    private String handleResultDesc;//  处理结果 简要情况

    private String handlePersonName;//   处理人_姓名


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHandleResultDesc() {
        return handleResultDesc;
    }

    public void setHandleResultDesc(String handleResultDesc) {
        this.handleResultDesc = handleResultDesc;
    }

    public String getHandlePersonName() {
        return handlePersonName;
    }

    public void setHandlePersonName(String handlePersonName) {
        this.handlePersonName = handlePersonName;
    }
}
