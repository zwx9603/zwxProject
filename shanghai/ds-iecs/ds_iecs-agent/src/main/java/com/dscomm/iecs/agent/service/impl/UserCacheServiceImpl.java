package com.dscomm.iecs.agent.service.impl;

import com.dscomm.iecs.agent.graphql.typebean.UserLanguage;
import com.dscomm.iecs.agent.service.UserCacheService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.AccessBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.NotifyNotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:用户缓存管理服务
 *
 * @author YangFuxi
 * Date Time 2019/8/30 10:34
 */
@Component
public class UserCacheServiceImpl implements UserCacheService {
    private static final Logger logger = LoggerFactory.getLogger(UserCacheServiceImpl.class);
    private LogService logService;
    private static Map<String, UserInfo> allUserInfoCache = new ConcurrentHashMap<>();
    private static Map<String, AccessBean> accessCache = new ConcurrentHashMap<>();
    private static Map<String, UserInfo> userInfoCache = new ConcurrentHashMap<>();
    private static Map<String, String> userLanguageCache = new ConcurrentHashMap<>();
    private static Map<String, Long> subAccessCache = new ConcurrentHashMap<>();
    private NotifyNotesService notifyNotesService;

    @Autowired
    public UserCacheServiceImpl(LogService logService, NotifyNotesService notifyNotesService) {
        this.logService = logService;
        this.notifyNotesService = notifyNotesService;
    }



    @Override
    public Map<String, AccessBean> modifyAccessCache(String cachePrefix, String operation, AccessBean bo, Boolean transmit) {
        if ("put".equals(operation)) {
            accessCache.put(bo.getSystemName(), bo);
        } else if ("remove".equals(operation)) {
            accessCache.remove(bo.getSystemName());
        }
        String path = "rest/iecs/v1.0/notifyOtherNodes/accessCache/" + operation;
        notifyNotesService.notifyNodes(path, transmit, bo);
        return accessCache;
    }

    @Override
    public Map<String, AccessBean> findAllAccess(String cachePrefix) {
        return accessCache;
    }


    @Override
    public AccessBean  findKeyAccess(String key){
        return accessCache.get( key );
    }

    /**
     * {@inheritDoc}
     *
     * @see #modifySubSystemAccessCache(String, String, Long, Boolean)
     */
    @Override
    public Map<String, Long> modifySubSystemAccessCache(String cachePrefix, String clientIp, Long accessTime, Boolean transmit) {
        subAccessCache.put(clientIp, accessTime);
        AccessBean bo = new AccessBean();
        bo.setClientIp(clientIp);
        bo.setLastAccessTime(accessTime);
        String path = "rest/iecs/v1.0/notifyOtherNodes/subAccessCache";

        notifyNotesService.notifyNodes(path, transmit, bo);
        return subAccessCache;
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAllSubSystemAccessCache(String)
     */
    @Override
    public Map<String, Long> findAllSubSystemAccessCache(String cachePrefix) {
        return subAccessCache;
    }


    @Override
    public Map<String, UserInfo> modifyAllUserInfoCache( String cachePrefix, String operation, UserInfo
            userInfo, Boolean transmit ) {
        if ("put".equals(operation)) {
            allUserInfoCache.put(userInfo.getAccount(), userInfo);
        } else if ("remove".equals(operation)) {
            allUserInfoCache.remove(userInfo.getAccount());
        }

        return allUserInfoCache;
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAllOnlineUserInfoCache(String)
     */
    @Override
    public Map<String, UserInfo> findAllUserInfoCache(String cachePrefix) {
        return allUserInfoCache;
    }

    @Override
    public Map<String, UserInfo> modifyOnlineUserInfoCache(String cachePrefix, String operation, UserInfo
            userInfo, Boolean transmit) {
        if ("put".equals(operation)) {
            userInfoCache.put(userInfo.getAccount(), userInfo);
            allUserInfoCache.put(userInfo.getAccount(), userInfo);
        } else if ("remove".equals(operation)) {
            userInfoCache.remove(userInfo.getAccount());
            allUserInfoCache.remove(userInfo.getAccount());

        }
        String path = "rest/iecs/v1.0/notifyOtherNodes/userInfoCache/" + operation;

        notifyNotesService.notifyNodes(path, transmit, userInfo);
        return userInfoCache;
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAllOnlineUserInfoCache(String)
     */
    @Override
    public Map<String, UserInfo> findAllOnlineUserInfoCache(String cachePrefix) {
        return userInfoCache;
    }

    /**
     * {@inheritDoc}
     *
     * @see #modifyUserLanguage(String, String, String, Boolean)
     */
    @Override
    public Map<String, String> modifyUserLanguage(String cachePrefix, String systemName, String language, Boolean transmit) {
        userLanguageCache.put(systemName, language);
        UserLanguage bo = new UserLanguage();
        String path = "rest/iecs/v1.0/notifyOtherNodes/userLanguageCache";

        notifyNotesService.notifyNodes(path, transmit, bo);
        return userLanguageCache;
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAllUserLanguage(String)
     */
    @Override
    public Map<String, String> findAllUserLanguage(String cachePrefix) {
        return userLanguageCache;
    }
}
