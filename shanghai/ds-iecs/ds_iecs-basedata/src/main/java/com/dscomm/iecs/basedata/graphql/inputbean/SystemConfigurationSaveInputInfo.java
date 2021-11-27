package com.dscomm.iecs.basedata.graphql.inputbean;

/**
 * 描述：系统配置 保存参数
 *
 */
public class SystemConfigurationSaveInputInfo   {

    private String id ; //主键

    private String configType; //配置项

    private String configDesc; //配置描述

    private String configValue; //配置数值

    private String remarks; // 备注

    private Integer valid  ;  //是否启用 0不启用 1启用


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
