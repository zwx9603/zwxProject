package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.basedata.dal.po.SystemConfigurationEntity;
import com.dscomm.iecs.basedata.graphql.inputbean.SystemConfigurationSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;

/**
 * 描述 :系统配置 数据转换工具
 */
public class SystemTransformUtil {

    /**
     * 描述：转换 系统配置
     *
     * @param source 系统配置保存INPUT
     * @return 系统配置PO
     */
    public static SystemConfigurationEntity transform(SystemConfigurationSaveInputInfo source) {
        if( source != null ){
            SystemConfigurationEntity target = new SystemConfigurationEntity();
            target.setId(source.getId());
            target.setConfigType(source.getConfigType());
            target.setConfigDesc(source.getConfigDesc());
            target.setConfigValue(source.getConfigValue());
            target.setRemarks(source.getRemarks());
            target.setValid( source.getValid() );
            return target;
        }
        return  null ;

    }

    /**
     * 描述：转换 系统配置
     *
     * @param source 系统配置PO
     * @return 系统配置BO
     */
    public static SystemConfigurationBean transform(SystemConfigurationEntity source) {
        if( source != null ){
            SystemConfigurationBean target = new SystemConfigurationBean();
            target.setId(source.getId());
            target.setConfigType(source.getConfigType());
            target.setConfigDesc(source.getConfigDesc());
            target.setConfigValue(source.getConfigValue());
            target.setRemarks(source.getRemarks());
            target.setValid( source.getValid() );
            return target;
        }
        return  null ;
    }



}
