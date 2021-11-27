package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;

import java.util.List;
import java.util.Map;

/**
 * 描述:字典缓存类
 * author:YangFuXi
 * Date:2021/5/17 Time:14:13
 **/
public interface DictionaryCacheService {
    /**
     * 修改字典缓存(key-value形式)
     * @param key 字典key
     * @param dicMap 字典缓存
     * @return 返回缓存信息
     */
    Map<String,String> modifyDickeyValueCache(String key,Map<String,String> dicMap);

    /**
     * 根据字典类型查询字典缓存(key-value形式)
     * @param dicType 字典类型
     * @return 返回对应类型的字典缓存
     */
    Map<String, String> findDickeyValueCache(String dicType);

    /**
     * 修改字典缓存(字典对象)
     * @param dicType 字典类型
     * @param list 字典
     * @return 返回字典缓存
     */
    List<DictionaryBean> modifyDicBeanCache(String dicType,List<DictionaryBean> list);

    /**
     * 修改字典缓存(字典对象)
     * @param dicType 字典类型
     * @return 返回字典缓存
     */
    List<DictionaryBean> findDicBeanCache(String dicType);

    Map<String,SystemConfigurationBean> modifySystemConfigCache(String type,SystemConfigurationBean bean);

    Map<String,SystemConfigurationBean> findAllSystemConfigCache();
}
