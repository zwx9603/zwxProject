package com.dscomm.iecs.agent.service;


/**
 *  坐席最新登录用户
 */
public interface AgentLoginAccountLoginService {


    /**
     *  保存 坐席 登录人员账号
     * @param account
     * @return
     */
    Boolean saveAgentAccount( String id , String  agentIp , String agentNumber , String account ) ;


    /**
     *  根据ip地址获得  坐席账号信息
     * @return
     */
    String findAgentAccount(  String  id    ) ;


}
