package com.dscomm.iecs.agent.utils;

import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.basedata.dal.po.AgentEntity;

/**
 * 描述 : 坐席模块转换工具
 *
 */
public class AgentTransformUtil {
    private AgentTransformUtil() {
    }

    /**
     * 数据库坐席转换
     *
     * @param source 数据库坐席PO
     * @return 数据库坐席BO
     */
    public static AgentBean transform(AgentEntity source  ) {
        if (null != source) {
            AgentBean target = new AgentBean();
            target.setId(source.getId());
            target.setAgentNumber(source.getAgentNumber());
            target.setExtensionNumber(source.getExtensionNumber());
            target.setEmerPhone(source.getEmerPhone());
            target.setOrganizationCode(source.getOrganizationCode());
            target.setIp(source.getIp());
            target.setLoginAcd(source.getLoginAcd());
            target.setExtensionType(source.getExtensionType());
            target.setAgentType(source.getAgentType());
            target.setSkillLevel(source.getSkillLevel());
            target.setLoginState(source.getLoginState());
            target.setLoginNum(source.getLoginNum());
            target.setOrder(source.getOrder());
            target.setJdlx21(source.getJdlx21());
            target.setRoom(source.getRoom());
            target.setDccode(source.getDccode());
            target.setDcpassword(source.getDcpassword());
            target.setLatesttime(source.getLatesttime());
            target.setIcpip(source.getIcpip());
            target.setSpeakerid(source.getSpeakerid());
            target.setWluserid(source.getWluserid());
            target.setWluserpassword(source.getWluserpassword());
            target.setWlserverinfo(source.getWlserverinfo());
            target.setTalkinggroup(source.getTalkinggroup());
            target.setWldeviceid(source.getWldeviceid());
            target.setWlconferenceid(source.getWlconferenceid());
            target.setElteproxymode(source.getElteproxymode());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 数据库坐席转换
     *
     * @param source 数据库坐席PO
     * @return 数据库坐席BO
     */
    public static AgentBean transform(AgentEntity source , AgentBean  target  ) {
        if (null != source) {
            target.setId(source.getId());
            target.setAgentNumber(source.getAgentNumber());
            target.setExtensionNumber(source.getExtensionNumber());
            target.setEmerPhone(source.getEmerPhone());
            target.setOrganizationCode(source.getOrganizationCode());
            target.setIp(source.getIp());
            target.setLoginAcd(source.getLoginAcd());
            target.setExtensionType(source.getExtensionType());
            target.setAgentType(source.getAgentType());
            target.setSkillLevel(source.getSkillLevel());
            target.setLoginState(source.getLoginState());
            target.setLoginNum(source.getLoginNum());
            target.setOrder(source.getOrder());
            target.setJdlx21(source.getJdlx21());
            target.setRoom(source.getRoom());
            target.setDccode(source.getDccode());
            target.setDcpassword(source.getDcpassword());
            target.setLatesttime(source.getLatesttime());
            target.setIcpip(source.getIcpip());
            target.setSpeakerid(source.getSpeakerid());
            target.setWluserid(source.getWluserid());
            target.setWluserpassword(source.getWluserpassword());
            target.setWlserverinfo(source.getWlserverinfo());
            target.setTalkinggroup(source.getTalkinggroup());
            target.setWldeviceid(source.getWldeviceid());
            target.setWlconferenceid(source.getWlconferenceid());
            target.setElteproxymode(source.getElteproxymode());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return target ;
    }


}
