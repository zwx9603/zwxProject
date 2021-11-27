package com.dscomm.iecs.basedata.service;



import com.dscomm.iecs.basedata.graphql.inputbean.OnDutySummarySaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.OnDutySummaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.OnDutySummaryOrganizationBean;

import java.util.List;

/**
 * 描述：值班信息服务
 *
 */
public interface OnDutyService {


    /**
     * 更新 值班信息 （ 主要更新 联系人 联系方式 ）
     * @param inputInfo  更新信息
     * @return 值班信息列表
     */
     Boolean  updateOnDuty( OnDutySummarySaveInputInfo inputInfo );


    /**
     * 根据 机构id 获得本机构以及全部下级机构的值班信息
     * @param organizationId 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryOrganizationBean> findAllOrganizationOnDuty(String organizationId);


    /**
     * 根据 机构id 获得支队值班信息
     * @param organizationId 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryOrganizationBean> findOrganizationOnDuty(  String organizationId );

    /**
     * 根据机构id 获得大队机构值班信息 （ ）
     * @param organizationId 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryOrganizationBean> findChildrenOrganizationOnDuty(   String organizationId );


    /**
     * 根据值班时间 机构id 获得管辖中队值班信息
     * @param organizationId 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryOrganizationBean> findSquadronOrganizationOnDuty(   String organizationId );



    /**
     * 根据机构ids 获得值班信息
     * @param organizationIds 机构id
     * @return 值班信息列表
     */
    List<OnDutySummaryBean> findOnDutyByOrganizationIds(List<String> organizationIds );


}
