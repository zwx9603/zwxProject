package com.dscomm.iecs.accept.hangzhou.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.hangzhou.bean.TTSSpeechDTO;
import com.dscomm.iecs.accept.hangzhou.service.TTSSpeechService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("tTSSpeechServiceImpl")
public class TTSSpeechServiceImpl implements TTSSpeechService {

    private static final Logger logger = LoggerFactory.getLogger(TTSSpeechServiceImpl.class);
    private LogService logService;
    private Environment env;
    private RestClientInvoke restClientInvoke = null;
    private Boolean whetherTtsSpeech  = false ; //是否ttss 播报第三方
    private String ttsSpeechUrl  ;// ttss 播报第三方 播放路径

    private String ttsSpeechToFileUrl  ;// ttss 生成播放文件服务路径


    @Autowired
    public TTSSpeechServiceImpl(LogService logService ,  Environment env
    ) {
        this.logService = logService;
        this.env = env ;
        this.restClientInvoke = new RestClientInvoke();
        whetherTtsSpeech = Boolean.parseBoolean(env.getProperty("ttsSpeech.enable"));
        ttsSpeechUrl = env.getProperty("ttsSpeechUrl");

        ttsSpeechToFileUrl = env.getProperty("ttsSpeechToFileUrl","");
   
    }

    /**
     * 所有的接口记录错误 但是不抛出异常，以保证接处警流程的正常进行
     */
    @Transactional
    @Override
    public Boolean pushIncidentHandleTTS(IncidentBean incidentBean,  HandleBean  handleBean ) {
        logService.infoLog(logger, "service", "pushIncidentHandleTTS", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (  !whetherTtsSpeech  ){
            return false;
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                    try {
                        TTSSpeechDTO ttsSpeechDTO =  transform(env.getProperty("timeZone") ,incidentBean, handleBean );
                        logService.writeLog(  logger, "restful", "pushIncidentHandleTTS",      "ttsSpeech data" +
                                JSONObject.toJSONString( ttsSpeechDTO ));
                        restClientInvoke.post(  ttsSpeechUrl ,
                                ttsSpeechDTO , DataVO .class);
                        logService.writeLog(  logger, "restful", "pushIncidentHandleTTS", ttsSpeechUrl   + "  successful  " );
                    } catch (Exception ex) {
                        logService.erorLog(  logger, "restful", "pushIncidentHandleTTS", "the  restful service fail ", ex );
                    }
            }
        });
        thread.start();

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pushIncidentHandleTTS", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return true;
    }


    @Transactional
    @Override
    public String buildSpeakToFile ( IncidentBean incidentBean,  HandleBean  handleBean   ){
        logService.infoLog(logger, "service", "buildSpeakToFile", "service is started...");
        Long logStart = System.currentTimeMillis();

        String  speakToFileUrl = "" ;

        try {
            TTSSpeechDTO ttsSpeechDTO =  transform(env.getProperty("timeZone") ,incidentBean, handleBean );
            //设置请求头参数
            Map<String, Object> headers = new HashMap<>();
            headers.put( "Content-Type","application/json;charset=UTF-8") ;
            String result  =  restClientInvoke.post(String.format("%s/rest/v1/tts/speakToFile", ttsSpeechToFileUrl ) ,
                    ttsSpeechDTO.getBBNR() , String.class , headers );
            logService.writeLog(  logger, "restful", "buildSpeakToFile", "the  restful service successfully  "
                    +   result  );
            if(  Strings.isNotBlank( result )  ){
                DataVO resultType = JSONObject.parseObject( result , DataVO.class ) ;
                if(  resultType.getErrorCode() == 0 ){
                    String localHostFileUrl = ( String ) resultType.getData() ;
                    //对返回路径 的分割符进行特殊处理
                    if(Strings.isNotBlank( localHostFileUrl) ){
                        localHostFileUrl = localHostFileUrl.trim().replace("\\","/") ;
                        String fileName = localHostFileUrl.substring( localHostFileUrl.lastIndexOf( "/" ) + 1 ) ;
                        speakToFileUrl = ttsSpeechToFileUrl + "/rest/v1/tts/fileDownload/" + fileName ;
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(  logger, "restful", "buildSpeakToFile", "the  restful service fail ", ex );
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "buildSpeakToFile", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return speakToFileUrl;
    }



    private TTSSpeechDTO transform(String timeZoneId , IncidentBean incidentBean, HandleBean  handleBean  )
             throws ParseException  {
        TTSSpeechDTO target = new TTSSpeechDTO() ;
        target.setAJID(   incidentBean.getId() );
        target.setAJLX( incidentBean.getIncidentTypeName() );

        target.setAJZT( incidentBean.getIncidentStatusName() );
        target.setAFDZ( incidentBean.getCrimeAddress() );

        if ( incidentBean.getAlarmStartTime() != null){
            target.setBJSJ(   DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,incidentBean.getAlarmStartTime() )    );
        }
        target.setLXDH( incidentBean.getAlarmPersonPhone());
        target.setZJYHM( incidentBean.getSquadronOrganizationName()  );
        String BBNR = "" ; //播放内容
        if( incidentBean.getWhetherTestSign() != null && incidentBean.getWhetherTestSign() == 1 ){
            BBNR = BBNR + "测试警情," ;
        }
        BBNR = BBNR + incidentBean.getIncidentTypeName() ; //案件类型
        BBNR = BBNR + ",地点," +  incidentBean.getCrimeAddress() ; //案件地址

        String CDBBNR = "," ;
        //播放内容 出动单位  出动车辆信息
        List<HandleOrganizationBean> handleOrganizationBeanList = handleBean.getHandleOrganizationBean() ;
        if( handleOrganizationBeanList != null && handleOrganizationBeanList.size() > 0 ){
            for(  HandleOrganizationBean handleOrganizationBean :  handleOrganizationBeanList  ){
                String  organizationName = handleOrganizationBean.getOrganizationName() ; //出动单位
                List<HandleOrganizationVehicleBean>  handleOrganizationVehicleBeanList  = handleOrganizationBean.getHandleOrganizationVehicleBean()  ;
                if(  handleOrganizationVehicleBeanList != null && handleOrganizationVehicleBeanList.size() > 0 ){
                    CDBBNR =  CDBBNR +  organizationName  + "出动," ;
                    for( HandleOrganizationVehicleBean handleOrganizationVehicleBean :  handleOrganizationVehicleBeanList ){
                        String  vehicleName  = handleOrganizationVehicleBean.getVehicleName() ;
                        CDBBNR  = CDBBNR + vehicleName + "," ;
                    }
                }
            }
        }
        BBNR = BBNR + CDBBNR ;

        target.setBBNR( BBNR );

        return  target ;
    }


    @Transactional
    @Override
    public String buildSpeakToFile (  EarlyWarningBean earlyWarningBean     ){
        logService.infoLog(logger, "service", "buildSpeakToFile", "service is started...");
        Long logStart = System.currentTimeMillis();

        String  speakToFileUrl = "" ;

        try {
            TTSSpeechDTO ttsSpeechDTO =  transform(env.getProperty("timeZone") , earlyWarningBean );
            //设置请求头参数
            Map<String, Object> headers = new HashMap<>();
            headers.put( "Content-Type","application/json;charset=UTF-8") ;
            String result  =  restClientInvoke.post(String.format("%s/rest/v1/tts/speakToFile", ttsSpeechToFileUrl ) ,
                    ttsSpeechDTO.getBBNR() , String.class , headers );
            logService.writeLog(  logger, "restful", "buildSpeakToFile", "the  restful service successfully  "
                    +   result  );
            if(  Strings.isNotBlank( result )  ){
                DataVO resultType = JSONObject.parseObject( result , DataVO.class ) ;
                if(  resultType.getErrorCode() == 0 ){
                    String localHostFileUrl = ( String ) resultType.getData() ;
                    //对返回路径 的分割符进行特殊处理
                    if(Strings.isNotBlank( localHostFileUrl) ){
                        localHostFileUrl = localHostFileUrl.trim().replace("\\","/") ;
                        String fileName = localHostFileUrl.substring( localHostFileUrl.lastIndexOf( "/" ) + 1 ) ;
                        speakToFileUrl = ttsSpeechToFileUrl + "/rest/v1/tts/fileDownload/" + fileName ;
                    }
                }
            }
        } catch (Exception ex) {
            logService.erorLog(  logger, "restful", "buildSpeakToFile", "the  restful service fail ", ex );
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "buildSpeakToFile", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return speakToFileUrl;
    }


    private TTSSpeechDTO transform(String timeZoneId ,  EarlyWarningBean earlyWarningBean   )
            throws ParseException  {
        TTSSpeechDTO target = new TTSSpeechDTO() ;
        target.setAJID(   earlyWarningBean.getIncidentId() );
        target.setAJLX( earlyWarningBean.getIncidentTypeName() );

        target.setAFDZ( earlyWarningBean.getCrimeAddress() );

        String BBNR = "" ; //播放内容
        if( earlyWarningBean.getWhetherTestSign() != null && earlyWarningBean.getWhetherTestSign() == 1 ){
            BBNR = BBNR + "测试警情," ;
        }
        BBNR = BBNR + earlyWarningBean.getIncidentTypeName() ; //案件类型
        BBNR = BBNR + ",地点" +  earlyWarningBean.getCrimeAddress() ; //案件地址

        target.setBBNR( BBNR );

        return  target ;
    }

}
