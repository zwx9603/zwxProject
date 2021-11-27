package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.inputbean.VehiclePersonsSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.VehiclePersonsBean;

import java.util.List;

/**
 * 描述：车辆人员服务
 */
public interface VehiclePersonService {

    /**
     * 根据车辆ids 获得车载人员（计划  ）
     * @return
     */
    List<VehiclePersonsBean>  findVehiclePersonsSplit (  List<String> vehicleIds   );



    /**
     * 根据车辆ids 获得车载人员（计划  ）
     * @return
     */
    List<VehiclePersonsBean>  findVehiclePersons( List<String> vehicleIds  );


    /**
     * 根据 机构 查询 车辆人员信息
     * @return
     */
    List<VehiclePersonsBean>  findVehiclePersonsByOrganizationId( String  organizationId   );


    /**
     *  保存 车辆人员信息
     * @return
     */
    Boolean  saveVehiclePersons( VehiclePersonsSaveInputInfo  inputInfo );






}
