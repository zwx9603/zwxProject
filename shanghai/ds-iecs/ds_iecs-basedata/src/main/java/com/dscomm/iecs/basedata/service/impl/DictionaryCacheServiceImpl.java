package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.DictionaryCacheService;
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

/**
 * 描述:字典缓存类
 * author:YangFuXi
 * Date:2021/5/17 Time:14:14
 **/
@Component
@CacheConfig(cacheNames = "basic_dictionary")
public class DictionaryCacheServiceImpl implements DictionaryCacheService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryCacheServiceImpl.class);
    private static Map<String, Map<String, String>> keyValueCache = new ConcurrentHashMap<>();//基础字典 key-value
    private static Map<String, List<DictionaryBean>> dicBeanCache = new ConcurrentHashMap<>();//基础字典 key-bean
    private static Map<String,SystemConfigurationBean> systemConfigurationCache = new ConcurrentHashMap<>();//系统配置项字典

    /**
     * {@inheritDoc}
     *
     * @see #modifyDickeyValueCache(String, Map)
     */
    @CachePut(key="'dic_key_value'.concat('-').concat(#dicType)")
    @Override
    public Map<String, String> modifyDickeyValueCache(String dicType, Map<String, String> dicMap) {
        try {
            if (!StringUtils.isBlank(dicType)) {
                keyValueCache.put(dicType, dicMap);
            }
            return dicMap;
        } catch (Exception ex) {
            logger.error(String.format("modify dictionary:%s fail", dicType), ex);
            return dicMap;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findDickeyValueCache(String)
     */
    @Cacheable(key="'dic_key_value'.concat('-').concat(#dicType)")
    @Override
    public Map<String, String> findDickeyValueCache(String dicType) {
        try {
            if (!StringUtils.isBlank(dicType)) {
                return keyValueCache.get(dicType);
            }
            return null;
        } catch (Exception ex) {
            logger.error(String.format("find dictionary:%s fail", dicType), ex);
            return null;
        }
    }
    /**
     * {@inheritDoc}
     *
     * @see #modifyDicBeanCache(String, List)
     */
    @Override
    @CachePut(key="'dic_bean_value'.concat('-').concat(#dicType)")
    public List<DictionaryBean> modifyDicBeanCache(String dicType, List<DictionaryBean> list) {
        try {
            if (!StringUtils.isBlank(dicType)){
                dicBeanCache.put(dicType,list);
                return list;
            }
            return null;
        }catch (Exception ex){
            logger.error(String.format("modifyDicBeanCache fail,dictionaryType:%s",dicType),ex);
            return null;
        }
    }
    /**
     * {@inheritDoc}
     *
     * @see #findDicBeanCache(String)
     */
    @Override
    @Cacheable(key="'dic_bean_value'.concat('-').concat(#dicType)")
    public List<DictionaryBean> findDicBeanCache(String dicType) {
        try {
            if (!StringUtils.isBlank(dicType)){
                return dicBeanCache.get(dicType);
            }
            return null;
        }catch (Exception ex){
            logger.error(String.format("findDicBeanCache fail,dictionaryType:%s",dicType),ex);
            return null;
        }
    }

    @CachePut(key = "'xtpz'")
    @Override
    public Map<String, SystemConfigurationBean> modifySystemConfigCache(String type, SystemConfigurationBean bean) {
        try {
            if (!StringUtils.isBlank(type)){
                systemConfigurationCache.put(type,bean);
            }
        }catch (Exception ex){
            logger.error(String.format("modifySystemConfigCache fail,dictionaryType:%s",type),ex);
        }
        return systemConfigurationCache;
    }

    @Cacheable(key = "'xtpz'")
    @Override
    public Map<String, SystemConfigurationBean> findAllSystemConfigCache() {
        try {
            return systemConfigurationCache;
        }catch (Exception ex){
            logger.error(String.format("findAllSystemConfigCache fail"),ex);
        }
        return systemConfigurationCache;
    }
}
