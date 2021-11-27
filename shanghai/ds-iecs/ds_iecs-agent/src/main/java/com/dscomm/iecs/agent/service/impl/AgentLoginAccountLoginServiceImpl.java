package com.dscomm.iecs.agent.service.impl;

import com.dscomm.iecs.agent.service.AgentLoginAccountLoginService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.AgentAccountEntity;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:坐席最新登录用户
 *
 */
@Component("agentLoginAccountLoginServiceImpl")
public class AgentLoginAccountLoginServiceImpl implements AgentLoginAccountLoginService {

    private static final Logger logger = LoggerFactory.getLogger(AgentLoginAccountLoginServiceImpl.class);

    private LogService logService;
    private GeneralAccessor accessor;
    private ServletService servletService ;

    @Autowired
    public AgentLoginAccountLoginServiceImpl(LogService logService ,  @Qualifier("generalAccessor") GeneralAccessor accessor , ServletService servletService ) {
        this.logService = logService;
        this.accessor = accessor ;
        this.servletService = servletService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see #saveAgentAccount(String, String, String )
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean saveAgentAccount( String id , String  agentIp , String agentNumber , String account ) {
        try {
            logService.infoLog(logger, "service", "saveAgentAccount", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false ;

            AgentAccountEntity agentAccountEntity = new AgentAccountEntity();

            agentAccountEntity.setId (  id );
            agentAccountEntity.setAgentIp( agentIp );
            agentAccountEntity.setAgentNumber(  agentNumber );
            agentAccountEntity.setAccount( account);
            agentAccountEntity.setLatesttime( servletService.getSystemTime() );


            logService.infoLog(logger, "repository", "saveAgentAccount", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save( agentAccountEntity ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveAgentAccount", String.format("repository is finished,execute time is :%sms", end - start));

            res = true ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveAgentAccount", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;

        } catch (Exception ex) {
            logService.erorLog( logger, "service", "saveAgentAccount", String.format("save agent account fail "), ex );
            return  false ;
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see #findAgentAccount(String)
     */
    @Transactional( readOnly =  true )
    @Override
    public String findAgentAccount(  String id    ) {
        try {
            logService.infoLog(logger, "service", "saveAgentAccount", "service is started...");
            Long logStart = System.currentTimeMillis();

            String account = null  ;

            logService.infoLog(logger, "repository", "saveAgentAccount", "repository is started...");
            Long start = System.currentTimeMillis();

            AgentAccountEntity agentAccountEntity =  accessor.getById( id , AgentAccountEntity.class  ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveAgentAccount", String.format("repository is finished,execute time is :%sms", end - start));

            if( agentAccountEntity != null ){
                account = agentAccountEntity.getAccount() ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveAgentAccount", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  account  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllAgentCache", "get all agent cache fail.", ex);
            return  null ;
        }
    }



}
