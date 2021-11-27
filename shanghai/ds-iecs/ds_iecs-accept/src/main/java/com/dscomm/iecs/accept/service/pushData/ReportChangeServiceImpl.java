package com.dscomm.iecs.accept.service.pushData;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.restful.vo.ReportVO.*;
import com.dscomm.iecs.accept.utils.transform.ReportTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("reportChangeServiceImpl")
public class ReportChangeServiceImpl implements ReportChaneService {
    private static final Logger logger = LoggerFactory.getLogger(ReportChangeServiceImpl.class);
    private LogService logService;
    private String reportUrl;
    private Environment env;
    private RestClientInvoke restClientInvoke = null;
    private Boolean whetherReport ;

    @Autowired
    public ReportChangeServiceImpl(LogService logService, Environment env ) {
        this.logService = logService;
        this.restClientInvoke = new RestClientInvoke();
        this.env = env;
        reportUrl = env.getProperty("reportDataUrl");
        whetherReport = Boolean.parseBoolean(env.getProperty("reportDataUrl.enable"));
    }

    /**
     * 所有的接口错误不抛出，以保证接处警流程的正常进行
     */

    @Override
    public Boolean newCase(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "newCase", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherReport){
            return false;
        }
        logService.writeLog(  logger, "restful", "newCase", "start thread to  newCase" );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CaseInfoDTO caseInfoVO = ReportTransformUtil.transform(env.getProperty("timeZone") ,incidentBean);
                    System.out.println(JSON.toJSON( caseInfoVO )  );
                    restClientInvoke.post(String.format("%s/DataReport/NewCase", reportUrl),
                            caseInfoVO, DataVO.class);
                    logService.writeLog(  logger, "restful", "newCase", reportUrl + "/DataReport/NewCase" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "newCase", "the  restful service fail ", ex );
                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "newCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean modifyCase(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "modifyCase", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherReport){
            return false;
        }
        logService.writeLog(  logger, "restful", "modifyCase", "start thread to  modifyCase" );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CaseInfoDTO caseInfoVO = ReportTransformUtil.transform(env.getProperty("timeZone") ,incidentBean);
                    restClientInvoke.post(String.format("%s/DataReport/ModifyCase", reportUrl),
                            caseInfoVO, DataVO.class);
                    logService.writeLog(  logger, "restful", "modifyCase", reportUrl + "/DataReport/ModifyCase" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "modifyCase", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "modifyCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean caseStat(IncidentStatusChangeBean incidentStatusChangeBean) {
        logService.infoLog(logger, "service", "caseStat", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherReport){
            return false;
        }
        logService.writeLog(  logger, "restful", "caseStat", "start thread to  caseState" );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    CaseStatDTO caseStatVO = ReportTransformUtil.transform(env.getProperty("timeZone") ,incidentStatusChangeBean);
                    restClientInvoke.post(String.format("%s/DataReport/CaseStat", reportUrl),
                            caseStatVO, DataVO.class);
                    logService.writeLog(  logger, "restful", "caseState", reportUrl + "/DataReport/CaseState" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "caseState", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "caseStat", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean caseMerger(IncidentMergeBean incidentMergeBean) {
        logService.infoLog(logger, "service", "caseMerge", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherReport){
            return false;
        }
        logService.writeLog(  logger, "restful", "caseMerge", "start thread to  CaseMerger" );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    CaseMergeDTO caseMergeVO = ReportTransformUtil.transform(incidentMergeBean);
                    restClientInvoke.post(String.format("%s/DataReport/CaseMerger", reportUrl),
                            caseMergeVO, DataVO.class);
                    logService.writeLog(  logger, "restful", "caseMerge", reportUrl + "/DataReport/CaseMerger" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "caseMerge", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "CaseMerge", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean dispatchVehicle(IncidentBean incidentBean, List<HandleBean> handleBeans) {
        logService.infoLog(logger, "service", "dispatchVehicle", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherReport){
            return false;
        }

        logService.writeLog(  logger, "restful", "dispatchVehicle", "start thread to  dispatchVehicle" );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    DispatchDTO dispatchVO = ReportTransformUtil.transform(env.getProperty("timeZone") ,incidentBean, handleBeans);
                    restClientInvoke.post(String.format("%s/DataReport/DispatchVehicle", reportUrl),
                            dispatchVO, DataVO.class);
                    logService.writeLog(  logger, "restful", "dispatchVehicle", reportUrl + "/DataReport/DispatchVehicle" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "dispatchVehicle", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "dispatchVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;

    }

    @Override
    public Boolean record( SoundRecordBean soundRecordBean) {
        logService.infoLog(logger, "service", "record", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherReport){
            logService.infoLog(logger, "service", "record", "service is false...");
            return false;
        }
        logService.writeLog(  logger, "restful", "Record", "start thread to  record" );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    RecordDTO recordVO = ReportTransformUtil.transform(env.getProperty("timeZone") ,soundRecordBean );
                    restClientInvoke.post(
                            String.format("%s/DataReport/Record", reportUrl),
                            recordVO, DataVO.class);
                    logService.writeLog(  logger, "restful", "Record", reportUrl + "/DataReport/Record" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "Record", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "record", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean fireDocument(DocumentBean documentBean) {
        logService.infoLog(logger, "service", "fireDocument", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherReport){
            return false;
        }
        logService.writeLog(  logger, "restful", "fireDocument", "start thread to  fireDocument" );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    FireDocumentDTO fireDocumentVO = ReportTransformUtil.transform(env.getProperty("timeZone") ,documentBean);
                    restClientInvoke.post(String.format("%s/DataReport/FireDocument", reportUrl),
                            fireDocumentVO, DataVO.class);
                    logService.writeLog(  logger, "restful", "fireDocument", reportUrl + "/DataReport/FireDocument" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "fireDocument", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "fireDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Override
    public Boolean sceneInfo(LocaleBean localeBean) {
        logService.infoLog(logger, "service", "sceneInfo", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherReport){
            return false;
        }
        logService.writeLog(  logger, "restful", "sceneInfo", "start thread to  fireDocument" );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    SceneInfoDTO sceneInfoVO = ReportTransformUtil.transform(env.getProperty("timeZone") ,localeBean);
                    restClientInvoke.post(String.format("%s/DataReport/SceneInfo", reportUrl),
                            sceneInfoVO, DataVO.class);
                    logService.writeLog(  logger, "restful", "sceneInfo", reportUrl + "/DataReport/SceneInfo" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "sceneInfo", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sceneInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }


}
