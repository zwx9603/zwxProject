package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.service.OrganizationCacheService;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述:
 * author:YangFuXi
 * Date:2021/5/18 Time:14:04
 **/
@Component
@CacheConfig(cacheNames = "orgcache")
public class OrganizationCacheServiceImpl implements OrganizationCacheService {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationCacheServiceImpl.class);
    private static Map<String, String> orgIdNameCache = new ConcurrentHashMap<>();//机构单位id-name 缓存
    private static Map<String, OrganizationBean> orgBeanCache = new ConcurrentHashMap<>();//机构单位id-bean 缓存
    private static OrganizationBean rootOrg = null;//机构根节点
    private static Map<String, String> orgIdCodeCache = new ConcurrentHashMap<>();//机构ID-code缓存
    private static Map<String, String> orgCodeIdCache = new ConcurrentHashMap<>();//机构code-ID缓存
    private static List<OrganizationBean> orgTreeCache = new CopyOnWriteArrayList<>();//机构树状缓存


    @CachePut(key = "'org_id_name'")
    @Override
    public Map<String, String> modifyOrgIdNameCache(String id, String name) {
        try {
            if (!StringUtils.isBlank(id)&&!StringUtils.isBlank(name)) {
                orgIdNameCache.put(id, name);
            }
        } catch (Exception ex) {
            logger.error(String.format("fail to modify cache:%s", "org_id_name"), ex);
        }
        return orgIdNameCache;
    }

    @Cacheable(key = "'org_id_name'")
    @Override
    public Map<String, String> findOrgIdNameCache() {
        try {
            return orgIdNameCache;
        } catch (Exception ex) {
            logger.error(String.format("fail to find cache:%s", "org_id_name"), ex);
            return orgIdNameCache;
        }

    }

    @CachePut(key = "'org_id_bean'")
    @Override
    public Map<String, OrganizationBean> modifyOrgBeanCache(String operation, String id, OrganizationBean bean) {
        try {
            if ("put".equals(operation)) {
                if (!StringUtils.isBlank(id)) {
                    orgBeanCache.put(id, bean);
                }
            } else {
                if (!StringUtils.isBlank(id)) {
                    orgBeanCache.remove(id);
                }
            }
        } catch (Exception ex) {
            logger.error(String.format("fail to modify cache:%s", "org_id_name"), ex);
        }
        return orgBeanCache;
    }

    @Cacheable(key = "'org_id_bean'")
    @Override
    public Map<String, OrganizationBean> findOrgBeanCache() {
        try {
            return orgBeanCache;
        } catch (Exception ex) {
            logger.error(String.format("fail to find cache:%s", "org_id_bean"), ex);
            return orgBeanCache;
        }

    }

    @CachePut(key = "'org_root'")
    @Override
    public OrganizationBean modifyRootOrg(OrganizationBean bean) {
        try {
            rootOrg = bean;
        } catch (Exception ex) {
            logger.error(String.format("fail to modify cache:%s", "org_id_name"), ex);
        }
        return rootOrg;
    }

    @Cacheable(key = "'org_root'")
    @Override
    public OrganizationBean findRootOrg() {
        try {
            return rootOrg;
        } catch (Exception ex) {
            logger.error(String.format("fail to find cache:%s", "org_id_name"), ex);
            return rootOrg;
        }
    }

    @CachePut(key = "'org_id_code'")
    @Override
    public Map<String, String> modifyOrgIdCodeCache(String id, String code) {
        try {
            if (!StringUtils.isBlank(id)&&!StringUtils.isBlank(code)){
                orgIdCodeCache.put(id,code);
            }
        } catch (Exception ex) {
            logger.error(String.format("fail to modify cache:%s，id:%s,code:%s", "org_id_code",id,code), ex);
        }
        return orgIdCodeCache;

    }

    @Cacheable(key = "'org_id_code'")
    @Override
    public Map<String, String> findOrgIdCodeCache() {
        try {
            return orgIdCodeCache;
        } catch (Exception ex) {
            logger.error(String.format("fail to find cache:%s", "org_id_code"), ex);
            return orgIdCodeCache;
        }

    }

    @CachePut(key = "'org_code_id'")
    @Override
    public Map<String, String> modifyOrgCodeIdCache(String code, String id) {
        try {
            if (!StringUtils.isBlank(code)&&!StringUtils.isBlank(id)){
                orgCodeIdCache.put(code,id);
            }
        } catch (Exception ex) {
            logger.error(String.format("fail to modify cache:%s", "org_id_name"), ex);
        }
        return orgCodeIdCache;
    }

    @Cacheable(key = "'org_code_id'")
    @Override
    public Map<String, String> findOrgCodeIdCache() {
        try {
            return orgCodeIdCache;
        } catch (Exception ex) {
            logger.error(String.format("fail to find cache:%s", "org_id_name"), ex);
            return orgCodeIdCache;
        }

    }

    @CachePut(key = "'org_tree'")
    @Override
    public List<OrganizationBean> modifyOrgTreeCache(List<OrganizationBean> treeOrg) {
        try {
            orgTreeCache=treeOrg;
        } catch (Exception ex) {
            logger.error(String.format("fail to modify cache:%s", "org_id_name"), ex);
        }
        return orgTreeCache;
    }

    @Cacheable(key = "'org_tree'")
    @Override
    public List<OrganizationBean> findOrgTreeCache() {
        try {
            return orgTreeCache;
        } catch (Exception ex) {
            logger.error(String.format("fail to find cache:%s", "org_id_name"), ex);
            return orgTreeCache;
        }

    }
}
