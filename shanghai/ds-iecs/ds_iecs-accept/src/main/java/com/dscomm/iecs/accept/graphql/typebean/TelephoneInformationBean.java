package com.dscomm.iecs.accept.graphql.typebean;


import java.util.List;

/**
 * 描述: 电话报警信息Bean
 *
 */
public class TelephoneInformationBean {

    private TelephoneBean mainTelephone;    //主案件电话报警记录

    private List<TelephoneBean> telephoneList; //案件相关的所有电话报警记录

    public TelephoneBean getMainTelephone() {
        return mainTelephone;
    }

    public void setMainTelephone(TelephoneBean mainTelephone) {
        this.mainTelephone = mainTelephone;
    }

    public List<TelephoneBean> getTelephoneList() {
        return telephoneList;
    }

    public void setTelephoneList(List<TelephoneBean> telephoneList) {
        this.telephoneList = telephoneList;
    }
}
