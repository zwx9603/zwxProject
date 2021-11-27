package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.basedata.graphql.typebean.RegionBean;
import com.dscomm.iecs.basedata.service.RegionCacheService;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 * author:YangFuXi
 * Date:2021/5/21 Time:18:34
 **/
@Component
@CacheConfig(cacheNames = "regioncache")
public class RegionCacheServiceImpl implements RegionCacheService {
    private static final Logger logger = LoggerFactory.getLogger(RegionCacheServiceImpl.class);
    private static Map<String, RegionBean> regionCache = new ConcurrentHashMap<>();

    @Override

    @CachePut(key = "'region'")
    public Map<String, RegionBean> modifyRegionCache(String id, RegionBean bean) {
        try {
            if (!StringUtils.isBlank(id)) {
                regionCache.put(id, bean);
            }
        } catch (Exception ex) {
            logger.error("modify cache fail", ex);
        }
        return regionCache;
    }

    @CacheEvict(key = "'region'")
    @Override
    public Map<String, RegionBean> cleanRegionCache() {
        regionCache.clear();
        return regionCache;
    }

    @Cacheable(key = "'region'")
    @Override
    public Map<String, RegionBean> findAllRegionCache() {
        return regionCache;
    }
}
