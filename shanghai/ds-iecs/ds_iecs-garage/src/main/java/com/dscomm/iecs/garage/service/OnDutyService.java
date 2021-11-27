package com.dscomm.iecs.garage.service;


import com.dscomm.iecs.garage.service.bean.OnDutySummaryBean;

import java.util.List;

/**
 * 描述：值班信息服务
 *
 */
public interface OnDutyService {


    /**
     * 根据值班时间 机构id 获得本机构以及全部下级机构的值班信息
     * @param organizationId 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryBean> findAllOrganizationOnDuty(String organizationId);


    /**
     * 根据值班时间 机构id 获得本机构值班信息
     * @param organizationId 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryBean> findOrganizationOnDuty(   String organizationId);

    /**
     * 根据值班时间 机构id 获得下级机构值班信息
     * @param organizationId 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryBean> findChildrenOrganizationOnDuty(   String organizationId);


    /**
     * 根据值班时间 机构id 获得管辖中队值班信息
     * @param organizationId 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryBean> findSquadronOrganizationOnDuty(   String organizationId);


}
