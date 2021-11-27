package com.dscomm.iecs.garage.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.garage.dal.repository.OnDutyRepository;
import com.dscomm.iecs.garage.dal.repository.StatisticsRepository;
import com.dscomm.iecs.garage.exception.GarageException;
import com.dscomm.iecs.garage.service.OnDutyService;
import com.dscomm.iecs.garage.service.ServletService;
import com.dscomm.iecs.garage.service.bean.OnDutySummaryBean;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 描述： 值班信息服务实现类
 */
@Component("onDutyServiceImpl")
public class OnDutyServiceImpl implements OnDutyService {
    private static final Logger logger = LoggerFactory.getLogger(OnDutyServiceImpl.class);
    private LogService logService;
    private Environment env;
    private OnDutyRepository onDutyRepository ;
    private ServletService servletService;
    private StatisticsRepository statisticsRepository;

    @Autowired
    public OnDutyServiceImpl( LogService logService , Environment env , OnDutyRepository onDutyRepository , ServletService servletService ,
                             StatisticsRepository statisticsRepository ) {
        this.logService = logService;
        this.env  = env ;
        this.onDutyRepository = onDutyRepository ;
        this.servletService = servletService ;
        this.statisticsRepository = statisticsRepository ;
    }


    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findChildrenOrganizationOnDuty(   String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryBean> findAllOrganizationOnDuty(    String organizationId ) {
        if ( Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findAllOrganizationOnDuty", "startTime is null or endTime is null or organizationId is blank.");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAllOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OnDutySummaryBean> res = new ArrayList<>();

            //今天 设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date( servletService.getSystemTime() );
            cal.setTime(timeDate);
            cal.set(Calendar.HOUR_OF_DAY,   0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
            Long startTime = cal.getTimeInMillis();
            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();


            //获得机构查询码
            String organizationTree = statisticsRepository.findOrganizationTree(organizationId);

            logService.infoLog(logger, "repository", "findAllOrganizationOnDuty", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> ondutySummarys = onDutyRepository.findAllOrganizationOnDuty(  new Date( startTime ), new Date( endTime ) , organizationTree + "%");

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAllOrganizationOnDuty", String.format("repository is finished,execute time is :%sms", end - start));


            if (ondutySummarys != null && ondutySummarys.size() > 0) {
                for (Object[] ondutySummary : ondutySummarys) {
                    OnDutySummaryBean ondutySummaryBean = transform(ondutySummary);
                    res.add(ondutySummaryBean);
                }
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOrganizationOnDuty", "execution error", ex);
            throw new   GarageException(GarageException.GarageErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findOrganizationOnDuty(   String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryBean> findOrganizationOnDuty(   String organizationId) {
        if (  Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findOrganizationOnDuty", "startTime is null or endTime is null or organizationId is blank.");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            //今天 设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date( servletService.getSystemTime() );
            cal.setTime(timeDate);
            cal.set(Calendar.HOUR_OF_DAY,   0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
            Long startTime = cal.getTimeInMillis();
            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();


            logService.infoLog(logger, "repository", "findOrganizationOnDuty", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> ondutySummarys = onDutyRepository.findOrganizationOnDuty( new Date( startTime ), new Date( endTime ) ,  organizationId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationOnDuty", String.format("repository is finished,execute time is :%sms", end - start));

            List<OnDutySummaryBean> res = new ArrayList<>();
            if (ondutySummarys != null && ondutySummarys.size() > 0) {
                for (Object[] ondutySummary : ondutySummarys) {
                    OnDutySummaryBean ondutySummaryBean = transform(ondutySummary);
                    res.add(ondutySummaryBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationOnDuty", "execution error", ex);
            throw new   GarageException(GarageException.GarageErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findChildrenOrganizationOnDuty(   String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryBean> findChildrenOrganizationOnDuty(   String organizationId) {
        if (  Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findChildrenOrganizationOnDuty", "startTime is null or endTime is null or organizationId is blank.");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findChildrenOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OnDutySummaryBean> res = new ArrayList<>();

            //今天 设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date( servletService.getSystemTime() );
            cal.setTime(timeDate);
            cal.set(Calendar.HOUR_OF_DAY,   0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
            Long startTime = cal.getTimeInMillis();
            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();



            logService.infoLog(logger, "repository", "findChildrenOrganizationOnDuty", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> ondutySummarys = onDutyRepository.findChildrenOrganizationOnDuty( new Date( startTime ), new Date( endTime ) ,  organizationId );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findChildrenOrganizationOnDuty", String.format("repository is finished,execute time is :%sms", end - start));

            if (ondutySummarys != null && ondutySummarys.size() > 0) {
                for (Object[] ondutySummary : ondutySummarys) {
                    OnDutySummaryBean ondutySummaryBean = transform(ondutySummary);
                    res.add(ondutySummaryBean);
                }
            }



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findChildrenOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findChildrenOrganizationOnDuty", "execution error", ex);
            throw new   GarageException(GarageException.GarageErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findChildrenOrganizationOnDuty(   String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryBean> findSquadronOrganizationOnDuty(    String organizationId ) {
        if (  Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findSquadronOrganizationOnDuty", "startTime is null or endTime is null or organizationId is blank.");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSquadronOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OnDutySummaryBean> res = new ArrayList<>();

            //今天 设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date( servletService.getSystemTime() );
            cal.setTime(timeDate);
            cal.set(Calendar.HOUR_OF_DAY,   0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
            Long startTime = cal.getTimeInMillis();
            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();


            //获得机构查询码
            String organizationTree = statisticsRepository.findOrganizationTree(organizationId);

            logService.infoLog(logger, "repository", "findSquadronOrganizationOnDuty", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> ondutySummarys = onDutyRepository.findSquadronOrganizationOnDuty( new Date( startTime ), new Date( endTime ) ,  organizationTree + "%");

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSquadronOrganizationOnDuty", String.format("repository is finished,execute time is :%sms", end - start));


            if (ondutySummarys != null && ondutySummarys.size() > 0) {
                for (Object[] ondutySummary : ondutySummarys) {
                    OnDutySummaryBean ondutySummaryBean = transform(ondutySummary);
                    res.add(ondutySummaryBean);
                }
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSquadronOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSquadronOrganizationOnDuty", "execution error", ex);
            throw new   GarageException(GarageException.GarageErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }


    /**
     * 值班信息转换
     *
     * @param source 值班信息PO
     * @return 值班信息BO
     */
    public  OnDutySummaryBean transform( Object []  source) {
        if (null != source) {
            OnDutySummaryBean target = new OnDutySummaryBean();
            target.setId(   toString( source[0])  );
            target.setOrganizationIntegrated(  toString( source[1])  );
            target.setOrganizationId(  toString( source[2])  );
            target.setOrganizationName( toString( source[3])  );
            target.setOnDutyTime(  toDateLong( source[4]) );
            target.setOnDutyType(  toString( source[5]) );
            target.setOnDutyName(  toString( source[6])  );
            target.setResponsibilities( toString( source[7])  );
            target.setOnDutyPersonId( toString( source[8] ) );
            target.setOnDutyPersonNumber(  toString( source[9])  );
            target.setOnDutyPersonName( toString( source[10])  );
            target.setContactNumber( toString( source[11])  );
            target.setOnDutyNumber( toString( source[12])  );
            target.setShowNumber( toInteger( source[13])  );
            return target;
        }
        return null;
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
     * 转换为float类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Integer  toInteger (Object obj) {
        return (obj == null) ? null  : Integer.valueOf( obj.toString() );
    }

    /**
     * 转换为Date类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Date toDate( Object obj ) {
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


}
