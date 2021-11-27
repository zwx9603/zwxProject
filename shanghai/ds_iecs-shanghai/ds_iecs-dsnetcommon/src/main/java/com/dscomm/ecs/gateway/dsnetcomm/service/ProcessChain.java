package com.dscomm.ecs.gateway.dsnetcomm.service;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.ecs.gateway.dsnetcomm.config.DsNetCommonConfigBean;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.DsMsgHeader;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.HeaderBO;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.MessageBO;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.MessageData;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:接收消息处理类
 *
 * @author YangFuxi
 * Date Time 2020/9/17 18:29
 */
public class ProcessChain {
    private static final Logger logger = LoggerFactory.getLogger(ProcessChain.class);
    private ApplicationContext context;
    private Map<String, List<Processer>> processers;
    private String charset = "UTF-8";
    private int xmlMsgId = 0xAA00;

    public ProcessChain(ApplicationContext context, DsNetCommonConfigBean config) {
        this.context = context;
        if (!StringUtils.isBlank(charset)) {
            this.charset = charset;
        } else {
            if (StringUtils.isBlank(config.getCharset())) {
                this.charset = config.getCharset();
            }
        }

    }

    public void init() {
        processers = new HashMap<>();
        Map<String, Processer> map = context.getBeansOfType(Processer.class);
        if (map != null && !map.isEmpty()) {
            map.keySet().forEach(name -> {
                Processer processer = map.get(name);
                if (processer != null && !StringUtils.isBlank(processer.getmsgID())) {
                    List<Processer> list = this.processers.get(processer.getmsgID());
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    if (StringUtils.isBlank(processer.getCharset())) {
                        processer.setCharset(charset);
                    }
                    list.add(processer);
                    processers.put(processer.getmsgID(), list);
                }
            });
        }
    }

    public void destroy() {
        processers = null;
    }

    public void onMessage(MessageData messageData) {
        try {
            DsMsgHeader header = messageData.getHeader();
            String messageId = toHexString(header.getMsgID());
            if (!StringUtils.isBlank(messageId)) {
                MessageBO messageBO = new MessageBO();
                HeaderBO headerBO = new HeaderBO();
                headerBO.setDestID(toHexString(header.getDestID()));
                headerBO.setDestType(toHexString(header.getDestType()));
                headerBO.setMsgID(messageId);
                headerBO.setSrcID(toHexString(header.getSrcID()));
                headerBO.setSrcType(toHexString(header.getSrcType()));
                headerBO.setTransType(header.getTransType());
                messageBO.setHeader(headerBO);
                //确认是xml消息还是普通消息
                if ("0xAAA".equals(headerBO.getMsgID())){
                    return;
                }else {
                    List<Processer> list = processers.get(messageId);
                    if (list != null && list.size() > 0) {
                        list.forEach(processer -> {
                            try {
                                messageBO.setMessage(new String(messageData.getData(), processer.getCharset()));
                                processer.process(messageBO);
                                if (logger.isInfoEnabled()) {
                                    logger.info(String.format("process success,data:%s", JSONObject.toJSONString(messageBO)));
                                }
                            } catch (Exception ex) {
                                logger.error("process message fail when transform message", ex);
                            }
                        });
                    }
                }

            }

        } catch (Exception ex) {
            logger.error("process message fail(processChain)", ex);
        }
    }

    String toHexString(int i) {
        return Integer.toHexString(i & 0xFFF);
    }
}
