package com.dscomm.iecs.accept.graphql.typebean;


import java.util.List;

/**
 * 描述: 接警信息bean
 *
 */
public class AcceptanceInformationBean {

    private AcceptanceBean mainAcceptance;    //主案件接警信息

    private List<AcceptanceBean> acceptanceList; //案件相关的所有接警信息

    public AcceptanceBean getMainAcceptance() {
        return mainAcceptance;
    }

    public void setMainAcceptance(AcceptanceBean mainAcceptance) {
        this.mainAcceptance = mainAcceptance;
    }

    public List<AcceptanceBean> getAcceptanceList() {
        return acceptanceList;
    }

    public void setAcceptanceList(List<AcceptanceBean> acceptanceList) {
        this.acceptanceList = acceptanceList;
    }
}
