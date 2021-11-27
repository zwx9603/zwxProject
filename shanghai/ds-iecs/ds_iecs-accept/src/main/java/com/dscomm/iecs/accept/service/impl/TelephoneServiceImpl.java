package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.dal.po.*;
import com.dscomm.iecs.accept.dal.repository.TelephoneRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.SoundRecordService;
import com.dscomm.iecs.accept.service.TelephoneService;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.ext.comm.call.CALL_DIRECTION_HC;
import com.dscomm.iecs.ext.comm.call.CALL_DIRECTION_HR;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.DigestUtils;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;

import static com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil.transform;

/**
 * 描述： 电话报警记录 服务类实现
 */
@Component("telephoneServiceImpl")
public class TelephoneServiceImpl implements TelephoneService {
    private static final Logger logger = LoggerFactory.getLogger(TelephoneServiceImpl.class);
    private LogService logService;

    private GeneralAccessor accessor;
    private Environment env;
    private TelephoneRepository telephoneRepository;
    private UserService userService ;
    private ServletService servletService ;
    private SystemConfigurationService systemConfigurationService;
    private AgentService agentService;
    private NotifyActionService notifyActionService ;
    private AuditLogService auditLogService;
    private SubAuditService subAuditService;
    private DictionaryService dictionaryService ;
    private PushDataService pushDataService ;
    private SoundRecordService soundRecordService ;

    private List<String> dics;
    /**
     * 默认的构造函数
     */
    @Autowired
    public TelephoneServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                                TelephoneRepository telephoneRepository, UserService userService , ServletService servletService,
                                SystemConfigurationService systemConfigurationService,   AgentService agentService,
                                NotifyActionService notifyActionService, AuditLogService auditLogService, SubAuditService subAuditService ,
                                DictionaryService dictionaryService, PushDataService pushDataService ,SoundRecordService soundRecordService

    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.telephoneRepository = telephoneRepository;
        this.userService = userService ;
        this.servletService = servletService ;
        this.systemConfigurationService = systemConfigurationService;
        this.agentService = agentService;
        this.notifyActionService = notifyActionService;
        this.auditLogService = auditLogService;
        this.subAuditService = subAuditService;
        this.dictionaryService = dictionaryService ;
        this.pushDataService = pushDataService ;
        this.soundRecordService = soundRecordService ;

        dics = new ArrayList<>(Arrays.asList("HJFX", "FJLX"    ));

    }


    /**
     * {@inheritDoc}
     *
     * @see #buildIdsNoCalling(String
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  TelephoneBean  buildIdsNoCalling ( String alarmPhone ) {
        try {
            logService.infoLog(logger, "service", "buildIdsNoCalling", "service is started...");
            Long logStart = System.currentTimeMillis();

            Long currentTime  = servletService.getSystemTime();

            TelephoneBean res = new  TelephoneBean();
            //生成临时案件id
            String  incidentId =DigestUtils.uuid().replaceAll("-","") ;
            //生成临时受理单id
            String  acceptId =DigestUtils.uuid().replaceAll("-","") ;

            res.setIncidentId(incidentId);
            res.setAcceptanceId(acceptId);

            res.setRingingTime(currentTime);
            res.setAnswerTime(currentTime);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "buildIdsNoCalling", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "buildIdsNoCalling", "get caseInfoBase noCalling fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #ringSaveTelephoneRecord(  String , String , Boolean  , String  )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TelephoneBean ringSaveTelephoneRecord(  String callingTelephone, String calledTelephone  ,   Boolean isCallback ,
                                                   String  incidentId ) {
        if ( StringUtils.isBlank(callingTelephone) || StringUtils.isBlank(calledTelephone) ) {
            logService.infoLog(logger, "service", "ringSaveTelephoneRecord", "ringSaveTelephoneRecord is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "ringSaveTelephoneRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            TelephoneBean res = null ;

            // 生成电话报警
            UserInfo userInfo = userService.getUserInfo();

            //根据电话号码获得装机信息
            String installedAddress = null ;
            String installedUserName = null ;
            List<PhoneLibraryEntity> phoneLibraryEntityList = telephoneRepository.findPhoneLibraryByPhoneNumber( callingTelephone ) ;
            if( phoneLibraryEntityList != null && phoneLibraryEntityList.size() > 0 ){
                PhoneLibraryEntity phoneLibraryEntity = phoneLibraryEntityList.get(0);
                installedAddress = phoneLibraryEntity.getUserAddress() ;
                installedUserName = phoneLibraryEntity.getUserName() ;
            }

            TelephoneEntity telephoneEntity = saveTelephoneRecord( callingTelephone , calledTelephone , servletService.getSystemTime() ,
                     userInfo , installedAddress , installedUserName , isCallback , incidentId   ) ;
            telephoneEntity.setRelayRecordNumber( null  );
            telephoneEntity.setPersonId( userInfo.getPersonId() );
            telephoneEntity.setSeatNumber( userInfo.getAgentNum() );
            telephoneEntity.setPersonName( userInfo.getPersonName() );
            telephoneEntity.setPersonNumber( userInfo.getAccount() );

            if (null != telephoneEntity) {
                logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(telephoneEntity);
                telephoneEntity.setIdCode( telephoneEntity.getId() );
                accessor.save(telephoneEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            res = transform(telephoneEntity , dicsMap );



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "ringSaveTelephoneRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "ringSaveTelephoneRecord", String.format(" ring  save Telephone fail by callingTelephone r: %s.", JSON.toJSONString(callingTelephone)), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_TELEPHONE_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #callBackSaveCallRecord(CallBackSaveInputInfo)
     */
    @Transactional
    @Override
    public TelephoneBean callBackSaveCallRecord(CallBackSaveInputInfo inputInfo) {
        if (inputInfo == null || StringUtils.isBlank(inputInfo.getCalledNumber()) || StringUtils.isBlank(inputInfo.getCalledNumber())) {
            logService.erorLog(logger, "service", "callBackSaveCallRecord", String.format("call back fail,the request data is null:%s", JSONObject.toJSONString(inputInfo)), null);
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "callBackSaveCallRecord", "service is started...");
            Long logStart = System.currentTimeMillis();


            TelephoneBean res = null ;

            // 生成电话报警
            UserInfo userInfo = userService.getUserInfo();

            //根据电话号码获得装机信息
            String installedAddress = null ;
            String installedUserName = null ;
            List<PhoneLibraryEntity> phoneLibraryEntityList = telephoneRepository.findPhoneLibraryByPhoneNumber( inputInfo.getCalledNumber() ) ;
            if( phoneLibraryEntityList != null && phoneLibraryEntityList.size() > 0 ){
                PhoneLibraryEntity phoneLibraryEntity = phoneLibraryEntityList.get(0);
                installedAddress = phoneLibraryEntity.getUserAddress() ;
                installedUserName = phoneLibraryEntity.getUserName() ;
            }

            TelephoneEntity telephoneEntity = saveTelephoneRecord( inputInfo.getCallingNumber() , inputInfo.getCalledNumber() , servletService.getSystemTime() ,
                    userInfo , installedAddress , installedUserName , Boolean.TRUE , inputInfo.getIncidentId()  ) ;
            telephoneEntity.setRelayRecordNumber( inputInfo.getRelayRecordNumber() );
            telephoneEntity.setPersonId( userInfo.getPersonId() );
            telephoneEntity.setSeatNumber( userInfo.getAgentNum() );
            telephoneEntity.setPersonName( userInfo.getPersonName() );
            telephoneEntity.setPersonNumber( userInfo.getAccount() );

            if (null != telephoneEntity) {
                logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(telephoneEntity);
                telephoneEntity.setIdCode( telephoneEntity.getId() );
                accessor.save(telephoneEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", String.format("repository is finished,execute time is :%sms", end - start));
            }
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            res = transform(telephoneEntity , dicsMap ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "callBackSaveCallRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "callBackSaveCallRecord", String.format("call back fail,the request data is :%s", JSONObject.toJSONString(inputInfo)), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_TELEPHONE_FAIL);
        }
    }




    private TelephoneEntity saveTelephoneRecord(String callingTelephone, String calledTelephone, Long currentTime , UserInfo
            userInfo,    String installedAddress , String installedUserName ,    Boolean isCallback    , String incidentId   ) {
        TelephoneEntity telephoneEntity = new TelephoneEntity();
        telephoneEntity.setCallingNumber( callingTelephone ); //主叫号码
        telephoneEntity.setCalledNumber( calledTelephone ); //被叫号码
        telephoneEntity.setRingingTime( currentTime  );

        telephoneEntity.setEndTime( null );

        if ( Strings.isNotBlank( incidentId )) {
            telephoneEntity.setIncidentId( incidentId );
        }

        telephoneEntity.setSeatNumber( userInfo.getAgentNum() );
        telephoneEntity.setAcdGroupNumber( userInfo.getAcdGroupNumber());
        //接警呼入
        telephoneEntity.setCallDirection( CALL_DIRECTION_HR.getCode() );

        // 装机地址
        if ( Strings.isNotBlank(installedAddress) ) {
            telephoneEntity.setInstalledAddress(installedAddress);
        }
        //装机用户名
        telephoneEntity.setInstalledUserName(installedUserName);


        // 如果是回拨确认，直接摘机，呼叫方向为呼出
        if ( isCallback != null && isCallback  ) {
            telephoneEntity.setAnswerTime(currentTime);
            telephoneEntity.setCallDirection( CALL_DIRECTION_HC.getCode());
            telephoneEntity.setInstalledUserName(userInfo.getUserName());
            telephoneEntity.setInstalledAddress(String.format("org[%s] room[%s] agent[%s]", userInfo.getOrgName(), userInfo.getAgentRoom(), userInfo.getAgentNum()));
        }

        return  telephoneEntity ;
    }



    /**
     * {@inheritDoc}
     *
     * @see #upLoadSoundRecordUpdateTelephone(String , String )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TelephoneBean pickUpUpdateTelephone(String telephoneId) {
        if ( Strings.isBlank( telephoneId )  ) {
            logService.infoLog(logger, "service", "pickUpUpdateTelephone", "telephoneId or agentCallRecordNum is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "pickUpUpdateTelephone", "service is started...");
            Long logStart = System.currentTimeMillis();

            TelephoneBean res = null ;

            logService.infoLog(logger, "repository", "findById(upLoadSoundRecordUpdateTelephone)", "repository is started...");
            Long start = System.currentTimeMillis();

            TelephoneEntity telephoneEntity =  accessor.getById( telephoneId , TelephoneEntity.class ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findById(telephoneId)", String.format("repository is finished,execute time is :%s ms", end - start));

            if( telephoneEntity == null || !telephoneEntity.isValid() ){
                logService.infoLog(logger, "service", "hangUpUpdateTelephone", String.format("can not find Telephone from db by telephoneId[%s]", telephoneId ));
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            // 更新电话呼入记录 接听时间 ( 接警开始时间 )
            Long currentTime = servletService.getSystemTime() ;
            telephoneEntity.setAnswerTime( currentTime );
            Long callRingTime = 0l;
            if ( telephoneEntity.getRingingTime()!= null  && telephoneEntity.getRingingTime()!= 0 ) {
                callRingTime = currentTime - telephoneEntity.getRingingTime() ;
            }
            telephoneEntity.setRingingDuration( callRingTime   ) ;

            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", "repository is started...");
            Long startSave = System.currentTimeMillis();

            accessor.save(telephoneEntity);

            Long endSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", String.format("repository is finished,execute time is :%sms", endSave - startSave ));

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            res = transform(telephoneEntity , dicsMap);

            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushTelephone(  res , otherParams  );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "pickUpUpdateTelephone", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "pickUpUpdateTelephone", String.format(" upLoad soundRecord update call fail by telephoneId: %s.", JSON.toJSONString(telephoneId )), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_TELEPHONE_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #upLoadSoundRecordUpdateTelephone(String , String )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TelephoneBean upLoadSoundRecordUpdateTelephone(String telephoneId, String agentCallRecordNum ) {
        if ( Strings.isBlank( telephoneId ) || StringUtils.isBlank(agentCallRecordNum)) {
            logService.infoLog(logger, "service", "upLoadSoundRecordUpdateTelephone", "telephoneId or agentCallRecordNum is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "upLoadSoundRecordUpdateTelephone", "service is started...");
            Long logStart = System.currentTimeMillis();

            TelephoneBean res = null ;

            logService.infoLog(logger, "repository", "findById(upLoadSoundRecordUpdateTelephone)", "repository is started...");
            Long start = System.currentTimeMillis();

            TelephoneEntity telephoneEntity =  accessor.getById( telephoneId , TelephoneEntity.class ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findById(telephoneId)", String.format("repository is finished,execute time is :%s ms", end - start));

            if( telephoneEntity == null || !telephoneEntity.isValid() ){
                logService.infoLog(logger, "service", "hangUpUpdateTelephone", String.format("can not find Telephone from db by telephoneId[%s]", telephoneId ));
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            //更新录音号
            telephoneEntity.setRelayRecordNumber(  agentCallRecordNum  );

            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", "repository is started...");
            Long startSave = System.currentTimeMillis();

            accessor.save(telephoneEntity);

            Long endSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", String.format("repository is finished,execute time is :%sms", endSave - startSave ));

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            res = transform(telephoneEntity , dicsMap);

            //保存录用记录
            SoundRecordBean soundRecord = new SoundRecordBean() ;
            soundRecord.setId( res.getId() );
            soundRecord.setIncidentId( res.getIncidentId());
            //判断是正常呼入 还是 回拨
            if(  CALL_DIRECTION_HR.getCode() == res.getCallDirection() ) {
                soundRecord.setType( 1 ); //接警录音
            }else{
                soundRecord.setType( 2 ); //接警回拨录音
            }
            soundRecord.setIncidentId( res.getIncidentId()  );
            soundRecord.setRecordId( agentCallRecordNum );
            soundRecord.setRecordNo( agentCallRecordNum );
            soundRecord.setFilename( null  );
            soundRecord.setSeatNumber( res.getSeatNumber() );
            soundRecord.setCallerId( res.getCallingNumber() );
            soundRecord.setCalledId( res.getCalledNumber() );
            soundRecord.setNumbers( null  );
            soundRecord.setBeginTime( servletService.getSystemTime() );
            soundRecord.setRemark( res.getRemarks() );
            SoundRecordBean soundRecordBean  =  soundRecordService.saveSoundRecord( soundRecord ) ;
            //其他系统
            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushSoundRecord( soundRecordBean , otherParams ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "upLoadSoundRecordUpdateTelephone", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "upLoadSoundRecordUpdateTelephone", String.format(" upLoad soundRecord update call fail by telephoneId: %s.", JSON.toJSONString(telephoneId )), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_TELEPHONE_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #hangUpUpdateTelephone(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TelephoneBean hangUpUpdateTelephone(String telephoneId) {
        if ( Strings.isBlank( telephoneId )) {
            logService.infoLog(logger, "service", "hangUpUpdateTelephone", "telephoneId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "hangUpUpdateTelephone", "service is started...");
            Long logStart = System.currentTimeMillis();

            TelephoneBean res = null ;

            logService.infoLog(logger, "repository", "findById(telephoneId)", "repository is started...");
            Long start = System.currentTimeMillis();

            TelephoneEntity telephoneEntity =  accessor.getById( telephoneId , TelephoneEntity.class ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findById(telephoneId)", String.format("repository is finished,execute time is :%s ms", end - start));

            if( telephoneEntity == null || !telephoneEntity.isValid() ){
                logService.infoLog(logger, "service", "hangUpUpdateTelephone", String.format("can not find Telephone from db by telephoneId[%s]", telephoneId ));
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            Long currentTime = servletService.getSystemTime() ;
            //更新电话报警 结束时间
            telephoneEntity.setEndTime( currentTime );
            //通话时长
            Long talkTime = 0l;
            if ( telephoneEntity.getAnswerTime() != null && telephoneEntity.getAnswerTime() != 0 ) {
                talkTime = currentTime - telephoneEntity.getAnswerTime() ;
            }
            telephoneEntity.setConversationDuration( talkTime   );

            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", "repository is started...");
            Long startSave = System.currentTimeMillis();

            accessor.save(telephoneEntity);

            Long endSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", String.format("repository is finished,execute time is :%sms", endSave - startSave ));

            //判断是否已经与受理单关联  如果关联判断
            logService.infoLog(logger, "repository", "save(dbAcceptanceEntity)", "repository is started...");
            Long startAcceptance = System.currentTimeMillis();

            String acceptanceId = telephoneEntity.getAcceptanceId() ;
            if( Strings.isNotBlank( acceptanceId ) ){
                AcceptanceEntity acceptanceEntity =  accessor.getById( acceptanceId , AcceptanceEntity.class ) ;
                if( acceptanceEntity != null  &&  acceptanceEntity.getAlarmEndTime() == null  ){
                    acceptanceEntity.setAlarmEndTime( currentTime );
                    acceptanceEntity.setReceiveEndTime( currentTime );
                    if( acceptanceEntity.getReceiveStartTime() != null && acceptanceEntity.getReceiveStartTime() != 0
                            &&  acceptanceEntity.getReceiveEndTime() != null && acceptanceEntity.getReceiveEndTime() != 0 ){
                        acceptanceEntity.setReceiveDuration(      acceptanceEntity.getReceiveEndTime()  - acceptanceEntity.getReceiveStartTime()        );
                    }
                    accessor.save( acceptanceEntity ) ;
                }
            }


            Long endAcceptance = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbAcceptanceEntity)", String.format("repository is finished,execute time is :%sms", endAcceptance - startAcceptance ));

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            res = transform(telephoneEntity , dicsMap);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "hangUpUpdateTelephone", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "hangUpUpdateTelephone", String.format(" hang update Telephone fail by telephoneId: %s.", JSON.toJSONString(telephoneId )), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_TELEPHONE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #updateTelephoneCoordinates(String , String , String, String )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TelephoneBean updateTelephoneCoordinates(String telephoneId , String longitude , String latitude , String height ) {
        if ( Strings.isBlank( telephoneId )) {
            logService.infoLog(logger, "service", "updateTelephoneCoordinates", "telephoneId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateTelephoneCoordinates", "service is started...");
            Long logStart = System.currentTimeMillis();

            TelephoneBean res = null ;

            logService.infoLog(logger, "repository", "findById(telephoneId)", "repository is started...");
            Long start = System.currentTimeMillis();

            TelephoneEntity telephoneEntity =  accessor.getById( telephoneId , TelephoneEntity.class ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findById(telephoneId)", String.format("repository is finished,execute time is :%s ms", end - start));

            if( telephoneEntity == null || !telephoneEntity.isValid() ){
                logService.infoLog(logger, "service", "hangUpUpdateTelephone", String.format("can not find Telephone from db by telephoneId[%s]", telephoneId ));
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            telephoneEntity.setLongitude(longitude);
            telephoneEntity.setLatitude(latitude);
            telephoneEntity.setHeight(height);

            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", "repository is started...");
            Long startSave = System.currentTimeMillis();

            accessor.save(telephoneEntity);

            Long endSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", String.format("repository is finished,execute time is :%sms", endSave - startSave ));

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            res = transform(telephoneEntity , dicsMap);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateTelephoneCoordinates", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateTelephoneCoordinates", String.format(" hang update Telephone fail by telephoneId: %s.", JSON.toJSONString(telephoneId )), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_TELEPHONE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findTelephoneIds(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findTelephoneIds (String incidentId){
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findTelephoneIds", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findTelephoneIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();



            logService.infoLog(logger, "repository", "findTelephoneByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            //获取所有电话报警记录
            List<TelephoneEntity> telephoneEntityList = telephoneRepository.findTelephoneByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findTelephoneByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            if (telephoneEntityList != null && telephoneEntityList.size() > 0) {
                for (TelephoneEntity entity : telephoneEntityList) {
                    res.add( entity.getId() );
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findTelephoneIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findTelephoneIds", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_TELEPHONE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see TelephoneService#findTelephone(String)
     */
    @Transactional(readOnly = true)
    @Override
    public TelephoneInformationBean findTelephone(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findTelephone", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findTelephone", "service is started...");
            Long logStart = System.currentTimeMillis();

            TelephoneInformationBean res = new TelephoneInformationBean();
            TelephoneBean mainTelephone = new TelephoneBean();
            List<TelephoneBean> telephoneList = new ArrayList<>();

            logService.infoLog(logger, "repository", "findTelephoneByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            //获取所有电话报警记录
            List<TelephoneEntity> telephoneEntityList = telephoneRepository.findTelephoneByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findTelephoneByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            if (telephoneEntityList != null && telephoneEntityList.size() > 0) {
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                for (TelephoneEntity entity : telephoneEntityList) {
                    TelephoneBean bean = transform(entity , dicsMap);
                    if( incidentId.equals(entity.getOriginalIncidentNumber() )  ){
                        mainTelephone = bean;
                    }
                    telephoneList.add(bean);
                }
            }

            //装配返回结果
            res.setMainTelephone(mainTelephone);
            res.setTelephoneList(telephoneList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findTelephone", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findTelephone", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_TELEPHONE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see TelephoneService#changeTelephoneIncidentId(String, String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeTelephoneIncidentId(String sourceIncidentId, String targetIncidentId) {
        if (Strings.isBlank(sourceIncidentId) || Strings.isBlank(targetIncidentId)) {
            logService.infoLog(logger, "service", "changeTelephoneIncidentId", "source or target IncidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "changeTelephoneIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据原案件id查找受理单
            logService.infoLog(logger, "repository", "findTelephoneByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<TelephoneEntity> telephoneEntityList = telephoneRepository.findTelephoneByIncidentId(sourceIncidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findTelephoneByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //将电话报警记录 关联的案件id置为目标案件id
            if (telephoneEntityList != null && telephoneEntityList.size() > 0) {
                for (TelephoneEntity entity : telephoneEntityList) {
                    entity.setIncidentId(targetIncidentId);
                }

                //保存更改
                logService.infoLog(logger, "repository", "save(dbTelephoneEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(telephoneEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTelephoneEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeTelephoneIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeTelephoneIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.CHANGE_TELEPHONE_INCIDENT_ID_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see TelephoneService#recoverTelephoneIncidentId(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recoverTelephoneIncidentId(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "recoverTelephoneIncidentId", "incidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "recoverTelephoneIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据原案件id查找受理单
            logService.infoLog(logger, "repository", "findTelephoneByOriginalIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<TelephoneEntity> telephoneEntityList = telephoneRepository.findTelephoneByOriginalIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findTelephoneByOriginalIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //将电话报警记录 关联的案件id置为原始案件id
            if (telephoneEntityList != null && telephoneEntityList.size() > 0) {
                for (TelephoneEntity entity : telephoneEntityList) {
                    entity.setIncidentId(entity.getOriginalIncidentNumber());
                }

                //保存更改
                logService.infoLog(logger, "repository", "save(dbTelephoneEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(telephoneEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTelephoneEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "recoverTelephoneIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "recoverTelephoneIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.RECOVER_TELEPHONE_INCIDENT_ID_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see TelephoneService#findPhoneLibraryByPhoneNumber(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<PhoneLibraryBean> findPhoneLibraryByPhoneNumber(String phoneNumber) {
        if (Strings.isBlank(phoneNumber)) {
            logService.infoLog(logger, "service", "findPhoneLibraryByPhoneNumber", "phoneNumber is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPhoneLibraryByPhoneNumber", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<PhoneLibraryBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findPhoneLibraryByPhoneNumber", "repository is started...");
            Long start = System.currentTimeMillis();

            List<PhoneLibraryEntity> phoneLibraryEntityList = telephoneRepository.findPhoneLibraryByPhoneNumber(phoneNumber);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPhoneLibraryByPhoneNumber", String.format("repository is finished,execute time is :%sms", end - start));

            if (phoneLibraryEntityList != null && phoneLibraryEntityList.size() > 0) {
                for (PhoneLibraryEntity entity : phoneLibraryEntityList) {
                    PhoneLibraryBean bean = transform(entity);
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPhoneLibraryByPhoneNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPhoneLibraryByPhoneNumber", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_TELEPHONE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see TelephoneService#findAlarmCount(String)
     */
    @Transactional(readOnly = true)
    @Override
    public Long findAlarmCount(String callingNumber) {
        if (Strings.isBlank(callingNumber)) {
            logService.infoLog(logger, "service", "findAlarmCount", "callingNumber is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAlarmCount", "service is started...");
            Long logStart = System.currentTimeMillis();

            Long res = 0L;

            Long targetTime =  servletService.getSystemTime() ;

            DateUtils.FieldType fieldType = DateUtils.FieldType.HOUR;

            Integer time = 1;
            //获取系统配置，判断重复报警查询的时间范围
            SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("repeatAlarmDetermineTime");
            if (systemConfigurationBean != null) {
                String configValue = systemConfigurationBean.getConfigValue();
                if(Strings.isNotBlank(configValue)){
                    try{
                        Integer temp = Integer.valueOf(configValue);
                        if( temp > 0 ){
                            time = temp ;
                        }
                    }catch ( Exception ex ){
                        time = 1 ;
                    }
                }
            }

            Long startTime = DateUtils.add(new Date(targetTime), fieldType,  Integer.parseInt( "-" + time ) ).getTime();

            logService.infoLog(logger, "repository", "findAlarmCount", "repository is started...");
            Long start = System.currentTimeMillis();

            res  = telephoneRepository.findAlarmCount(callingNumber,startTime,targetTime);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAlarmCount", String.format("repository is finished,execute time is :%sms", end - start));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAlarmCount", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAlarmCount", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_TELEPHONE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #monitorInsert(Integer)
     */
    public Boolean monitorInsert(Integer agentNum) {
        try {
            List<AgentBean> allAgent = agentService.findAllAgent();
            List<String> toUsers = new ArrayList<>();
            if (allAgent != null && allAgent.size() > 0) {
                for (AgentBean agentBean : allAgent) {
                    if (Integer.valueOf(agentBean.getAgentNumber()) == agentNum) {
                        toUsers.add(agentBean.getPersonBean().getAccount());
                        break;
                    }
                }
                //websocket推送
                if (!toUsers.isEmpty()) {
                    notifyActionService.pushMessageToUsers(WebsocketCodeEnum.MONITORINSERT.getCode(), userService.getUserInfo().getAgentNum(), toUsers);
                }
            }
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "monitorInsert", "push monitorInsert message failed", ex);
            return false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #monitorBarge(Integer)
     */
    public Boolean monitorBarge(Integer agentNum) {
        try {
            List<AgentBean> allAgent = agentService.findAllAgent();
            List<String> toUsers = new ArrayList<>();
            if (allAgent != null && allAgent.size() > 0) {
                for (AgentBean agentBean : allAgent) {
                    if (Integer.valueOf(agentBean.getAgentNumber()) == agentNum) {
                        toUsers.add(agentBean.getPersonBean().getAccount());
                        break;
                    }
                }
                //websocket推送
                if (!toUsers.isEmpty()) {
                    notifyActionService.pushMessageToUsers(WebsocketCodeEnum.MONITORBARGE.getCode(), userService.getUserInfo().getAgentNum(), toUsers);
                }
            }
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "monitorInsert", "push monitorInsert message failed", ex);
            return false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #getmonitorInsertToPushCaseinfo(MonitotTakeOverIncidentInputInfo, Integer)
     */
    public Boolean getmonitorInsertToPushCaseinfo(MonitotTakeOverIncidentInputInfo caseInfoBO, Integer agentNum) {
        try {
            List<AgentBean> allAgent = agentService.findAllAgent();
            List<String> toUsers = new ArrayList<>();
            MonitorCaseInputInfo monitorCaseInfoBO = new MonitorCaseInputInfo();
            monitorCaseInfoBO.setAgentNum(Integer.valueOf(this.userService.getUserInfo().getAgentNum()));
            monitorCaseInfoBO.setCaseInfoBO(caseInfoBO);
            if (allAgent != null && allAgent.size() > 0) {
                for (AgentBean agentBean : allAgent) {
                    if (Integer.valueOf(agentBean.getAgentNumber()) == agentNum) {
                        toUsers.add(agentBean.getPersonBean().getAccount());
                        break;
                    }
                }
                //websocket推送
                if (!toUsers.isEmpty()) {
                    notifyActionService.pushMessageToUsers(WebsocketCodeEnum.MONITORINSERT_PUSHCASEINFO.getCode(), monitorCaseInfoBO, toUsers);
                }
            }
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getmonitorInsertToPushCaseinfo", "push getmonitorInsertToPushCaseinfo message failed", ex);
            return false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #getmonitorInsertToPushCaseinfoResult(MonitotTakeOverIncidentInputInfo, Integer)
     */
    public Boolean getmonitorInsertToPushCaseinfoResult(MonitotTakeOverIncidentInputInfo caseInfoBO, Integer agentNum) {
        try {
            UserInfo userInfo = userService.getUserInfo();
            Long time = servletService.getSystemTime();
            IncidentBean incidentBean = caseInfoBO.getIncidentBean();

            String auditLogDetails = String.format("Supervisior AgentNum:%s ActiveTakeover Calltaker AgentNum:%d, IncidentId:%s", userInfo.getAgentNum(), agentNum, incidentBean.getId());
            //保存操作记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(time);
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_ACTIVETAKEOVER.getCode()));
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
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_ACTIVETAKEOVER.getName());

            try {
                List<AgentBean> allAgent = agentService.findAllAgent();
                List<ReceiverMessageBean> receivers = new ArrayList<>();
                if (allAgent != null && allAgent.size() > 0) {
                    for (AgentBean agentBean : allAgent) {
                        if (Integer.valueOf(agentBean.getAgentNumber()) == agentNum) {
                            ReceiverMessageBean receiver = new ReceiverMessageBean("user", agentBean.getPersonBean().getAccount());
                            receivers.add(receiver);
                            break;
                        }
                    }
                    //websocket推送
                    if (!receivers.isEmpty()) {
                        notifyActionService.pushMessage(WebsocketCodeEnum.MONITORINSERT_PUSHCASEINFO_RESULT.getCode(), incidentBean , receivers);
                    }
                }
            } catch (Exception ex) {
                logService.erorLog(logger, "service", "getmonitorInsertToPushCaseinfoResult", "push getmonitorInsertToPushCaseinfoResult message failed", ex);
            }
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getmonitorInsertToPushCaseinfoResult", "push getmonitorInsertToPushCaseinfoResult message failed", ex);
            return false;
        }
    }



    /**
     * {@inheritDoc}
     *
     */
    @Transactional
    @Override
    public VoiceTranscribeBean saveVoiceTranscribe(VoiceTranscribeSaveInputInfo inputInfo ) {
        if ( inputInfo == null   ) {
            logService.infoLog(logger, "service", "saveVoiceTranscribe", "voiceTranscribeSaveInputInfo   is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            decodeVoiceTranscribeSaveInputInfo( inputInfo ) ;

            logService.infoLog(logger, "service", "saveVoiceTranscribe", "service is started...");
            Long logStart = System.currentTimeMillis();

            VoiceTranscribeBean  res =  null ;

            VoiceTranscribeEntity voiceTranscribeEntity = transform( inputInfo );

            UserInfo userInfo = userService.getUserInfo() ;
            if( userInfo != null ){
                voiceTranscribeEntity.setSeatNumber( userInfo.getAgentNum() );
                voiceTranscribeEntity.setAcceptancePerson( userInfo.getAccount() );
                voiceTranscribeEntity.setAcceptancePersonName( userInfo.getPersonName() );
                voiceTranscribeEntity.setAcceptancePersonNumber( userInfo.getAccount() );
            }

            if( voiceTranscribeEntity != null ){
                logService.infoLog(logger, "repository", "saveVoiceTranscribe", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( voiceTranscribeEntity ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "saveVoiceTranscribe", String.format("repository is finished,execute time is :%sms", end - start));

                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

                res = transform( voiceTranscribeEntity , dicsMap );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveVoiceTranscribe", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "saveVoiceTranscribe", String.format("call back fail,the request data is :%s", "" ), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     */
    @Transactional
    @Override
    public PaginationBean<TelephoneBean> findTelephonePagination(  TelephoneQueryInputInfo queryBean  ){

        try {

            logService.infoLog(logger, "service", "saveVoiceTranscribe", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<TelephoneBean> res = new PaginationBean<>();
            List<TelephoneBean> beans = new ArrayList<>();

            //如果没有开始时间 结束时间 设置默认时间段
            if ( queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, -1 ).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }

            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<TelephoneEntity> telephoneEntityList = telephoneRepository.findTelephoneCondition( queryBean.getStartTime() , queryBean.getEndTime() ,
                    queryBean.getSeatNumber() , queryBean.getPersonNumber() ,queryBean.getPhoneKeyword() , queryBean.getNameKeyword() ,
                    queryBean.getWhetherPage() , queryBean.getPagination().getPage() , queryBean.getPagination().getSize() )  ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (telephoneEntityList != null && telephoneEntityList.size() > 0) {
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                for (TelephoneEntity telephoneEntity  : telephoneEntityList) {
                    TelephoneBean bean = transform( telephoneEntity , dicsMap );
                    String incidentId = bean.getIncidentId();
                    if (Strings.isNotBlank(incidentId)){
                        IncidentEntity incidentEntity = accessor.getById(incidentId,IncidentEntity.class);
                        if (incidentEntity!=null&&incidentEntity.isValid()){
                            bean.setIncidentNumber(incidentEntity.getIncidentNumber());
                        }
                    }
                    beans.add( bean );
                }
            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total =  telephoneRepository.findTelephoneConditionTotal( queryBean.getStartTime() , queryBean.getEndTime() ,
                    queryBean.getSeatNumber() , queryBean.getPersonNumber() ,queryBean.getPhoneKeyword() , queryBean.getNameKeyword()  )  ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveVoiceTranscribe", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "saveVoiceTranscribe", String.format("call back fail,the request data is :%s", "" ), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }


    /**
     * 对IncidentSaveInputInfo中需要前端手动输入的属性进行解码
     * URLDecoder
     *
     */
    private void decodeVoiceTranscribeSaveInputInfo(VoiceTranscribeSaveInputInfo source){
        if (source != null){
            try {
                if (!StringUtils.isBlank(source.getTranscribeContent())){
                    source.setTranscribeContent((URLDecoder.decode(source.getTranscribeContent(),"utf-8")));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL );
            }
        }
    }

}
