package com.dscomm.iecs.agent.service.impl;

import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.DataFilterService;
import com.dscomm.iecs.base.enums.BasicEnumNumberBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:数据分权分域过滤
 *
 * @author YangFuxi
 * Date Time 2020/1/3 13:28
 */
@Component
public class DataFilterServiceImpl implements DataFilterService {
    private static final Logger logger = LoggerFactory.getLogger(DataFilterServiceImpl.class);
    private LogService logService;
    private OrganizationService organizationService;
    private AgentCacheService agentCacheService;
    private SessionDataStore dataStore;
    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;

    @Autowired
    public DataFilterServiceImpl(LogService logService, OrganizationService organizationService, AgentCacheService agentCacheService, SessionDataStore dataStore) {
        this.logService = logService;
        this.organizationService = organizationService;
        this.agentCacheService = agentCacheService;
        this.dataStore = dataStore;
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAllOnlineAgentByAuthorization()
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Map<String, AgentBean> findAllOnlineAgentByAuthorization() {
        try {
            long start = System.currentTimeMillis();
            Map<String, AgentBean> map = new HashMap<>();
            UserInfo userInfo = (UserInfo) dataStore.get().get("userInfo");
            Map<String, OrganizationBean> idOrgMap = organizationService.findOrganizationAllAuthorization(userInfo.getOrgId(), null);
            Map<String, AgentBean> agentCache = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            List<String> orgIds = new ArrayList<>(idOrgMap.keySet());
            filterAgentsByOrgs(map, agentCache, orgIds);
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOnlineAgentByAuthorization", String.format("end findAllOnlineAgentByAuthorization,totalTime:%sms", end - start));
            return map;
        }
//        catch (UserInterfaceException ex) {
//            logService.erorLog(logger, "service", "findAllOnlineAgentByAuthorization", "find all online agent by authorizate fail", ex);
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOnlineAgentByAuthorization", "find all online agent by authorizate fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_AGENT_FAIL);
        }
    }

    /**
     * 私有方法 根据单位过滤在线坐席
     *
     * @param map        目标集合
     * @param agentCache 坐席缓存
     * @param orgIds   机构id集合
     */
    private void filterAgentsByOrgs(Map<String, AgentBean> map, Map<String, AgentBean> agentCache, List<String> orgIds) {
        if (agentCache != null && agentCache.size() > 0) {
            if (orgIds != null && orgIds.size() > 0) {
                agentCache.keySet().forEach(key -> {
                    AgentBean agent = agentCache.get(key);
                    if (agent.getPersonBean() != null && orgIds.contains(agent.getPersonBean().getPersonOrgId())) {
                        agent.setOnlineSign(1);
                        map.put(agent.getAgentNumber(), agent);
                    }
                });
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAllOnlineAccountByCondition(List, String, List, List)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<String> findAllOnlineAccountByCondition(List<String> orgIds, String direction, List<Integer> roles , List<String> incidentTypes) {
        try {
            long start = System.currentTimeMillis();
            List<String> accounts = new ArrayList<>();
            List<String> orgs = new ArrayList<>();
            if (orgIds != null && orgIds.size() > 0) {
                switch (direction) {
                    case "0":
                        orgs.addAll(orgIds);
                        break;
                    case "1":
                        orgs.addAll(organizationService.findParentOrganizationIds(orgIds));
                        break;
                    case "2":
                        orgs.addAll(organizationService.findChildOrganizationIds(orgIds));
                        break;
                    default:
                }
            }
            Map<String, AgentBean> agentCache = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if (agentCache != null && agentCache.size() > 0) {
                agentCache.keySet().forEach(key -> {
                    boolean res = true;
                    AgentBean agent = agentCache.get(key);
                    if (agent == null || agent.getPersonBean() == null) {
                        res = false;
                    }
                    if (res && orgs.size() > 0) {
                        if (!orgs.contains(agent.getPersonBean().getPersonOrgId())) {
                            res = false;
                        }
                    }
                    if (res && roles != null && roles.size() > 0) {
                        if (agent.getPersonBean().getPersonRole() == null) {
                            res = false;
                        }
                        List<Integer> codeList = new ArrayList<>();
                        if (agent.getPersonBean().getPersonRole() != null && agent.getPersonBean().getPersonRole().size()>0){
                            for (BasicEnumNumberBean basicEnumNumberBean:agent.getPersonBean().getPersonRole()){
                                codeList.add(basicEnumNumberBean.getCode());
                            }
                        }
                        roles.retainAll(codeList);
                        if (roles.size() == 0){
                            res = false;
                        }
                    }
                    if (res) {
                        accounts.add(agent.getPersonBean().getAccount());
                    }
                });
            }
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOnlineAccountByCondition", String.format("total time:%sms", end - start));
            return accounts;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOnlineAgentByAuthorizationAndType", "find all online agent by authorizate fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_ONLINEPERSONACCOUNT_FAIL);
        }
    }

}

