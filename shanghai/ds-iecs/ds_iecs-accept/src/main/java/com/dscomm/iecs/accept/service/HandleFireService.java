package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.DispatchAgencyCarBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;

import java.util.List;

/**
 * 描述：处警记录（调派记录） 实战指挥 服务
 */
public interface HandleFireService {


    /**
     * 根据案件id查询 出动单位（去重）
     *
     * @param incidentId 案件id
     * @return 单位 列表
     */
    List<OrganizationBean> findIncidentHandleOrganizationBean(String incidentId);


    /**
     * 根据案件id统计出动车辆
     *
     * @param incidentId 案件id
     * @return 统计信息
     */
    List<DispatchAgencyCarBean> findIncidentHandleVehicle(String incidentId);


    /**
     * 根据案件id统计人员（随车人数）
     * @param incidentId 案件id
     * @return 统计信息
     * */
    Integer findIncidentParticipant(String incidentId);




}
