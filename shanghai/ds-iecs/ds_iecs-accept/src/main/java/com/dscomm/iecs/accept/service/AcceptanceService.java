package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.AcceptanceInformationBean;



/**
 * 描述：受理单 服务
 */
public interface AcceptanceService {


    /**
     * 根据警情id(案件id)获取案件报警信息 ( 受理记录 )
     *
     * @param incidentId 警情id参数
     * @return 案件报警信息列表
     */
    AcceptanceInformationBean findAcceptance(String incidentId);

    /**
     * 变更受理单关联的案件id
     *
     * @param sourceIncidentId 原案件id
     * @param targetIncidentId 目标案件id
     */
    void changeAcceptanceIncidentId(String sourceIncidentId, String targetIncidentId);

    /**
     * 恢复受理单关联的案件id
     *
     * @param incidentId 案件id
     */
    void recoverAcceptanceIncidentId(String incidentId);

}
