package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述：指挥序列
 *
 * @author YangFuXi Date Time 2018/7/6 8:49
 */
public class CommandSequenceBean {
    //指挥ID
    private String commandId;
    //启动指挥事件
    private Long startCommandTime;
    //结束指挥时间
    private Long endCommandTime;
    //环节异常信息
    List<ErrorInfoBean> errorInfo;
    //警情环节集合
    List<IncidentProcessStepBean> processSteps;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public Long getStartCommandTime() {
        return startCommandTime;
    }

    public void setStartCommandTime(Long startCommandTime) {
        this.startCommandTime = startCommandTime;
    }

    public Long getEndCommandTime() {
        return endCommandTime;
    }

    public void setEndCommandTime(Long endCommandTime) {
        this.endCommandTime = endCommandTime;
    }

    public List<ErrorInfoBean> getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(List<ErrorInfoBean> errorInfo) {
        this.errorInfo = errorInfo;
    }

    public List<IncidentProcessStepBean> getProcessSteps() {
        return processSteps;
    }

    public void setProcessSteps(List<IncidentProcessStepBean> processSteps) {
        this.processSteps = processSteps;
    }
}
