package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.BlacklistEntity;
import com.dscomm.iecs.accept.dal.repository.BlacklistRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.BlackListQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.BlacklistSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.BlacklistBean;
import com.dscomm.iecs.accept.service.BlacklistService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.keydata.utils.transform.KeyDataTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：标签 服务类实现
 */
@Component("blacklistServiceImpl")
public class BlacklistServiceImpl implements BlacklistService {
    private static final Logger logger = LoggerFactory.getLogger(BlacklistServiceImpl.class);
    private LogService logService;
    private BlacklistRepository blacklistRepository;
    private UserService userService ;
    private DictionaryService dictionaryService ;
    private ServletService servletService ;
    private SystemConfigurationService systemConfigurationService ;
    private AuditLogService auditLogService;

    /**
     * 默认的构造函数
     */
    @Autowired
    public BlacklistServiceImpl(LogService logService, BlacklistRepository blacklistRepository, UserService userService, DictionaryService dictionaryService,
                                ServletService servletService, SystemConfigurationService systemConfigurationService,

                                AuditLogService auditLogService) {
        this.logService = logService;
        this.blacklistRepository = blacklistRepository;
        this.userService = userService ;
        this.dictionaryService = dictionaryService ;
        this.servletService = servletService ;
        this.systemConfigurationService = systemConfigurationService ;
        this.auditLogService = auditLogService;
    }

    /**
     * {@inheritDoc}
     *
     * @see #findBlacklistPhoneCondition(BlackListQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<BlacklistBean> findBlacklistPhoneCondition(BlackListQueryInputInfo queryBean) {
        try {
            logService.infoLog(logger, "service", "findBlacklistPhoneCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean  res  = new PaginationBean() ;
            List<BlacklistBean> beans = new ArrayList<>();

            logService.infoLog(logger, "repository", "findBlacklistPhoneNumber(phoneNumber)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<BlacklistEntity> blacklistEntityList = blacklistRepository.findBlacklistCondition( queryBean.getKeyword() , queryBean.getPhoneKeyword() ,queryBean.getPersonNameKeyword() ,
                    queryBean.getStartTime() , queryBean.getEndTime() ,  queryBean.getWhetherPage() ,
                    queryBean.getPagination().getPage() , queryBean.getPagination().getSize() );


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findBlacklistPhoneNumber(phoneNumber)", String.format("repository is finished,execute time is :%sms", end - start));

            if (blacklistEntityList != null && blacklistEntityList.size() > 0) {
                for(BlacklistEntity blacklistEntity : blacklistEntityList){
                    BlacklistBean blacklistBean  = IncidentTransformUtil.transform(blacklistEntity);
                    beans.add( blacklistBean ) ;

                }
            }

            logService.infoLog(logger, "repository", "findBlacklistConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total =  blacklistRepository.findBlacklistConditionTotal(  queryBean.getKeyword() , queryBean.getPhoneKeyword() ,queryBean.getPersonNameKeyword() , queryBean.getStartTime() , queryBean.getEndTime()  ) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findBlacklistConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage( queryBean.getPagination().getPage());
            pagination.setSize( queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findBlacklistPhoneNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findBlacklistPhoneCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_BLACKLIST_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see BlacklistService#saveBlacklist(BlacklistSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BlacklistBean saveBlacklist(BlacklistSaveInputInfo inputInfo) {
        if (inputInfo == null || Strings.isBlank( inputInfo.getPhoneNumber() ) ) {
            logService.infoLog(logger, "service", "saveBlacklist", "BlacklistSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveBlacklist", "service is started...");
            Long logStart = System.currentTimeMillis();
            BlacklistBean res =  null ;

            Long oldStartTime=null;
            Long oldEndTime=null;
            Boolean addFlag=true;
            Long currentTime = servletService.getSystemTime() ;

            BlacklistEntity blacklistEntity=blacklistRepository.findById(inputInfo.getPhoneNumber()).orElse(null);
            if (blacklistEntity!=null&&blacklistEntity.getEndTime()!=null&&blacklistEntity.getEndTime()>currentTime){
                addFlag=false;
                oldStartTime=blacklistEntity.getStartTime();
                oldEndTime=blacklistEntity.getEndTime();
            }else {
                blacklistEntity =  IncidentTransformUtil.transform( inputInfo    );
            }


            //获得用户
            UserInfo userInfo = userService.getUserInfo() ;
            blacklistEntity.setPersonId( userInfo.getAccount() );
            blacklistEntity.setPersonName( userInfo.getPersonName() );
            AuditLogSaveInputInfo auditLog = preapareAuditLog(inputInfo, userInfo);

            //获得字典信息
            List<DictionaryBean> dictionaryBeans = dictionaryService.findGridDictionary( "HMDSZSL",false  ) ;
            if( dictionaryBeans != null && dictionaryBeans.size() >0  ){
                for(DictionaryBean dictionaryBean : dictionaryBeans){
                    if( dictionaryBean.getCode().equals( inputInfo.getBlackTimeCode() ) ){
                        Long endTime = currentTime +  (  Integer.parseInt(  dictionaryBean.getDesc()   ) * 3600 * 1000 )  ;
                        blacklistEntity.setStartTime( currentTime );
                        blacklistEntity.setEndTime( endTime );
                        break;
                    }
                }
            }else{
                Integer time = 1 ;
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType( "BlacklistTime") ;
                if(  systemConfigurationBean != null){
                    time =  Integer.parseInt( systemConfigurationBean.getConfigValue() ) ;
                }

                Long endTime = currentTime +  ( time * 3600  )  ;
                blacklistEntity.setStartTime( currentTime );
                blacklistEntity.setEndTime( endTime );
            }


            if (null != blacklistEntity) {
                logService.infoLog(logger, "repository", "save(dbBlacklistEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                BlacklistEntity saveResult = blacklistRepository.save(blacklistEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbBlacklistEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                res = IncidentTransformUtil.transform(saveResult);

            }

            StringBuilder detail=new StringBuilder();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //记录审计日志
            if (addFlag){
                detail.append("添加黑名单，电话号码:").append(blacklistEntity.getPhoneNumber()).append("黑名单锁定开始时间:").append(sdf.format(blacklistEntity.getStartTime())).append("黑名单锁定结束时间:").append(sdf.format(blacklistEntity.getEndTime()));
                auditLog.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_INSERTBLACKLIST.getCode()));
            }else {
                auditLog.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_MODIFYBLACKLIST.getCode()));
                detail.append("修改黑名单，电话号码:").append(blacklistEntity.getPhoneNumber()).append(",:");
                if (KeyDataTransformUtil.judgeChange(oldStartTime,blacklistEntity.getStartTime())){
                    detail.append("黑名单锁定开始时间修改前:").append(sdf.format(oldStartTime)).append(",修改后:").append(sdf.format(blacklistEntity.getStartTime())).append(";  ");
                }
                if (KeyDataTransformUtil.judgeChange(oldStartTime,blacklistEntity.getStartTime())){
                    detail.append("黑名单锁定结束时间修改前:").append(sdf.format(oldEndTime)).append(",修改后:").append(sdf.format(blacklistEntity.getEndTime())).append(";  ");
                }
            }
            auditLog.setDesc(detail.toString());
            auditLogService.saveAuditLog(auditLog);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveBlacklist", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveBlacklist", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_BLACKLIST_FAIL);
        }
    }

    private AuditLogSaveInputInfo preapareAuditLog(BlacklistSaveInputInfo inputInfo, UserInfo userInfo) {
        AuditLogSaveInputInfo auditLog=new AuditLogSaveInputInfo();
        auditLog.setAcceptancePersonName(userInfo.getPersonName());
        auditLog.setAcceptancePersonNumber(userInfo.getAccount());
        auditLog.setDocumentNumber(inputInfo.getPhoneNumber());
        auditLog.setRelevantDocumentNumber(inputInfo.getAlarmNumber());
        auditLog.setIpAddress(userInfo.getClientIp());
        auditLog.setOperateSeatNumber(userInfo.getAgentNum());
        auditLog.setOrganizationId(userInfo.getOrgId());
        auditLog.setOrganizationName(userInfo.getOrgName());
        return auditLog;
    }

    /**
     * {@inheritDoc}
     *
     * @see BlacklistService#removeBlacklist(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeBlacklist(String blacklistId) {
        if (Strings.isBlank(blacklistId)) {
            logService.infoLog(logger, "service", "removeBlacklist", "blacklistId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeBlacklist", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean existsFlag = blacklistRepository.existsById(blacklistId);

            if (existsFlag) {

                logService.infoLog(logger, "repository", "deleteById(blacklistId)", "repository is started...");
                Long start = System.currentTimeMillis();

                blacklistRepository.deleteById(blacklistId);
                //获得用户
                UserInfo userInfo = userService.getUserInfo() ;
                AuditLogSaveInputInfo auditLog =new AuditLogSaveInputInfo();
                auditLog.setAcceptancePersonName(userInfo.getPersonName());
                auditLog.setAcceptancePersonNumber(userInfo.getAccount());
                auditLog.setDesc(String.format("删除黑名单，电话号码为:%s",blacklistId));
                auditLog.setDocumentNumber(blacklistId);
                auditLog.setIpAddress(userInfo.getClientIp());
                auditLog.setOperateSeatNumber(userInfo.getAgentNum());
                auditLog.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_DELETEBLACKLIST.getCode()));
                auditLog.setOrganizationId(userInfo.getOrgId());
                auditLog.setOrganizationName(userInfo.getOrgName());
                auditLogService.saveAuditLog(auditLog);
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "deleteById(blacklistId)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeBlacklist", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeBlacklist", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_BLACKLIST_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findBlacklistByPhoneNumber(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  BlacklistBean findBlacklistByPhoneNumber( String phoneNumber) {
        if (Strings.isBlank(phoneNumber)) {
            logService.infoLog(logger, "service", "findBlacklistByPhoneNumber", "phoneNumber is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findBlacklistByPhoneNumber", "service is started...");
            Long logStart = System.currentTimeMillis();

            BlacklistBean res = null ;

            logService.infoLog(logger, "repository", "findBlacklistByPhoneNumber", "repository is started...");
            Long start = System.currentTimeMillis();

            List<BlacklistEntity>   blacklistEntityList =   blacklistRepository.findBlacklistByPhoneNumber( phoneNumber );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findBlacklistByPhoneNumber", String.format("repository is finished,execute time is :%sms", end - start));

            if( blacklistEntityList != null && blacklistEntityList.size() > 0 ){
                BlacklistEntity blacklistEntity = blacklistEntityList.get(0) ;
                res = IncidentTransformUtil.transform( blacklistEntity );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findBlacklistByPhoneNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findBlacklistByPhoneNumber", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_BLACKLIST_FAIL);
        }
    }


}
