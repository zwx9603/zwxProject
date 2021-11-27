package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.accept.dal.po.ViolationRecordEntity;
import com.dscomm.iecs.accept.dal.repository.ViolationRecordRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.MonitorQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.ViolationSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DimensionAssembleBean;
import com.dscomm.iecs.accept.graphql.typebean.DimensionAssembleStatisticsBean;
import com.dscomm.iecs.accept.graphql.typebean.ViolationRecordBean;
import com.dscomm.iecs.accept.service.ViolationService;
import com.dscomm.iecs.accept.utils.transform.OndutyTransformUtil;
import com.dscomm.iecs.agent.graphql.typebean.ViolationBean;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

/**
 * 描述：违规操作记录 服务类实现
 */
@Component("violationServiceImpl")
public class ViolationServiceImpl implements ViolationService {
    private static final Logger logger = LoggerFactory.getLogger(ViolationServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private ServletService servletService;
    private OrganizationService organizationService;
    private ViolationRecordRepository violationRecordRepository;
    private AgentService agentService;
    private DictionaryService dictionaryService ;

    private List<String> dics;
    /**
     * 默认的构造函数
     */
    @Autowired
    public ViolationServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                Environment env, ServletService servletService, ViolationRecordRepository violationRecordRepository,
                                OrganizationService organizationService,AgentService agentService , DictionaryService dictionaryService
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.servletService = servletService;
        this.organizationService = organizationService;
        this.violationRecordRepository = violationRecordRepository;
        this.agentService = agentService;
        this.dictionaryService = dictionaryService ;

        dics = new ArrayList<>(Arrays.asList("WGLX"    ));
    }


    /**
     * {@inheritDoc}
     *
     * @see ViolationService#saveViolation(ViolationSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ViolationRecordBean saveViolation(ViolationSaveInputInfo queryBean) {
        if (null == queryBean) {
            logService.infoLog(logger, "service", "saveViolation", "ViolationSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveViolation", "service is started...");
            Long logStart = System.currentTimeMillis();

            ViolationRecordBean res = null;

            ViolationRecordEntity violationRecordEntity = OndutyTransformUtil.transform(queryBean, servletService.getSystemTime());

            //更新坐席信息 判断违规状态( 1违规中 ,2结束违规 )
            if( "2".equals( queryBean.getViolationStatus() )){

                agentService.removeAgentViolation(  String.valueOf( queryBean.getViolationSeatNumber()));

            }else  {

                if (null != violationRecordEntity) {
                    logService.infoLog(logger, "repository", "save(dbViolationRecordEntity)", "repository is started...");
                    Long start = System.currentTimeMillis();

                    accessor.save(violationRecordEntity);

                    Long end = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save(dbViolationRecordEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                    //获得机构名称map
                    Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                    //获得警情类型字典信息
                    Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                    res = OndutyTransformUtil.transform(violationRecordEntity,dicsMap , organizationNameMap);

                    ViolationBean violationBean = new ViolationBean();
                    violationBean.setCode( res.getViolationType() == null ? null : String.valueOf(res.getViolationType()) );
                    violationBean.setName(res.getViolationType());
                    violationBean.setViolateTime(res.getViolationTime());
                    agentService.updateAgentViolation(violationBean, String.valueOf(res.getViolationSeatNumber()));
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveViolation", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveViolation", String.format(" save violation  fail by seat number: %s.", JSON.toJSONString(queryBean)), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_VIOLATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findViolationTimeSeatPersonNumber(MonitorQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<ViolationRecordBean> findViolationTimeSeatPersonNumber(MonitorQueryInputInfo queryInputInfo) {
        if (queryInputInfo == null) {
            logService.infoLog(logger, "service", "findViolation", "MonitorQueryInputInfo is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findViolation", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<ViolationRecordBean> res = new PaginationBean();
            List<ViolationRecordBean>  beans  = new ArrayList<>();

            String searchPath = null; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            if( Strings.isNotBlank( queryInputInfo.getScopeSquadronId()) ){
                if( Strings.isNotBlank( queryInputInfo.getScopeSquadronId() )){
                    OrganizationEntity organization = accessor.getById(queryInputInfo.getScopeSquadronId(), OrganizationEntity.class);
                    if (organization != null && organization.isValid()) {
                        searchPath = organization.getSearchPath()  ;
                    }
                }
            }


            if(  queryInputInfo.getSearchTimeType() == 0 ){
                queryInputInfo.setStartTime( buildDayStartTime( servletService.getSystemTime() ) );
                queryInputInfo.setEndTime( buildDayEndTime( servletService.getSystemTime() ));
            }else{
                queryInputInfo.setStartTime(  bulidSearchTime(  servletService.getSystemTime() , queryInputInfo.getSearchTimeType() )   );
                queryInputInfo.setEndTime( servletService.getSystemTime() );
            }

            logService.infoLog(logger, "repository", "findViolationCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<ViolationRecordEntity> violationRecordEntityList = violationRecordRepository.findViolationCondition(
                    queryInputInfo.getStartTime(), queryInputInfo.getEndTime(),searchPath, queryInputInfo.getSeatNumber(), queryInputInfo.getPersonNumber() ,
                    queryInputInfo.getKeyword() , queryInputInfo.getTypeCode() , env.getProperty("WGLX", "") ,
                    queryInputInfo.getWhetherPage() , queryInputInfo.getPagination().getPage(), queryInputInfo.getPagination().getSize() );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findViolationCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (violationRecordEntityList != null && violationRecordEntityList.size() > 0) {
                //获得机构名称map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                //获得警情类型字典信息
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;


                for (ViolationRecordEntity entity : violationRecordEntityList) {
                    ViolationRecordBean bean = OndutyTransformUtil.transform(entity, dicsMap , organizationNameMap);
                    beans.add(bean);
                }
            }

            logService.infoLog(logger, "repository", "findViolationCondition", "repository is started...");
            Long startTotal = System.currentTimeMillis();

            Integer  total  = violationRecordRepository.findViolationConditionTotal(
                    queryInputInfo.getStartTime(), queryInputInfo.getEndTime(), searchPath , queryInputInfo.getSeatNumber(), queryInputInfo.getPersonNumber() ,
                    queryInputInfo.getKeyword() , queryInputInfo.getTypeCode()  , env.getProperty("WGLX", "")  );

            Long endTotal = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findViolationCondition", String.format("repository is finished,execute time is :%sms", endTotal - startTotal));

            Pagination pagination = new Pagination();
            pagination.setPage(queryInputInfo.getPagination().getPage());
            pagination.setSize(queryInputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findViolation", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findViolation", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_VIOLATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findViolationTimeSeatPersonNumber(MonitorQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public DimensionAssembleStatisticsBean findSeatViolationStatistics(MonitorQueryInputInfo queryInputInfo)  {
        if (queryInputInfo == null) {
            logService.infoLog(logger, "service", "findSeatViolationStatistics", "MonitorQueryInputInfo is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findViolation", "service is started...");
            Long logStart = System.currentTimeMillis();

            DimensionAssembleStatisticsBean res = new DimensionAssembleStatisticsBean();

            if(  queryInputInfo.getSearchTimeType() == 0 ){
                queryInputInfo.setStartTime( buildDayStartTime( servletService.getSystemTime() ) );
                queryInputInfo.setEndTime( buildDayEndTime( servletService.getSystemTime() ));
            }else{
                queryInputInfo.setStartTime(  bulidSearchTime(  servletService.getSystemTime() , queryInputInfo.getSearchTimeType() )   );
                queryInputInfo.setEndTime( servletService.getSystemTime() );
            }

            String searchPath = null; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            if( Strings.isNotBlank( queryInputInfo.getScopeSquadronId()  ) ){
                OrganizationEntity organization = accessor.getById(queryInputInfo.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath()  ;
                }
            }

            //获得警情类型字典信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> violationTypeMap = dicsMap.get("WGLX");

            logService.infoLog(logger, "repository", "findViolationCondition", "repository is started...");
            Long startTotal = System.currentTimeMillis();

            List<Object[]> objects  = violationRecordRepository.findStatisticsViolationType(
                    queryInputInfo.getStartTime(), queryInputInfo.getEndTime() , searchPath ,  queryInputInfo.getSeatNumber(), queryInputInfo.getPersonNumber() ,
                    queryInputInfo.getKeyword() , queryInputInfo.getTypeCode() , env.getProperty("WGLX", "")  );

            Long endTotal = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findViolationCondition", String.format("repository is finished,execute time is :%sms", endTotal - startTotal));

            res = transform( objects, violationTypeMap );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSeatViolationStatistics", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSeatViolationStatistics", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_VIOLATION_FAIL);
        }
    }


    /**
     * 装配警情趋势统计结果
     *
     * @param objects 符合条件的对象
     * @return 警情bean
     */
    private DimensionAssembleStatisticsBean transform(List<Object[]> objects, Map<String, String> violationTypeMap) {
        Long sum = 0l;
        DimensionAssembleStatisticsBean bean = new DimensionAssembleStatisticsBean();
        Map<String, DimensionAssembleBean> maps = new HashMap<>();
        Set<String> keys = violationTypeMap.keySet();
        for (String key : keys) {
            maps.put(key, new DimensionAssembleBean(key, violationTypeMap.get(key), "0"));
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
                dimensionAssembleBean.setDimensionCode(typeCode);
                dimensionAssembleBean.setDimensionName(violationTypeMap.get(typeCode));
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


    //构建当天开始时间
    private Long buildDayStartTime( Long currentTime ){
        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        Date currentDate  = new Date(currentTime);
        cal.setTime(currentDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
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
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date target  = DateUtils.add(new Date( cal.getTimeInMillis() ),  DateUtils.FieldType.DAY , 1) ;
        return  target.getTime() ;
    }

    /**
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
        return  0l  ;
    }

}
