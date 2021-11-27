package com.dscomm.iecs.agent.service;

import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.graphql.typebean.AuditAgentSleepParamBO;
import com.dscomm.iecs.agent.graphql.typebean.OrganizationAgentBean;
import com.dscomm.iecs.agent.graphql.typebean.ViolationBean;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;

import java.util.List;

/**
 * 描述:座席话务状态service服务
 *
 * @author ZhaiYanTao
 * Date Time 2019/8/12 10:00
 */
public interface AgentService {


    /**
     * 根据坐席号获得 坐席信息
     *
     * @return 坐席列表
     */
    AgentBean findAgent( String agentNumber );

    /**
     * 获取全部坐席列表
     *
     * @return 坐席列表
     */
    List<AgentBean> findAllAgent();



    /**
     * 分权分域获取全部坐席列表
     *
     * @return 坐席列表
     */
    List<AgentBean> findAllAgentByAuthorization();


    /**
     * 分权分域获取全部坐席列表
     *
     * @return 坐席列表
     */
    List<AgentBean> findAllOnlineAgentByAuthorization();


    /**
     * 获取指定坐席类型的在线坐席列表
     * 传入坐席类型为空，返回所有坐席
     * （暂定:1返回接警类3个，2返回处警类3个，3返回高级2个；不传返回所有）
     *
     * @param agentType 坐席类型
     * @return 指定坐席类型的在线坐席列表
     */
    List<AgentBean> findOnlineAgentByAgentType(String agentType );


    /**
     * 获取指定坐席类型的在线坐席列表
     * 传入坐席类型为空，返回所有坐席
     * @param agentType 坐席类型
     * @return 指定坐席类型的在线坐席列表
     */
    List<AgentBean> findOnlineAgentByAgentType(List<String> agentType);

    /**
     * 根据人员角色类型获取对应在线坐席
     *
     * @param roleCodes 人员脚色
     * @return 返回在线坐席
     */
    List<AgentBean> findOnlineAgentByRoleCodes(Integer... roleCodes);

    /**
     * 根据人员角色类型获取对应坐席
     *
     * @param roleCodes 人员脚色
     * @return 返回坐席
     */
    List<AgentBean> findAgentByRoleCodes(Integer... roleCodes);

    /**
     * 获取单位下的所有在线的处警座席信息
     * @param orgId 单位id
     * @return 在线的处警员和处警班长座席列表
     */
    List<AgentBean> findOnlineDispatchAgentByOrgId(String orgId);

    /**
     * 根据角色code（可变参数）获取在线人员账号列表,不传参返回所有
     *
     * @param roleCodes 角色枚举code可变参数
     * @return 指定坐席类型的在线坐席列表
     */
    List<String> getOnlinePersonAccountByRoleCodes(Integer... roleCodes);

    /**
     * 根据申请人角色code，添加对应班长角色code到班长角色code列表
     *
     * @param monitorRoleCodeList   班长角色code列表
     * @param requestPersonRoleCode 申请人角色code
     */
    void addMonitorRoleCodeByRequestPersonRoleCode(List<Integer> monitorRoleCodeList, String requestPersonRoleCode);

    /**
     * 更改坐席状态（agentState）
     *
     * @param agentNum   坐席台号
     * @param agentState 坐席状态code
     * @param phone      坐席电话分机号
     * @return 更改后的坐席信息
     */
    AgentBean changeAgentState(String agentNum, Integer agentState, String phone);

    /**
     * 更改坐席操作状态（agentOperateState）
     *
     * @param agentNum          坐席台号
     * @param agentOperateState 坐席操作状态code
     * @param phone             坐席分机号
     * @return 更改后的坐席信息
     */
    AgentBean changeAgentOperateState(String agentNum, Integer agentOperateState, String phone);

    /**
     * 更改坐席话务状态（agentPhoneState）
     *
     * @param agentNum        坐席台号
     * @param agentPhoneState 坐席话务状态code
     * @param phone           坐席电话号码
     * @param meetingMark     是否会议中
     * @param remotePhone     对端电话
     * @return 更改后的坐席信息
     */
    AgentBean changeAgentPhoneState(String agentNum, Integer agentPhoneState, String phone, String meetingMark, String remotePhone);

    /**
     * 坐席登录(开班)操作
     *
     * @param agentBean 坐席信息
     */
    void agentLogin(AgentBean agentBean);

    /**
     * 坐席退出注销服务
     *
     * @param agentNum 坐席号
     * @param type     退出原因 OPERATIONTYPE_LOGOUT(110,"登出"), OPERATIONTYPE_FORCEDEXIT(120,"强制退出")
     */
    void agentLogOut(String agentNum, OperationTypeEnum type);

    /**
     *  删除缓存无效坐席
     */
    void deleteAgent( );

    /**
     * 查数据库坐席数据，更新进缓存，看缓存前缀，存不同的ip
     *
     * @return 成功标志
     */
    Boolean initAgentCache();

    /**
     * 向班长申请休眠(班长审批之后会发送websocket通知，收到通知之后)
     *
     * @param reasonCode 休眠原因code
     * @param reason     休眠原因
     * @return 返回操作结果
     */
    Boolean applyChangeToSleep(String reasonCode, String reason);

    /**
     * 审核坐席休眠申请
     *
     * @param bo 审核信息
     */
    Boolean auditSleep(AuditAgentSleepParamBO bo);

    /**
     * 更改休眠服务
     *
     * @param operate 操作类型，1代表休眠，0代表解除休眠
     * @param dormancyCode 休眠类型
     * @return 返回操作结果
     */
    Boolean changeSleepState(String operate, String dormancyCode);

    /**
     * 坐席(用户)强制退出
     *
     * @param agentNum 坐席号
     * @param account  账号
     * @param userinfoNum  班长坐席号
     * @return 返回操作结果
     */
    Boolean forceQuit(String agentNum, String account,String userinfoNum);

    /**
     * 更新坐席违规信息
     *
     * @param bo       坐席违规信息
     * @param agentNum 坐席号
     */
    void updateAgentViolation(ViolationBean bo, String agentNum);

    /**
     * 移除坐席违规信息
     *
     * @param agentNum 坐席号
     */
    void removeAgentViolation(  String agentNum);

    /**
     * 给在线班长坐席推送消息
     *
     * @param code    消息号
     * @param obj     消息内容
     * @param orgId 推送单位(单位向上递归，包含自身)
     * @return 返回推送结果
     */
    Boolean agentSendWebSocketToSuperior(String code, Object obj, String orgId);


    /**
     * 坐席
     *
     * @return 数据列表
     */
    List<AgentBean> findAllZx();

    /**
     * 根据ip匹配坐席
     * @param ips ip信息
     * @return 返回坐席信息
     */
    AgentBean findAgentByIp(String[] ips);


    /**
     * 获取 指定单位 坐席
     * @param organizationId 坐席类型
     * @return 指定坐席类型的在线坐席列表
     */
    List<AgentBean> findAgentByOrganizationId( String organizationId );


    /**
     * 获取 各个机构坐席信息
     * 传入坐席类型为空，返回所有坐席
     *
     * @return 指定坐席类型的在线坐席列表
     */
    List<OrganizationAgentBean> findAgentByOrganizationNature(  Integer nature);

}
