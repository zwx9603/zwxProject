package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.basedata.graphql.inputbean.OrganizationQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 机构服务类
 */
public interface OrganizationService {


    /** 强制更新全部有效机构缓存
     * @return 返回结果
     */
    void forceUpdateCacheOrganization( );


    /** 获得机构id 与 机构名称 对应关系的map
     * @return 返回结果
     */
    Map<String,String> findOrganizationNameMap( );

    /** 获得全部有效机构
     * @return 返回结果
     */
    Map<String, OrganizationBean> findOrganizationAll( );


    /** 获得全部有效机构-树状
     * @return 返回结果
     */
    List<OrganizationBean> findTreeOrganization( );

    /** 获得某类全部有效机构
     * @return 返回结果
     */
    List<OrganizationBean> findOrganizationNatureAll( String squadronId , Integer nature  );


    /**
     * 获取分权分域机构信息
     * @param  categoryCodes 机构类别
     * @return 机构集合
     */
    Map<String, OrganizationBean> findOrganizationAllAuthorization(String organizationId , List<String> categoryCodes );


    /**
     * 描述：根据机构id查询指定机构详情
     *
     * @param organizationId 机构ID
     * @return 返回机构详情Bean
     */
    OrganizationBean findOrganizationByOrganizationId(String organizationId);


    /**
     * 根据条件查询机构信息
     *
     * @param inputInfo 条件
     * @return 返回机构详情Bean
     */
    List<OrganizationBean> findOrganizationCondition(OrganizationQueryInputInfo inputInfo);


    /**
     * 描述：根据机构id获得下级机构信息
     *
     * @param organizationId 机构ID
     * @return 返回机构详情Bean
     */
    List<OrganizationBean> findOrganizationChildren(String organizationId);

    /**
     * 描述：根据机构id获得平级机构信息
     *
     * @param organizationId 机构ID
     * @return 返回机构详情Bean
     */
    List<OrganizationBean> findOrganizationSamelevel (String organizationId);

    /**
     * 描述：根据机构id获得上级机构信息
     *
     * @param organizationId 机构ID
     * @return 返回机构详情Bean
     */
    OrganizationBean findOrganizationHigherlevel(String organizationId );


    /**
     * 描述：根据机构id 获得 机构查询码信息
     *
     * @param organizationId 机构ids
     * @return 返回机构查询码
     */
    String  findSearchPathById  ( String   organizationId  );


    /**
     * 描述：根据机构ids获得 机构查询码信息
     *
     * @param organizationIds 机构ids
     * @return 返回机构查询码
     */
    List<String> findSearchPathByIds ( List<String>  organizationIds );


    /**
     * 描述：根据机构ids获得 机构列表
     *
     * @param organizationIds 机构ids
     * @return 返回机构列表
     */
    List<OrganizationBean> findOrganizationsByIds ( List<String>  organizationIds );

    /**
     * 获取机构id集合
     *
     * @return 机构code集合
     */
    Set<String> findOrganizationIdSet();

    /**
     * 根据机构id获取父id，包含本身id
     *
     * @param organizationId 机构id
     * @return 返回code集合
     */
    List<String> findParentOrganizationId(String organizationId );


    /**
     * 根据id获取父id，包含本身id
     *
     * @param organizationIds 机构id集合
     * @return 返回code集合
     */
    List<String> findParentOrganizationIds (List<String> organizationIds);


    /**
     * 根据机构id获取子单位id集合(包含自身)
     *
     * @param organizationId 机构code
     * @return 子单位code集合
     */
    List<String> findChildOrganizationId(String organizationId);

    /**
     * 根据机构id获取子单位id集合（包含自身）
     *
     * @param organizationIds 机构id集合
     * @return 子单位id集合
     */
    List<String> findChildOrganizationIds (List<String> organizationIds);


    /**
     * 根据机构id获取机构编码
     *
     * @param organizationId 机构id集合
     * @return 编码
     */
     String  findOrganizationCodesById  ( String  organizationId );

    /**
     * 根据机构ids获取机构编码
     *
     * @param organizationIds 机构id集合
     * @return 编码
     */
    List<String> findOrganizationCodesByIds (List<String> organizationIds);


    /**
     * 根据机构编码获取机构id
     *
     * @param organizationCode 机构id集合
     * @return 编码
     */
    String  findOrganizationIdsByCode  ( String  organizationCode );

    /**
     * 根据机构编码s获取机构id
     *
     * @param organizationCodes 机构id集合
     * @return 编码
     */
    List<String> findOrganizationIdsByCodes (List<String> organizationCodes);



    /**
     * 获取根机构
     * @return 返回根机构
     */
    OrganizationBean getRootOrg();


    /**
     * 获取根机构id
     * @return 返回根机构
     */
    String getRootOrgId();


}
