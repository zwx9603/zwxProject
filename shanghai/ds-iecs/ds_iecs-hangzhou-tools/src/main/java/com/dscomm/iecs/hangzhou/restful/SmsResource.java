package com.dscomm.iecs.hangzhou.restful;

import com.dscomm.iecs.hangzhou.restful.vo.SmsMessageBackVO;
import com.dscomm.iecs.hangzhou.restful.vo.SmsMessageVO;
import com.dscomm.iecs.hangzhou.service.SendMessageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mx.error.UserInterfaceException;
import org.mx.service.error.UserInterfaceServiceErrorException;
import org.mx.service.rest.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 描述： 警情实时推送Rest服务资源
 *
 * @author john peng
 * Date time 2018/7/15 上午9:54
 */

@Path("yunmas_api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SmsResource {

    private static final Log logger = LogFactory.getLog(SmsResource.class);

    private Environment env ;
    private SendMessageService sendMessageService ;
    @Autowired
    public SmsResource( Environment env , SendMessageService sendMessageService ) {
        this.env = env ;
        this.sendMessageService = sendMessageService ;
    }

    @Path("smsApi/batchSendMessage")
    @POST
    public DataVO<SmsMessageBackVO> batchSendMessage(SmsMessageVO vo) {
        try {
            Boolean bl = "true".equalsIgnoreCase( env.getProperty("whetherBuildSign") ) ;
            SmsMessageBackVO res = sendMessageService.batchSendMessage( vo , bl );
            return new DataVO<>( res );
        } catch (UserInterfaceException ex) {
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logger.error( "message fail ", ex );
            return new DataVO<>(new UserInterfaceServiceErrorException(
                    UserInterfaceServiceErrorException.ServiceErrors.SERVICE_OTHER_FAIL));
        }
    }

    @Path("smsApi/test")
    @GET
    public DataVO<SmsMessageBackVO> test(  @QueryParam("mobile") String mobile ) {
        try {
            SmsMessageVO vo = new SmsMessageVO();
            vo.setMobiles( new String[]{ mobile });
            vo.setContent("测试短信");
            SmsMessageBackVO res = sendMessageService.batchSendMessage( vo , true  );
            return new DataVO<>( res );
        } catch (UserInterfaceException ex) {
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logger.error( "message fail ", ex );
            return new DataVO<>(new UserInterfaceServiceErrorException(
                    UserInterfaceServiceErrorException.ServiceErrors.SERVICE_OTHER_FAIL));
        }
    }

}
