package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.restful.vo.IncidentCocVO;
import com.dscomm.iecs.accept.utils.transform.ReportTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("dockingChangeServiceImpl")
public class DockingChangeServiceImpl implements DockingChangeService {
    private static final Logger logger = LoggerFactory.getLogger(DockingChangeServiceImpl.class);
    private LogService logService;
    private Environment env;
    private Boolean whetherCoc;
    private String cocUrl;
    private RestClientInvoke restClientInvoke = null;

    @Autowired
    public DockingChangeServiceImpl(LogService logService , Environment env   ) {
        this.logService = logService;
        whetherCoc = Boolean.parseBoolean(env.getProperty("coc.enable"));
        cocUrl = env.getProperty("cocUrl");
        this.restClientInvoke = new RestClientInvoke();

    }

    

    @Override
    public Boolean newCOCCase(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "newCOCCase", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherCoc){
            return false;
        }
        logService.infoLog(  logger, "restful", "newCOCCase", "start thread to save newCOCCase" );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    IncidentCocVO vo = ReportTransformUtil.transformCOC(incidentBean);
                    DataVO resultType =  restClientInvoke.post(String.format("%s/rest/COC/FireAccept/NewCase", cocUrl),
                            vo, DataVO.class);
                    logService.infoLog(  logger, "restful", "newCOCCase", cocUrl + "/rest/COC/FireAccept/NewCase" + "  successful " );
                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "newCOCCase", "the  restful service fail ", ex );
                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "newCOCCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean modifyCOCCase(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "modifyCOCCase", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherCoc){
            return false;
        }
        logService.infoLog(  logger, "restful", "modifyCOCCase", "start thread to  modifyCOCCase" );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    IncidentCocVO vo = ReportTransformUtil.transformCOC(incidentBean);
                    restClientInvoke.post(String.format("%s/rest/COC/FireAccept/ModifyCase", cocUrl),
                            vo, DataVO.class);
                    logService.infoLog(  logger, "restful", "modifyCOCCase", cocUrl + "/rest/COC/FireAccept/ModifyCase" + "  successful " );
                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "modifyCOCCase", "the  restful service fail ", ex );
                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "modifyCOCCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


}
