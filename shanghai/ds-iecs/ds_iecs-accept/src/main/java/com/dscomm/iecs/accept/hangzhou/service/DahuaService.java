package com.dscomm.iecs.accept.hangzhou.service;

import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;

import java.util.Map;

public interface DahuaService {
    /**
     * 获取大华服务接口token请求头信息
     * @return
     */
    Map<String,String> getHeaders();


    void asyncPushIncident(IncidentBean incidentBean );


    void asyncPushIncidentStatus(  IncidentBean incidentBean  );


    void asyncPushHandle(  IncidentBean incidentBean  , HandleBean handleBean );


    void asyncPushCarStatus(String vehicleId, String vehicleStatusCode);



}
