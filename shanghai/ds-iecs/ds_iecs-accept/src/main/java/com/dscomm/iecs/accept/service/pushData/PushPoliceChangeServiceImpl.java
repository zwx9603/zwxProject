package com.dscomm.iecs.accept.service.pushData;


import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.restful.vo.UdpVO.*;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.UnTrafficAlarmService;
import com.dscomm.iecs.accept.utils.transform.UdpMessageTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("pushPoliceChangeServiceImpl")
public class PushPoliceChangeServiceImpl implements PushPoliceChangeService {

    private static final Logger logger = LoggerFactory.getLogger(PushPoliceChangeServiceImpl.class);
    private LogService logService;
    private String policeUrl;
    private Environment env;
    private RestClientInvoke restClientInvoke = null;
    private Boolean whetherUdp;
    private UserService userService;
    private IncidentService incidentService;
    private UnTrafficAlarmService unTrafficAlarmService ;

    @Autowired
    public PushPoliceChangeServiceImpl(LogService logService,
                                       Environment env,UserService userService,IncidentService incidentService ,
                                       UnTrafficAlarmService unTrafficAlarmService ) {
        this.logService = logService;
        this.restClientInvoke = new RestClientInvoke();
        this.env = env;
        this.userService = userService;
        this.incidentService = incidentService;
        this.unTrafficAlarmService = unTrafficAlarmService ;
        policeUrl = env.getProperty("policeUrl");
        whetherUdp = Boolean.parseBoolean(env.getProperty("police.enable"));
    }

    @Transactional( readOnly =  true )
    @Override
    public Boolean sendPolicePhone(TelephoneBean telephoneBean) {
        logService.infoLog(logger, "service", "sendPolicePhone", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherUdp){
            return false;
        }
        UserInfo userInfo = userService.getUserInfo();

        logService.infoLog(  logger, "restful", "sendPolicePhone", "start thread to  sendPolicePhone" );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    UdpPhoneContentVO vo = UdpMessageTransformUtil.transfrom( env , telephoneBean,userInfo.getClientIp());
                    restClientInvoke.post(String.format("%s/udp/sendPhone", policeUrl),
                            vo, DataVO.class);
                    logService.infoLog(  logger, "restful", "sendPolicePhone", policeUrl + "/udp/sendPhone" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "sendPolicePhone", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPolicePhone", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional( readOnly =  true )
    @Override
    public Boolean sendPoliceIncident(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "sendPoliceIncident", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherUdp){
            return false;
        }
        UserInfo userInfo = userService.getUserInfo();

        logService.infoLog(  logger, "restful", "sendPoliceIncident", "start thread to  sendPoliceIncident" );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    UdpIncidentContentVO vo = UdpMessageTransformUtil.transfrom( env , incidentBean,userInfo.getClientIp());
                    restClientInvoke.post(String.format("%s/udp/sendIncident", policeUrl),
                            vo, DataVO.class);
                    logService.infoLog(  logger, "restful", "sendPoliceIncident", String.format(policeUrl + "/udp/sendIncident  successful,the request data:%s ",JSONObject.toJSONString(vo)) );
                    logService.infoLog(  logger, "restful", "sendPoliceIncident", "UdpIncidentContentVO is:"+vo.toString() );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "sendPoliceIncident", String.format(" %s/udp/sendIncident  fail,the request data:%s ",policeUrl,JSONObject.toJSONString(incidentBean)), ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional( readOnly =  true )
    @Override
    public Boolean sendPoliceHandle(HandleBean handleBean ) {
        logService.infoLog(logger, "service", "sendPoliceHandle", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherUdp){
            return false;
        }
        UserInfo userInfo = userService.getUserInfo();

        logService.infoLog(  logger, "restful", "sendPoliceHandle", "start thread to  sendPoliceHandle" );
        IncidentBean incidentBean = incidentService.findIncident(handleBean.getIncidentId(),false);
        if (incidentBean != null) {
            logService.infoLog(logger, "restful", "sendPoliceHelp", "start thread to  sendPoliceHelp");

            //根据案件id获得 非话务转警记录
            List<UnTrafficAlarmBean> unTrafficAlarmBeans = unTrafficAlarmService.findUnTrafficAlarm( handleBean.getIncidentId() ) ;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        UdpHandle119ContentVO vo = UdpMessageTransformUtil.transfrom( env , handleBean,incidentBean, unTrafficAlarmBeans, userInfo.getClientIp());
                        restClientInvoke.post(String.format("%s/udp/sendHandle", policeUrl),
                                vo, DataVO.class);
                        logService.infoLog(logger, "restful", "sendPoliceHandle", policeUrl + "/udp/sendHandle" + "  successful ");

                    } catch (Exception ex) {
                        logService.erorLog(logger, "restful", "sendPoliceHandle", "the  restful service fail ", ex);

                    }
                }
            });
            thread.start();
        }
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceHandle", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional( readOnly =  true )
    @Override
    public Boolean sendPoliceFeedBack(AccidentBean accidentBean) {
        logService.infoLog(logger, "service", "sendPoliceFeedBack", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherUdp){
            return false;
        }
        UserInfo userInfo = userService.getUserInfo();
        IncidentBean incidentBean = incidentService.findIncident(accidentBean.getIncidentId(),false);
        logService.infoLog(  logger, "restful", "sendPoliceFeedBack", "start thread to  sendPoliceHelp" );

        //根据案件id获得 非话务转警记录
        List<UnTrafficAlarmBean> unTrafficAlarmBeans = unTrafficAlarmService.findUnTrafficAlarm( accidentBean.getIncidentId() ) ;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    UdpFeedBackContentVO vo = UdpMessageTransformUtil.transform( env , accidentBean,incidentBean,unTrafficAlarmBeans,userInfo.getClientIp());
                    restClientInvoke.post(String.format("%s/udp/sendFeedBack", policeUrl),
                            vo, DataVO.class);
                    logService.infoLog(  logger, "restful", "sendPoliceFeedBack", policeUrl + "/udp/sendFeedBack" + "  successful " );

                } catch (Exception ex) {
                    logService.erorLog(  logger, "restful", "sendPoliceFeedBack", "the  restful service fail ", ex );

                }
            }
        });
        thread.start();
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceFeedBack", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional( readOnly =  true )
    @Override
    public Boolean sendPoliceHelp(OutsideInputInfo queryBean   ) {
        logService.infoLog(logger, "service", "sendPoliceHelp", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherUdp){
            return false;
        }
        UserInfo userInfo = userService.getUserInfo();
        IncidentBean incidentBean = incidentService.findIncident(queryBean.getIncidentId(),false);
        if (incidentBean != null){
            logService.infoLog(  logger, "restful", "sendPoliceHelp", "start thread to  sendPoliceHelp" );

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        UdpHelpContentVO vo = UdpMessageTransformUtil.transfrom(  env ,  queryBean ,incidentBean, userInfo , userInfo.getClientIp());
                        restClientInvoke.post(String.format("%s/udp/sendHelp", policeUrl),
                                vo, DataVO.class);
                        logService.infoLog(  logger, "restful", "sendPoliceHelp", policeUrl + "/udp/sendHelp" + "  successful " );

                    } catch (Exception ex) {
                        logService.erorLog(  logger, "restful", "sendPoliceHelp", "the  restful service fail ", ex );

                    }
                }
            });
            thread.start();
        }
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceHelp", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional( readOnly =  true )
    @Override
    public Boolean sendIncidentReceiveState( String incidentId , String  originalIncidentNumber ,String districtCode ) {
        logService.infoLog(logger, "service", "sendPoliceHelp", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (!whetherUdp){
            return false;
        }
        if (  Strings.isNotBlank( incidentId ) ){
            logService.infoLog(  logger, "restful", "sendPoliceHelp", "start thread to  sendPoliceHelp" );

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        // 发送已接收
                        UdpIncidentReceiveStateContentVO vo  = new UdpIncidentReceiveStateContentVO();
                        vo.setZQBH( incidentId ) ;
                        vo.setJJDBH( originalIncidentNumber );
                        vo.setZT("YJS");
                        vo.setXZQH(districtCode);
                        restClientInvoke.post(String.format("%s/udp/sendIncidentReceiveState", policeUrl),
                                vo, DataVO.class);
                        logService.infoLog(  logger, "restful", "sendPoliceHelp", policeUrl + "/udp/sendHelp" + "  successful " );

                    } catch (Exception ex) {
                        logService.erorLog(  logger, "restful", "sendPoliceHelp", "the  restful service fail ", ex );

                    }
                }
            });
            thread.start();
        }
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "sendPoliceHelp", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

}
