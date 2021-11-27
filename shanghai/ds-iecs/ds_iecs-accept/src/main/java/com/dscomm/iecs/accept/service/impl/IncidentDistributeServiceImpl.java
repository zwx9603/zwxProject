package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.service.IncidentDistributeService;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.AccountJurisdictionPO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.AgentJurisdictionPO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.DistributeCaseNaturePO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.JurisdictionPO;
import com.dscomm.iecs.agent.dal.repository.JurisdictionRepository;
import com.dscomm.iecs.agent.enums.AgentStateEnum;
import com.dscomm.iecs.agent.enums.RoleEnum;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.distribute.DistributeService;
import com.dscomm.iecs.agent.service.distribute.bean.DistributeConfigBean;
import com.dscomm.iecs.agent.service.distribute.bean.DistributeModeBean;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述:警情分配服务
 *
 * @author YangFuxi
 * Date Time 2019/9/20 8:52
 */
@Component
public class IncidentDistributeServiceImpl implements IncidentDistributeService {

    private static final Logger logger = LoggerFactory.getLogger(IncidentDistributeServiceImpl.class);

    private LogService logService;
    private AgentService agentService ;
    private NotifyActionService notifyActionService ;
    private DistributeService distributeService;
    private DictionaryService dictionaryService ;
    private JurisdictionRepository jurisdictionRepository ;
    private OrganizationService organizationService ;



    @Autowired
    public IncidentDistributeServiceImpl(LogService logService , AgentService agentService ,
                                         NotifyActionService notifyActionService , DistributeService distributeService ,
                                         DictionaryService dictionaryService , JurisdictionRepository jurisdictionRepository ,
                                         OrganizationService organizationService

    ) {

        this.logService = logService;
        this.agentService = agentService ;
        this.notifyActionService = notifyActionService ;
        this.distributeService = distributeService ;
        this.dictionaryService = dictionaryService ;
        this.jurisdictionRepository = jurisdictionRepository ;
        this.organizationService = organizationService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see #distribute(IncidentBean)
     */
    @Transactional(  readOnly =  true )
    @Override
    public void distribute( IncidentBean incidentBean) {
        try {
            List<AgentBean> allAgent = agentService.findAllAgent();
            List<ReceiverMessageBean> receivers = new ArrayList<>();

            if (allAgent != null && allAgent.size() > 0) {
                allAgent.forEach(agent -> {
                    try {
                        if (agent.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
                                && agent.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_DISPATCH)) {
                            Boolean res = judgeDistribute( incidentBean , agent);
                            if (res) {
                                ReceiverMessageBean receiver = new ReceiverMessageBean("user", agent.getPersonBean().getAccount());
                                receivers.add(receiver);
                            }
                        }
                    } catch (Exception ex) {
                        logService.erorLog(logger, "service", "distribute", String.format("distribute incident fail at agent:%s,incidentId:%s",
                                agent.getAgentNumber(), incidentBean.getId()), ex);
                    }
                });
                //websocket推送
                if (!receivers.isEmpty()) {
                    notifyActionService.pushMessage(WebsocketCodeEnum.DISTRIBUTEINCIDENTTODISPATCH.getCode(), incidentBean  , receivers);
                    notifyActionService.pushMessage(WebsocketCodeEnum.SAVE_NEW_INCIDENT.getCode(), incidentBean , receivers);
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "distribute", String.format("distribute incident fail at incidentId:%s", incidentBean.getId()), ex);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #judgeDistribute(IncidentBean, AgentBean )
     */
    @Transactional(  readOnly =  true )
    @Override
    public Boolean judgeDistribute(IncidentBean incidentBean, AgentBean agent ) {

        try {
            if ( agent  == null || incidentBean == null) {
                return false;
            }

            if ( agent.getPersonBean() == null || StringUtils.isBlank(agent.getPersonBean().getAccount()) || agent.getDistribute() == null) {
                return false;
            }
            DistributeModeBean distributeMode = agent.getDistribute();

            Boolean alarmTypeFilter = distributeMode.getAlarmTypeFilteration();
            List<String> alarmTypes = distributeMode.getAlarmTypes();
            String model = distributeMode.getDistributeMode();
            List<String> items = distributeMode.getAcceptItems();
            //警种过滤 消防无警种
//            if (alarmTypeFilter) {
//                if (alarmTypes == null || alarmTypes.isEmpty() || !alarmTypes.contains(incidentBean.getAlarmType())) {
//                    return false;
//                }
//            }
//
//            //再处警分配 unDispatchType 未处警类型（1：已受理未处警，2：上级单位派发未处警） 暂时消防无此情况
//            Boolean res = false;
//            if (model.equals("7")) {
//                // 单位信息
//                Map<String, OrganizationBean> codeOrgMap = organizationService.findOrganizationAll();
//                //深圳模式
//                String orgType = codeOrgMap.get(agent.getPersonBean().getPersonOrgId() ).getOrganizationTypeCode();
//                if (orgType.equals("SJ")) {
//                    if ( incidentBean.getRegisterOrganizationId().equals(agent.getPersonBean().getPersonOrgId()) &&
//                            incidentBean.getWhetherImportSign() != null && incidentBean.getWhetherImportSign() == 1) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                } else {
//                    if ( incidentBean.getUnDispatchType() != null && incidentBean.getUnDispatchType() == 2) {
//                        if (incidentBean.getUnDispatchOrderState() != null && incidentBean.getUnDispatchOrderState() < 60 &&
//                                agent.getPersonBean() != null && agent .getPersonBean().getPersonOrgId().equals(incidentBean.getDealIncidentUnitCode())) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    } else {
//                        return false;
//                    }
//                }
//            } else {

                //处警分配

                /**
                 *
                 * 0.接处合一
                 * 1.坐席分配(接管哪几个接警坐席)
                 * 2.工号辖区
                 * 3.坐席辖区
                 * 4.轮询分配
                 * 5.坐席案由
                 *
                 *  备注：所有的都要兼容警种分配
                 */

                if (items == null || items.isEmpty()) {
                    return false;
                }
                Boolean res = false;
                switch (model) {
                    case "0":
                        //接处合一
                        break;
                    case "1":
                        //坐席分配(接管哪几个接警坐席)
                        if (items.contains(agent.getAgentNumber())) {
                            res = true;
                        }
                        break;
                    case "2":
                        //工号辖区
                        if (items.contains(incidentBean.getDistrictCode())) {
                            res = true;
                        }
                        break;
                    case "3":
                        //坐席辖区
                        if (items.contains(incidentBean.getDistrictCode())) {
                            res = true;
                        }
                        break;
                    case "4":
                        //轮询分配
                        break;
                    case "5":
                        //坐席案由
                        if ( items.contains(incidentBean.getIncidentTypeCode() ) ) {
                            res = true;
                        }
                        break;
                    default:
                        break;
                }

                return res;
//            }

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "judgeDistribute", "distribute incident fail", ex);
            throw new AcceptException(AcceptException.AccetpErrors.DISTRIBUTE_FAIL);
        }
    }

    @Transactional(  readOnly =  true )
    @Override
    public String missDispatchCheck(AgentBean agentBO) {
        try {
            DistributeConfigBean distributeConfigBO = distributeService.getDistributeConfig();
            String model = distributeConfigBO.getDistributeMode();
            switch (model) {
                case "1":
                    missDispatchCheckForAgent(agentBO);
                    break;
                case "2":
                    missDispatchCheckForAccountJurisdiction(agentBO);
                    break;
                case "3":
                    missDispatchCheckForAgentJurisdiction(agentBO);
                    break;
                case "5":
                    missDispatchCheckForCaseNature(agentBO);
                    break;
                default:
                    break;
            }
            return model;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheck", "missDispatchCheck fail ", ex);
            throw new AcceptException(AcceptException.AccetpErrors.MISS_DISPATCHCHECK_FAIL);
        }
    }

    @Transactional(  readOnly =  true )
    @Override
    public void missDispatchCheckForMonitor() {
        try {
            DistributeConfigBean distributeConfigBO = distributeService.getDistributeConfig();
            String model = distributeConfigBO.getDistributeMode();
            switch (model) {
                case "1":
                    missDispatchCheckForMonitorForAgent();
                    break;
                case "2":
                    missDispatchCheckForMonitorForAccountJurisdiction();
                    break;
                case "3":
                    missDispatchCheckForMonitorForAgentJurisdiction();
                    break;
                case "5":
                    missDispatchCheckForMonitorForCaseNature();
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheck", "missDispatchCheck fail ", ex);
        }
    }

    /**
     * 接处分开分配策略漏管检查(处警台辖区分配模式)
     *
     * @param currentAgentBO
     */
    public void missDispatchCheckForAgentJurisdiction(AgentBean currentAgentBO) {
        try {
            List<AgentBean> allAgent = agentService.findOnlineAgentByAgentType(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode() + "");
            List<ReceiverMessageBean> receivers = new ArrayList<>();
            receivers.add(new ReceiverMessageBean("user", currentAgentBO.getPersonBean().getAccount()));
            Map<String, List<String>> dealMappingDistrictAgent = new HashMap<>();
            List<AgentJurisdictionPO> agentJurisdictionPOS = distributeService.queryAllDistrictAgent();//查处辖区跟坐席的绑定关系
            if (agentJurisdictionPOS != null) {
                for (AgentJurisdictionPO agentJurisdictionPO : agentJurisdictionPOS) {
                    Boolean isExist = false;
                    for (AgentBean agentBO : allAgent) {
                        if (agentBO.getAgentNumber().equals(agentJurisdictionPO.getAgentNum())) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        agentJurisdictionPOS.remove(agentJurisdictionPO);//将已经接管辖区的，但是不在线的处警坐席从集合中去除
                    }
                }
            }
            List<JurisdictionPO> jurisdictionPOS = distributeService.queryAllJurisdiction();//获取所有处警辖区
            if (jurisdictionPOS != null) {
                for (JurisdictionPO jurisdictionPO : jurisdictionPOS) {
                    List<String> agentList = new ArrayList<>();
                    if (agentJurisdictionPOS != null) {
                        for (AgentJurisdictionPO agentJurisdictionPO : agentJurisdictionPOS) {
                            if (jurisdictionPO.getId().equals(agentJurisdictionPO.getJurisdictionId())) {
                                agentList.add(String.valueOf(agentJurisdictionPO.getAgentNum()));
                            }
                        }
                    }
                    dealMappingDistrictAgent.put(jurisdictionPO.getId(), agentList);//将每个处警辖区对应所在线的处警坐席添加到map中
                }
            }
            Iterator<Map.Entry<String, List<String>>> entryIterator = dealMappingDistrictAgent.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, List<String>> entry = entryIterator.next();
                if (entry.getValue().size() == 1 && entry.getValue().contains(currentAgentBO.getAgentNumber())) {//如果当前辖区只有一个在线的人结果，报当前辖区没有其他人接管
                    if (!receivers.isEmpty()) {
                        notifyActionService.pushMessage(WebsocketCodeEnum.MISSDISPATCHCHECKRESULT.getCode(), "1111111", receivers);
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheckForAgentJurisdiction", "missDispatchCheckForAgentJurisdiction fail ", ex);
        }
    }

    /**
     * 接处分开分配策略漏管检查(处警台辖区分配模式)
     *
     * @param currentAgentBO
     */
    private void missDispatchCheckForAgent(AgentBean currentAgentBO) {
        try {
            List<AgentBean> dispatchAgent = agentService.findOnlineAgentByAgentType(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode() +"" );
            List<AgentBean> acceptAgent = agentService.findOnlineAgentByAgentType(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() +""  );
            List<ReceiverMessageBean> receivers = new ArrayList<>();
            receivers.add(new ReceiverMessageBean("user", currentAgentBO.getPersonBean().getAccount()));
            Map<String, List<AgentBean>> dealMappingDistrictAgent = new HashMap<>();
            for (AgentBean agentBO : dispatchAgent) {
                dealMappingDistrictAgent.put(agentBO.getAgentNumber(), acceptAgent);
            }

            Iterator<Map.Entry<String, List<AgentBean>>> entryIterator = dealMappingDistrictAgent.entrySet().iterator();
            for (AgentBean agentBO : acceptAgent) {
                List<String> managerList = new ArrayList<>();
                managerList.clear();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, List<AgentBean>> entry = entryIterator.next();
                    if (entry.getValue().contains(agentBO)) {//如果当前辖区只有一个在线的人结果，报当前辖区没有其他人接管
                        managerList.add(entry.getKey());
                    }
                }
                if (managerList.contains(currentAgentBO.getAgentNumber()) && managerList.size() == 1) {
                    if (!receivers.isEmpty()) {
                        notifyActionService.pushMessage(WebsocketCodeEnum.MISSDISPATCHCHECKRESULT.getCode(), "1111111", receivers);
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheckForAgent", "missDispatchCheckForAgent fail ", ex);
        }
    }

    /**
     * 接处分开分配策略漏管检查(处警员辖区分配模式)
     *
     * @param currentAgentBO
     */
    private void missDispatchCheckForAccountJurisdiction(AgentBean currentAgentBO) {
        try {
            List<AgentBean> allAgent = agentService.findOnlineAgentByAgentType(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode() +"" );
            List<ReceiverMessageBean> receivers = new ArrayList<>();
            receivers.add(new ReceiverMessageBean("user", currentAgentBO.getPersonBean().getAccount()));
            Map<String, List<String>> dealMappingDistrictAgent = new HashMap<>();
            List<AccountJurisdictionPO> accountJurisdictionPOS = distributeService.queryAllDistrictLogin();//查处辖区跟处警员的绑定关系
            if (accountJurisdictionPOS != null) {
                for (AccountJurisdictionPO accountJurisdictionPO : accountJurisdictionPOS) {
                    Boolean isExist = false;
                    for (AgentBean agentBO : allAgent) {
                        if (agentBO.getPersonBean().getAccount().equals(accountJurisdictionPO.getAccountNum())) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        accountJurisdictionPOS.remove(accountJurisdictionPO);//将已经接管辖区的，但是不在线的处警员从集合中去除
                    }
                }
            }
            List<JurisdictionPO> jurisdictionPOS = distributeService.queryAllJurisdiction();//获取所有处警辖区
            if (jurisdictionPOS != null) {
                for (JurisdictionPO jurisdictionPO : jurisdictionPOS) {
                    List<String> accountList = new ArrayList<>();
                    if (accountJurisdictionPOS != null) {
                        for (AccountJurisdictionPO accountJurisdictionPO : accountJurisdictionPOS) {
                            if (jurisdictionPO.getId().equals(accountJurisdictionPO.getJurisdictionId())) {
                                accountList.add(String.valueOf(accountJurisdictionPO.getAccountNum()));
                            }
                        }
                    }
                    dealMappingDistrictAgent.put(jurisdictionPO.getId(), accountList);//将每个处警辖区对应所在线的处警坐席添加到map中
                }
            }
            Iterator<Map.Entry<String, List<String>>> entryIterator = dealMappingDistrictAgent.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, List<String>> entry = entryIterator.next();
                if (entry.getValue().size() == 1 && entry.getValue().contains(currentAgentBO.getPersonBean().getAccount())) {//如果当前辖区只有一个在线的人结果，报当前辖区没有其他人接管
                    if (!receivers.isEmpty()) {
                        notifyActionService.pushMessage(WebsocketCodeEnum.MISSDISPATCHCHECKRESULT.getCode(), "1111111", receivers);
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheckForAccountJurisdiction", "missDispatchCheckForAccountJurisdiction fail ", ex);
        }
    }

    /**
     * 接处分开分配策略漏管检查(处警台案由分配模式)
     *
     * @param currentAgentBO
     */
    public void missDispatchCheckForCaseNature(AgentBean currentAgentBO) {
        try {
            List<AgentBean> allAgent = agentService.findOnlineAgentByAgentType( RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode() + "" );
            List<ReceiverMessageBean> receivers = new ArrayList<>();
            receivers.add(new ReceiverMessageBean("user", currentAgentBO.getPersonBean().getAccount()));
            Map<String, List<String>> dealMappingDistrictAgent = new HashMap<>();
            List<DistributeCaseNaturePO> distributeCaseNaturePOS = distributeService.queryAllCaseNature();//查案由跟处警台的绑定关系
            if (distributeCaseNaturePOS != null) {
                for (DistributeCaseNaturePO distributeCaseNaturePO : distributeCaseNaturePOS) {
                    Boolean isExist = false;
                    for (AgentBean agentBO : allAgent) {
                        if (agentBO.getAgentNumber().equals(distributeCaseNaturePO.getAgentNum())) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        distributeCaseNaturePOS.remove(distributeCaseNaturePO);//将已经接管案由的，但是不在线的处警台从集合中去除
                    }
                }
            }
//            List<CauseActionPO> causeActionPOS = distributeService.findLevelOneCaseNature();//获取所有一级案由
            List<DictionaryBean> causeActionPOS = dictionaryService.findGridDictionary( "AJLX",false);//获取所有一级案由
            if (causeActionPOS != null) {
                for (DictionaryBean causeActionPO : causeActionPOS) {
                    List<String> agentList = new ArrayList<>();
                    if (distributeCaseNaturePOS != null) {
                        for (DistributeCaseNaturePO accountJurisdictionPO : distributeCaseNaturePOS) {
                            if (causeActionPO.getId().equals(accountJurisdictionPO.getCaseNatureId())) {
                                agentList.add(String.valueOf(accountJurisdictionPO.getAgentNum()));
                            }
                        }
                    }
                    dealMappingDistrictAgent.put(causeActionPO.getId(), agentList);//将每个处警辖区对应所在线的处警坐席添加到map中
                }
            }
            Iterator<Map.Entry<String, List<String>>> entryIterator = dealMappingDistrictAgent.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, List<String>> entry = entryIterator.next();
                if (entry.getValue().size() == 1 && entry.getValue().contains(currentAgentBO.getAgentNumber())) {//如果当前辖区只有一个在线的人结果，报当前案由没有其他人接管
                    if (!receivers.isEmpty()) {
                        notifyActionService.pushMessage(WebsocketCodeEnum.MISSDISPATCHCHECKRESULT.getCode(), "1111111", receivers);
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheckForCaseNature", "missDispatchCheckForCaseNature fail ", ex);
        }
    }

    /**
     * 接处分开分配策略漏管检查(处警台辖区分配模式)
     */
    public void missDispatchCheckForMonitorForCaseNature() {
        try {
            List<AgentBean> allAgent = agentService.findOnlineAgentByAgentType(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode() + "");
            List<AgentBean> dispatchSupervisiorAgent = agentService.findOnlineAgentByAgentType(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode()+ "");
            List<ReceiverMessageBean> receivers = new ArrayList<>();
            for (AgentBean agentBO : dispatchSupervisiorAgent) {
                ReceiverMessageBean receiver = new ReceiverMessageBean("user", agentBO.getPersonBean().getAccount());
                receivers.add(receiver);
            }
            Map<String, List<String>> dealMappingDistrictAgent = new HashMap<>();
            List<DistributeCaseNaturePO> distributeCaseNaturePOS = distributeService.queryAllCaseNature();//查案由跟处警台的绑定关系
            if (distributeCaseNaturePOS != null) {
                for (DistributeCaseNaturePO distributeCaseNaturePO : distributeCaseNaturePOS) {
                    Boolean isExist = false;
                    for (AgentBean agentBO : allAgent) {
                        if (agentBO.getAgentNumber().equals(distributeCaseNaturePO.getAgentNum())) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        distributeCaseNaturePOS.remove(distributeCaseNaturePO);//将已经接管案由的，但是不在线的处警台从集合中去除
                    }
                }
            }
//            List<CauseActionPO> causeActionPOS = distributeService.findLevelOneCaseNature();//获取所有一级案由
            List<DictionaryBean> causeActionPOS = dictionaryService.findGridDictionary( "AJLX",false);//获取所有一级案由
            if (causeActionPOS != null) {
                for (DictionaryBean causeActionPO : causeActionPOS) {
                    List<String> agentList = new ArrayList<>();
                    if (distributeCaseNaturePOS != null) {
                        for (DistributeCaseNaturePO accountJurisdictionPO : distributeCaseNaturePOS) {
                            if (causeActionPO.getId().equals(accountJurisdictionPO.getCaseNatureId())) {
                                agentList.add(String.valueOf(accountJurisdictionPO.getAgentNum()));
                            }
                        }
                    }
                    dealMappingDistrictAgent.put(causeActionPO.getId(), agentList);//将每个处警辖区对应所在线的处警坐席添加到map中
                }
            }
            Iterator<Map.Entry<String, List<String>>> entryIterator = dealMappingDistrictAgent.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, List<String>> entry = entryIterator.next();
                if (entry.getValue().size() < 1) {//如果当前辖区没有在线的人结果，报当前辖区没有人接管
                    if (!receivers.isEmpty()) {
                        notifyActionService.pushMessage(WebsocketCodeEnum.MISSDISPATCHCHECKFORMONITORRESULT.getCode(), "1111111", receivers);
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheckForMonitorForCaseNature", "missDispatchCheckForMonitorForCaseNature fail ", ex);
        }
    }

    /**
     * 接处分开分配策略漏管检查(处警台辖区分配模式)
     */
    public void missDispatchCheckForMonitorForAgent() {
        try {
            List<String> dispatchSupervisiorAgent = agentService.getOnlinePersonAccountByRoleCodes(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode());
            List<ReceiverMessageBean> receivers = new ArrayList<>();
            for (String account : dispatchSupervisiorAgent) {
                ReceiverMessageBean receiver = new ReceiverMessageBean("user", account);
                receivers.add(receiver);
            }
            List<AgentBean> dispatchAgent = agentService.findOnlineAgentByRoleCodes(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode());
            List<AgentBean> acceptAgent = agentService.findOnlineAgentByRoleCodes(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode());
            Map<String, List<AgentBean>> dealMappingDistrictAgent = new HashMap<>();
            for (AgentBean agentBO : dispatchAgent) {
                dealMappingDistrictAgent.put(agentBO.getAgentNumber(), acceptAgent);
            }

            Iterator<Map.Entry<String, List<AgentBean>>> entryIterator = dealMappingDistrictAgent.entrySet().iterator();
            for (AgentBean agentBO : acceptAgent) {
                List<String> managerList = new ArrayList<>();
                managerList.clear();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, List<AgentBean>> entry = entryIterator.next();
                    if (entry.getValue().contains(agentBO)) {//如果当前辖区只有一个在线的人结果，报当前辖区没有其他人接管
                        managerList.add(entry.getKey());
                    }
                }
                if (managerList.size() < 1) {
                    if (!receivers.isEmpty()) {
                        notifyActionService.pushMessage(WebsocketCodeEnum.MISSDISPATCHCHECKRESULT.getCode(), "1111111", receivers);
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheckForMonitorForAgent", "missDispatchCheckForMonitorForAgent fail ", ex);
        }
    }

    /**
     * 接处分开分配策略漏管检查(处警台辖区分配模式)
     */
    @Transactional(readOnly = true)
    public void missDispatchCheckForMonitorForAgentJurisdiction() {
        try {
            List<AgentBean> allAgent = agentService.findAllAgent();
            //所有的在线处警员坐席集合
            List<String> onlineDispatchAgents = new ArrayList<>();

            //将在线的班长席账号按照单位编组
            Map<String, List<String>> superOrgMap = new HashMap<>();
            // 获取在线坐席
            if (allAgent != null && allAgent.size() > 0) {
                for (AgentBean agentBO : allAgent) {

                    if (agentBO.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode() && agentBO.getPersonBean() != null &&
                            agentBO.getPersonBean().getPersonRole()!=null) {
                        if (agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR)) {
                            List<String> list = superOrgMap.get(agentBO.getPersonBean().getPersonOrgId());
                            if (list == null) {
                                list = new ArrayList<>();
                            }
                            if (!list.contains(agentBO.getPersonBean().getAccount())) {
                                list.add(agentBO.getPersonBean().getAccount());
                            }
                            superOrgMap.put(agentBO.getPersonBean().getPersonOrgId(), list);
                        } else if (agentBO.getPersonBean().getPersonRole().contains(RoleEnum.AGENT_PERSONROLE_DISPATCH)) {
                            onlineDispatchAgents.add(agentBO.getAgentNumber());
                        }
                    }

                }
            }
            //处警辖区和单位code对应关系
            Map<String, String> xqOrgCodeMap = new HashMap<>();
            //所有辖区
            Map<String, JurisdictionPO> xqMap = new HashMap<>();
            //处警辖区和坐席接管关系
            Map<String, List<String>> xqAgentMap = new HashMap<>();
            //获取所有的处警辖区以及辖区所属单位code
            List<Object[]> res = jurisdictionRepository.findAllJurisdictionPOAndOrgCode();
            //获取处警辖区和坐席绑定关系
            List<AgentJurisdictionPO> agentJurisdictionPOS = distributeService.queryAllDistrictAgent();//查处辖区跟坐席的绑定关系
            if (res != null && res.size() > 0) {
                res.forEach(obj -> {
                    JurisdictionPO po = (JurisdictionPO) obj[0];
                    String orgCode = (String) obj[1];
                    xqOrgCodeMap.put(po.getId(), orgCode);
                    xqMap.put(po.getId(), po);
                });
            }
            //处理辖区坐席对照关系
            if (agentJurisdictionPOS != null && agentJurisdictionPOS.size() > 0) {
                agentJurisdictionPOS.forEach(po -> {
                    String agentNum = String.valueOf(po.getAgentNum());
                    if (onlineDispatchAgents.contains(agentNum)) {
                        List<String> list = xqAgentMap.get(po.getJurisdictionId());
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        if (!list.contains(agentNum)) {
                            list.add(agentNum);
                        }
                        xqAgentMap.put(po.getJurisdictionId(), list);
                    }
                });
            }
            //获取未接管辖区
            Map<String, JurisdictionPO> unHandleMap = new HashMap<>();
            if (xqAgentMap.size() > 0) {
                xqAgentMap.keySet().forEach(key -> {
                    if (xqAgentMap.get(key) == null || xqAgentMap.get(key).isEmpty()) {
                        unHandleMap.put(xqOrgCodeMap.get(key), xqMap.get(key));
                    }
                });
            }
            //遍历班长席并且判断是否需要发送websocket
            if (unHandleMap.size() > 0 && superOrgMap.size() > 0) {
                List<JurisdictionPO> list = new ArrayList<>();
                superOrgMap.keySet().forEach(orgCode -> {
//                    List<String> childList = organizationService.findChildCode(orgCode);
                    List<String> childList = organizationService.findChildOrganizationId(orgCode);
                    list.clear();

                    for (String key : unHandleMap.keySet()) {
                        if (childList.contains(key)) {
                            list.add(unHandleMap.get(key));
                        }
                    }
                    if (list.size() > 0) {
                        notifyActionService.pushMessageToUsers(WebsocketCodeEnum.MISSDISPATCHCHECKFORMONITORRESULT.getCode(), list, superOrgMap.get(orgCode));

                    }
                });
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheckForMonitorForAgentJurisdiction", "missDispatchCheckForMonitorForAgentJurisdiction fail ", ex);
        }
    }

    /**
     * 接处分开分配策略漏管检查(处警员辖区分配模式)
     */
    public void missDispatchCheckForMonitorForAccountJurisdiction() {
        try {
            List<AgentBean> allAgent = agentService.findOnlineAgentByAgentType(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode() + "");
            List<AgentBean> dispatchSupervisiorAgent = agentService.findOnlineAgentByAgentType(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode()+ "");
            List<ReceiverMessageBean> receivers = new ArrayList<>();
            for (AgentBean agentBO : dispatchSupervisiorAgent) {
                ReceiverMessageBean receiver = new ReceiverMessageBean("user", agentBO.getPersonBean().getAccount());
                receivers.add(receiver);
            }
            Map<String, List<String>> dealMappingDistrictAgent = new HashMap<>();
            List<AccountJurisdictionPO> accountJurisdictionPOS = distributeService.queryAllDistrictLogin();//查处辖区跟处警员的绑定关系
            if (accountJurisdictionPOS != null) {
                for (AccountJurisdictionPO accountJurisdictionPO : accountJurisdictionPOS) {
                    Boolean isExist = false;
                    for (AgentBean agentBO : allAgent) {
                        if (agentBO.getPersonBean().getAccount().equals(accountJurisdictionPO.getAccountNum())) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        accountJurisdictionPOS.remove(accountJurisdictionPO);//将已经接管辖区的，但是不在线的处警员从集合中去除
                    }
                }
            }
            List<JurisdictionPO> jurisdictionPOS = distributeService.queryAllJurisdiction();//获取所有处警辖区
            if (jurisdictionPOS != null) {
                for (JurisdictionPO jurisdictionPO : jurisdictionPOS) {
                    List<String> accountList = new ArrayList<>();
                    if (accountJurisdictionPOS != null) {
                        for (AccountJurisdictionPO accountJurisdictionPO : accountJurisdictionPOS) {
                            if (jurisdictionPO.getId().equals(accountJurisdictionPO.getJurisdictionId())) {
                                accountList.add(String.valueOf(accountJurisdictionPO.getAccountNum()));
                            }
                        }
                    }
                    dealMappingDistrictAgent.put(jurisdictionPO.getId(), accountList);//将每个处警辖区对应所在线的处警坐席添加到map中
                }
            }
            Iterator<Map.Entry<String, List<String>>> entryIterator = dealMappingDistrictAgent.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, List<String>> entry = entryIterator.next();
                if (entry.getValue().size() < 1) {//如果当前辖区没有在线的人结果，报当前辖区没有人接管
                    if (!receivers.isEmpty()) {
                        notifyActionService.pushMessage(WebsocketCodeEnum.MISSDISPATCHCHECKFORMONITORRESULT.getCode(), "1111111", receivers);
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "missDispatchCheckForMonitorForAccountJurisdiction", "missDispatchCheckForMonitorForAccountJurisdiction fail ", ex);
        }
    }


}
