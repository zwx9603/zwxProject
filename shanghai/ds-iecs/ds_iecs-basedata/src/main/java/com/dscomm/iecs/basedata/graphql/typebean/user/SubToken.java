package com.dscomm.iecs.basedata.graphql.typebean.user;

/**
 * 描述:第三方系统token
 *
 */
public class SubToken {
    private String clientIp; //访问ip
    private Long accessTime; //访问时间
    private String system; //系统

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Long accessTime) {
        this.accessTime = accessTime;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
