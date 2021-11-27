package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.VehicleStateChangeQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.VehicleStatusChangeBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

import java.util.List;


/**
 * 描述：车辆状态变动记录 服务
 */
public interface VehicleStatusChangeService {



    /**
     * 根据车辆IDs 修改车辆状态信息
     *
     * @param vehicleIds         车辆ids
     * @param vehicleStatusCode 车辆状态
     * @return 修改结果
     */
    Boolean updateVehicleStatus( String  incidentId , String handleId ,   List<String> vehicleIds ,
                                 List<String> handleOrganizationVehicleIds   , String vehicleStatusCode  , String changeSource  );


    /**
     * 根据车辆ID 修改车辆状态信息
     *
     * @param vehicleId         车辆id
     * @param vehicleStatusCode 车辆状态
     * @return 修改结果
     */
    Boolean updateVehicleStatus( String  incidentId , String handleId ,
                                 String vehicleId ,  String handleOrganizationVehicleId   , String vehicleStatusCode  , String changeSource  );

    /**
     * 车辆状态变更记录（单个） 保存
     * @return 返回结果
     */
    Boolean saveVehicleStatusChange( String  incidentId , String handleId , String vehicleId  ,  String handleOrganizationVehicleId  , String vehicleStatusCode ,  String changeSource );




    //2021-05-01 车辆状态变更记录
    //列表显示  大队、所属机构、车辆名称、车牌号、变更状态、通过时间、坐席
    //按照时间排序
    //主要记录非战时状态申请 0306 0307 0308 0309 0399 0400 0500 0600 0700 0800 0900 0901 0902 0999 1000 1100 9900 9991
    // 除 通知 待命 出动 途中 到场 出水 停水 返回 中返 返队

    /**
     * 查询车辆状态变更记录
     * @param queryInputInfo
     * @return
     */
    PaginationBean<VehicleStatusChangeBean> findVehicleStateChangeRecord(VehicleStateChangeQueryInputInfo queryInputInfo);




}
