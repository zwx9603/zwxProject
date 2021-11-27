package com.dscomm.iecs.garage.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.garage.exception.GarageException;
import com.dscomm.iecs.garage.service.ServletService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述： 服务器 服务类实现
 *
 */
@Component("servletServiceImpl")
public class ServletServiceImpl implements ServletService {

    private static final Logger logger = LoggerFactory.getLogger(ServletServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor ;
    private Environment env;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ServletServiceImpl(LogService logService , @Qualifier("generalAccessor") GeneralAccessor accessor , Environment env
    ) {
        this.logService = logService;
        this.accessor = accessor ;
        this.env = env ;

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

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger,"service","getSystemTime","get the server systemTime fail.",ex);
            throw new GarageException(GarageException.GarageErrors.GET_SYSTEMTIME_FAIL);
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

            Date res = null ;

            if("org.postgresql.Driver".equals( env.getProperty( "db.driver" )   )  ){
                String sql = "select now()";
                Query query = entityManager.createNativeQuery(sql);
                res = (Timestamp) query.getSingleResult();
            }else if( "oracle.jdbc.driver.OracleDriver".equals( env.getProperty( "db.driver" ) ) ) {
                String sql = "select to_char(sysdate,'yyyy-MM-dd hh24') from dual";
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh");
                Query query = entityManager.createNativeQuery(sql);
                Object dateTimes= query.getSingleResult();
                res=simpleDateFormat.parse(dateTimes.toString());
            }else  if( "com.microsoft.sqlserver.jdbc.SQLServerDriver".equals( env.getProperty( "db.driver" ) )    ){
                String sql = " select  getdate() ";
                Query query = entityManager.createNativeQuery(sql);
                res = (Timestamp) query.getSingleResult();
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemTimeFormat", String.format("repository is finished,execute time is :%sms", end- start));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger,"service","getSystemTimeFormat","get the server systemTime fail.",ex);
            throw new GarageException(GarageException.GarageErrors.GET_SYSTEMTIME_FAIL);
        }
    }


}
