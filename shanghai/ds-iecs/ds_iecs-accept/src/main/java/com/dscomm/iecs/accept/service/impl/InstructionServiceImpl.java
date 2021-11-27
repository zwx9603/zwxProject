package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.InstructionEntity;
import com.dscomm.iecs.accept.dal.po.InstructionRecordEntity;
import com.dscomm.iecs.accept.dal.repository.InstructionRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.DocumentService;
import com.dscomm.iecs.accept.service.HandleService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.InstructionService;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_QT;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_JA;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.error.UserInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;

@Component("instructionServiceImpl")
public class InstructionServiceImpl implements InstructionService {

    private static final Logger logger = LoggerFactory.getLogger(IncidentStatusChangeServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private ServletService servletService ;
    private AuditLogService auditLogService;
    private SubAuditService subAuditService;
    private UserService userService ;
    private DocumentService documentService;
    private NotifyActionService notifyActionService ;
    private OrganizationService organizationService ;
    private IncidentService incidentService ;
    private HandleService handleService ;
    private InstructionRepository instructionRepository ;
    private VehicleService vehicleService ;
    private DictionaryService dictionaryService ;
    private PushDataService pushDataService ;


    private List<String> dics;

    /**
     * 默认的构造函数
     *
     * @param accessor   JPA数据库操作器
     */
    @Autowired
    public InstructionServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env ,
                                  ServletService servletService , AuditLogService auditLogService  , SubAuditService subAuditService  ,
                                  UserService userService , DocumentService documentService , NotifyActionService notifyActionService ,
                                  OrganizationService organizationService , IncidentService incidentService , HandleService handleService ,
                                  InstructionRepository instructionRepository , VehicleService vehicleService ,
                                  DictionaryService dictionaryService , PushDataService pushDataService


    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.servletService = servletService ;
        this.auditLogService = auditLogService;
        this.subAuditService = subAuditService ;
        this.userService = userService ;
        this.documentService = documentService ;
        this.notifyActionService = notifyActionService ;
        this.organizationService = organizationService ;
        this.incidentService = incidentService ;
        this.handleService = handleService ;
        this.instructionRepository = instructionRepository ;
        this.vehicleService = vehicleService ;
        this.dictionaryService = dictionaryService ;
        this.pushDataService = pushDataService ;

        dics = new ArrayList<>(Arrays.asList("ZLLX", "ZLLB", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX" ,"JZJG" ,"YWQK", "ZHCS" ,"JQDX"   ));
    }


    /**
     * {@inheritDoc}
     *
     * @see #saveInstruction( InstructionSaveInputInfo  )
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public InstructionBean saveInstruction ( InstructionSaveInputInfo queryBean  )  {
        if ( queryBean == null || Strings.isBlank( queryBean.getIncidentId() ) ) {
            logService.infoLog(logger, "service", "saveInstruction", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveInstruction", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeInstructionSaveInputInfo(queryBean);//URLDecoder inputInfo编码

            InstructionBean res = null ;

            String incidentId = queryBean.getIncidentId();//案件ID
            String commandId = queryBean.getCommandId();//指挥ID
            //转换调派单
            InstructionEntity instructionEntity  = HandleDispatchTransformUtil.transform( queryBean )  ;
            if( queryBean.getInstructionsTime() == null ){
                instructionEntity.setInstructionsTime( servletService.getSystemTime()  );
            }else{
                instructionEntity.setInstructionsTime(queryBean.getInstructionsTime()  );
            }

            List<String> receivingObjectIds = new ArrayList<>() ;

            if( instructionEntity != null   ){
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                //保存调派单  生成 调派单id
                logService.infoLog(logger, "repository", "save( dbInstructionEntity )", "repository is started...");
                Long startInstruction = System.currentTimeMillis();

                accessor.save( instructionEntity );
                instructionEntity.setIdCode( instructionEntity.getId() );
                accessor.save( instructionEntity );

                Long endInstruction = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save( dbInstructionEntity )", String.format("repository is finished,execute time is :%sms", endInstruction - startInstruction ));

                String instructionId = instructionEntity.getId() ;

                res = HandleDispatchTransformUtil.transform( instructionEntity , dicsMap ) ;

                if( Strings.isBlank( instructionId ) ){
                    if (logger.isDebugEnabled()) {
                        logger.debug(" instructionId id  is null    ");
                    }
                    throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL );
                }
                //保存接收对象 生成调派指令单
                List<InstructionRecordSaveInputInfo> instructionRecordSaveInputInfoList = queryBean.getInstructionRecordList() ;
                if( instructionRecordSaveInputInfoList == null ){
                    instructionRecordSaveInputInfoList = new ArrayList<>() ;
                }
                List<InstructionRecordEntity> instructionRecordEntityList = new ArrayList<>() ;
                List<InstructionRecordBean> instructionRecordBeanList = new ArrayList<>() ;

                //只有指令来源为接处警的场合才需要生成调派指令单
                if (res.getInstructionsSource().equals("1")){

                    List<HandleOrganizationVehicleBean> handleVechicleBeans = handleService.findHandleOrganizationVehicle(incidentId, "", true);

                    if (handleVechicleBeans != null && handleVechicleBeans.size()>0){
                        for (HandleOrganizationVehicleBean handleOrganizationVehicleBean:handleVechicleBeans){
                            if (handleOrganizationVehicleBean != null){
                                InstructionRecordSaveInputInfo recordSaveInputInfo = new InstructionRecordSaveInputInfo();
                                recordSaveInputInfo.setReceivingObjectType("1");//调派对象为车辆
                                recordSaveInputInfo.setReceivingObjectId(handleOrganizationVehicleBean.getVehicleId());
                                recordSaveInputInfo.setReceivingObjectName(handleOrganizationVehicleBean.getVehicleName());
                                recordSaveInputInfo.setReceivingObjectOrganizationId(handleOrganizationVehicleBean.getOrganizationId());
                                recordSaveInputInfo.setReceivingObjectOrganizationName(handleOrganizationVehicleBean.getOrganizationName());
                                instructionRecordSaveInputInfoList.add(recordSaveInputInfo);
                            }
                        }
                    }

                }

                if( instructionRecordSaveInputInfoList != null && instructionRecordSaveInputInfoList.size() > 0 ){
                    for (InstructionRecordSaveInputInfo instructionRecordSaveInputInfo  : instructionRecordSaveInputInfoList) {
                        receivingObjectIds.add( instructionRecordSaveInputInfo.getReceivingObjectId() ) ;
                        InstructionRecordEntity instructionRecordEntity =  HandleDispatchTransformUtil.transform ( instructionRecordSaveInputInfo , incidentId , commandId , instructionId ) ;
                        //判断指令记录实体是否为空，不为空则执行保存操作
                        if ( instructionRecordEntity != null) {
                            instructionRecordEntity.setInstructionRecordTime( servletService.getSystemTime() );
                            instructionRecordEntityList.add( instructionRecordEntity ) ;
                        }


                    }
                    // 保存 指令记录单
                    logService.infoLog(logger, "repository", "save( dbInstructionRecordEntityList )", "repository is started...");
                    Long startInstructionRecord = System.currentTimeMillis();

                    accessor.save( instructionRecordEntityList ) ;

                    Long endInstructionRecord = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save( dbInstructionRecordEntityList )", String.format("repository is finished,execute time is :%sms", endInstructionRecord - startInstructionRecord ));

                    for (  InstructionRecordEntity instructionRecordEntity  : instructionRecordEntityList) {
                        InstructionRecordBean instructionRecordBean =  HandleDispatchTransformUtil.transform ( instructionRecordEntity , dicsMap  ) ;
                        instructionRecordBeanList.add( instructionRecordBean ) ;
                    }

                    res.setInstructionRecordList( instructionRecordBeanList );
                }
            }

            IncidentBean incidentBean =  incidentService.findIncident( queryBean.getIncidentId() , false ) ;
            //判断是否需要handle派车处理
            if (  queryBean.getWhetherInHandle() ) {
                handleRecord(incidentBean , res.getInstructionRecordList() ) ;
            }


            UserInfo userInfo = userService.getUserInfo();


            //下达指令  保存文书
            DocumentSaveInputInfo inputInfo = new DocumentSaveInputInfo();
            inputInfo.setIncidentId(incidentId);
            inputInfo.setDateSourceId( res.getId() );
            inputInfo.setTitle( DOCUMENT_TYPE_QT.getName() );
            inputInfo.setContent(  queryBean.getInstructionsContent()  );
            inputInfo.setType(  DOCUMENT_TYPE_QT.getCode() );
            inputInfo.setFeedbackPerson(userInfo.getPersonName());
            inputInfo.setFeedbackOrganizationId(userInfo.getOrgId());
            inputInfo.setTerminalId(null);
            inputInfo.setRemarks(null);
            documentService.saveDocument(inputInfo);

            //保存操作日志记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_INSTRUCTION.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum() );
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom() );
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc( "incident dispatch instruction  content:  \"" + queryBean.getInstructionsContent()  + "\" " );
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_INSTRUCTION.getName());


            //websocket 通知
            InstructionWebSocketPushBean instructionWebSocketPushBean = new InstructionWebSocketPushBean();
            instructionWebSocketPushBean.setIncidentBean(incidentBean);
            instructionWebSocketPushBean.setInstructionBean(res);

            Set<String> orgSet = new HashSet<>() ;
            //通知处警单位
            orgSet.add(queryBean.getInstructionsOrganizationId());
            //警情调派单位
            orgSet.addAll( receivingObjectIds ) ;

            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( queryBean.getIncidentId() );
            orgSet.addAll( orgIds ) ;

            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);

            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_INSTRUCTION.getCode(), instructionWebSocketPushBean, orgSet );

            // 其他系统
            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushIncidentInstruction(  incidentId , receivingObjectIds , res , otherParams ) ;


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveInstruction", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return  res ;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveInstruction", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * 判断是否需要调用 派车接口
     */
    private  void  handleRecord( IncidentBean incidentBean  , List<InstructionRecordBean> instructionRecordBeanList ){
        //判断警情是否已经结案 已经结案 不进行派车

        if( incidentBean == null ||  INCIDENT_STATUS_JA.getCode().equals( incidentBean.getIncidentStatusCode() ) ||
                    INCIDENT_STATUS_JA.getCode().equals( incidentBean.getIncidentStatusCode() ) ||
                    INCIDENT_STATUS_JA.getCode().equals( incidentBean.getIncidentStatusCode() ) ){
            return;
        }
        if( instructionRecordBeanList != null && instructionRecordBeanList.size() > 0   ){
            //获得调派车辆信息
            List<String>  vehicleIds = new ArrayList<>( ) ;

            for(InstructionRecordBean instructionRecordBean : instructionRecordBeanList ){
                vehicleIds.add( instructionRecordBean.getReceivingObjectId() ) ;

            }
            //获得处警中车辆信息( 去掉 返回 中返 状态车辆)
            List<String> fightVehicleIds = handleService.findIncidentFightVehicleIds( incidentBean.getId() , vehicleIds  ) ;
            List<String> tempVehicleIds = vehicleIds ;
            tempVehicleIds.removeAll( fightVehicleIds ) ;
            if( tempVehicleIds != null && tempVehicleIds.size() > 0  ){
                List<EquipmentVehicleBean> vehicleBeans = vehicleService.findVehicleCacheList( tempVehicleIds ) ;

                //组合调派信息
                UserInfo userInfo = userService.getUserInfo();
                HandleSaveInputInfo queryBean = new HandleSaveInputInfo();
                queryBean.setIncidentId( incidentBean.getId() );
                queryBean.setHandleOrganizationId( userInfo.getOrgId() );
                queryBean.setHandleSeatNumber( userInfo.getAgentNum() );
                queryBean.setHandlePersonName( userInfo.getPersonName() );
                queryBean.setHandlePersonNumber( userInfo.getAccount() );
                queryBean.setHandleSource("2");// 处警来源（ 1 接处警/ 2实战指挥/ 3移动终端）
                Map<String , List<HandleOrganizationVehicleSaveInputInfo> > vehicleSaveMap = new HashMap<>( ) ;
                for( EquipmentVehicleBean vehicleBean : vehicleBeans ){
                    List<HandleOrganizationVehicleSaveInputInfo>  handleVehicle = vehicleSaveMap.get( vehicleBean.getOrganizationId() ) ;
                    if( handleVehicle == null ){
                        handleVehicle = new ArrayList<>( ) ;
                    }
                    HandleOrganizationVehicleSaveInputInfo handleOrganizationVehicleSaveInputInfo = new HandleOrganizationVehicleSaveInputInfo();
                    handleOrganizationVehicleSaveInputInfo.setVehicleId( vehicleBean.getId());
                    handleOrganizationVehicleSaveInputInfo.setVehicleTypeCode( vehicleBean.getVehicleTypeCode() );
                    handleVehicle.add(handleOrganizationVehicleSaveInputInfo) ;
                    vehicleSaveMap.put(  vehicleBean.getOrganizationId() ,handleVehicle  ) ;
                }
                List<HandleOrganizationSaveInputInfo>  handleOrganizationSaveInputInfoList = new ArrayList<>() ;
                for( String key : vehicleSaveMap.keySet() ){
                    HandleOrganizationSaveInputInfo handleOrganizationSaveInputInfo = new HandleOrganizationSaveInputInfo() ;
                    handleOrganizationSaveInputInfo.setOrganizationId( key );
                    handleOrganizationSaveInputInfo.setHandleOrganizationVehicleSaveInputInfo( vehicleSaveMap.get( key  ));
                    handleOrganizationSaveInputInfoList.add( handleOrganizationSaveInputInfo ) ;
                }
                queryBean.setHandleOrganizationSaveInputInfo( handleOrganizationSaveInputInfoList );
                handleService.saveHandle( queryBean ,null  );
            }
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #findInstructionCondition( InstructionQueryInputInfo )
     */
    @Transactional(  readOnly =  true )
    @Override
    public List<InstructionBean> findInstructionCondition (InstructionQueryInputInfo queryBean   ){
        try {

            logService.infoLog(logger, "service", "findDispatchCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeInstructionQueryInputInfo(queryBean);//URLDecoder query编码

            Map<String,InstructionBean > instructionBeanMap = new HashMap<>() ;
            Map<String,List<InstructionRecordBean> > instructionRecordMap = new HashMap<>() ;


            List<InstructionBean>  beans = new ArrayList<>() ;

            logService.infoLog(logger, "repository", "findInstructionCondition", "repository is started...");
            Long startInstruction = System.currentTimeMillis();

            List<InstructionEntity> instructionEntityList =  instructionRepository.findInstructionCondition( queryBean.getIncidentId() , queryBean.getCommandId() ,
                    queryBean.getInstructionType() , queryBean.getInstructionsSource() ,  queryBean.getKeyword() ) ;

            Long endInstruction = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findInstructionCondition", String.format("repository is finished,execute time is :%sms", endInstruction - startInstruction ));



            if( instructionEntityList != null && instructionEntityList.size() > 0 ){

                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

                List<String> instructionIds = new ArrayList<>() ;
                for ( InstructionEntity instructionEntity  :  instructionEntityList) {
                    //转换调派单
                    InstructionBean instructionBean = HandleDispatchTransformUtil.transform( instructionEntity , dicsMap  );
                    instructionIds.add( instructionBean.getId() ) ;
                    instructionBeanMap.put( instructionBean.getId() , instructionBean ) ;
                }

                logService.infoLog(logger, "repository", "findInstructionCondition", "repository is started...");
                Long startInstructionRecord = System.currentTimeMillis();

                List<InstructionRecordEntity> instructionRecordEntityList  = instructionRepository.findInstructionRecord( instructionIds ) ;

                Long endInstructionRecord = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findInstructionCondition", String.format("repository is finished,execute time is :%sms", endInstructionRecord - startInstructionRecord ));


                for( InstructionRecordEntity instructionRecordEntity :  instructionRecordEntityList ){
                    InstructionRecordBean instructionRecordBean = HandleDispatchTransformUtil.transform( instructionRecordEntity , dicsMap  );
                    List<InstructionRecordBean>  instructionRecordBeanList = instructionRecordMap.get( instructionRecordBean.getInstructionId() ) ;
                    if( instructionRecordBeanList == null ){
                        instructionRecordBeanList = new ArrayList<>( ) ;
                        instructionRecordBeanList.add( instructionRecordBean ) ;
                    }else{
                        instructionRecordBeanList.add( instructionRecordBean ) ;
                    }
                    instructionRecordMap.put(instructionRecordBean.getInstructionId()  , instructionRecordBeanList ) ;
                }

                for( String key :  instructionBeanMap.keySet() ){
                    InstructionBean instructionBean = instructionBeanMap.get( key ) ;
                    instructionBean.setInstructionRecordList( instructionRecordMap.get( instructionBean.getId()  ) );
                    beans.add( instructionBean ) ;
                }

            }
            //指令
            beans.sort( new Comparator<InstructionBean>() {
                @Override
                public int compare(InstructionBean o1, InstructionBean o2) {
                    Long d1 = o1.getInstructionsTime();
                    Long d2 = o2.getInstructionsTime();
                    return  d2.compareTo(d1);
                }
            } );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDispatchCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return beans ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDispatchCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #findInstructionRecordCondition( InstructionRecordQueryInputInfo )
     */
    @Transactional(  readOnly =  true )
    @Override
    public  List<InstructionRecordBean> findInstructionRecordCondition (InstructionRecordQueryInputInfo queryBean    ) {
        try {

            logService.infoLog(logger, "service", "findDispatchInstructionCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<InstructionRecordBean>  beans = new ArrayList<>() ;


            Map<String , InstructionBean>  instructionBeanMap = new HashMap<>() ;
            List<String> instructionIds = new ArrayList<>() ;

            logService.infoLog(logger, "repository", "findInstructionRecordCondition", "repository is started...");
            Long startInstructionRecord = System.currentTimeMillis();

            List<InstructionRecordEntity> instructionRecordEntityList  =  instructionRepository.findInstructionRecordCondition( queryBean.getIncidentId() , queryBean.getCommandId() ,
                    queryBean.getInstructionId() , queryBean.getReceivingObjectId() , queryBean.getInstructState()  ) ;

            Long endInstructionRecord = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findInstructionRecordCondition", String.format("repository is finished,execute time is :%sms", endInstructionRecord - startInstructionRecord ));



            if( instructionRecordEntityList != null && instructionRecordEntityList.size() > 0 ){

                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

                for( InstructionRecordEntity instructionRecordEntity  :  instructionRecordEntityList ){
                    InstructionRecordBean instructionRecordBean =  HandleDispatchTransformUtil.transform( instructionRecordEntity , dicsMap  ) ;
                    instructionIds.add( instructionRecordBean.getInstructionId() ) ;
                    beans.add( instructionRecordBean ) ;
                }
                logService.infoLog(logger, "repository", "findInstructionCondition", "repository is started...");
                Long startInstruction = System.currentTimeMillis();

                List<InstructionEntity> instructionEntityList  = instructionRepository.findInstruction( instructionIds ) ;

                Long endInstruction = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findInstructionCondition", String.format("repository is finished,execute time is :%sms", endInstructionRecord - startInstructionRecord ));



                for( InstructionEntity instructionRecordEntity :  instructionEntityList ){
                    InstructionBean instructionBean = HandleDispatchTransformUtil.transform( instructionRecordEntity , dicsMap  );
                    instructionBeanMap.put(instructionBean.getId()  , instructionBean ) ;
                }

                for( InstructionRecordBean key :  beans ){
                    InstructionBean instructionBean = instructionBeanMap.get( key.getInstructionId() ) ;
                    key.setInstructionBean( instructionBean );
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDispatchInstructionCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return beans ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDispatchInstructionCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }




    /**InstructionSaveInputInfo 编码*/
    private void decodeInstructionSaveInputInfo(InstructionSaveInputInfo source){
        if (source != null){
            try {
                if (!StringUtils.isBlank(source.getInstructionsPersonName())){
                    source.setInstructionsPersonName(URLDecoder.decode(source.getInstructionsPersonName(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getInstructionsPersonRankName())){
                    source.setInstructionsPersonRankName(URLDecoder.decode(source.getInstructionsPersonRankName(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getInstructionsContent())){
                    source.setInstructionsContent(URLDecoder.decode(source.getInstructionsContent(),"UTF-8"));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }

    /**InstructionQueryInputInfo 编码*/
    private void decodeInstructionQueryInputInfo(InstructionQueryInputInfo query){
        if (query != null){
            try {
                if (!StringUtils.isBlank(query.getKeyword())){
                    query.setKeyword(URLDecoder.decode(query.getKeyword(),"UTF-8"));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #saveInstruction( InstructionSaveInputInfo  )
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean saveInstructionRecordHandle (  InstructionRecordHandleSaveInputInfo queryBean    ){
        if ( queryBean == null || Strings.isBlank( queryBean.getId()  ) ) {
            logService.infoLog(logger, "service", "saveInstructionRecordHandle", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveInstructionRecordHandle", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false ;

            //转码
            try {
                if (!StringUtils.isBlank(queryBean.getHandleResultDesc())){
                    queryBean.setHandleResultDesc(URLDecoder.decode(queryBean.getHandleResultDesc(),"UTF-8"));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }

            InstructionRecordEntity instructionRecordEntity =  accessor.getById( queryBean.getId() , InstructionRecordEntity.class  ) ;

            if( instructionRecordEntity != null   ){
                instructionRecordEntity.setHandleSign( EnableEnum.ENABLE_TRUE.getCode() );
                instructionRecordEntity.setHandlePersonName( queryBean.getHandlePersonName() );
                instructionRecordEntity.setHandleResultDesc( queryBean.getHandleResultDesc() );
                instructionRecordEntity.setHandleTime( servletService.getSystemTime() );

                logService.infoLog(logger, "repository", "save(dbInstructionRecordEntity )", "repository is started...");
                Long startInstruction = System.currentTimeMillis();

                accessor.save( instructionRecordEntity ) ;

                Long endInstruction = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbInstructionRecordEntity )", String.format("repository is finished,execute time is :%sms", endInstruction - startInstruction ));

                res = true ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveInstructionRecordHandle", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return  res ;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveInstructionRecordHandle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }



}
