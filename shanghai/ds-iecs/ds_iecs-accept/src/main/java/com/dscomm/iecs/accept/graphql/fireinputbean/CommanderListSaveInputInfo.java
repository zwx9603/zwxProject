package com.dscomm.iecs.accept.graphql.fireinputbean;

import java.util.List;

/**
 * 描述： 现场指挥员录入
 *
 * @author AIguibin Date time 2018/7/16 10:29
 */
public class CommanderListSaveInputInfo {

    private String  incidentId ; //案件id

    private List<CommanderSaveInputInfo> commanderList ;

    public List<CommanderSaveInputInfo> getCommanderList() {
        return commanderList;
    }

    public void setCommanderList(List<CommanderSaveInputInfo> commanderList) {
        this.commanderList = commanderList;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }
}
