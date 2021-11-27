package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.IncidentStatusChangeEntity;
import com.dscomm.iecs.accept.dal.repository.IncidentStatusChangeRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentStatusChangeBean;
import com.dscomm.iecs.accept.service.DocumentService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.IncidentStatusChangeService;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_JQZT;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_GD;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_JA;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：警情状态 服务类实现
 */
@Component("incidentStatusChangeServiceImpl")
public class IncidentStatusChangeServiceImpl implements IncidentStatusChangeService {
    private static final Logger logger = LoggerFactory.getLogger(IncidentStatusChangeServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private DictionaryService dictionaryService;
    private IncidentStatusChangeRepository incidentStatusChangeRepository ;
    private OrganizationService organizationService;
    private NotifyActionService notifyActionService ;
    private IncidentService incidentService ;
    private UserService userService ;
    private DocumentService documentService;
    private PushDataService pushDataService ;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public IncidentStatusChangeServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                                           DictionaryService dictionaryService , IncidentStatusChangeRepository incidentStatusChangeRepository ,
                                           OrganizationService organizationService , NotifyActionService notifyActionService , IncidentService incidentService ,
                                            UserService userService , DocumentService documentService ,
                                            PushDataService pushDataService
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.dictionaryService = dictionaryService;
        this.incidentStatusChangeRepository = incidentStatusChangeRepository ;
        this.organizationService =  organizationService ;
        this.notifyActionService = notifyActionService ;
        this.incidentService = incidentService ;
        this.userService = userService ;
        this.documentService = documentService ;
        this.pushDataService = pushDataService ;

        dics = new ArrayList<>(Arrays.asList(  "AJZT" ));


    }

    /**
     * {@inheritDoc}
     *
     * @see IncidentStatusChangeService#findIncidentStatusChange(String)
     */
    @Transactional(readOnly = true)
    @Override
    public  List<IncidentStatusChangeBean> findIncidentStatusChange(String incidentId ) {
        if ( Strings.isBlank( incidentId) ) {
            logService.infoLog(logger, "service", "findIncidentStatusChange", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentStatusChange", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<IncidentStatusChangeBean> res = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            logService.infoLog(logger, "repository", "findIncidentStatusChange( incidentId )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<IncidentStatusChangeEntity> incidentStatusChangeEntityList = incidentStatusChangeRepository.findIncidentStatusChange( incidentId ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentStatusChange( incidentId )", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != incidentStatusChangeEntityList && incidentStatusChangeEntityList.size() > 0  ){
                for( IncidentStatusChangeEntity incidentStatusChangeEntity : incidentStatusChangeEntityList ){
                    IncidentStatusChangeBean incidentStatusChangeBean = IncidentTransformUtil.transform( incidentStatusChangeEntity , dicsMap )  ;
                    res.add( incidentStatusChangeBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentStatusChange", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentStatusChange", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_STATUS_CHANGE_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see  #saveIncidentStatusChange(String , String ,Boolean , String)
     */
    @Transactional( rollbackFor = Exception.class )
    @Override
    public  Boolean saveIncidentStatusChange(String incidentId ,  String incidentStatusCode ,Boolean  whetherWobSocket ,
                                             String handleId   ){
        if ( Strings.isBlank( incidentId) ) {
            logService.infoLog(logger, "service", "saveIncidentStatusChange", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncidentStatusChange", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false ;

            IncidentStatusChangeEntity   incidentStatusChangeEntity = new  IncidentStatusChangeEntity() ;
            incidentStatusChangeEntity.setIncidentId( incidentId );
            incidentStatusChangeEntity.setHandleId( handleId );
            incidentStatusChangeEntity.setIncidentStatusCode( incidentStatusCode );
            incidentStatusChangeEntity.setChangeTime( System.currentTimeMillis() );

            logService.infoLog(logger, "repository", "findIncidentStatusChange( incidentId )", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save( incidentStatusChangeEntity ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentStatusChange( incidentId )", String.format("repository is finished,execute time is :%sms", end - start));

            res = true ;

            UserInfo userInfo = userService.getUserInfo();

            IncidentBean incidentBean = incidentService.findIncident( incidentId , false ) ;
            incidentBean.setIncidentStatusCode( incidentStatusCode );
            //根据状态码获取案件类型名字

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            incidentBean.setIncidentStatusName( dicsMap.get("AJZT").get( incidentStatusCode )    );

            //警情状态 保存文书
            DocumentSaveInputInfo documentSaveInputInfo = new DocumentSaveInputInfo();
            documentSaveInputInfo.setIncidentId( incidentId  );
            documentSaveInputInfo.setDateSourceId( incidentStatusChangeEntity.getId() );
            documentSaveInputInfo.setTitle(  DOCUMENT_TYPE_JQZT.getName() );
            documentSaveInputInfo.setContent(   incidentBean.getCrimeAddress() + ":" + incidentBean.getIncidentStatusName()   );
            documentSaveInputInfo.setType(   DOCUMENT_TYPE_JQZT.getCode()   );
            documentSaveInputInfo.setTypeSubitemCode( incidentStatusCode );
            documentSaveInputInfo.setFeedbackPerson( userInfo.getPersonName() );
            documentSaveInputInfo.setFeedbackOrganizationId( userInfo.getOrgId() );
            documentSaveInputInfo.setTerminalId( null );
            documentSaveInputInfo.setRemarks( null );
            documentService.saveDocument( documentSaveInputInfo ) ;


            if( whetherWobSocket ){
                //消息通知立案机构、主管单位、以及所属大队 消息通知案件参与单位
                Set<String> orgSet = new HashSet<>() ;
                List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( incidentId  );
                orgSet.addAll( orgIds ) ;
                List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
                orgSet.addAll(orgCodes);

                //结案 归档 发送指定websocket code
                if( INCIDENT_STATUS_JA.getCode().equals(incidentStatusCode)){
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_INCIDENT_END_STATUS.getCode(),incidentBean,orgSet);

                    // 通知 主管机构填写结案反馈
                    Set<String> orgSetAccident = new HashSet<>() ;
                    orgSetAccident.add ( incidentBean.getSquadronOrganizationId() ) ;
                    String  orgCodeAccident =  organizationService.findOrganizationCodesById( incidentBean.getSquadronOrganizationId() );
                    if( Strings.isNotBlank ( orgCodeAccident )){
                         orgSetAccident.add(orgCodeAccident);
                    }
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.WRITE_ACCIDENT.getCode(),incidentBean,orgSetAccident);

                }else if( INCIDENT_STATUS_GD.getCode().equals(incidentStatusCode)){
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_INCIDENT_PALACE_FILE_STATUS.getCode(),incidentBean,orgSet);
                }else{
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_INCIDENT_STATUS.getCode(), incidentBean , orgSet);
                }
            }

            /**一体化上报*/
            IncidentStatusChangeBean incidentStatusChangeBean = IncidentTransformUtil.transform(incidentStatusChangeEntity,dicsMap);
            Map<String, String > otherParams = new HashMap<>()  ;
            pushDataService.pushIncidentStatusChange(incidentBean ,incidentStatusChangeBean , otherParams ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentStatusChange", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncidentStatusChange", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_STATUS_CHANGE_FAIL);
        }

    }


}
