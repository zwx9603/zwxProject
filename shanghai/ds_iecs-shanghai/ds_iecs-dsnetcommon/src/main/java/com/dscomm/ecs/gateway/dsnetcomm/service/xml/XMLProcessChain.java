package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import com.dscomm.ecs.gateway.dsnetcomm.config.DsNetCommonConfigBean;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.MessageData;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:处理xml信息
 *
 * @author YangFuxi
 * Date Time 2020/9/25 19:05
 */
public class XMLProcessChain {
    private static final Logger logger = LoggerFactory.getLogger(XMLProcessChain.class);
    private BodyFactory bodyFactory;
    private List<XmlProcessor> processers;
    private ApplicationContext context;
    private int MsgId = 0xAA00;//xml 消息号
    private String charset = "UTF-8";

    public XMLProcessChain(ApplicationContext context, BodyFactory bodyFactory, DsNetCommonConfigBean configBean) {
        this.context = context;
        this.bodyFactory = bodyFactory;
        if (!StringUtils.isBlank(configBean.getCharset())) {
        }
        this.charset = configBean.getCharset();
    }

    public void init() {
        try {
            logger.info("start init XMLProcessChain...");
            Map<String, XmlProcessor> map = context.getBeansOfType(XmlProcessor.class);
            processers = new ArrayList<>(map.values());
            logger.info("init XMLProcessChain success.");
        } catch (Exception ex) {
            logger.error("init XMLProcessChain fail", ex);
        }
    }

    public void onMessage(MessageData messageData) {
        try {
            if (messageData == null || messageData.getHeader() == null || messageData.getHeader().getMsgID() != MsgId) {
                return;
            } else {
                MainMsg mainMsg = new MainMsg();
                mainMsg.setBodyFactory(bodyFactory);
                if (processers != null && !processers.isEmpty()) {
                    processers.forEach(processer -> {
                        try {
                            String charset = processer.getCharset();
                            if (!StringUtils.isBlank(charset)) {
                                charset = this.charset;
                            }
                            String xml = new String(messageData.getData(), charset);
                            mainMsg.fromXml(xml);
                            processer.process(messageData.getHeader(), mainMsg);
                        } catch (Exception ex) {
                            logger.error("one processer process message fail", ex);
                        }
                    });
                }

            }
        } catch (Exception ex) {
            logger.error("XMLProcessChain execute onMessage fail", ex);
        }
    }
}
