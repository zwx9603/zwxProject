package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.repository.AcceptNativeQueryRepository;
import com.dscomm.iecs.accept.dal.repository.IncidentRepository;
import com.dscomm.iecs.accept.dal.repository.StatisticsRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.firetypebean.InjuriesAndDeathsBean;
import com.dscomm.iecs.accept.graphql.firetypebean.InjuriesAndDeathsNumBean;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.StatisticsService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_DSZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_SJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XXDD;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统计服务实现类
 */
@Component("statisticsServiceImpl")
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);
    private Environment env;
    private GeneralAccessor accessor;
    private LogService logService;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private StatisticsRepository statisticsRepository;
    private ServletService servletService;
    private SystemConfigurationService systemConfigurationService ;
    private AcceptNativeQueryRepository acceptNativeQueryRepository ;
    private IncidentRepository incidentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StatisticsServiceImpl(Environment env, @Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                                 DictionaryService dictionaryService, OrganizationService organizationService,
                                 StatisticsRepository statisticsRepository, ServletService servletService,
                                 SystemConfigurationService systemConfigurationService, AcceptNativeQueryRepository acceptNativeQueryRepository,

                                 IncidentRepository incidentRepository) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.statisticsRepository = statisticsRepository;
        this.servletService = servletService;
        this.systemConfigurationService = systemConfigurationService ;
        this.acceptNativeQueryRepository = acceptNativeQueryRepository ;
        this.incidentRepository = incidentRepository;
    }

    /**
     * {@inheritDoc}
     *
     * @see StatisticsService#findVehicleStatusStatistics( VehicleStatisticsQueryInputInfo )
     */
    @Transactional(readOnly = true)
    @Override
    public DimensionAssembleNestingStatisticsBean findVehicleStatusStatistics(VehicleStatisticsQueryInputInfo inputInfo) {
        if ( null == inputInfo || Strings.isBlank( inputInfo.getScopeSquadronId() ) ) {
            logService.infoLog(logger, "service", "findStatisticsVehicleStatus", "VehicleStatisticsQueryInputInfo or scopeSquadronId is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findStatisticsVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            DimensionAssembleNestingStatisticsBean res = new DimensionAssembleNestingStatisticsBean();
            List<DimensionAssembleNestingBean> beanList = new ArrayList<>();

            String searchPath = inputInfo.getScopeSquadronId() ;
            if (1 == inputInfo.getScopeType()) {
                OrganizationEntity organization = accessor.getById( inputInfo.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                }
            }else{
                searchPath = null ;
            }
            //  -1 0 总队 1 支队 2 大队 3救援站（中队） -1查询全部  其他查询全部
            String natureLike = "" ;
            Integer nature = inputInfo.getNature() ;
            if( null == nature ){
                natureLike = "" ;
            } else if( 0 == nature ){
                natureLike =  ORGANIZATION_NATURE_HEAD_SJZD.getCode() + "%"; //0 总队
            }else if( 1 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_DSZD.getCode() + "%"; //1 支队
            }else if( 2 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_XXDD.getCode() + "%"; //大队
            }else if( 3 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_XJZD.getCode() + "%"; //救援站（中队）
            }

            //获得车辆状态 统计
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleOnDutyStatus)", "repository is started...");
            Long startOnDutyStatus = System.currentTimeMillis();

            List<Object[] > onDutyList= statisticsRepository.findStatisticsVehicleStatus( env.getProperty("WLCLZT")  ,   searchPath ,
                    inputInfo.getKeyword() , inputInfo.getOrganizationIds() , inputInfo.getVehicleTypeCodes() , inputInfo.getVehicleStatusCodes() , natureLike) ;

            Long endOnDutyStatus = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleOnDutyStatus)", String.format("repository is finished,execute time is :%sms", endOnDutyStatus - startOnDutyStatus));

            //根据车辆状态 设置 统计类别（处警中、可调派、公务中、其他）

            //分别计算车辆数量  车辆总数
            Long vehicleTotalCount = 0L;

            //车辆处警中 车辆总数
            Long vehicleOnDutyStatusCount = 0L;
            DimensionAssembleNestingBean ondutyNestingBean = new DimensionAssembleNestingBean();
            ondutyNestingBean.setNestingDimensionCode("vehicleOnDutyStatus");
            ondutyNestingBean.setNestingDimensionName("处警中");
            List<String> ondutyStatusList = new ArrayList<>();
            String vehicleOnDutyStatus =  systemConfigurationService.getSystemConfigByConfigType("vehicleOnDutyStatus").getConfigValue();
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] ondutyStatus = vehicleOnDutyStatus.split(",");
                ondutyStatusList = Arrays.asList(ondutyStatus);
            }

            //车辆可调派 车辆总数
            Long vehicleDispatchStatusCount = 0L;
            DimensionAssembleNestingBean dispatchNestingBean = new DimensionAssembleNestingBean();
            dispatchNestingBean.setNestingDimensionCode("vehicleDispatchStatus");
            dispatchNestingBean.setNestingDimensionName("可调派");
            List<String> dispatchStatusList = new ArrayList<>();
            String vehicleDispatchStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleDispatchStatus").getConfigValue();
            if (Strings.isNotBlank(vehicleDispatchStatus)) {
                String[] dispatchStatus = vehicleDispatchStatus.split(",");
                dispatchStatusList = Arrays.asList(dispatchStatus);
            }

            //不可调 车辆数
            Long vehicleOtherStatusCount = 0L;
            DimensionAssembleNestingBean otherNestingBean = new DimensionAssembleNestingBean();
            otherNestingBean.setNestingDimensionCode("vehicleOtherStatus");
            otherNestingBean.setNestingDimensionName("不可调派");
            List<String> otherStatusList = new ArrayList<>();
            String vehicleOtherStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleOtherStatus").getConfigValue();
            if (Strings.isNotBlank(vehicleOtherStatus)) {
                String[] otherStatus = vehicleOtherStatus.split(",");
                otherStatusList = Arrays.asList(otherStatus);
            }

            //公务中 车辆总数
            Long vehicleBusinessStatusCount = 0L;
            DimensionAssembleNestingBean businessNestingBean = new DimensionAssembleNestingBean();
            businessNestingBean.setNestingDimensionCode("vehicleBusinessStatus");
            businessNestingBean.setNestingDimensionName("公务中");
            List<String> businessStatusList = new ArrayList<>();
            String vehicleBusinessStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleBusinessStatus").getConfigValue();
            if (Strings.isNotBlank(vehicleBusinessStatus)) {
                String[] businessStatu = vehicleBusinessStatus.split(",");
                businessStatusList = Arrays.asList(businessStatu);
            }




            //解析查询结果，分类统计数量
            if (onDutyList != null && onDutyList.size() > 0) {

                for (Object[] objects : onDutyList) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }
                    DimensionAssembleNestingBean bean =  null ;
                    //判断 车辆状态属于具体大类
                    String  vehicleStatusCode =  toString( objects[0]) ;
                    String  vehicleStatusName =  toString( objects[1]) ;
                    Long mainNum = toLong(objects[3] );
                    //车辆处警中 车辆总数
                    if( ondutyStatusList.contains( vehicleStatusCode ) ){
                        bean = new DimensionAssembleNestingBean();
                        bean.setId( vehicleStatusCode  );
                        bean.setParentId( "vehicleOnDutyStatus" );
                        bean.setNestingDimensionCode( vehicleStatusCode );
                        bean.setNestingDimensionName( vehicleStatusName );
                        bean.setNestingParentDimensionCode( "vehicleOnDutyStatus"  );
                        vehicleOnDutyStatusCount = vehicleOnDutyStatusCount +  mainNum ;
                        vehicleTotalCount = vehicleTotalCount +  mainNum ;
                        bean.setNestingDimensionMainNun( toString( mainNum ));
                    }else if (  dispatchStatusList.contains( vehicleStatusCode ) ){
                        bean = new DimensionAssembleNestingBean();
                        bean.setId( vehicleStatusCode  );
                        bean.setParentId( "vehicleDispatchStatus" );
                        bean.setNestingDimensionCode( vehicleStatusCode );
                        bean.setNestingDimensionName( vehicleStatusName );
                        bean.setNestingParentDimensionCode( "vehicleDispatchStatus"  );
                        vehicleDispatchStatusCount = vehicleDispatchStatusCount +  mainNum ;
                        vehicleTotalCount = vehicleTotalCount +  mainNum ;
                        bean.setNestingDimensionMainNun( toString( mainNum ));
                    }else if (  businessStatusList.contains( vehicleStatusCode ) ){
                        bean = new DimensionAssembleNestingBean();
                        bean.setId( vehicleStatusCode );
                        bean.setParentId( "vehicleBusinessStatus" );
                        bean.setNestingDimensionCode( vehicleStatusCode );
                        bean.setNestingDimensionName( vehicleStatusName );
                        bean.setNestingParentDimensionCode( "vehicleBusinessStatus"  );
                        vehicleBusinessStatusCount = vehicleBusinessStatusCount +  mainNum ;
                        vehicleTotalCount = vehicleTotalCount +  mainNum ;
                        bean.setNestingDimensionMainNun( toString( mainNum ));
                    }else if( otherStatusList.contains( vehicleStatusCode  )){
                        bean = new DimensionAssembleNestingBean();
                        bean.setId( vehicleStatusCode  );
                        bean.setParentId( "vehicleOtherStatus" );
                        bean.setNestingDimensionCode( vehicleStatusCode );
                        bean.setNestingDimensionName( vehicleStatusName );
                        bean.setNestingParentDimensionCode( "vehicleOtherStatus"  );
                        vehicleOtherStatusCount = vehicleOtherStatusCount +  mainNum ;
                        vehicleTotalCount = vehicleTotalCount +  mainNum ;
                        bean.setNestingDimensionMainNun( toString( mainNum ));
                    }
                    if( bean != null ){
                        beanList.add( bean ) ;
                    }

                }

                ondutyNestingBean.setNestingDimensionMainNun( String.valueOf( vehicleOnDutyStatusCount ));
                beanList.add( ondutyNestingBean  ) ;
                dispatchNestingBean.setNestingDimensionMainNun( String.valueOf( vehicleDispatchStatusCount ));
                beanList.add( dispatchNestingBean ) ;
                businessNestingBean.setNestingDimensionMainNun( String.valueOf( vehicleBusinessStatusCount ));
                beanList.add( businessNestingBean  ) ;
                otherNestingBean.setNestingDimensionMainNun( String.valueOf( vehicleOtherStatusCount ));
                beanList.add( otherNestingBean  ) ;
            }

            //装配返回结果
            res.setDimensionCount(vehicleTotalCount);
            res.setBeans(beanList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findStatisticsVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findStatisticsVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.STATISTICS_VEHICLE_STATUS_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see StatisticsService#findVehicleTypeStatistics( VehicleStatisticsQueryInputInfo )
     */
    @Transactional(readOnly = true)
    @Override
    public  DimensionAssembleNestingStatisticsBean findVehicleTypeStatistics( VehicleStatisticsQueryInputInfo inputInfo ){
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "findVehicleTypeStatistics", "VehicleStatisticsQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicleTypeStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();

            DimensionAssembleNestingStatisticsBean res = new DimensionAssembleNestingStatisticsBean();
            List<DimensionAssembleNestingBean> beanList = new ArrayList<>();


            String searchPath = inputInfo.getScopeSquadronId() ;
            if (1 == inputInfo.getScopeType()) {
                OrganizationEntity organization = accessor.getById( inputInfo.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                }
            }else{
                searchPath = null ;
            }
            //  -1 0 总队 1 支队 2 大队 3救援站（中队） -1查询全部  其他查询全部
            String natureLike = "" ;
            Integer nature = inputInfo.getNature() ;
            if( null == nature ){
                natureLike = "" ;
            } else if( 0 == nature ){
                natureLike =  ORGANIZATION_NATURE_HEAD_SJZD.getCode() + "%"; //0 总队
            }else if( 1 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_DSZD.getCode() + "%"; //1 支队
            }else if( 2 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_XXDD.getCode() + "%"; //大队
            }else if( 3 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_XJZD.getCode() + "%"; //救援站（中队）
            }
            List<Object[]> queryResultList;
            logService.infoLog(logger, "repository", "findVehicleTypeStatistics", "repository is started...");
            Long start = System.currentTimeMillis();

            queryResultList= statisticsRepository.findVehicleTypeStatistics( env.getProperty("WLCLLX") , searchPath , inputInfo.getKeyword() ,
                    inputInfo.getOrganizationIds() , inputInfo.getVehicleTypeCodes() , inputInfo.getVehicleStatusCodes() , natureLike ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleTypeStatistics", String.format("repository is finished,execute time is :%sms", end - start));


            Long totalCount = 0L;

            //解析查询结果，分类统计数量
            if (queryResultList != null && queryResultList.size() > 0) {

                for (Object[] objects : queryResultList) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }
                    DimensionAssembleNestingBean bean = new DimensionAssembleNestingBean();
                    String id = toString( objects[0]) ;
                    String parentId  = toString( objects[2]) ;
                    String name  = toString( objects[1]) ;
                    if( Strings.isBlank( id ) ){
                        id = "NA" ;
                        parentId = "NA" ;
                        name = "其他" ;
                    }
                    bean.setId( id  );
                    bean.setParentId( parentId );
                    bean.setNestingDimensionCode( id );
                    bean.setNestingDimensionName( name );
                    bean.setNestingParentDimensionCode( parentId );
                    Long mainNum = toLong(objects[3] );
                    totalCount = totalCount + mainNum ;
                    bean.setNestingDimensionMainNun( toString( mainNum ));
                    if (mainNum >0){
                        beanList.add(bean);
                    }
                }
            }

            //装配返回结果
            res.setDimensionCount(totalCount);
            res.setBeans(beanList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleTypeStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleTypeStatistics", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.STATISTICS_VEHICLE_TYPE_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see StatisticsService#findVehicleOrganizationStatistics(VehicleStatisticsQueryInputInfo  )
     */
    @Transactional(readOnly = true)
    @Override
    public DimensionAssembleNestingStatisticsBean findVehicleOrganizationStatistics( VehicleStatisticsQueryInputInfo inputInfo ) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "findVehicleOrganizationStatistics", "VehicleStatisticsQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicleOrganizationStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();

            DimensionAssembleNestingStatisticsBean res = new DimensionAssembleNestingStatisticsBean();
            List<DimensionAssembleNestingBean> beanList = new ArrayList<>();


            String searchPath = inputInfo.getScopeSquadronId() ;
            if (1 == inputInfo.getScopeType()) {
                OrganizationEntity organization = accessor.getById( inputInfo.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                }
            }else{
                searchPath = null ;
            }
            //  -1 0 总队 1 支队 2 大队 3救援站（中队） -1查询全部  其他查询全部
            String natureLike = "" ;
            Integer nature = inputInfo.getNature() ;
            if( null == nature ){
                natureLike = "" ;
            } else if( 0 == nature ){
                natureLike =  ORGANIZATION_NATURE_HEAD_SJZD.getCode() + "%"; //0 总队
            }else if( 1 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_DSZD.getCode() + "%"; //1 支队
            }else if( 2 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_XXDD.getCode() + "%"; //大队
            }else if( 3 == nature){
                natureLike =  ORGANIZATION_NATURE_HEAD_XJZD.getCode() + "%"; //救援站（中队）
            }

            List<Object[]> queryResultList;
            logService.infoLog(logger, "repository", "findVehicleTypeStatistics", "repository is started...");
            Long start = System.currentTimeMillis();

            queryResultList= statisticsRepository.findVehicleOrganizationStatistics( searchPath , inputInfo.getKeyword() ,
                    inputInfo.getOrganizationIds() , inputInfo.getVehicleTypeCodes() , inputInfo.getVehicleStatusCodes() , natureLike ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleTypeStatistics", String.format("repository is finished,execute time is :%sms", end - start));


            Long totalCount = 0L;

            //获得机构名称map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            //解析查询结果，分类统计数量
            if (queryResultList != null && queryResultList.size() > 0) {

                for (Object[] objects : queryResultList) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }
                    DimensionAssembleNestingBean bean = new DimensionAssembleNestingBean();
                    bean.setId( toString( objects[0])  );
                    bean.setParentId( toString( objects[2]) );
                    bean.setNestingDimensionCode( toString( objects[0]));
                    bean.setNestingDimensionName( toString( objects[1]));
                    bean.setNestingParentDimensionCode(toString( objects[2]) );

                    Long mainNum = toLong(objects[3] );
                    totalCount = totalCount + mainNum ;
                    bean.setNestingDimensionMainNun( toString( mainNum ));
                    beanList.add(bean);
                }
            }

            //装配返回结果
            res.setDimensionCount(totalCount);
            res.setBeans(beanList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleOrganizationStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleOrganizationStatistics", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.STATISTICS_VEHICLE_ORGANIZATION_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see StatisticsService#findIncidentTimeTrend(IncidentTrendQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<TimeTrendBean> findIncidentTimeTrend(IncidentTrendQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentTimeTrend", "IncidentTrendQueryInputInfo is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentTimeTrend", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<TimeTrendBean> res = new ArrayList<>();
            Long targetTime = queryBean.getEndTime();
            Long lastUpdataTime = queryBean.getStartTime();
            //获取时间类型、并根据时间类型设置时间段间隔
            DateUtils.FieldType fieldType = transFormDateFieldType(queryBean.getTimeType());
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date lastupdate = new Date(lastUpdataTime);
            cal.setTime(lastupdate);
            if (fieldType == DateUtils.FieldType.MONTH) {
                final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.DAY_OF_MONTH, last);
                lastUpdataTime = cal.getTimeInMillis();
            } else if (fieldType == DateUtils.FieldType.DAY) {
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                lastUpdataTime = cal.getTimeInMillis();
            } else if (fieldType == DateUtils.FieldType.HOUR) {
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                lastUpdataTime = cal.getTimeInMillis();
            }

            //获得警情类型字典信息

            List<DictionaryBean>  ajlxlist =  dictionaryService.findGridDictionary( "AJLX" , false );

            String squadronSearch = null; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    squadronSearch = organization.getSearchPath();
                }
            }

            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {

                while (lastUpdataTime < targetTime) {
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), fieldType, 1).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    List<Object[]> objects = statisticsRepository.findIncidentTypeGroup(lastUpdataTime, endTime,
                            queryBean.getDistrictCode(), squadronSearch);

                    TimeTrendBean bean = new TimeTrendBean();
                    bean.setStartTime(lastUpdataTime);
                    bean.setEndTime(endTime);
                    bean.setDimensionCount(0l);

                    //装配统计结果
                    DimensionAssembleStatisticsBean dimensionAssembleStatisticsBean = transform(objects, ajlxlist);
                    bean.setDimensionCount(dimensionAssembleStatisticsBean.getDimensionCount());
                    bean.setBeans(dimensionAssembleStatisticsBean.getBeans());

                    res.add(bean);
                    lastUpdataTime = endTime;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentTimeTrend", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentTimeTrend", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_TIME_TREND_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see StatisticsService#findIncidentTypeStatistics(IncidentStatisticsQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public DimensionAssembleStatisticsBean findIncidentTypeStatistics(IncidentStatisticsQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentTypeStatistics", "IncidentStatisticsQueryInputInfo is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentTypeStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();
            DimensionAssembleStatisticsBean res = new DimensionAssembleStatisticsBean();

            String searchPath = queryBean.getScopeSquadronId() ; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                }
            }else{
                searchPath = null ;
            }

            //获得警情类型字典信息

            List<DictionaryBean>  ajlxlist =  dictionaryService.findGridDictionary( "AJLX" , false );

            logService.infoLog(logger, "repository", "findStatisticsIncidentType", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> objects = statisticsRepository.findStatisticsIncidentType(searchPath, queryBean.getSquadronIds(), queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), queryBean.getIncidentStateCodes(), queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(),
                    queryBean.getStartTime(), queryBean.getEndTime() , queryBean.getDistrictCodes() );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findStatisticsIncidentType", String.format("repository is finished,execute time is :%sms", end - start));


            res = transform(objects, ajlxlist );


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentTypeStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentTypeStatistics", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_TYPE_STATISTICS_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #findHandleStatistics( HandleStatisticsQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public DimensionAssembleStatisticsBean findHandleStatistics(HandleStatisticsQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findHandleStatistics", "IncidentStatisticsQueryInputInfo is null");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();
            DimensionAssembleStatisticsBean res = new DimensionAssembleStatisticsBean();

            String searchPath = null; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            List<Object[]> countList = null;

            logService.infoLog(logger, "repository", "findHandleStatistics", "repository is started...");
            Long start = System.currentTimeMillis();

            String natureLike =  ORGANIZATION_NATURE_HEAD_XJZD.getCode() + "%"; //救援站（中队）

            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                    countList = acceptNativeQueryRepository.findHandleStatisticsBySearchPath(searchPath + "%", queryBean.getStartTime(), queryBean.getEndTime() , natureLike);
                }
            } else {
                countList = acceptNativeQueryRepository.findHandleStatistics(queryBean.getStartTime(), queryBean.getEndTime() , natureLike);
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleStatistics", String.format("repository is finished,execute time is :%sms", end - start));

            Long count = 0L;

            List<DimensionAssembleBean> beanList = new ArrayList<>();
            if( countList != null && countList.size() >0 ){
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
                for( Object[] obj : countList ){
                    DimensionAssembleBean bean = new DimensionAssembleBean() ;
                    bean.setDimensionCode( toString( obj[0]));
                    bean.setDimensionName( organizationNameMap.get( toString( obj[0]) ));
                    Long countNum  = toLong( obj[1]) ;
                    if( countNum !=null ){
                        count = count + countNum ;
                        bean.setDimensionMainNun( countNum.toString()   );
                    }
                    beanList.add( bean ) ;
                }
//                //升序
//                beanList.sort(new Comparator<DimensionAssembleBean>() {
//                    @Override
//                    public int compare(DimensionAssembleBean o1, DimensionAssembleBean o2) {
//                        Integer num1 = Integer.valueOf(o1.getDimensionMainNun());
//                        Integer num2 = Integer.valueOf(o2.getDimensionMainNun());
//                        return num1.compareTo(num2);
//                    }
//                });

            }

            //装配统计结果
            res.setDimensionCount(count);
            res.setBeans(beanList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleStatistics", "execution error", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_DATA_FAIL);
        }
    }



    /**
     * @param type 类型
     * @return 类型
     */
    private DateUtils.FieldType transFormDateFieldType(String type) {
        switch (type) {
            case "year":
                return DateUtils.FieldType.YEAR;
            case "month":
                return DateUtils.FieldType.MONTH;
            case "date":
                return DateUtils.FieldType.DAY;
            case "time":
                return DateUtils.FieldType.HOUR;
            default:
                return DateUtils.FieldType.HOUR;
        }
    }

    /**
     * 装配警情趋势统计结果
     *
     * @param objects 符合条件的对象
     * @return 警情bean
     */
    private DimensionAssembleStatisticsBean transform(List<Object[]> objects,List<DictionaryBean> incidentTypeList) {
        Long sum = 0l;
        DimensionAssembleStatisticsBean bean = new DimensionAssembleStatisticsBean();
        Map<String, DimensionAssembleBean> maps = new LinkedHashMap<>();

        for (DictionaryBean key : incidentTypeList ) {
            maps.put( key.getCode() , new DimensionAssembleBean( key.getCode() , key.getName(), "0"));
        }
        if (objects != null && objects.size() > 0) {
            List<DimensionAssembleBean> beans = new ArrayList<>();
            for (Object[] object : objects) {
                String typeCode = (String) object[0];
                if (Strings.isBlank(typeCode)) {
                    continue;
                }
                DimensionAssembleBean dimensionAssembleBean = maps.get(typeCode);
                if (dimensionAssembleBean == null) {
                    continue;
                }
                Long number = Strings.isBlank(toString(object[1])) ? 0l : Long.parseLong(toString(object[1]));
                dimensionAssembleBean.setDimensionMainNun(toString(object[1]));
                sum += number;
                beans.add(dimensionAssembleBean);
            }
        }
        bean.setDimensionCount(sum);
        bean.setBeans(new ArrayList<>(maps.values()));
        return bean;
    }




    /**
     * {@inheritDoc}
     *
     * @see StatisticsService#findHandlePowerStatistics( String )
     */
    @Transactional(readOnly = true)
    @Override
    public IncidentPowerStatisticsBean findHandlePowerStatistics(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandlePowerStatistics", "incidentId is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandlePowerStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentPowerStatisticsBean res = null;

            //获取警情量
            logService.infoLog(logger, "repository", "findHandlePowerStatistics", "repository is started...");
            Long incidentStart = System.currentTimeMillis();

            List<Long[]> handlePowerStatistics = acceptNativeQueryRepository.findHandlePowerStatistics(incidentId);

            Long incidentEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandlePowerStatistics", String.format("repository is finished,execute time is :%sms", incidentEnd - incidentStart));

            if (handlePowerStatistics != null && handlePowerStatistics.size() > 0) {
                Object[] objs = handlePowerStatistics.get(0);
                res = new IncidentPowerStatisticsBean();
                res.setHandleBatchNum(toString(objs[0]));
                res.setHandleOrganizationNum(toString(objs[1]));
                res.setHandleVehicleNum(toString(objs[2]));
                res.setHandlePersonNum(toString(objs[3]));
            }
            //统计载水量和载泡沫量
            List<Object[]> vechileMedicament = incidentRepository.countIncidentVechileMedicament(incidentId);
            if (vechileMedicament!=null&&!vechileMedicament.isEmpty()){
                Object[] objs = vechileMedicament.get(0);
                if (objs!=null&&objs.length==2){
                    String totalFoam= IncidentTransformUtil.toString(objs[0]) ;
                    String totalWater= IncidentTransformUtil.toString(objs[1]);
                    res.setTotalFoam(totalFoam);
                    res.setTotalWater(totalWater);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandlePowerStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandlePowerStatistics", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_POWER_STATISTICS_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see StatisticsService#findIncidentTypeContrastStatistics(String, Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public IncidentTypeContrastStatisticsBean findIncidentTypeContrastStatistics(String scopeSquadronId, Integer scopeType) {
        if (Strings.isBlank(scopeSquadronId) || scopeType == null) {
            logService.infoLog(logger, "service", "findIncidentTypeContrastStatistics", "scopeSquadronId is blank or scopeType is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentTypeContrastStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();


            IncidentTypeContrastStatisticsBean res = new IncidentTypeContrastStatisticsBean();

            DimensionAssembleStatisticsBean todayStatistics = new DimensionAssembleStatisticsBean();
            List<DimensionAssembleBean> todayBeanList = new ArrayList<>();

            DimensionAssembleStatisticsBean yesterdayStatistics = new DimensionAssembleStatisticsBean();
            List<DimensionAssembleBean> yesterdayBeanList = new ArrayList<>();

            DimensionAssembleStatisticsBean lastMonthTodayStatistics = new DimensionAssembleStatisticsBean();
            List<DimensionAssembleBean> lastMonthTodayBeanList = new ArrayList<>();

//            DimensionAssembleStatisticsBean currentMonthStatistics = new DimensionAssembleStatisticsBean();
//            List<DimensionAssembleBean> currentMonthBeanList = new ArrayList<>();
//
//            DimensionAssembleStatisticsBean lastMonthStatistics = new DimensionAssembleStatisticsBean();
//            List<DimensionAssembleBean> lastMonthBeanList = new ArrayList<>();
//
//            DimensionAssembleStatisticsBean lastYearCurrentMonthStatistics = new DimensionAssembleStatisticsBean();
//            List<DimensionAssembleBean> lastYearCurrentMonthBeanList = new ArrayList<>();

            Long time = servletService.getSystemTime();

            //设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date(time);
            cal.setTime(timeDate);
            cal.set(Calendar.HOUR_OF_DAY,   0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
            Long minTodayTime = cal.getTimeInMillis();
            Long maxTodayTime = DateUtils.add(new Date(minTodayTime), DateUtils.FieldType.DAY, 1).getTime();
            //昨天数据统计时间段参数（minYesterdayTime - maxYesterdayTime）
            Long minYesterdayTime = DateUtils.add(new Date(minTodayTime), DateUtils.FieldType.DAY, -1).getTime();
            Long maxYesterdayTime = minTodayTime;

            Long lastMonthMinTodayTime = DateUtils.add(new Date(minTodayTime), DateUtils.FieldType.MONTH, -1).getTime();
            Long lastMonthMaxTodayTime = DateUtils.add(new Date(maxTodayTime), DateUtils.FieldType.MONTH, -1).getTime();


//            cal.set(Calendar.DAY_OF_MONTH, 1);
//            //本月数据统计时间段参数（minCurrentMonthTime - maxCurrentMonthTime）
//            Long minCurrentMonthTime = cal.getTimeInMillis();
//            Long maxCurrentMonthTime = DateUtils.add(new Date(minCurrentMonthTime), DateUtils.FieldType.MONTH, 1).getTime();
//            //上月数据统计时间段参数（minLastMonthTime - maxLastMonthTime）
//            Long minLastMonthTime = DateUtils.add(new Date(minCurrentMonthTime), DateUtils.FieldType.MONTH, -1).getTime();
//            Long maxLastMonthTime = minCurrentMonthTime;
//
//            Long lastYearMonthMinTodayTime = DateUtils.add(new Date(minCurrentMonthTime), DateUtils.FieldType.YEAR, -1).getTime();
//            Long lastYearMonthMaxTodayTime = DateUtils.add(new Date(maxCurrentMonthTime), DateUtils.FieldType.YEAR, -1).getTime();


            Long todayTotalCount = 0L;
            Long yesterdayTotalCount = 0L;
            Long lastMonthTodayTotalCount = 0L;

//            Long currentMonthTotalCount = 0L;
//            Long lastMonthTotalCount = 0L;
//            Long lastYearMonthTotalCount = 0L;


            List<Object[]> todayQueryResult = new ArrayList<>();
            List<Object[]> yesterdayQueryResult = new ArrayList<>();
            List<Object[]> lastMonthTodayQueryResult = new ArrayList<>();


//            List<Object[]> currentMonthQueryResult = new ArrayList<>();
//            List<Object[]> lastMonthQueryResult = new ArrayList<>();
//            List<Object[]> lastYearMonthQueryResult = new ArrayList<>();

            String searchPath = scopeSquadronId  ; //机构查询码 0根机构 1非根机构

            logService.infoLog(logger, "repository", "findIncidentTypeContrastStatistics", "repository is started...");
            Long start = System.currentTimeMillis();


            if (1 == scopeType ) {
                OrganizationEntity organization = accessor.getById( scopeSquadronId , OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath() + "%";
                }
                todayQueryResult = statisticsRepository.findIncidentTypeContrastStatisticsBySearchPath(minTodayTime, maxTodayTime, searchPath);
                yesterdayQueryResult = statisticsRepository.findIncidentTypeContrastStatisticsBySearchPath(minYesterdayTime, maxYesterdayTime, searchPath);
                lastMonthTodayQueryResult  = statisticsRepository.findIncidentTypeContrastStatisticsBySearchPath(lastMonthMinTodayTime, lastMonthMaxTodayTime, searchPath);

//                currentMonthQueryResult = statisticsRepository.findIncidentTypeContrastStatisticsBySearchPath(minCurrentMonthTime, maxCurrentMonthTime, searchPath);
//                lastMonthQueryResult = statisticsRepository.findIncidentTypeContrastStatisticsBySearchPath(minLastMonthTime, maxLastMonthTime, searchPath);
//                lastYearMonthQueryResult = statisticsRepository.findIncidentTypeContrastStatisticsBySearchPath(lastYearMonthMinTodayTime, lastYearMonthMaxTodayTime, searchPath);
            }else{
                todayQueryResult = statisticsRepository.findIncidentTypeContrastStatistics(minTodayTime, maxTodayTime );
                yesterdayQueryResult = statisticsRepository.findIncidentTypeContrastStatistics(minYesterdayTime, maxYesterdayTime );
                lastMonthTodayQueryResult  = statisticsRepository.findIncidentTypeContrastStatistics(lastMonthMinTodayTime, lastMonthMaxTodayTime );

//                currentMonthQueryResult = statisticsRepository.findIncidentTypeContrastStatistics(minCurrentMonthTime, maxCurrentMonthTime );
//                lastMonthQueryResult = statisticsRepository.findIncidentTypeContrastStatistics(minLastMonthTime, maxLastMonthTime );
//                lastYearMonthQueryResult = statisticsRepository.findIncidentTypeContrastStatistics(lastYearMonthMinTodayTime, lastYearMonthMaxTodayTime );
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentTypeContrastStatistics", String.format("repository is finished,execute time is :%sms", end - start));

            //处理查询结果

            List<DictionaryBean>  ajlxlist =  dictionaryService.findGridDictionary( "AJLX" , false );
            for( DictionaryBean key  : ajlxlist ){
                DimensionAssembleBean bean = new DimensionAssembleBean();
                bean.setDimensionName(  key.getName() );
                bean.setDimensionCode( key.getCode()  );
                bean.setDimensionMainNun("0");

                if (todayQueryResult != null && todayQueryResult.size() > 0) {
                    for (Object[] objects : todayQueryResult) {
                        if (objects == null || objects.length < 1) {
                            continue;
                        }
                        if( key.getCode().equals( toString(objects[0])  )){
                            bean.setDimensionMainNun(toString(objects[1]));
                            Long count = (Long) objects[1];
                            if (count != null) {
                                todayTotalCount += count;
                            }
                            break;
                        }
                    }
                }
                todayBeanList.add(bean);
            }

            for( DictionaryBean key  : ajlxlist ){
                DimensionAssembleBean bean = new DimensionAssembleBean();
                bean.setDimensionName(  key.getName() );
                bean.setDimensionCode( key.getCode()  );
                bean.setDimensionMainNun("0");
                if (yesterdayQueryResult != null && yesterdayQueryResult.size() > 0) {
                    for (Object[] objects : yesterdayQueryResult) {
                        if (objects == null || objects.length < 1) {
                            continue;
                        }
                        if( key.getCode().equals( toString(objects[0])  )){
                            bean.setDimensionMainNun(toString(objects[1]));
                            Long count = (Long) objects[1];
                            if (count != null) {
                                yesterdayTotalCount += count;
                            }
                            break;

                        }
                    }
                }
                yesterdayBeanList.add(bean);
            }

            for( DictionaryBean key  : ajlxlist ){
                DimensionAssembleBean bean = new DimensionAssembleBean();
                bean.setDimensionName(  key.getName() );
                bean.setDimensionCode( key.getCode()  );
                bean.setDimensionMainNun("0");
                if (lastMonthTodayQueryResult != null && lastMonthTodayQueryResult.size() > 0) {
                    for (Object[] objects : lastMonthTodayQueryResult) {
                        if (objects == null || objects.length < 1) {
                            continue;
                        }
                        if( key.getCode().equals( toString(objects[0])  )){
                            bean.setDimensionMainNun(toString(objects[1]));
                            Long count = (Long) objects[1];
                            if (count != null) {
                                lastMonthTodayTotalCount += count;
                            }
                            break;

                        }
                    }
                }
                lastMonthTodayBeanList.add(bean);
            }


//            for( DictionaryBean key  : ajlxlist ){
//                DimensionAssembleBean bean = new DimensionAssembleBean();
//                bean.setDimensionName(  key.getName() );
//                bean.setDimensionCode( key.getCode()  );
//                bean.setDimensionMainNun("0");
//                if (currentMonthQueryResult != null && currentMonthQueryResult.size() > 0) {
//                    for (Object[] objects : currentMonthQueryResult) {
//
//
//                        if (objects == null || objects.length < 1) {
//                            continue;
//                        }
//                        if( key.getCode().equals( toString(objects[0])  )){
//                            bean.setDimensionMainNun(toString(objects[1]));
//                            Long count = (Long) objects[1];
//                            if (count != null) {
//                                currentMonthTotalCount += count;
//                            }
//                            break;
//                        }
//                    }
//                }
//                currentMonthBeanList.add(bean);
//            }
//            for( DictionaryBean key  : ajlxlist ){
//                DimensionAssembleBean bean = new DimensionAssembleBean();
//                bean.setDimensionName(  key.getName() );
//                bean.setDimensionCode( key.getCode()  );
//                bean.setDimensionMainNun("0");
//                if (lastMonthQueryResult != null && lastMonthQueryResult.size() > 0) {
//                    for (Object[] objects : lastMonthQueryResult) {
//                        if (objects == null || objects.length < 1) {
//                            continue;
//                        }
//                        if( key.getCode().equals( toString(objects[0])  )){
//                            bean.setDimensionMainNun(toString(objects[1]));
//                            Long count = (Long) objects[1];
//                            if (count != null) {
//                                lastMonthTotalCount += count;
//                            }
//                            break;
//                        }
//                    }
//                }
//                lastMonthBeanList.add(bean);
//            }
//
//            for( DictionaryBean key  : ajlxlist ){
//                DimensionAssembleBean bean = new DimensionAssembleBean();
//                bean.setDimensionName(  key.getName() );
//                bean.setDimensionCode( key.getCode()  );
//                bean.setDimensionMainNun("0");
//                if (lastYearMonthQueryResult != null && lastYearMonthQueryResult.size() > 0) {
//                    for (Object[] objects : lastYearMonthQueryResult) {
//                        if (objects == null || objects.length < 1) {
//                            continue;
//                        }
//                        if( key.getCode().equals( toString(objects[0])  )){
//                            bean.setDimensionMainNun(toString(objects[1]));
//                            Long count = (Long) objects[1];
//                            if (count != null) {
//                                lastYearMonthTotalCount += count;
//                            }
//                            break;
//                        }
//                    }
//                }
//                lastYearCurrentMonthBeanList.add(bean);
//            }


            //装配结果
            todayStatistics.setDimensionCount(todayTotalCount);
            todayStatistics.setBeans(todayBeanList);

            yesterdayStatistics.setDimensionCount(yesterdayTotalCount);
            yesterdayStatistics.setBeans(yesterdayBeanList);

            lastMonthTodayStatistics.setDimensionCount(lastMonthTodayTotalCount);
            lastMonthTodayStatistics.setBeans(lastMonthTodayBeanList);

//            lastMonthStatistics.setDimensionCount(lastMonthTotalCount);
//            lastMonthStatistics.setBeans(lastMonthBeanList);
//
//            lastMonthStatistics.setDimensionCount(lastMonthTotalCount);
//            lastMonthStatistics.setBeans(lastMonthBeanList);
//
//            lastYearCurrentMonthStatistics.setDimensionCount(lastYearCurrentMonthBeanList);
//            lastYearCurrentMonthStatistics.setBeans(lastYearCurrentMonthBeanList);


            res.setTodayStatistics(todayStatistics);
            res.setYesterdayStatistics(yesterdayStatistics);
            res.setLastMonthTodayStatistics( lastMonthTodayStatistics );

//            res.setCurrentMonthStatistics(currentMonthStatistics);
//            res.setLastMonthStatistics(lastMonthStatistics);
//            res.setLastYearCurrentMonthStatistics( lastYearCurrentMonthStatistics);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentTypeContrastStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentTypeContrastStatistics", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_TYPE_CONTRAST_STATISTICS_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #findOrganizationDisasterStatistics(IncidentStatisticsQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationDisasterStatisticsBean> findOrganizationDisasterStatistics(IncidentStatisticsQueryInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "findOrganizationDisasterStatistics", "HandleStatisticsQueryInputInfo is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganizationDisasterStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<OrganizationDisasterStatisticsBean> res = new ArrayList<>();

            String  organizationId = inputInfo.getScopeSquadronId() ; //机构ID
            //优先查询结果
            List<OrganizationBean> localChildOrganizations = new ArrayList<>() ;
            if( organizationService.findOrganizationByOrganizationId(organizationId) != null ){
                localChildOrganizations.add(organizationService.findOrganizationByOrganizationId(organizationId));
            }

            //根据机构id查询子级机构
            List<OrganizationBean> childOrganizations = organizationService.findOrganizationChildren(organizationId);

            //如该机构 子级机构、则加入查询
            if(childOrganizations != null && childOrganizations.size() > 0  ){
                localChildOrganizations.addAll(childOrganizations) ;
            }

            //获取案件类型名称map
            List<DictionaryBean>  ajlxlist =  dictionaryService.findGridDictionary( "AJLX" , false );

            //根据子级机构查询码，获取时间段内警情分类统计结果

            logService.infoLog(logger, "repository", "findIncidentTypeContrastStatisticsBySearchPath", "repository is started...");
            Long start = System.currentTimeMillis();


            for (OrganizationBean childOrganization: localChildOrganizations ) {
                OrganizationDisasterStatisticsBean organizationDisasterStatisticsBean = new OrganizationDisasterStatisticsBean();
                organizationDisasterStatisticsBean.setOrganizationId(childOrganization.getId());
                organizationDisasterStatisticsBean.setOrganizationName(childOrganization.getOrganizationName());
                //构建
                Long totalCount = 0L;
                Map<String,DimensionAssembleBean>  dimensionAssembleBeanMap = new LinkedHashMap<>( );
                for( DictionaryBean key :  ajlxlist ){
                    DimensionAssembleBean dimensionAssembleBean = new DimensionAssembleBean();
                    dimensionAssembleBean.setDimensionCode( key.getCode()  );
                    dimensionAssembleBean.setDimensionName( key.getName() );
                    dimensionAssembleBeanMap.put( key.getCode()  , dimensionAssembleBean ) ;
                }
                if(Strings.isNotBlank(childOrganization.getSearchPath())){
                    String searchPath = childOrganization.getSearchPath() + "%";
                    List<Object[]> queryResult = statisticsRepository.findIncidentTypeContrastStatisticsBySearchPath(inputInfo.getStartTime(), inputInfo.getEndTime(),searchPath);
                    if(queryResult != null && queryResult.size() > 0){


                        for (Object[] objects: queryResult) {
                            if (objects == null || objects.length < 1) {
                                continue;
                            }
                            String key = toString(objects[0]) ;
                            DimensionAssembleBean dimensionAssembleBean  = dimensionAssembleBeanMap.get( key  ) ;
                            if( dimensionAssembleBean != null ){
                                dimensionAssembleBean.setDimensionMainNun(toString(objects[1]));
                                totalCount += toLong(objects[1]);
                            }

                        }
                    }
                }
                organizationDisasterStatisticsBean.setDimensionCount(totalCount);
                organizationDisasterStatisticsBean.setDimensionAssembleBeanList(  new ArrayList<>(dimensionAssembleBeanMap.values() ));
                res.add(organizationDisasterStatisticsBean);
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentTypeContrastStatisticsBySearchPath", String.format("repository is finished,execute time is :%sms", end - start));

            //根据警情数量排序
            res.sort( new Comparator<OrganizationDisasterStatisticsBean>() {
                @Override
                public int compare(OrganizationDisasterStatisticsBean o1, OrganizationDisasterStatisticsBean o2) {
                    Long d1 = o1.getDimensionCount();
                    Long d2 = o2.getDimensionCount();
                    return  d2.compareTo(d1);
                }
            } );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationDisasterStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationDisasterStatistics", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_ORGANIZATION_DISASTER_STATISTICS_FALL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #findIncidentDistrictStatistics(Long , Long , String )
     */
    @Transactional(readOnly = true)
    @Override
    public IncidentDistrictStatisticsBean findIncidentDistrictStatistics( Long startTime, Long endTime, String districtCode)  {
        if (startTime == null || endTime == null ) {
            logService.infoLog(logger, "service", "findIncidentDistrictStatistics", "startTime or endTime is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentDistrictStatistics", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentDistrictStatisticsBean res = null ;

            //获得案件类 辖区字点
            //获取案件类型名称map
            List<DictionaryBean>  ajlxlist =  dictionaryService.findGridDictionary( "AJLX" , false );
            //获取行政区划型名称map
            List<DictionaryBean>  xzqhlist = new ArrayList<>( ) ;
            List<DictionaryBean>  xzqhalllist =  dictionaryService.findGridDictionary( "XZQX" , false );
            if( Strings.isNotBlank( districtCode )) {
                for(DictionaryBean dictionaryBean : xzqhalllist){
                   if( districtCode.equals( dictionaryBean.getCode() )){
                       xzqhlist.add( dictionaryBean ) ;
                       break;
                   }
                }
            }else{
                xzqhlist.addAll( xzqhalllist ) ;
            }

            //根据时间段 统计分组
            List<Object[]> queryResult = null ;

            logService.infoLog(logger, "repository", "findIncidentTypeContrastStatisticsBySearchPath", "repository is started...");
            Long start = System.currentTimeMillis();

            if( Strings.isNotBlank( districtCode )){
                queryResult = statisticsRepository.findIncidentDistrictStatistics( startTime , endTime , districtCode ) ;
            }else{
                queryResult = statisticsRepository.findIncidentDistrictStatistics(  startTime , endTime  ) ;
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentTypeContrastStatisticsBySearchPath", String.format("repository is finished,execute time is :%sms", end - start));

            //统计总数
            Long total = 0l ;
            Map<String,Long> districtIncidentTypeNumMap = new HashMap<>() ;
            if(queryResult != null && queryResult.size() > 0){
                for (Object[] objects: queryResult) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }
                    String districtCodeKey = toString(objects[0]) ;
                    String incidentTypeCodeKey  = toString(objects[1]) ;
                    Long statisticsNum =  toLong(objects[2]);
                    total = total + statisticsNum ;

                    //辖区code
                    Long districtMapNum = districtIncidentTypeNumMap.get( districtCodeKey ) ;
                    if( districtMapNum ==  null ){
                        districtMapNum = 0l ;
                    }
                    districtIncidentTypeNumMap.put( districtCodeKey ,districtMapNum + statisticsNum  ) ;
                    //辖区 类型code
                    Long districtIncidetTypeMapNum = districtIncidentTypeNumMap.get( districtCodeKey + "-" +  incidentTypeCodeKey ) ;
                    if( districtIncidetTypeMapNum ==  null ){
                        districtIncidetTypeMapNum = 0l ;
                    }
                    districtIncidentTypeNumMap.put(  districtCodeKey + "-" +  incidentTypeCodeKey , districtIncidetTypeMapNum + statisticsNum  ) ;
                }
                res = new  IncidentDistrictStatisticsBean() ;
                res.setDimensionCount( total );
            }


            //组合数据
            List<DimensionAssembleCodeNameStatisticsBean> dimensionAssembleBeanList = new ArrayList<>( ) ;
            for( DictionaryBean xzqh : xzqhlist ){
                DimensionAssembleCodeNameStatisticsBean   xzqhBean = new DimensionAssembleCodeNameStatisticsBean() ;
                xzqhBean.setDimensionCode( xzqh.getCode() );
                xzqhBean.setDimensionName( xzqh.getName() );
                Long districtNum = districtIncidentTypeNumMap.get (xzqh.getCode() ) ;
                xzqhBean.setDimensionCount( districtNum == null ? 0 : districtNum );
                List<DimensionAssembleBean> beans = new ArrayList<>( ) ;
                for(  DictionaryBean ajlx :   ajlxlist ){
                    DimensionAssembleBean ajlxBean = new DimensionAssembleBean() ;
                    ajlxBean.setDimensionCode( ajlx.getCode() );
                    ajlxBean.setDimensionName( ajlx.getName() );
                    Long districtTypeNum = districtIncidentTypeNumMap.get (  xzqh.getCode() + "-" + ajlx.getCode() ) ;
                    ajlxBean.setDimensionMainNun( districtTypeNum == null ? "0" : districtTypeNum +"" );
                    beans.add( ajlxBean ) ;
                }
                xzqhBean.setBeans( beans );
                dimensionAssembleBeanList.add( xzqhBean ) ;
            }

            res.setDimensionAssembleBeanList( dimensionAssembleBeanList );


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentDistrictStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentDistrictStatistics", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COUNT_INCIDENT_FAIL);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<InjuriesAndDeathsBean> findInjuriesAndDeaths(IncidentTrendQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findInjuriesAndDeaths", "IncidentTrendQueryInputInfo is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findInjuriesAndDeaths", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<InjuriesAndDeathsBean> res = new ArrayList<>();
            Long targetTime = queryBean.getEndTime();
            Long lastUpdataTime = queryBean.getStartTime();
            //获取时间类型、并根据时间类型设置时间段间隔
            DateUtils.FieldType fieldType = transFormDateFieldType(queryBean.getTimeType());
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date lastupdate = new Date(lastUpdataTime);
            cal.setTime(lastupdate);
            if (fieldType == DateUtils.FieldType.MONTH) {
                final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.DAY_OF_MONTH, last);
                lastUpdataTime = cal.getTimeInMillis();
            } else if (fieldType == DateUtils.FieldType.DAY) {
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                lastUpdataTime = cal.getTimeInMillis();
            } else if (fieldType == DateUtils.FieldType.HOUR) {
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                lastUpdataTime = cal.getTimeInMillis();
            }

            //获得警情类型字典信息

            List<DictionaryBean>  ajlxlist =  dictionaryService.findGridDictionary( "AJLX" , false );

            String squadronSearch = null; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    squadronSearch = organization.getSearchPath();
                }
            }

            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {

                while (lastUpdataTime < targetTime) {
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), fieldType, 1).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    List<Object[]> objects = statisticsRepository.findInjuriesAndDeaths(lastUpdataTime, endTime,
                            queryBean.getDistrictCode(), squadronSearch);

                    InjuriesAndDeathsBean bean = new InjuriesAndDeathsBean();
                    bean.setStartTime(lastUpdataTime);
                    bean.setEndTime(endTime);
                    Long injuriesNum  = 0L;
                    Long deathsNum = 0L;
                    if (objects != null && objects.size() > 0) {
                        for (Object[] object : objects) {
                            Object sourceInjuriesNum = object[0];
                            Object sourceDeathsNum = object[1];
                            if(sourceDeathsNum != null){
                                injuriesNum = Long.valueOf(String.valueOf(sourceInjuriesNum));
                            }
                            if(sourceDeathsNum != null){
                                deathsNum = Long.valueOf(String.valueOf(sourceDeathsNum));
                            }
                        }
                    }
                    bean.setInjuriesNum(injuriesNum);
                    bean.setDeathsNum(deathsNum);
                    //装配统计结果
                    InjuriesAndDeathsNumBean injuriesAndDeathsNumBean = null; //= transform(objects, ajlxlist);
                    res.add(bean);
                    lastUpdataTime = endTime;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findInjuriesAndDeaths", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findInjuriesAndDeaths", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_TIME_TREND_FAIL);
        }
    }

    @Override
    public List<StatisticsBean> staticsIncidentVehicles(IncidentVehicleStatisticsInputInfo inputInfo) {
        try {
            List<StatisticsBean> list=new ArrayList<>();
            StringBuilder sbd=new StringBuilder("select t.disposalObjectCode,t.incidentLevelCode,h.vehicleTypeCode,count(h.vehicleTypeCode) " +
                    " from IncidentEntity t left join HandleOrganizationVehicleEntity h on h.incidentId=t.id where t.valid=1 ");
            Long start=null;
            Long end=null;
            if (inputInfo!=null){
                if (inputInfo.getStartTime()!=null){
                    sbd.append(" and t.registerIncidentTime>=").append(inputInfo.getStartTime());
                    start=inputInfo.getStartTime();
                }
                if(inputInfo.getEndTime()!=null){
                    sbd.append(" and t.registerIncidentTime<").append(inputInfo.getEndTime());
                    end=inputInfo.getEndTime();
                }
                if (!StringUtils.isBlank(inputInfo.getDisposalObjectCode())){
                    sbd.append(" and t.disposalObjectCode='").append(inputInfo.getDisposalObjectCode()).append("'");
                }
                if (!StringUtils.isBlank(inputInfo.getIncidentLevelCode())){
                    sbd.append(" and t.incidentLevelCode='").append(inputInfo.getIncidentLevelCode()).append("'");
                }
            }
            sbd.append("group by t.disposalObjectCode,t.incidentLevelCode,h.vehicleTypeCode ");
            Query query = entityManager.createQuery(sbd.toString());
            List<Object[]> result = (List<Object[]>) query.getResultList();
            if (result!=null&&!result.isEmpty()){
                Map<String, Map<String, String>> dictionaryMap = dictionaryService.findDictionaryMap(Arrays.asList("CZDX", "AJDJ","WLCLLX"));
                Map<String, String> czdxMap = dictionaryMap.get("CZDX");
                Map<String, String> ajdjMap = dictionaryMap.get("AJDJ");
                Map<String, String> cllxMap = dictionaryMap.get("WLCLLX");
                Map<String,Map<String,List<IncidentVehicleStatisticsBean>>> map=new HashMap<>();
                for (Object[] res : result) {
                    transform(start, end, czdxMap, ajdjMap, cllxMap, map, res);
                }
                for (String key : map.keySet()) {
                    StatisticsBean bean=new StatisticsBean();
                    Map<String, List<IncidentVehicleStatisticsBean>> temp = map.get(key);
                    bean.setIncidentLevelCode(key);
                    bean.setIncidentLevelName(ajdjMap.get(key));
                    List<IncidentLevelDispatchVehicleBean> levelDispatchVehicleBeans=new ArrayList<>();
                    if (temp!=null){
                        temp.keySet().forEach(czdx->{
                            IncidentLevelDispatchVehicleBean dispatchVehicleBean=new IncidentLevelDispatchVehicleBean();
                            dispatchVehicleBean.setDisposalObjectCode(czdx);
                            dispatchVehicleBean.setDisposalObjectName(czdxMap.get(czdx));
                            List<IncidentVehicleStatisticsBean> statisticsBeans = temp.get(czdx);
                            if (statisticsBeans==null){
                                statisticsBeans=new ArrayList<>();
                            }
                            statisticsBeans.sort((a,b)->b.getVehicleCount().compareTo(a.getVehicleCount()));
                            dispatchVehicleBean.setStaticItems(statisticsBeans);
                            if (statisticsBeans.isEmpty()){
                                dispatchVehicleBean.setVehicleTotal(0);
                            }else {
                                dispatchVehicleBean.setVehicleTotal(statisticsBeans.stream().map(e->e.getVehicleCount()).reduce(Integer::sum).get());
                            }
                            if (!StringUtils.isBlank(dispatchVehicleBean.getDisposalObjectCode())&&!StringUtils.isBlank(dispatchVehicleBean.getDisposalObjectName())){
                                levelDispatchVehicleBeans.add(dispatchVehicleBean);
                            }

                        });
                    }
                    levelDispatchVehicleBeans.sort((a,b)->b.getVehicleTotal().compareTo(a.getVehicleTotal()));
                    bean.setLevelItems(levelDispatchVehicleBeans);
                    bean.setVehicleTotal(levelDispatchVehicleBeans.stream().map(e->e.getVehicleTotal()).reduce(Integer::sum).get());
                    list.add(bean);
                }
                list.sort((a,b)->b.getVehicleTotal().compareTo(a.getVehicleTotal()));

            }
            return list;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "staticsIncidentVehicles", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_VEHICLE_DISPATCH_DISTRIBUTION_FAIL);
        }
    }

    private void transform(Long start, Long end, Map<String, String> czdxMap, Map<String, String> ajdjMap, Map<String, String> cllxMap, Map<String, Map<String, List<IncidentVehicleStatisticsBean>>> map, Object[] res) {
        String disposalObjectCode=IncidentTransformUtil.toString(res[0]);
        String incidentLevelCode=IncidentTransformUtil.toString(res[1]);
        String vehicleTypeCode=IncidentTransformUtil.toString(res[2]);
        Integer vehicleCount=IncidentTransformUtil.toInteger2(res[3]);
        IncidentVehicleStatisticsBean bean=new IncidentVehicleStatisticsBean();
        bean.setDisposalObjectCode(disposalObjectCode);
        if (czdxMap!=null&&czdxMap.containsKey(disposalObjectCode)){
            bean.setDisposalObjectName(czdxMap.get(disposalObjectCode));
        }
        bean.setEndTime(end);
        bean.setIncidentLevelCode(incidentLevelCode);
        if (ajdjMap!=null&&ajdjMap.containsKey(incidentLevelCode)){
            bean.setIncidentLevelName(ajdjMap.get(incidentLevelCode));
        }
        bean.setStartTime(start);
        bean.setVehicleCount(vehicleCount);
        if (StringUtils.isBlank(vehicleTypeCode)){
            bean.setVehicleTypeCode(" ");
        }else {
            bean.setVehicleTypeCode(vehicleTypeCode);
            if (cllxMap!=null&&cllxMap.containsKey(vehicleTypeCode)){
                bean.setVehicleTypeName(cllxMap.get(vehicleTypeCode));
            }
        }
        if (StringUtils.isBlank(bean.getVehicleTypeName())){
            bean.setVehicleTypeName(" ");
        }

        if (!StringUtils.isBlank(bean.getVehicleTypeCode())&&bean.getVehicleCount()>0){
            Map<String, List<IncidentVehicleStatisticsBean>> levelMap = map.get(incidentLevelCode);
            if (levelMap==null){
                levelMap=new HashMap<>();
            }
            List<IncidentVehicleStatisticsBean> beans = levelMap.get(disposalObjectCode);
            if (beans==null){
                beans=new ArrayList<>();
            }
            beans.add(bean);
            levelMap.put(disposalObjectCode,beans);
            map.put(incidentLevelCode,levelMap);
        }

    }

    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private String toString(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }


    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */

    /**
     * 转换为Long类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private static Long toLong(Object obj) {
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
        return  0l ;
    }


}
