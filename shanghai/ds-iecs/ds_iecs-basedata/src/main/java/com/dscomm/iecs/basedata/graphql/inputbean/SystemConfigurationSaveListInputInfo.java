package com.dscomm.iecs.basedata.graphql.inputbean;

import java.util.List;

/**
 * 描述：系统配置 保存参数
 *
 */
public class SystemConfigurationSaveListInputInfo {

   List<SystemConfigurationSaveInputInfo> systemConfigurationSaveInputInfos ;

    public List<SystemConfigurationSaveInputInfo> getSystemConfigurationSaveInputInfos() {
        return systemConfigurationSaveInputInfos;
    }

    public void setSystemConfigurationSaveInputInfos(List<SystemConfigurationSaveInputInfo> systemConfigurationSaveInputInfos) {
        this.systemConfigurationSaveInputInfos = systemConfigurationSaveInputInfos;
    }
}
