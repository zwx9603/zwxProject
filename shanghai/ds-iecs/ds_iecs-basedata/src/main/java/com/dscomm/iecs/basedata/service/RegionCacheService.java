package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.basedata.graphql.typebean.RegionBean;

import java.util.Map;

/**
 * 描述:范围缓存
 * author:YangFuXi
 * Date:2021/5/21 Time:16:56
 **/
public interface RegionCacheService {
    /**
     * 修改范围缓存
     *
     * @param id   id
     * @param bean bean
     * @return 缓存
     */
    Map<String, RegionBean> modifyRegionCache(String id, RegionBean bean);

    /**
     * 清理缓存
     *
     * @return 缓存
     */
    Map<String, RegionBean> cleanRegionCache();

    /**
     * 获取全部缓存
     *
     * @return 缓存
     */
    Map<String, RegionBean> findAllRegionCache();
}
