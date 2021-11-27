package com.dscomm.iecs.base.config.cas;

import org.springframework.beans.factory.annotation.Value;

/**
 * 描述:cas认证配置类
 *
 * @author YangFuxi
 * Date Time 2020/1/14 16:48
 */

public class CasConfigBean {
    @Value("${cas.isOpenCas:false}")
    private String isOpenCas;
    @Value("${cas.casUrl:}")
    private String casUrl;

    @Value("${serverUrl:}")
    private String serverUrl;

    @Value("${cas.excludePaths:}")
    private String excludePaths;

    @Value("${appWebUrl:}")
    private String appWebUrl;

    @Value("${clusterNode:}")
    private String clusterNode ;

    public String getIsOpenCas() {
        return isOpenCas;
    }

    public void setIsOpenCas(String isOpenCas) {
        this.isOpenCas = isOpenCas;
    }

    public String getCasUrl() {
        return casUrl;
    }

    public void setCasUrl(String casUrl) {
        this.casUrl = casUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(String excludePaths) {
        this.excludePaths = excludePaths;
    }

    public String getAppWebUrl() {
        return appWebUrl;
    }

    public void setAppWebUrl(String appWebUrl) {
        this.appWebUrl = appWebUrl;
    }

    public String getClusterNode() {
        return clusterNode;
    }

    public void setClusterNode(String clusterNode) {
        this.clusterNode = clusterNode;
    }
}
