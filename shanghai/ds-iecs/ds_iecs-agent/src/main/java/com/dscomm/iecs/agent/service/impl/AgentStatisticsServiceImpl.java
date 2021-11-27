package com.dscomm.iecs.agent.service.impl;

import com.dscomm.iecs.agent.dal.repository.AgentStatisticsRepository;
import com.dscomm.iecs.agent.enums.AgentStateEnum;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.inputbean.MonitorQueryInputInfo;
import com.dscomm.iecs.agent.graphql.typebean.*;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.AgentStatisticsService;
import com.dscomm.iecs.base.enums.BasicEnumNumberBean;
import com.dscomm.iecs.base.enums.BasicEnumNumberUtils;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

/**
 * 统计服务实现类
 */
@Component("agentStatisticsServiceImpl")
public class AgentStatisticsServiceImpl implements AgentStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(AgentStatisticsServiceImpl.class);
    private Environment env;
    private GeneralAccessor accessor;
    private LogService logService;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private AgentStatisticsRepository agentStatisticsRepository;
    private AgentCacheService agentCacheService;
    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;
    private ServletService servletService ;


    @Autowired
    public AgentStatisticsServiceImpl(Environment env, @Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                                      DictionaryService dictionaryService, OrganizationService organizationService, AgentStatisticsRepository agentStatisticsRepository,
                                      AgentCacheService agentCacheService , ServletService servletService
    ) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.agentStatisticsRepository = agentStatisticsRepository;
        this.agentCacheService = agentCacheService;
        this.servletService = servletService ;
    }



    /**
     * {@inheritDoc}
     *
     * @see AgentStatisticsService#findSeatStatusStatistics(String)
     */
    @Transactional(readOnly = true)
    @Override
    public DimensionAssembleStatisticsBean findSeatStatusStatistics(String organizationId) {
        try {
            logService.infoLog(logger, "service", "findSeatStatusStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();
            DimensionAssembleStatisticsBean res = new DimensionAssembleStatisticsBean();
            List<DimensionAssembleBean> dimensionAssembleBeanList = new ArrayList<>();

            //获取坐席状态所有枚举值，设置统计初始值
            List<String> allTypes = BasicEnumNumberUtils.allTypes(AgentStateEnum.class);
            Map<String, Long> typeCountMap = new HashMap<>();
            for (String type : allTypes) {
                Long count = 0L;
                typeCountMap.put(type, count);
            }
            Long totalCount = 0L;

            //获得违规坐席的信息
            typeCountMap.put( "violateInfo" ,  0l ) ;


            //获取所有坐席
            Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);


            //统计
            if (agentBeanMap != null) {
                Set<Map.Entry<String, AgentBean>> entrySet = agentBeanMap.entrySet();
                //判断机构id是否存在
                if( Strings.isNotBlank( organizationId )){
                    //获得 机构以及子机构信息
                    List<String > organizationIds = organizationService.findChildOrganizationId(organizationId) ;

                    for (Map.Entry<String, AgentBean> entry : entrySet) {
                        AgentBean agentBean = entry.getValue();
                        if ( organizationIds.contains( agentBean.getOrganizationCode() ) ) {
                            BasicEnumNumberBean state = agentBean.getAgentState();
                            if (state != null) {
                                Long c = typeCountMap.get(state.getType());
                                typeCountMap.put(state.getType(),c + 1L);
                                totalCount += 1L;
                            }
                            //判断坐席是否存在违规
                            ViolationBean violateInfo = agentBean.getViolateInfo() ;
                            if( violateInfo != null ){
                                Long c = typeCountMap.get("violateInfo");
                                typeCountMap.put("violateInfo",c + 1L);
                            }

                        }
                    }
                }else{
                    for (Map.Entry<String, AgentBean> entry : entrySet) {
                        AgentBean agentBean = entry.getValue();
                        BasicEnumNumberBean state = agentBean.getAgentState();
                        if (state != null) {
                            Long c = typeCountMap.get(state.getType());
                            typeCountMap.put(state.getType(),c + 1L);
                            totalCount += 1L;
                        }
                        //判断坐席是否存在违规
                        ViolationBean violateInfo = agentBean.getViolateInfo() ;
                        if( violateInfo != null ){
                            Long c = typeCountMap.get("violateInfo");
                            typeCountMap.put("violateInfo",c + 1L);
                        }

                    }
                }



            }

            //根据统计结果，组装统计维度bean
            Set<Map.Entry<String, Long>> typeCountEntrySet = typeCountMap.entrySet();
            for (Map.Entry<String, Long> entry : typeCountEntrySet) {
                DimensionAssembleBean dimensionAssembleBean = new DimensionAssembleBean();
                if( "violateInfo".equals( entry.getKey()  )){
                    dimensionAssembleBean.setDimensionCode( "violateInfo" );
                    dimensionAssembleBean.setDimensionName( "违规"  );
                }else{
                    dimensionAssembleBean.setDimensionCode(entry.getKey());
                    dimensionAssembleBean.setDimensionName( BasicEnumNumberUtils.getNameByType( AgentStateEnum.class , entry.getKey()  )   );
                }
                dimensionAssembleBean.setDimensionMainNun(entry.getValue().toString());
                dimensionAssembleBeanList.add(dimensionAssembleBean);
            }

            //装配统计结果
            res.setDimensionCount(totalCount);
            res.setBeans(dimensionAssembleBeanList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSeatStatusStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSeatStatusStatistics", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AgentStatisticsService#findSeatAcceptanceTimeTrend(MonitorQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<TimeTrendBean> findSeatAcceptanceTimeTrend(MonitorQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findSeatAcceptanceTimeTrend", "MonitorQueryInputInfo is null");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSeatAcceptanceTimeTrend", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<TimeTrendBean> res = new ArrayList<>();

            Long targetTime = queryBean.getEndTime();
            Long lastUpdataTime = queryBean.getStartTime();
            //设置时间段间隔为小时
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date lastupdate = new Date(lastUpdataTime);
            cal.setTime(lastupdate);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            lastUpdataTime = cal.getTimeInMillis();

            String searchPath = null; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
            if (organization != null && organization.isValid()) {
                searchPath = organization.getSearchPath();
            }

            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                while (lastUpdataTime < targetTime) {
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), DateUtils.FieldType.HOUR, 1).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    List<Long[]> countList = agentStatisticsRepository.findSeatAcceptanceTimeTrend(lastUpdataTime,
                            endTime, queryBean.getSeatNumber(), queryBean.getPersonNumber(), searchPath);

                    if (countList != null && countList.size() > 0) {
                        //装配统计结果
                        Long count = Optional.ofNullable(countList.get(0)[0]).orElse(0L);
                        DimensionAssembleStatisticsBean dimensionAssembleStatisticsBean = new DimensionAssembleStatisticsBean();
                        dimensionAssembleStatisticsBean.setDimensionCount(count);
                        TimeTrendBean bean = new TimeTrendBean();
                        bean.setDimensionCount(count);
                        bean.setStartTime(lastUpdataTime);
                        bean.setEndTime(endTime);
                        res.add(bean);
                    }
                    lastUpdataTime = endTime;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSeatAcceptanceTimeTrend", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSeatAcceptanceTimeTrend", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see AgentStatisticsService#findSeatIncidentTimeTrend(MonitorQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<TimeTrendBean> findSeatIncidentTimeTrend(MonitorQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findSeatIncidentTimeTrend", "MonitorQueryInputInfo is null");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSeatIncidentTimeTrend", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<TimeTrendBean> res = new ArrayList<>();

            Long targetTime = queryBean.getEndTime();
            Long lastUpdataTime = queryBean.getStartTime();
            //设置时间段间隔为小时
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date lastupdate = new Date(lastUpdataTime);
            cal.setTime(lastupdate);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            lastUpdataTime = cal.getTimeInMillis();

            String searchPath = null; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
            if (organization != null && organization.isValid()) {
                searchPath = organization.getSearchPath();
            }

            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                while (lastUpdataTime < targetTime) {
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), DateUtils.FieldType.HOUR, 1).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }

                    List<Long[]> countList = agentStatisticsRepository.findSeatIncidentTimeTrend(lastUpdataTime,
                            endTime, queryBean.getSeatNumber(), queryBean.getPersonNumber(), searchPath);

                    if (countList != null && countList.size() > 0) {
                        //装配统计结果
                        Long count = Optional.ofNullable(countList.get(0)[0]).orElse(0L);
                        DimensionAssembleStatisticsBean dimensionAssembleStatisticsBean = new DimensionAssembleStatisticsBean();
                        dimensionAssembleStatisticsBean.setDimensionCount(count);
                        TimeTrendBean bean = new TimeTrendBean();
                        bean.setDimensionCount(count);
                        bean.setStartTime(lastUpdataTime);
                        bean.setEndTime(endTime);
                        res.add(bean);
                    }
                    lastUpdataTime = endTime;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSeatIncidentTimeTrend", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSeatIncidentTimeTrend", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AgentStatisticsService#findSeatViolationTrend(MonitorQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<TimeTrendBean> findSeatViolationTrend(MonitorQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findSeatViolationTrend", "MonitorQueryInputInfo is null");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSeatViolationTrend", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<TimeTrendBean> res = new ArrayList<>();

            Long targetTime = queryBean.getEndTime();
            Long lastUpdataTime = queryBean.getStartTime();
            //设置时间段间隔为小时
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date lastupdate = new Date(lastUpdataTime);
            cal.setTime(lastupdate);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            lastUpdataTime = cal.getTimeInMillis();

            String searchPath = null; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
            if (organization != null && organization.isValid()) {
                searchPath = organization.getSearchPath()  ;
            }

            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                while (lastUpdataTime < targetTime) {
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), DateUtils.FieldType.HOUR, 1).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    List<Long[]> countList = agentStatisticsRepository.findSeatViolationTrend(lastUpdataTime,
                            endTime, queryBean.getSeatNumber(), queryBean.getPersonNumber(), searchPath);

                    if (countList != null && countList.size() > 0) {
                        //装配统计结果
                        Long count = Optional.ofNullable(countList.get(0)[0]).orElse(0L);
                        DimensionAssembleStatisticsBean dimensionAssembleStatisticsBean = new DimensionAssembleStatisticsBean();
                        dimensionAssembleStatisticsBean.setDimensionCount(count);
                        TimeTrendBean bean = new TimeTrendBean();
                        bean.setDimensionCount(count);
                        bean.setStartTime(lastUpdataTime);
                        bean.setEndTime(endTime);
                        res.add(bean);
                    }
                    lastUpdataTime = endTime;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSeatViolationTrend", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSeatViolationTrend", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see AgentStatisticsService#findKPIStatistics(MonitorQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public KPIStatisticsBean findKPIStatistics(MonitorQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findKPIStatistics", "MonitorQueryInputInfo is null");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findKPIStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();

            KPIStatisticsBean res = new KPIStatisticsBean();
            res.setAcceptanceTotal(0);
            res.setIncidentTotal(0);
            res.setViolationTotal(0);
            res.setAvgAcceptanceDuration(0l);
            res.setAvgHandleDuration(0l);

            //构建查询开始时间 结束时间

            Long targetTime = queryBean.getEndTime();
            Long lastUpdataTime = queryBean.getStartTime();
            //设置时间段间隔为小时
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date lastupdate = new Date(lastUpdataTime);
            cal.setTime(lastupdate);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            lastUpdataTime = cal.getTimeInMillis();


            //获取违规量
            List<Long[]> violatioResult = agentStatisticsRepository.findViolationCount( lastUpdataTime , targetTime , queryBean.getSeatNumber(), queryBean.getPersonNumber());

            if (violatioResult != null && violatioResult.size() > 0 && violatioResult.get(0) != null) {
                if (violatioResult.get(0).length > 0) {
                    Long violatioCount = violatioResult.get(0)[0];
                    if (violatioCount != null) {
                        res.setViolationTotal(violatioCount.intValue());
                    }
                }

            }
            //获取接警量和平均接警时长
            logService.infoLog(logger, "repository", "findAcceptanceCountAndAvgDuration", "repository is started...");
            Long acceptanceStart = System.currentTimeMillis();

            List<Long[]> acceptanceResult = agentStatisticsRepository.findAcceptanceCountAndAvgDuration(queryBean.getStartTime(), queryBean.getEndTime(), queryBean.getSeatNumber(), queryBean.getPersonNumber());

            Long acceptanceEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAcceptanceCountAndAvgDuration", String.format("repository is finished,execute time is :%sms", acceptanceEnd - acceptanceStart));

            if (acceptanceResult != null && acceptanceResult.size() > 0 && acceptanceResult.get(0) != null) {
                if (acceptanceResult.get(0).length > 0) {
                    Long acceptanceCount = acceptanceResult.get(0)[0];
                    if (acceptanceCount != null) {
                        res.setAcceptanceTotal(acceptanceCount.intValue());
                    }
                }
                if (acceptanceResult.get(0).length > 1) {
                    Long avgAcceptanceDuration = acceptanceResult.get(0)[1];
                    if (avgAcceptanceDuration != null) {
                        res.setAvgAcceptanceDuration(avgAcceptanceDuration );
                    }
                }
            }
            //获取警情量
            logService.infoLog(logger, "repository", "findSeatIncidentTimeTrend", "repository is started...");
            Long incidentStart = System.currentTimeMillis();

            List<Long[]> incidentCountResult = agentStatisticsRepository.findSeatIncidentTimeTrend(queryBean.getStartTime(), queryBean.getEndTime(), queryBean.getSeatNumber(), queryBean.getPersonNumber(), null);

            Long incidentEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSeatIncidentTimeTrend", String.format("repository is finished,execute time is :%sms", incidentEnd - incidentStart));

            if (incidentCountResult != null && incidentCountResult.size() > 0 && incidentCountResult.get(0) != null && incidentCountResult.get(0).length > 0) {
                Long incidentCount = incidentCountResult.get(0)[0];
                if (incidentCount != null) {
                    res.setIncidentTotal(incidentCount.intValue());
                }
            }
            //获取平均处警时长
            logService.infoLog(logger, "repository", "findAvgHandleDuration", "repository is started...");
            Long handleStart = System.currentTimeMillis();

            List<Long[]> handleResult = agentStatisticsRepository.findAvgHandleDuration(queryBean.getStartTime(), queryBean.getEndTime(), queryBean.getSeatNumber(), queryBean.getPersonNumber());

            Long handleEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAvgHandleDuration", String.format("repository is finished,execute time is :%sms", handleEnd - handleStart));

            if (handleResult != null && handleResult.size() > 0 && handleResult.get(0) != null && handleResult.get(0).length > 0) {
                Long avgHandleDuration = handleResult.get(0)[0];
                if (avgHandleDuration != null) {
                    res.setAvgHandleDuration(avgHandleDuration );
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findKPIStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKPIStatistics", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findSeatLogRecord(MonitorQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<LogRecordBean> findSeatLogRecord(MonitorQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findSeatLogRecord", "MonitorQueryInputInfo is null");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSeatLogRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<LogRecordBean> res = new ArrayList<>( );

            //构建查询开始时间 结束时间
            if(  queryBean.getSearchTimeType() == 0 ){
                queryBean.setStartTime( buildDayStartTime( servletService.getSystemTime() ) );
                queryBean.setEndTime( buildDayEndTime( servletService.getSystemTime() ));
            }else{
                queryBean.setStartTime(  bulidSearchTime(  servletService.getSystemTime() , queryBean.getSearchTimeType() )   );
                queryBean.setEndTime( servletService.getSystemTime() );
            }

            //获取登录日志
            logService.infoLog(logger, "repository", "findSeatLogRecord", "repository is started...");
            Long acceptanceStart = System.currentTimeMillis();

            //获登录日志
            List< Object [] > logRecord  = agentStatisticsRepository.findSeatLogRecord(queryBean.getStartTime(), queryBean.getEndTime(), queryBean.getSeatNumber(), queryBean.getPersonNumber());

            Long acceptanceEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSeatLogRecord", String.format("repository is finished,execute time is :%sms", acceptanceEnd - acceptanceStart));

            if (logRecord != null && logRecord.size() > 0  ) {
                //设置时间以及操作类型
                for(  Object [] obj :  logRecord  ){
                    String czlx = toString( obj[3] ) ;
                    if( OperationTypeEnum.OPERATIONTYPE_LOGIN.getCode() ==  Integer.parseInt(  czlx  ) ){
                        LogRecordBean logRecordBean = new LogRecordBean() ;
                        logRecordBean.setPersonName( toString( obj[0] ));
                        logRecordBean.setPersonNumber( toString( obj[1] ));
                        logRecordBean.setLoginTime( toLong( obj[2]) );
                        if( res.size() > 0 ){
                            LogRecordBean  previous  = res.get( res.size() - 1 ) ;
                            if( previous.getLogoutTime() == null ){
                                previous.setLogoutTime(  toLong( obj[2]) );
                                if( previous.getLoginTime() != null  && previous.getLogoutTime() != null ){
                                    previous.setDuration(  previous.getLogoutTime() - previous.getLoginTime()  );
                                }
                            }
                        }
                        res.add( logRecordBean ) ;
                    }else{
                        if( res.size() > 0 ){
                            LogRecordBean logRecordBean  = res.get( res.size() - 1 ) ;
                            logRecordBean.setLogoutTime( toLong( obj[2])  );
                            if( logRecordBean.getLoginTime() != null  && logRecordBean.getLogoutTime() != null ){
                                logRecordBean.setDuration(  logRecordBean.getLogoutTime() - logRecordBean.getLoginTime()  );
                            }
                        }

                    }
                }


            }

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSeatLogRecord", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }









    //构建当天开始时间
    private Long buildDayStartTime( Long currentTime ){
        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        Date currentDate  = new Date(currentTime);
        cal.setTime(currentDate);
        cal.set( Calendar.HOUR_OF_DAY , 0 ) ;
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return  cal.getTimeInMillis();
    }


    //构建当天结束时间
    private Long buildDayEndTime( Long currentTime ){
        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        Date currentDate  = new Date(currentTime);
        cal.setTime(currentDate);
        cal.set( Calendar.HOUR_OF_DAY , 0 ) ;
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date target  = DateUtils.add(new Date( cal.getTimeInMillis() ),  DateUtils.FieldType.DAY , 1) ;
        return  target.getTime() ;
    }

    /**
     * TODO 需要优化
     * 根据 时间查询类型  0 今日  1 24小时 2 一周 3 半月 4 一月 5 半年 查询目标时间
     */
    private Long bulidSearchTime ( Long  start, Integer type  ){
        int offset = 0 ;
        String fieldType = "day" ;
        if( type == 0 ){
            offset = 1 ;
            fieldType = "day" ;
        }else if( type == 1 ) {
            offset = -1 ;
            fieldType = "day" ;
        } else if( type == 2 ) {
            offset = -7 ;
            fieldType = "day" ;
        }else if( type == 3 ) {
            offset = -15 ;
            fieldType = "day" ;
        }else if( type == 4 ) {
            offset = -1 ;
            fieldType = "month" ;
        }else if( type == 5 ) {
            offset = -6 ;
            fieldType = "month" ;
        }

        Date target = DateHandleUtils.addTime( new Date( start ) , fieldType , offset ) ;
        return  target.getTime() ;
    }


    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private   String toString(Object obj) {
        return (obj == null) ? null : obj.toString();
    }




    /**
     * 转换为BigInteger类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private  Integer toInteger(Object obj) {
        if(obj == null ){
            return null  ;
        }else{
            Integer sl = (Integer )obj ;
            return  sl.intValue() ;
        }
    }

    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private  Long toLong(Object obj) {
        if (obj != null) {
            Long count = 0l;
            try {
                count = (Long) obj;
            } catch (ClassCastException e) {
                BigInteger bigInteger = (BigInteger) obj;
                count = bigInteger.longValue();
            }
            return  count.longValue();
        }
        return  0l  ;
    }

}
