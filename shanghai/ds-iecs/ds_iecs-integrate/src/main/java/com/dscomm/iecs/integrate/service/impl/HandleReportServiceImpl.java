package com.dscomm.iecs.integrate.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.*;
import com.dscomm.iecs.integrate.service.HandleReportService;
import org.mx.StringUtils;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.client.rest.RestInvokeException;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HandleReportServiceImpl implements HandleReportService {
    private static final Logger logger = LoggerFactory.getLogger(HandleReportServiceImpl.class);
    private Environment env;
    private LogService logService;
    private String reportUrl ;



    @Autowired
    public HandleReportServiceImpl(Environment env, LogService logService) {
        this.env = env;
        this.logService = logService;
        this.reportUrl = env.getProperty("dataReportUrl");
    }

    /**
     * 调派单上报
     * */
    @Transactional(readOnly = true)
    @Override
    public Boolean dispatchVehicle(DispatchDTO dispatchDTO) {
        if (  null == dispatchDTO) {
            logService.infoLog(logger, "service", "dispatchVehicle", " dispatchVO is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "dispatchVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            Boolean res = false   ;
            String url = reportUrl+"/DataReport/DispatchVehicle";

            try {
                restClientInvoke.post(url,
                        dispatchDTO, DataVO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the DispatchVehicle restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the DispatchVehicle restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "dispatchVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "dispatchVehicle", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }

    /**
     * 录音号上报
     *
     * @return*/
    @Transactional(readOnly = true)
    @Override
    public Boolean record(RecordDTO recordDTO) {
        if (  null == recordDTO || StringUtils.isBlank(recordDTO.getLYBH())) {
            logService.infoLog(logger, "service", "record", " recordVO is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "record", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            String url = reportUrl+"/DataReport/Record";
            Boolean res = false   ;

            try {
                ReturnDTO r = restClientInvoke.post(url,
                        recordDTO, ReturnDTO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the Record restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the Record restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "record", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "record", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }

    /**
     * 现场信息上报
     * */
    @Transactional(readOnly = true)
    @Override
    public Boolean sceneInfo(SceneInfoDTO sceneInfoDTO) {
        if (  null == sceneInfoDTO || StringUtils.isBlank(sceneInfoDTO.getID())) {
            logService.infoLog(logger, "service", "sceneInfo", " sceneInfoVO is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "sceneInfo", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            Boolean res = false   ;

            try {
                restClientInvoke.post(reportUrl+"/DataReport/SceneInfo",
                        sceneInfoDTO, DataVO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the SceneInfo restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the SceneInfo restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "sceneInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "sceneInfo", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }

    /**
     * 火场文书上报
     * */
    @Transactional(readOnly = true)
    @Override
    public Boolean fireDocument(FireDocumentDTO fireDocumentDTO) {
        if (  null == fireDocumentDTO || StringUtils.isBlank(fireDocumentDTO.getWSID())) {
            logService.infoLog(logger, "service", "fireDocument", " fireDocumentVO is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "fireDocument", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            Boolean res = false   ;

            try {
                restClientInvoke.post(reportUrl+"/DataReport/FireDocument",
                        fireDocumentDTO, DataVO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the FireDocument restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the FireDocument restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "fireDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "fireDocument", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }
}
