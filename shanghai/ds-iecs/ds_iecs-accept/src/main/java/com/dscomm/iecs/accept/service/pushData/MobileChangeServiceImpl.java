package com.dscomm.iecs.accept.service.pushData;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.accept.enums.MobileMessageEnum;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.HandleService;
import com.dscomm.iecs.accept.service.bean.MobileMessageBean;
import com.dscomm.iecs.accept.service.bean.MoblieNotifyMessageBean;
import com.dscomm.iecs.accept.service.impl.LocaleServiceImpl;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.RestfulClient;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.keydata.service.timeline.bo.Result;
import com.dscomm.iecs.keydata.service.timeline.bo.ResultStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component("mobileChangeServiceImpl")
public class MobileChangeServiceImpl implements MobileChangeService {


    private static final Logger logger = LoggerFactory.getLogger(LocaleServiceImpl.class);
    private LogService logService;
    private Environment env;
    private VehicleService vehicleService ;
    private HandleService handleService ;
    private UserService userService ;


    @Value("${mobileUrl.enable:false}")
    private Boolean  mobileEnable  ;
    @Value("${mobileUrl:http://192.168.93.169:8080}")
    private String mobileUrl;


    @Autowired
    public MobileChangeServiceImpl( LogService logService , Environment env ,
                                    VehicleService vehicleService  ,
                                    HandleService handleService ,
                                    UserService userService        ) {
        this.logService = logService ;
        this.env = env;
        this.vehicleService = vehicleService ;
        this.handleService = handleService ;
        this.userService = userService ;
        mobileEnable  =  Boolean.parseBoolean(env.getProperty("mobileUrl.enable" , "false")); // 是否推送以移动
        mobileUrl = env.getProperty("mobileUrl","http://192.168.93.169:8080") ; //移动移动推送地址
    }


    @Transactional
    @Override
    public void dispatchVehicle(IncidentBean incidentBean, HandleBean handleBean )  {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");


        if(  !mobileEnable  ){
            logService.infoLog(logger, "service", "pushMobileMessage", "push mobile message enablebl is "  + mobileEnable );
            return  ;
        }
        String code = MobileMessageEnum.NEW_INCIDENT.getCode()  ;

        //构建调派车辆 调派装备信息
        List<HandleOrganizationBean> handleOrganizationBeans = handleBean.getHandleOrganizationBean() ;
        List<String> vehicleIdList = new ArrayList<>() ;
        if( handleOrganizationBeans != null && handleOrganizationBeans.size() > 0 ){
            for( HandleOrganizationBean handleOrganizationBean :  handleOrganizationBeans){
                List<HandleOrganizationVehicleBean>  handleOrganizationVehicleBean = handleOrganizationBean.getHandleOrganizationVehicleBean() ;
                if( handleOrganizationVehicleBean != null && handleOrganizationVehicleBean.size() > 0 ){
                    for( HandleOrganizationVehicleBean handleOrganizationVehicle : handleOrganizationVehicleBean ){
                        vehicleIdList.add ( handleOrganizationVehicle.getVehicleId()  ) ;
                    }
                }
            }
        }
        if ( vehicleIdList != null && vehicleIdList.size() > 0 ) {
//            //推送iecs产品推送移动端 TODO  项目具体对象个性需求
            MobileMessageBean mobileMessageBean = new MobileMessageBean();
            mobileMessageBean.setOrgId( handleBean.getHandleOrganizationId() );
            mobileMessageBean.setOrgCode( handleBean.getHandleOrganizationId() );
            mobileMessageBean.setIncidentId( handleBean.getIncidentId() );
            mobileMessageBean.setInstructionId( handleBean.getId() );
            mobileMessageBean.setContent(incidentBean.getCrimeAddress() + "发生警情!");

            //新规则 针对车辆id 转 车牌号
            //mobileMessageBean.setVehicleIds( vehicleIds );
            //mobileMessageBean.setVehicles( StringUtils.join(vehicleIds.toArray(), ",")  );
            List<String> vehicleNumbers = vehicleService.findVehicleNumberCacheByIds( vehicleIdList );
            mobileMessageBean.setVehicleIds(vehicleNumbers);
            mobileMessageBean.setVehicles(StringUtils.join(vehicleNumbers.toArray(), ","));

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        logService.infoLog(logger, "service", "pushMobileMessage", "service is started...");
                        Long logStart = System.currentTimeMillis();

                        MoblieNotifyMessageBean moblieNotifyMessageBean = new MoblieNotifyMessageBean();

                        moblieNotifyMessageBean.setCode( code );
                        moblieNotifyMessageBean.setContent( JSON.toJSONString( mobileMessageBean ) );

                        logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl is  : "+ mobileUrl  );
                        logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl params is : " + JSON.toJSONString( moblieNotifyMessageBean )   );

                        ResponseEntity<Result> rtvo1 = RestfulClient.getClient().postForEntity( mobileUrl , moblieNotifyMessageBean , Result.class);
                        if (rtvo1.getBody().ret.equalsIgnoreCase(ResultStatus.OK)) {
                            logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is mobileUrl is successful  :%s ", "" ) ) ;
                        }

                        Long logEnd = System.currentTimeMillis();
                        logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is finished,execute time is :%sms", logEnd - logStart));

                    }catch ( Exception ex ) {
                        logService.erorLog(logger, "service", "pushMobileMessage", "execution error", ex);
                    }
                }
            });
            thread.start();

        }
        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }


    @Transactional
    @Override
    public void instruction (String incidentId , List<String> receivingObjectIds ,
                             InstructionBean instructionBean )    {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");


        //启用 mobile  发送消息
        if(  !mobileEnable  ){
            logService.infoLog(logger, "service", "pushMobileMessage", "push mobile message enablebl is "  + mobileEnable );
            return  ;
        }
        UserInfo userInfo = userService.getUserInfo() ;

        String code = MobileMessageEnum.NEW_INSTRUCTION.getCode()  ;

        if ( receivingObjectIds != null && receivingObjectIds.size() > 0 ) {
//            //推送iecs产品推送移动端 TODO  项目具体对象个性需求
            MobileMessageBean mobileMessageBean = new MobileMessageBean();
            mobileMessageBean.setOrgId( userInfo.getOrgId());
            mobileMessageBean.setOrgCode( userInfo.getOrgCode() );
            mobileMessageBean.setIncidentId(incidentId);
            mobileMessageBean.setInstructionId( instructionBean.getId() );
            mobileMessageBean.setInstructionsTypeCode( instructionBean.getInstructionsType() );
            mobileMessageBean.setContent( instructionBean.getInstructionsContent()  );

            //新规则 针对车辆id 转 车牌号
            //mobileMessageBean.setVehicleIds( vehicleIds );
            //mobileMessageBean.setVehicles( StringUtils.join(vehicleIds.toArray(), ",")  );
            List<String> vehicleNumbers = vehicleService.findVehicleNumberCacheByIds( receivingObjectIds );
            mobileMessageBean.setVehicleIds(vehicleNumbers);
            mobileMessageBean.setVehicles(StringUtils.join(vehicleNumbers.toArray(), ","));

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        logService.infoLog(logger, "service", "pushMobileMessage", "service is started...");
                        Long logStart = System.currentTimeMillis();

                        MoblieNotifyMessageBean moblieNotifyMessageBean = new MoblieNotifyMessageBean();

                        moblieNotifyMessageBean.setCode( code );
                        moblieNotifyMessageBean.setContent( JSON.toJSONString( mobileMessageBean ) );

                        logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl is  : "+ mobileUrl  );
                        logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl params is : " + JSON.toJSONString( moblieNotifyMessageBean )   );

                        ResponseEntity<Result> rtvo1 = RestfulClient.getClient().postForEntity( mobileUrl , moblieNotifyMessageBean , Result.class);
                        if (rtvo1.getBody().ret.equalsIgnoreCase(ResultStatus.OK)) {
                            logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is mobileUrl is successful  :%s ", "" ) ) ;
                        }

                        Long logEnd = System.currentTimeMillis();
                        logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is finished,execute time is :%sms", logEnd - logStart));

                    }catch ( Exception ex ) {
                        logService.erorLog(logger, "service", "pushMobileMessage", "execution error", ex);
                    }
                }
            });
            thread.start();

        }
        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }



    @Transactional
    @Override
    public  void modifyCase (IncidentBean incidentBean  )    {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");

        //启用 mobile  发送消息
        if(  !mobileEnable  ){
            logService.infoLog(logger, "service", "pushMobileMessage", "push mobile message enablebl is "  + mobileEnable );
            return  ;
        }
        UserInfo userInfo = userService.getUserInfo() ;

        String code = MobileMessageEnum.UPDATE_INCIDENT.getCode()  ;

        //通知案件参与车辆
        List<String> vehicleIds = handleService.findIncidentHandleOrganizationVehicleId(  incidentBean.getId()   ) ;
        if( vehicleIds != null && vehicleIds.size() > 0 ){
            //推送iecs产品推送移动端 TODO  项目具体对象个性需求
            MobileMessageBean mobileMessageBean = new MobileMessageBean();
            mobileMessageBean.setOrgId( userInfo.getOrgId());
            mobileMessageBean.setOrgCode( userInfo.getOrgCode() );
            mobileMessageBean.setIncidentId(  incidentBean.getId()  );
            mobileMessageBean.setContent(  incidentBean.getCrimeAddress() +  "警情信息更新"  );

            //新规则 针对车辆id 转 车牌号
            //mobileMessageBean.setVehicleIds( vehicleIds );
            //mobileMessageBean.setVehicles( StringUtils.join(vehicleIds.toArray(), ",")  );
            List<String> vehicleNumbers =  vehicleService.findVehicleNumberCacheByIds(vehicleIds) ;
            mobileMessageBean.setVehicleIds( vehicleNumbers );
            mobileMessageBean.setVehicles( StringUtils.join(vehicleNumbers.toArray(), ",")  );

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        logService.infoLog(logger, "service", "pushMobileMessage", "service is started...");
                        Long logStart = System.currentTimeMillis();

                        MoblieNotifyMessageBean moblieNotifyMessageBean = new MoblieNotifyMessageBean();

                        moblieNotifyMessageBean.setCode( code );
                        moblieNotifyMessageBean.setContent( JSON.toJSONString( mobileMessageBean ) );

                        logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl is  : "+ mobileUrl  );
                        logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl params is : " + JSON.toJSONString( moblieNotifyMessageBean )   );

                        ResponseEntity<Result> rtvo1 = RestfulClient.getClient().postForEntity( mobileUrl , moblieNotifyMessageBean , Result.class);
                        if (rtvo1.getBody().ret.equalsIgnoreCase(ResultStatus.OK)) {
                            logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is mobileUrl is successful  :%s ", "" ) ) ;
                        }

                        Long logEnd = System.currentTimeMillis();
                        logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is finished,execute time is :%sms", logEnd - logStart));

                    }catch ( Exception ex ) {
                        logService.erorLog(logger, "service", "pushMobileMessage", "execution error", ex);
                    }
                }
            });
            thread.start();

        }
        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }





    @Transactional
    @Override
    public void caseStat( IncidentBean incidentBean  ,
                          IncidentStatusChangeBean incidentStatusChangeBean   )   {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");


        //启用 mobile  发送消息
        if(  !mobileEnable  ){
            logService.infoLog(logger, "service", "pushMobileMessage", "push mobile message enablebl is "  + mobileEnable );
            return  ;
        }
        UserInfo userInfo = userService.getUserInfo() ;

        String code = MobileMessageEnum.NEW_INCIDENT_STATE.getCode()  ;

        //通知案件参与车辆
        List<String> vehicleIds = handleService.findIncidentHandleOrganizationVehicleId(  incidentBean.getId()   ) ;
        if( vehicleIds != null && vehicleIds.size() > 0 ){
            //推送iecs产品推送移动端 TODO  项目具体对象个性需求
            MobileMessageBean mobileMessageBean = new MobileMessageBean();
            mobileMessageBean.setOrgId( userInfo.getOrgId());
            mobileMessageBean.setOrgCode( userInfo.getOrgCode() );
            mobileMessageBean.setIncidentId(  incidentBean.getId()  );
            mobileMessageBean.setContent(  MobileMessageEnum.NEW_INCIDENT_STATE.getName()  );

            //新规则 针对车辆id 转 车牌号
            //mobileMessageBean.setVehicleIds( vehicleIds );
            //mobileMessageBean.setVehicles( StringUtils.join(vehicleIds.toArray(), ",")  );
            List<String> vehicleNumbers =  vehicleService.findVehicleNumberCacheByIds(vehicleIds) ;
            mobileMessageBean.setVehicleIds( vehicleNumbers );
            mobileMessageBean.setVehicles( StringUtils.join(vehicleNumbers.toArray(), ",")  );

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        logService.infoLog(logger, "service", "pushMobileMessage", "service is started...");
                        Long logStart = System.currentTimeMillis();

                        MoblieNotifyMessageBean moblieNotifyMessageBean = new MoblieNotifyMessageBean();

                        moblieNotifyMessageBean.setCode( code );
                        moblieNotifyMessageBean.setContent( JSON.toJSONString( mobileMessageBean ) );

                        logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl is  : "+ mobileUrl  );
                        logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl params is : " + JSON.toJSONString( moblieNotifyMessageBean )   );

                        ResponseEntity<Result> rtvo1 = RestfulClient.getClient().postForEntity( mobileUrl , moblieNotifyMessageBean , Result.class);
                        if (rtvo1.getBody().ret.equalsIgnoreCase(ResultStatus.OK)) {
                            logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is mobileUrl is successful  :%s ", "" ) ) ;
                        }

                        Long logEnd = System.currentTimeMillis();
                        logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is finished,execute time is :%sms", logEnd - logStart));

                    }catch ( Exception ex ) {
                        logService.erorLog(logger, "service", "pushMobileMessage", "execution error", ex);
                    }
                }
            });
            thread.start();

        }
        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }




    @Transactional
    @Override
    public   void carStatus(  String incidentId , String handleId ,   String  vehicleId  ,
                              String vehicleStatusCode     ) {

        this.carStatus(incidentId,handleId,Arrays.asList(vehicleId),vehicleStatusCode);

    }

    @Transactional
    @Override
    public void carStatus(String incidentId, String handleId, List<String> vehicleIds, String vehicleStatusCode) {
        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");

        //启用 mobile  发送消息
        if(  !mobileEnable  ){
            logService.infoLog(logger, "service", "pushMobileMessage", "push mobile message enablebl is "  + mobileEnable );
            return  ;
        }
        if (vehicleIds==null||vehicleIds.isEmpty()){
            logService.infoLog(logger, "service", "pushMobileMessage", "do not push mobile message,because vehicleIds is null " );
            return;
        }
        UserInfo userInfo = userService.getUserInfo() ;

        String code = MobileMessageEnum.VEHICLE_STATE_CHANGE.getCode()  ;

        //发送车辆状态变更 推送消息移动终端
        MobileMessageBean mobileMessageBean = new MobileMessageBean();
        mobileMessageBean.setOrgId( userInfo.getOrgId());
        mobileMessageBean.setOrgCode( userInfo.getOrgCode() );
        mobileMessageBean.setIncidentId( incidentId );
        mobileMessageBean.setContent( vehicleStatusCode );

        //新规则 针对车辆id 转 车牌号
        //mobileMessageBean.setVehicleIds( vehicleIds );
        //mobileMessageBean.setVehicles( StringUtils.join(vehicleIds.toArray(), ",")  );
        List<String> vehicleNumbers =  vehicleService.findVehicleNumberCacheByIds(vehicleIds) ;
        mobileMessageBean.setVehicleIds( vehicleNumbers );
        mobileMessageBean.setVehicles( StringUtils.join(vehicleNumbers.toArray(), ",")  );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    logService.infoLog(logger, "service", "pushMobileMessage", "service is started...");
                    Long logStart = System.currentTimeMillis();

                    MoblieNotifyMessageBean moblieNotifyMessageBean = new MoblieNotifyMessageBean();

                    moblieNotifyMessageBean.setCode( code );
                    moblieNotifyMessageBean.setContent( JSON.toJSONString( mobileMessageBean ) );

                    logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl is  : "+ mobileUrl  );
                    logService.infoLog(logger, "service", "pushMobileMessage",  "service is mobileUrl params is : " + JSON.toJSONString( moblieNotifyMessageBean )   );

                    ResponseEntity<Result> rtvo1 = RestfulClient.getClient().postForEntity( mobileUrl , moblieNotifyMessageBean , Result.class);
                    if (rtvo1.getBody().ret.equalsIgnoreCase(ResultStatus.OK)) {
                        logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is mobileUrl is successful  :%s ", "" ) ) ;
                    }

                    Long logEnd = System.currentTimeMillis();
                    logService.infoLog(logger, "service", "pushMobileMessage", String.format("service is finished,execute time is :%sms", logEnd - logStart));

                }catch ( Exception ex ) {
                    logService.erorLog(logger, "service", "pushMobileMessage", "execution error", ex);
                }
            }
        });
        thread.start();
        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }
}
