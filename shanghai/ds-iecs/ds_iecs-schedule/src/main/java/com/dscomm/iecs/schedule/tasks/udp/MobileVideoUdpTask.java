package com.dscomm.iecs.schedule.tasks.udp;

import com.dscomm.iecs.schedule.service.udp.MobileVideoUDPService;
import com.dscomm.iecs.schedule.service.udp.UdpServer;
import org.mx.StringUtils;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 描述：UDP监控接收移动视频信息
 *
 * @author YangFuXi Date Time 2018/12/1 12:54
 */
@Component("mobileVideoUdpTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MobileVideoUdpTask extends BaseTask {


    private static final Logger logger = LoggerFactory.getLogger( MobileVideoUdpTask.class);
    private MobileVideoUDPService service;
    private Environment env;

    public MobileVideoUdpTask(  MobileVideoUDPService service, Environment env) {
        this.service = service;
        this.env = env;
    }

    @Override
    public void invoke() {

        try {
            logger.info(  "start excute tasks");
            long start = System.currentTimeMillis();

            String port = env.getProperty("udps.mobileVideoUdpTask.port");

            logger.info(  String.format("UDP monitor  monitor port:%s ", port ));

            UdpServer server = null;
            if (!StringUtils.isBlank(port)) {

                int udpPort = Integer.parseInt(port);
                server = new UdpServer(udpPort);

                while (true) {
                    try {
                        String message = server.read("GB2312");
                        if (!StringUtils.isBlank(message)) {
                            service.transport(message);
                        }
                    } catch (IOException ex) {
                        logger.error(  "UDP monitor fail ", ex);
                    }catch (Exception ex){
                        logger.error(  "UDP monitor fail ", ex);
                    }
                }

            }

            long end = System.currentTimeMillis();
            logger.info(  String.format("end excute tasks,totalTime:%sms", end - start));

        } catch ( Exception ex) {
            logger.info(  "UDP monitor fail ", ex);
        }

    }



}
