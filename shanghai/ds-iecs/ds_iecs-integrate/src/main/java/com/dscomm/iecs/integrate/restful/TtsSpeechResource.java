package com.dscomm.iecs.integrate.restful;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.TTSSpeechDTO;
import com.dscomm.iecs.integrate.service.TTSSpeechService;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/** tts 播放路径 */
@Path("TTSSpeech")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TtsSpeechResource {

    private static final Logger logger = LoggerFactory.getLogger(TtsSpeechResource.class);
    private LogService logService;

    private TTSSpeechService  ttsSpeechService;

    @Autowired
    public TtsSpeechResource(  LogService logService ,TTSSpeechService  ttsSpeechService ) {
        this.logService = logService ;
        this.ttsSpeechService = ttsSpeechService;
    }

    /**
     *  tts 播放
     * */

    @POST
    public DataVO<Boolean> pushIncidentHandleTTS (   TTSSpeechDTO ttsSpeechDTO ){
        if (  null == ttsSpeechDTO || Strings.isBlank( ttsSpeechDTO.getAJID() )) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "pushIncidentHandleTTS", "pushIncidentHandleTTS :" + JSON.toJSONString( ttsSpeechDTO ) );

        Boolean res = ttsSpeechService.pushIncidentHandleTTS( ttsSpeechDTO ) ;

        return new DataVO<>(res);
    }




}
