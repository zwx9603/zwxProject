package com.dscomm.iecs.accept.service;

import java.util.Map;

/**
 * 描述:预警信息缓存
 * author:YangFuXi
 * Date:2021/5/20 Time:16:18
 **/
public interface TTSCacheService {
    /**
     * 修改预警信息缓存
     * @param operator 操作类型 put remove
     * @param id id
     * @param ttspath tts路径
     * @return 返回所有缓存信息
     */
    Map<String,String> modifyEarlyWarningSpeakFileCache(String operator, String id, String ttspath);

    /**
     * 获取预警信息缓存
     * @return 返回预警缓存
     */
    Map<String,String> findAllEarlyWarningSpeakFileCache();

    /**
     * 修改预警信息缓存
     * @param operator 操作类型 put remove
     * @param id id
     * @param ttspath tts路径
     * @return 返回所有缓存信息
     */
    Map<String,String> modifyHandleOrganizationSpeakFileCache(String operator, String id, String ttspath);

    /**
     * 获取预警信息缓存
     * @return 返回预警缓存
     */
    Map<String,String> findAllHandleOrganizationSpeakFileCache();
}
