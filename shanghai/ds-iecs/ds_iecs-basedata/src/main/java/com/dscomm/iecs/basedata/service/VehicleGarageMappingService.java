package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.inputbean.VehicleGarageMappingQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.VehicleGarageMappingSaveListInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.VehicleGarageMappingBean;

import java.util.List;

/**
 * 描述：中队车辆与车库门对应关系 服务
 */
public interface VehicleGarageMappingService {

    /**
     * 根据条件获得车辆与车库关系
     * 机构id集合
     * 关系类型集合
     * 车辆id集合
     * 车库id集合
     * @param queryInputInfo 查询条件
     * @return 车辆与车库关系列表
     */
    List<VehicleGarageMappingBean> findVehicleGarageMappingCondition(VehicleGarageMappingQueryInputInfo queryInputInfo);

    /**
     * 保存 车辆与车库关系
     *
     * @param inputInfo 车辆与车库关系 保存参数
     * @return 保存结果
     */
    List<VehicleGarageMappingBean> saveVehicleGarageMapping(VehicleGarageMappingSaveListInputInfo inputInfo);


}
