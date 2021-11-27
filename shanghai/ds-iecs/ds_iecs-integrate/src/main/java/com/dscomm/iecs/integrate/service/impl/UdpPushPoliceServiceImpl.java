package com.dscomm.iecs.integrate.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.integrate.service.UdpPushPoliceService;
import com.dscomm.iecs.integrate.service.udpBean.*;
import com.dscomm.iecs.integrate.utils.UdpSendServer;
import com.dscomm.iecs.integrate.utils.XmlTransfromUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 描述： UDP 推送公安警情
 *
 * @author YangFuXi Date Time 2018/11/29 17:18
 */
@Component("udpPushPoliceServiceImpl")
public class UdpPushPoliceServiceImpl implements UdpPushPoliceService {
    private static final Logger logger = LoggerFactory.getLogger(UdpPushPoliceServiceImpl.class);
    private LogService logService;
    private Environment env ;
    private String encoding;
    private String ip;
    private Integer port;
    private String policeUdpUrl ;
    private String localHostUdpPort ;
    private String localHostUdpIp ;
    private String localHostUdpUrl ;
    private String localHostUdpEncoding ;
    private Boolean policeUdpEnable ;



    @Autowired
    public UdpPushPoliceServiceImpl( LogService logService ,  Environment env    ) {
        this.logService = logService ;
        this.env = env;

        encoding = env.getProperty("policeUdpEncoding","UTF-8");
        ip = env.getProperty("policeUdpSendIp","127.0.0.1");
        port = Integer.parseInt(  env.getProperty("policeUdpSendPort","8434")  ) ;
        policeUdpUrl = ip + ":" + port ;
        localHostUdpIp =    env.getProperty("localHostUdpIp","127.0.0.1")   ;
        localHostUdpPort =    env.getProperty("localHostUdpPort","8434")   ;
        localHostUdpEncoding = env.getProperty("localHostUdpEncoding","UTF-8");
        localHostUdpUrl = localHostUdpIp + ":" + localHostUdpPort ;
        policeUdpEnable = Boolean.parseBoolean(env.getProperty("policeUdpEnable"));

    }



    @Override
    public void sendPhone(UdpPhoneContentVO phone) {
        try {
            if( !policeUdpEnable ){
                return ;
            }

            UdpPhoneVO vo = new UdpPhoneVO();
            vo.setMsgType("STHY11");
            vo.setMsgSender(localHostUdpUrl );
            vo.setMsgReceiver(policeUdpUrl);
            vo.setMsgSendTime(  DateHandleUtils.getDatePattern( env.getProperty("timeZone") , System.currentTimeMillis()  ,"yyyy/MM/dd HH:mm:ss" )   );
            vo.setMsgContent( phone );

            String xml = XmlTransfromUtil.toXml(vo,UdpPhoneVO.class,encoding);
            logService.writeLog( logger, "service", "sendPhone", " sendPhone message" + xml   );
            UdpSendServer.send(encoding,xml,ip,port ,localHostUdpEncoding );

            logService.writeLog( logger, "service", "sendPhone", " sendPhone message" + xml   );
        }catch (Exception ex ){
            logService.erorLog( logger, "service", "sendPhone", " sendPhone message" , ex );
        }
    }

    @Override
    public void sendIncident( UdpIncidentContentVO incident ) {
        try {
            if( !policeUdpEnable ){
                return ;
            }

            UdpIncidentVO vo = new UdpIncidentVO();
            vo.setMsgType("STHY12");
            vo.setMsgSender(localHostUdpUrl );
            vo.setMsgReceiver(policeUdpUrl);
            vo.setMsgSendTime(  DateHandleUtils.getDatePattern( env.getProperty("timeZone") ,  System.currentTimeMillis()  ,"yyyy/MM/dd HH:mm:ss" )   );
            vo.setMsgContent( incident );


            String xml = XmlTransfromUtil.toXml(vo,UdpIncidentVO.class,encoding);
            logService.infoLog( logger, "service", "sendIncident", " sendIncident message" + xml   );

            UdpSendServer.send(encoding,xml,ip,port ,localHostUdpEncoding );

            logService.writeLog( logger, "service", "sendIncident", " sendIncident message" + xml   );
        }catch (Exception ex ){
            logService.erorLog( logger, "service", "sendIncident", " sendIncident message" , ex );
        }

    }

    @Override
    public void sendHandle( UdpHandle119ContentVO  handle) {
        try {
            if( !policeUdpEnable ){
                return ;
            }

            UdpHandle119VO vo = new UdpHandle119VO();
            vo.setMsgType("STHY13");
            vo.setMsgSender(localHostUdpUrl );
            vo.setMsgReceiver(policeUdpUrl);
            vo.setMsgSendTime(  DateHandleUtils.getDatePattern( env.getProperty("timeZone") ,  System.currentTimeMillis()   ,"yyyy/MM/dd HH:mm:ss" )   );
            vo.setMsgContent( handle );

            String xml = XmlTransfromUtil.toXml(vo,UdpHandle119VO.class,encoding);

            logService.infoLog( logger, "service", "sendIncident", " sendHandle message" + xml   );

            UdpSendServer.send(encoding,xml,ip,port ,localHostUdpEncoding );

            logService.writeLog( logger, "service", "sendHandle", " sendHandle message" + xml   );
        }catch ( Exception ex ){
            logService.erorLog( logger, "service", "sendHandle", " sendHandle message" , ex );
        }

    }

    @Override
    public void sendFeedBack( UdpFeedBackContentVO feedback  ) {
        try {
            if( !policeUdpEnable ){
                return ;
            }

            UdpFeedBackVO vo = new UdpFeedBackVO();
            vo.setMsgType("STHY14");
            vo.setMsgSender(localHostUdpUrl );
            vo.setMsgReceiver(policeUdpUrl);
            vo.setMsgSendTime(  DateHandleUtils.getDatePattern( env.getProperty("timeZone") ,  System.currentTimeMillis()   ,"yyyy/MM/dd HH:mm:ss" )   );
            vo.setMsgContent( feedback );

            String xml = XmlTransfromUtil.toXml(vo,UdpFeedBackVO.class,encoding);
            logService.infoLog( logger, "service", "sendFeedBack", " sendFeedBack message" + xml   );

            UdpSendServer.send(encoding,xml,ip,port ,localHostUdpEncoding );


            logService.writeLog( logger, "service", "sendFeedBack", " sendFeedBack message" + xml   );
        }catch (Exception ex){
            logService.erorLog( logger, "service", "sendFeedBack", " sendFeedBack message" , ex );
        }
    }
    @Override
    public void sendHelp( UdpHelpContentVO help ) {
        try {
            if( !policeUdpEnable ){
                return ;
            }

            UdpHelpVO vo = new UdpHelpVO();
            vo.setMsgType("STHY15");
            vo.setMsgSender(localHostUdpUrl );
            vo.setMsgReceiver(policeUdpUrl);
            vo.setMsgSendTime(  DateHandleUtils.getDatePattern( env.getProperty("timeZone") ,  System.currentTimeMillis()  ,"yyyy/MM/dd HH:mm:ss" )   );
            vo.setMsgContent( help );


            String xml = XmlTransfromUtil.toXml(vo,UdpHelpVO.class,encoding);


            logService.infoLog( logger, "service", "sendHelp", " sendHelp message" + xml   );

            UdpSendServer.send(encoding,xml,ip,port ,localHostUdpEncoding );

            logService.writeLog( logger, "service", "sendHelp", " sendHelp message" + xml   );

        }catch ( Exception ex ){
            logService.erorLog( logger, "service", "sendHelp", " sendHelp message" , ex );
        }
    }

    @Override
    public void sendIncidentReceiveState( UdpIncidentReceiveStateContentVO  incidentReceiveState ) {
        try {
            if( !policeUdpEnable ){
                return ;
            }

            UdpIncidentReceiveStateVO vo  = new UdpIncidentReceiveStateVO();
            vo.setMsgType("STHY19");
            vo.setMsgSender(localHostUdpUrl );
            vo.setMsgReceiver(policeUdpUrl);
            vo.setMsgSendTime(  DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone") ,  System.currentTimeMillis()   )   );
            vo.setMsgContent( incidentReceiveState );

            String xml = XmlTransfromUtil.toXml(vo,UdpIncidentReceiveStateVO.class,encoding);
            logService.infoLog( logger, "service", "sendIncidentReceiveState", " sendIncidentReceiveState message" + xml   );

            UdpSendServer.send(encoding,xml,ip,port ,localHostUdpEncoding );

            logService.writeLog( logger, "service", "sendIncidentReceiveState", " sendIncidentReceiveState message" + xml   );

        }catch ( Exception ex ){
            logService.erorLog( logger, "service", "sendIncidentReceiveState", " sendIncidentReceiveState message" , ex );
        }
    }

    @Override
    public void sendHeart(  UdpHeartContentVO  heart ) {
        try {
            if( !policeUdpEnable ){
                return ;
            }

            UdpHeartVO vo  = new UdpHeartVO();
            vo.setMsgType("STHY18");
            vo.setMsgSender(localHostUdpUrl );
            vo.setMsgReceiver(policeUdpUrl);
            vo.setMsgSendTime(  DateHandleUtils.getDatePattern( env.getProperty("timeZone") ,  System.currentTimeMillis()   ,"yyyy/MM/dd HH:mm:ss" )   );
            vo.setMsgContent( heart );

            String xml = XmlTransfromUtil.toXml(vo,UdpHeartVO.class,encoding);

            UdpSendServer.send(encoding,xml,ip,port ,localHostUdpEncoding );

        }catch ( Exception ex ){
            logService.erorLog( logger, "service", "sendHeart", " sendHeart message" , ex );
        }
    }



}
