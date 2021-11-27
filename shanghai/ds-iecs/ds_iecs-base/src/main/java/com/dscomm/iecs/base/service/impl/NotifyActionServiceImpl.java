package com.dscomm.iecs.base.service.impl;

import com.dscomm.iecs.base.graphql.typebean.CLMessageBean;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.NotifyClientService;
import com.dscomm.iecs.base.service.WebSocketPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 描述:notify websocket 通知推送行为
 *
 */
@Component
public class NotifyActionServiceImpl implements NotifyActionService {
    private static final Logger logger = LoggerFactory.getLogger(NotifyActionServiceImpl.class);

    private WebSocketPushService webSocketPushService ;
    private NotifyClientService notifyClientService ;


    @Autowired
    public NotifyActionServiceImpl(WebSocketPushService webSocketPushService , NotifyClientService notifyClientService) {
        this.webSocketPushService   = webSocketPushService ;
        this.notifyClientService = notifyClientService ;
    }


    /**
     * {@inheritDoc}
     *
     * @see #clientNotify(CLMessageBean, Object)
     */
    @Override
    public void clientNotify(CLMessageBean clMessage,Object body) {
        webSocketPushService.clientNotify( clMessage , body   ) ;
    }



    @Override
    public void pushMessageToUsers(String code, Object body, List<String> receivers) {
        webSocketPushService.pushMessageToUsers( code , body , receivers ) ;
        notifyClientService.pushMessageToUsers( code , body , receivers   );
    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessage(String, Object, List)
     */
    @Override
    public void pushMessage(String code, Object body, List<ReceiverMessageBean> receivers) {
        webSocketPushService.pushMessage( code , body , receivers   ) ;
        notifyClientService.pushMessage( code , body , receivers );
    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessage(String, Object, String, List)
     */
    @Override
    public void pushMessage(String code, Object body, String fromUserCode, List<ReceiverMessageBean> receivers) {
        webSocketPushService.pushMessage( code , body ,fromUserCode , receivers   ) ;
        notifyClientService.pushMessage( code , body , receivers   );
    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToDefaultSystemBusinessOrg(String, Object, Set)
     */
    @Override
    public void pushMessageToDefaultSystemBusinessOrg(String code, Object body, Set<String> orgs) {
        webSocketPushService.pushMessageToDefaultSystemBusinessOrg( code , body , orgs   ) ;
        notifyClientService.pushMessageToDefaultSystemBusinessOrg( code , body, orgs   );
    }

    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToBusinessOrg(String, Object, Set, String)
     */
    @Override
    public void pushMessageToBusinessOrg(String code, Object body, Set<String> orgs, String system) {
        webSocketPushService.pushMessageToBusinessOrg( code , body , orgs , system   ) ;
    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToDefaultSystemBusinessOrgRole(String, Object, Set , Set )
     */
    @Override
    public void pushMessageToDefaultSystemBusinessOrgRole(String code, Object body, Set<String> orgs , Set<String> roles ) {
        webSocketPushService.pushMessageToDefaultSystemBusinessOrgRole( code , body , orgs  , roles    ) ;
        notifyClientService.pushMessageToDefaultSystemBusinessOrgRole( code , body , orgs  , roles );
    }

    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToBusinessOrgRole(String, Object, Set, Set , String  )
     */
    @Override
    public void pushMessageToBusinessOrgRole(String code, Object body, Set<String> orgs , Set<String> roles , String system  )  {
        webSocketPushService.pushMessageToBusinessOrgRole( code , body , orgs  , roles , system    ) ;
    }
}
