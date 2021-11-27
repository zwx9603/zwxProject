package com.dscomm.iecs.agent.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.agent.enums.*;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.typebean.*;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.DataFilterService;
import com.dscomm.iecs.agent.service.UserCacheService;
import com.dscomm.iecs.agent.utils.AgentTransformUtil;
import com.dscomm.iecs.base.enums.BasicEnumNumberBean;
import com.dscomm.iecs.base.enums.BasicEnumNumberUtils;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.SafetyRedLineUtils;
import com.dscomm.iecs.basedata.dal.po.AgentEntity;
import com.dscomm.iecs.basedata.dal.repository.AgentRepository;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.dscomm.iecs.agent.enums.RoleEnum.AGENT_PERSONROLE_CALLTAKING;
import static com.dscomm.iecs.agent.enums.RoleEnum.AGENT_PERSONROLE_CALLTAKING_DISPATCH;
import static com.dscomm.iecs.agent.enums.RoleEnum.AGENT_PERSONROLE_DISPATCH;

/**
 * 描述:座席话务状态service服务实现
 *
 * @author ZhaiYanTao
 * Date Time 2019/8/12 10:23
 */

@Component
public class AgentServiceImpl implements AgentService {
    private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);
    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;
    private AgentCacheService agentCacheService;
    private NotifyActionService notifyActionService ;
    private AuditLogService auditLogService;
    private LogService logService;
    private UserCacheService userCacheService;
    private SessionDataStore sessionDataStore;
    private SubAuditService subAuditService;
    private OrganizationService organizationService;
    private DataFilterService dataFilterService;
    private SystemConfigurationService systemConfigurationService;
    private AgentRepository agentRepository;
    private ServletService servletService;

    private Environment env ;
    private Boolean whetherAgent;

    @Autowired
    public AgentServiceImpl( Environment env, AgentCacheService agentCacheService, NotifyActionService notifyActionService ,
                            AuditLogService auditLogService, AgentRepository agentRepository,
                            LogService logService, UserCacheService userCacheService,
                            SessionDataStore sessionDataStore, SubAuditService subAuditService,
                            OrganizationService organizationService, DataFilterService dataFilterService,
                            SystemConfigurationService systemConfigurationService , ServletService servletService) {

        this.agentCacheService = agentCacheService;
        this.notifyActionService = notifyActionService;
        this.auditLogService = auditLogService;
        this.logService = logService;
        this.userCacheService = userCacheService;
        this.sessionDataStore = sessionDataStore;
        this.subAuditService = subAuditService;
        this.organizationService = organizationService;
        this.dataFilterService = dataFilterService;
        this.systemConfigurationService = systemConfigurationService;
        this.agentRepository = agentRepository;
        this.servletService = servletService;

        this.env = env;
        whetherAgent = Boolean.parseBoolean(env.getProperty("whetherAgent"));

    }

    /**
     * {@inheritDoc}
     *
     * @see AgentService#findAgent( String )
     */
    @Transactional(readOnly = true)
    @Override
    public AgentBean findAgent(String agentNumber ) {
        try {
            logService.infoLog(logger, "service", "findAgentByAgentNumber", "service is started...");
            Long logStart = System.currentTimeMillis();

            AgentBean agentBean = agentCacheService.findAgentByAgentNumber( agentNumber ) ;
            AgentEntity agent = agentRepository.findAgentByAgentNumber( agentNumber ) ;

            if ( agentBean == null    ) {
                agentBean= AgentTransformUtil.transform(  agent    );
            }else{
                agentBean= AgentTransformUtil.transform(  agent , agentBean    );
            }

            if (null != agentBean) {
                Map<String , OrganizationBean > organizationBeanMap = organizationService.findOrganizationAll();
                agentBean.setOrder( agentBean.getAgentNumber() );
                OrganizationBean organizationBean = organizationBeanMap.get( agentBean.getOrganizationCode() ) ;
                if( organizationBean != null ){
                    agentBean.setOrganizationName( organizationBean.getOrganizationName() );
                    agentBean.setOrganizationOrderNum( organizationBean.getOrganizationOrderNum() == null ? 999 : organizationBean.getOrganizationOrderNum() );
                }
            }

            if (null != agentBean) {

                if ("cad".equals(cacheKeyPrefix)) {
//                    if (null != agentBean.getAgentState() && 1 != agentBean.getAgentState().getCode()) {
                    if (null != agentBean.getPersonBean() ) {
                        agentBean.setOnlineSign(1);// 在线用1表示
                    } else {
                        agentBean.setOnlineSign(0);// 不在线用0表示
                    }
                }
                if ("mdc".equals(cacheKeyPrefix)) {
//                    if (null != agentBean.getAgentPhoneState() && 1 != agentBean.getAgentPhoneState().getCode()) {
                    if (null != agentBean.getPersonBean() ) {
                        agentBean.setOnlineSign(1);// 在线用1表示
                    } else {
                        agentBean.setOnlineSign(0);// 不在线用0表示
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAgentByAgentNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return agentBean ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllAgent", "find all agent fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see AgentService#findAllAgent()
     */
    @Transactional(readOnly = true)
    @Override
    public List<AgentBean> findAllAgent( ) {
        try {
            logService.infoLog(logger, "service", "findAllAgent", "service is started...");
            Long logStart = System.currentTimeMillis();

            Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);

            if (null == agentBeanMap) {
                logService.infoLog(logger, "service", "findAllAgent", "cache agentBeanMap is null.");
                agentBeanMap = new HashMap<>();
            }
            List<AgentBean> agentBeanList = new ArrayList<>(agentBeanMap.values());
            for (AgentBean agentBean : agentBeanList) {
                if (null != agentBean) {
                    agentBean.setOrder(agentBean.getAgentNumber());
                    if ("cad".equals(cacheKeyPrefix)) {
//                        if (null != agentBean.getAgentState()
//                                &&   agentBean.getAgentState().getCode( ) > AgentStateEnum.AGENT_STATE_OFFLINE.getCode() ) {
                        if (null != agentBean.getPersonBean() ) {
                            agentBean.setOnlineSign(1);// 在线用1表示
                        } else {
                            agentBean.setOnlineSign(0);// 不在线用0表示
                        }
                    }
                    if ("mdc".equals(cacheKeyPrefix)) {
//                        if (null != agentBean.getAgentPhoneState()
//                                &&   agentBean.getAgentPhoneState().getCode() > AgentStateEnum.AGENT_STATE_OFFLINE.getCode()   ) {
                        if (null != agentBean.getPersonBean() ) {
                            agentBean.setOnlineSign(1);// 在线用1表示
                        } else {
                            agentBean.setOnlineSign(0);// 不在线用0表示
                        }
                    }
                }
            }

            agentBeanList.sort(Comparator.comparing(AgentBean::getOnlineSign).thenComparing(AgentBean::getOrder));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllAgent", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return agentBeanList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllAgent", "find all agent fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see AgentService#findAllOnlineAgentByAuthorization()
     */
    @Transactional(  readOnly =  true )
    @Override
    public List<AgentBean> findAllAgentByAuthorization() {
        try {
            long start = System.currentTimeMillis();
            List<AgentBean> list = new ArrayList<>();
            UserInfo userInfo = getUserInfo();
            Map<String, OrganizationBean> idOrgMap = organizationService.findOrganizationAllAuthorization(userInfo.getOrgId(), null);
            Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);

            List<String> orgIds = new ArrayList<>(idOrgMap.keySet());
            if (null == agentBeanMap) {
                logService.infoLog(logger, "service", "findAllAgent", "cache agentBeanMap is null.");
                agentBeanMap = new HashMap<>();
            }
            List<AgentBean> agentBeanList = new ArrayList<>(agentBeanMap.values());
            if (orgIds != null && orgIds.size() > 0) {
                agentBeanList.forEach(agentBean -> {
                    if ( 1 == agentBean.getOnlineSign() ) {
                        if (orgIds.contains(agentBean.getPersonBean().getPersonOrgId())) {
                            list.add(agentBean);
                        }
                    }else{
                        if( orgIds.contains( agentBean.getOrganizationCode() )){
                            list.add(agentBean);
                        }
                    }
                });
            }
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllAgentByAuthorization", String.format("end findAllAgentByAuthorization,totalTime:%sms", end - start));
            return list;
        }
//        catch (UserInterfaceException ex) {
//            logService.erorLog(logger, "service", "findAllOnlineAgentByAuthorization", "find all online agent by authorizate fail", ex);
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllAgentByAuthorization", "find all online agent by authorizate fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see AgentService#findAllOnlineAgentByAuthorization()
     */
    @Transactional(  readOnly =  true )
    @Override
    public List<AgentBean> findAllOnlineAgentByAuthorization() {
        try {
            long start = System.currentTimeMillis();
            List<AgentBean> list = new ArrayList<>();
            UserInfo userInfo = getUserInfo();
            Map<String, OrganizationBean> idOrgMap = organizationService.findOrganizationAllAuthorization(userInfo.getOrgId(), null);
            Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);

            List<String> orgIds = new ArrayList<>(idOrgMap.keySet());
            if (null == agentBeanMap) {
                logService.infoLog(logger, "service", "findAllAgent", "cache agentBeanMap is null.");
                agentBeanMap = new HashMap<>();
            }
            List<AgentBean> agentBeanList = new ArrayList<>(agentBeanMap.values());
            if (orgIds != null && orgIds.size() > 0) {
                agentBeanList.forEach(agentBean -> {
                    if ( 1 == agentBean.getOnlineSign() ) {
                        if (orgIds.contains(agentBean.getPersonBean().getPersonOrgId())) {
                            list.add(agentBean);
                        }
                    }
                });
            }
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOnlineAgentByAuthorization", String.format("end findAllAgentByAuthorization,totalTime:%sms", end - start));
            return list;
        }

        catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOnlineAgentByAuthorization", "find all online agent by authorizate fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findOnlineAgentByAgentType(String)
     */
    @Transactional(readOnly = true)
    @Override
    public  List<AgentBean> findOnlineAgentByAgentType(String agentType ) {
        try {
            logService.infoLog(logger, "service", "findOnlineAgentByAgentType", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<AgentBean> resultList = new ArrayList<>();

            List<AgentBean> agentBOList = findAllAgent();

            //（暂定:1返回接警类3个，2返回处警类3个，3返回高级2个；不传返回所有）
            if (null == agentType) {
                // 传入坐席类型为空，返回所有在线坐席
                if (null != agentBOList && agentBOList.size() > 0) {
                    for (AgentBean agentBO : agentBOList) {
                        if (null != agentBO && null != agentBO.getAgentState() && agentBO.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()) {
                            resultList.add(agentBO);
                        }
                    }
                }
            } else {
                Integer agentTypeNum = Integer.parseInt( agentType ) ;

                if (!(1 <= agentTypeNum && agentTypeNum <= 3)) {
                    logService.infoLog(logger, "service", "findOnlineAgentByAgentType", String.format("agentType[%s] is not in the correct scope.", agentType));
//                    throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.ENUMCODE_AGAINSTRULE_ERROR);
                }
                List<Integer> personRoleCodeList = new ArrayList<>();
                if (1 == agentTypeNum) {
//                    personRoleCodeList.add(10);// 接警班长
//                    personRoleCodeList.add(5);// 接警员
//                    personRoleCodeList.add(9);// 技防接警员
                    personRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode());// 接警班长
                    personRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode());// 接警员
                    personRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_INTELLIGENT.getCode());// 技防接警员
                }
                if (2 == agentTypeNum) {
//                    personRoleCodeList.add(11);// 处警班长
//                    personRoleCodeList.add(6);// 处警员
//                    personRoleCodeList.add(7);// 接处合一
                    personRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode());// 处警班长
                    personRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode());// 处警员
                    personRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_CALLTAKING_DISPATCH.getCode());// 接处合一
                }
                if (3 == agentTypeNum) {
//                    personRoleCodeList.add(8);// 高级班长
//                    personRoleCodeList.add(12);// 高级处警员
                    personRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_ALL_SUPERVISOR.getCode());// 高级班长
                    personRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_SENIOR_DISPATCH.getCode());// 高级处警员
                }
                if (null != agentBOList && agentBOList.size() > 0) {
                    for (AgentBean agentBO : agentBOList) {
                        if (null != agentBO && null != agentBO.getAgentState() && agentBO.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
                                && null != agentBO.getPersonBean() && null != agentBO.getPersonBean().getPersonRole() ) {
                            List<Integer> roleCodeList = getPersonRoleCodeList(agentBO.getPersonBean().getPersonRole());
                            personRoleCodeList.retainAll(roleCodeList);
                            if (personRoleCodeList.size() >= 1){
                                resultList.add(agentBO);
                            }
                        }
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOnlineAgentByAgentType", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return resultList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOnlineAgentByAgentType", "find onlineAgent by agentType fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see AgentService#findOnlineAgentByAgentType(List<String>)
     */
    @Transactional(readOnly = true)
    @Override
    public List<AgentBean> findOnlineAgentByAgentType(List<String> agentTypes) {
        try {
            logService.infoLog(logger, "service", "findOnlineAgentByAgentType", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<AgentBean> resultList = new ArrayList<>();

            List<AgentBean> agentBeanList = findAllAgent();

            if (  agentTypes == null || agentTypes.isEmpty() || agentTypes.size() <= 0 ) {
                // 传入坐席类型为空，返回所有在线坐席
                if (null != agentBeanList && agentBeanList.size() > 0) {
                    for (AgentBean agentBean : agentBeanList) {
//                        if (null != agentBean && null != agentBean.getAgentState()
//                              && agentBean.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()) {
                        if (null != agentBean && agentBean.getPersonBean() != null ) {
                                resultList.add(agentBean);
                        }
                    }
                }
            } else {
                if (null != agentBeanList && agentBeanList.size() > 0) {
                    for (AgentBean agentBean : agentBeanList) {
//                        if (null != agentBean && null != agentBean.getAgentState() &&
//                                agentBean.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
//                                && agentBean.getAgentType() != null && agentTypes.contains(agentBean.getAgentType())) {
                        if (null != agentBean && agentBean.getPersonBean() != null
                                    && agentBean.getAgentType() != null && agentTypes.contains(agentBean.getAgentType())) {
                            resultList.add(agentBean);
                        }
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOnlineAgentByAgentType", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return resultList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOnlineAgentByAgentType", "find onlineAgent by agentType fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AgentService#findOnlineDispatchAgentByOrgId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<AgentBean> findOnlineDispatchAgentByOrgId(String orgId) {
        try {
            logService.infoLog(logger, "service", "findOnlineDispatchAgentByOrgCode", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<AgentBean> agentBeanList = findAllAgent();
            List<AgentBean> onlineDispatchAgentList = new ArrayList<>();

            List<Integer> roleList = Arrays.asList(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode(), RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode());
            for (AgentBean agent : agentBeanList) {

                if (agent.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
                        && null != agent.getPersonBean() && null != agent.getPersonBean().getPersonRole()
                        && agent.getPersonBean().getPersonOrgId().equals(orgId) ) {
                    Integer personRoleNum = roleList.size();
                    List<Integer> roleCodeList = getPersonRoleCodeList(agent.getPersonBean().getPersonRole());
                    roleList.removeAll(roleCodeList);
                    if (roleList.size() < personRoleNum){
                        onlineDispatchAgentList.add(agent);
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOnlineDispatchAgentByOrgCode", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return onlineDispatchAgentList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOnlineDispatchAgentByOrgCode", "get online dispatch agent fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AGENT_CACHE_GET_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #getOnlinePersonAccountByRoleCodes(Integer...)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<String> getOnlinePersonAccountByRoleCodes(Integer... roleCodes) {
        try {
            logService.infoLog(logger, "service", "getOnlinePersonAccountByRoleCodes", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<AgentBean> agentBeanList = findAllAgent();

            Set<String> accountSet = new HashSet<>();

            if (null == roleCodes || roleCodes.length == 0) {
                for (AgentBean agent : agentBeanList) {
                    if (agent.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
                            && null != agent.getPersonBean() && null != agent.getPersonBean().getPersonRole()) {
                        accountSet.add(agent.getPersonBean().getAccount());
                    }
                }
            } else {
                List<Integer> roleList = Arrays.asList(roleCodes);
                for (AgentBean agent : agentBeanList) {
                    if (agent.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
                            && null != agent.getPersonBean() && null != agent.getPersonBean().getPersonRole()) {

                        Integer personRoleNum = roleList.size();
                        List<Integer> roleCodeList = getPersonRoleCodeList(agent.getPersonBean().getPersonRole());
                        roleList.removeAll(roleCodeList);
                        if (roleList.size() < personRoleNum){
                            accountSet.add(agent.getPersonBean().getAccount());
                        }

                    }
                }
            }

            List<String> accountList = new ArrayList<>(accountSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getOnlinePersonAccountByRoleCodes", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return accountList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getOnlinePersonAccountByRoleCodes", "get online person account by roleCodes fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_ONLINEPERSONACCOUNT_FAIL);
        }
    }

    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<AgentBean> findOnlineAgentByRoleCodes(Integer... roleCodes) {
        try {
            List<AgentBean> agents = new ArrayList<>();
            Map<String, AgentBean> cache = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if (cache == null) {
                cache = new HashMap<>();
            }
            List<String> roles = new ArrayList<>();
            for (Integer code : roleCodes) {
                roles.add(code.toString());
            }
            if (roles.size() > 0) {
                for (String key : cache.keySet()) {
                    AgentBean agentBean = cache.get(key);
                    if (agentBean.getAgentState() != null
//                            && agentBean.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
                            &&  agentBean.getPersonBean() != null
                            && agentBean.getPersonBean().getPersonRole() != null) {
                        Integer personRoleNum = roles.size();
                        List<Integer> roleCodeList = getPersonRoleCodeList(agentBean.getPersonBean().getPersonRole());
                        roles.removeAll(roleCodeList);
                        if (roles.size() < personRoleNum){
                            agents.add(agentBean);
                        }
                    }
                }
            } else {
                agents.addAll(cache.values());
            }
            return agents;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOnlineAgentByRoleCodes", "find onlinagents by roleCodes fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }

    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<AgentBean> findAgentByRoleCodes(Integer... roleCodes) {
        try {
            List<AgentBean> agents = new ArrayList<>();
            Map<String, AgentBean> cache = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if (cache == null) {
                cache = new HashMap<>();
            }
            List<String> roles = new ArrayList<>();
            for (Integer code : roleCodes) {
                roles.add(code.toString());
            }
            if (roles.size() > 0) {
                for (String key : cache.keySet()) {
                    AgentBean agentBean = cache.get(key);
                    if (agentBean.getAgentState() != null &&
                            agentBean.getPersonBean() != null && agentBean.getPersonBean().getPersonRole() != null ) {
                        Integer personRoleNum = roles.size();
                        List<Integer> roleCodeList = getPersonRoleCodeList(agentBean.getPersonBean().getPersonRole());
                        roles.removeAll(roleCodeList);
                        if (roles.size() < personRoleNum){
                            agents.add(agentBean);
                        }
                    }
                }
            } else {
                agents.addAll(cache.values());
            }
            return agents;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAgentByRoleCodes", "find agents by roleCodes fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AgentService#addMonitorRoleCodeByRequestPersonRoleCode(List, String)
     */
    @Override
    public void addMonitorRoleCodeByRequestPersonRoleCode(List<Integer> monitorRoleCodeList, String requestPersonRoleCode) {
        if (!StringUtils.isBlank(requestPersonRoleCode)) {
            // 接警员申请的，接警班长可审核
            if (requestPersonRoleCode.equals(String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode()))) {
                monitorRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode());
            }
            // 处警员、接处警员申请的，处警班长可审核
            if (requestPersonRoleCode.equals(String.valueOf(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode()))
                    || requestPersonRoleCode.equals(String.valueOf(AGENT_PERSONROLE_CALLTAKING_DISPATCH.getCode()))) {
                monitorRoleCodeList.add(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode());
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AgentService#changeAgentState(String, Integer, String)
     */
    @Override
    public AgentBean changeAgentState(String agentNum, Integer agentState, String phone) {
        UserInfo userInfo = getUserInfo();
        if (StringUtils.isBlank(agentNum) || null == agentState) {
            logService.infoLog(logger, "service", "changeAgentState", "agentNum or agentState is blank.");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "changeAgentState", "service is started...");
            Long logStart = System.currentTimeMillis();

            // 验证状态值，屏蔽 0"未登录"
            if (!(1 <= agentState && agentState <= 7)) {
                logService.infoLog(logger, "service", "changeAgentState", String.format("cad agentState[%s] is not in the correct scope.", agentState));
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.ENUMCODE_AGAINSTRULE_ERROR);
            }

            logService.infoLog(logger, "cache service", "findAllAgentCache(cacheKeyPrefix)", "cache service is started...");
            Long start = System.currentTimeMillis();
            Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "cache service", "findAllAgentCache(cacheKeyPrefix)", String.format("cache service is finished,execute time is :%sms", end - start));

            if (!agentBeanMap.containsKey(agentNum)) {
                logService.infoLog(logger, "service", "changeAgentState", String.format("can not find the cad cache agentBean info by agentNum[%s]", SafetyRedLineUtils.transform(agentNum)));
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
            }
            AgentBean agentBean = agentBeanMap.get(agentNum);
            BasicEnumNumberBean rawAgentStateEnum = agentBean.getAgentState();

            BasicEnumNumberBean agentStateEnum = BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentStateEnum.class, agentState);
            agentBean.setAgentState(agentStateEnum);
            if (!StringUtils.isBlank(phone)) {
                agentBean.setPhone(phone);
                userInfo.setDeskphone(phone);
                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);
            }
            //清除违规信息
            agentBean.setViolateInfo(null);
            // 保存信息到缓存
            logService.infoLog(logger, "cache service", "mergeAgentCache put", "cache service is started...");
            start = System.currentTimeMillis();
            agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, true);
            end = System.currentTimeMillis();
            logService.infoLog(logger, "cache service", "mergeAgentCache put", String.format("cache service is finished,execute time is :%sms", end - start));

            // webSocket推送
            agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBean,  agentBean.getPersonBean().getPersonOrgId());



            // 保存审计日志
            String auditLogOperateDetails = String.format("changeAgentState:agent[%s] state[%s] change to [%s]", agentNum, rawAgentStateEnum.getName(), agentStateEnum.getName());
            agentSaveAuditLog(agentBean, auditLogOperateDetails, OperationTypeEnum.OPERATIONTYPE_CHANGEAGENTSTATE, agentBean.getPersonBean().getAccount());

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeAgentState", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return agentBean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeAgentState", String.format("change agent[%s] state to [%s] fail.", agentNum, agentState), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.CHANGE_AGENTSTATE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AgentService#changeAgentOperateState(String, Integer, String)
     */
    @Override
    public AgentBean changeAgentOperateState(String agentNum, Integer agentOperateState, String phone) {
        UserInfo userInfo = getUserInfo();
        if (StringUtils.isBlank(agentNum) || null == agentOperateState) {
            logService.infoLog(logger, "service", "changeAgentOperateState", "agentNum or agentOperateState is blank.");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "changeAgentOperateState", "service is started...");
            Long logStart = System.currentTimeMillis();

            // 验证状态值，屏蔽 1"未登录"，2"登录"
            if (!(3 <= agentOperateState && agentOperateState <= 6)) {
                logService.infoLog(logger, "service", "changeAgentOperateState", String.format("agentOperateState[%s] is not in the correct scope.", agentOperateState));
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.ENUMCODE_AGAINSTRULE_ERROR);
            }

            logService.infoLog(logger, "cache service", "findAllAgentCache(cacheKeyPrefix)", "cache service is started...");
            Long start = System.currentTimeMillis();
            Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "cache service", "findAllAgentCache(cacheKeyPrefix)", String.format("cache service is finished,execute time is :%sms", end - start));

            if (!agentBeanMap.containsKey(agentNum)) {
                logService.infoLog(logger, "service", "changeAgentOperateState", String.format("can not find the cad cache agentBean info by agentNum[%s]", agentNum));
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
            }
            AgentBean agentBean = agentBeanMap.get(agentNum);
            BasicEnumNumberBean rawAgentOperateStateEnum = agentBean.getAgentOperateState();

            BasicEnumNumberBean agentOperateStateEnum = BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentOperateStateEnum.class, agentOperateState);
            agentBean.setAgentOperateState(agentOperateStateEnum);
            if (!StringUtils.isBlank(phone)) {
                agentBean.setPhone(phone);
                userInfo.setDeskphone(phone);
                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);
            }
            //清除违规信息
            agentBean.setViolateInfo(null);
            // 保存信息到缓存
            logService.infoLog(logger, "cache service", "mergeAgentCache put", "cache service is started...");
            start = System.currentTimeMillis();
            agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, true);
            end = System.currentTimeMillis();
            logService.infoLog(logger, "cache service", "mergeAgentCache put", String.format("cache service is finished,execute time is :%sms", end - start));

            // webSocket推送
            agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBean, agentBean.getPersonBean().getPersonOrgId());


            // 保存审计日志
            String auditLogOperateDetails = String.format("changeAgentOperateState:agent[%s] operateState[%s] change to [%s]", agentNum, rawAgentOperateStateEnum.getName(), agentOperateStateEnum.getName());
            agentSaveAuditLog(agentBean, auditLogOperateDetails, OperationTypeEnum.OPERATIONTYPE_CHANGEAGENTSTATE, agentBean.getPersonBean().getAccount());

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeAgentOperateState", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return agentBean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeAgentOperateState", String.format("change agent[%s] operateState to [%s] fail.", agentNum, agentOperateState), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.CHANGE_AGENTSTATE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AgentService#changeAgentPhoneState(String, Integer, String, String, String)
     */
    @Override
    public AgentBean changeAgentPhoneState(String agentNum, Integer agentPhoneState, String phone, String meetingMark, String remotePhone) {

        if (StringUtils.isBlank(agentNum) || null == agentPhoneState) {
            logService.infoLog(logger, "service", "changeAgentPhoneState", "agentNum or agentPhoneState is blank.");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "changeAgentPhoneState", "service is started...");
            Long logStart = System.currentTimeMillis();

            String auditLogOperateDetails = "";
            logService.infoLog(logger, "cache service", "findAllAgentCache(cacheKeyPrefix)", "cache service is started...");
            Long start = System.currentTimeMillis();
            Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "cache service", "findAllAgentCache(cacheKeyPrefix)", String.format("cache service is finished,execute time is :%sms", end - start));
            if (!agentBeanMap.containsKey(agentNum)) {
                logService.infoLog(logger, "service", "changeAgentPhoneState", String.format("can not find the cad cache agentBean info by agentNum[%s]", agentNum));
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
            }
            AgentBean agentBean = agentBeanMap.get(agentNum);
            if (!StringUtils.isBlank(phone)) {
                agentBean.setPhone(phone);
            }
            BasicEnumNumberBean test = agentBean.getAgentPhoneState();
            String ip = agentBean.getIp();

            if ("cad".equals(cacheKeyPrefix)) {
                // 验证状态值
                if (!(0 <= agentPhoneState && agentPhoneState <= 6)) {
                    logService.infoLog(logger, "service", "changeAgentPhoneState", String.format("cad agentPhoneState[%s] is not in the correct scope.", agentPhoneState));
                    throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.ENUMCODE_AGAINSTRULE_ERROR);
                }
                if (!StringUtils.isBlank(phone)) {
                    UserInfo userInfo = getUserInfo();
                    userInfo.setDeskphone(phone);
                    userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);
                }
                BasicEnumNumberBean agentPhoneStateEnum = BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentPhoneStateEnum.class, agentPhoneState);
                agentBean.setAgentPhoneState(agentPhoneStateEnum);

                auditLogOperateDetails = String.format("changeAgentPhoneState:agent[%s] phoneState[%s] change to [%s]", agentNum, test.getName(), agentPhoneStateEnum.getName());
            } else if ("mdc".equals(cacheKeyPrefix)) {
                Integer[] states = {0, 1, 4, 5, 6};
                List<Integer> stateList = Arrays.asList(states);
                if (!stateList.contains(agentPhoneState)) {
                    logService.infoLog(logger, "service", "changeAgentPhoneState", String.format("mdc agentPhoneState[%s] is not in the correct scope.", agentPhoneState));
                    throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.ENUMCODE_AGAINSTRULE_ERROR);
                }
//                BasicEnumBO phontstate=agentBean.getAgentPhoneState();

                BasicEnumNumberBean agentPhoneStateEnum = BasicEnumNumberUtils.getBasicEnumBeanByCode(MdcAgentEnum.class, agentPhoneState);
                agentBean.setAgentPhoneState(agentPhoneStateEnum);
                if (!StringUtils.isBlank(meetingMark)) {
                    agentBean.setMeetingMark(meetingMark);
                }
                if (!StringUtils.isBlank(remotePhone)) {
                    agentBean.setRemotePhone(remotePhone);
                }
                auditLogOperateDetails = String.format("mdc changeAgentPhoneState:mdc agent[%s] phoneState[%s] change to [%s]", agentNum, test.getName(), agentPhoneStateEnum.getName());
            }

            //清除违规信息
            agentBean.setViolateInfo(null);
            // 保存信息到缓存
            logService.infoLog(logger, "cache service", "mergeAgentCache put", "cache service is started...");
            Long cacheStart = System.currentTimeMillis();
            agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, true);
            Long cacheEnd = System.currentTimeMillis();
            logService.infoLog(logger, "cache service", "mergeAgentCache put", String.format("cache service is finished,execute time is :%sms", cacheEnd - cacheStart));

            // webSocket推送
            agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBean, agentBean.getPersonBean().getPersonOrgId());


            // 保存审计日志
            agentSaveAuditLog(agentBean, auditLogOperateDetails, OperationTypeEnum.OPERATIONTYPE_CHANGEAGENTSTATE, agentBean.getPersonBean().getAccount());

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeAgentPhoneState", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return agentBean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeAgentPhoneState", String.format("change agent[%s] phoneState to [%s] fail.", agentNum, agentPhoneState), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.CHANGE_AGENTSTATE_FAIL);
        }

    }

    /**
     * 坐席状态变更保存审计日志
     *
     * @param agentBean              坐席信息
     * @param auditLogOperateDetails 审计日志操作描述
     * @return 保存成功标志
     */
    @Transactional
    protected Boolean agentSaveAuditLog(AgentBean agentBean, String auditLogOperateDetails, OperationTypeEnum operationTypeEnum, String operator) {
        try {
            if ("cad".equals(cacheKeyPrefix)) {

                AgentPersonBean personBean =  agentBean.getPersonBean() ;
                if( personBean == null ){
                    personBean = new AgentPersonBean();
                }

                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(operationTypeEnum.getCode()));
                auditLogSaveInputInfo.setOrganizationId(personBean.getPersonOrgId());
                auditLogSaveInputInfo.setOrganizationName(personBean.getPersonOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(agentBean.getAgentNumber());
                auditLogSaveInputInfo.setAcceptancePersonNumber(operator);
                auditLogSaveInputInfo.setAcceptancePersonName(personBean.getUserName() );
                auditLogSaveInputInfo.setIpAddress(agentBean.getIp());
                auditLogSaveInputInfo.setDesc(auditLogOperateDetails);
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(personBean.getAccount(), personBean.getUserName(), personBean.getPersonOrgId(), personBean.getPersonOrgName(),
                        "Edit", "Ok", auditLogOperateDetails);
            }
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "agentSaveAuditLog", "agent save auditLog fail.", ex);
//            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.SAVE_AUDITLOG_ERROR);
            return false;
        }
    }

    /**
     * 坐席状态变更发送webSocket
     *
     * @param code    websocket消息号
     * @param obj     推送信息
     * @param orgId 推送单位，默认向上递归
     * @return 发送成功标志
     */
    public Boolean agentSendWebSocketToSuperior(String code, Object obj, String orgId) {
        try {
            // 推送给所有的班长席
            List<AgentBean> agentBeanList = findAllAgent();
            List<Integer> supervisors = new ArrayList<>();

            if ("cad".equals(cacheKeyPrefix)) {
                supervisors = this.getAllSupervisorCode();
            }
            if ("mdc".equals(cacheKeyPrefix)) {
                supervisors.add(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode());
            }
            if (StringUtils.isBlank(orgId)) {
                OrganizationBean rootOrg = organizationService.getRootOrg();
                orgId = rootOrg.getId();
            }
            //向上递归获取单位下的坐席
            List<String> tos = dataFilterService.findAllOnlineAccountByCondition(Arrays.asList(orgId), "1", supervisors, null);
//            for (agentBean agent : agentBeanList) {
//                if (agent.getPersonBean() != null && agent.getPersonBean().getPersonRole() != null)
//                    if (supervisors.contains(agent.getPersonBean().getPersonRole().getCode())) {
//                        // 班长席
////                        ReceiverMessageBO receiverMessageBO = new ReceiverMessageBO("user", agent.getPersonBean().getAccount());
////                        receivers.add(receiverMessageBO);
//                        toUsers.add(agent.getPersonBean().getAccount());
//                    }
//            }
            // 发送webSocket
//            webSocketPushService.pushMessage(WebsocketCodeEnum.AGENTSTATECHANGE.name(), agentBean, receivers);
            notifyActionService.pushMessageToUsers(code, obj, tos);
//            System.out.println("----webSocket----发送了webSocket[" + JSON.toJSONString(agentBean) + "]给[" + JSON.toJSONString(receivers) + "].");
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "agentSendWebSocketToSuperior", "agent send webSocket fail.", ex);
//            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.SEND_WEBSOCKET_ERROR);
            return false;
        }
    }


    public UserInfo getUserInfo() {
        try {
            UserInfo userInfo = new UserInfo();
            Map<String, UserInfo> allOnlineUsers = userCacheService.findAllOnlineUserInfoCache(cacheKeyPrefix);
            if (allOnlineUsers != null && allOnlineUsers.size() > 0) {
                String userCode = sessionDataStore.getCurrentUserCode();
                userInfo = allOnlineUsers.get(userCode);
            }
            if (userInfo == null) {
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
            }
//            userInfo.setUserName(i18nService.getMessage(String.format("t_tyqx_yh.mc.%s", userInfo.getUserId())));//用户名称
//            userInfo.setUserName(i18nService.getMessage(String.format("t_tyqx_ry.xm.%s", userInfo.getUserId())));//人员名称
//            userInfo.setOrgName(i18nService.getMessage(String.format("t_tyqx_zzjg.mc.%s", userInfo.getOrgId())));//机构名称
//            userInfo.setOrgName(i18nService.getMessage(String.format("t_tyqx_zzjg.qmc.%s", userInfo.getOrgId())));//机构全称
//            userInfo.setUserRole(i18nService.getMessage(String.format("t_tyqx_xtjs.mc.%s", userInfo.getOrgId())));//角色名称
            return userInfo;
        }
//        catch (UserInterfaceException ex) {
//            logService.erorLog(logger, "service", "getUserInfo", "get userInfo fail", ex);
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "getUserInfo", "get userInfo fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #agentLogin(AgentBean)
     */
    @Override
    public void agentLogin(AgentBean agentBean) {
        try {
            long start = System.currentTimeMillis();

            //保存审计日志
            String auditLogOperateDetails = String.format("agent login :agent[%s] user:[%s] ", agentBean.getAgentNumber(), agentBean.getPersonBean().getAccount());
            Boolean auditLogFlag = agentSaveAuditLog(agentBean, auditLogOperateDetails, OperationTypeEnum.OPERATIONTYPE_LOGIN, agentBean.getPersonBean().getAccount());

            // 更新坐席缓存
            agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, true);
            agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBean, agentBean.getPersonBean().getPersonOrgId() );


            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "agentLogin", String.format("agent login total time:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "agentLogin", String.format("agent login fail,user:%s,agent:%s",
                    agentBean.getPersonBean().getAccount(), agentBean.getAgentNumber()), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AGENT_LOGIN_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAllZx()
     */

    private List<String> findAllAgentNumber() {
        try {
            logService.infoLog(logger, "repository", "findAllZx()", "repository is started...");
            Long start = System.currentTimeMillis();

            List<String> agentNumberList = agentRepository.findAllAgentNumber();

            if( agentNumberList == null ){
                agentNumberList = new ArrayList<>() ;
            }
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAllZx()", String.format("repository is finished,execute time is :%sms", end - start));

            return agentNumberList ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllZx", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }

    @Transactional( readOnly =  true )
    @Override
    public void deleteAgent(   ){

        //获得缓存坐席总数
        Map<String, AgentBean>  cacheAgent = agentCacheService.findAllAgentCache( cacheKeyPrefix )  ;
        List<String> agentNumberList =  findAllAgentNumber()  ;
        List<String> deleteAgentNumberList = new ArrayList<>( cacheAgent.keySet() );
        deleteAgentNumberList.removeAll( agentNumberList ) ;
        if( agentNumberList != null && agentNumberList.size() > 0 ){
            if(deleteAgentNumberList != null &&  deleteAgentNumberList.size() > 0  ){
                //缓存中判断哪个坐席删除
                for( String cacheKey : deleteAgentNumberList  ){
                    AgentBean deleteAgent = cacheAgent.get( cacheKey ) ;
                    // 更新坐席缓存
                    agentCacheService.mergeAgentCache("delete", cacheAgent.get( cacheKey ), cacheKeyPrefix, true);
                    agentSendWebSocketToSuperior(WebsocketCodeEnum.DELETEAGENT.getCode(), deleteAgent , deleteAgent.getOrganizationCode() );
                }
            }
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see #agentLogOut(String, OperationTypeEnum)
     */
    @Override
    public void agentLogOut( String agentNum, OperationTypeEnum type ) {
        try {
            Map<String, AgentBean> agents = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if ( Strings.isNotBlank( agentNum ) && agents != null && agents.size() > 0    ) {
                AgentBean agentBean = agents.get(agentNum);
                if( agentBean != null ){
                    if ("cad".equals(cacheKeyPrefix)) {
                        agentBean.setAgentOperateState(BasicEnumNumberUtils.getBasicEnumBean(AgentOperateStateEnum.class, AgentOperateStateEnum.AGENT_OPERATE_STATE_OFFLINE.getType()));
                        agentBean.setAgentPhoneState(BasicEnumNumberUtils.getBasicEnumBean(AgentPhoneStateEnum.class, AgentPhoneStateEnum.AGENT_PHONE_STATE_INVALID.getType()));
                        agentBean.setAgentState(BasicEnumNumberUtils.getBasicEnumBean(AgentStateEnum.class, AgentStateEnum.AGENT_STATE_OFFLINE.getType()));
                    } else {
                        agentBean.setAgentOperateState(null);
                        agentBean.setAgentPhoneState(BasicEnumNumberUtils.getBasicEnumBean(MdcAgentEnum.class, MdcAgentEnum.MDCAGENT_WORKSTATE_OFFLINE.getType()));
                        agentBean.setAgentState(null);
                    }

                    AgentPersonBean personBean = agentBean.getPersonBean();
                    if (personBean == null) {
                        personBean = new AgentPersonBean();
                    }
                    //保存审计日志
                    String auditLogOperateDetails = String.format("agent logout :agent[%s] user:[%s] cause:%s", agentBean.getAgentNumber(), personBean.getAccount(), type.getType());
                    Boolean auditLogFlag = agentSaveAuditLog(agentBean, auditLogOperateDetails, type, personBean.getAccount());

                    agentBean.setPersonBean(null);
                    agentBean.setAccessBean( null );

                    //设置坐席在线标识
                    agentBean.setOnlineSign( 0 ); // 在线标志  0 不在线 1 在线
                    agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, true);
                    agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBean, personBean.getPersonOrgId());
                }


            }
        }
//        catch (UserInterfaceException ex) {
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "agentLogOut", String.format("agent logout fail,agent:%s", agentNum), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AGENT_LOGOUT_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #initAgentCache()
     */
    @Override
    public Boolean initAgentCache() {
        try {
            logService.infoLog(logger, "service", "initAgentCache", "service is started...");
            Long logStart = System.currentTimeMillis();

            agentCacheService.clearAllAgentCache(cacheKeyPrefix);

            logService.infoLog(logger, "repository", "findAllZx()", "repository is started...");
            Long start = System.currentTimeMillis();
            List<AgentBean> agentBeanList = findAllZx();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAllZx()", String.format("repository is finished,execute time is :%sms", end - start));

            if (null == agentBeanList) {
                agentBeanList = new ArrayList<>();
            }

            if (!StringUtils.isBlank(cacheKeyPrefix) && "cad".equals(cacheKeyPrefix)) {
                for (AgentBean agentBean : agentBeanList) {
                    if (null != agentBean && null != agentBean.getAgentNumber() && !StringUtils.isBlank(agentBean.getIp())) {

                        // AGENT_STATE_OFFLINE(1, "离线")
                        agentBean.setAgentState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentStateEnum.class, AgentStateEnum.AGENT_STATE_OFFLINE.getCode()));
                        // AGENT_OPERATE_STATE_OFFLINE(1, "未登录")
                        agentBean.setAgentOperateState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentOperateStateEnum.class, AgentOperateStateEnum.AGENT_OPERATE_STATE_OFFLINE.getCode()));
                        // AGENT_CALLTYPE_AUTHENTIC(11, "物理电话")
                        agentBean.setAgentPhoneType(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentEnum.class, AgentEnum.AGENT_CALLTYPE_AUTHENTIC.getCode()));
                        // AGENT_PHONE_STATE_IDLE(2,"空闲")
                        agentBean.setAgentPhoneState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentPhoneStateEnum.class, AgentPhoneStateEnum.AGENT_PHONE_STATE_IDLE.getCode()));

                        agentBean.setOnlineSign( 0 ); // 在线标志  0 不在线 1 在线

                        agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, false );
                    }
                }
            }

            if (!StringUtils.isBlank(cacheKeyPrefix) && "mdc".equals(cacheKeyPrefix)) {
                for (AgentBean agentBean : agentBeanList) {
                    if (null != agentBean && null != agentBean.getAgentNumber() && !StringUtils.isBlank(agentBean.getIcpip())) {

                        // 调度台只有话务状态
                        agentBean.setAgentState(null);
                        agentBean.setAgentOperateState(null);
                        agentBean.setAgentPhoneType(null);
                        // MDCAGENT_WORKSTATE_OFFLINE(1, "离线")
                        agentBean.setAgentPhoneState(BasicEnumNumberUtils.getBasicEnumBeanByCode(MdcAgentEnum.class, MdcAgentEnum.MDCAGENT_WORKSTATE_OFFLINE.getCode()));

                        agentBean.setOnlineSign( 0 ); // 在线标志  0 不在线 1 在线

                        agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, false );
                    }
                }
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "initAgentCache", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "initAgentCache", "query db, init agent cache fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.INIT_AGENTCACHE_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see #applyChangeToSleep(String, String)
     */
    @Transactional
    @Override
    public Boolean applyChangeToSleep(String reasonCode, String reason) {
        long start = System.currentTimeMillis();
        UserInfo userInfo = getUserInfo();
        try {
            Long systemTime = servletService.getSystemTime();
            AgentApplySleepBO bo = new AgentApplySleepBO();
            bo.setAgentNum(userInfo.getAgentNum());
            bo.setOrgCode(userInfo.getOrgCode());
            bo.setOrgName(userInfo.getOrgName());
            bo.setReasonCode(reasonCode);
            bo.setReason(reason);
            bo.setUserAccount(userInfo.getAccount());
            bo.setUserName(userInfo.getUserName());
            bo.setApplayTime(systemTime);
            //保存审计日志
            String desc = String.format("agent:%s account:%s apply to sleep", userInfo.getAgentNum(), userInfo.getAccount());
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_APPLY_SLEEP.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgFullName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getUserName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc(desc);
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgCode(), userInfo.getOrgName(),
                    "Edit", "Ok", desc);

            //推送websocket给班长席
            agentSendWebSocketToSuperior(WebsocketCodeEnum.APPLY_SLEEP.getCode(), bo, userInfo.getOrgCode());
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "applyChangeToSleep", String.format("service is finished,totalTime:%s", end - start));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "applyChangeToSleep", String.format("applay sleep fail,agent:%s,account:%s", userInfo.getAgentNum(), userInfo.getAccount()), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.APPLAY_SLEEP_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #auditSleep(AuditAgentSleepParamBO)
     */
    @Transactional
    @Override
    public Boolean auditSleep(AuditAgentSleepParamBO bo) {
        long start = System.currentTimeMillis();
        UserInfo userInfo = getUserInfo();
        if (bo == null || StringUtils.isBlank(bo.getAgentNum()) || StringUtils.isBlank(bo.getAccount()) || StringUtils.isBlank(bo.getAuditResult())) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        if (!("0".equals(bo.getAuditResult()) || "1".equals(bo.getAuditResult()))) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUDIT_RESULT_ILLEGAL);
        }
        try {
            Long systemTime = servletService.getSystemTime();
            Map<String, AgentBean> agents = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            AgentBean agentBean = agents.get(bo.getAgentNum());
            AuditAgentSleepBO sleepBO = new AuditAgentSleepBO();
            sleepBO.setAgentNum(bo.getAgentNum());
            sleepBO.setAuditResult(bo.getAuditResult());
            sleepBO.setAuditTime(systemTime);
            sleepBO.setSupervisorAccount(userInfo.getAccount());
            sleepBO.setSupervisorAgentNum(userInfo.getAgentNum());
            sleepBO.setSupervisorName(userInfo.getUserName());
            sleepBO.setReasonCode(bo.getReasonCode());
            sleepBO.setReason(bo.getReason());
            //记录审计日志
            String desc = String.format("audit agent sleep,auditorAgent:%s,applyAgent:%s,audit result:%s", userInfo.getAgentNum(), bo.getAgentNum(), bo.getAuditResult());
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_AUDIT_SLEEP.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgFullName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getUserName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc(desc);
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgCode(), userInfo.getOrgName(),
                    "Edit", "Ok", desc);


            //推送websocket
            //推送给班长席
            agentSendWebSocketToSuperior(WebsocketCodeEnum.AUDIT_SLEEP.getCode(), sleepBO, agentBean.getPersonBean().getPersonOrgId());
            //推送给申请者
            notifyActionService.pushMessageToUsers(WebsocketCodeEnum.AUDIT_SLEEP.getCode(), sleepBO, Arrays.asList(bo.getAccount()));
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "auditSleep", String.format("service is finished,totalTime:%s", end - start));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "auditSleep", String.format("audit agent sleep fail," +
                    "apply agent:%s,audit agent:%s,auditResult:%s", bo.getAgentNum(), userInfo.getAgentNum(), bo.getAuditResult()), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUDIT_SLEEP_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #changeSleepState(String, String)
     */
    @Transactional
    @Override
    public Boolean changeSleepState(String operate, String dormancyCode) {
        UserInfo userInfo = getUserInfo();
        if (StringUtils.isBlank(operate)) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        if (!("0".equals(operate) || "1".equals(operate))) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.PARAM_ILLEGAL);
        }
        try {
            Map<String, AgentBean> agents = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if (agents != null && agents.get(userInfo.getAgentNum()) != null) {
                AgentBean agentBean = agents.get(userInfo.getAgentNum());
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                if ("1".equals(operate)) {
                    agentBean.setAgentState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentStateEnum.class, AgentStateEnum.AGENT_STATE_SLEEP.getCode()));
                    agentBean.setAgentOperateState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentOperateStateEnum.class, AgentOperateStateEnum.AGENT_OPERATE_STATE_SLEEP.getCode()));
                    auditLogSaveInputInfo.setDesc(String.format("agent change to sleep,agent:%s,account:%s", userInfo.getAgentNum(), userInfo.getAccount()));
                    auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_SLEEP.getCode()));
//                    auditLogSaveInputInfo.setRemarks(dormancyCode);
                } else if ("0".equals(operate)) {
                    agentBean.setAgentState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentStateEnum.class, AgentStateEnum.AGENT_STATE_BUSY.getCode()));
                    agentBean.setAgentOperateState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentOperateStateEnum.class, AgentOperateStateEnum.AGENT_OPERATE_STATE_IDLE.getCode()));
                    auditLogSaveInputInfo.setDesc(String.format("agent relieve sleep,agent:%s,account:%s", userInfo.getAgentNum(), userInfo.getAccount()));
                    auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UNSLEEP.getCode()));
                }
                agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, true);
                //记录审计日志
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgFullName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getUserName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgCode(), userInfo.getOrgName(),
                        "Edit", "Ok", auditLogSaveInputInfo.getDesc());

                //发送websocket
                agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBean, agentBean.getPersonBean().getPersonOrgId());
                return true;
            } else {
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
            }
        }
//        catch (UserInterfaceException ex) {
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "changeToSleep", String.format("agent change to sleep fail," +
                    "agent:%s,account:%s", userInfo.getAgentNum(), userInfo.getAccount()), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.SAVE_AGENT_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #forceQuit(String, String, String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean forceQuit(String agentNum, String account, String userinfoNum) {
        if ( StringUtils.isBlank(account)) {
            logService.erorLog(logger, "service", "forceQuit", String.format("the account:%s is null",   account), null);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        UserInfo userInfo = getUserInfo();
        try {
            //记录审计日志
            String desc = "" ;
            if( Strings.isNotBlank( agentNum )){
                desc = String.format("monitor(agent:%s,account:%s) force quit agent:%s", userInfo.getAgentNum(), userInfo.getAccount(), agentNum);

            }else{
                desc = String.format("monitor(agent:%s,account:%s) force quit account:%s", userInfo.getAgentNum(), userInfo.getAccount(), account );
            }
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_FORCEDEXIT.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getUserName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc(desc);
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgCode(), userInfo.getOrgName(),
                    "Edit", "Ok", desc);

            Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if (null == agentBeanMap) {
                logService.infoLog(logger, "service", "findAllAgent", "cache agentBeanMap is null.");
                agentBeanMap = new HashMap<>();
            }

            List<AgentBean> agentBeanList = new ArrayList<>(agentBeanMap.values());
            AgentBean superAgent = new AgentBean();
            for (AgentBean agentBean : agentBeanList) {
                if (null != agentBean) {
                    if (agentBean.getAgentNumber().equals(userinfoNum)) {
                        superAgent = agentBean;
                        break;
                    }
                }
            }
            //顺序 坐席 用户 访问信息
            UserInfo logOutInfo = userCacheService.findAllOnlineUserInfoCache(cacheKeyPrefix).get( account );
            //用户登出
            if( logOutInfo != null ){
                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "remove", logOutInfo, true);
            }
            if( Strings.isNotBlank( agentNum ) ){
                //推出坐席信息
                agentLogOut( agentNum , OperationTypeEnum.OPERATIONTYPE_LOGOUT);
            }
            //推送websocket
            notifyActionService.pushMessageToUsers(WebsocketCodeEnum.MONITOR_FORCE_QUIT.getCode(), superAgent , Arrays.asList(account));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "forceQuit", String.format("force quit  fail,agent:%s"), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FORCE_QUIT_FAIL);
        }
    }

    /**
     * 获取所有班长枚举code
     *
     * @return 返回所有班长枚举code
     */
    private List<Integer> getAllSupervisorCode() {
        List<Integer> list = new ArrayList<>() ;
        list.add(  RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ;
        list.add(   RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode() ) ;
        list.add(   RoleEnum.AGENT_PERSONROLE_ALL_SUPERVISOR.getCode()     ) ;
        if( whetherAgent ){
            list.add(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ;
            list.add(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode() ) ;
            list.add(RoleEnum.AGENT_PERSONROLE_CALLTAKING_DISPATCH.getCode() ) ;
        }

        return list;
    }

    /**
     * {@inheritDoc}
     *
     * @see #updateAgentViolation(ViolationBean, String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public void updateAgentViolation(ViolationBean violationBean, String agentNum) {
        try {
            Map<String, AgentBean> agents = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if (agents != null && agents.size() > 0) {
                AgentBean agentBean = agents.get(agentNum);
                if (agentBean != null && violationBean != null) {
                    agentBean.setViolateInfo(violationBean);
                    agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, true);
                    if( agentBean.getPersonBean() != null ){
                        agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBean, agentBean.getPersonBean().getPersonOrgId());
                    }

                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateAgentViolation", String.format("update agent violation info fail,agent:%s,info:%s", agentNum, JSONObject.toJSONString(violationBean)), ex);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #removeAgentViolation(  String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public void removeAgentViolation(  String agentNum) {
        try {
            Map<String, AgentBean> agents = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if (agents != null && agents.size() > 0) {
                AgentBean agentBean = agents.get(agentNum);
                if (agentBean != null ) {
                    agentBean.setViolateInfo(null );
                    agentCacheService.mergeAgentCache("put", agentBean, cacheKeyPrefix, true);
                    if( agentBean.getPersonBean() != null ){
                        agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBean, agentBean.getPersonBean().getPersonOrgId());
                    }

                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeAgentViolation", String.format("update agent violation info fail,agent:%s,info:%s", agentNum, ""), ex);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #findAllZx()
     */
    @Transactional(readOnly = true)
    @Override
    public List<AgentBean> findAllZx() {
        try {
            logService.infoLog(logger, "repository", "findAllZx()", "repository is started...");
            Long start = System.currentTimeMillis();
            List<AgentEntity> zhPos = agentRepository.findAllZx();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAllZx()", String.format("repository is finished,execute time is :%sms", end - start));

            List<AgentBean> beans = new ArrayList<>();

            if (zhPos != null && !zhPos.isEmpty()) {
                Map<String , OrganizationBean > organizationBeanMap = organizationService.findOrganizationAll();
                for (AgentEntity entity : zhPos) {
                    AgentBean bean = AgentTransformUtil.transform(entity    );
                    if (null != bean) {
                        bean.setOrder( bean.getAgentNumber() );
                        OrganizationBean organizationBean = organizationBeanMap.get( bean.getOrganizationCode() ) ;
                        if( organizationBean != null ){
                            bean.setOrganizationName( organizationBean.getOrganizationName() );
                            bean.setOrganizationOrderNum( organizationBean.getOrganizationOrderNum() == null ? 999 : organizationBean.getOrganizationOrderNum() );
                        }
                        beans.add(bean);
                    }
                }
            }

            return beans;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllZx", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see AgentService#findAgentByIp(String[])
     */
    @Transactional(readOnly = true)
    @Override
    public AgentBean findAgentByIp(String[] ips) {

        try {
            logService.infoLog(logger, "service", "findAgentByIp", "service is started...");
            Long logStart = System.currentTimeMillis();
            AgentBean res = new AgentBean();

            AgentEntity agentEntity = agentRepository.findAgentByIp(ips);

            res = AgentTransformUtil.transform(agentEntity   );
            if( res != null ){
                res.setOrder( res.getAgentNumber() );
                Map<String , OrganizationBean > organizationBeanMap = organizationService.findOrganizationAll();
                OrganizationBean organizationBean = organizationBeanMap.get( res.getOrganizationCode() ) ;
                if( organizationBean != null ){
                    res.setOrganizationName( organizationBean.getOrganizationName() );
                    res.setOrganizationOrderNum( organizationBean.getOrganizationOrderNum() == null ? 999 : organizationBean.getOrganizationOrderNum() );
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAgentByIp", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAgentByIp", "find all agent fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see AgentService#findAgentByOrganizationId( String )
     */
    @Transactional(readOnly = true)
    @Override
    public List<AgentBean> findAgentByOrganizationId( String organizationId ) {
        try {
            logService.infoLog(logger, "service", "findOnlineAgentByAgentType", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<AgentBean> resultList = new ArrayList<>();

            List<AgentBean> agentBeanList = findAllAgent();

            if (  Strings.isBlank( organizationId ) ) {
                // 传入坐席类型为空，返回所有在线坐席
                if (null != agentBeanList && agentBeanList.size() > 0) {
                    for (AgentBean agentBean : agentBeanList) {
                        if (null != agentBean  ) {
                            resultList.add(agentBean);
                        }
                    }
                }
            } else {
                if (null != agentBeanList && agentBeanList.size() > 0) {
                    for (AgentBean agentBean : agentBeanList) {
                        if (null != agentBean   && organizationId.equals( agentBean.getOrganizationCode() ) ) {
                            resultList.add(agentBean);
                        }
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOnlineAgentByAgentType", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return resultList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOnlineAgentByAgentType", "find onlineAgent by agentType fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see AgentService#findAgentByOrganizationNature( Integer )
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationAgentBean> findAgentByOrganizationNature(  Integer nature) {
        try {
            logService.infoLog(logger, "service", "findAgentByOrganizationNature", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationAgentBean> resultList = new ArrayList<>();

            List<AgentBean> agentBeanList = findAllAgent();


            UserInfo userInfo = getUserInfo();
            //获得 机构下中队  nature 0 总队 1 支队 2 大队 3救援站（中队）
            Map<String, OrganizationBean> organizationBeanMap = new HashMap<>() ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            if( nature == null ){
                organizationBeanMap = organizationService.findOrganizationAll() ;
            }else{
                List<OrganizationBean> natureOrganizationBeanList = organizationService.findOrganizationNatureAll(userInfo.getOrgId() , nature) ;
                if(natureOrganizationBeanList != null && natureOrganizationBeanList.size() > 0  ){
                    for( OrganizationBean  organizationBean : natureOrganizationBeanList){
                        organizationBeanMap.put( organizationBean.getId() , organizationBean ) ;
                    }
                }
            }

            Map<String, List<AgentBean>> organizationAgentMap = new HashMap<>() ;
            Map<String, List<AgentBean>> onlineOrganizationAgentMap = new HashMap<>() ;

            if (null != agentBeanList && agentBeanList.size() > 0) {
                for (AgentBean agentBean : agentBeanList) {
                    if ( null != agentBean  && agentBean.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
                            && null != agentBean.getPersonBean() ) {

                        String orgId = agentBean.getPersonBean().getPersonOrgId() ;
                        List<AgentBean> organizatioAgentList =   organizationAgentMap.get( orgId  ) ;
                        if( organizatioAgentList == null ){
                            organizatioAgentList = new ArrayList<>( ) ;
                        }
                        organizatioAgentList.add(agentBean ) ;
                        organizationAgentMap.put ( orgId  ,  organizatioAgentList ) ;
                        List<AgentBean>  onlineOrganizatioAgentList  =   onlineOrganizationAgentMap.get( orgId ) ;

                        if( onlineOrganizatioAgentList == null ){
                            onlineOrganizatioAgentList = new ArrayList<>( ) ;
                        }
                        onlineOrganizatioAgentList.add(agentBean ) ;

                        onlineOrganizationAgentMap.put ( orgId ,  onlineOrganizatioAgentList ) ;
                    }
                }
            }
            for( String organizationAgentKey :  organizationAgentMap.keySet()){
                OrganizationBean organizationBean = organizationBeanMap.get( organizationAgentKey ) ;
                if( organizationBean != null ){
                    OrganizationAgentBean organizationAgentBean = new OrganizationAgentBean();
                    organizationAgentBean.setOrganizationCode( organizationBean.getOrganizationCode() );
                    organizationAgentBean.setOrganizationId ( organizationBean.getId() );
                    organizationAgentBean.setOrganizationName( organizationBean.getOrganizationName() );

                    organizationAgentBean.setOrganizationParentCode(organizationBean.getOrganizationParentCode());
                    organizationAgentBean.setOrganizationParentId( organizationBean.getOrganizationParentId() );
                    organizationAgentBean.setOrganizationParentName ( organizationNameMap.get( organizationBean.getOrganizationParentId() ));

                    organizationAgentBean.setContactPerson( organizationBean.getContactPerson()  );
                    organizationAgentBean.setContactPhone( organizationBean.getContactPhone() );
                    organizationAgentBean.setAgentBeanList( organizationAgentMap.get( organizationAgentKey ) );
                    organizationAgentBean.setOnlineAgentBeanList( onlineOrganizationAgentMap.get( organizationAgentKey ) );

                    resultList.add( organizationAgentBean ) ;


                }

            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAgentByOrganizationNature", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return resultList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAgentByOrganizationNature", "find onlineAgent by agentType fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }

    /**将personRole转成int数组*/
    private List<Integer> getPersonRoleCodeList (List<BasicEnumNumberBean> basicEnumNumberBeans){
        List<Integer> codeList = new ArrayList<>();
        if (basicEnumNumberBeans != null && basicEnumNumberBeans.size()>0){
           for (BasicEnumNumberBean basicEnumNumberBean:basicEnumNumberBeans){
               codeList.add(basicEnumNumberBean.getCode());
           }
        }
        return codeList;
    }

}
