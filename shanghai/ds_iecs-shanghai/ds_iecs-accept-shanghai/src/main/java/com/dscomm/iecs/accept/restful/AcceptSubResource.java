package com.dscomm.iecs.accept.restful;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.hangzhou.bean.IncidentInformationBean;
import com.dscomm.iecs.accept.hangzhou.bean.IncidentShowBean;
import com.dscomm.iecs.accept.hangzhou.service.IncidentInformationService;
import com.dscomm.iecs.accept.restful.vo.UserSMSLocationVO;
import com.dscomm.iecs.accept.service.LocationService;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.service.AuthenticateService;
import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.AES256Helper;
import com.dscomm.iecs.base.utils.ObtainIPUtils;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.error.UserInterfaceException;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * 描述:提供给第三方调用的服务
 *
 * @author YangFuxi
 * Date Time 2019/10/16 14:58
 */
@Path("iecs/v1.0/brace")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcceptSubResource {
    private static final Logger logger = LoggerFactory.getLogger(AcceptSubResource.class);
    private LogService logService;
    private SessionDataStore sessionDataStore;
    private ServletService servletService ;

    private AuthenticateService authenticateService;
    private LocationService locationService;

    private IncidentInformationService incidentInformationService;

    @Autowired
    public AcceptSubResource(  LogService logService  , SessionDataStore sessionDataStore , ServletService servletService ,
                               AuthenticateService authenticateService , LocationService locationService,IncidentInformationService incidentInformationService ) {
        this.logService = logService;
        this.sessionDataStore  = sessionDataStore ;
        this.servletService = servletService ;
        this.authenticateService = authenticateService ;
        this.locationService = locationService ;
        this.incidentInformationService = incidentInformationService;
    }


    /**
     * 生成tokenkey
     * @param request
     * @return
     */
    @Path("gettokenkey")
    @GET
    public DataVO<String> getTokenkey(@Context HttpServletRequest request) {
        String ip = ObtainIPUtils.getIpAddress(request);
        String token = "";
        String str = String.format("%s,%s,9,test", System.currentTimeMillis(), ip);
        try {
            token = AES256Helper.encrypt(str);
        } catch (Exception ex) {
            logService.erorLog(logger, "rest", "getTokenkey", "get token fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.OTHER_FAIL);
        }
        return new DataVO<>(token);
    }


    /**
     *  接收 短信 定位信息
     * @param headers
     * @param addressInfo
     * @return
     */
    @Path("smsLocationResult")
    @POST
    public DataVO<String> smsLocationResult(@Context HttpHeaders headers, UserSMSLocationVO addressInfo) {
        try {
            logService.infoLog(logger, "restful", "smsLocationResult", "restful is started...");
            Long start = System.currentTimeMillis();

//            String tokenkey = headers.getHeaderString("tokenkey");
//            authenticateService.subAuthenticate(tokenkey);
            String sendResult = locationService.subSMSLocationResultSendToUser(addressInfo);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "smsLocationResult", String.format("restful is finished,execute time is :%sms", end - start));

            return new DataVO<>(sendResult);
        } catch (UserInterfaceException ex) {
            logService.erorLog(logger, "rest", "smsLocationResult", "sub smsLocationResult fail", ex);
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logService.erorLog(logger, "rest", "smsLocationResult", "sub smsLocationResult fail", ex);
            return new DataVO<>(new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.SEND_SMSLOCATIONRESULT_FAIL));
        }
    }

    /**
     * 查询当日火灾，抢险数量以及最新警情
     * */
    @Path("showIncidentInformation")
    @POST
    public DataVO<IncidentInformationBean> showIncidentInformation(HttpHeaders headers){
        try {
            logService.infoLog(logger, "restful", "showIncidentInformation", "restful is started...");
            Long start = System.currentTimeMillis();

            IncidentInformationBean res = incidentInformationService.showIncidentInformation();

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "showIncidentInformation", String.format("restful is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        }catch (Exception e){
            logService.erorLog(logger, "service", "showIncidentInformation", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }

    /**
     * 获取24小时警情信息
     * */
    @Path("twentyFourHoursIncident")
    @POST
    public DataVO<PaginationBean<IncidentShowBean>> twentyFourHoursIncident(@Context HttpHeaders headers, PaginationInputInfo pagination){
        try {
            logService.infoLog(logger, "restful", "twentyFourHoursIncident", "restful is started...");
            Long start = System.currentTimeMillis();

            PaginationBean<IncidentShowBean> res = incidentInformationService.twentyFourHoursIncident(pagination);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "twentyFourHoursIncident", String.format("restful is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        }catch (Exception e){
            logService.erorLog(logger, "service", "twentyFourHoursIncident", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }

}
