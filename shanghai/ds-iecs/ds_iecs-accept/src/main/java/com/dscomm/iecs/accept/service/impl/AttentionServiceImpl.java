package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.AttentionEntity;
import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.dal.repository.AttentionRepository;
import com.dscomm.iecs.accept.dal.repository.IncidentRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.AttentionBean;
import com.dscomm.iecs.accept.service.AttentionService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.commons.lang3.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Description:
 *
 * @author: ZhangSheng
 * Date: created in 9:38 2019/9/16
 */
@Component("attentionServiceImpl")
public class AttentionServiceImpl implements AttentionService {
    private static final Logger logger = LoggerFactory.getLogger(AttentionServiceImpl.class);
    private LogService logService;
    private AttentionRepository attentionRepository;
    private AuditLogService auditLogService;
    private UserService userService;
    private IncidentRepository incidentRepository;
//    private CurrentIncidentInfoCacheService currentIncidentInfoCacheService;
    private SubAuditService subAuditService;
    private GeneralAccessor accessor;
    private ServletService servletService ;
    private IncidentService incidentService ;
    private OrganizationService organizationService ;
    private NotifyActionService notifyActionService ;

    // 缓存前缀
    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;


    @Autowired
    public AttentionServiceImpl(AttentionRepository attentionRepository , @Qualifier("generalAccessor") GeneralAccessor accessor,
                                AuditLogService auditLogService, LogService logService, ServletService servletService,
                                UserService userService, IncidentRepository incidentRepository,
//                                     CurrentIncidentInfoCacheService currentIncidentInfoCacheService,
                                SubAuditService subAuditService , IncidentService incidentService , OrganizationService organizationService ,
                                NotifyActionService notifyActionService
    ) {
        this.attentionRepository = attentionRepository;
        this.auditLogService = auditLogService;
        this.logService = logService;
        this.userService = userService;
        this.incidentRepository = incidentRepository;
//        this.currentIncidentInfoCacheService = currentIncidentInfoCacheService;
        this.subAuditService = subAuditService;
        this.accessor = accessor;
        this.servletService = servletService;
        this.incidentService =  incidentService ;
        this.organizationService = organizationService ;
        this.notifyActionService = notifyActionService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see #saveAttention(String  , Integer , Integer , Integer , String )
     */
    @Transactional
    @Override
    public AttentionBean saveAttention(String incidentId , Integer attentionType  , Integer type  , Integer attentionWay  , String attentionReason  ) {
        if (StringUtils.isBlank(incidentId) || null == attentionType ) {
            logService.infoLog(logger, "service", "saveAttention", "the required field is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        if ((attentionType != 1 && attentionType != 2)) {
            throw new AcceptException(AcceptException.AccetpErrors.ATTENTIONTYPE_NOTMATCH_ERROR);
        }
        try {
            logService.infoLog(logger, "service", "saveAttention", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentEntity incidentEntity = accessor.getById(incidentId,IncidentEntity.class);
            if (incidentEntity == null) {
                logService.infoLog(logger, "service", "saveAttention", String.format("can not find incidentEntity by the incidentId[%s]", incidentId));
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            //attentionWay 1 系统关注  2 人工关注
            UserInfo userInfo = new UserInfo() ;
            if( attentionWay == 2 ){
                 userInfo = userService.getUserInfo();
            }
            Long systemTime = servletService.getSystemTime();

            AttentionEntity attentionEntity = null;
            String auditLogDetails = "";

            if( type == 1 ){
//                if (attentionType == 2) {
//                    attentionEntity = attentionRepository.getByIncidentId(incidentId , type );
//                    auditLogDetails = String.format("supervisorAttention[account:%s,name:%s] attention incident[%s]", userInfo.getAccount(), userInfo.getPersonName(), incidentId);
//                }
                if (attentionType == 1) {
                    attentionEntity = attentionRepository.getByIncidentIdAndAttentionPersonId(incidentId, userInfo.getAccount() , type);
                    auditLogDetails = String.format("normalAttention[account:%s,name:%s] attention incident[%s]", userInfo.getAccount(), userInfo.getPersonName(), incidentId);
                }
            }else{
                if (attentionType == 2) {
                    attentionEntity = attentionRepository.getByIncidentId(incidentId , type );
                    auditLogDetails = String.format("supervisorAttention[account:%s,name:%s] attention incident[%s]", userInfo.getAccount(), userInfo.getPersonName(), incidentId);
                }
            }



            if (null != attentionEntity) {
                logService.infoLog(logger, "service", "saveAttention", "AttentionEntity already exist,only update info.");
            } else {
                attentionEntity = new AttentionEntity();
            }

            attentionEntity.setValid( true );
            attentionEntity.setType(type);
            attentionEntity.setIncidentId(incidentId);
            attentionEntity.setAttentionType(attentionType);
            attentionEntity.setAttentionPersonId(userInfo.getAccount());
            attentionEntity.setAttentionWay( attentionWay );
            attentionEntity.setAttentionReason( attentionReason );
            attentionEntity.setAttentionTime(systemTime);

            logService.infoLog(logger, "repository", "AttentionRepository.save(AttentionEntity)", "repository is started...");
            Long start = System.currentTimeMillis();
            accessor.save(attentionEntity);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "AttentionRepository.save(AttentionEntity)", String.format("repository is finished,execute time is :%s ms", end - start));
            AttentionBean attentionBean = IncidentTransformUtil.transform(attentionEntity);

            //保存操作记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(systemTime);
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_ATTENTION.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc(auditLogDetails);
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_ATTENTION.getName());

            //重要警情关注通知
            if( type == 2 ){
                //消息通知案件参与单位
                Set<String> orgSet = new HashSet<>() ;
                List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( incidentId );
                orgSet.addAll( orgIds ) ;
                List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
                orgSet.addAll(orgCodes);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.BUILD_IMPORTANT_INCIDENT.getCode(), attentionBean, orgSet);
            }



            // 更新实时警情缓存
//            try {
//                Map<String, IncidentInfoBO> incidentInfoBOMap = currentIncidentInfoCacheService.findAllCurrentIncidentInfoCache(cacheKeyPrefix);
//                if (null != incidentInfoBOMap && incidentInfoBOMap.size() > 0) {
//                    if (incidentInfoBOMap.containsKey(AttentionVO.getIncidentId())) {
//                        IncidentInfoBO incidentInfoBO = incidentInfoBOMap.get(AttentionVO.getIncidentId());
//                        List<String> attentionPersonAccount = incidentInfoBO.getAttentionPersonAccount();
//                        if (null == attentionPersonAccount) {
//                            attentionPersonAccount = new ArrayList<>();
//                        }
//                        if (AttentionVO.getType() == 2) {//班长关注的
//                            if (incidentInfoBO.getTop() != 1) {
//                                attentionPersonAccount.add(userInfo.getAccount());
//                            }
//                            incidentInfoBO.setAttentionPersonAccount(attentionPersonAccount);
//                            incidentInfoBO.setSortField(2);
//                            incidentInfoBO.setTop(1);
//                        }
//                        if (AttentionVO.getType() == 1) {//非班长关注
//                            attentionPersonAccount.add(userInfo.getAccount());
//                            incidentInfoBO.setAttentionPersonAccount(attentionPersonAccount);
//                            incidentInfoBO.setAttention(1);
//                            if (incidentInfoBO.getSortField() == 0) {
//                                incidentInfoBO.setSortField(1);
//                            }
//                        }
//                        currentIncidentInfoCacheService.mergeCurrentIncidentInfoCache("put", incidentInfoBO, cacheKeyPrefix, true);
//                    }
//                }
//            } catch (Exception ex) {
//                logService.erorLog(logger, "service", "currentIncidentInfoCacheService.mergeCurrentIncidentInfoCache", "update currentIncidentInfoCache fail.", ex);
//            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveAttention", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return attentionBean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveAttention", "save Attention fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_ATTENTION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #deleteAttention(String  , Integer , Integer , Integer )
     */
    @Transactional
    @Override
    public Boolean deleteAttention( String incidentId , Integer attentionType , Integer type  ,   Integer attentionWay  ) {
        if (StringUtils.isBlank(incidentId) || null == attentionType) {
            logService.infoLog(logger, "service", "deleteAttention", "the required field is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        if ((attentionType != 1 && attentionType != 2)) {
            throw new AcceptException(AcceptException.AccetpErrors.ATTENTIONTYPE_NOTMATCH_ERROR);
        }

        try {
            logService.infoLog(logger, "service", "deleteAttention", "service is started...");
            Long logStart = System.currentTimeMillis();

            UserInfo userInfo = userService.getUserInfo();

            AttentionEntity attentionEntity = null;
            String auditLogDetails = "";


            if( type == 1 ){
//                if (attentionType == 2) {
//                    attentionEntity = attentionRepository.getByIncidentId(incidentId , type );
//                    auditLogDetails = String.format("supervisorAttention[account:%s,name:%s] attention incident[%s]", userInfo.getAccount(), userInfo.getPersonName(), incidentId);
//                }
                if (attentionType == 1) {
                    attentionEntity = attentionRepository.getByIncidentIdAndAttentionPersonId(incidentId, userInfo.getAccount() , type);
                    auditLogDetails = String.format("normalAttention[account:%s,name:%s] attention incident[%s]", userInfo.getAccount(), userInfo.getPersonName(), incidentId);
                }
            }else{
                if (attentionType == 2) {
                    attentionEntity = attentionRepository.getByIncidentId(incidentId , type );
                    auditLogDetails = String.format("supervisorAttention[account:%s,name:%s] attention incident[%s]", userInfo.getAccount(), userInfo.getPersonName(), incidentId);
                }
            }

            if (null == attentionEntity) {
                logService.infoLog(logger, "service", "deleteAttention", "AttentionEntity not exist in db.");
            } else {
                logService.infoLog(logger, "repository", "AttentionRepository.deleteById()", "repository is started...");
                Long start = System.currentTimeMillis();

                attentionEntity.setValid( false );
                attentionEntity.setAttentionWay( attentionWay );
                accessor.save( attentionEntity ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "AttentionRepository.deleteById()", String.format("repository is finished,execute time is :%s ms", end - start));

                //保存操作记录
                Long systemTime = servletService.getSystemTime();
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(systemTime);
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UNFOLLOW.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc(auditLogDetails);
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UNFOLLOW.getName());
                // 更新实时警情缓存
//                try {
//                    Map<String, IncidentInfoBO> incidentInfoBOMap = currentIncidentInfoCacheService.findAllCurrentIncidentInfoCache(cacheKeyPrefix);
//                    if (null != incidentInfoBOMap && incidentInfoBOMap.size() > 0) {
//                        if (incidentInfoBOMap.containsKey(AttentionVO.getIncidentId())) {
//                            IncidentInfoBO incidentInfoBO = incidentInfoBOMap.get(AttentionVO.getIncidentId());
//                            List<String> attentionPersonAccount = incidentInfoBO.getAttentionPersonAccount();
//                            if (null == attentionPersonAccount) {
//                                attentionPersonAccount = new ArrayList<>();
//                            }
//                            if (AttentionVO.getType() == 2) {
//                                attentionPersonAccount.remove(AttentionEntity.getAttentionPersonId());
//                                incidentInfoBO.setAttentionPersonAccount(attentionPersonAccount);
//                                incidentInfoBO.setSortField(1);
//                                incidentInfoBO.setTop(0);
//                            }
//                            if (AttentionVO.getType() == 1) {
//                                attentionPersonAccount.remove(userInfo.getAccount());
//                                incidentInfoBO.setAttentionPersonAccount(attentionPersonAccount);
//                                incidentInfoBO.setAttention(0);
//                            }
//                            if (attentionPersonAccount.size() == 0) {
//                                incidentInfoBO.setSortField(0);
//                                incidentInfoBO.setTop(0);
//                                incidentInfoBO.setAttention(0);
//                            }
//                            currentIncidentInfoCacheService.mergeCurrentIncidentInfoCache("put", incidentInfoBO, cacheKeyPrefix, true);
//                        }
//                    }
//                } catch (Exception ex) {
//                    logService.erorLog(logger, "service", "currentIncidentInfoCacheService.mergeCurrentIncidentInfoCache", "update currentIncidentInfoCache fail.", ex);
//                }

                //重要警情关注通知
                if( attentionEntity.getType() == 2 ){
                    //消息通知案件参与单位
                    Set<String> orgSet = new HashSet<>() ;
                    List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( incidentId );
                    orgSet.addAll( orgIds ) ;
                    List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
                    orgSet.addAll(orgCodes);
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.REMOVE_IMPORTANT_INCIDENT.getCode(), incidentId , orgSet);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deleteAttention", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "deleteAttention", "delete Attention fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_ATTENTION_FAIL);
        }


    }



    /**
     * {@inheritDoc}
     *
     * @see #existImportantAttention(String     )
     */
    @Transactional
    @Override
    public  Boolean existImportantAttention( String incidentId    ){
        if (StringUtils.isBlank(incidentId) ) {
            logService.infoLog(logger, "service", "deleteAttention", "the required field is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "deleteAttention", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false  ;

            logService.infoLog(logger, "repository", "AttentionRepository.deleteById()", "repository is started...");
            Long start = System.currentTimeMillis();

            List<AttentionEntity> attentionEntityList = attentionRepository.getImportantByIncidentId( incidentId ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "AttentionRepository.deleteById()", String.format("repository is finished,execute time is :%s ms", end - start));

            if (null == attentionEntityList || attentionEntityList.size() < 1 ) {
                res = false ;
            } else {
                res = true  ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deleteAttention", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "deleteAttention", "delete Attention fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_ATTENTION_FAIL);
        }


    }

}
