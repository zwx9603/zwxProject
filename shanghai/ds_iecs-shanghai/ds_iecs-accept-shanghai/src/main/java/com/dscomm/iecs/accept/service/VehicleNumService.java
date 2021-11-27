package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.VehicleNumBean;

import java.util.List;


public interface VehicleNumService {

    /*
    * 查询应派车辆、已派车辆、本次调派车辆的信息
    * */
    List<VehicleNumBean> findVehicleNumInfo(String id,String czdx,String ajdj,String ajlx);
}
