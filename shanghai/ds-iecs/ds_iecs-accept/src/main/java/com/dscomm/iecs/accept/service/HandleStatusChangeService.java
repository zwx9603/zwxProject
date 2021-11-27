package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.IncidentVehicleStatusUpdateInputInfo;

/**
 * 描述：处警记录（调派记录） 服务
 */
public interface HandleStatusChangeService {




    /**
     * 更新案件多车辆状态
     *
     * @param  inputInfo 更新状态信息
     * @return 车辆信息 列表
     */
    void  updateIncidentVehicleStatus(IncidentVehicleStatusUpdateInputInfo inputInfo);



    /**
     * 根据 案件id 车辆id  更新作战车辆状态 返回作战车辆id
     *
     * @param incidentId 案件id
     */
    String  updateHandleOrganizationVehicleStatus( String incidentId , String  handleId   ,  String vehicleId , String newVehicleStatus   ) ;


    /**
     * 更新案件单车辆状态
     * @return 车辆信息 列表
     */
    void  buildIncidentVehicleStatus( String incidentId ,  String  handleId   ,    String  vehicleId  ,
                                      String newVehicleStatus  , String changeSource   );






}
