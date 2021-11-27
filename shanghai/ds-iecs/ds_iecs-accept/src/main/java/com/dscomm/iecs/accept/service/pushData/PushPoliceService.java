package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.AccidentBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.TelephoneBean;

/** 推送 警情信息 公安 */
public interface PushPoliceService {

    /**
     * 向公安发送三字段
     * */
    Boolean sendPolicePhone(TelephoneBean telephoneBean);
    /**
     * 新警情发送公安
     * */
    Boolean sendPoliceIncident(IncidentBean incidentBean);

    /**
     * 处警发送公安
     * */
    Boolean sendPoliceHandle(HandleBean handleBean);

    /**
     * 向公安发送求援
     * */
    Boolean sendPoliceHelp(OutsideInputInfo queryBean   );


    /**
     * 向公安发送反馈
     * */
    Boolean sendPoliceFeedBack(AccidentBean accidentBean);

    /**
     * 警单信息返回
     */
    Boolean sendIncidentReceiveState( String incidentId , String  originalIncidentNumber ,String districtCode );

}
