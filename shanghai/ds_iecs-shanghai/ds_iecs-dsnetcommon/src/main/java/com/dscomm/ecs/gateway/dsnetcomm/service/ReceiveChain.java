package com.dscomm.ecs.gateway.dsnetcomm.service;

import com.dscomm.ecs.gateway.dsnetcomm.service.bo.DsMsgHeader;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.MessageData;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.XMLProcessChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Integer.toHexString;

/**
 * 描述:消息接收者
 *
 * @author YangFuxi
 * Date Time 2020/9/29 10:20
 */
public class ReceiveChain {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveChain.class);
    private ProcessChain processChain;
    private XMLProcessChain xmlProcessChain;

    public ReceiveChain(ProcessChain processChain, XMLProcessChain xmlProcessChain) {
        this.processChain = processChain;
        this.xmlProcessChain = xmlProcessChain;
    }

    public void onReceive(MessageData messageData) {
        try {
            DsMsgHeader header = messageData.getHeader();
            //xml消息
            String messageId = String.format("0x%s", toHexString(header.getMsgID() & 0xFFF));
            if ("0xAA00".equals(messageId)) {
                xmlProcessChain.onMessage(messageData);
            } else {
                //非xml消息
                processChain.onMessage(messageData);
            }
        } catch (Exception ex) {
            logger.error("ReceiveChain execute fail", ex);
        }
    }

}
