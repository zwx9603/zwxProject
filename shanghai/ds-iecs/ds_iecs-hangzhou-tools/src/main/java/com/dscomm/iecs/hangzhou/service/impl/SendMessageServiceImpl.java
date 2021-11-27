package com.dscomm.iecs.hangzhou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.hangzhou.MD5Util.MD5Util;
import com.dscomm.iecs.hangzhou.restful.vo.SmsMessageBackVO;
import com.dscomm.iecs.hangzhou.restful.vo.SmsMessageVO;
import com.dscomm.iecs.hangzhou.service.SendMessageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.error.UserInterfaceServiceErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：终端（含PC或移动终端）警情实时推送服务实现
 *
 * @author john peng
 * Date time 2018/7/15 上午10:52
 */
@Component("sendMessageServiceImpl")
public class SendMessageServiceImpl implements SendMessageService {

    private static final Log logger = LogFactory.getLog(SendMessageServiceImpl.class);

    private Environment env ;
    private RestClientInvoke restClientInvoke = null ;
    /**
     * 构造函数
     *
     */
    @Autowired
    public SendMessageServiceImpl(  Environment env  ) {
        super();
        this.env = env ;
        this.restClientInvoke = new RestClientInvoke();
    }

    /**
     *发送短信
     */
    public SmsMessageBackVO batchSendMessage(SmsMessageVO vo , Boolean  bl ) {
        try {
            if( bl ){
                String applicationId = env.getProperty("applicationId"); //应用账号(AppKey)
                String password = env.getProperty("password") ; //应用密码(AppSecret)
                String sign = env.getProperty("sign") ; //接口校验令牌
                String requestTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                vo.setApplicationId( applicationId );
                vo.setPassword( password);
                vo.setRequestTime( requestTime);
                String oldSign = applicationId + password +  requestTime + sign ;
                vo.setSign( MD5Util.MD5( oldSign ));
                String sendTime =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                vo.setSendTime( sendTime );

            }
            Map<String, Object> headers = new HashMap<>();
            headers.put("Content-Type","application/json;charset=utf-8") ;
            String smsUrl = env.getProperty( "smsUrl") ;
            String result = restClientInvoke.post(String.format("%s/yunmas_api/smsApi/batchSendMessage", smsUrl ),
                    vo  , String.class , headers );
            //进行判断 处理
            SmsMessageBackVO backVO = JSONObject.parseObject( result , SmsMessageBackVO.class ) ;
            if(backVO != null && "0".equals( backVO.getResultCode() )){
                logger.info( "  send sms  successful "  );
            }else{
                logger.error( "  send sms fail , error msg : "  + result );
            }
            return  backVO  ;
        }catch ( Exception ex) {
            logger.error(String.format(" invoke the sms restful service fail."), ex);
            throw new UserInterfaceServiceErrorException(
                    UserInterfaceServiceErrorException.ServiceErrors.SERVICE_OTHER_FAIL) ;
        }

    }



}
