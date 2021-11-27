package com.dscomm.iecs.integrate.tasks;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.service.UdpReceivePoliceService;
import com.dscomm.iecs.integrate.utils.UdpReceiveServer;
import org.mx.StringUtils;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 描述：UDP监控  接收公安推送信息
 *
 * @author YangFuXi Date Time 2018/12/1 12:54
 */
@Component("udpPoliceReceiveTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UdpPoliceReceiveTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(UdpPoliceReceiveTask.class);
    private LogService logService;
    private UdpReceivePoliceService udpReceivePolice ;
    private Environment env;
    private Boolean localHostUdpEnable ;

    @Autowired
    public UdpPoliceReceiveTask(   Environment env  ,LogService logService , UdpReceivePoliceService  udpReceivePolice ) {
        this.env = env;
        this.logService = logService ;
        this.udpReceivePolice = udpReceivePolice ;

    }

    @Override
    public void invoke() {
        localHostUdpEnable = Boolean.parseBoolean(env.getProperty("localHostUdpEnable"));
        String port = env.getProperty("localHostUdpPort");
        String encoding = env.getProperty("localHostUdpEncoding","UTF-8");

        if ( localHostUdpEnable && !StringUtils.isBlank(port) ) {
            try {
                int udpPort = Integer.parseInt(port);
                UdpReceiveServer server = new UdpReceiveServer(udpPort);

                logService.infoLog(logger, "task", "udpPoliceReceiveTask", "udp port:" + port );

                while (true) {
                    try{
                        String message = server.read(encoding);
                        if (!StringUtils.isBlank(message)) {

                            logService.writeLog(logger, "task", "udpPoliceReceiveTask", "udp message :" + message );

                            String type = udpReceivePolice.analyse(message);
                            switch (type){
                                case "STHY01":
                                    udpReceivePolice.transformPhone(message);
                                    break;
                                case "STHY02":
                                    udpReceivePolice.transformIncident(message);
                                    break;
                                case "STHY03":
                                    udpReceivePolice.transformHandle(message);
                                    break;
                                case "STHY04":
                                    udpReceivePolice.transformHelp(message);
                                    break;
                                case "STHY09":
                                    udpReceivePolice.transformIncidentReceiveState(message);
                                    break;
                                case "STHY08":
                                    udpReceivePolice.transformHeart(message);
                                    break;
                                default:
                                    break;
                            }
                        }

                    } catch (IOException ex) {
                        logService.erorLog(logger, "task", "udpPoliceReceiveTask", "read udp message "   , ex );
                    }catch (Exception ex){
                        logService.erorLog(logger, "task", "udpPoliceReceiveTask", "execution error"   , ex );
                    }

                }
            }catch (Exception ex){
                logService.erorLog(logger, "task", "udpPoliceReceiveTask", "udp port:" + port   , ex );
            }
        }
    }
}
