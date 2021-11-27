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

@Component("reportServiceImpl")
public class ReportServiceImpl implements ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    private LogService logService;
    private ReportChaneService reportChaneService ;

    @Autowired
    public ReportServiceImpl(LogService logService, @Qualifier("reportChange") ReportChaneService reportChaneService
                               ) {
        this.logService = logService;
        this.reportChaneService = reportChaneService ;
    }

    /**
     * 所有的接口错误不抛出，以保证接处警流程的正常进行
     */
    @Transactional
    @Override
    public Boolean newCase(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "newCase", "service is started...");
        Long logStart = System.currentTimeMillis();

        reportChaneService.newCase(incidentBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "newCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional
    @Override
    public Boolean modifyCase(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "modifyCase", "service is started...");
        Long logStart = System.currentTimeMillis();

        reportChaneService.modifyCase(incidentBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "modifyCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }
    @Transactional
    @Override
    public Boolean caseStat(IncidentStatusChangeBean incidentStatusChangeBean) {
        logService.infoLog(logger, "service", "caseStat", "service is started...");
        Long logStart = System.currentTimeMillis();

        reportChaneService.caseStat(incidentStatusChangeBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "caseStat", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }
    @Transactional
    @Override
    public Boolean caseMerger(IncidentMergeBean incidentMergeBean) {
        logService.infoLog(logger, "service", "caseMerger", "service is started...");
        Long logStart = System.currentTimeMillis();

        reportChaneService.caseMerger(incidentMergeBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "caseMerger", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }
    @Transactional
    @Override
    public Boolean dispatchVehicle(IncidentBean incidentBean, List<HandleBean> handleBeans) {
        logService.infoLog(logger, "service", "dispatchVehicle", "service is started...");
        Long logStart = System.currentTimeMillis();

        reportChaneService.dispatchVehicle(incidentBean ,handleBeans  ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "dispatchVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;

    }
    @Transactional
    @Override
    public Boolean record( SoundRecordBean soundRecordBean) {
        logService.infoLog(logger, "service", "record", "service is started...");
        Long logStart = System.currentTimeMillis();

        reportChaneService.record( soundRecordBean  ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "record", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }
    @Transactional
    @Override
    public Boolean fireDocument(DocumentBean documentBean) {
        logService.infoLog(logger, "service", "fireDocument", "service is started...");
        Long logStart = System.currentTimeMillis();

        reportChaneService.fireDocument( documentBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "fireDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }
    @Transactional
    @Override
    public Boolean sceneInfo(LocaleBean localeBean) {
        logService.infoLog(logger, "service", "sceneInfo", "service is started...");
        Long logStart = System.currentTimeMillis();

        reportChaneService.sceneInfo( localeBean ) ;


        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sceneInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }



}
