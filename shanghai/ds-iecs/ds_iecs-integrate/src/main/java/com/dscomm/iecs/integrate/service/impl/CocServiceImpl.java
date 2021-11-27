package com.dscomm.iecs.integrate.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.IncidentCocVO;
import com.dscomm.iecs.integrate.service.CocService;
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

@Component("cocServiceImpl")
public class CocServiceImpl implements CocService {

    private static final Logger logger = LoggerFactory.getLogger(CocServiceImpl.class);
    private Environment env;
    private LogService logService;
    private String cocUrl ;

    @Autowired
    public CocServiceImpl(Environment env, LogService logService) {
        this.env = env;
        this.logService = logService;
        cocUrl = env.getProperty("cocUrl");
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean newCase(IncidentCocVO vo) {

        if (  null == vo || StringUtils.isBlank(vo.getIncidentId())) {
            logService.infoLog(logger, "service", "newCase", " IncidentCocVO is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "newCase", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            String url = cocUrl+"/rest/COC/FireAccept/NewCase";
            Boolean res = false   ;

            try {
                restClientInvoke.post(url, vo, DataVO.class);
                logger.debug("Invoke the newCase restful service.");
                res = true ;
            } catch (RestInvokeException ex) {
                logger.error("Invoke the newCase restful service fail.", ex);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "newCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "newCase", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Boolean modifyCase(IncidentCocVO vo) {

        if (  null == vo || StringUtils.isBlank(vo.getIncidentId())) {
            logService.infoLog(logger, "service", "modifyCase", " IncidentCocVO is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "modifyCase", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            String url = cocUrl+"/rest/COC/FireAccept/ModifyCase";
            Boolean res = false   ;

            try {
                restClientInvoke.post(url, vo, DataVO.class);
                logger.debug("Invoke the modifyCase restful service.");
                res = true ;
            } catch (RestInvokeException ex) {
                logger.error("Invoke the modifyCase restful service fail.", ex);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "modifyCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "modifyCase", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.REPORT_FAIL);
        }
    }
}
