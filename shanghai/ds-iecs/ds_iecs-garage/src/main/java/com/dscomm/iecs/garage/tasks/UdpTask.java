package com.dscomm.iecs.garage.tasks;

import com.dscomm.iecs.garage.service.UdpService;
import com.dscomm.iecs.garage.utils.UdpServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mx.StringUtils;
import org.mx.spring.task.BaseTask;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;

/**
 * 描述：UDP监控 车库变动消息信息
 *
 * @author YangFuXi Date Time 2018/12/1 12:54
 */
@Component("udpTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UdpTask extends BaseTask {
    private Log logger = LogFactory.getLog(UdpTask.class);
    private UdpService udpService ;
    private Environment env;

    public UdpTask(UdpService udpService , Environment env) {
        this.udpService = udpService ;
        this.env = env;
    }

    @Override
    public void invoke() {
        String port = env.getProperty("udpPort");
        UdpServer server = null;
        if (!StringUtils.isBlank(port)) {
            try {
                int udpPort = Integer.parseInt(port);
                server = new UdpServer(udpPort);
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("Udp监控开始，监控端口为:%s", port));
                }
                while (true) {
                    try {
                        String message = server.read("GB2312");
                        if (!StringUtils.isBlank(message)) {
                            udpService.transport(message);
                        }
                    } catch (IOException ex) {
                        if (logger.isErrorEnabled()) {
                            logger.error(String.format("UDP监控读取数据失败"),ex);
                        }
                    }catch (Exception ex){
                        if (logger.isErrorEnabled()){
                            logger.error(String.format("UDP监控异常"),ex);
                        }
                    }
                }

            } catch (SocketException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error(String.format("UDP监控端口:%s 失败.", port));
                }
            }
        }
    }
}
