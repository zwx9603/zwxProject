package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.OrganizationInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.VehiclexxInputInfo;

public interface OrganizationInfoService {
    /*
    * 修改消防力量机构信息
    * */
    Boolean updateOrganizationInfo(OrganizationInputInfo parse);

    /*
    * 保存消防力量车辆信息
    * */
    Boolean saveVehicleInfo(VehiclexxInputInfo info);

    /*
     * 修改消防力量车辆信息
     * */
    Boolean updateVehicleInfo(VehiclexxInputInfo info);
}
