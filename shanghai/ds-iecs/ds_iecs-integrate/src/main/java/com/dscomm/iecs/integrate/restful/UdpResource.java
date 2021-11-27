package com.dscomm.iecs.integrate.restful;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.service.UdpPushPoliceService;
import com.dscomm.iecs.integrate.service.udpBean.*;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("udp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UdpResource {

    private static final Logger logger = LoggerFactory.getLogger(UdpResource.class);
    private LogService logService;
    private UdpPushPoliceService udpPushPoliceService ;

    @Autowired
    public UdpResource( LogService logService , UdpPushPoliceService udpPushPoliceService) {
        this.logService = logService ;
        this.udpPushPoliceService = udpPushPoliceService;
    }

    @Path("sendPhone")
    @POST
    public DataVO<Boolean> sendPhone(UdpPhoneContentVO vo){
        if (  null == vo ) {
            logService.writeLog(logger, "restful", "sendPhone", "UdpPhoneContentVO is null " );
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "sendPhone", "sendPhone" + JSON.toJSONString( vo ) );

        udpPushPoliceService.sendPhone(vo);
        return new DataVO<>(true);
    }

    @Path("sendIncident")
    @POST
    public DataVO<Boolean> sendIncident(UdpIncidentContentVO vo){
        if (  null == vo ) {
            logService.writeLog(logger, "restful", "sendIncident", "UdpIncidentContentVO is null " );
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "sendIncident", "sendIncident" + JSON.toJSONString( vo ) );

        udpPushPoliceService.sendIncident(vo);
        return new DataVO<>(true);
    }

    @Path("sendHandle")
    @POST
    public DataVO<Boolean> sendHandle(UdpHandle119ContentVO vo){
        if (  null == vo ) {
            logService.writeLog(logger, "restful", "sendHandle", "UdpHandle119ContentVO is null " );
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }

        logService.infoLog(logger, "restful", "sendHandle", "sendHandle" + JSON.toJSONString( vo ) );

        udpPushPoliceService.sendHandle(vo);
        return new DataVO<>(true);
    }

    @Path("sendFeedBack")
    @POST
    public DataVO<Boolean> sendFeedBack(UdpFeedBackContentVO vo){
        if (  null == vo ) {
            logService.writeLog(logger, "restful", "sendFeedBack", "UdpFeedBackContentVO is null " );
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "sendFeedBack", "sendFeedBack" + JSON.toJSONString( vo ) );

        udpPushPoliceService.sendFeedBack(vo);
        return new DataVO<>(true);
    }

    @Path("sendHelp")
    @POST
    public DataVO<Boolean> sendHelp(UdpHelpContentVO vo){
        if (  null == vo ) {
            logService.writeLog(logger, "restful", "sendHelp", "UdpHelpContentVO is null " );
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "sendHelp", "sendHelp" + JSON.toJSONString( vo ) );

        udpPushPoliceService.sendHelp(vo);
        return new DataVO<>(true);
    }

    @Path("sendIncidentReceiveState")
    @POST
    public DataVO<Boolean> sendIncidentReceiveState(UdpIncidentReceiveStateContentVO vo){
        if (  null == vo ) {
            logService.writeLog(logger, "restful", "sendIncidentReceiveState", "UdpHelpContentVO is null " );
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "sendIncidentReceiveState", "sendIncidentReceiveState" + JSON.toJSONString( vo ) );

        udpPushPoliceService.sendIncidentReceiveState(vo);
        return new DataVO<>(true);
    }
}
