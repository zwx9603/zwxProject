package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;

/** 对接 coc 服务 */
public interface DockingService {



    /**
     * COC 新警情提醒
     * */
    Boolean newCOCCase(IncidentBean incidentBean);

    /**
     * COC 修改警情提醒
     * */
    Boolean modifyCOCCase(IncidentBean incidentBean );

}
