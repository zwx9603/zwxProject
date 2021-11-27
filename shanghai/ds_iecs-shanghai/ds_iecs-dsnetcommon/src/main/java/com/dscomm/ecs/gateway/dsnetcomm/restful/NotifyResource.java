package com.dscomm.ecs.gateway.dsnetcomm.restful;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.ecs.gateway.dsnetcomm.exception.DsNotifyException;
import com.dscomm.ecs.gateway.dsnetcomm.service.NotifyService;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.SendBodyMessage;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.SendMessageBO;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.TransmitXmlMessage;
import org.mx.error.UserInterfaceException;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 描述:21消息服务
 *
 * @author YangFuxi
 * Date Time 2020/10/12 10:45
 */
@Path("21notify")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotifyResource {
    private static final Logger logger=LoggerFactory.getLogger(NotifyResource.class);
    private NotifyService notifyService;

    @Autowired
    public NotifyResource(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    @Path("sendMessage")
    @POST
    public DataVO<Boolean> send21Message(SendMessageBO bo){
        try {
            Boolean result = notifyService.sendMessage(bo);
            return new DataVO<>(result);
        }catch (UserInterfaceException ex){
            logger.error("fail to send 21 message",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("fail to send 21 message",ex);
            return new DataVO<>(new DsNotifyException(DsNotifyException.DsNetErrors.SEND_21_MESSAGE_FAIL));
        }
    }

    /**
     * 发送xml消息
     * @param bo 消息体
     * @return 返回发送结果
     */
    @Path("sendXmlMessage")
    @POST
    public DataVO<Boolean> send21Message(SendBodyMessage bo){
        try {
            Boolean result = notifyService.sendMessage(bo);
            return new DataVO<>(result);
        }catch (UserInterfaceException ex){
            logger.error("fail to send 21 message",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("fail to send 21 message",ex);
            return new DataVO<>(new DsNotifyException(DsNotifyException.DsNetErrors.SEND_21_MESSAGE_FAIL));
        }
    }

    /**
     * 转发xml消息
     * @param bo 消息体
     * @return 返回结果
     */
    @Path("transmitXmlMessage")
    @POST
    public DataVO<Boolean> send21Message(TransmitXmlMessage bo){
        try {
            logger.info(String.format("send transmitXmlMessage :%s",JSONObject.toJSONString(bo)));
            Boolean result = notifyService.transmitMessage(bo);
            return new DataVO<>(result);
        }catch (UserInterfaceException ex){
            logger.error("fail to send 21 message",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("fail to send 21 message",ex);
            return new DataVO<>(new DsNotifyException(DsNotifyException.DsNetErrors.SEND_21_MESSAGE_FAIL));
        }
    }
}
