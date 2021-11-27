
package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.graphql.firetypebean.CommanderBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentDossierBean;

import java.util.List;

/**
 * 描述:案件其他服务
 *
 */
public interface IncidentInfoService {

    /**
     * 获取警情卷宗
     *
     * @param incidentId 事件id
     * @return 警情卷宗
     */
    IncidentDossierBean findIncidentDossier(String incidentId);


    /**
     *  保存 案件群组信息
     *
     * @param incidentId 警情ID
     * @return -警情详情
     */
    Boolean  saveIncidentGroup (String incidentId , String incidentGroupId );


    /**
     *  根据案件id查询 现场指挥员信息( 车辆 ) 杭州
     *
     * @param incidentId 警情ID
     * @param whetherNotCommander  是否查询非现场指挥员
     * @return -警情详情
     */
    List<CommanderBean> findVehiclePersonsByIncidentId (String incidentId , Boolean  whetherNotCommander );


}

