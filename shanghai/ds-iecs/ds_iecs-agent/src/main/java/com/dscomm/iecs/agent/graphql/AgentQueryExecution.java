package com.dscomm.iecs.agent.graphql;

import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.inputbean.MonitorQueryInputInfo;
import com.dscomm.iecs.agent.graphql.typebean.*;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.AgentStatisticsService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.GroupBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.repository.OrganizationRepository;
import com.dscomm.iecs.basedata.graphql.typebean.AcdBean;
import com.dscomm.iecs.basedata.graphql.typebean.AgentAcdBean;
import com.dscomm.iecs.basedata.graphql.typebean.UserAcdBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.AcdService;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.mx.service.rest.graphql.GraphQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 描述:坐席管理模块graphql查询操作执行类
 *
 * @author YangFuxi
 * Date Time 2019/7/21 22:18
 */
@Component("agentQueryExecution")
public class AgentQueryExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(AgentMutationExecution.class);
    private LogService logService;
    private AgentStatisticsService agentStatisticsService;
    private AgentCacheService agentCacheService;
    private AgentService agentService;
    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;
    private AcdService acdService ;
    private UserService userService ;
    private OrganizationRepository organizationRepository;

    @Autowired
    public AgentQueryExecution(LogService logService , AgentStatisticsService agentStatisticsService,
                               AgentCacheService agentCacheService, AgentService agentService ,
                               AcdService acdService , UserService userService, OrganizationRepository organizationRepository

    ) {
        this.logService = logService;
        this.agentStatisticsService = agentStatisticsService;
        this.agentCacheService = agentCacheService;
        this.agentService = agentService;
        this.acdService = acdService ;
        this.userService = userService ;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public String getTypeName() {
        return "Query";
    }



    @GraphQLFieldAnnotation("findAllAcd")
    public List<AcdBean> findAllAcd(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "findAllAcd", "graphql is started...");
        Long start = System.currentTimeMillis();
        List<AcdBean> acdBeanList = acdService.findAllAcd();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllAcd", String.format("graphql is finished,execute time is :%sms", end - start));
        return acdBeanList;
    }


    /**
     * 查询用户acd
     *
     * @param env 上下文环境变量
     * @return 用户acd列表
     */
    @GraphQLFieldAnnotation("getUserAcd")
    public List<UserAcdBean> getUserAcd(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "getUserAcd", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        String userId = (String) env.getArgument("userId");
        List<UserAcdBean> userAcdBeanList = acdService.findUserAcdBO(userId);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getUserAcd", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return userAcdBeanList;
    }

    /**
     * 查询坐席acd
     *
     * @param env 上下文环境变量
     * @return 坐席acd列表
     */
    @GraphQLFieldAnnotation("getAgentAcd")
    public List<AgentAcdBean> getAgentAcd(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "getAgentAcd", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        String  agentNumber =  env.getArgument("agentNumber");
        List<AgentAcdBean> agentAcdBeanList = acdService.findAgentAcdBO(agentNumber);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getAgentAcd", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return agentAcdBeanList ;
    }



    /**
     * 6.1 根据机构id 获得坐席状态统计数信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSeatStatusStatistics")
    public DimensionAssembleStatisticsBean findSeatStatusStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSeatStatusStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断

        DimensionAssembleStatisticsBean res = agentStatisticsService.findSeatStatusStatistics(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSeatStatusStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }





    /**
     * 6.2 根据时间段 辖区机构id 坐席号 人员工号 获得接警量时间趋势
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSeatAcceptanceTimeTrend")
    public List<TimeTrendBean> findSeatAcceptanceTimeTrend(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSeatAcceptanceTimeTrend", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        MonitorQueryInputInfo queryBean = GraphQLUtils.parse(input, MonitorQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        List<TimeTrendBean> res = agentStatisticsService.findSeatAcceptanceTimeTrend(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSeatAcceptanceTimeTrend", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 6.3 根据时间段  辖区机构id 坐席号 人员工号 获得警情量时间趋势
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSeatIncidentTimeTrend")
    public List<TimeTrendBean> findSeatIncidentTimeTrend(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSeatIncidentTimeTrend", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        MonitorQueryInputInfo queryBean = GraphQLUtils.parse(input, MonitorQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }

        List<TimeTrendBean> res = agentStatisticsService.findSeatIncidentTimeTrend(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSeatIncidentTimeTrend", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 6.4 根据时间段  辖区机构id 坐席号 人员工号 获得违规量时间趋势
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSeatViolationTrend")
    public List<TimeTrendBean> findSeatViolationTrend(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSeatViolationTrend", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        MonitorQueryInputInfo queryBean = GraphQLUtils.parse(input, MonitorQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }

        List<TimeTrendBean> res = agentStatisticsService.findSeatViolationTrend(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSeatViolationTrend", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 6.7 根据时间段 坐席号 人员工号 获得KPI信息包含(接警量、警情量、平均接警时长、平均处警时长、违规量)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findKPIStatistics")
    public KPIStatisticsBean findKPIStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findKPIStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        MonitorQueryInputInfo queryBean = GraphQLUtils.parse(input, MonitorQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        KPIStatisticsBean res = agentStatisticsService.findKPIStatistics(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findKPIStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     *  获得全部坐席缓存信息
     * @param env
     * @return
     */
    @GraphQLFieldAnnotation("getAllAgentCache")
    public List<AgentBean> getAllAgentCache(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "getAllAgentCache", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, AgentBean> allAgentCache = agentCacheService.findAllAgentCache(cacheKeyPrefix);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getAllAgentCache", String.format("graphql is finished,execute time is :%sms", end - start));
        return new ArrayList<>(allAgentCache.values());
    }

    /**
     * 根据坐席台号获取坐席
     *
     * @param environment 上下文环境变量
     * @return 坐席信息
     */
    @GraphQLFieldAnnotation("findAgentByAgentNumber")
    public AgentBean findAgentByAgentNumber(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAgentByAgentNumber", "graphql is started...");
        Long start = System.currentTimeMillis();

        String agentNumber = environment.getArgument("agentNumber");
        //参数判断
        if (Strings.isBlank(agentNumber)) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }

        AgentBean agentBean = agentService.findAgent(agentNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAgentByAgentNumber", String.format("graphql is finished,execute time is :%sms", end - start));
        return agentBean;
    }

    /**
     * 获取所有坐席信息列表
     *
     * @param env 上下文环境变量
     * @return allAgentList:所有坐席信息列表
     */
    @GraphQLFieldAnnotation("findAllAgent")
    public List<AgentBean> findAllAgent(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "findAllAgent", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<AgentBean> allAgentList = agentService.findAllAgent();

        allAgentList.sort(new Comparator<AgentBean>() {
            @Override
            public int compare( AgentBean o1, AgentBean o2 ) {
                Integer d1 = Integer.parseInt( o1.getAgentNumber() )  ;
                Integer d2 = Integer.parseInt( o2.getAgentNumber() )  ;
                return d1.compareTo(d2);
            }
        });

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllAgent", String.format("graphql is finished,execute time is :%sms", end - start));
        return allAgentList;
    }

    /**
     * 获取指定坐席类型的在线坐席列表
     *（不传返回所有）
     * @param env 上下文环境变量
     * @return onlineAgentList:指定坐席类型的在线坐席列表
     */
    @GraphQLFieldAnnotation("findOnlineAgentByAgentType")
    public List<AgentBean> findOnlineAgentByAgentType(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "findOnlineAgentByAgentType", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<String> agentTypes = env.getArgument("agentTypes");

        List<AgentBean> onlineAgentList = agentService.findOnlineAgentByAgentType(agentTypes);

        onlineAgentList.sort(new Comparator<AgentBean>() {
            @Override
            public int compare( AgentBean o1, AgentBean o2 ) {
                Integer d1 = Integer.parseInt( o1.getAgentNumber() )  ;
                Integer d2 = Integer.parseInt( o2.getAgentNumber() )  ;
                return d1.compareTo(d2);
            }
        });

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOnlineAgentByAgentType", String.format("graphql is finished,execute time is :%sms", end - start));
        return onlineAgentList;
    }


    /**
     * 分权分域获取所有坐席
     *
     * @param env 环境变量
     * @return 在线坐席
     */
    @GraphQLFieldAnnotation("findAllAgentByAuthorization")
    public List<AgentBean> findAllAgentByAuthorization(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "findAllOnlineAgentByAuthorization", "graphql is started...");
        Long logStart = System.currentTimeMillis();

        List<AgentBean> tempAgents = agentService.findAllAgentByAuthorization();

        List<GroupBean> groupBeans = groupAgent( tempAgents ) ;

        List<AgentBean> res = new ArrayList<>();
        for( GroupBean groupBean : groupBeans ){
            res.addAll( groupBean.getList() ) ;
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllOnlineAgentByAuthorization", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return res;
    }


    /**
     * 分权分域获取所有在线坐席
     *
     * @param env 环境变量
     * @return 在线坐席
     */
    @GraphQLFieldAnnotation("findAllOnlineAgentByAuthorization")
    public List<AgentBean> findAllOnlineAgentByAuthorization(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "findAllOnlineAgentByAuthorization", "graphql is started...");
        Long logStart = System.currentTimeMillis();

        List<AgentBean> tempAgents = agentService.findAllOnlineAgentByAuthorization();

        List<GroupBean> groupBeans = groupAgent( tempAgents ) ;

        List<AgentBean> res = new ArrayList<>();
        for( GroupBean groupBean : groupBeans ){
            res.addAll( groupBean.getList() ) ;
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllOnlineAgentByAuthorization", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return res;
    }



    /**
     * 分权分域获取所有坐席信息 以单位为条件分组
     *
     * @param env 环境变量
     * @return 在线坐席
     */
    @GraphQLFieldAnnotation("findOrganizationGroupAgentByAuthorization")
    public List<GroupBean> findOrganizationGroupAgentByAuthorization(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "findOrganizationGroupAgentByAuthorization", "graphql is started...");
        Long logStart = System.currentTimeMillis();

        List<GroupBean> res  = new ArrayList<>();

        Integer whetherOnline = env.getArgument("whetherOnline");
        List<AgentBean> tempAgents = new ArrayList<>() ;
        if( whetherOnline == null || whetherOnline ==0  ){
            tempAgents = agentService.findAllAgentByAuthorization()  ;
        }else{
            tempAgents = agentService.findAllOnlineAgentByAuthorization();
        }

        res  =  groupAgent(tempAgents ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationGroupAgentByAuthorization", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return res;
    }


    private List<GroupBean >  groupAgent( List<AgentBean> tempAgents ){
        List<GroupBean> groupBeans = new ArrayList<>() ;
        Map< String , GroupBean<AgentBean> > groupBeanMap = new HashMap<>() ;

        List<String>orgSort = organizationRepository.findOrgOrder();
        for( AgentBean agent : tempAgents ){
            String key = agent.getOrganizationCode()  ;
            GroupBean groupBean = groupBeanMap.get( key ) ;
            //判断是否在 在线标志  0 不在线 1 在线  在线：登录用户机构  不在线：坐席机构
            if( 1 == agent.getOnlineSign() ){
                key = agent.getPersonBean().getPersonOrgId() ;
                groupBean = groupBeanMap.get( key ) ;
                if( groupBean == null ){
                    groupBean = new GroupBean();
                    groupBean.setGroupId( key );
                    groupBean.setGroupName( agent.getOrganizationName() );


                    groupBean.setGroupNum(  orgSort.indexOf(key));
                    //groupBean.setGroupNum(  agent.getOrganizationOrderNum() );
                    List<AgentBean> dataList = new ArrayList<>() ;
                    dataList.add( agent ) ;
                    dataList.sort(new Comparator<AgentBean>() {
                        @Override
                        public int compare( AgentBean o1, AgentBean o2 ) {
                            Integer d1 = Integer.parseInt( o1.getAgentNumber() )  ;
                            Integer d2 = Integer.parseInt( o2.getAgentNumber() )  ;
                            return d1.compareTo(d2);
                        }
                    });
                    groupBean.setList( dataList );
                }else{
                    List<AgentBean> dataList = groupBean.getList() ;
                    dataList.add( agent ) ;
                    dataList.sort(new Comparator<AgentBean>() {
                        @Override
                        public int compare( AgentBean o1, AgentBean o2 ) {
                            Integer d1 = Integer.parseInt( o1.getAgentNumber() )  ;
                            Integer d2 = Integer.parseInt( o2.getAgentNumber() )  ;
                            return d1.compareTo(d2);
                        }
                    });
                }
            }else{
                groupBean = groupBeanMap.get( key ) ;
                if( groupBean == null ){
                    groupBean = new GroupBean();
                    groupBean.setGroupId( key );
                    groupBean.setGroupName( agent.getOrganizationName() );
                    groupBean.setGroupNum(  orgSort.indexOf(key));
                    //groupBean.setGroupNum(  agent.getOrganizationOrderNum() );
                    List<AgentBean> dataList = new ArrayList<>() ;
                    dataList.add( agent ) ;
                    dataList.sort(new Comparator<AgentBean>() {
                        @Override
                        public int compare( AgentBean o1, AgentBean o2 ) {
                            Integer d1 = Integer.parseInt( o1.getAgentNumber() )  ;
                            Integer d2 = Integer.parseInt( o2.getAgentNumber() )  ;
                            return d1.compareTo(d2);
                        }
                    });
                    groupBean.setList( dataList );
                }else{
                    List<AgentBean> dataList = groupBean.getList() ;
                    dataList.add( agent ) ;
                    dataList.sort(new Comparator<AgentBean>() {
                        @Override
                        public int compare( AgentBean o1, AgentBean o2 ) {
                            Integer d1 = Integer.parseInt( o1.getAgentNumber() )  ;
                            Integer d2 = Integer.parseInt( o2.getAgentNumber() )  ;
                            return d1.compareTo(d2);
                        }
                    });

                    groupBean.setList( dataList );
                }
            }
            groupBeanMap.put( key , groupBean  ) ;
        }

        //排序
        groupBeans.addAll(  groupBeanMap.values() ) ;
        groupBeans.sort((a, b) -> a.getGroupNum().compareTo(b.getGroupNum()));


        return  groupBeans ;

    }





    /**
     * 6.1 根据机构id 获得坐席信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findAgentByOrganizationId")
    public List<AgentBean>  findAgentByOrganizationId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAgentByOrganizationId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }

        List<AgentBean>  res = agentService.findAgentByOrganizationId(organizationId);

        res.sort(new Comparator<AgentBean>() {
            @Override
            public int compare( AgentBean o1, AgentBean o2 ) {
                Integer d1 = Integer.parseInt( o1.getAgentNumber() )  ;
                Integer d2 = Integer.parseInt( o2.getAgentNumber() )  ;
                return d2.compareTo(d1);
            }
        });

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAgentByOrganizationId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 6.1 根据机构性质  查询 机构坐席信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findAgentByOrganizationNature")
    public List<OrganizationAgentBean> findAgentByOrganizationNature(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAgentByOrganizationNature", "graphql is started...");
        Long start = System.currentTimeMillis();

        Integer nature = environment.getArgument("nature");
        //参数判断
        List<OrganizationAgentBean> res = agentService.findAgentByOrganizationNature( nature ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAgentByOrganizationNature", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 6.2 根据条件查询上下岗记录
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSeatLogRecord")
    public    List<LogRecordBean>  findSeatLogRecord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSeatLogRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        MonitorQueryInputInfo queryBean = GraphQLUtils.parse(input, MonitorQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        List<LogRecordBean>  res = agentStatisticsService.findSeatLogRecord(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSeatLogRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }





    /**
     * 获取全部已登录用户信息
     *
     * @param environment 环境变量
     * @return 在线坐席
     */
    @GraphQLFieldAnnotation("findAllOnlineUserInfo")
    public List<UserInfo> findAllOnlineUserInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAllOnlineUserInfo", "graphql is started...");
        Long logStart = System.currentTimeMillis();

        List<UserInfo> tempUsers = userService.findAllOnlineUserInfo();

        List<GroupBean> groupBeans = groupUserInfo( tempUsers ) ;

        List<UserInfo> res = new ArrayList<>();
        for( GroupBean groupBean : groupBeans ){
            res.addAll( groupBean.getList() ) ;
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllOnlineUserInfo", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return res ;
    }


    /**
     *  分权分域获取所有非接警用户信息 ( 不包含坐席登录用户信息 )
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("getAllUserInfoByAuthorization")
    public List<UserInfo> getAllOnlineUserInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "getAllOnlineUserInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<UserInfo> tempUsers = userService.getAllUserInfoByAuthorization()  ;

        List<GroupBean> groupBeans = groupUserInfo( tempUsers ) ;

        List<UserInfo> res = new ArrayList<>();
        for( GroupBean groupBean : groupBeans ){
            res.addAll( groupBean.getList() ) ;
        }

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getAllOnlineUserInfo", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }



    /**
     * 分权分域获取所有非接警在线用户( 去掉坐席登录用户信息 )
     *
     * @param environment 环境变量
     * @return 在线坐席
     */
    @GraphQLFieldAnnotation("findAllOnlineUserInfoByAuthorization")
    public List<UserInfo> findAllOnlineUserInfoByAuthorization(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAllOnlineUserInfoByAuthorization", "graphql is started...");
        Long logStart = System.currentTimeMillis();

        List<UserInfo> tempUsers = userService.findAllOnlineUserInfoByAuthorization();

        List<GroupBean> groupBeans = groupUserInfo( tempUsers ) ;

        List<UserInfo> res = new ArrayList<>();
        for( GroupBean groupBean : groupBeans ){
            res.addAll( groupBean.getList() ) ;
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllOnlineUserInfoByAuthorization", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return res ;
    }


    /**
     *  分权分域获取所有非接警用户信息 ( 不包含坐席登录用户信息 ) 以单位为条件分组
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("getOrganizationGroupUserInfoByAuthorization")
    public List<GroupBean> getOrganizationGroupUserInfoByAuthorization(DataFetchingEnvironment environment ) {
        logService.infoLog(logger, "graphql", "getOrganizationGroupUserInfoByAuthorization", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<GroupBean> res  = new ArrayList<>();

        Integer whetherOnline = environment.getArgument("whetherOnline");
        List<UserInfo> tempUsers = new ArrayList<>() ;
        if( whetherOnline == null || whetherOnline ==0  ){
            tempUsers = userService.getAllUserInfoByAuthorization()  ;
        }else{
            tempUsers = userService.findAllOnlineUserInfoByAuthorization();
        }

        res = groupUserInfo( tempUsers ) ;


        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getOrganizationGroupUserInfoByAuthorization", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }


    private List<GroupBean >  groupUserInfo( List<UserInfo> tempUsers ){
        List<GroupBean> groupBeans = new ArrayList<>() ;
        Map< String , GroupBean<UserInfo> > groupBeanMap = new HashMap<>() ;
        List<String>orgSort = organizationRepository.findOrgOrder();
        for( UserInfo userInfo : tempUsers ){
            String key  = userInfo.getOrgId() ;
            GroupBean groupBean = groupBeanMap.get( key ) ;
            if( groupBean == null ){
                groupBean = new GroupBean();
                groupBean.setGroupId( key );
                groupBean.setGroupName( userInfo.getOrgName() );
                groupBean.setGroupNum(  orgSort.indexOf(key));
                //groupBean.setGroupNum(  userInfo.getOrgOrderNum() );
                List<UserInfo> dataList = new ArrayList<>() ;
                dataList.add( userInfo ) ;
                dataList.sort( ( a, b )->  a.getAccount().compareTo(b.getAccount()) );
                groupBean.setList( dataList );
            }else{
                List<UserInfo> dataList = groupBean.getList() ;
                dataList.add( userInfo ) ;
                dataList.sort( ( a, b )->  a.getAccount().compareTo(b.getAccount()) );
                groupBean.setList( dataList );
            }
            groupBeanMap.put( key , groupBean  ) ;
        }
        //排序
        groupBeans.addAll(  groupBeanMap.values() ) ;
        groupBeans.sort((a, b) -> a.getGroupNum().compareTo(b.getGroupNum()));


        return  groupBeans ;

    }


}
