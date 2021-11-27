package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.inputbean.SystemConfigurationSaveListInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：系统配置 服务
 */
public interface SystemConfigurationService {


    /** 强制更新全部字典缓存
     * @return 返回结果
     */
    void forceUpdateCacheSystemConfiguration( );


    /**
     *  查询 全部系统配置信息

     * @return 保存结果
     */
    Map<String, SystemConfigurationBean> findSystemConfigurationMap();

    /**
     *  查询 全部系统配置信息

     * @return 保存结果
     */
    List<SystemConfigurationBean> findSystemConfiguration();



    /**
     * 根据 configType 获取系统配置信息

     * @param configType 配置项
     * @return 返回查询结果
     */
    SystemConfigurationBean getSystemConfigByConfigType  (String configType  );


    /**
     * 根据 systemConfigurationType 列表获取系统配置项列表
     *
     * @param configTypeList 配置项列表
     * @return 返回查询结果
     */
    List<SystemConfigurationBean> getSystemConfigByConfigTypeList(List<String> configTypeList);


    /**
     * 保存修改系统配置
     *
     * @param inputInfo 系统 保存参数
     * @return 保存结果
     */
    List<SystemConfigurationBean> saveSystemConfiguration(SystemConfigurationSaveListInputInfo inputInfo);


    /**
     *  根据系统配置id 是否启用 系统配置
     * @param systemConfigId 配置项
     * @param   valid 是否启用 0不启用 1启用
     * @return 返回查询结果
     */
    SystemConfigurationBean enabledSystemConfig  (String systemConfigId , Integer  valid  );



}
