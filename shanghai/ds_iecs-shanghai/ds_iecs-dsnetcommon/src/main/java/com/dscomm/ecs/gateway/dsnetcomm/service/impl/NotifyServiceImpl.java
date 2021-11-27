package com.dscomm.ecs.gateway.dsnetcomm.service.impl;

import com.dscomm.ecs.gateway.dsnetcomm.service.DsNetCommonManger;
import com.dscomm.ecs.gateway.dsnetcomm.service.NotifyService;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.SendBodyMessage;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.SendMessageBO;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.TransmitXmlMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:发送消息服务
 *
 * @author YangFuxi
 * Date Time 2020/9/29 23:57
 */
@Component
public class NotifyServiceImpl implements NotifyService {
    private static final Logger logger=LoggerFactory.getLogger(NotifyServiceImpl.class);
    private DsNetCommonManger manger;

    @Autowired
    public NotifyServiceImpl(DsNetCommonManger manger) {
        this.manger = manger;
    }

    @Override
    public Boolean sendMessage(SendMessageBO bo) {
        try {
            return manger.sendMessage(Integer.parseInt(bo.getDstType().substring(2),16),Integer.parseInt(bo.getDstId()),Integer.parseInt(bo.getTransType()),Integer.parseInt(bo.getMsgId().substring(2),16),Integer.parseInt(bo.getReserved().substring(2),16),bo.getData());
        }catch (Exception ex){
            logger.error("send message fail",ex);
            return false ;
        }
    }

    @Override
    public Boolean sendMessage(SendBodyMessage bo) {
        try {
            return manger.sendMessage(bo.getDstType(),bo.getDstId(),Integer.parseInt(bo.getTransType()),bo.getMsgId(),bo.getReserved(),bo.getData());
        }catch (Exception ex){
            ex.printStackTrace();
            return false ;
        }
    }

    @Override
    public Boolean transmitMessage(TransmitXmlMessage bo) {
        try {
            return manger.sendMessage(bo.getDstType(),bo.getDstId(),Integer.parseInt(bo.getTransType()),bo.getMsgId(),bo.getReserved(),bo.getData());
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
