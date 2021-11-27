package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.firetypebean.IncidentDossierDrawingBean;
import com.dscomm.iecs.accept.graphql.firetypebean.IncidentSummaryBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentReplayBean;


/**
 * 描述：案件（警情信息） 服务
 */
public interface IncidentFireService {



    IncidentSummaryBean findIncidentSummary(String incidentId );


    //图上指挥卷宗
    IncidentDossierDrawingBean findIncidentDossierDrawing(String incidentId);

    /**
     * 获取已经于处理过的警情回溯内容
     *
     * @param incidentId 警情ID
     * @return 返回回溯内容
     */
    IncidentReplayBean incidentReplayReady(String incidentId);





}
