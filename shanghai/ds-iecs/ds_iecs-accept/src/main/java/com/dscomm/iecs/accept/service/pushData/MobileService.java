package com.dscomm.iecs.accept.service.pushData;


import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentStatusChangeBean;
import com.dscomm.iecs.accept.graphql.typebean.InstructionBean;

import java.util.List;

public interface MobileService {


    /**
     * 手机移动端消息通知接口
     *  调派车辆信息
     *  MobileMessageEnum NEW_INCIDENT("001","处置警情")
     * */
    void dispatchVehicle(IncidentBean incidentBean,  HandleBean handleBean ) ;

    /**
     * 手机移动端消息通知接口
     *   指令信息
     *  NEW_INSTRUCTION("002","指令信息")
     * */
    void instruction (String incidentId , List<String> receivingObjectIds , InstructionBean instructionBean ) ;

    /**
     * 手机移动端消息通知接口
     *  案件状态更新
     *  NEW_INCIDENT_STATE("003","案件状态变更"),
     * */
    void caseStat( IncidentBean incidentBean  ,
                   IncidentStatusChangeBean incidentStatusChangeBean   ) ;

    /**
     * 手机移动端消息通知接口
     *  案件信息更新
     *  UPDATE_INCIDENT("004","案件信息更新"),
     * */
    void modifyCase (IncidentBean incidentBean  ) ;



    /**
     * 手机移动端消息通知接口
     *   车辆状态更新
     *  VEHICLE_STATE_CHANGE("006","车辆状态更新")
     * */
    void carStatus(  String incidentId , String handleId ,   String  vehicleId  ,
                     String vehicleStatusCode     ) ;

    /**
     * 手机移动端消息通知接口
     *   车辆状态更新
     *  VEHICLE_STATE_CHANGE("006","车辆状态更新")
     * */
    void carStatus(  String incidentId , String handleId ,   List<String>  vehicleIds  ,
                     String vehicleStatusCode     ) ;



}
