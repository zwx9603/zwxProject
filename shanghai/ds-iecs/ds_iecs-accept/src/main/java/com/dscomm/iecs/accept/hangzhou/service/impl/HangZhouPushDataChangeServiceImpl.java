package com.dscomm.iecs.accept.hangzhou.service.impl;

import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.hangzhou.service.DahuaService;
import com.dscomm.iecs.accept.hangzhou.service.TTSSpeechService;
import com.dscomm.iecs.accept.hangzhou.service.ZhiJianDataChangeService;
import com.dscomm.iecs.accept.service.UnTrafficAlarmService;
import com.dscomm.iecs.accept.service.pushData.*;
import com.dscomm.iecs.base.service.log.LogService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("hangZhouPushDataChangeServiceImpl")
public class HangZhouPushDataChangeServiceImpl implements PushDataChangeService  {

    private static final Logger logger = LoggerFactory.getLogger(HangZhouPushDataChangeServiceImpl.class);
    private LogService logService;

    private ReportService reportService ;
    private PushPoliceService pushPoliceService ;
    private DockingService dockingService  ;
    private MobileService mobileService ;
    private UnTrafficAlarmService unTrafficAlarmService;
    private TTSSpeechService ttsSpeechService ;

    private DahuaService dahuaService ;
    private ZhiJianDataChangeService zhiJianDataChangeService ;



    @Autowired
    public HangZhouPushDataChangeServiceImpl(LogService logService ,
                                             ReportService reportService , PushPoliceService pushPoliceService ,
                                             DockingService dockingService , MobileService mobileService ,
                                             UnTrafficAlarmService unTrafficAlarmService  ,
                                             DahuaService dahuaService   , ZhiJianDataChangeService zhiJianDataChangeService  ,
                                             TTSSpeechService ttsSpeechService
                                             ) {
        this.logService = logService;

        this.reportService = reportService ;
        this.pushPoliceService = pushPoliceService ;
        this.dockingService = dockingService ;
        this.mobileService = mobileService ;
        this.unTrafficAlarmService = unTrafficAlarmService  ;

        this.dahuaService = dahuaService ;
        this.zhiJianDataChangeService = zhiJianDataChangeService;

        this.ttsSpeechService = ttsSpeechService ;


    }

    /**
     * 所有的接口记录错误 但是不抛出异常，以保证接处警流程的正常进行
     */

    @Transactional
    @Override
    public Boolean pushTelephone( TelephoneBean telephoneBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushTelephone", "service is started...");
        Long logStart = System.currentTimeMillis();

        /**公安*/
        pushPoliceService.sendPolicePhone( telephoneBean )  ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushTelephone", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushNewIncident(IncidentBean incidentBean , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushNewIncident", "service is started...");
        Long logStart = System.currentTimeMillis();


        /**一体化上报*/
        reportService.newCase( incidentBean );
        /**公安*/
        String unTrafficAlarmId =   otherParams.get( "unTrafficAlarmId"  ) ;
        //若存在非话务报警信息id，回写案件id到非话务报警信息中 并且 发送公安 警情已到达
        //若不存在非话务报警信息id 则 发送新警情 公安
        if (Strings.isNotBlank( unTrafficAlarmId  ) ) {
            unTrafficAlarmService.writeBackIncidentId( unTrafficAlarmId , incidentBean.getId() );
        }else{
            pushPoliceService.sendPoliceIncident( incidentBean );
        }
        /**coc通报*/
        dockingService.newCOCCase( incidentBean );
        //todo  项目特有
        dahuaService.asyncPushIncident( incidentBean );
        zhiJianDataChangeService.newCase(  incidentBean ) ;



        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushNewIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }




    @Transactional
    @Override
    public Boolean pushUpdateIncident (IncidentBean incidentBean   , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushModifyIncident", "service is started...");
        Long logStart = System.currentTimeMillis();

        /**一体化上报*/
        reportService.modifyCase( incidentBean );
        /**coc通报*/
        dockingService.modifyCOCCase( incidentBean  );
        /** 手机 */
        mobileService.modifyCase(incidentBean    ) ;
        //todo  项目特有
        zhiJianDataChangeService.modifyCase(incidentBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushModifyIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }



    @Transactional
    @Override
    public Boolean pushIncidentStatusChange (   IncidentBean incidentBean  ,
                          IncidentStatusChangeBean incidentStatusChangeBean , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentStatusChange", "service is started...");
        Long logStart = System.currentTimeMillis();


        /**一体化上报*/
        reportService.caseStat(incidentStatusChangeBean);
        /**coc通报*/
        dockingService.modifyCOCCase(incidentBean );
        /** 手机 */
        mobileService.caseStat(incidentBean  , incidentStatusChangeBean   ) ;
        //todo 项目特有
        dahuaService.asyncPushIncidentStatus( incidentBean );
        zhiJianDataChangeService.modifyCase( incidentBean )  ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentStatusChange", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public  Boolean pushIncidentMerger (IncidentMergeBean incidentMergeBean , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentMerger", "service is started...");
        Long logStart = System.currentTimeMillis();

        /** 一体化上报 */
        reportService.caseMerger(incidentMergeBean);

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentMerger", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushIncidentHandle(IncidentBean incidentBean, HandleBean  handleBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentHandle", "service is started...");
        Long logStart = System.currentTimeMillis();


        /** tts speech **/
        ttsSpeechService.pushIncidentHandleTTS( incidentBean , handleBean ) ;
        /**一体化*/
        List<HandleBean> handleBeans = new ArrayList<>();
        handleBeans.add( handleBean );
        reportService.dispatchVehicle(incidentBean, handleBeans);
        /**公安*/
        pushPoliceService.sendPoliceHandle( handleBean );
        /**  手机  */
        mobileService.dispatchVehicle( incidentBean , handleBean );
        //todo  项目特有
        dahuaService.asyncPushHandle( incidentBean , handleBean   );
        zhiJianDataChangeService.dispatchVehicle( incidentBean , handleBeans ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentHandle", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public  Boolean pushIncidentInstruction ( String incidentId , List<String> receivingObjectIds ,
                                              InstructionBean instructionBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentInstruction", "service is started...");
        Long logStart = System.currentTimeMillis();


        /**  手机  */
        mobileService.instruction( incidentId , receivingObjectIds , instructionBean  );

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentInstruction", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushVehicleStatusChange(  String incidentId , String handleId ,    String  vehicleId  ,
                                             String vehicleStatusCode  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushVehicleStatusChange", "service is started...");
        Long logStart = System.currentTimeMillis();

        /**  手机  */
        mobileService.carStatus( incidentId , handleId , vehicleId , vehicleStatusCode );
        //todo  项目特有
        dahuaService.asyncPushCarStatus(vehicleId , vehicleStatusCode);

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushVehicleStatusChange", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Override
    public Boolean pushVehicleStatusChange(String incidentId, String handleId, List<String> vehicleIds, String vehicleStatusCode, Map<String, String> otherParams) {
        logService.infoLog(logger, "service", "pushVehicleStatusChange", "service is started...");
        Long logStart = System.currentTimeMillis();

        /**  手机  */
        mobileService.carStatus( incidentId , handleId , vehicleIds , vehicleStatusCode );
        //todo  项目特有
        for (String vehicleId : vehicleIds) {
            dahuaService.asyncPushCarStatus(vehicleId , vehicleStatusCode);
        }


        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushVehicleStatusChange", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional
    @Override
    public Boolean pushSoundRecord( SoundRecordBean soundRecordBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushSoundRecord", "service is started...");
        Long logStart = System.currentTimeMillis();

        /** 一体化上报 */
        reportService.record( soundRecordBean  );

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushSoundRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushIncidentDocument(DocumentBean documentBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentDocument", "service is started...");
        Long logStart = System.currentTimeMillis();

        /** 一体化上报 */
        reportService.fireDocument( documentBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional
    @Override
    public Boolean pushIncidentLocale(LocaleBean localeBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentLocale", "service is started...");
        Long logStart = System.currentTimeMillis();

        /** 一体化上报 */
        reportService.sceneInfo(  localeBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentLocale", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


    @Transactional
    @Override
    public Boolean pushIncidentAccident( AccidentBean accidentBean  , Map<String, String > otherParams )   {
        logService.infoLog(logger, "service", "pushIncidentAccident", "service is started...");
        Long logStart = System.currentTimeMillis();

        /** 公安 */
        pushPoliceService.sendPoliceFeedBack( accidentBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentAccident", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


}
