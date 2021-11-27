package com.dscomm.iecs.garage.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.garage.dal.repository.StatisticsRepository;
import com.dscomm.iecs.garage.exception.GarageException;
import com.dscomm.iecs.garage.service.ServletService;
import com.dscomm.iecs.garage.service.StatisticsService;
import com.dscomm.iecs.garage.service.bean.*;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * 描述:统计 服务实现类
 */
@Component
public class StatisticsServiceImpl implements StatisticsService {
    private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);
    private Environment env;
    private LogService logService;
    private StatisticsRepository statisticsRepository;
    private ServletService servletService ;

    @Autowired
    public StatisticsServiceImpl(Environment env, LogService logService,
                                 StatisticsRepository statisticsRepository , ServletService servletService ) {
        this.env = env;
        this.logService = logService;
        this.statisticsRepository = statisticsRepository;
        this.servletService = servletService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see #findStatisticsVehicleStatus(String, Boolean)
     */
    @Override
    public DimensionAssembleNestingStatisticsBean findStatisticsVehicleStatus(String organizationId, Boolean whetherOnlySquadron) {
        if (null == whetherOnlySquadron || Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findStatisticsVehicleStatus", "scopeSquadronId or whetherOnlySquadron is null");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findStatisticsVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            DimensionAssembleNestingStatisticsBean res = new DimensionAssembleNestingStatisticsBean();
            List<DimensionAssembleNestingBean> beanList = new ArrayList<>();

            String scopeSquadronId = organizationId;
            if (!whetherOnlySquadron) {
                String organizationTree = statisticsRepository.findOrganizationTree(organizationId);
                if (Strings.isNotBlank(organizationTree)) {
                    scopeSquadronId = organizationTree;
                }
            }
            //根据车辆状态 设置 统计类别（处警中、可调派、公务中、其他）
            //分别计算车辆数量  车辆总数
            Long vehicleTotalCount = 0L;

            //车辆处警中 车辆总数
            Long vehicleOnDutyStatusCount = 0L;
            DimensionAssembleNestingBean ondutyNestingBean = new DimensionAssembleNestingBean();
            ondutyNestingBean.setNestingDimensionCode("vehicleOnDutyStatus");
            ondutyNestingBean.setNestingDimensionName("作战中");
            List<String> ondutyStatusList = new ArrayList<>();
            String vehicleOnDutyStatus = env.getProperty("vehicleOnDutyStatus");
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] ondutyStatus = vehicleOnDutyStatus.split(",");
                ondutyStatusList = Arrays.asList(ondutyStatus);
            }


            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleOnDutyStatus)", "repository is started...");
            Long startOnDutyStatus = System.currentTimeMillis();

            List<Object[]> onDutyList = statisticsRepository.findStatisticsVehicleStatus(scopeSquadronId, ondutyStatusList, whetherOnlySquadron);

            Long endOnDutyStatus = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleOnDutyStatus)", String.format("repository is finished,execute time is :%sms", endOnDutyStatus - startOnDutyStatus));



            //解析查询结果，分类统计数量
            if (onDutyList != null && onDutyList.size() > 0) {
                //作战中将多余的分类归类到“到场”
                List<String> vehicleOnDutyStatusList = new ArrayList<>();

                String vehicleOnDutyOther = env.getProperty("vehicleOnDutyOther","");
                boolean vehicleOnDutyOtherFlag = Boolean.FALSE;
                Long onDutyOtherCount = 0L;
                if (Strings.isNotBlank(vehicleOnDutyOther)) {
                    String[] businessOtherStatus = vehicleOnDutyOther.split(",");
                    vehicleOnDutyStatusList = Arrays.asList(businessOtherStatus);
                    vehicleOnDutyOtherFlag = Boolean.TRUE;
                }

                for (Object[] objects : onDutyList) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }
                    DimensionAssembleNestingBean bean = new DimensionAssembleNestingBean();
                    bean.setId(toString(objects[0]));
                    bean.setParentId("vehicleOnDutyStatus");
                    bean.setNestingDimensionCode(toString(objects[0]));
                    bean.setNestingDimensionName(toString(objects[1]));
                    bean.setNestingParentDimensionCode("vehicleOnDutyStatus");
                    Long mainNum = toLong(objects[2]);
                    vehicleOnDutyStatusCount = vehicleOnDutyStatusCount + mainNum;
                    vehicleTotalCount = vehicleTotalCount + mainNum;
                    bean.setNestingDimensionMainNun(toString(mainNum));
                    if(vehicleOnDutyOtherFlag && vehicleOnDutyStatusList.contains(toString(objects[0]))){
                        onDutyOtherCount += toLong(objects[2]);
                        continue;
                    }
                    beanList.add(bean);
                }

                //作战中将多余的分类归类到“到场”
                if(vehicleOnDutyOtherFlag){
                    String vehicleOnDutyOtherCode = env.getProperty("vehicleOnDutyOtherCode","");
                    for (DimensionAssembleNestingBean bean : beanList) {
                        if(vehicleOnDutyOtherCode.equals(bean.getNestingDimensionCode())){
                            Long num = Long.valueOf(bean.getNestingDimensionMainNun());
                            num += onDutyOtherCount;
                            bean.setNestingDimensionMainNun(num.toString());
                        }
                    }
                }

                ondutyNestingBean.setNestingDimensionMainNun(String.valueOf(vehicleOnDutyStatusCount));
                beanList.add(ondutyNestingBean);

            }

            //车辆可调派 车辆总数
            Long vehicleDispatchStatusCount = 0L;
            DimensionAssembleNestingBean dispatchNestingBean = new DimensionAssembleNestingBean();
            dispatchNestingBean.setNestingDimensionCode("vehicleDispatchStatus");
            dispatchNestingBean.setNestingDimensionName("可调派");
            List<String> dispatchStatusList = new ArrayList<>();
            String vehicleDispatchStatus = env.getProperty("vehicleDispatchStatus");
            if (Strings.isNotBlank(vehicleDispatchStatus)) {
                String[] dispatchStatus = vehicleDispatchStatus.split(",");
                dispatchStatusList = Arrays.asList(dispatchStatus);
            }
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleDispatchStatus)", "repository is started...");
            Long startDispatch = System.currentTimeMillis();

            List<Object[]> dispatchList = statisticsRepository.findStatisticsVehicleStatus(scopeSquadronId, dispatchStatusList, whetherOnlySquadron);

            Long endDispatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleDispatchStatus)", String.format("repository is finished,execute time is :%sms", endDispatch - startDispatch));

            //解析查询结果，分类统计数量
            if (dispatchList != null && dispatchList.size() > 0) {

                for (Object[] objects : dispatchList) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }
                    DimensionAssembleNestingBean bean = new DimensionAssembleNestingBean();
                    bean.setId(toString(objects[0]));
                    bean.setParentId("vehicleDispatchStatus");
                    bean.setNestingDimensionCode(toString(objects[0]));
                    bean.setNestingDimensionName(toString(objects[1]));
                    bean.setNestingParentDimensionCode("vehicleDispatchStatus");
                    Long mainNum = toLong(objects[2]);
                    vehicleDispatchStatusCount = vehicleDispatchStatusCount + mainNum;
                    vehicleTotalCount = vehicleTotalCount + mainNum;
                    bean.setNestingDimensionMainNun(toString(mainNum));
                    beanList.add(bean);
                }
                dispatchNestingBean.setNestingDimensionMainNun(String.valueOf(vehicleDispatchStatusCount));
                beanList.add(dispatchNestingBean);

            }
            //公务中 车辆总数
            Long vehicleBusinessStatusCount = 0L;
            DimensionAssembleNestingBean businessNestingBean = new DimensionAssembleNestingBean();
            businessNestingBean.setNestingDimensionCode("vehicleBusinessStatus");
            businessNestingBean.setNestingDimensionName("任务中");
            List<String> businessStatusList = new ArrayList<>();
            String vehicleBusinessStatus = env.getProperty("vehicleBusinessStatus");
            if (Strings.isNotBlank(vehicleBusinessStatus)) {
                String[] businessStatu = vehicleBusinessStatus.split(",");
                businessStatusList = Arrays.asList(businessStatu);
            }
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleBusinessStatus)", "repository is started...");
            Long startBusiness = System.currentTimeMillis();

            List<Object[]> businessList = statisticsRepository.findStatisticsVehicleStatus(scopeSquadronId, businessStatusList, whetherOnlySquadron);

            Long endBusiness = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleBusinessStatus)", String.format("repository is finished,execute time is :%sms", endBusiness - startBusiness));

            //公务中将多余的分类归类到“其他”
            List<String> vehicleBusinessOtherStatusList = new ArrayList<>();
            String vehicleBusinessOther = env.getProperty("vehicleBusinessOther","");
            String vehicleBusinessOtherCode = env.getProperty("vehicleBusinessOtherCode","");
            boolean vehicleBusinessOtherOtherFlag = Boolean.FALSE;
            Long vehicleBusinessOtherCount = 0L;
            DimensionAssembleNestingBean vehicleBusinessOtherbean =  new DimensionAssembleNestingBean();
            if (Strings.isNotBlank(vehicleBusinessOther)) {
                String[] vehicleBusinessOtherStatus = vehicleBusinessOther.split(",");
                vehicleBusinessOtherStatusList = Arrays.asList(vehicleBusinessOtherStatus);
                vehicleBusinessOtherOtherFlag = Boolean.TRUE;
                vehicleBusinessOtherbean.setId(vehicleBusinessOtherCode);
                vehicleBusinessOtherbean.setParentId("vehicleBusinessStatus");
                vehicleBusinessOtherbean.setNestingDimensionCode(vehicleBusinessOtherCode);
                vehicleBusinessOtherbean.setNestingDimensionName("其他");
                vehicleBusinessOtherbean.setNestingParentDimensionCode("vehicleBusinessStatus");

            }

            //解析查询结果，分类统计数量
            if (businessList != null && businessList.size() > 0) {
                for (Object[] objects : businessList) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }
                    DimensionAssembleNestingBean bean = new DimensionAssembleNestingBean();
                    bean.setId(toString(objects[0]));
                    bean.setParentId("vehicleBusinessStatus");
                    bean.setNestingDimensionCode(toString(objects[0]));
                    bean.setNestingDimensionName(toString(objects[1]));
                    bean.setNestingParentDimensionCode("vehicleBusinessStatus");
                    Long mainNum = toLong(objects[2]);
                    vehicleBusinessStatusCount = vehicleBusinessStatusCount + mainNum;
                    vehicleTotalCount = vehicleTotalCount + mainNum;
                    bean.setNestingDimensionMainNun(toString(mainNum));
                    if(vehicleBusinessOtherOtherFlag && vehicleBusinessOtherStatusList.contains(toString(objects[0]))){
                        vehicleBusinessOtherCount += toLong(objects[2]);
                        continue;
                    }
                    beanList.add(bean);
                }
                //公务中将多余的分类归类到“其他”
                if(vehicleBusinessOtherOtherFlag){
                    vehicleBusinessOtherbean.setNestingDimensionMainNun(vehicleBusinessOtherCount.toString());
                    beanList.add(vehicleBusinessOtherbean);
                }
                businessNestingBean.setNestingDimensionMainNun(String.valueOf(vehicleBusinessStatusCount));
                beanList.add(businessNestingBean);


            }
            //其他 车辆数
            Long vehicleOtherStatusCount = 0L;
            DimensionAssembleNestingBean otherNestingBean = new DimensionAssembleNestingBean();
            otherNestingBean.setNestingDimensionCode("vehicleOtherStatus");
            otherNestingBean.setNestingDimensionName("不可调派");
            List<String> otherStatusList = new ArrayList<>();
            String vehicleOtherStatus = env.getProperty("vehicleOtherStatus");
            if (Strings.isNotBlank(vehicleOtherStatus)) {
                String[] otherStatus = vehicleOtherStatus.split(",");
                otherStatusList = Arrays.asList(otherStatus);
            }
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleOtherStatus)", "repository is started...");
            Long startOther = System.currentTimeMillis();

            List<Object[]> otherList = statisticsRepository.findStatisticsVehicleStatus(scopeSquadronId, otherStatusList, whetherOnlySquadron);

            Long endOther = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findStatisticsVehicleStatus(vehicleOtherStatus)", String.format("repository is finished,execute time is :%sms", endOther - startOther));

            //解析查询结果，分类统计数量
            if (otherList != null && otherList.size() > 0) {

                for (Object[] objects : otherList) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }

                    DimensionAssembleNestingBean bean = new DimensionAssembleNestingBean();
                    bean.setId(toString(objects[0]));
                    bean.setParentId("vehicleOtherStatus");
                    bean.setNestingDimensionCode(toString(objects[0]));
                    bean.setNestingDimensionName(toString(objects[1]));
                    bean.setNestingParentDimensionCode("vehicleOtherStatus");
                    Long mainNum = toLong(objects[2]);
                    vehicleOtherStatusCount = vehicleOtherStatusCount + mainNum;
                    vehicleTotalCount = vehicleTotalCount + mainNum;
                    bean.setNestingDimensionMainNun(toString(mainNum));
                    beanList.add(bean);
                }
                otherNestingBean.setNestingDimensionMainNun(String.valueOf(vehicleOtherStatusCount));
                beanList.add(otherNestingBean);
            }
            //装配返回结果
            res.setDimensionCount(vehicleTotalCount);
            res.setBeans(beanList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findStatisticsVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findStatisticsVehicleStatus", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.STATISTICS_VEHICLE_STATUS_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findVehicle(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<EquipmentVehicleBean> findVehicle(String organizationId) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findVehicle", "organizationId is blank");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EquipmentVehicleBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findVehicle", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> otherList = statisticsRepository.findVehicle(organizationId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicle", String.format("repository is finished,execute time is :%sms", end - start));

            //解析查询结果
            if (otherList != null && otherList.size() > 0) {
                for (Object[] objects : otherList) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }

                    EquipmentVehicleBean bean = new EquipmentVehicleBean();
                    bean.setId(toString(objects[0]));
                    bean.setVehicleCode(toString(objects[1]));
                    bean.setVehicleName(toString(objects[2]));
                    bean.setVehicleTypeCode(toString(objects[3]));
                    bean.setVehicleTypeName(toString(objects[4]));
                    bean.setVehicleStatusCode(toString(objects[5]));
                    bean.setVehicleStatusName(toString(objects[6]));
                    bean.setVehicleNumber(toString(objects[7]));
                    bean.setGarageCode(toString(objects[8]));
                    bean.setEicdCode(toString(objects[9]));
                    bean.setRoomNumber(toString(objects[10]));
                    bean.setGarageStatusCode(toString(objects[11]));
                    bean.setVehicleGarageStatusCode( toString(objects[12]) );
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicle", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**

     * {@inheritDoc}
     *
     * @see  #findStatisticsVehicleNum(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<VehicleNumBean> findStatisticsVehicleNum(String organizationId ){
        try {
            logService.infoLog(logger, "service", "findStatisticsVehicleNum", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<VehicleNumBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findStatisticsVehicleNum", "repository is started...");
            Long start = System.currentTimeMillis();

            List<String> ondutyStatusList = new ArrayList<>();
            String vehicleOnDutyStatus = env.getProperty("vehicleOnDutyStatus");
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] ondutyStatus = vehicleOnDutyStatus.split(",");
                ondutyStatusList = Arrays.asList(ondutyStatus);
            }

            List<Object[]> otherList= statisticsRepository.findStatisticsVehicleNum( organizationId , ondutyStatusList ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findStatisticsVehicleNum", String.format("repository is finished,execute time is :%sms", end - start));

            //解析查询结果
            if (otherList != null && otherList.size() > 0) {
                for (Object[] source  : otherList) {
                    VehicleNumBean bean = new VehicleNumBean();
                    bean.setOrganizationTree(  toString( source[0])  );
                    bean.setOrganizationId(  toString( source[1]) );
                    bean.setOrganizationName(toString(source[10]));
                    bean.setBrigadeOrganizationId(  toString( source[3]) );
                    bean.setBrigadeOrganizationName(  toString( source[4]) );
                    bean.setDetachmentOrganizationId(  toString( source[5]) );
                    bean.setDetachmentOrganizationName(  toString( source[6]) );
                    bean.setDimensionMainNun(  toString( source[7]) );
                    bean.setDimensionSecondaryNum(  toString( source[8])  );
                    bean.setContactPhone(  toString( source[9])  );
                    bean.setOrganizationShortName(toString(source[10]));
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findStatisticsVehicleNum", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findStatisticsVehicleNum", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**

     * {@inheritDoc}
     *
     * @see  #findStatisticsVehicleOnDutyTrend(String , String , int )
     */
    @Transactional(readOnly = true)
    @Override
    public List<TimeTrendBean> findStatisticsVehicleOnDutyTrend(  String organizationId ,   String timeType  ,  int time    ){

        if ( Strings.isBlank( organizationId) ) {
            logService.infoLog(logger, "service", "findStatisticsVehicleOnDutyTrend", "organizationId is null");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findStatisticsVehicleOnDutyTrend", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<TimeTrendBean> res = new ArrayList<>();
            Long targetTime =  servletService.getSystemTime() ;
            DateUtils.FieldType fieldType = transFormDateFieldType( timeType );
            Long lastUpdataTime = DateUtils.add(new Date(targetTime), fieldType,  Integer.parseInt( "-" + time ) ).getTime();
            //获取时间类型、并根据时间类型设置时间段间隔
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

            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {

                while (lastUpdataTime < targetTime) {
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), fieldType, 1).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    Integer total  = statisticsRepository.findStatisticsVehicleOnDutyTrend(new Date(lastUpdataTime), new Date( endTime ) , organizationId );

                    TimeTrendBean bean = new TimeTrendBean();
                    bean.setStartTime(lastUpdataTime);
                    bean.setEndTime(endTime);
                    bean.setDimensionCount( total );

                    res.add(bean);
                    lastUpdataTime = endTime;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findStatisticsVehicleOnDutyTrend", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findStatisticsVehicleOnDutyTrend", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.FIND_ONDUTY_VEHICLE_TREND_FAIL);
        }

    }



    /**

     * {@inheritDoc}
     *
     * @see  #findStatisticsDispatchVehicleCount(String , Integer ,Integer  )
     */
    @Transactional(readOnly = true)
    @Override
    public List<VehicleNumBean> findStatisticsDispatchVehicleCount(  String organizationId , Integer type,Integer limit){

        if ( Strings.isBlank( organizationId) || type == null || limit == null ) {
            logService.infoLog(logger, "service", "findStatisticsDispatchVehicleCount", "organizationId is blank or type is null or limit is null");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findStatisticsDispatchVehicleCount", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<VehicleNumBean> res = new ArrayList<>();

            //type为 1 本周数据 2 为本月数据 其他默认本周
            Date startTime = null ;
            Date endTime = null ;

            if( type.equals(2)){
                startTime = getTimesMonthmorning()  ;
                endTime =  getTimesMonthnight() ;
            }else{
                startTime = getTimesWeekmorning()  ;
                endTime =  getTimesWeeknight() ;
            }

            List<Object[]> queryRestul = statisticsRepository.findStatisticsDispatchVehicleCount(startTime, endTime, organizationId,limit);

            //解析查询结果
            if (queryRestul != null && queryRestul.size() > 0) {
                for (Object[] source  : queryRestul) {
                    VehicleNumBean bean = new VehicleNumBean();
                    bean.setDimensionMainNun( null  );
                    bean.setDimensionSecondaryNum( toString( source[0])   );
                    bean.setOrganizationName( toString( source[1])  );
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findStatisticsDispatchVehicleCount", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findStatisticsDispatchVehicleCount", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.FIND_STATISTICS_DISPATCH_VEHICLE_COUNT_FAIL);
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

     * {@inheritDoc}
     *
     * @see  #findStatisticsOrganizationGoOutAvgTime(String , int ,int )
     */
    @Transactional(readOnly = true)
    @Override
    public  List<VehicleNumBean> findStatisticsOrganizationGoOutAvgTime(  String organizationId  , int type   ,int top ){

        if ( Strings.isBlank( organizationId) ) {
            logService.infoLog(logger, "service", "findStatisticsOrganizationGoOutAvgTime", "organizationId is null");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findStatisticsOrganizationGoOutAvgTime", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<VehicleNumBean> res = new ArrayList<>();
            //type为 1 本周数据 2 为本月数据 其他默认本周
            Date startTime = null ;
            Date endTime = null ;
            if( type == 2 ){
                startTime = getTimesMonthmorning()  ;
                endTime =  getTimesMonthnight() ;
            }else{
                startTime = getTimesWeekmorning()  ;
                endTime =  getTimesWeeknight() ;
            }

            logService.infoLog(logger, "repository", "findStatisticsOrganizationGoOutAvgTime", "service is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> otherList= statisticsRepository.findStatisticsOrganizationGoOutAvgTime( startTime , endTime , organizationId  ,top ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findStatisticsOrganizationGoOutAvgTime", String.format("repository is finished,execute time is :%sms", end - start));

            //解析查询结果
            if (otherList != null && otherList.size() > 0) {
                for (Object[] source  : otherList) {
                    VehicleNumBean bean = new VehicleNumBean();
                    bean.setOrganizationTree(  toString( source[0])  );
                    bean.setOrganizationId(  toString( source[1]) );
                    bean.setOrganizationName(toString(source[8]));
                    bean.setBrigadeOrganizationId(  toString( source[3]) );
                    bean.setBrigadeOrganizationName(  toString( source[4]) );
                    bean.setDetachmentOrganizationId(  toString( source[5]) );
                    bean.setDetachmentOrganizationName(  toString( source[6]) );
                    //TODO:假数据
                    Double num = 70+Math.random()*(40-1+1);
                    bean.setDimensionMainNun(num.toString());
                    bean.setDimensionSecondaryNum( null );
                    bean.setOrganizationShortName(toString(source[8]));
                    res.add(bean);
                }

            }

            res.sort(new Comparator<VehicleNumBean>() {
                @Override
                public int compare(VehicleNumBean o1, VehicleNumBean o2) {
                    Double num1 = Double.valueOf(o1.getDimensionMainNun());
                    Double num2 = Double.valueOf(o2.getDimensionMainNun());
                    return num1.compareTo(num2);
                }
            });

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findStatisticsOrganizationGoOutAvgTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findStatisticsOrganizationGoOutAvgTime", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.FIND_VEHICLE_DISPATCH_AVG_FAIL);
        }

    }


    // 获得本周一0点时间
    public  Date getTimesWeekmorning() {
        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public  Date getTimesWeeknight() {
        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public  Date getTimesMonthmorning() {
        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public  Date getTimesMonthnight() {
        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }



    /**
     * {@inheritDoc}
     *
     * @see #findOrganization(String)
     */
    @Transactional(readOnly = true)
    @Override
    public OrganizationBean findOrganization(String organizationId) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findOrganization", "organizationId is blank");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            OrganizationBean res = new OrganizationBean();

            logService.infoLog(logger, "repository", "findOrganization", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> queryResults = statisticsRepository.findOrganization(organizationId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganization", String.format("repository is finished,execute time is :%sms", end - start));

            //解析查询结果
            if (queryResults != null && queryResults.size() > 0) {
                Object[] objects = queryResults.get(0);
                if (objects != null || objects.length < 1) {
                    res.setOrganizationCode(toString(objects[0]));
                    res.setOrganizationName(toString(objects[1]));
                    res.setOrganizationShortName(toString(objects[2]));
                    res.setOrganizationAddress(toString(objects[3]));
                    res.setOrganizationDesc(toString(objects[4]));
                    res.setDistrictCode(toString(objects[5]));
                    res.setLongitude(toString(objects[6]));
                    res.setLatitude(toString(objects[7]));
                    res.setHeight(toString(objects[8]));
                    res.setMailCode(toString(objects[9]));
                    res.setFaxNumber(toString(objects[10]));
                    res.setContactPerson(toString(objects[11]));
                    res.setContactPhone(toString(objects[12]));
                    res.setOrganizationTree(toString(objects[13]));
                    res.setRemarks(toString(objects[14]));
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganization", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.FIND_ORGANIZATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findLatestIncident(String, Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public List<IncidentBean> findLatestIncident(String organizationId,Integer limit) {
        if (Strings.isBlank(organizationId) || limit == null) {
            logService.infoLog(logger, "service", "findOrganization", "organizationId or limit is null");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findLatestIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<IncidentBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findLatestIncident", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> queryResults = statisticsRepository.findLatestIncident(organizationId,limit);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findLatestIncident", String.format("repository is finished,execute time is :%sms", end - start));

            //解析查询结果
            if (queryResults != null && queryResults.size() > 0) {
                for (Object[] objects : queryResults) {
                    if (objects == null || objects.length < 1) {
                        continue;
                    }
                    IncidentBean bean = new IncidentBean();
                    bean.setId(toString(objects[0]));
                    bean.setIncidentNumber(toString(objects[1]));
                    bean.setAcceptanceId(toString(objects[2]));
                    bean.setRegisterIncidentTime(toDateLong(objects[3]));
                    bean.setCrimeAddress(toString(objects[4]));
                    bean.setLongitude(toString(objects[5]));
                    bean.setLatitude(toString(objects[6]));
                    bean.setIncidentStatusCode(toString(objects[7]));
                    bean.setIncidentStatusName(toString(objects[8]));
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findLatestIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findLatestIncident", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.FIND_INCIDENT_FAIL);
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
     * 转换为float类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Float  toFloat(Object obj) {
        return (obj == null) ? null  : Float.valueOf( obj.toString() );
    }

    /**
     * 转换为Date类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Date toDate(Object obj ) {
        return (obj == null) ? null : (Timestamp)obj ;
    }

    /**
     * 转换为Date long类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Long toDateLong( Object obj ) {
        return (obj == null) ? null : (  (Timestamp)obj  ).getTime()  ;
    }

    /**
     * 转换为Long类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Long toLong(Object obj) {
        if (obj != null) {
            Integer count = (Integer) obj;
            return count.longValue();
        }
        return 0l;
    }

}
