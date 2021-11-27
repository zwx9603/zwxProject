/*
 * @(#)TimeAxisFileServiceRequestIF.java
 *
 * Copyright 2016, 迪爱斯通信设备有限公司保留.
 */
package com.dscomm.iecs.keydata.service.timeline;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.base.utils.AES256Helper;
import com.dscomm.iecs.keydata.exception.UserInterfaceKeydataException;
import com.dscomm.iecs.keydata.service.timeline.bo.EsTimelineRequestBO;
import com.dscomm.iecs.keydata.service.timeline.bo.Result;
import org.mx.service.client.rest.RestClientInvoke;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 时间轴服务请求组件
 *
 */
@Component
public class TimeLineServiceImpl implements TimeLineService {
    private static final Logger logger = LoggerFactory.getLogger(TimeLineServiceImpl.class);
    @Value("${timeline.server:http://127.0.0.1:12001/ds-portal-timeline-web/timelineservice/insertRecord/insert}")
    private String timelineUrl;
    @Value(("${localIp:127.0.0.1}"))
    private String localIp;
    @Value("${system.code:HJSL}")
    private String systemCode;
    @Value("${callOtherServerNeedToken:false}")
    private String callOtherServerNeedToken;

    /**
     * {@inheritDoc}
     *
     * @see #saveTimeline(EsTimelineRequestBO)
     */
    public Boolean saveTimeline(EsTimelineRequestBO esTLineRequestBO) {
        long logStart = System.currentTimeMillis();
        logger.info("start thread to save TimeLine");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (logger.isInfoEnabled()) {
                    logger.info(String.format("version:3.0.0,Method:saveTimeline,start..."));
                }
                Long start = System.currentTimeMillis();
                try {

                    RestClientInvoke client = new RestClientInvoke();
                    Map<String, Object> headers = new HashMap<>();
                    String key = System.currentTimeMillis() + "," + localIp + "," + systemCode;
                    if ("true".equals(callOtherServerNeedToken)){
                        String token = AES256Helper.encrypt(key);
                        headers.put("tokenkey", token);
                    }
                    String post = client.post(timelineUrl, esTLineRequestBO, String.class, headers);
                    Result result = JSON.parseObject(post, Result.class);
                    if (result != null && "ok".equalsIgnoreCase(result.getRet())) {
                        if (!"OK".equalsIgnoreCase(result.getRet())) {
                            if (logger.isErrorEnabled()) {
                                logger.error(String.format("version:3.0.0,Method:saveTimeline,save timeline fail,result is %s",JSONObject.toJSONString(result)));
                            }
                            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.TIMELINE_SAVE_FAIL);
                        }
                        Long end = System.currentTimeMillis();
                        if (logger.isInfoEnabled()) {
                            logger.info(String.format("version:3.0.0,Method:saveTimeline,end,execute time:%sms", end - start));
                        }
//                        return true;
                    } else {
                        if (logger.isErrorEnabled()) {
                            logger.error(String.format("version:3.0.0,Method:saveTimeline,save timeline fail,result is %s",  JSONObject.toJSONString(result)));
                        }
                        throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.TIMELINE_SAVE_FAIL);
                    }
                } catch (Exception ex) {
                    logger.error(String.format("version:3.0.0,Method:saveTimeline,save timeline error"), ex);
                }
            }
        });
        thread.start();
        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to save TimeLine,totalTime:%sms", logEnd - logStart));
        return true;
    }

}
