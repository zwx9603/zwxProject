package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.service.LibsService;
import com.dscomm.iecs.accept.service.bean.DeviceLocationBean;
import com.dscomm.iecs.agent.service.bean.Result;
import com.dscomm.iecs.agent.service.bean.ResultStatus;
import com.dscomm.iecs.agent.service.impl.UserStoreServiceImpl;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.RestfulClient;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zs
 * @Date: 16:14 2020/7/24
 * desc:
 */
@Component("libsServiceImpl")
public class LibsServiceImpl implements LibsService {


    private static final Logger logger = LoggerFactory.getLogger(UserStoreServiceImpl.class);
    private LogService logService;
    private Environment env ;


    @Autowired
    public LibsServiceImpl(  LogService logService , Environment env  ) {
        this.logService = logService ;
        this.env = env;
    }

    /**
     *  根据 终端编号 获得 终端位置 信息
     * @param deviceCode
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public DeviceLocationBean findCurrentDeviceLocation(String deviceCode ) {
        try {
            logService.infoLog(logger, "service", "findCurrentDeviceLocation", "service is started...");
            Long start = System.currentTimeMillis();

            DeviceLocationBean deviceLocation = null;
            if (Strings.isNotBlank(deviceCode)) {
                // 从libs 获得 终端现在位置信息
                logService.infoLog(logger, "service", "findCurrentDeviceLocation", " doing portal getUser start ");

                String theurl = env.getProperty("libsPathUrl") + "lbs/querycurrentdata?version=v1.1.0&devicecode=" + deviceCode;

                logService.infoLog(logger, "service", "findCurrentDeviceLocation", " doing portal getUser url " + theurl);

                HttpHeaders headers = new HttpHeaders();
                HttpEntity<?> entity = new HttpEntity<>(headers);
                ResponseEntity<String> result = RestfulClient.getClient().exchange(theurl, HttpMethod.GET, entity, String.class);

                logService.infoLog(logger, "service", "findCurrentDeviceLocation", " doing libs querycurrentdata result  " + JSONObject.toJSONString(result));

                if (result.getStatusCodeValue() == 200) {
                    String bodystr = result.getBody();
                    Result result1 = JSONObject.parseObject(bodystr, Result.class);
                    if (ResultStatus.OK.equalsIgnoreCase(result1.ret)) {
                        String userString = JSONObject.toJSONString(result1.getDataStore());
                        if (Strings.isNotBlank(userString)) {
                            deviceLocation = JSONObject.parseObject(userString, DeviceLocationBean.class);
                        }
                    } else {
                        logService.infoLog(logger, "service", "findCurrentDeviceLocation", " doing libs querycurrentdata fail  " + JSONObject.toJSONString(result));
                    }
                }
                logService.infoLog(logger, "service", "findCurrentDeviceLocation", " doing portal getUser end ");

            }

            logService.infoLog(logger, "service", "login", String.format("querycurrentdata....,totalTime2 :%sms", System.currentTimeMillis() - start));

            return deviceLocation ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findCurrentDeviceLocation", "get querycurrentdata fail", ex);
            return  null ;
        }

    }



}
