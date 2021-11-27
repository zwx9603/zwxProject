package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.base.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("pushDataServiceImpl")
public class PushDataServiceImpl implements PushDataService  {

    private static final Logger logger = LoggerFactory.getLogger(PushDataServiceImpl.class);
    private LogService logService;
    private PushDataChangeService pushDataChangeService ;


    @Autowired
    public PushDataServiceImpl( LogService logService , @Qualifier("pushDataChange") PushDataChangeService pushDataChangeService ) {
        this.logService = logService;
        this.pushDataChangeService = pushDataChangeService ;
    }
    /**
     * 所有的接口记录错误 但是不抛出异常，以保证接处警流程的正常进行
     */

    @Transactional
    @Override
    public Boolean pushTelephone( TelephoneBean telephoneBean , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushTelephone", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushTelephone( telephoneBean    , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushTelephone", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushNewIncident(IncidentBean incidentBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushNewIncident", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushNewIncident( incidentBean   , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushNewIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }




    @Transactional
    @Override
    public Boolean pushUpdateIncident(IncidentBean incidentBean ,  Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushModifyIncident", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushUpdateIncident( incidentBean     , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushModifyIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }



    @Transactional
    @Override
    public Boolean pushIncidentStatusChange (IncidentBean incidentBean  ,
                                             IncidentStatusChangeBean incidentStatusChangeBean ,  Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentStatusChange", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushIncidentStatusChange( incidentBean , incidentStatusChangeBean   , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentStatusChange", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public  Boolean pushIncidentMerger (IncidentMergeBean incidentMergeBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentMerger", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushIncidentMerger( incidentMergeBean   , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentMerger", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushIncidentHandle(IncidentBean incidentBean   ,  HandleBean  handleBean   , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentHandle", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushIncidentHandle( incidentBean ,handleBean    , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentHandle", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional
    @Override
    public Boolean pushIncidentInstruction ( String incidentId , List<String> receivingObjectIds ,
                                             InstructionBean instructionBean  , Map<String, String > otherParams )    {
        logService.infoLog(logger, "service", "pushIncidentInstruction", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushIncidentInstruction( incidentId , receivingObjectIds  ,instructionBean  , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentInstruction", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushVehicleStatusChange(  String incidentId , String handleId ,   String  vehicleId  ,
                                             String vehicleStatusCode  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushVehicleStatusChange", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushVehicleStatusChange( incidentId , handleId , vehicleId  ,
                vehicleStatusCode  , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushVehicleStatusChange", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean pushVehicleStatusChange(String incidentId, String handleId, List<String> vehicleId, String vehicleStatusCode, Map<String, String> otherParams) {
        return null;
    }

    @Transactional
    @Override
    public Boolean pushSoundRecord( SoundRecordBean soundRecordBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushSoundRecord", "service is started...");
        Long logStart = System.currentTimeMillis();

        //需要录音号保存
        pushDataChangeService.pushSoundRecord( soundRecordBean   , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushSoundRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushIncidentDocument(DocumentBean documentBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentDocument", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushIncidentDocument( documentBean   , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional
    @Override
    public Boolean pushIncidentLocale(LocaleBean localeBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentLocale", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushIncidentLocale( localeBean  , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentLocale", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushIncidentAccident( AccidentBean accidentBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentAccident", "service is started...");
        Long logStart = System.currentTimeMillis();

        pushDataChangeService.pushIncidentAccident(   accidentBean    , otherParams ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentAccident", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }



}
