package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.repository.HandleOrganizationRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleWebSocketPushBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.service.HandleService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.OverTimeTaskService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.ext.instructions.STATUS_GIVEN;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：处警记录（调派记录）服务类实现
 */
@Component("overTimeTaskServiceImpl")
public class OverTimeTaskServiceImpl implements OverTimeTaskService {
    private static final Logger logger = LoggerFactory.getLogger(OverTimeTaskServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private ServletService servletService ;
    private UserService userService ;
    private NotifyActionService notifyActionService ;
    private SystemConfigurationService systemConfigurationService ;
    private HandleOrganizationRepository handleOrganizationRepository ;
    private HandleService handleService ;
    private OrganizationService  organizationService ;
    private IncidentService incidentService ;


    /**
     * 默认的构造函数
     */
    @Autowired
    public OverTimeTaskServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env ,
                                   ServletService servletService , UserService userService  ,
                                   NotifyActionService notifyActionService  ,
                                   SystemConfigurationService systemConfigurationService ,
                                   HandleOrganizationRepository handleOrganizationRepository , HandleService handleService ,
                                   OrganizationService  organizationService ,  IncidentService incidentService


    ) {

        this.accessor = accessor;
        this.logService = logService;
        this.env = env;
        this.servletService = servletService ;
        this.userService = userService ;
        this.notifyActionService = notifyActionService;
        this.systemConfigurationService = systemConfigurationService ;
        this.handleOrganizationRepository  =  handleOrganizationRepository  ;
        this.handleService = handleService ;
        this.organizationService = organizationService ;
        this.incidentService = incidentService ;


    }

    /**
     * {@inheritDoc}
     *
     * @see  #handleOrganizationOverTimeTask( )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  void  handleNoticeOrganizationOverTimeTask()  {

        try {
            logService.infoLog(logger, "service", "handleNoticeOrganizationOverTimeTask", "service is started...");
            Long logStart = System.currentTimeMillis();


            Long currentTime = servletService.getSystemTime() ;

            Long  overTime = 15 * 1000l ; //默认 15s

            List<String> handleIds = findNoticeOverTimeHandleIds( currentTime , overTime );

            //根据handleIds 遍历通知 任务下发坐席 账号
            if( handleIds != null && handleIds.size() > 0 ){
                for( String handleId :  handleIds){

                    HandleWebSocketPushBean handleWebSocketPushBean = new HandleWebSocketPushBean();

                    HandleBean handleBean  =  handleService.findHandleByHandleId(  handleId    ) ;
                    handleBean.setDispatchOrganization( null );
                    handleBean.setDispatchVehicle( null );
                    //针对 出动单位去掉 出动单位下出动车辆信息
                    for(HandleOrganizationBean handleOrganizationBean :  handleBean.getHandleOrganizationBean() ){
                        handleOrganizationBean.setHandleOrganizationEquipmentBean( null );
                        handleOrganizationBean.setHandleOrganizationVehicleBean( null );
                    }
                    IncidentBean  incidentBean = incidentService.findIncident( handleBean.getIncidentId() , false   ) ;

                    //webSocket需要通知的机构id列表
//                    if( incidentBean.getWhetherTestSign() != 1 ){
                    handleWebSocketPushBean.setIncidentBean(incidentBean);
                    handleWebSocketPushBean.setHandleBean(handleBean );

                    List<ReceiverMessageBean> receivers = new ArrayList<>() ;
                    ReceiverMessageBean receiverMessageBean = new ReceiverMessageBean();
                    receiverMessageBean.setId( handleBean.getHandlePersonNumber() );
                    receiverMessageBean.setType("user");
                    receivers.add(receiverMessageBean ) ;

                    notifyActionService.pushMessage(WebsocketCodeEnum.NOTICE_OVER_TIME_HANDLE_TIME.getCode(), handleWebSocketPushBean , receivers );
//                    }

                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "handleOrganizationOverTimeTask", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "handleOrganizationOverTimeTask", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }
    }



    @Transactional(rollbackFor = Exception.class )
    @Override
    public List<String>  findNoticeOverTimeHandleIds (  Long currentTime  , Long overTime ){
        try {
            logService.infoLog(logger, "service", "findNoticeOverTimeHandleIds", "service is started...");
            Long logStart = System.currentTimeMillis();


            //获得处警单位未签收的处警单位信息
            logService.infoLog(logger, "repository", "findNoticeOverTimeHandleIds", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<String> res = handleOrganizationRepository.findNoticeOverTimeHandleIds(    currentTime ,overTime  ) ;

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findNoticeOverTimeHandleIds", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findNoticeOverTimeHandleIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findNoticeOverTimeHandleIds", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see  #handleOrganizationOverTimeTask( )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  void  handleOrganizationOverTimeTask()  {

        try {
            logService.infoLog(logger, "service", "handleOrganizationOverTimeTask", "service is started...");
            Long logStart = System.currentTimeMillis();


            Long currentTime = servletService.getSystemTime() ;

            SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("signHandleOrganizationOvertime") ;
            Long  overTime = 15 * 1000l ; //默认 15s
            if( systemConfigurationBean != null ){
                overTime  =  Long.parseLong( systemConfigurationBean.getConfigValue() ) * 1000 ;
            }

            List<String> handleIds = findOverTimeHandleIds( currentTime , overTime );

            //根据handleIds 遍历通知 任务下发坐席 账号
            if( handleIds != null && handleIds.size() > 0 ){
                for( String handleId :  handleIds){

                    HandleWebSocketPushBean handleWebSocketPushBean = new HandleWebSocketPushBean();

                    HandleBean handleBean  =  handleService.findHandleByHandleId(  handleId    ) ;
                    handleBean.setDispatchOrganization( null );
                    handleBean.setDispatchVehicle( null );
                    //针对 出动单位去掉 出动单位下出动车辆信息
                    for(HandleOrganizationBean handleOrganizationBean :  handleBean.getHandleOrganizationBean() ){
                        handleOrganizationBean.setHandleOrganizationEquipmentBean( null );
                        handleOrganizationBean.setHandleOrganizationVehicleBean( null );
                    }
                    IncidentBean  incidentBean = incidentService.findIncident( handleBean.getIncidentId() , false   ) ;

                    //webSocket需要通知的机构id列表
//                    if( incidentBean.getWhetherTestSign() != 1 ){
                        handleWebSocketPushBean.setIncidentBean(incidentBean);
                        handleWebSocketPushBean.setHandleBean(handleBean );

                        List<ReceiverMessageBean> receivers = new ArrayList<>() ;
                        ReceiverMessageBean receiverMessageBean = new ReceiverMessageBean();
                        receiverMessageBean.setId( handleBean.getHandlePersonNumber() );
                        receiverMessageBean.setType("user");
                        receivers.add(receiverMessageBean ) ;

                        notifyActionService.pushMessage(WebsocketCodeEnum.OVER_TIME_HANDLE_TIME.getCode(), handleWebSocketPushBean , receivers );
//                    }

                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "handleOrganizationOverTimeTask", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "handleOrganizationOverTimeTask", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }
    }



    @Transactional(rollbackFor = Exception.class )
    @Override
    public List<String>  findOverTimeHandleIds (  Long currentTime  , Long overTime ){
        try {
            logService.infoLog(logger, "service", "findOverTimeHandleIds", "service is started...");
            Long logStart = System.currentTimeMillis();


            //获得处警单位未签收的处警单位信息
            logService.infoLog(logger, "repository", "findOverTimeHandleIds", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<String> res = handleOrganizationRepository.findOverTimeHandleIds(   currentTime ,overTime  ) ;

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOverTimeHandleIds", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));


            //修改处警信息 任务执行
            if( res != null && res.size() > 0 ){
                handleOrganizationRepository.updateOverHandleTimeByIds(  currentTime , res  );
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOverTimeHandleIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOverTimeHandleIds", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }
    }








}
