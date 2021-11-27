package com.dscomm.iecs.basedata.service;


/**
 * 描述： 基础数据 缓存信息
 */
public interface BaseDateCacheService {





    /**
     *  强制更新 全部基础数据需缓存数据 （ 频繁  ）
     */
    void updateBaseDateCache(    ) ;


    /**
     *  强制更新 全部基础数据需缓存数据 ( 不频繁 )
     */
    void updateBaseDateNoKeyCache(    ) ;


}
