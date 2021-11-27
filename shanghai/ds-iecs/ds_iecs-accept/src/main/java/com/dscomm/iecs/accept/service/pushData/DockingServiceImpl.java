package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.base.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("dockingServiceImpl")
public class DockingServiceImpl implements DockingService {
    private static final Logger logger = LoggerFactory.getLogger(DockingServiceImpl.class);
    private LogService logService;
    private DockingChangeService dockingChangeService ;

    @Autowired
    public DockingServiceImpl(LogService logService, @Qualifier("dockingChange")  DockingChangeService dockingChangeService ) {
        this.logService = logService;
        this.dockingChangeService = dockingChangeService ;
    }


    @Transactional
    @Override
    public Boolean newCOCCase(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "sceneInfo", "service is started...");
        Long logStart = System.currentTimeMillis();

        dockingChangeService.newCOCCase( incidentBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sceneInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return true;
    }
    @Transactional
    @Override
    public Boolean modifyCOCCase(IncidentBean incidentBean  ) {
        logService.infoLog(logger, "service", "sceneInfo", "service is started...");
        Long logStart = System.currentTimeMillis();

         dockingChangeService.modifyCOCCase( incidentBean ) ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sceneInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return true;
    }
}
