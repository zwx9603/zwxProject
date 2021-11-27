package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;

/** 对接服务 动态*/
public interface DockingChangeService {



    /**
     * COC 新警情提醒
     * */
    Boolean newCOCCase(IncidentBean incidentBean);

    /**
     * COC 修改警情提醒
     * */
    Boolean modifyCOCCase(IncidentBean incidentBean);


}
