package com.dscomm.iecs.agent.service;

import com.dscomm.iecs.basedata.graphql.typebean.user.AccessBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;

import java.util.Map;

/**
 * 描述:用户信息缓存
 *
 * @author YangFuxi
 * Date Time 2019/8/30 9:57
 */
public interface UserCacheService {
    /**
     * 保存/修改 访问记录缓存
     *
     * @param cachePrefix 缓存key前缀
     * @param operation   操作类型 put/remove
     * @param bo          访问记录
     * @param transmit    是否传递 用于通知其他节点是否同步更新缓存,一般应设置为true
     * @return 返回所有缓存
     */
    Map<String, AccessBean> modifyAccessCache(String cachePrefix, String operation, AccessBean bo, Boolean transmit);

    /**
     * 获取所有访问记录缓存
     *
     * @param cachePrefix 缓存key前缀
     * @return 返回所有访问记录缓存
     */
    Map<String, AccessBean> findAllAccess(String cachePrefix);


    /**
     * 获取所有访问记录缓存
     *
     * @param key 缓存key
     * @return 返回所有访问记录缓存
     */
    AccessBean  findKeyAccess(String key);

    /**
     * 保存、修改第三方系统访问记录
     *
     * @param cachePrefix 缓存前缀
     * @param clientIp 访问ip
     * @param accessTime 访问时间
     * @param transmit 是否传递 用于通知其他节点是否同步更新缓存,一般应设置为true
     * @return 返回所有系统访问记录
     */
    Map<String, Long> modifySubSystemAccessCache(String cachePrefix,String clientIp,Long accessTime, Boolean transmit);

    /**
     * 获取所有第三方系统访问记录
     *
     * @param cachePrefix 缓存前缀
     * @return 返回所有系统访问记录
     */
    Map<String, Long> findAllSubSystemAccessCache(String cachePrefix);

    /**
     * 保存/修改人员信息
     *
     * @param cachePrefix 缓存key前缀
     * @param operation   操作类型 put/remove
     * @param userInfo    人员信息
     * @param transmit    是否传递 用于通知其他节点是否同步更新缓存,一般应设置为true
     * @return 返回所有在线人员信息
     */
    Map<String, UserInfo> modifyAllUserInfoCache( String cachePrefix, String operation, UserInfo
            userInfo, Boolean transmit );

    /**
     * 返回所有人员信息
     *
     * @param cachePrefix 缓存key前缀
     * @return 返回所有在线人员信息
     */
    Map<String, UserInfo> findAllUserInfoCache(String cachePrefix);

    /**
     * 保存/修改在线人员信息
     *
     * @param cachePrefix 缓存key前缀
     * @param operation   操作类型 put/remove
     * @param userInfo    人员信息
     * @param transmit    是否传递 用于通知其他节点是否同步更新缓存,一般应设置为true
     * @return 返回所有在线人员信息
     */
    Map<String, UserInfo> modifyOnlineUserInfoCache(String cachePrefix, String operation, UserInfo userInfo, Boolean transmit);

    /**
     * 返回所有在线人员信息
     *
     * @param cachePrefix 缓存key前缀
     * @return 返回所有在线人员信息
     */
    Map<String, UserInfo> findAllOnlineUserInfoCache(String cachePrefix);

    /**
     * 维护用户语言缓存
     *
     * @param cachePrefix 缓存前缀
     * @param systemName  用户账号
     * @param language    用户语言
     * @param transmit  是否传递 用于通知其他节点是否同步更新缓存,一般应设置为true
     * @return 返回用户所用语言
     */
    Map<String, String> modifyUserLanguage(String cachePrefix, String systemName, String language, Boolean transmit);

    /**
     * 获取所有用户语言信息
     *
     * @param cachePrefix 缓存前缀
     * @return 返回所有用户语言信息
     */
    Map<String, String> findAllUserLanguage(String cachePrefix);
}
