package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.ReinforcementAskEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.ReinforcementAskSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.ReinforcementAskBean;
import com.dscomm.iecs.accept.service.DocumentService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.ReinforcementService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_QQZY;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;


/**
 * 描述： 增援 服务实现类
 */
@Component("reinforcementServiceImpl")
public class ReinforcementServiceImpl implements ReinforcementService {
    private static final Logger logger = LoggerFactory.getLogger(ReinforcementServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private NotifyActionService notifyActionService ;
    private AuditLogService auditLogService;
    private SubAuditService subAuditService;
    private UserService userService ;
    private IncidentService incidentService ;
    private DocumentService documentService ;
    private DictionaryService dictionaryService;

    private List<String> dics;

    @Autowired
    public ReinforcementServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService, OrganizationService organizationService ,
                                    NotifyActionService notifyActionService ,  AuditLogService auditLogService  , SubAuditService subAuditService  ,
                                    UserService userService , IncidentService incidentService , DocumentService documentService  ,
                                    DictionaryService dictionaryService ) {
        this.accessor = accessor;
        this.logService = logService;
        this.organizationService = organizationService;
        this.notifyActionService = notifyActionService;
        this.auditLogService = auditLogService;
        this.subAuditService = subAuditService ;
        this.userService = userService ;
        this.incidentService = incidentService ;
        this.documentService = documentService ;
        this.dictionaryService = dictionaryService ;

        dics = new ArrayList<>(Arrays.asList( "WLCLLX", "ZBLX" ));
    }

    /**
     * {@inheritDoc}
     *
     * @see ReinforcementService#saveReinforcementAsk(ReinforcementAskSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReinforcementAskBean saveReinforcementAsk(ReinforcementAskSaveInputInfo inputInfo) {
        if (inputInfo == null || Strings.isBlank( inputInfo.getIncidentId() )) {
            logService.infoLog(logger, "service", "saveReinforcementAsk", "ReinforcementAskSaveInputInfo or incidentId  is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveReinforcementAsk", "service is started...");
            Long logStart = System.currentTimeMillis();

            ReinforcementAskBean res = null ;

            IncidentBean incidentBean = incidentService.findIncident( inputInfo.getIncidentId() , false ) ;
            if( incidentBean == null ){
                logService.infoLog(logger, "service", "saveReinforcementAsk", " incident bean   is null.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            decodeReinforcementAskSaveInputInfo( inputInfo ) ;

            ReinforcementAskEntity reinforcementAskEntity = HandleDispatchTransformUtil.transform(inputInfo);
            //判断请求增援单位id 是否为空 如果为空 设置为请求单位的上级单位
            if( Strings.isBlank( reinforcementAskEntity.getReceiveOrganizationId() ) ){
                OrganizationBean higherLevel = organizationService.findOrganizationHigherlevel( reinforcementAskEntity.getAskOrganizationId() ) ;
                if( higherLevel != null ){
                    reinforcementAskEntity.setReceiveOrganizationId( higherLevel.getId() );
                }
            }

            logService.infoLog(logger, "repository", "save(dbReinforcementAskEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(reinforcementAskEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbReinforcementAskEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            res = HandleDispatchTransformUtil.transform(reinforcementAskEntity, organizationNameMap , dicsMap );


            UserInfo userInfo = userService.getUserInfo();
            if( userInfo == null ){
                userInfo = new UserInfo() ;
            }

            //请求增援 保存文书
            DocumentSaveInputInfo documentSaveInputInfo = new DocumentSaveInputInfo();
            documentSaveInputInfo.setIncidentId( inputInfo.getIncidentId()  );
            documentSaveInputInfo.setDateSourceId( res.getId() );
            documentSaveInputInfo.setTitle(  DOCUMENT_TYPE_QQZY.getName() );
            documentSaveInputInfo.setContent(   incidentBean.getCrimeAddress() +   DOCUMENT_TYPE_QQZY.getName() + ":" +
                    inputInfo.getContent()    );
            documentSaveInputInfo.setType(   DOCUMENT_TYPE_QQZY.getCode()   );
            documentSaveInputInfo.setFeedbackPerson( userInfo.getPersonName() );
            documentSaveInputInfo.setFeedbackOrganizationId( userInfo.getOrgId() );
            documentSaveInputInfo.setTerminalId( null );
            documentSaveInputInfo.setRemarks( null );
            documentService.saveDocument( documentSaveInputInfo ) ;


            //保存操作日志记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_REINFORCEMENT_ASK.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount() );
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc( "incident \"" + inputInfo.getIncidentId()  + "\" ask content  :  \"" + res.getContent()   + "\" " );
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_REINFORCEMENT_ASK.getName());


            //消息通知接受机构
            if( Strings.isNotBlank( res.getReceiveOrganizationId() )){
                Set<String> orgs = new HashSet<>();
                orgs.add(res.getReceiveOrganizationId());

                List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgs));
                orgs.addAll(orgCodes);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_REINFORCEMENT_ASK.getCode(),res,orgs);
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveReinforcementAsk", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveReinforcementAsk", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_REINFORCEMENT_ASK_FAIL);
        }
    }




    /**
     * 对IncidentSaveInputInfo中需要前端手动输入的属性进行解码
     * URLDecoder
     *
     */
    private void decodeReinforcementAskSaveInputInfo(ReinforcementAskSaveInputInfo source){
        if (source != null){
            try {
                if (!StringUtils.isBlank(source.getReceiveOrganizationId() )){
                    source.setReceiveOrganizationId((URLDecoder.decode(source.getReceiveOrganizationId(),"utf-8")));
                }
                if (!StringUtils.isBlank(source.getMeetAddress())){
                    source.setMeetAddress((URLDecoder.decode(source.getMeetAddress(),"utf-8")));
                }
                if (!StringUtils.isBlank(source.getMeetContactPerson())){
                    source.setMeetContactPerson(URLDecoder.decode( source.getMeetContactPerson() ,"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getRemarks())){
                    source.setRemarks( (URLDecoder.decode( source.getRemarks() ,"utf-8")));
                }
                if (!StringUtils.isBlank(source.getContent())){
                    source.setContent((URLDecoder.decode( source.getContent() ,"utf-8")));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL );
            }
        }
    }


}
