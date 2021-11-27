package com.dscomm.iecs.agent.service.distribute;


import com.dscomm.iecs.agent.dal.po.DistributeStrategy.AccountJurisdictionPO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.AgentJurisdictionPO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.DistributeCaseNaturePO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.JurisdictionPO;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.distribute.bean.*;

import java.util.List;

/**
 * 描述:处警分配服务
 *
 * @author YangFuxi
 * Date Time 2019/9/18 19:07
 */
public interface DistributeService {
    /**
     * 获取分配策略
     *
     * @param systemName  用户账号
     * @param agentNum    坐席号
     * @param acceptTypes 警种集合
     * @return 返回分配设置信息
     */
    DistributeModeBean getDistributeMode(String systemName, Integer agentNum, List<String> acceptTypes);

    /**
     * 获取警情分配配置信息
     *
     * @return 返回配置信息
     */
    DistributeConfigBean getDistributeConfig();

    /**
     * 根据坐席号获取处警台接管的单位编号
     *
     * @param agentCode
     * @return 返回单位编号集合
     */
    List<String> findOrgCodesByAgentJurisdictions(Integer agentCode);

    /**
     * 根据账号获取处警台接管的单位编号
     *
     * @param userCode
     * @return 返回单位编号集合
     */
    List<String> findAllOrgCodesByAccountNumJurisdictions(String userCode);

    /**
     * 根据坐席获取事件单编号集合
     *
     * @param userCode         坐席号
     * @param distributeStatus 分配状态
     * @return 返回事件单编号集合
     */
    List<String> findAllIncidentIdFromDistributeRecord(String userCode, Integer distributeStatus);

    /**
     * 查询处警台与处警辖区的绑定关系
     *
     * @return 返回绑定关系集合
     */
    List<AgentJurisdictionPO> queryAllDistrictAgent();

    /**
     * 查询处警员与处警辖区的绑定关系
     *
     * @return 返回绑定关系集合
     */
    List<AccountJurisdictionPO> queryAllDistrictLogin();


    /**
     * 查询所有处警辖区
     *
     * @return 返回处警辖区集合
     */
    List<JurisdictionPO> queryAllJurisdiction();

    /**
     * 查询所有处警辖区
     *
     * @return 返回处警辖区集合
     */
    List<JurisdictionBean> queryAllJurisdictionBO();



    /**
     * 查询所有辖区与接管其的坐席集合
     *
     * @return 所有辖区与接管其的坐席集合
     */
    List<JurisdictionForAgentBean> queryAllJurisdictionForAgent();

    /**
     * 查询当前辖区可绑定的坐席
     *
     * @return 坐席集合
     */
    List<AgentBean> queryAgentBosForJurisdiction(String jurisdictionNum);

    /**
     * 查询所有辖区与接管其的坐席集合
     *
     * @param agentNum        坐席号
     * @param jurisdictionNum 辖区号
     * @return 所有辖区与接管其的坐席集合
     */
    List<JurisdictionForAgentBean> addJurisdictionForAgent(Integer agentNum, String jurisdictionNum);

    /**
     * 查询所有辖区与接管其的坐席集合
     *
     * @param agentNum        坐席号
     * @param jurisdictionNum 辖区号
     * @return 所有辖区与接管其的坐席集合
     */
    List<JurisdictionForAgentBean> deleteJurisdictionForAgent(Integer agentNum, String jurisdictionNum);

    /**
     * 查询处警员与案由的绑定关系
     *
     * @return 返回绑定关系集合
     */
    List<DistributeCaseNaturePO> queryAllCaseNature();


    /**
     * 查询所有的案由坐席绑定关系
     *
     * @return 返回查询信息
     */
    List<CauseFoAgentBean> findAllCauseFoAgent();

    /**
     * 添加案由和坐席的绑定关系
     *
     * @param ayCode
     * @param agentNum
     * @return
     */
    Boolean addAyForAgent(String ayCode, String agentNum);

    /**
     * 移除案由和坐席的绑定关系
     *
     * @param ayCode   案由
     * @param agentNum 坐席号
     * @return 返回操作结果
     */
    Boolean removeAyForAgent(String ayCode, String agentNum);



}
