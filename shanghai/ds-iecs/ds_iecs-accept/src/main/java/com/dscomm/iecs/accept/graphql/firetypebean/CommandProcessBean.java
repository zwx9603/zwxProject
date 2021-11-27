package com.dscomm.iecs.accept.graphql.firetypebean;

import java.util.List;

public class CommandProcessBean {

    //指令
    private List<InstructionRecordBean> instructionRecordBeanList;

    public List<InstructionRecordBean> getInstructionRecordBeanList() {
        return instructionRecordBeanList;
    }

    public void setInstructionRecordBeanList(List<InstructionRecordBean> instructionRecordBeanList) {
        this.instructionRecordBeanList = instructionRecordBeanList;
    }
}
