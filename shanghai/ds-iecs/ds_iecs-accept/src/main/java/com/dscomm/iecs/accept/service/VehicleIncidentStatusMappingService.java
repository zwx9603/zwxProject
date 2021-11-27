package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.VehicleIncidentStatusMappingBean;


/**
 * 描述： 车辆案件状态关系 服务
 */
public interface VehicleIncidentStatusMappingService {


    /**
     * 根据案件类型  获得车辆案件状态关系
     *
     * @param incidentTypeCode  案件类型
     * @param vehicleStatusCode 车辆状态
     * @return  返回结果
     */
    VehicleIncidentStatusMappingBean findVehicleIncidentStatusMapping (String incidentTypeCode , String vehicleStatusCode );



}
