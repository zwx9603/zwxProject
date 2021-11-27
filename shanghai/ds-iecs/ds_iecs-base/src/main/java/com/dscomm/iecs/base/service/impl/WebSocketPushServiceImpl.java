package com.dscomm.iecs.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.base.graphql.typebean.CLMessageBean;
import com.dscomm.iecs.base.graphql.typebean.CLMsgHeadBean;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.WebSocketPushService;
import com.dscomm.iecs.base.utils.JaxbUtil;
import com.dscomm.iecs.base.utils.SafetyRedLineUtils;
import org.mx.StringUtils;
import org.mx.error.UserInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 描述:websocket推送服务
 *
 */
@Component
public class WebSocketPushServiceImpl implements WebSocketPushService {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketPushServiceImpl.class);
    private WebSocketConnector connector;
    @Value("${websocket.fromUser:system}")
    private String systemUser;
    @Value("${websocket.system:14}")
    private String system;
    @Value("${websocket.xmlEnable:false}")
    private Boolean xmlEnable ;


    @Autowired
    public WebSocketPushServiceImpl(WebSocketConnector connector) {
        this.connector = connector;
    }

    /**
     * {@inheritDoc}
     *
     * @see #pushMessage(String, Object, List)
     */
    @Override
    public void pushMessage(String code, Object body, List<ReceiverMessageBean> receivers) {
        pushMessage(code, body, systemUser, receivers);
    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessage(String, Object, String, List)
     */
    @Override
    public void pushMessage(String code, Object body, String fromUserCode, List<ReceiverMessageBean> receivers) {
        try {
            CLMsgHeadBean head = new CLMsgHeadBean();
            head.setCode(code);
            head.setFrom(new ReceiverMessageBean(fromUserCode, system, "user"));
            head.setTos(receivers);
            CLMessageBean clMessage = new CLMessageBean( head );
            clientNotify(clMessage,body);
        } catch (UserInterfaceException ex) {
            if (logger.isErrorEnabled()) {
                logger.error("send websocket fail", ex);
            }
//            throw ex;
        } catch (Exception ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("websocket send message fail,code:%s,data:%s,from:%s", code, JSONObject.toJSONString(body), fromUserCode), ex);
            }
//            throw new UserInterfaceWebsocketException(UserInterfaceWebsocketException.WebsocketErrors.WEBSOCKET_MESSAGE_SEND_FAIL);
        }
    }

    @Override
    public void pushMessageToUsers(String code, Object body, List<String> receivers) {
        List<ReceiverMessageBean> tos = new ArrayList<>();
        if (receivers != null && receivers.size() > 0) {
            receivers.forEach(r -> {
                ReceiverMessageBean bo = new ReceiverMessageBean(r, system, "user");
                tos.add(bo);
            });
            pushMessage(code, body, tos);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #clientNotify(CLMessageBean, Object)
     */
    public void clientNotify(CLMessageBean clMessage,Object body) {

        String notify = "" ;
        if( xmlEnable ){
            clMessage.setBody( JSONObject.toJSONString( body ) );
            notify = JaxbUtil.convertToXml(clMessage);
        }else {
            Map<String, Object> msg = new HashMap<>();
            msg.put("msg", clMessage);
            clMessage.setBody(body);
            notify = JSONObject.toJSONString(msg);
        }
        logger.info("websocket message：" + notify );
        CLMessageBean logClMessage = new CLMessageBean();
        logClMessage.setHead(clMessage.getHead());
        logClMessage.setUserCode(clMessage.getUserCode());
        Object logBody = null;
        try {
            logBody = SafetyRedLineUtils.transformObjectForSafety(body);
        } catch (Exception ex) {
            logger.error("redline transform info fail when send websocket", ex);
        }
        logClMessage.setBody(JSONObject.toJSONString(logBody));
        Map<String, Object> logmsg = new HashMap<>();
        logmsg.put("msg", logClMessage);
        String logInfo  = JSONObject.toJSONString(logmsg);
        // 向服务器发送消息
        try {
            connector.send(notify,false);
        } catch (UserInterfaceException ex) {
            logger.error(String.format("InnerError: fail to notify web client notify server,message:%s", logInfo), ex);

        } catch (Exception ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("InnerError: fail to notify web client notify server,message:%s", logInfo), ex);
            }

        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToDefaultSystemBusinessOrg(String, Object, Set)
     */
    @Override
    public void pushMessageToDefaultSystemBusinessOrg(String code, Object body, Set<String> orgs) {
        pushMessageToBusinessOrg(code, body, orgs, system);
    }

    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToBusinessOrg(String, Object, Set, String)
     */
    @Override
    public void pushMessageToBusinessOrg(String code, Object body, Set<String> orgs, String system) {
        List<ReceiverMessageBean> tos = new ArrayList<>();
        if (orgs != null && orgs.size() > 0) {
            orgs.forEach(org -> {
                if (!StringUtils.isBlank(org)) {
                    tos.add(new ReceiverMessageBean("businessOrg.role" ,org + "|" + system      ));
                }
            });
        }
        pushMessage(code, body, tos);
    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToDefaultSystemBusinessOrg(String, Object, Set)
     */
    @Override
    public void pushMessageToDefaultSystemBusinessOrgRole(String code, Object body, Set<String> orgs , Set<String> roles ) {
        pushMessageToBusinessOrgRole(code, body, orgs , roles , system  );
    }

    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToBusinessOrg(String, Object, Set, String)
     */
    @Override
    public void pushMessageToBusinessOrgRole(String code, Object body, Set<String> orgs , Set<String> roles , String system  )  {
        List<ReceiverMessageBean> tos = new ArrayList<>();
        if (orgs != null && orgs.size() > 0) {
            orgs.forEach(org -> {
                if (!StringUtils.isBlank(org)) {
                    if( roles != null && roles.size() > 0 ){
                        roles.forEach( role ->{
                            if( !StringUtils.isBlank(role) ){
                                tos.add(new ReceiverMessageBean(org + "|" +  role + "|" +  system ,   system ,   "businessOrg.role"  ));
                            }
                        });
                    }
                }
            });
        }
        pushMessage(code, body, tos);
    }
}
