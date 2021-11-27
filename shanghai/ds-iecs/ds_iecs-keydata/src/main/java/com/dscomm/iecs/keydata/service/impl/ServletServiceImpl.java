package com.dscomm.iecs.keydata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.keydata.exception.UserInterfaceKeydataException;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述： 服务器 服务类实现
 *
 */
@Component("servletServiceImpl")
public class ServletServiceImpl implements ServletService {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogServiceImpl.class);
    private LogService logService;
    private Environment env;
    private Long timeLive = 0l ;// 应用服务器与数据库相快毫秒数

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ServletServiceImpl(LogService logService , Environment env
    ) {
        this.logService = logService;
        this.env = env ;


    }


    /**
     * 获取系统时间
     *
     * @return 返回统一时时间
     */
    @Transactional(readOnly = true)
    public void updateSystemTime(  ) {
        try {
            logService.infoLog(logger, "service", "getSystemTime", "service is started...");
            Long logStart = System.currentTimeMillis();

            Long res = 0l ;

            if("org.postgresql.Driver".equals( env.getProperty( "db.driver" )   )  ){
                String sql = "select now()";
                Query query = entityManager.createNativeQuery(sql);
                Timestamp dateTime = (Timestamp) query.getSingleResult();
                res =  dateTime.getTime();
            }else if( "oracle.jdbc.driver.OracleDriver".equals( env.getProperty( "db.driver" ) ) ) {
                String sql = "select (TO_NUMBER(sysdate - to_date('1970-01-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss'))*24 * 60 * 60 * 1000) from dual";
                Query query = entityManager.createNativeQuery(sql);
                BigDecimal dateTime = (BigDecimal) query.getSingleResult();
                res = dateTime.longValue();
            }else  if( "com.microsoft.sqlserver.jdbc.SQLServerDriver".equals( env.getProperty( "db.driver" ) )    ) {
                String sql = " select  getdate() ";
                Query query = entityManager.createNativeQuery(sql);
                Timestamp dateTime = (Timestamp) query.getSingleResult();
                res = dateTime.getTime();
            }

            Long currentTime = System.currentTimeMillis() ;

            timeLive = currentTime - res ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger,"service","getSystemTime","get the server systemTime fail.",ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.GET_SYSTEMTIME_FAIL);
        }
    }



    /**
     * 获取系统时间
     *
     * @return 返回统一时时间
     */
    @Transactional(readOnly = true)
    public Long getSystemTime(   ) {
        try {
            logService.infoLog(logger, "service", "getSystemTime", "service is started...");
            Long logStart = System.currentTimeMillis();

            Long currentTime = System.currentTimeMillis() ;

            Long res = currentTime  - timeLive ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger,"service","getSystemTime","get the server systemTime fail.",ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.GET_SYSTEMTIME_FAIL);
        }
    }

    /**
     * 获取系统时间
     *
     * @return 返回统一时时间
     */
    @Transactional(readOnly = true)
    public Date getSystemTimeFormat(    ) {
        try {
            logService.infoLog(logger, "service", "getSystemTimeFormat", "repository is started...");
            Long start = System.currentTimeMillis();

            Long currentTime = this.getSystemTime() ;

            Date res   = new Date( currentTime ) ; ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemTimeFormat", String.format("repository is finished,execute time is :%sms", end- start));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger,"service","getSystemTimeFormat","get the server systemTime fail.",ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.GET_SYSTEMTIME_FAIL);
        }
    }




    /**
     * 获取系统时间
     *
     * @return 返回统一时时间
     */
    @Transactional(readOnly = true)
    public Long getSystemTime(  Integer type  ) {
        try {
            logService.infoLog(logger, "service", "getSystemTime", "service is started...");
            Long logStart = System.currentTimeMillis();

            Long res = 0l ;

            Long currentTime = this.getSystemTime() ;

            if( type == 1 ){
                res = buildDayStartTime(currentTime) ;
            }else if( type == 2 ) {
                res = buildDayEndTime(currentTime) ;
            }else if(  type == 3 ) {
                res = buildDayStartTime(currentTime) - 24 * 60 * 60 * 1000 ;
            }else if(  type == 4 ) {
                res = buildDayEndTime(currentTime) - 24 * 60 * 60 * 1000 ;
            }else{
                 res = currentTime ;
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger,"service","getSystemTime","get the server systemTime fail.",ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.GET_SYSTEMTIME_FAIL);
        }
    }

    /**
     * 获取系统时间
     *
     * @return 返回统一时时间
     */
    @Transactional(readOnly = true)
    public Date getSystemTimeFormat(  Integer type   ) {
        try {
            logService.infoLog(logger, "service", "getSystemTimeFormat", "repository is started...");
            Long start = System.currentTimeMillis();

            Date res = null ;

            Long currentTime = this.getSystemTime() ;
            Long time = 0l ;
            if( type == 1 ){
                time = buildDayStartTime(currentTime) ;
            }else if( type == 2 ) {
                time = buildDayEndTime(currentTime) ;
            }else if(  type == 3 ) {
                time = buildDayStartTime(currentTime) - 24 * 60 * 60 * 1000 ;
            }else if(  type == 4 ) {
                time = buildDayEndTime(currentTime) - 24 * 60 * 60 * 1000 ;
            }else{
                time = currentTime ;
            }
            res = new Date( time ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemTimeFormat", String.format("repository is finished,execute time is :%sms", end- start));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger,"service","getSystemTimeFormat","get the server systemTime fail.",ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.GET_SYSTEMTIME_FAIL);
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


}
