package com.dscomm.iecs.agent.service.distribute;

import com.dscomm.iecs.agent.dal.po.DistributeStrategy.*;
import com.dscomm.iecs.agent.dal.repository.AgentIncidentTypeRepository;
import com.dscomm.iecs.agent.dal.repository.AgentJurisdictionRepository;
import com.dscomm.iecs.agent.dal.repository.DistributeCaseRecordRepository;
import com.dscomm.iecs.agent.dal.repository.JurisdictionRepository;
import com.dscomm.iecs.agent.enums.AgentStateEnum;
import com.dscomm.iecs.agent.enums.RoleEnum;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.agent.service.distribute.bean.*;
import com.dscomm.iecs.base.enums.BasicEnumNumberBean;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述:处警分配服务
 *
 * @author YangFuxi
 * Date Time 2019/9/18 19:12
 */
@Component
public class DistributeServiceImpl implements DistributeService {
    private static final Logger logger = LoggerFactory.getLogger(DistributeServiceImpl.class);

    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;

    private LogService logService;
    private SystemConfigurationService systemConfigurationService ;
    private JurisdictionRepository jurisdictionRepository;
    private DistributeCaseRecordRepository distributeCaseRecordRepository ;
    private AgentService agentService;
    private UserService userService ;
    private OrganizationService organizationService ;
    private AgentJurisdictionRepository agentJurisdictionRepository;
    private ServletService servletService ;
    private AgentCacheService agentCacheService;
    private NotifyActionService notifyActionService ;
    private DictionaryService dictionaryService ;
    private AgentIncidentTypeRepository agentIncidentTypeRepository;


    @Autowired
    public DistributeServiceImpl(  LogService logService , SystemConfigurationService systemConfigurationService ,
                                   JurisdictionRepository jurisdictionRepository , DistributeCaseRecordRepository distributeCaseRecordRepository ,
                                    AgentService agentService , UserService userService , OrganizationService organizationService ,
                                   AgentJurisdictionRepository agentJurisdictionRepository , ServletService servletService , AgentCacheService agentCacheService ,
                                   NotifyActionService notifyActionService , DictionaryService dictionaryService , AgentIncidentTypeRepository agentIncidentTypeRepository

    ) {
        this.logService = logService;
        this.systemConfigurationService = systemConfigurationService ;
        this.jurisdictionRepository = jurisdictionRepository ;
        this.distributeCaseRecordRepository = distributeCaseRecordRepository ;
        this.agentService = agentService ;
        this.userService = userService ;
        this.organizationService = organizationService ;
        this.agentJurisdictionRepository = agentJurisdictionRepository ;
        this.servletService = servletService ;
        this.agentCacheService = agentCacheService ;
        this.notifyActionService = notifyActionService ;
        this.dictionaryService = dictionaryService ;
        this.agentIncidentTypeRepository = agentIncidentTypeRepository ;
    }



    /**
     * {@inheritDoc}
     *
     * @see #getDistributeMode(String, Integer, List)
     */
    @Transactional(readOnly = true)
    @Override
    public DistributeModeBean getDistributeMode(String systemName, Integer agentNum, List<String> acceptTypes) {
        try {
            DistributeConfigBean config = getDistributeConfig();
            DistributeModeBean bo = new DistributeModeBean();
            bo.setAlarmTypeFilteration(config.getAlarmTypeFilter());
            bo.setDistributeMode(config.getDistributeMode());
            //设置警种属性
            if (config.getAlarmTypeFilter()) {
                if (acceptTypes == null || acceptTypes.isEmpty() || ( acceptTypes.size() == 1 && "0".equals(acceptTypes.get(0)))) {
                    List<String> types = jurisdictionRepository.findAllAcdJz();
                    bo.setAlarmTypes(types);
                } else {
                    bo.setAlarmTypes(acceptTypes);
                }
            } else {
                bo.setAlarmTypes(acceptTypes);
            }
            /**
             * 0.接处合一
             * 1.坐席分配(接管哪几个接警坐席)
             * 2.工号辖区
             * 3.坐席辖区
             * 4.轮询分配
             * 5.坐席案由
             */
            List<String> codes = new ArrayList<>();//接管明细
            List<String> accepts = new ArrayList<>();//接管大项
            switch (config.getDistributeMode()) {
                case "0":
                    //接处合一
                    break;
                case "1":
                    //坐席分配(接管哪几个接警坐席)

                    break;
                case "2":
                    //TODO工号辖区
                    List<Object[]> res = jurisdictionRepository.findAllOrgCodesByAccountNumJurisdictions(systemName);
                    transformOrgAndJurisdiction(codes, accepts, res);
                    break;
                case "3":
                    //坐席辖区
                    List<Object[]> ret = jurisdictionRepository.findOrgCodesByAgentJurisdictions(agentNum);
                    transformOrgAndJurisdiction(codes, accepts, ret);
                    break;
                case "4":
                    //轮询分配

                    break;
                case "5":
                    //TODO坐席案由分配
                    List<String> ay = jurisdictionRepository.findAyByAgentNum(agentNum);
                    accepts.addAll(ay);
//                    codes = causeService.findCauseCodes(ay); //获得某个案由全部下级案由 消防暂无此需求
                    break;
                default:
                    break;
            }
            bo.setAccepts(accepts);
            bo.setAcceptItems(codes);
            return bo;

        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "getDisributeMode", "", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_DISTRIBUTE_INFO_FAIL);
        }
    }


    /**
     * 转换方法，处理辖区查询结果
     *
     * @param codes   存放接管辖区对应的单位
     * @param accepts 接管辖区
     * @param res     查询结果
     */
    private void transformOrgAndJurisdiction(List<String> codes, List<String> accepts, List<Object[]> res) {
        if (res != null && res.size() > 0) {
            for (Object[] obj : res) {
                String code = (String) obj[0];
                JurisdictionPO po = (JurisdictionPO) obj[1];
                if (!codes.contains(code)) {
                    codes.add(code);
                }
                if (accepts != null && po != null && !accepts.contains(po.getId())) {
                    accepts.add(po.getId());
                }
//                if (acceptAreas.get(po.getId()) == null) {
//                    JurisdictionBO jbo = new JurisdictionBO();
//                    jbo.setId(po.getId());
//                    jbo.setName(I18nMessageUtils.getI18nMessage(String.format("t_ecs_cjxq.mc.%s", po.getId()), po.getName()));
//                    acceptAreas.put(jbo.getId(), jbo);
//                }
            }
        }
    }



    @Transactional(readOnly = true)
    @Override
    public DistributeConfigBean getDistributeConfig() {
        try {
            Long start = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getDistributeConfig", "start excute sql to query distribute config info");
            SystemConfigurationBean alarmTypeConfig = systemConfigurationService.getSystemConfigByConfigType("alarmTypeFilter");
            SystemConfigurationBean distributeModeConfig = systemConfigurationService.getSystemConfigByConfigType("jcfk_mode");
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getDistributeConfig", String.format("end excute sql to query distribute config info,the total time is:%sms", end - start));
            DistributeConfigBean configBO = new DistributeConfigBean();
            if (alarmTypeConfig != null && !StringUtils.isBlank(alarmTypeConfig.getConfigValue()) && "1".equals(alarmTypeConfig.getConfigValue())) {
                configBO.setAlarmTypeFilter(true);
            }
            if (distributeModeConfig != null) {
                configBO.setDistributeMode(distributeModeConfig.getConfigValue());
            }
            return configBO;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getDistributeConfig", "find incident distribute config fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_DISTRIBUTE_CONFIG_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #findOrgCodesByAgentJurisdictions(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findOrgCodesByAgentJurisdictions(Integer agentCode) {
        try {
            logService.infoLog(logger, "service", "findOrgCodesByAgentJurisdictions", "start excute sql to query OrgCode ");
            Long start = System.currentTimeMillis();
            List<Object[]> res = jurisdictionRepository.findOrgCodesByAgentJurisdictions(agentCode);
            List<String> orgCodeList = new ArrayList<>();
            List<String> list = new ArrayList<>();
            transformOrgAndJurisdiction(orgCodeList, list, res);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrgCodesByAgentJurisdictions", String.format("end excute sql to query OrgCode,the total time is:%sms", end - start));
            return orgCodeList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrgCodesByAgentJurisdictions", "find all OrgCode fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_ORGCODESBYAGENTJURISDICTIONS_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #findAllOrgCodesByAccountNumJurisdictions(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findAllOrgCodesByAccountNumJurisdictions(String userCode) {
        try {
            logService.infoLog(logger, "service", "findAllOrgCodesByAccountNumJurisdictions", "start excute sql to query OrgCode ");
            Long start = System.currentTimeMillis();
            List<Object[]> res = jurisdictionRepository.findAllOrgCodesByAccountNumJurisdictions(userCode);
            List<String> orgCodeList = new ArrayList<>();
            List<String> list = new ArrayList<>();
            transformOrgAndJurisdiction(orgCodeList, list, res);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOrgCodesByAccountNumJurisdictions", String.format("end excute sql to query OrgCode,the total time is:%sms", end - start));
            return orgCodeList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOrgCodesByAccountNumJurisdictions", "find all OrgCode fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_ORGCODESBYACCOUNTNUMJURISDICTIONS_FAIL);
        }
    }





    /**
     * {@inheritDoc}
     *
     * @see #findAllIncidentIdFromDistributeRecord(String, Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findAllIncidentIdFromDistributeRecord(String userCode, Integer distributeStatus) {
        try {
            logService.infoLog(logger, "service", "findAllIncidentIdFromDistributeRecord", "start excute sql to query IncidentId ");
            Long start = System.currentTimeMillis();
            List<String> incidentIdList = distributeCaseRecordRepository.findAllIncidentIdFromDistributeRecord(userCode, distributeStatus);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllIncidentIdFromDistributeRecord", String.format("end excute sql to query IncidentId,the total time is:%sms", end - start));
            return incidentIdList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOrgCodesByAccountNumJurisdictions", "find all IncidentId fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_ORGCODESBYACCOUNTNUMJURISDICTIONS_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #queryAllDistrictAgent()
     */
    @Transactional(readOnly = true)
    @Override
    public List<AgentJurisdictionPO> queryAllDistrictAgent() {
        try {
            logService.infoLog(logger, "service", "queryAllDistrictAgent", "start excute sql to query AgentJurisdictionPO ");
            Long start = System.currentTimeMillis();
            List<AgentJurisdictionPO> agentJurisdictionPOS = jurisdictionRepository.queryAllDistrictAgent();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryAllDistrictAgent", String.format("end excute sql to query AgentJurisdictionPO,the total time is:%sms", end - start));
            return agentJurisdictionPOS;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAllDistrictAgent", "find all AgentJurisdictionPO fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.QUERY_ALLDISTRICTAGENT_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #queryAllDistrictLogin()
     */
    @Transactional(readOnly = true)
    @Override
    public List<AccountJurisdictionPO> queryAllDistrictLogin() {
        try {
            logService.infoLog(logger, "service", "queryAllDistrictLogin", "start excute sql to query AccountJurisdictionPO ");
            Long start = System.currentTimeMillis();
            List<AccountJurisdictionPO> agentJurisdictionPOS = jurisdictionRepository.queryAllDistrictLogin();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryAllDistrictLogin", String.format("end excute sql to query AccountJurisdictionPO,the total time is:%sms", end - start));
            return agentJurisdictionPOS;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAllDistrictLogin", "find all AccountJurisdictionPO fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.QUERY_ALLDISTRICTACCOUNT_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #queryAllJurisdiction()
     */
    @Transactional(readOnly = true)
    @Override
    public List<JurisdictionPO> queryAllJurisdiction() {
        try {
            logService.infoLog(logger, "service", "queryAllJurisdiction", "start excute sql to query JurisdictionPO ");
            Long start = System.currentTimeMillis();
            List<JurisdictionPO> jurisdictionPOS = jurisdictionRepository.queryAllJurisdiction();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryAllJurisdiction", String.format("end excute sql to query JurisdictionPO,the total time is:%sms", end - start));
            return jurisdictionPOS;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAllJurisdiction", "find all JurisdictionPO fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.QUERY_ALLJURISDICTION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #queryAllJurisdictionBO()
     */
    @Transactional(readOnly = true)
    @Override
    public List<JurisdictionBean> queryAllJurisdictionBO() {
        try {
            logService.infoLog(logger, "service", "queryAllJurisdictionBO", "start excute sql to query JurisdictionPO ");
            Long start = System.currentTimeMillis();
            List<JurisdictionPO> jurisdictionPOS = jurisdictionRepository.queryAllJurisdiction();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryAllJurisdictionBO", String.format("end excute sql to query JurisdictionPO,the total time is:%sms", end - start));

            List<JurisdictionBean> jurisdictionBOList = new ArrayList<>();
            if (null != jurisdictionPOS && jurisdictionPOS.size() > 0) {
                for (JurisdictionPO jurisdictionPO : jurisdictionPOS) {
                    if (null != jurisdictionPO) {
                        JurisdictionBean jurisdictionBO = new JurisdictionBean();
                        jurisdictionBO.setId(jurisdictionPO.getId());
                        jurisdictionBO.setName(jurisdictionPO.getName());
                        jurisdictionBOList.add(jurisdictionBO);
                    }
                }
            }

            return jurisdictionBOList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAllJurisdictionBO", "find all JurisdictionBO fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.QUERY_ALLJURISDICTION_FAIL);
        }
    }







    /**
     * {@inheritDoc}
     *
     * @see #queryAllJurisdictionForAgent()
     */
    @Transactional(readOnly = true)
    @Override
    public List<JurisdictionForAgentBean> queryAllJurisdictionForAgent() {
        try {
//            logService.infoLog(logger, "service", "queryAllJurisdictionForAgent", "start excute sql to query JurisdictionPO ");
//            Long start = System.currentTimeMillis();
//            List<JurisdictionForAgentBO> jurisdictionForAgentBOS = new ArrayList<JurisdictionForAgentBO>();
//            List<JurisdictionPO> jurisdictionPOS = jurisdictionRepository.queryAllJurisdiction();//查询出所有处警辖区集合
//            List<AgentJurisdictionPO> agentJurisdictionPOS = jurisdictionRepository.queryAllDistrictAgent();//处警辖区与坐席目前数据库中存在的绑定关系
//            List<AgentJurisdictionPO> onlineAgentJurisdictionPOS = judgeAgentIsOnline(agentJurisdictionPOS);//判断存在的绑定关系中，坐席是否在线
//            transformJurisdictionForAgentList(jurisdictionForAgentBOS, jurisdictionPOS, onlineAgentJurisdictionPOS);
//            Long end = System.currentTimeMillis();
//            logService.infoLog(logger, "service", "queryAllJurisdictionForAgent", String.format("end excute sql to query JurisdictionPO,the total time is:%sms", end - start));
//            return jurisdictionForAgentBOS;


            logService.infoLog(logger, "service", "queryAllJurisdictionForAgent", "start excute sql to query JurisdictionPO ");
            Long start = System.currentTimeMillis();
            List<JurisdictionForAgentBean> list=new ArrayList<>();
            Map<String,String> xqOrgMap=new HashMap<>();
            // 获取在线处警员座席 消防无处警席位  只有接警席位
//            List<AgentBean> onlineDispatchAgentBOList = agentService.findOnlineAgentByRoleCodes(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode());
            List<AgentBean> onlineDispatchAgentBOList = agentService.findOnlineAgentByRoleCodes(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode());
            List<String> onlineDispatchAgentNumList = new ArrayList<>();
            if (null != onlineDispatchAgentBOList && onlineDispatchAgentBOList.size() > 0) {
                for (AgentBean agentBO : onlineDispatchAgentBOList) {
                    if (null != agentBO) {
                        onlineDispatchAgentNumList.add(agentBO.getAgentNumber());
                    }
                }
            }
            // 获取已绑定辖区坐席关系（辖区id，辖区name，坐席号）
            List<Object[]> existJurisdictionAndAgent = jurisdictionRepository.findExistJurisdictionAndAgent();
            Map<String, JurisdictionForAgentBean> jurisdictionForAgentBOMap = new HashMap<>();
            if (null != existJurisdictionAndAgent && existJurisdictionAndAgent.size() > 0) {
                for (Object[] objects : existJurisdictionAndAgent) {
                    String jurisdictionId = (String) objects[0];
                    String jurisdictionName = (String) objects[1];
                    String orgId= (String) objects[2];
                    if (!StringUtils.isBlank(orgId)){
                        xqOrgMap.put(jurisdictionId,orgId);
                    }
                    Integer agentNum = (Integer) objects[3];
                    if (!StringUtils.isBlank(jurisdictionId)) {
                        if (!jurisdictionForAgentBOMap.containsKey(jurisdictionId)) {
                            // 不存在，创建
                            JurisdictionForAgentBean jurisdictionForAgentBO = new JurisdictionForAgentBean();
                            jurisdictionForAgentBO.setId(jurisdictionId);
                            jurisdictionForAgentBO.setName(jurisdictionName);
                            List<String> agentNumList = new ArrayList<>();
                            // 判断坐席号是在线的处警坐席,添加进结果
                            if (agentNum!=null&&onlineDispatchAgentNumList.contains(String.valueOf(agentNum))) {
                                agentNumList.add(String.valueOf(agentNum));
                            }
                            jurisdictionForAgentBO.setAgentBOList(agentNumList);
                            jurisdictionForAgentBOMap.put(jurisdictionId, jurisdictionForAgentBO);
                        } else if (jurisdictionForAgentBOMap.containsKey(jurisdictionId)) {
                            // 已存在，添加坐席号集合
                            // 判断坐席号是在线的处警坐席,添加进结果
                            if (agentNum!=null&&onlineDispatchAgentNumList.contains(String.valueOf(agentNum))) {
                                List<String> agentBOList = jurisdictionForAgentBOMap.get(jurisdictionId).getAgentBOList();
                                agentBOList.add(String.valueOf(agentNum));
                            }
                        }
                    }
                }
            }
            //判断分权分域
            boolean fqfy=isLimitedByFQFY();
            if (fqfy){
                UserInfo userInfo = userService.getUserInfo() ;
//                List<String> orgIds = organizationService.findChildId(userInfo.getOrgCode());
                List<String> orgIds = organizationService.findChildOrganizationId( userInfo.getOrgId() );
                if (jurisdictionForAgentBOMap!=null&&jurisdictionForAgentBOMap.size()>0){
                    jurisdictionForAgentBOMap.keySet().forEach(key->{
                        if (orgIds.contains(xqOrgMap.get(key))){
                            list.add(jurisdictionForAgentBOMap.get(key));
                        }
                    });
                }
            }else {
                list.addAll(jurisdictionForAgentBOMap.values());
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryAllJurisdictionForAgent", String.format("end excute sql to query JurisdictionPO,the total time is:%sms", end - start));

            return list;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAllJurisdictionForAgent", "find all JurisdictionPO fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.QUERY_ALLJURISDICTIONFORAGENT_FAIL);
        }
    }

    private  boolean isLimitedByFQFY() {
        try {
//            Map<String, LicenseBO> licenseBOMap = licenseCacheService.getLicenseCache(cacheKeyPrefix);
//            List<LicenseBO> licenseBOList = new ArrayList<>(licenseBOMap.values());
//            if (licenseBOList.size() < 1) {
//                return false;
//            }
//            for (LicenseBO licenseBO : licenseBOList) {
//                if (licenseBO.getProductCode().equals("cad_fqfy")) {
//                    if (licenseBO.getState() == LicenseResultEnum.LICENSE_LICENSED.getCode() || licenseBO.getState() == LicenseResultEnum.LICENSE_SOONEXPIRE.getCode()) {
//                        return true;
//                    }
//                }
//            }
//            Map<String, SystemConfigBO> systemConfigBOMap = systemConfigCacheService.getSystemConfigCache(cacheKeyPrefix);
//            if (systemConfigBOMap.size() < 1) {
//                return false;
//            }
//            if (systemConfigBOMap.containsKey("fqfy")) {
//                SystemConfigBO systemConfigBO = systemConfigBOMap.get("fqfy");
//                return (null != systemConfigBO && "1".equals(systemConfigBO.getValue()));
//            }

            UserInfo userInfo = userService.getUserInfo() ;
            return userInfo.getNeedAuthenticate();
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "IsLimitedByLicense", "get IsLimitedByLicense fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }





    /**
     * {@inheritDoc}
     *
     * @see #queryAgentBosForJurisdiction(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<AgentBean> queryAgentBosForJurisdiction(String jurisdictionNum) {
        try {
//            logService.infoLog(logger, "service", "queryAgentBosForJurisdiction", "start excute sql to query Agent ");
//            Long start = System.currentTimeMillis();
//            List<String> childCodeList = organizationService.findChildCode(getUserInfo().getOrgCode());
//            List<AgentBO> agentBOS = agentService.findAllAgent();
//            List<AgentBO> resultagentBOS = new ArrayList<>();
//            List<AgentJurisdictionPO> agentJurisdictionPOS = jurisdictionRepository.queryAllDistrictAgentByJurisdictionNum(jurisdictionNum);//获取当前辖区的数据库中绑定关系 不管在不在线
//            for (AgentBO agent : agentBOS) {
//                if (agentJurisdictionPOS != null && agentJurisdictionPOS.size() < 1) {//如果不存在已经绑定数据时遍历
//                    if (agent.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode() &&
//                            agent.getPersonBO().getPersonRole().getCode() == RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode()) {
//                        if ((isLimitedByLicense() && childCodeList.contains(agent.getPersonBO().getPersonOrgCode())) || !isLimitedByLicense()) {
//                            resultagentBOS.add(agent);
//                        }
//                        break;
//                    }
//                } else {
//                    Boolean isExist = false;
//                    if (agent.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode() &&
//                            agent.getPersonBO().getPersonRole().getCode() == RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode()) {//先判断坐席是否符合权限
//                        for (AgentJurisdictionPO agentJurisdictionPO : agentJurisdictionPOS) {//判断是否该坐席已经管理该辖区
//                            if (Integer.valueOf(agent.getAgentNum()) == agentJurisdictionPO.getAgentNum()) {
//                                isExist = true;
//                                break;
//                            }
//                        }
//                        if (((isLimitedByLicense() && childCodeList.contains(agent.getPersonBO().getPersonOrgCode())) || !isLimitedByLicense()) && !isExist) {
//                            resultagentBOS.add(agent);
//                        }
//                    }
//
//                }
//            }
//            Long end = System.currentTimeMillis();
//            logService.infoLog(logger, "service", "queryAgentBosForJurisdiction", String.format("end excute sql to query Agent,the total time is:%sms", end - start));
//            return resultagentBOS;


            logService.infoLog(logger, "service", "queryAgentBosForJurisdiction", "start excute sql to query Agent ");
            Long start = System.currentTimeMillis();
            //获取辖区对应坐席
//            String orgCode = jurisdictionRepository.findOrgCodeByJuristdictionNum(jurisdictionNum);
            JurisdictionPO jurisdictionPO = jurisdictionRepository.findByJurisdictionNum(jurisdictionNum);


            // 获取已绑定该辖区的座席号集合
            List<AgentJurisdictionPO> agentJurisdictionPOList = jurisdictionRepository.queryAllDistrictAgentByJurisdictionNum(jurisdictionNum);//获取当前辖区的数据库中绑定关系 不管在不在线
            List<String> existAgentNumList = new ArrayList<>();
            if (null != agentJurisdictionPOList && agentJurisdictionPOList.size() > 0) {
                for (AgentJurisdictionPO agentJurisdictionPO : agentJurisdictionPOList) {
                    if (null != agentJurisdictionPO && null != agentJurisdictionPO.getAgentNum()) {
                        existAgentNumList.add(String.valueOf(agentJurisdictionPO.getAgentNum()));
                    }
                }
            }

            // 获取子单位code集合，包含自身
//            List<String> childCodeList = organizationService.findChildCode(getUserInfo().getOrgCode());
            List<String> childCodeList = organizationService.findChildOrganizationId( jurisdictionPO.getOrgid() );
            // 获取在线处警员座席
            List<AgentBean> onlineDispatchAgentBOList = agentService.findOnlineAgentByRoleCodes(RoleEnum.AGENT_PERSONROLE_DISPATCH.getCode());
            // 是否分权分域
            boolean isAuthorization = isLimitedByFQFY();
            // 结果集
            List<AgentBean> resultAgentList = new ArrayList<>();
            if (null != onlineDispatchAgentBOList && onlineDispatchAgentBOList.size() > 0) {
                for (AgentBean agentBO : onlineDispatchAgentBOList) {
//                    if (null != agentBO
//                            && null != agentBO.getPersonBO()
//                            && !StringUtils.isBlank(agentBO.getPersonBO().getPersonOrgCode())
//                            // 判断单位
//                            && (!isAuthorization || (orgCode.equals(agentBO.getPersonBO().getPersonOrgCode())))
//                            // 判断是否已绑定
//                            && !existAgentNumList.contains(agentBO.getAgentNum())) {
//                        resultAgentList.add(agentBO);
//                    }

                    if (null != agentBO
                            && null != agentBO.getPersonBean()
                            && !StringUtils.isBlank(agentBO.getPersonBean().getPersonOrgId())
                            // 判断单位
                            && (!isAuthorization || (childCodeList.contains(agentBO.getPersonBean().getPersonOrgId())))
                            // 判断是否已绑定
                            && !existAgentNumList.contains(agentBO.getAgentNumber())) {
                        resultAgentList.add(agentBO);
                    }
                }
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryAgentBosForJurisdiction", String.format("end excute sql to query Agent,the total time is:%sms", end - start));

            return resultAgentList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAgentBosForJurisdiction", "find agent for jurisdiction fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.QUERY_AGENTBOSFORJURISDICTION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #addJurisdictionForAgent(Integer, String)
     */
    @Transactional
    @Override
    public List<JurisdictionForAgentBean> addJurisdictionForAgent(Integer agentNum, String jurisdictionNum) {
        try {
            logService.infoLog(logger, "service", "addJurisdictionForAgent", "start excute sql to add JurisdictionForAgent ");
            Long start = System.currentTimeMillis();
            Long time = servletService.getSystemTime();
            AgentJurisdictionPO agentJurisdictionPO = agentJurisdictionRepository.getByAgentNumAndJurisdictionId(agentNum, jurisdictionNum);
            if (agentJurisdictionPO != null) {
                return queryAllJurisdictionForAgent();
            } else {
                agentJurisdictionPO = new AgentJurisdictionPO();
                agentJurisdictionPO.setAgentNum(agentNum);
                agentJurisdictionPO.setJurisdictionId(jurisdictionNum);
                agentJurisdictionPO.setSjc(time);
                agentJurisdictionRepository.save(agentJurisdictionPO);
                List<JurisdictionForAgentBean> jurisdictionForAgentBOS = queryAllJurisdictionForAgent();//获取所有辖区被在线坐席接管的集合
                Long end = System.currentTimeMillis();
                List<AgentBean> allAgent = agentService.findAllAgent();
                for (AgentBean agent : allAgent) {
                    if (agent.getAgentNumber().equals(String.valueOf(agentNum))) {
//                        List<String> accepts = new ArrayList<>();//获取当前缓存中该坐席接管的辖区集合
//                        List<String> acceptItems = new ArrayList<>();
//                        List<Object[]> ret = jurisdictionRepository.findOrgCodesByAgentJurisdictions(agentNum);//重新刷新一遍坐席中的接管单位与接管辖区缓存
                        DistributeModeBean distributeModeBO = agent.getDistribute();
                        List<String> accepts = distributeModeBO.getAccepts();
                        List<String> acceptItems = distributeModeBO.getAcceptItems();
                        if (accepts == null) {
                            accepts = new ArrayList<>();
                        }
                        if (acceptItems == null) {
                            acceptItems = new ArrayList<>();
                        }
                        String orgCode = jurisdictionRepository.findOrgCodeByJuristdictionNum(jurisdictionNum);
                        if (!accepts.contains(jurisdictionNum)) {
                            accepts.add(jurisdictionNum);
                        }
                        if (!acceptItems.contains(orgCode)) {
                            acceptItems.add(orgCode);
                        }
                        distributeModeBO.setAccepts(accepts);
                        distributeModeBO.setAcceptItems(acceptItems);
                        agent.setDistribute(distributeModeBO);
//                        transformOrgAndJurisdiction(acceptItems, accepts, ret);
//                        agent.getDistribute().setAcceptItems(acceptItems);
//                        agent.getDistribute().setAccepts(accepts);
                        agentCacheService.mergeAgentCache("put", agent, cacheKeyPrefix, true);
                        break;
                    }
                }
                logService.infoLog(logger, "service", "addJurisdictionForAgent", String.format("end excute sql to add JurisdictionForAgent,the total time is:%sms", end - start));
                try {
                    pushJurisdictionConnectMessage(jurisdictionForAgentBOS);
                } catch (Exception ex) {
                    logService.erorLog(logger, "service", "addJurisdictionForAgent", "send websockt add JurisdictionForAgent fail", ex);
                }
                return jurisdictionForAgentBOS;
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "addJurisdictionForAgent", "add JurisdictionForAgent fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.ADD_JURISDICTIONFORAGENT_FAIL);
        }
    }


    private  void pushJurisdictionConnectMessage(List<JurisdictionForAgentBean> jurisdictionForAgentBOS) {
        try {
            List<AgentBean> allAgent = agentService.findAllAgent();
            List<ReceiverMessageBean> receivers = new ArrayList<>();
            if (allAgent != null && allAgent.size() > 0) {
                allAgent.forEach(agent -> {
                    if (agent.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode()
                            && agent.getPersonBean().getPersonRole() != null && agent.getPersonBean().getPersonRole().size() > 0) {
                        for ( BasicEnumNumberBean basicEnumNumberBean : agent.getPersonBean().getPersonRole()  ){
                            if (basicEnumNumberBean.getCode() == RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode()){
                                ReceiverMessageBean receiver = new ReceiverMessageBean("user", agent.getPersonBean().getAccount());
                                receivers.add(receiver);
                                break;
                            }
                        }
                    }
                });
                //websocket推送
                if (!receivers.isEmpty()) {
                    notifyActionService.pushMessage(WebsocketCodeEnum.JURISDICTIONFORAGENTCHANGE.getCode(), jurisdictionForAgentBOS, receivers);
                }
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "pushJurisdictionConnectMessage", "pushJurisdictionConnectMessage failed", ex);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #deleteJurisdictionForAgent(Integer, String)
     */
    @Transactional
    @Override
    public List<JurisdictionForAgentBean> deleteJurisdictionForAgent(Integer agentNum, String jurisdictionNum) {
        try {
            logService.infoLog(logger, "service", "deleteJurisdictionForAgent", "start excute sql to delete JurisdictionForAgent");
            Long start = System.currentTimeMillis();
            agentJurisdictionRepository.deleteJurisdictionForAgent(agentNum, jurisdictionNum);
            List<JurisdictionForAgentBean> jurisdictionForAgentBOS = queryAllJurisdictionForAgent();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deleteJurisdictionForAgent", String.format("end excute sql to delete JurisdictionForAgent,the total time is:%sms", end - start));
            List<AgentBean> allAgent = agentService.findAllAgent();
            for (AgentBean agent : allAgent) {
                if (agent.getAgentNumber().equals(String.valueOf(agentNum))) {
//                    List<String> accepts = new ArrayList<>();//获取当前缓存中该坐席接管的辖区集合
//                    List<String> acceptItems = new ArrayList<>();
//                    List<Object[]> ret = jurisdictionRepository.findOrgCodesByAgentJurisdictions(agentNum);//重新刷新一遍坐席中的接管单位与接管辖区缓存
//                    transformOrgAndJurisdiction(acceptItems, accepts, ret);
//                    agent.getDistribute().setAcceptItems(acceptItems);
//                    agent.getDistribute().setAccepts(accepts);
                    DistributeModeBean distribute = agent.getDistribute();
                    String orgCode = jurisdictionRepository.findOrgCodeByJuristdictionNum(jurisdictionNum);
                    List<String> accepts = distribute.getAccepts();
                    List<String> acceptItems = distribute.getAcceptItems();
                    if (accepts != null && accepts.size() > 0) {
                        accepts.remove(jurisdictionNum);
                    }
                    if (acceptItems != null && acceptItems.size() > 0) {
                        acceptItems.remove(orgCode);
                    }
                    distribute.setAccepts(accepts);
                    distribute.setAcceptItems(acceptItems);
                    agent.setDistribute(distribute);
                    agentCacheService.mergeAgentCache("put", agent, cacheKeyPrefix, true);
                    break;
                }
            }
            try {
                pushJurisdictionConnectMessage(jurisdictionForAgentBOS);
            } catch (Exception ex) {
                logService.erorLog(logger, "service", "deleteJurisdictionForAgent", "send websockt delete JurisdictionForAgent fail", ex);
            }
            return jurisdictionForAgentBOS;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "deleteJurisdictionForAgent", "delete JurisdictionForAgent fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DELETE_JURISDICTIONFORAGENT_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #queryAllCaseNature()
     */
    @Transactional(readOnly = true)
    @Override
    public List<DistributeCaseNaturePO> queryAllCaseNature() {
        try {
            logService.infoLog(logger, "service", "queryAllCaseNature", "start excute sql to query DistributeCaseNaturePO ");
            Long start = System.currentTimeMillis();
            List<DistributeCaseNaturePO> distributeCaseNaturePOS = jurisdictionRepository.queryAllCaseNature();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryAllCaseNature", String.format("end excute sql to query DistributeCaseNaturePO,the total time is:%sms", end - start));
            return distributeCaseNaturePOS;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAllCaseNature", "find all DistributeCaseNaturePO fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.QUERY_ALLCASENATURE_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #findAllCauseFoAgent()
     */
    @Transactional(readOnly = true)
    @Override
    public List<CauseFoAgentBean> findAllCauseFoAgent() {
        try {
            List<CauseFoAgentBean> list = new ArrayList<>();
            //获取所有一级案由
            Map<String, CauseFoAgentBean> map = getCauseFoAgentBO();
            list.addAll(map.values());
            return list;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllCauseFoAgent", "find agent cause info fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_CAUSE_AGENT_FAIL);
        }
    }

    /**
     * 私有方法，获取所有坐席案由对应关系
     *
     * @return 返回所有一级案由和坐席的对应关系
     */
    private Map<String, CauseFoAgentBean> getCauseFoAgentBO() {
        List<DictionaryBean> causes = dictionaryService.findGridDictionary("AJLX", false ) ;
        //获取所有坐席
        List<AgentBean> agets = agentService.findAllAgent();
        Map<String, CauseFoAgentBean> map = new HashMap<>();
        if (causes != null && causes.size() > 0) {

            causes.forEach(c -> {
                if (map.get(c.getCode()) == null) {
                    CauseFoAgentBean bo = new CauseFoAgentBean();
                    bo.setCode(c.getCode());
                    bo.setId(c.getId());
//                    bo.setName(I18nMessageUtils.getI18nMessage("t_tyqx_ywzdx.mc." + c.getId(), c.getName()));
                    map.put(c.getCode(), bo);
                }
            });
            if (agets != null && agets.size() > 0) {
                agets.forEach(agent -> {
                    //找出在线的处警席
                    if (agent.getAgentState().getCode() != AgentStateEnum.AGENT_STATE_OFFLINE.getCode() &&
                            agent.getPersonBean() != null && agent.getPersonBean().getPersonRole() != null && agent.getPersonBean().getPersonRole().size() > 0) {
                        for ( BasicEnumNumberBean basicEnumNumberBean : agent.getPersonBean().getPersonRole()  ){
                            if (basicEnumNumberBean.getCode() == RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode()){
                                DistributeModeBean distribute = agent.getDistribute();
                                if (distribute != null && distribute.getAccepts() != null && distribute.getAccepts().size() > 0) {
                                    List<String> accepts = distribute.getAccepts();
                                    accepts.forEach(accept -> {
                                        CauseFoAgentBean bo = map.get(accept);
                                        if (bo != null) {
                                            Set<AgentBrieflyBean> agentInfoBOS = bo.getAgents();
                                            if (agentInfoBOS == null) {
                                                agentInfoBOS = new HashSet<>();
                                            }
                                            AgentBrieflyBean agentInfoBO = new AgentBrieflyBean();
                                            agentInfoBO.setAccount(agent.getPersonBean().getAccount());
                                            agentInfoBO.setAgentNum(agent.getAgentNumber());
                                            agentInfoBO.setUserName(agent.getPersonBean().getUserName());
                                            agentInfoBOS.add(agentInfoBO);
                                            bo.setAgents(agentInfoBOS);
                                        }
                                    });
                                    break;
                                }
                            }
                        }
                    }

                });
            }

        }
        return map;
    }

    /**
     * {@inheritDoc}
     *
     * @see #addAyForAgent(String, String)
     */
    @Transactional
    @Override
    public Boolean addAyForAgent(String ayCode, String agentNum) {
        if (StringUtils.isBlank(ayCode) || StringUtils.isBlank(agentNum)) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            long start = System.currentTimeMillis();
            Long systemTime = servletService.getSystemTime() ;
            AgentIncidentTypePO po = new AgentIncidentTypePO();
            po.setAgentNum(Integer.valueOf(agentNum));
            po.setIncidentType(ayCode);
            po.setSjc(systemTime);
            agentIncidentTypeRepository.save(po);
            Map<String, AgentBean> agents = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            if (agents != null) {
                AgentBean agentBO = agents.get(agentNum);
                DistributeModeBean distribute = agentBO.getDistribute();
                if (distribute == null) {
                    distribute = new DistributeModeBean();
                }
                List<String> accepts = distribute.getAccepts();
                if (accepts == null) {
                    accepts = new ArrayList<>();
                }
                accepts.add(ayCode);
                distribute.setAccepts(accepts);
                List<String> acceptItems = distribute.getAcceptItems();
                if (acceptItems == null) {
                    acceptItems = new ArrayList<>();
                }
//                List<String> causeCodes = causeService.findCauseCodes(Arrays.asList(ayCode));
                List<String> causeCodes = Arrays.asList(ayCode) ; // 消防案件类型只有大类
                acceptItems.addAll(causeCodes);
                distribute.setAcceptItems(acceptItems);
                agentBO.setDistribute(distribute);
            }
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "addAyForAgent", String.format("add ay:%s to agent:%s,total time:%sms", ayCode, agentNum, end - start));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "addAyForAgent", String.format("add ay:%s to agent:%s fail", ayCode, agentNum), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.ADD_CAUSE_TO_AGENT_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #removeAyForAgent(String, String)
     */
    @Override
    public Boolean removeAyForAgent(String ayCode, String agentNum) {
        if (StringUtils.isBlank(ayCode) || StringUtils.isBlank(agentNum)) {
            logService.infoLog(logger, "service", "removeAyForAgent", String.format("data ayCode:%s or agentNum:%s is null", ayCode, agentNum));
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            Map<String, CauseFoAgentBean> map = getCauseFoAgentBO();
            CauseFoAgentBean bo = map.get(ayCode);
            Set<AgentBrieflyBean> agents = bo.getAgents();
            if (agents != null && agents.size() > 0) {
                AgentBrieflyBean agentInfoBO = null;
                for (AgentBrieflyBean infoBO : agents) {
                    if (agentNum.equals(infoBO.getAgentNum())) {
                        agentInfoBO = infoBO;
                    }
                }
                agents.remove(agentInfoBO);
                if (agents.size() < 1) {
                    logService.erorLog(logger, "service", "removeAyForAgent", String.format("remove agnent:%s is the only takeover agent for ay:%s", agentNum, ayCode), null);
                    throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.REMOVE_CAUSE_TO_AGENT_FAIL_NO_AGENT_TAKEOVER);
                }
//                List<String> causeCodes = causeService.findCauseCodes(Arrays.asList(ayCode));
                List<String> causeCodes = Arrays.asList(ayCode) ; // 消防案件类型只有大类
                Map<String, AgentBean> agentBOs = agentCacheService.findAllAgentCache(cacheKeyPrefix);
                AgentIncidentTypePO agentPO = agentIncidentTypeRepository.findByAyAndAgentNum(ayCode, Integer.valueOf(agentNum));
                if (agentPO != null) {
                    agentIncidentTypeRepository.delete(agentPO);
                }
                AgentBean agentBO = agentBOs.get(agentNum);
                DistributeModeBean distribute = agentBO.getDistribute();
                if (distribute != null) {
                    List<String> accepts = distribute.getAccepts();
                    if (accepts != null) {
                        accepts.remove(ayCode);
                        distribute.setAccepts(accepts);
                    }
                    List<String> acceptItems = distribute.getAcceptItems();
                    if (acceptItems != null) {
                        acceptItems.removeAll(causeCodes);
                        distribute.setAcceptItems(acceptItems);
                    }
                    agentBO.setDistribute(distribute);
                }
                agentCacheService.mergeAgentCache("put", agentBO, cacheKeyPrefix, true);
            }
            return true;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "addAyForAgent", String.format("remove ay:%s to agent:%s fail", ayCode, agentNum), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.REMOVE_CAUSE_TO_AGENT_FAIL);
        }
    }




}
