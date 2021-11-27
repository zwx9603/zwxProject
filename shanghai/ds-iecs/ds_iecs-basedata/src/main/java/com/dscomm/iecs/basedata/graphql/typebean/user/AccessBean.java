package com.dscomm.iecs.basedata.graphql.typebean.user;

import java.io.Serializable;

/**
 * 描述:访问信息BO
 *
 */
public class AccessBean implements Serializable {
    private String clientIp; //访问ip
    private String systemName; //系统名称
    private long lastAccessTime; //上次访问时间
    private String agentNum; //坐席台号

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }
}
