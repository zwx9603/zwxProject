package com.dscomm.iecs.accept.hangzhou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.hangzhou.service.AlarmTransformUtil;
import com.dscomm.iecs.accept.hangzhou.service.DahuaService;
import com.dscomm.iecs.accept.restful.dahua.ResultTokenDto;
import com.dscomm.iecs.accept.restful.dahua.TokenParam;
import com.dscomm.iecs.accept.service.bean.dahua.alarm.ChangeCaseBean;
import com.dscomm.iecs.accept.service.bean.dahua.alarm.DhAlarmBean;
import com.dscomm.iecs.accept.service.bean.dahua.alarm.JQZT;
import com.dscomm.iecs.accept.service.bean.dahua.dispatch.CLZT;
import com.dscomm.iecs.accept.service.bean.dahua.dispatch.CarchangeBean;
import com.dscomm.iecs.accept.service.bean.dahua.dispatch.DispatchBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.HttpClientUtil;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("dahuaServiceImpl")
public class DahuaServiceImpl implements DahuaService {
    private static final Logger logger = LoggerFactory.getLogger(DahuaServiceImpl.class);
    private LogService logService;
    private Environment env;
    private Boolean isSend = false ;
    private ServletService servletService ;


    @Autowired
    public DahuaServiceImpl( LogService logService, Environment env  , ServletService servletService) {
        this.logService = logService;
        this.env = env;
        this.servletService = servletService ;
        isSend = Boolean.parseBoolean(env.getProperty("dh.send")); // 是否转发大华
    }

    @Transactional( readOnly =  true )
    @Override
    public Map<String, String> getHeaders() {
        logService.infoLog(logger, "service", "getHeaders", "service is started...");
        Long logStart = System.currentTimeMillis();

        String url = env.getProperty("dh.serviceUrl");
        String tokenPath = "/BDOFI/token";
        String appId = env.getProperty("dh.appId");
        String secret = env.getProperty("dh.secret");
        String tokenPrefix = env.getProperty("dh.tokenPrefix");
        // 获取token
        String toString = JSONObject.toJSON(new TokenParam(appId, secret)).toString();
        String resString = HttpClientUtil.doPost(url + tokenPath, toString);
        ResultTokenDto resultTokenDto = JSONObject.parseObject(resString, ResultTokenDto.class);
        Map headersMap = new HashMap();
        if (null != resultTokenDto && !CollectionUtils.isEmpty(resultTokenDto.getData()) && !Strings.isBlank(resultTokenDto.getData().get(0).getToKen())) {
            headersMap.put("token", tokenPrefix + " "+ resultTokenDto.getData().get(0).getToKen());
        } else {
            return null;
        }
        logService.infoLog( logger ,"service", "getHeaders", "header Json" + JSONObject.toJSONString( headersMap) );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "service", "getHeaders", String.format("repository is finished,execute time is :%sms", end - logStart));
        return headersMap;

    }

    @Transactional( readOnly =  true )
    @Override
    public void asyncPushIncident(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "asyncPushIncident", "asyncPushIncident is started...");
        Long logStart = System.currentTimeMillis();

        if( !isSend ){
            return;
        }

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            threadPool.execute(() -> {
                DhAlarmBean res = AlarmTransformUtil.transforms(incidentBean);
                Map<String, String> headers = this.getHeaders();
                String url = env.getProperty("dh.serviceUrl");
                String alarm = "/DsAlarm/basicinfomation";

                HttpClientUtil.doPost(url + alarm, JSONObject.toJSONString(res), headers);
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "asyncPushIncident", String.format("asyncPushIncident is finished,execute time is :%sms", logEnd - logStart));
            threadPool.shutdown();
        }
    }

    @Transactional( readOnly =  true )
    @Override
    public void asyncPushIncidentStatus( IncidentBean incidentBean ) {
        logService.infoLog(logger, "service", "asyncPushIncidentStatus", "asyncPushIncidentStatus is started...");
        Long logStart = System.currentTimeMillis();

        if( !isSend ){
            return;
        }

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        try {
            threadPool.execute(() -> {
                ChangeCaseBean changeCaseBean = new ChangeCaseBean( incidentBean.getId(),
                        new JQZT(incidentBean.getIncidentStatusCode(), new Date( servletService.getSystemTime() ) ),
                        incidentBean.getIncidentStatusCode(), null);

                Map<String, String> headers = this.getHeaders();
                String url = env.getProperty("dh.serviceUrl");
                String changeCase = "/DsCase/changeCase";
                HttpClientUtil.doPost(url+changeCase,JSONObject.toJSONString(changeCaseBean),headers);

            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "asyncPushIncidentStatus", String.format("asyncPushIncidentStatus is finished,execute time is :%sms", logEnd - logStart));
            threadPool.shutdown();
        }
    }


    @Transactional( readOnly =  true )
    @Override
    public synchronized void asyncPushHandle(IncidentBean incidentBean, HandleBean handleBean  ) {
        logService.infoLog(logger, "service", "asyncPushHandle", "asyncPushHandle is started...");
        Long logStart = System.currentTimeMillis();

        if( !isSend ){
            return;
        }

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        try {
            threadPool.execute(() -> {
                DispatchBean dispatchBean = AlarmTransformUtil.transforms(    incidentBean , handleBean  );
                logService.infoLog(logger, "service", "asyncPushHandle", "dispatchBean{}" + dispatchBean);

                Map<String, String> headers = this.getHeaders();
                String url = env.getProperty("dh.serviceUrl");
                String dispatch = "/DsAlarm/dispatch";
                HttpClientUtil.doPost(url+dispatch,JSONObject.toJSONString(dispatchBean),headers);

            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "asyncPushHandle", String.format("asyncPushHandle is finished,execute time is :%sms", logEnd - logStart));
            threadPool.shutdown();
        }
    }



    @Override
    public synchronized void asyncPushCarStatus(String vehicleId, String vehicleStatusCode) {
        logService.infoLog(logger, "service", "asyncPushIncidentStatus", "asyncPushIncidentStatus is started...");
        Long logStart = System.currentTimeMillis();

        if( !isSend ){
            return;
        }


        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Date date = new Date();
        try {
            threadPool.execute(() -> {
                CarchangeBean changeCaseBean = new CarchangeBean(vehicleId, new CLZT(vehicleStatusCode, date), vehicleStatusCode, date.toString());
                Map<String, String> headers = this.getHeaders();
                String url = env.getProperty("dh.serviceUrl");
                String changeCar = "/DsAlarm/changecar";
                HttpClientUtil.doPost(url+changeCar,JSONObject.toJSONString(changeCaseBean),headers);
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "asyncPushIncidentStatus", String.format("asyncPushIncidentStatus is finished,execute time is :%sms", logEnd - logStart));
            threadPool.shutdown();
        }
    }

}
