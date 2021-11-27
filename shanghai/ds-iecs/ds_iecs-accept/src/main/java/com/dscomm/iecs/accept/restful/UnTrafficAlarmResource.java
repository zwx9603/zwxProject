package com.dscomm.iecs.accept.restful;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.restful.vo.ReceiveUnTrafficAlarmVO;
import com.dscomm.iecs.accept.service.UnTrafficAlarmService;
import com.dscomm.iecs.accept.service.outside.OutsideService;
import com.dscomm.iecs.agent.service.AuthenticateService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * 描述： 非话务转警情 restful 接口
 */
@Path("iecs/v1.0/outside")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UnTrafficAlarmResource {


    private static final Logger logger = LoggerFactory.getLogger(UnTrafficAlarmResource.class);
    private UnTrafficAlarmService unTrafficAlarmService ;
    private OutsideService outsideService ;
    private SessionDataStore sessionDataStore;
    private ServletService servletService ;
    private AuthenticateService authenticateService;
    private UserService userService;

    @Autowired
    public UnTrafficAlarmResource(UnTrafficAlarmService unTrafficAlarmService, OutsideService outsideService, SessionDataStore sessionDataStore, ServletService servletService, AuthenticateService authenticateService, UserService userService) {
        this.unTrafficAlarmService = unTrafficAlarmService;
        this.outsideService = outsideService;
        this.sessionDataStore = sessionDataStore;
        this.servletService = servletService;
        this.authenticateService = authenticateService;
        this.userService = userService;
    }


    /**
     * 消防 收到  转警 ( 非话务 )
     *
     * @return 返回更新结果
     */
    @Path("unTrafficAlarm")
    @POST
    public DataVO<String> saveUnTrafficAlarmIncident( @Context HttpHeaders headers , ReceiveUnTrafficAlarmVO inputInfo ) {
        prepareRequest( headers );
        //参数判断
        if( inputInfo == null || inputInfo.getUnTrafficAlarmVO() == null ){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        String res = unTrafficAlarmService.saveUnTrafficAlarmIncident( inputInfo , inputInfo.getWhetherSaveIncident()   ) ;

        return new DataVO<>(res);
    }


    /**
     * 消防接收 其他系统 电话转警 回执操作 ( 待定 需要是否 )
     *
     * @return 返回更新结果
     */
    @Path("receiveConfirmTrafficAlarm")
    @POST
    public DataVO<Boolean> receiveConfirmTrafficAlarm(    @QueryParam("alarmPhone")String  alarmPhone ,
                                                         @QueryParam("agentNumber")String agentNumber     ) {
        //参数判断
        if (  Strings.isBlank( alarmPhone) || Strings.isBlank( agentNumber )     ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res = unTrafficAlarmService.receiveConfirmTrafficAlarm( alarmPhone , agentNumber    ) ;

        return new DataVO<>(res);
    }


    /**
     * 消防接收 其他系统警情转警 回执操作 ( 待定 需要是否 )
     *
     * @return 返回更新结果
     */
    @Path("receiveConfirmUnTrafficAlarm")
    @POST
    public DataVO<Boolean> receiveConfirmUnTrafficAlarm( @QueryParam("receiveMessageId")String  receiveMessageId ,
                                                         @QueryParam("incidentId")String  incidentId ,
                                                         @QueryParam("agentNumber")String agentNumber  ,
                                                         @QueryParam("backResult") Integer backResult    ) {
        //参数判断
        if (  Strings.isBlank( receiveMessageId) || Strings.isBlank( agentNumber ) || backResult == null   ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res = unTrafficAlarmService.receiveConfirmUnTrafficAlarm( receiveMessageId , incidentId , agentNumber , backResult  ) ;

        return new DataVO<>(res);
    }







    /**
     * 消防 转警
     * @return 返回更新结果
     */
    @Path("transferOutIncident")
    @POST
    public DataVO<Boolean> transferOutIncident(@Context HttpHeaders headers , OutsideInputInfo inputInfo  ) {
        prepareRequest( headers );
        //参数判断
        if (inputInfo == null    || inputInfo.getType() == null || inputInfo.getType().size() < 1     ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res = outsideService.transferOutIncident( inputInfo ) ;

        return new DataVO<>(res);

    }



    /**
     * 消防  其他系统转警 结果
     *
     * @return 返回更新结果
     */
    @Path("receiveTransferOutIncident")
    @POST
    public DataVO<Boolean> receiveTransferOutIncident (  ReceiveUnTrafficAlarmVO inputInfo ) {
        //参数判断
        if (  Strings.isBlank( inputInfo.getIncidentId() )  ||  Strings.isBlank( inputInfo.getOriginalIncidentNumber() )   ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res =  outsideService.receiveTransferOutIncident( inputInfo  ) ;

        return new DataVO<>(res);
    }




    /**
     * 预处理方法
     *
     */
    private void prepareRequest( HttpHeaders headers  ) {
        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;
//        i18nService.setLocal(headers.getHeaderString("language"));
//        String token = headers.getHeaderString("tokenkey");
//        Boolean res = authenticateService.authentication(token);
//        if (!res) {
//            userService.login(token, null , null );
//        }
    }

}
