package com.dscomm.iecs.integrate.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.TTSSpeechDTO;
import com.dscomm.iecs.integrate.service.TTSSpeechService;
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
public class TTSSpeechServiceImpl implements TTSSpeechService {
    private static final Logger logger = LoggerFactory.getLogger(TTSSpeechServiceImpl.class);
    private Environment env;
    private LogService logService;
    private String ttsSpeechUrl ;

    @Autowired
    public TTSSpeechServiceImpl(Environment env, LogService logService) {
        this.env = env;
        this.logService = logService;
        ttsSpeechUrl = env.getProperty("ttsSpeechUrl");
    }

    /**
     * 调派单 tts 播报
     * */
    @Transactional(readOnly = true)
    @Override
    public Boolean pushIncidentHandleTTS(TTSSpeechDTO ttsSpeechDTO) {
        if (  null == ttsSpeechDTO ) {
            logService.infoLog(logger, "service", "pushIncidentHandleTTS", " ttsSpeechDTO is blank.");
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "pushIncidentHandleTTS", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            Boolean res = false   ;

            try {
                restClientInvoke.post( ttsSpeechUrl , ttsSpeechDTO, DataVO.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Invoke the pushIncidentHandleTTS restful service.");
                }
                res = true ;
            } catch (RestInvokeException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Invoke the pushIncidentHandleTTS restful service fail.", ex);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "pushIncidentHandleTTS", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "pushIncidentHandleTTS", "execution error", ex);
            throw new IntegrateException(IntegrateException.IntegrateErrors.TTS_SPEECH_FAIL);
        }
    }


}
