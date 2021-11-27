package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.AccidentBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.TelephoneBean;
import com.dscomm.iecs.base.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("pushPoliceServiceImpl")
public class PushPoliceServiceImpl implements PushPoliceService {
    private static final Logger logger = LoggerFactory.getLogger(PushPoliceServiceImpl.class);
    private LogService logService;
    private PushPoliceChangeService pushPoliceChangeService  ;

    @Autowired
    public PushPoliceServiceImpl(LogService logService,   @Qualifier("pushPoliceChange") PushPoliceChangeService pushPoliceChangeService ) {
        this.logService = logService;
        this.pushPoliceChangeService = pushPoliceChangeService;
    }

    /**
     * 所有的接口错误不抛出，以保证接处警流程的正常进行
     */

    @Override
    public Boolean sendPolicePhone(TelephoneBean telephoneBean) {
        logService.infoLog(logger, "service", "sendPolicePhone", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushPoliceChangeService.sendPolicePhone( telephoneBean ) ;


        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPolicePhone", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean sendPoliceIncident(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "sendPoliceIncident", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushPoliceChangeService.sendPoliceIncident( incidentBean ) ;


        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean sendPoliceHandle(HandleBean handleBean) {
        logService.infoLog(logger, "service", "sendPoliceHandle", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushPoliceChangeService.sendPoliceHandle( handleBean ) ;


        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceHandle", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean sendPoliceHelp(OutsideInputInfo queryBean  ) {
        logService.infoLog(logger, "service", "sendPoliceHelp", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushPoliceChangeService.sendPoliceHelp( queryBean  ) ;


        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceHelp", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }



    @Override
    public Boolean sendPoliceFeedBack(AccidentBean accidentBean) {
        logService.infoLog(logger, "service", "sendPoliceFeedBack", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushPoliceChangeService.sendPoliceFeedBack( accidentBean ) ;


        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceFeedBack", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public  Boolean sendIncidentReceiveState( String incidentId , String  originalIncidentNumber ,String districtCode ) {
        logService.infoLog(logger, "service", "sendPoliceFeedBack", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushPoliceChangeService.sendIncidentReceiveState( incidentId , originalIncidentNumber , districtCode  ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceFeedBack", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

}
