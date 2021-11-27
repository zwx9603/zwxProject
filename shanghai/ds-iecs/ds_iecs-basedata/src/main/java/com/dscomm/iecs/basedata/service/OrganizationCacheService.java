package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 * author:YangFuXi
 * Date:2021/5/18 Time:14:03
 **/
public interface OrganizationCacheService {
    /**
     * 修改机构ID-name 缓存
     * @param id 机构ID
     * @param name 机构名称
     * @return 返回缓存
     */
    Map<String, String> modifyOrgIdNameCache(String id,String name);

    /**
     * 获取机构ID-name 缓存
     * @return 返回缓存
     */
    Map<String, String> findOrgIdNameCache();

    /**
     * 修改机构缓存 ID-bean
     * @param operation 操作类型 put remove
     * @param id 机构ID
     * @param bean 机构bean
     * @return 返回缓存
     */
    Map<String, OrganizationBean> modifyOrgBeanCache(String operation,String id,OrganizationBean bean);

    /**
     * 获取所有的机构缓存 ID-beaan
     * @return 机构缓存
     */
    Map<String, OrganizationBean> findOrgBeanCache();

    /**
     * 修改根机构缓存
     * @param bean 根机构
     * @return 返回缓存
     */
    OrganizationBean modifyRootOrg(OrganizationBean bean);

    /**
     * 获取根机构缓存
     * @return 缓存
     */
    OrganizationBean findRootOrg();

    /**
     * 修改机构缓存 ID-code
     * @param id 机构ID
     * @param code 机构code
     * @return 返回缓存
     */
    Map<String, String> modifyOrgIdCodeCache(String id,String code);

    /**
     * 获取机构缓存 ID-code
     * @return 返回缓存
     */
    Map<String, String> findOrgIdCodeCache();
    /**
     * 修改机构缓存 code-ID
     * @param code 机构code
     * @param id 机构ID
     * @return 返回缓存
     */
    Map<String, String> modifyOrgCodeIdCache(String code,String id);
    /**
     * 获取机构缓存 code-ID
     * @return 返回缓存
     */
    Map<String, String> findOrgCodeIdCache();

    /**
     * 修改树状结构机构缓存
     * @param treeOrg 机构树状结构
     * @return 返回缓存
     */
    List<OrganizationBean> modifyOrgTreeCache(List<OrganizationBean> treeOrg);

    /**
     * 获取机构树状缓存
     * @return 返回缓存
     */
    List<OrganizationBean> findOrgTreeCache();

}
