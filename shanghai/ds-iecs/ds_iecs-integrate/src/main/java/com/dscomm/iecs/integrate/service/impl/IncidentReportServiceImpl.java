package com.dscomm.iecs.integrate.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.CaseInfoDTO;
import com.dscomm.iecs.integrate.restful.vo.CaseMergeDTO;
import com.dscomm.iecs.integrate.restful.vo.CaseStatDTO;
import com.dscomm.iecs.integrate.service.IncidentReportService;
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

@Component
public class IncidentReportServiceImpl implements IncidentReportService {
    private static final Logger logger = LoggerFactory.getLogger(IncidentReportServiceImpl.class);
    private Environment env;
    private LogService logService;
    private String reportUrl ;



    @Autowired
    public IncidentReportServiceImpl(Environment env, LogService logService) {
        this.env = env;
        this.logService = logService;
        this.reportUrl = env.getProperty("dataReportUrl");
    }
    /**
     * 新增警情上报
     * */
    @Transactional(readOnly = true)
    @Override
    public Boolean newCase(CaseInfoDTO caseInfoDTO) {
        if (  null == caseInfoDTO || Strings.isBlank( caseInfoDTO.getAJID() )) {
            logService.infoLog(logger, "service", "newCase", " caseInfoVO or AJID is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "newCase", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            Boolean res = false   ;

            try {
                restClientInvoke.post(reportUrl+"/DataReport/NewCase",
                        caseInfoDTO, DataVO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the NewCase restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the NewCase restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "newCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "newCase", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }

    /**
     * 修改警情上报
     * */
    @Transactional(readOnly = true)
    @Override
    public Boolean modifyCase(CaseInfoDTO caseInfoDTO) {
        if (  null == caseInfoDTO || Strings.isBlank( caseInfoDTO.getAJID() )) {
            logService.infoLog(logger, "service", "modifyCase", " caseInfoVO or AJID is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "modifyCase", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            Boolean res = false   ;

            try {
                restClientInvoke.post(reportUrl+"/DataReport/ModifyCase",
                        caseInfoDTO, DataVO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the ModifyCase restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the ModifyCase restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "modifyCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "modifyCase", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }

    /**
     * 案件状态修改上报
     * */
    @Transactional(readOnly = true)
    @Override
    public Boolean caseStat(CaseStatDTO caseStatDTO) {
        if (  null == caseStatDTO || Strings.isBlank( caseStatDTO.getAJID() )) {
            logService.infoLog(logger, "service", "caseStat", " caseInfoVO or AJID is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "caseStat", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            Boolean res = false   ;

            try {
                restClientInvoke.post(reportUrl+"/DataReport/CaseStat",
                        caseStatDTO, DataVO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the CaseStat restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the CaseStat restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "caseStat", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "caseStat", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }

    /**
     * 案件合并上报
     * */
    @Transactional(readOnly = true)
    @Override
    public Boolean caseMerger(CaseMergeDTO caseMergeDTO) {
        if (  null == caseMergeDTO || Strings.isBlank( caseMergeDTO.getCaseid() )) {
            logService.infoLog(logger, "service", "caseMerger", " caseInfoVO or AJID is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "caseMerger", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            Boolean res = false   ;

            try {
                restClientInvoke.post(reportUrl+"/DataReport/CaseMerger",
                        caseMergeDTO, DataVO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the CaseMerger restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the CaseMerger restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "caseMerger", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "caseMerger", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }

}
