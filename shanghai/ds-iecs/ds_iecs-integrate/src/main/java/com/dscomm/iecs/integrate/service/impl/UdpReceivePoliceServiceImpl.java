package com.dscomm.iecs.integrate.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.ext.incident.alarm.INCIDENT_ALARM_MODE_YYZDBJ_GZ;
import com.dscomm.iecs.ext.incident.register.INCIDENT_REGISTER_MODE_110LA;
import com.dscomm.iecs.ext.incident.source.INCIDENT_SOURCE_GA;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.ReceiveUnTrafficAlarmVO;
import com.dscomm.iecs.integrate.restful.vo.UnTrafficAlarmVO;
import com.dscomm.iecs.integrate.service.OutsideService;
import com.dscomm.iecs.integrate.service.UdpPushPoliceService;
import com.dscomm.iecs.integrate.service.UdpReceivePoliceService;
import com.dscomm.iecs.integrate.service.udpBean.*;
import com.dscomm.iecs.integrate.tasks.UdpPoliceReceiveTask;
import com.dscomm.iecs.integrate.utils.XmlTransfromUtil;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.client.rest.RestInvokeException;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

/**
 * 描述：  UDP 接收公安消息
 *
 * @author YangFuXi Date Time 2018/11/29 17:18
 */
@Component("udpReceivePoliceServiceImpl")
public class UdpReceivePoliceServiceImpl implements UdpReceivePoliceService {

    private static final Logger logger = LoggerFactory.getLogger(UdpPoliceReceiveTask.class);
    private LogService logService;
    private UdpPushPoliceService udpPushPoliceService ;
    private OutsideService outsideService ;

    private SimpleDateFormat sdf;
    private Environment env;
    private String acceptUrl ;
    private RestClientInvoke restClientInvoke = null ;

    @Autowired
    public UdpReceivePoliceServiceImpl(
            LogService logService , UdpPushPoliceService udpPushPoliceService   ,
            OutsideService outsideService ,Environment env  ) {
        this.logService = logService ;
        this.env = env;
        this.udpPushPoliceService = udpPushPoliceService ;
        this.outsideService = outsideService ;
        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.acceptUrl = env.getProperty("acceptUrl");
        this.restClientInvoke = new RestClientInvoke();
    }


    @Transactional(readOnly = true)
    @Override
    public String analyse(String message) {

            logService.infoLog(logger, "service", "analyse", "service is started...");
            Long logStart = System.currentTimeMillis();

            UdpMessageTypeVO udpMessage = XmlTransfromUtil.fromXml(message,UdpMessageTypeVO.class);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "analyse", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            analyseMessage( udpMessage , message  ) ;

            return udpMessage.toString();
    }

    private void analyseMessage(  UdpMessageTypeVO udpMessage , String message ){

        if( "STHY08".equals( udpMessage.getMsgType() )  ||  "STHY06".equals( udpMessage.getMsgType())  ){
            return ;
        }
        udpMessage.setMsgContent( message );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    restClientInvoke.post(String.format("%s/rest/iecs/v1.0/police/analyse", acceptUrl),
                            udpMessage, DataVO.class);
                } catch (RestInvokeException ex) {
                    logService.erorLog(logger, "service", "analyse", String.format("Invoke the %s restful service fail.", acceptUrl), ex);
                }
            }
        });
        thread.start();
    }


    @Transactional(readOnly = true)
    @Override
    public void transformPhone(String message) {

        try {
            UdpPhoneVO vo = XmlTransfromUtil.fromXml(message, UdpPhoneVO.class);
            //todo 119暂无处理
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "transformPhone", " transformPhone message", ex);
        }
    }

    @Override
    public void transformIncident(String message) {
        try {
            UdpIncidentVO vo = XmlTransfromUtil.fromXml(message,UdpIncidentVO.class);
            // 发送已到达



            // 非话务转警
            ReceiveUnTrafficAlarmVO receiveUnTrafficAlarmVO = new ReceiveUnTrafficAlarmVO();
            UnTrafficAlarmVO unTrafficAlarmVO = new UnTrafficAlarmVO();
            unTrafficAlarmVO.setIncidentId(vo.getMsgContent().getJJDBH());
            unTrafficAlarmVO.setAlarmPhone( vo.getMsgContent().getDHHM());
            unTrafficAlarmVO.setAlarmPersonName( vo.getMsgContent().getYHXM() );
            unTrafficAlarmVO.setAlarmAddress( vo.getMsgContent().getZJDZ() );
            unTrafficAlarmVO.setAlarmTime( sdf.parse(vo.getMsgContent().getHRSJ()).getTime() );
            unTrafficAlarmVO.setAlarmStartTime(sdf.parse(vo.getMsgContent().getHRSJ()).getTime());
            unTrafficAlarmVO.setCrimeAddress(vo.getMsgContent().getHZDZ());

            unTrafficAlarmVO.setIncidentTypeCode( vo.getMsgContent().getHZLX() );
            unTrafficAlarmVO.setDisposalObjectCode( vo.getMsgContent().getRSWZ() );
            unTrafficAlarmVO.setIncidentLevelCode( vo.getMsgContent().getZHDJ() );
            unTrafficAlarmVO.setBuildingStructureCode(vo.getMsgContent().getJZLX());
            unTrafficAlarmVO.setBurningFloor(vo.getMsgContent().getQHLC());

            unTrafficAlarmVO.setLatitude(vo.getMsgContent().getX());
            unTrafficAlarmVO.setLongitude(vo.getMsgContent().getY());
            unTrafficAlarmVO.setIncidentDescribe(vo.getMsgContent().getJYJQ());
            unTrafficAlarmVO.setDistrictCode(vo.getMsgContent().getXZQH());

            //公安转警的报警方式
            unTrafficAlarmVO.setAlarmModeCode(INCIDENT_ALARM_MODE_YYZDBJ_GZ.getCode() );
            unTrafficAlarmVO.setRegisterModeCode(INCIDENT_REGISTER_MODE_110LA.getCode() );
            unTrafficAlarmVO.setIncidentSource(INCIDENT_SOURCE_GA.getCode() );

            receiveUnTrafficAlarmVO.setIncidentId( vo.getMsgContent().getJJDBH() );
            receiveUnTrafficAlarmVO.setUnTrafficAlarmVO(unTrafficAlarmVO);
            receiveUnTrafficAlarmVO.setWhetherSaveIncident( false  );

            outsideService.unTrafficAlarm( receiveUnTrafficAlarmVO ) ;

            UdpIncidentReceiveStateContentVO incidnetReceiverState  = new UdpIncidentReceiveStateContentVO();
            incidnetReceiverState.setJJDBH(vo.getMsgContent().getJJDBH());
            incidnetReceiverState.setZT("YDD");
            incidnetReceiverState.setXZQH(vo.getMsgContent().getXZQH());
            udpPushPoliceService.sendIncidentReceiveState( incidnetReceiverState );

        }catch (Exception ex ){
            logService.erorLog( logger, "service", "transformIncident", " transformIncident message" , ex );
        }

    }

    @Override
    public void transformHandle(String message) {
        try {
            UdpHandle110VO vo = XmlTransfromUtil.fromXml(message,UdpHandle110VO.class);
            // todo 119暂无处理
        }catch (Exception ex){
            logService.erorLog( logger, "service", "transformHandle", " transformHandle message" , ex );
        }
    }

    @Override
    public void transformHelp(String message) {

        try {
            logService.infoLog(logger, "service", "transformHelp", "service is started...");
            Long logStart = System.currentTimeMillis();

            UdpHelpVO udpHelp  = XmlTransfromUtil.fromXml(message,UdpHelpVO.class);

            try {
                restClientInvoke.post(String.format("%s/rest/iecs/v1.0/police/udpHelp", acceptUrl),
                        udpHelp, DataVO.class);


            } catch (RestInvokeException ex) {
                logService.erorLog(logger, "service", "transformHelp", String.format("Invoke the %s restful service fail.", acceptUrl), ex);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "transformHelp", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "transformHelp", "execution error", ex);
            throw new IntegrateException( IntegrateException.IntegrateErrors.REQUEST_OUT_SIDE_FAIL ) ;
        }


    }

    @Override
    public void transformIncidentReceiveState(String message) {
        try {
            UdpIncidentReceiveStateVO vo = XmlTransfromUtil.fromXml(message,UdpIncidentReceiveStateVO.class);
            //todo 119无处理操作
        }catch (Exception ex){
            logService.erorLog( logger, "service", "transformIncidentReceiveState", " transformIncidentReceiveState message" , ex );
        }

    }

    @Override
    public void transformHeart(String message) {

        try {
            UdpHeartVO vo = XmlTransfromUtil.fromXml(message,UdpHeartVO.class);
            // 返回心跳报文
            UdpHeartContentVO msgContent =  vo.getMsgContent();
            udpPushPoliceService.sendHeart( msgContent );

        }catch (Exception ex){
            logService.erorLog( logger, "service", "transformHeart", " transformHeart message" , ex );
        }
    }




}
