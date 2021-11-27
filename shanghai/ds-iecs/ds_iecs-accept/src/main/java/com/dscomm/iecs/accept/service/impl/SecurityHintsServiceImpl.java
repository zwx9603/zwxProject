package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.SecurityHintsEntity;
import com.dscomm.iecs.accept.dal.po.SecurityHintsReceiverEntity;
import com.dscomm.iecs.accept.dal.repository.SecurityRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.SecurityInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.SecurityReceiverInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.SecurityBean;
import com.dscomm.iecs.accept.graphql.typebean.SecurityReceiverBean;
import com.dscomm.iecs.accept.service.SecurityHintsService;
import com.dscomm.iecs.accept.utils.transform.SecurityTransformUtil;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//import com.dscomm.iecs.accept.dal.repository.DataExchangeRepository;

/**
 * @Author: xyy
 * @Date: 14:18 2020/8/3
 * desc:
 */
@Component
public class SecurityHintsServiceImpl implements SecurityHintsService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityHintsServiceImpl.class);
    private GeneralAccessor accessor;
    private Environment environment;
    private LogService logService;
    private SecurityRepository securityRepository;
    private NotifyActionService notifyActionService ;
    private ServletService servletService ;

    @Autowired
    public SecurityHintsServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService, SecurityRepository securityRepository
                                    , NotifyActionService notifyActionService , ServletService servletService
                                , Environment environment) {
        this.accessor = accessor;
        this.logService = logService;
        this.environment = environment;
        this.securityRepository = securityRepository;
        this.notifyActionService = notifyActionService;
        this.servletService = servletService ;
    }

    /**
     *下达特别警示
     * @param inputInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveSecurityHints(SecurityInputInfo inputInfo) {
        if ( inputInfo == null || StringUtils.isBlank(inputInfo.getIncidentId()) || StringUtils.isBlank( inputInfo.getWriterId() )
                || StringUtils.isBlank(inputInfo.getCommandId())) {
            logService.infoLog(logger, "service", "saveSecurityHints", "inputInfo is empty.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "saveSecurityHints", "service is started...");
            Long logStart = System.currentTimeMillis();

            Long currentTime = servletService.getSystemTime() ;

            SecurityHintsEntity securityHintsEntity = SecurityTransformUtil.transform(inputInfo);
            securityHintsEntity.setNotificationTime( currentTime );

            if( securityHintsEntity != null ){

                logService.infoLog(logger, "repository", "save(dbSecurityHintsEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(securityHintsEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbSecurityHintsEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                SecurityBean res =  SecurityTransformUtil.transform( securityHintsEntity ) ;

                List<SecurityReceiverInputInfo> securityReceiverInputInfoList = inputInfo.getReceivers();
                if ( securityReceiverInputInfoList != null && securityReceiverInputInfoList.size()>0){//有接受者的场合需要进行推送
                    Set<String> receiverIds = new HashSet<>();
                    List< SecurityHintsReceiverEntity> securityHintsReceiverEntityList = new ArrayList<>( ) ;
                    for (SecurityReceiverInputInfo securityReceiverInputInfo : securityReceiverInputInfoList ){
                        SecurityHintsReceiverEntity securityHintsReceiverEntity = SecurityTransformUtil.transform(securityReceiverInputInfo);
                        securityHintsReceiverEntity.setSecurityHintsId(securityHintsEntity.getId());
                        securityHintsReceiverEntity.setIncidentId( inputInfo.getIncidentId() );
                        securityHintsReceiverEntity.setCommandId( inputInfo.getCommandId() );
                        securityHintsReceiverEntity.setNotificationTime( currentTime );
                        securityHintsReceiverEntity.setSecurityHintsId( res.getId()  );
                        securityHintsReceiverEntityList.add( securityHintsReceiverEntity ) ;
                        receiverIds.add(securityReceiverInputInfo.getReceiverId() );
                    }

                    logService.infoLog(logger, "repository", "save(dbSecurityHintsEntity)", "repository is started...");
                    Long startList = System.currentTimeMillis();

                    accessor.save( securityHintsReceiverEntityList );

                    Long endList = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save(dbSecurityHintsEntity)", String.format("repository is finished,execute time is :%sms", endList - startList));

                    List<SecurityReceiverBean> securityReceiverBeanList = new ArrayList<>() ;
                    for (SecurityHintsReceiverEntity securityHintsReceiverEntity : securityHintsReceiverEntityList ){
                        SecurityReceiverBean securityReceiverBean  = SecurityTransformUtil.transform(securityHintsReceiverEntity);
                        securityReceiverBeanList.add( securityReceiverBean );
                    }

                    res.setReceivers( securityReceiverBeanList );

                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.GIVE_SECURITY_HINTS.getCode(), res , receiverIds );
                }

                //TODO  移动数据交换

                return  true ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveSecurityHints", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return false ;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveSecurityHints", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_SECURITY_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #signSecurityHints(  List  )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public   Boolean  signSecurityHints(  List<String> securityHintsReceiverIs  ) {
        if ( null == securityHintsReceiverIs || securityHintsReceiverIs.size() < 1) {
            logService.infoLog(logger, "service", "signSecurityHints", "securityHintsReceiverIs  is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "signSecurityHints", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findSecurityHintsReceiverByIds(securityHintsReceiverIs)", "repository is started...");
            Long startFind = System.currentTimeMillis();

            List<SecurityHintsReceiverEntity> entityList  = securityRepository.findSecurityHintsReceiverByIds(  securityHintsReceiverIs  );

            Long endFind = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSecurityHintsReceiverByIds(securityHintsReceiverIs)", String.format("repository is finished,execute time is :%sms", endFind - startFind ));

            if (entityList != null) {
                Long currentTime = servletService.getSystemTime() ;
                for (SecurityHintsReceiverEntity entity : entityList ) {
                    entity.setSignTime( currentTime );
                }

                logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(entityList);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", String.format("repository is finished,execute time is :%sms", end - start));

            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeEarlyWarningStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeEarlyWarningStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.CHANGE_EARLY_WARNING_STATUS_FAIL);
        }
    }


    /**
     * 特别警示历史记录
     * @param incidentId 特别警示id
     */
    @Transactional(readOnly = true)
    @Override
    public List<SecurityBean> findSecurityHintsList (String incidentId){
        if (StringUtils.isBlank(incidentId)){
            logService.infoLog(logger, "service", "findSecurityHintsList", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSecurityHintsList", "service is started...");
            Long logStart = System.currentTimeMillis();


            List<SecurityBean>  beans = new ArrayList<>() ;

            Map<String,SecurityBean > securityMap = new HashMap<>() ;
            Map<String,List<SecurityReceiverBean> > securityReceiverMap  = new HashMap<>() ;

            logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<SecurityHintsEntity> securityHintsEntityList =  securityRepository.findSecurityHints( incidentId ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", String.format("repository is finished,execute time is :%sms", end - start));

            if( securityHintsEntityList != null && securityHintsEntityList.size() > 0 ){

                List<String>  securityHintsIds = new ArrayList<>( ) ;
                for( SecurityHintsEntity securityHintsEntity :  securityHintsEntityList){
                    SecurityBean securityBean  =  SecurityTransformUtil.transform( securityHintsEntity ) ;
                    securityHintsIds.add( securityBean.getId() ) ;
                    securityMap.put( securityBean.getId() , securityBean ) ;
                }


                List<SecurityHintsReceiverEntity>  securityHintsReceiverEntityList =   securityRepository.findSecurityHintsReceiver( incidentId ,securityHintsIds  );
                for( SecurityHintsReceiverEntity securityHintsReceiverEntity : securityHintsReceiverEntityList){
                    SecurityReceiverBean securityReceiverBean  = SecurityTransformUtil.transform(securityHintsReceiverEntity);
                    List<SecurityReceiverBean>  securityReceiverBeanList  = securityReceiverMap.get( securityReceiverBean.getSecurityHintsId()  ) ;
                    if( securityReceiverBeanList == null ){
                        securityReceiverBeanList = new ArrayList<>( ) ;
                        securityReceiverBeanList.add( securityReceiverBean ) ;
                    }else{
                        securityReceiverBeanList.add( securityReceiverBean ) ;
                    }
                    securityReceiverMap.put(securityReceiverBean.getSecurityHintsId()  , securityReceiverBeanList ) ;
                }

                for( String key :  securityMap.keySet() ){
                    SecurityBean securityBean = securityMap.get( key ) ;
                    securityBean.setReceivers( securityReceiverMap.get( securityBean.getId()  ) );
                    beans.add( securityBean ) ;
                }
            }

            //指令下达
            beans.sort( new Comparator<SecurityBean>() {
                @Override
                public int compare(SecurityBean o1, SecurityBean o2) {
                    Long d1 = o1.getNotificationTime();
                    Long d2 = o2.getNotificationTime();
                    return  d2.compareTo(d1);
                }
            } );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSecurityHintsList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return beans  ;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findSecurityHintsList", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_SECURITY_LIST_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #findSecurityReceiverList(String, String )
     */
    @Transactional(  readOnly =  true )
    @Override
    public  List<SecurityReceiverBean> findSecurityReceiverList (String incidentId , String receiverId ) {
        if ( Strings.isBlank(incidentId)  || Strings.isBlank( receiverId  )  ){
            logService.infoLog(logger, "service", "findSecurityReceiverList", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "findSecurityReceiverList", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<SecurityReceiverBean>  beans = new ArrayList<>() ;


            Map<String , SecurityBean>  securityBeanMap = new HashMap<>() ;
            List<String> securityHintsIds = new ArrayList<>() ;

            logService.infoLog(logger, "repository", "findSecurityHintsReceiver", "repository is started...");
            Long startInstructionRecord = System.currentTimeMillis();

            List<SecurityHintsReceiverEntity> securityHintsReceiverEntityList  =  securityRepository.findSecurityHintsReceiver( incidentId , receiverId ) ;

            Long endInstructionRecord = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSecurityHintsReceiver", String.format("repository is finished,execute time is :%sms", endInstructionRecord - startInstructionRecord ));



            if( securityHintsReceiverEntityList != null && securityHintsReceiverEntityList.size() > 0 ){
                for( SecurityHintsReceiverEntity securityHintsReceiverEntity  :  securityHintsReceiverEntityList ){
                    SecurityReceiverBean securityReceiverBean  = SecurityTransformUtil.transform(securityHintsReceiverEntity);
                    securityHintsIds.add( securityReceiverBean.getSecurityHintsId()  ) ;
                    beans.add( securityReceiverBean ) ;
                }
                logService.infoLog(logger, "repository", "findSecurityHints", "repository is started...");
                Long startInstruction = System.currentTimeMillis();

                List<SecurityHintsEntity> securityHintsEntityList  = securityRepository.findSecurityHints( securityHintsIds ) ;

                Long endInstruction = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findSecurityHints", String.format("repository is finished,execute time is :%sms", endInstruction - startInstruction ));



                for( SecurityHintsEntity securityHintsEntity :  securityHintsEntityList ){
                    SecurityBean securityBean  =  SecurityTransformUtil.transform( securityHintsEntity ) ;
                    securityBeanMap.put(securityBean.getId()  , securityBean ) ;
                }

                for( SecurityReceiverBean key :  beans ){
                    SecurityBean securityBean = securityBeanMap.get( key.getSecurityHintsId() ) ;
                    key.setSecurityBean(  securityBean );
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSecurityReceiverList", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return beans ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSecurityReceiverList", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }



}
