package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.service.TTSCacheService;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:预警信息缓存
 * author:YangFuXi
 * Date:2021/5/20 Time:16:19
 **/
@Component
@CacheConfig(cacheNames = "speakfilecache")
public class TTSCacheServiceImpl implements TTSCacheService {
    private static final Logger logger = LoggerFactory.getLogger(TTSCacheServiceImpl.class);
    private static Map<String, String> earlywarningCache = new ConcurrentHashMap<>();//预警tts路径缓存
    private static Map<String, String> handleOrganizationSpeakFileCache = new ConcurrentHashMap<>();//处警tts路径缓存

    @CachePut(key = "'earlywarningtts'")
    @Override
    public Map<String, String> modifyEarlyWarningSpeakFileCache(String operator, String id, String ttspath) {
        try {
            if (!StringUtils.isBlank(id)) {
                if ("put".equals(operator)) {
                    earlywarningCache.put(id, ttspath);
                } else {
                    earlywarningCache.remove(id);
                }
            }
        } catch (Exception ex) {
            logger.error("fail to modify cache:earlywarning", ex);
        }
        return earlywarningCache;
    }

    @Cacheable(key = "'earlywarningtts'")
    @Override
    public Map<String, String> findAllEarlyWarningSpeakFileCache() {
        return earlywarningCache;
    }

    @CachePut(key = "'handletts'")
    @Override
    public Map<String, String> modifyHandleOrganizationSpeakFileCache(String operator, String id, String ttspath) {
        try {
            if (!StringUtils.isBlank(id)) {
                if ("put".equals(operator)) {
                    handleOrganizationSpeakFileCache.put(id, ttspath);
                } else {
                    handleOrganizationSpeakFileCache.remove(id);
                }
            }
        } catch (Exception ex) {

        }
        return handleOrganizationSpeakFileCache;
    }

    @Cacheable(key = "'handletts'")
    @Override
    public Map<String, String> findAllHandleOrganizationSpeakFileCache() {
        return handleOrganizationSpeakFileCache;
    }
}
