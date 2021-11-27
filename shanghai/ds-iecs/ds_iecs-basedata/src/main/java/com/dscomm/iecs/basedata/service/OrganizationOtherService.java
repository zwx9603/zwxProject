package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.basedata.graphql.typebean.*;

import java.util.List;

/**
 * 机构其他服务类  包含 机构外部单位信息  机构地方非消防机构车辆  机构间的毗邻关系
 */
public interface OrganizationOtherService {

    /**
     * 描述：根据机构id 获得 非消防机构车辆
     *
     * @param organizationId 机构ID
     * @return 返回结果
     */
    List<OrganizationLocalFullTimeUnitBean> findOrganizationLocalFullTimeUnitByOrganizationId (String organizationId);

    /**
     * 描述：根据机构地方非消防机构车辆id获得机构地方非消防机构车辆情
     *
     * @param organizationFullTimeUnitId 机构地方非消防机构车辆ID
     * @return 返回结果
     */
    OrganizationLocalFullTimeUnitBean findOrganizationLocalFullTimeUnitById (String organizationFullTimeUnitId);


    /**
     * 描述：  构建 消防机构毗邻优先级缓存
     *
     * @return 返回结果
     */
    void buildAdjacentOrganizationCache  (     );


    /**
     * 描述：  构建 消防机构毗邻优先级缓存
     *
     * @return 返回结果
     */
    void buildAdjacentOrganization  (     );


    /**
     * 描述：根据主管机构 id 获得 消防机构毗邻优先级
     *
     * @param chargeOrganizationId 主管机构id
     * @return 返回结果
     */
    List<OrganizationAdjacentPriorityBean> findAdjacentOrganizationByChargeOrganizationId (String chargeOrganizationId);


    /**
     * 根据联勤保障单位id 获得 联勤保障单位信息
     */
    UnitJointServiceBean findUnitJointService( String  unitJointServiceId ) ;


    /**
     * 根据应急联动单位id 获得 应急联动单位信息
     */
    UnitEmergencyBean findUnitEmergency(String  unitEmergencyId  );


    /**
     * 根据坐标查范围内联动单位
     * @param radius 范围半径 米
     * @return 联动单位列表
     */
    List<UnitEmergencyBean> findUnitEmergencyRange( String longitude , String latitude , String radius );


    /**
     * 根据坐标查范围内联勤保障单位
     * @param radius 范围半径 米
     * @return 联动单位列表
     */
    List<UnitJointServiceBean> findUnitJointRange( String longitude , String latitude , String radius );


    /**
     * 根据坐标查范围内的消防机构
     * @param radius 范围半径 米
     * @return 联动单位列表
     */
    List<OrganizationBean> findOrganizationRange(String longitude , String latitude , String radius );


    /**
     * 获得可接警机构信息
     */
    List<OrganizationBean> findReceiveOrganization (  );


}
