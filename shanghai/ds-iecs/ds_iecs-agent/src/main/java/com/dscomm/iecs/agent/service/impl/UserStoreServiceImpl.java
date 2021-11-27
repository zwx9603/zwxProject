package com.dscomm.iecs.agent.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.service.UserStoreService;
import com.dscomm.iecs.agent.service.bean.ImeiPortalBean;
import com.dscomm.iecs.agent.service.bean.Result;
import com.dscomm.iecs.agent.service.bean.ResultStatus;
import com.dscomm.iecs.agent.service.bean.UserPortalBean;
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

@Component("userStoreServiceImpl")
public class UserStoreServiceImpl implements UserStoreService {

    private static final Logger logger = LoggerFactory.getLogger(UserStoreServiceImpl.class);
    private LogService logService;
    private  Environment env ;


    @Autowired
    public UserStoreServiceImpl(  LogService logService , Environment env  ) {
        this.logService = logService ;
        this.env = env;
    }

    @Override
    @Transactional(readOnly = true)
    public UserPortalBean getUser(String account)  {
        try {
            logService.infoLog(logger, "service", "getUser", "service is started...");
            Long start = System.currentTimeMillis();

            UserPortalBean userPortalBean  = null;
            if ( Strings.isNotBlank( account )) {
                // 从调用基础数据接口 获得用户信息
                logService.infoLog(logger, "service", "getUser", " doing portal getUser start ");

                String theurl = env.getProperty("portalUrl") + "user/get_userinfo_by_systemname?systemname=" + account;

                logService.infoLog(logger, "service", "getUser", " doing portal getUser url " + theurl );

                HttpHeaders headers = new HttpHeaders();
                HttpEntity<?> entity = new HttpEntity<>(headers);
                ResponseEntity<String> result = RestfulClient.getClient().exchange(theurl, HttpMethod.GET, entity, String.class);

                logService.infoLog(logger, "service", "getUser", " doing portal getUser result  " + JSONObject.toJSONString( result )  );

                if (  result.getStatusCodeValue() == 200  ) {
                    String  bodystr = result.getBody() ;
                    Result result1 =  JSONObject.parseObject(bodystr , Result.class ) ;
                    if( ResultStatus.OK.equalsIgnoreCase( result1.ret  )  ){
                        String  userString =  JSONObject.toJSONString( result1.getDataStore() )  ;
                        if( Strings.isNotBlank( userString )){
                            userPortalBean = JSONObject.parseObject(userString  , UserPortalBean.class ) ;
                        }
                    }else{
                        logService.infoLog(logger, "service", "getUser", " doing portal getUser fail  " + JSONObject.toJSONString( result )  );
                    }
                }
                logService.infoLog(logger, "service", "getUser", " doing portal getUser end ");

            }

            logService.infoLog(logger, "service", "getUser", String.format("getUser....,totalTime2 :%sms", System.currentTimeMillis() - start));

            return userPortalBean ;
        }catch ( Exception ex ){
            logService.erorLog(logger, "service", "getUserInfo", "get userInfo fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }

    }


    @Override
    @Transactional(readOnly = true)
    public ImeiPortalBean getImei(String imei )  {
        try {
            logService.infoLog(logger, "service", "getImei", "service is started...");
            Long start = System.currentTimeMillis();

            ImeiPortalBean imeiPortalBean  = null;
            if ( Strings.isNotBlank( imei )) {
                // 从调用基础数据接口 获得用户信息
                logService.infoLog(logger, "service", "getImei", " doing portal getImei start ");


                String theurl = env.getProperty("portalUrl") + "terminal/imei?imei=" + imei;

                logService.infoLog(logger, "service", "getImei", " doing portal getImei url " + theurl );

                HttpHeaders headers = new HttpHeaders();
                HttpEntity<?> entity = new HttpEntity<>(headers);
                ResponseEntity<String> result = RestfulClient.getClient().exchange(theurl, HttpMethod.GET, entity, String.class);

                logService.infoLog(logger, "service", "getImei", " doing portal getImei result  " + JSONObject.toJSONString( result )  );

                if (  result.getStatusCodeValue() == 200  ) {
                    String  bodystr = result.getBody() ;
                    Result result1 =  JSONObject.parseObject(bodystr , Result.class ) ;
                    if( ResultStatus.OK.equalsIgnoreCase( result1.ret  )  ){
                        String  userString =  JSONObject.toJSONString( result1.getDataStore() )  ;
                        if( Strings.isNotBlank( userString )){
                            imeiPortalBean = JSONObject.parseObject(userString  , ImeiPortalBean.class ) ;
                        }
                    }else{
                        logService.infoLog(logger, "service", "getImei", " doing portal getImei fail  " + JSONObject.toJSONString( result )  );
                    }
                }
                logService.infoLog(logger, "service", "getImei", " doing portal getImei end ");

            }

            logService.infoLog(logger, "service", "getImei", String.format("getImei....,totalTime2 :%sms", System.currentTimeMillis() - start));

            return imeiPortalBean ;
        }catch ( Exception ex ){
            logService.erorLog(logger, "service", "getImei", "get getImei fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_IMEI_FAIL);
        }

    }


}
