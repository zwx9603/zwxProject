package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.*;
import com.dscomm.iecs.accept.dal.repository.HandleMiniatureStationRepository;
import com.dscomm.iecs.accept.dal.repository.HandleRepository;
import com.dscomm.iecs.accept.dal.repository.IVRPlayRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.HandleMiniatureStationFeedbackSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.HandleMiniatureStationSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.HandleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleMiniatureStationBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleMiniatureStationFeedbackBean;
import com.dscomm.iecs.accept.hangzhou.service.DahuaService;
import com.dscomm.iecs.accept.service.HandleMiniatureStationService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.IncidentStatusChangeService;
import com.dscomm.iecs.accept.utils.GenerateUtil;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.ext.handle.HANDLE_TYPE_IVR;
import com.dscomm.iecs.ext.instructions.STATUS_SIGNED;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DigestUtils;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;

import static com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil.transform;

/**
 * 描述：处警记录（调派记录）服务类实现
 */
@Component("handleMiniatureStationServiceImpl")
public class HandleMiniatureStationServiceImpl implements HandleMiniatureStationService {
    private static final Logger logger = LoggerFactory.getLogger(HandleMiniatureStationServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private UserService userService;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private ServletService servletService;
    private HandleMiniatureStationRepository  handleMiniatureStationRepository ;
    private IncidentService incidentService;
    private HandleRepository handleRepository;
    private IVRPlayRepository ivrPlayRepository ;
    private NotifyActionService notifyActionService;


    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    @Lazy(true)
    public HandleMiniatureStationServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                             Environment env, UserService userService , DictionaryService dictionaryService  , OrganizationService organizationService ,
                                             ServletService servletService , HandleMiniatureStationRepository handleMiniatureStationRepository ,
                                             DahuaService dahuaService , IncidentService incidentService , HandleRepository handleRepository ,
                                             IncidentStatusChangeService incidentStatusChangeService , IVRPlayRepository ivrPlayRepository ,
                                             NotifyActionService notifyActionService

    ) {

        this.accessor = accessor;
        this.logService = logService;
        this.env = env;
        this.userService = userService ;
        this.dictionaryService = dictionaryService ;
        this.organizationService = organizationService ;
        this.servletService = servletService ;
        this.handleMiniatureStationRepository = handleMiniatureStationRepository ;
        this.incidentService = incidentService ;
        this.handleRepository = handleRepository ;
        this.ivrPlayRepository = ivrPlayRepository ;
        this.notifyActionService = notifyActionService ;

        dics = new ArrayList<>(Arrays.asList(  "ZLZT", "ZLDP","XZQX", "DPFS","DPDXLX" ));

    }



    /**
     * {@inheritDoc}
     *
     * @see  #saveHandleMiniatureStation(HandleSaveInputInfo, UserInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HandleBean saveHandleMiniatureStation(HandleSaveInputInfo queryBean, UserInfo userInfo) {
        if (null == queryBean || Strings.isBlank(queryBean.getIncidentId())) {
            logService.infoLog(logger, "service", "saveHandleMiniatureStation", "HandleSaveInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveHandleMiniatureStation", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeHandleMiniatureStationSaveInputInfo(queryBean); //URLDecoder inputInfo转码

            String incidentId = queryBean.getIncidentId();
            IncidentEntity incidentEntity = accessor.getById(incidentId, IncidentEntity.class);
            if (null == incidentEntity) {
                logService.infoLog(logger, "service", "saveHandleMiniatureStation", "incidentEntity is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            if (null == userInfo) {
                userInfo = userService.getUserInfo();
            }

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            //处警记录保存
            //生成处警编号
            if (Strings.isBlank(queryBean.getHandleNumber())) {
                Date nowDate = new Date(servletService.getSystemTime());
                String agentNum = userInfo.getAgentNum();
                OrganizationBean handleOrg = null ;
                if( Strings.isNotBlank( queryBean.getHandleOrganizationId() ) ){
                    handleOrg = organizationService.findOrganizationByOrganizationId( queryBean.getHandleOrganizationId() ) ;
                }
                if( handleOrg == null ){
                    handleOrg = organizationService.getRootOrg() ;
                }
                //消防无 机构编码  使用车辆所在行政区划
                String handleNumber = GenerateUtil.GenerateCJDBH(nowDate, agentNum, handleOrg.getDistrictCode() );
                queryBean.setHandleNumber(handleNumber);
            }
            Long currentTime = servletService.getSystemTime();
            if ( queryBean.getHandleStartTime() == null || queryBean.getHandleStartTime() == 0) {
                queryBean.setHandleStartTime( currentTime  );
            }
            if (queryBean.getHandleEndTime() == null || queryBean.getHandleEndTime() == 0) {
                queryBean.setHandleEndTime(currentTime);
            }

            HandleEntity handleEntity = HandleDispatchTransformUtil.transform(queryBean, incidentId);
            handleEntity.setHandleOrganizationName(organizationNameMap.get(handleEntity.getHandleOrganizationId()));

            //判断此处调度批次
            logService.infoLog(logger, "repository", "save(dbHandleEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            Integer handleBatchNum = handleRepository.findHandleBatchByIncidentId(queryBean.getIncidentId());

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            handleBatchNum = handleBatchNum + 1;

            handleEntity.setHandleBatch(String.valueOf(handleBatchNum));

            logService.infoLog(logger, "repository", "save(dbHandleEntity)", "repository is started...");
            Long startHandle = System.currentTimeMillis();

            accessor.save(handleEntity);

            Long endHandle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleEntity)", String.format("repository is finished,execute time is :%sms", endHandle - startHandle));

            //获得处警记录id
            String handleId = handleEntity.getId();
            handleEntity.setIdCode(handleId);

            HandleBean res = HandleDispatchTransformUtil.transform(handleEntity, dicsMap, organizationNameMap);

            //调派微站信息
            List<HandleMiniatureStationSaveInputInfo>  handleMiniatureStationSaveInputInfo  = queryBean.getHandleMiniatureStationSaveInputInfo() ;

            //保存调派 微站信息
            Map<String, List<HandleMiniatureStationBean>> handleMiniatureStationBeanMap = saveHandleMiniatureStation(
                    handleMiniatureStationSaveInputInfo ,   handleEntity,   userInfo, dicsMap,   organizationNameMap) ;

            //判断是否为 ivr 调派  如果为ivr 调派 新增 ivr 播放记录表
            saveIVRPlayRecord( handleMiniatureStationBeanMap ) ;

            //进行业务逻辑 处理
            List<HandleMiniatureStationBean>  handleMiniatureStationBeans = new ArrayList<>( ) ;

            List<HandleMiniatureStationBean> tempmi = new ArrayList<>();
            for( List<HandleMiniatureStationBean> temp1: handleMiniatureStationBeanMap.values() ){
                if( temp1 != null && temp1.size() > 0 ){
                    tempmi.addAll(temp1 ) ;
                }
            }
            handleMiniatureStationBeans.addAll(tempmi ) ;
            res.setHandleMiniatureStationBean( handleMiniatureStationBeans );

            // 调派微站 发送消息
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(incidentId);
            orgSet.addAll(orgIds);

            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.HANDLE_MINIATURE_STATION.getCode(), res  , orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveHandleMiniatureStation", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveHandleMiniatureStation", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_HANDLE_FAIL);
        }
    }


    private  void   saveIVRPlayRecord(  Map<String, List<HandleMiniatureStationBean>> handleMiniatureStationBeanMap  ){

        logService.infoLog(logger, "service", "findHandleMiniatureStation", "service is started...");
        Long logStart = System.currentTimeMillis();

        if( handleMiniatureStationBeanMap != null && handleMiniatureStationBeanMap.size() > 0 ){
            List<IVRPlayRecordEntity> ivrPlayRecordEntityList = new ArrayList<>() ;
            for( List<HandleMiniatureStationBean> handleMiniatureStationBeans : handleMiniatureStationBeanMap.values() ){
                if (handleMiniatureStationBeans != null && handleMiniatureStationBeans.size() > 0) {
                    for (HandleMiniatureStationBean  handleMiniatureStationBean : handleMiniatureStationBeans) {
                        if (handleMiniatureStationBean != null && HANDLE_TYPE_IVR.getCode().equals(handleMiniatureStationBean.getHandleWay())) {
                            IVRPlayRecordEntity ivrPlayRecordEntity = new IVRPlayRecordEntity();
                            String uuid = DigestUtils.uuid().replaceAll("-", "");
                            ivrPlayRecordEntity.setId(uuid);
                            ivrPlayRecordEntity.setHandleMiniatureStationId(handleMiniatureStationBean.getId());
                            ivrPlayRecordEntity.setRelayRecordNumber(handleMiniatureStationBean.getRelayRecordNumber());
                            ivrPlayRecordEntity.setSmsContent(handleMiniatureStationBean.getHandleContent());
                            ivrPlayRecordEntity.setVisitTime( new Date( servletService.getSystemTime() ) );
                            ivrPlayRecordEntity.setRecordStatus(EnableEnum.ENABLE_FALSE.getCode());

                            ivrPlayRecordEntityList.add( ivrPlayRecordEntity ) ;
                        }
                    }
                }
            }
            if( ivrPlayRecordEntityList != null && ivrPlayRecordEntityList.size() > 0 ){
                ivrPlayRepository.saveAll( ivrPlayRecordEntityList ) ;
            }
        }
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "saveIVRPlayRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

    }


    /**
     * 保存 微型消防站
     */
    private Map<String, List<HandleMiniatureStationBean>> saveHandleMiniatureStation(
            List<HandleMiniatureStationSaveInputInfo>  queryBean, HandleEntity handleEntity, UserInfo userInfo,
            Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {

        Map<String, List<HandleMiniatureStationBean>> handleMiniatureStationBeanMap = new HashMap<>();

        String incidentId = handleEntity.getIncidentId(); //警情id
        String handleId = handleEntity.getId();  //调派单id

        if (null != queryBean && queryBean.size() > 0) {

            List<HandleMiniatureStationEntity>  handleMiniatureStationEntityList = new ArrayList<>() ;

            for (HandleMiniatureStationSaveInputInfo handleMiniatureStation : queryBean ) {

                HandleMiniatureStationEntity handleMiniatureStationEntity = HandleDispatchTransformUtil.transform(handleMiniatureStation, incidentId, handleId);

                handleMiniatureStationEntity.setHandleTime(servletService.getSystemTime());
                handleMiniatureStationEntity.setHandleOrganizationId(userInfo.getOrgId());
                handleMiniatureStationEntity.setHandlePersonNumber(userInfo.getAccount());
                handleMiniatureStationEntity.setHandleSeatNumber(userInfo.getAgentNum());

                handleMiniatureStationEntityList.add( handleMiniatureStationEntity ) ;

            }

            logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationEntity)", "repository is started...");
            Long startHandleOrganization = System.currentTimeMillis();

            accessor.save(handleMiniatureStationEntityList);

            Long endHandleOrganization = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationEntity)", String.format("repository is finished,execute time is :%sms", endHandleOrganization - startHandleOrganization));

            for( HandleMiniatureStationEntity handleMiniatureStationEntity : handleMiniatureStationEntityList ){
                HandleMiniatureStationBean handleMiniatureStationBean  = HandleDispatchTransformUtil.transform( handleMiniatureStationEntity, dicsMap, organizationNameMap);

                handleMiniatureStationBean.setHandleBatch(handleEntity.getHandleBatch());

                List<HandleMiniatureStationBean> handleMiniatureStationBeanList =
                        handleMiniatureStationBeanMap.get(handleMiniatureStationBean.getOrganizationId());
                if (handleMiniatureStationBeanList == null) {
                    handleMiniatureStationBeanList = new ArrayList<>();
                }
                handleMiniatureStationBeanList.add(handleMiniatureStationBean);
                handleMiniatureStationBeanMap.put(handleMiniatureStationBean.getOrganizationId(), handleMiniatureStationBeanList);

            }
        }

        return handleMiniatureStationBeanMap ;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Transactional( readOnly =  true )
    @Override
    public  List<HandleMiniatureStationBean>   findHandleMiniatureStation( String incidentId , String handleId  )  {
        if (Strings.isBlank( incidentId)) {
            logService.infoLog(logger, "service", "findHandleMiniatureStation", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleMiniatureStation", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HandleMiniatureStationBean> res = new ArrayList<>( ) ;

            logService.infoLog(logger, "repository", "findHandleMiniatureStation", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> entityList = null;
            if (Strings.isBlank( handleId ) ) {
                entityList = handleMiniatureStationRepository.findHandleMiniatureStation(incidentId);
            } else {
                entityList = handleMiniatureStationRepository.findHandleMiniatureStation(incidentId, handleId );
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleMiniatureStation", String.format("repository is finished,execute time is :%sms", end - start));

            if (entityList != null && entityList.size() > 0) {
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                // 查询机构id-名称缓存map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                for (Object[] entity : entityList) {
                    HandleMiniatureStationEntity handleMiniatureStationEntity = (HandleMiniatureStationEntity) entity[0];
                    HandleEntity handleEntity = (HandleEntity) entity[1];
                    HandleMiniatureStationBean handleMiniatureStationBean = transform(handleMiniatureStationEntity, dicsMap, organizationNameMap);
                    handleMiniatureStationBean.setHandleBatch(handleEntity.getHandleBatch());
                    res.add( handleMiniatureStationBean );
                }
            }



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleMiniatureStation", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleMiniatureStation", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     */
    @Transactional( rollbackFor =  Exception.class  )
    @Override
    public  HandleMiniatureStationFeedbackBean saveHandleMiniatureStationFeedback(HandleMiniatureStationFeedbackSaveInputInfo inputInfo ) {
        if ( inputInfo == null || Strings.isBlank( inputInfo.getHandleMiniatureStationId() )) {
            logService.infoLog(logger, "service", "saveHandleMiniatureStationFeedback", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveHandleMiniatureStationFeedback", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeHandleMiniatureStationFeedbackSaveInputInfo(inputInfo); //URLDecoder inputInfo转码

            HandleMiniatureStationFeedbackBean res  = null ;

            HandleMiniatureStationFeedbackEntity handleMiniatureStationFeedbackEntity = HandleDispatchTransformUtil.transform( inputInfo ) ;
            if( handleMiniatureStationFeedbackEntity.getFeedbackTime() == null  ){
                handleMiniatureStationFeedbackEntity.setFeedbackTime( servletService.getSystemTime()  );
            }

            logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationFeedbackEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save( handleMiniatureStationFeedbackEntity ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationFeedbackEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            //更新调派微站记录信息 状态
            logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationFeedbackEntity)", "repository is started...");
            Long start1 = System.currentTimeMillis();

            HandleMiniatureStationEntity  handleMiniatureStationEntity  =  accessor.getById(  inputInfo.getHandleMiniatureStationId() , HandleMiniatureStationEntity.class ) ;

            if( handleMiniatureStationEntity != null ){
                handleMiniatureStationEntity.setHandleStatus( STATUS_SIGNED.getCode() );
                if( handleMiniatureStationEntity.getLatestFeedbackTime() == null || handleMiniatureStationEntity.getLatestFeedbackTime() == 0 ||
                   handleMiniatureStationEntity.getLatestFeedbackTime() < handleMiniatureStationFeedbackEntity.getFeedbackTime()  ){
                    handleMiniatureStationEntity.setLatestFeedbackTime( handleMiniatureStationFeedbackEntity.getFeedbackTime()   );
                }
                accessor.save( handleMiniatureStationEntity ) ;
            }

            Long end1 = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationFeedbackEntity)", String.format("repository is finished,execute time is :%sms", end1 - start1 ));

            if (handleMiniatureStationFeedbackEntity != null  ) {
                res= HandleDispatchTransformUtil.transform( handleMiniatureStationFeedbackEntity ) ;
            }

            // 调派微站 发送消息
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( inputInfo.getIncidentId() );
            orgSet.addAll(orgIds);

            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.HANDLE_MINIATURE_STATION_FEEDBACK.getCode(), res  , orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveHandleMiniatureStationFeedback", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveHandleMiniatureStationFeedback", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Transactional( readOnly =  true )
    @Override
    public  List<HandleMiniatureStationFeedbackBean> findHandleMiniatureStationFeedback( String incidentId , String handleMiniatureStationId ) {
        if (Strings.isBlank( incidentId) || Strings.isBlank( handleMiniatureStationId )) {
            logService.infoLog(logger, "service", "findHandleMiniatureStationFeedback", "incidentId or handleMiniatureStationId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleMiniatureStationFeedback", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HandleMiniatureStationFeedbackBean> res = new ArrayList<>( ) ;

            logService.infoLog(logger, "repository", "findHandleMiniatureStationFeedback", "repository is started...");
            Long start = System.currentTimeMillis();

            List<HandleMiniatureStationFeedbackEntity> entityList = handleMiniatureStationRepository.findHandleMiniatureStationFeedback( incidentId , handleMiniatureStationId ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleMiniatureStation", String.format("repository is finished,execute time is :%sms", end - start));

            if (entityList != null && entityList.size() > 0) {
                for ( HandleMiniatureStationFeedbackEntity handleMiniatureStationFeedbackEntity  : entityList) {
                    HandleMiniatureStationFeedbackBean bean = HandleDispatchTransformUtil.transform( handleMiniatureStationFeedbackEntity ) ;
                    res.add( bean );
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleMiniatureStationFeedback", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleMiniatureStationFeedback", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }


    /**
     * HandleSaveInputInfo 解码
     */
    private void decodeHandleMiniatureStationSaveInputInfo(HandleSaveInputInfo source) {
        if (source != null) {
            try {
                List<HandleMiniatureStationSaveInputInfo> handleMiniatureStationSaveInputInfo =
                        source.getHandleMiniatureStationSaveInputInfo() ;
                for(HandleMiniatureStationSaveInputInfo  handleMiniatureStation : handleMiniatureStationSaveInputInfo ){
                    if( handleMiniatureStation != null ){
                        if (!StringUtils.isBlank(handleMiniatureStation.getHandleContent() )) {
                            source.setHandleContent(URLDecoder.decode( handleMiniatureStation.getHandleContent() , "UTF-8"));
                        }
                    }
                }
            } catch (Exception e) {
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }

    /**
     * HandleSaveInputInfo 解码
     */
    private void decodeHandleMiniatureStationFeedbackSaveInputInfo(HandleMiniatureStationFeedbackSaveInputInfo source) {
        if (source != null) {
            try {

                if (!StringUtils.isBlank(source.getLocaleDesc() )) {
                    source.setLocaleDesc(URLDecoder.decode( source.getLocaleDesc() , "UTF-8"));
                }

            } catch (Exception e) {
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }

}
