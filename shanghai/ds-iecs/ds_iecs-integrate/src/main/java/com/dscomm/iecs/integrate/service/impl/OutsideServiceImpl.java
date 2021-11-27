package com.dscomm.iecs.integrate.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.OutsideVO;
import com.dscomm.iecs.integrate.restful.vo.ReceiveUnTrafficAlarmVO;
import com.dscomm.iecs.integrate.service.OutsideService;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.client.rest.RestInvokeException;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



/**
 * 描述：外部接口 服务 实现类
 */
@Component("outsideServiceImpl")
public class OutsideServiceImpl implements OutsideService {

    private static final Logger logger = LoggerFactory.getLogger(OutsideServiceImpl.class);
    private Environment env;
    private LogService logService;
    private String acceptUrl ;
    private RestClientInvoke restClientInvoke = null ;
    /**
     * 默认的构造函数
     */
    @Autowired
    public OutsideServiceImpl(LogService logService,   Environment env
    ) {
        this.env = env;
        this.logService = logService;
        this.acceptUrl = env.getProperty("acceptUrl");
        this.restClientInvoke = new RestClientInvoke();
    }

    /**
     * {@inheritDoc}
     *
     * @see #transferOutIncident(OutsideVO)
     */
    @Transactional(readOnly = true)
    @Override
    public  Boolean transferOutIncident(  OutsideVO queryBean ) {
        if (  null == queryBean  || Strings.isBlank( queryBean.getIncidentId() )) {
            logService.infoLog(logger, "service", "transferOutIncident", " OutsideInputInfo or incidentId is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "transferOutIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false   ;

            //项目 具体实现

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "transferOutIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "transferOutIncident", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REQUEST_OUT_SIDE_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #dislocationIncident(OutsideVO)
     */
    @Transactional(readOnly = true)
    @Override
    public  Boolean dislocationIncident(  OutsideVO queryBean ) {
        if (  null == queryBean  || Strings.isBlank( queryBean.getIncidentId() )) {
            logService.infoLog(logger, "service", "dislocationIncident", " OutsideInputInfo or incidentId is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "dislocationIncident", "service is started...");
            Long logStart = System.currentTimeMillis();


            Boolean res = false   ;

            //项目 具体实现


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "dislocationIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "dislocationIncident", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REQUEST_OUT_SIDE_FAIL);
        }

    }




    /**
     * {@inheritDoc}
     *
     * @see #assistIncident(OutsideVO)
     */
    @Transactional(readOnly = true)
    @Override
    public  Boolean assistIncident(  OutsideVO queryBean ) {
        if (  null == queryBean  || Strings.isBlank( queryBean.getIncidentId() )) {
            logService.infoLog(logger, "service", "assistIncident", " OutsideInputInfo or incidentId is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "assistIncident", "service is started...");
            Long logStart = System.currentTimeMillis();


            Boolean res = false   ;

            //项目 具体实现

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "assistIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "assistIncident", "execution error", ex);
            throw new IntegrateException( IntegrateException.IntegrateErrors.REQUEST_OUT_SIDE_FAIL ) ;
        }

    }





    /**
     * {@inheritDoc}
     *
     * @see #unTrafficAlarm(ReceiveUnTrafficAlarmVO)
     */
    @Transactional(readOnly = true)
    @Override
    public  String unTrafficAlarm ( ReceiveUnTrafficAlarmVO queryBean ) {
        if (  null == queryBean   ) {
            logService.writeLog(logger, "service", "unTrafficAlarm", " OutsideInputInfo or incidentId is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "unTrafficAlarm", "service is started...");
            Long logStart = System.currentTimeMillis();

            String res = ""   ;

            try {
                DataVO resultType =   restClientInvoke.post(String.format("%s/rest/iecs/v1.0/outside/unTrafficAlarm", acceptUrl),
                        queryBean, DataVO.class);
                //判断 是否成功 0成功 非0 不成功
                if( resultType.getErrorCode() == 0){
                    res = ( String ) resultType.getData();
                }else{
                    logService.erorLog(logger, "service", "unTrafficAlarm", String.format("Invoke the %s restful service fail.", acceptUrl ) ,
                            new IntegrateException( IntegrateException.IntegrateErrors.REQUEST_OUT_SIDE_FAIL ) ) ;
                }
            } catch (RestInvokeException ex) {
                logService.erorLog(logger, "service", "unTrafficAlarm", String.format("Invoke the %s restful service fail.", acceptUrl), ex);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "unTrafficAlarm", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "unTrafficAlarm", "execution error", ex);
            throw new IntegrateException( IntegrateException.IntegrateErrors.REQUEST_OUT_SIDE_FAIL ) ;
        }

    }



}