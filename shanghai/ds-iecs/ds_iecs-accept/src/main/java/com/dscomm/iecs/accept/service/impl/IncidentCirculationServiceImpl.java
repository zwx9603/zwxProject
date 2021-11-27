package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.IncidentCirculationBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentCirculationConfirmBean;
import com.dscomm.iecs.accept.service.IncidentCirculationService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.keydata.enums.AuditOperateTypeEnum;
import com.dscomm.iecs.keydata.enums.TerminalTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:警情流转服务
 *
 * @author YangFuxi
 * Date Time 2019/9/5 19:33
 */
@Component
public class IncidentCirculationServiceImpl implements IncidentCirculationService {
    private static final Logger logger = LoggerFactory.getLogger(IncidentCirculationServiceImpl.class);
    private LogService logService;
    private NotifyActionService notifyActionService ;
    private UserService userService ;
    private ServletService servletService ;
    private AuditLogService auditLogService;
    private SubAuditService subAuditService;


    @Autowired
    public IncidentCirculationServiceImpl(LogService logService , NotifyActionService notifyActionService , UserService userService  ,
                                          AuditLogService auditLogService, SubAuditService subAuditService,   ServletService servletService ) {
        this.logService = logService;
        this.notifyActionService = notifyActionService ;
        this.userService = userService ;
        this.auditLogService = auditLogService;
        this.subAuditService = subAuditService;
        this.servletService = servletService ;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean circulation(IncidentCirculationBean bo) {
        if (bo == null || StringUtils.isBlank(bo.getReceiver()) || bo.getIncidentBean() == null) {
            throw new AcceptException(AcceptException.AccetpErrors.INCIDENT_OR_RECEIVER_IS_NULL);
        }
        try {

            //发送websocket消息
            List<ReceiverMessageBean> list = new ArrayList<>();
            list.add(new ReceiverMessageBean("user", bo.getReceiver()));
            notifyActionService.pushMessage( WebsocketCodeEnum.INCIDENTCIRCULATE.getCode() , bo , userService.getAccount() , list  );

            //记录操作日志
            Long currentTime  = servletService.getSystemTime();
            UserInfo userInfo = userService.getUserInfo();

            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime( currentTime );
            auditLogSaveInputInfo.setOperateType(String.valueOf(AuditOperateTypeEnum.INCIDENTCIRCULATE.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount() );
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc( String.format("incident circulate success,incidentId:%s,sourceUser:%s,receiver:%s",
                    bo.getIncidentBean().getId(), userService.getAccount(), bo.getReceiver()) );
            auditLogSaveInputInfo.setRemarks(String.valueOf(TerminalTypeEnum.CADAGENT.getCode()));
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Edit", "Ok", auditLogSaveInputInfo.getDesc());


            return true;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "circulation", String.format("circulation", "circulation incident fail.", bo.getReceiver(), bo.getIncidentBean().getId()), ex);
            throw new AcceptException(AcceptException.AccetpErrors.CIRCULATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #notifyCirculationSuccess(IncidentCirculationConfirmBean)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean notifyCirculationSuccess(IncidentCirculationConfirmBean bo ) {
        if (bo == null || StringUtils.isBlank(bo.getIncidentId()) || StringUtils.isBlank(bo.getReceiver())) {
            throw new AcceptException(AcceptException.AccetpErrors.INCIDENT_OR_RECEIVER_IS_NULL);
        }
        try {

            //发送websocket消息
            List<ReceiverMessageBean> list = new ArrayList<>();
            list.add(new ReceiverMessageBean("user", bo.getReceiver()));
            notifyActionService.pushMessage( WebsocketCodeEnum.INCIDENTCIRCULATE.getCode() , bo , userService.getAccount() , list  );

            //记录操作日志
            Long currentTime  = servletService.getSystemTime();
            UserInfo userInfo = userService.getUserInfo();

            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime( currentTime );
            auditLogSaveInputInfo.setOperateType(String.valueOf(AuditOperateTypeEnum.INCIDENTCIRCULATEOK.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount() );
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc( String.format("incident circulate success,incidentId:%s,sourceUser:%s,receiver:%s",
                    bo.getIncidentId() , userService.getAccount(), bo.getReceiver()) );
            auditLogSaveInputInfo.setRemarks(String.valueOf(TerminalTypeEnum.CADAGENT.getCode()));
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Edit", "Ok", auditLogSaveInputInfo.getDesc());

            return true;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "notifyCirculationSuccess", String.format("notify incident circulation fail,sender:%s,receiver:%s,incidentId:%s", userService.getAccount() , bo.getReceiver(), bo.getIncidentId()), ex);
            throw new AcceptException(AcceptException.AccetpErrors.CIRCULATION_CONFIRM_FAIL);
        }
    }
}
