package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述：警情回溯
 *
 * @author YangFuXi Date Time 2018/7/9 10:19
 */
public class IncidentReplayBean {
    private IncidentInfoBaseInfoBean baseInformation;
    private List<CommandSequenceBean> commands;

    public IncidentInfoBaseInfoBean getBaseInformation() {
        return baseInformation;
    }

    public void setBaseInformation(IncidentInfoBaseInfoBean baseInformation) {
        this.baseInformation = baseInformation;
    }

    public List<CommandSequenceBean> getCommands() {
        return commands;
    }

    public void setCommands(List<CommandSequenceBean> commands) {
        this.commands = commands;
    }
}
