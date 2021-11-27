package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.IncidentStatusChangeBean;

import java.util.List;


/**
 * 描述：案件状态变动 服务
 */
public interface IncidentStatusChangeService {

    /**
     * 根据警情id 获得警情状态变动记录
     *
     * @param incidentId 警情id
     * @return 返回结果
     */
    List<IncidentStatusChangeBean> findIncidentStatusChange(String incidentId );

    /**
     * 保存 案件状态变动
     *
     * @param incidentId 警情id
     * @return 返回结果
     */
    Boolean saveIncidentStatusChange(String incidentId  , String incidentStatusCode  ,Boolean  whetherWobSocket  ,
                                     String handleId   );









}
